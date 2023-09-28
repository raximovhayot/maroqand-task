package uz.raximov.maroqandtask.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.raximov.maroqandtask.domain.DeliveryRegionsPerNTDataset;
import uz.raximov.maroqandtask.domain.region.Carrier;
import uz.raximov.maroqandtask.domain.region.Place;
import uz.raximov.maroqandtask.domain.transaction.Offer;
import uz.raximov.maroqandtask.domain.transaction.Request;
import uz.raximov.maroqandtask.domain.transaction.Transaction;
import uz.raximov.maroqandtask.exceptions.RestException;
import uz.raximov.maroqandtask.payload.NameItem;
import uz.raximov.maroqandtask.payload.transaction.CreateOfferDTO;
import uz.raximov.maroqandtask.payload.transaction.CreateRequestDTO;
import uz.raximov.maroqandtask.payload.transaction.CreateTransactionDTO;
import uz.raximov.maroqandtask.payload.transaction.ValidatedData;
import uz.raximov.maroqandtask.repository.DeliveryRegionsPerNTDatasetRepository;
import uz.raximov.maroqandtask.repository.region.PlaceRepository;
import uz.raximov.maroqandtask.repository.transaction.OfferRepository;
import uz.raximov.maroqandtask.repository.transaction.RequestRepository;
import uz.raximov.maroqandtask.repository.transaction.TransactionRepository;
import uz.raximov.maroqandtask.service.region.CarrierService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final OfferRepository offerRepository;
    private final RequestRepository requestRepository;
    private final TransactionRepository transactionRepository;
    private final PlaceRepository placeRepository;
    private final CarrierService carrierService;
    private final DeliveryRegionsPerNTDatasetRepository deliveryRegionsPerNTDatasetRepository;

    public void addRequest(@Valid CreateRequestDTO dto) {
        if (requestRepository.existsByRequestId(dto.getRequestId())) {
            throw RestException.restThrow("Bunday requestId allaqachon tizimda mavjud!", HttpStatus.BAD_REQUEST);
        }

        NameItem place = placeRepository.findByName(dto.getPlaceName())
                .orElseThrow(() -> RestException.restThrow("Bunday joy mavjud emas!", HttpStatus.BAD_REQUEST));

        Request request = new Request();
        request.setRequestId(dto.getRequestId());
        request.setProductId(dto.getProductId());
        request.setPlaceId(place.getId());
        requestRepository.save(request);
    }

    public void addOffer(@Valid CreateOfferDTO dto) {
        if (offerRepository.existsByOfferId(dto.getOfferId())) {
            throw RestException.restThrow("Bunday offerId allaqachon tizimda mavjud!", HttpStatus.BAD_REQUEST);
        }

        NameItem place = placeRepository.findByName(dto.getPlaceName())
                .orElseThrow(() -> RestException.restThrow("Bunday joy mavjud emas!", HttpStatus.BAD_REQUEST));

        Offer offer = new Offer();
        offer.setOfferId(dto.getOfferId());
        offer.setProductId(dto.getProductId());
        offer.setPlaceId(place.getId());
        offerRepository.save(offer);
    }

    @Transactional
    public void addTransaction(@Valid CreateTransactionDTO dto) {
        ValidatedData data = validateTransactionDetails(dto);
        Transaction transaction = new Transaction();
        transaction.setTransactionId(dto.getTransactionId());
        transaction.setCarrierId(data.getCarrier().getId());
        transaction.setOfferId(data.getOffer().getId());
        transaction.setRequestId(data.getRequest().getId());
        transaction.setScore(0);
        transactionRepository.save(transaction);
        incrementTransactionCount(data.getRegionIds());
    }

    private ValidatedData validateTransactionDetails(CreateTransactionDTO dto) {
        Carrier carrier = carrierService.findCarrierByName(dto.getCarrierName());

        Request request = requestRepository.findByRequestId(dto.getRequestId()).orElseThrow(() -> RestException.restThrow("Bunday requestId lik request mavjud emas!", HttpStatus.BAD_REQUEST));

        Offer offer = offerRepository.findByOfferId(dto.getOfferId()).orElseThrow(() -> RestException.restThrow("Bunday offerId lik request mavjud emas!", HttpStatus.BAD_REQUEST));

        if (transactionRepository.existsByRequestIdOrOfferId(request.getId(), offer.getId())) {
            throw RestException.restThrow("Bunday requestId yoki offerIdlik transaction mavjud!", HttpStatus.BAD_REQUEST);
        }

        if (!Objects.equals(offer.getProductId(), request.getProductId())) {
            throw RestException.restThrow("Request va Offer bir xil mahsulot identificatioriga ega emas!",  HttpStatus.BAD_REQUEST);
        }

        boolean containsBothPlaces = new HashSet<>(carrier.getPlaces()).containsAll(Arrays.asList(request.getPlace(), offer.getPlace()));
        if (!containsBothPlaces) {
            throw RestException.restThrow("Kuryer etkazib berish, ham olib ketish joylariga (ya'ni, tegishli hududlarga) xizmat qilishi kerak!", HttpStatus.BAD_REQUEST);
        }

        Long requestRegionId = Optional.ofNullable(request.getPlace()).map(Place::getRegionId)
                .orElseGet(() -> placeRepository.findById(request.getPlaceId()).map(Place::getRegionId).orElse((long)-1));


        Long offerRegionId = Optional.ofNullable(offer.getPlace()).map(Place::getRegionId)
                .orElseGet(() -> placeRepository.findById(offer.getPlaceId()).map(Place::getRegionId).orElse((long) -1));

        return ValidatedData.builder()
                .request(request)
                .offer(offer)
                .carrier(carrier)
                .regionIds(Set.of(requestRegionId, offerRegionId))
                .build();
    }

    private void incrementTransactionCount(@NonNull Set<Long> regionIds) {
        for (Long regionId : regionIds) {
            DeliveryRegionsPerNTDataset dataset = deliveryRegionsPerNTDatasetRepository.findByRegionId(regionId).orElse(new DeliveryRegionsPerNTDataset(regionId));
            dataset.incrementTransactionCount();
            deliveryRegionsPerNTDatasetRepository.save(dataset);
        }
    }

    public boolean evaluateTransaction(int score, String transactionId) {
        Transaction transaction = transactionRepository.findByTransactionId(transactionId)
                .orElseThrow(() -> RestException.restThrow("Bunday transactionId lik transaction mavjud emas!", HttpStatus.BAD_REQUEST));
        transaction.setScore(score);
        transactionRepository.save(transaction);
        return score >= 1 && score <= 10;
    }
}
