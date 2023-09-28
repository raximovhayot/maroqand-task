package uz.raximov.maroqandtask.service.region;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.raximov.maroqandtask.domain.auth.Role;
import uz.raximov.maroqandtask.domain.auth.User;
import uz.raximov.maroqandtask.domain.region.Carrier;
import uz.raximov.maroqandtask.domain.region.Region;
import uz.raximov.maroqandtask.exceptions.RestException;
import uz.raximov.maroqandtask.mapper.RegionMapper;
import uz.raximov.maroqandtask.payload.NameItem;
import uz.raximov.maroqandtask.payload.auth.CreateCarrierDTO;
import uz.raximov.maroqandtask.payload.auth.CreateUserDTO;
import uz.raximov.maroqandtask.repository.region.CarrierRepository;
import uz.raximov.maroqandtask.repository.region.RegionRepository;
import uz.raximov.maroqandtask.service.auth.UserService;

import jakarta.validation.Valid;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarrierService {
    private final UserService userService;
    private final RegionRepository regionRepository;
    private final CarrierRepository carrierRepository;
    private final RegionMapper regionMapper;

    @Transactional
    public List<NameItem> create(@Valid CreateCarrierDTO dto) {
        User user = userService.create(CreateUserDTO.builder()
                .name(dto.getName())
                .email(String.format("%s@mail.generated", dto.getName()))
                .password(String.format("%s@password%d", dto.getName(), System.currentTimeMillis()))
                .role(Role.ROLE_CARRIER)
                .build());

        Carrier carrier = new Carrier();
        carrier.setUser(user);
        carrier.setUserId(carrier.getUserId());

        List<Region> regions = regionRepository.findAllByNameIn(dto.getRegionNames());
        carrier.setRegions(regions);
        carrierRepository.save(carrier);
        return regionMapper.toNameItemCollection(carrier.getRegions());
    }

    public List<NameItem> getCarriersForRegion(String regionName) {
        Region region = regionRepository.findByName(regionName)
                .orElseThrow(() -> RestException.restThrow("Bunday region mavjud emas!", HttpStatus.BAD_REQUEST));
        List<NameItem> carriers = carrierRepository.findRegionCarriers(region.getId());
        return carriers.stream().sorted(Comparator.comparing(NameItem::getName)).toList();
    }

    public Carrier findCarrierByName(String name) {
       return carrierRepository.findByUser_Name(name)
               .orElseThrow(() -> RestException.restThrow("Bunday ismli kuryer topilmadi!", HttpStatus.BAD_REQUEST));
    }
}
