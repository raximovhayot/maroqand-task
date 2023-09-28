package uz.raximov.maroqandtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.raximov.maroqandtask.domain.DeliveryRegionsPerNTDataset;

import java.util.Optional;

public interface DeliveryRegionsPerNTDatasetRepository extends JpaRepository<DeliveryRegionsPerNTDataset, Long> {

    Optional<DeliveryRegionsPerNTDataset> findByRegionId(Long regionId);
}
