package uz.raximov.maroqandtask.repository.region;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.raximov.maroqandtask.domain.region.Region;

import java.util.Collection;
import java.util.List;

public interface RegionRepository extends JpaRepository<Region, Long> {
    boolean existsByName(String name);

    List<Region> findAllByNameIn(Collection<String> name);
}
