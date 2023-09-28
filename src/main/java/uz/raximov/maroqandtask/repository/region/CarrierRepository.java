package uz.raximov.maroqandtask.repository.region;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.raximov.maroqandtask.domain.region.Carrier;
import uz.raximov.maroqandtask.payload.NameItem;

import java.util.List;

public interface CarrierRepository extends JpaRepository<Carrier, Long> {

    @Query(value = "select c.id, u.name  from carrier c left join carrier_regions cr on c.id = cr.carriers_id left join users u where c.user_id = u.id where cr.regions_id = :regionId", nativeQuery = true)
    List<NameItem> findRegionCarriers(@Param("regionId") Long regionId);
}
