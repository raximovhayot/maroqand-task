package uz.raximov.maroqandtask.repository.region;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.raximov.maroqandtask.domain.region.Place;
import uz.raximov.maroqandtask.payload.NameItem;

import java.util.List;
import java.util.Set;


public interface PlaceRepository extends JpaRepository<Place, Long> {

    List<NameItem> findAllByNameIn(Set<String> name);
}
