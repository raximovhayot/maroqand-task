package uz.raximov.maroqandtask.service.region;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import uz.raximov.maroqandtask.domain.region.Place;
import uz.raximov.maroqandtask.domain.region.Region;
import uz.raximov.maroqandtask.exceptions.RestException;
import uz.raximov.maroqandtask.payload.NameItem;
import uz.raximov.maroqandtask.payload.region.CreateRegionDTO;
import uz.raximov.maroqandtask.repository.region.PlaceRepository;
import uz.raximov.maroqandtask.repository.region.RegionRepository;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Validated
@RequiredArgsConstructor
public class RegionService {

    private final RegionRepository regionRepository;
    private final PlaceRepository placeRepository;

    @Transactional
    public List<String> create(@Valid CreateRegionDTO dto) {
        validateCreationDetails(dto);

        Region region = new Region();
        region.setName(dto.getName());
        regionRepository.save(region);
        return savePlaces(dto.getPlaces(), region.getId());
    }

    private void validateCreationDetails(CreateRegionDTO dto) {
        if (regionRepository.existsByName(dto.getName())) {
            throw RestException.restThrow("Bunday hudud mavjud!", HttpStatus.BAD_REQUEST);
        }

        if (!CollectionUtils.isEmpty(dto.getPlaces())) {
            Set<String> places = dto.getPlaces();
            List<NameItem> oldNames = placeRepository.findAllByNameIn(places);
            oldNames.forEach(n -> places.remove(n.getName()));
//            dto.setPlaces(places);
        }
    }

    private List<String> savePlaces(Set<String> places, Long regionId) {
        List<Place> newPlaces = places.stream().map(placeName -> {
            Place place = new Place();
            place.setName(placeName);
            place.setRegionId(regionId);
            return place;
        }).collect(Collectors.toList());

        return placeRepository.saveAll(newPlaces).stream().sorted().map(Place::getName).collect(Collectors.toList());
    }
}
