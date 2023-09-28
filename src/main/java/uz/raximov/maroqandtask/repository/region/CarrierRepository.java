package uz.raximov.maroqandtask.repository.region;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.raximov.maroqandtask.domain.region.Carrier;
import uz.raximov.maroqandtask.payload.NameItem;

import java.util.List;
import java.util.Optional;

public interface CarrierRepository extends JpaRepository<Carrier, Long> {

    @Query(nativeQuery = true)
    List<NameItem> findRegionCarriers(@Param("regionId") Long regionId);

    Optional<Carrier> findByUser_Name(String name);
}
