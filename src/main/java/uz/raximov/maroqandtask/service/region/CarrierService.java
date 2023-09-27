package uz.raximov.maroqandtask.service.region;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.raximov.maroqandtask.domain.auth.Role;
import uz.raximov.maroqandtask.domain.auth.User;
import uz.raximov.maroqandtask.domain.region.Carrier;
import uz.raximov.maroqandtask.domain.region.Region;
import uz.raximov.maroqandtask.mapper.RegionMapper;
import uz.raximov.maroqandtask.payload.NameItem;
import uz.raximov.maroqandtask.payload.auth.CreateCarrierDTO;
import uz.raximov.maroqandtask.payload.auth.CreateUserDTO;
import uz.raximov.maroqandtask.repository.region.CarrierRepository;
import uz.raximov.maroqandtask.repository.region.RegionRepository;
import uz.raximov.maroqandtask.service.auth.UserService;

import javax.validation.Valid;
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
}
