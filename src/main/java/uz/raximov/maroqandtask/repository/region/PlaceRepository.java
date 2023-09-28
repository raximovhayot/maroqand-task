package uz.raximov.maroqandtask.repository.region;

import jakarta.persistence.SqlResultSetMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.raximov.maroqandtask.domain.region.Place;
import uz.raximov.maroqandtask.payload.NameItem;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface PlaceRepository extends JpaRepository<Place, Long> {

    @Query("select new uz.raximov.maroqandtask.payload.NameItem(p.id, p.name) from Place p where p.name in (:name)")
    List<NameItem> findAllByNameIn(Set<String> name);

    @Query("select new uz.raximov.maroqandtask.payload.NameItem(p.id, p.name) from Place p where p.name = :name")
    Optional<NameItem> findByName(String name);
}
