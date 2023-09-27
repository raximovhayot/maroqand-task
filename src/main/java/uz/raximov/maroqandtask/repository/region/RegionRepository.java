package uz.raximov.maroqandtask.repository.region;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.raximov.maroqandtask.domain.region.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {
    boolean existsByName(String name);
}
