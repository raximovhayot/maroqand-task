package uz.raximov.maroqandtask.repository.region;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.raximov.maroqandtask.domain.region.Region;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Long> {
    boolean existsByName(String name);

    List<Region> findAllByNameIn(Collection<String> name);

    Optional<Region> findByName(String name);
}
