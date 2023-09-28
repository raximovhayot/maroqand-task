package uz.raximov.maroqandtask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.raximov.maroqandtask.domain.DeliveryRegionsPerNTDataset;
import uz.raximov.maroqandtask.mapper.RegionMapper;
import uz.raximov.maroqandtask.payload.NameItem;
import uz.raximov.maroqandtask.payload.statistics.DeliveryRegionsPerNT;
import uz.raximov.maroqandtask.payload.statistics.NTPerProduct;
import uz.raximov.maroqandtask.payload.statistics.ScorePerCarrier;
import uz.raximov.maroqandtask.repository.DeliveryRegionsPerNTDatasetRepository;
import uz.raximov.maroqandtask.repository.transaction.TransactionRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final TransactionRepository transactionRepository;
    private final DeliveryRegionsPerNTDatasetRepository deliveryRegionsPerNTDatasetRepository;
    private final RegionMapper regionMapper;

    public List<ScorePerCarrier> scorePerCarrier(int minimumScore) {
        List<ScorePerCarrier> score = transactionRepository.scorePerCarrier(minimumScore);
        return score.stream().sorted(Comparator.comparing(ScorePerCarrier::getName)).toList();
    }

    public List<NTPerProduct> nTPerProduct() {
        List<NTPerProduct> products = transactionRepository.nTPerProduct();
        return products.stream().filter(nt -> nt.getTransactionCount() > 0).toList();
    }

    public List<DeliveryRegionsPerNT> deliveryRegionsPerNT() {
        List<DeliveryRegionsPerNTDataset> datasets = deliveryRegionsPerNTDatasetRepository.findAll();
        Map<Integer, List<NameItem>> dataMap = new HashMap<>();
        for (DeliveryRegionsPerNTDataset dataset : datasets) {
            List<NameItem> val = dataMap.getOrDefault(dataset.getTransactionCount(), new ArrayList<>());
            val.add(regionMapper.toNameItem(dataset.getRegion()));
            val = val.stream().sorted(Comparator.comparing(NameItem::getName)).collect(Collectors.toList());
            dataMap.put(dataset.getTransactionCount(), val);
        }

        List<DeliveryRegionsPerNT> res = new ArrayList<>();
        for (Map.Entry<Integer, List<NameItem>> entry : dataMap.entrySet()) {
            res.add(new DeliveryRegionsPerNT(entry.getKey(), entry.getValue()));
        }
        return res.stream().sorted(Comparator.comparing(DeliveryRegionsPerNT::getTransactionNumber)).toList();
    }
}
