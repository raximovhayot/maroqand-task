package uz.raximov.maroqandtask.domain.region;

import jakarta.persistence.Column;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SqlResultSetMapping;
import lombok.Getter;
import lombok.Setter;
import uz.raximov.maroqandtask.domain.auth.User;
import uz.raximov.maroqandtask.domain.template.AbstractLongEntity;
import uz.raximov.maroqandtask.payload.NameItem;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity(name = "carrier")
@SqlResultSetMapping(name = "CarrierNameItemMapper",
        classes = {
                @ConstructorResult(targetClass = NameItem.class,
                        columns = {
                                @ColumnResult(name = "id", type = Long.class),
                                @ColumnResult(name = "name", type = String.class)
                        })
})
@NamedNativeQuery(name = "Carrier.findRegionCarriers",
        resultSetMapping = "CarrierNameItemMapper",
        query = "select c.id as id, u.name as name from carrier c left join carrier_regions cr on c.id = cr.carrier_id left join users u on c.user_id = u.id where cr.regions_id = :regionId order by u.name",
        resultClass = NameItem.class)
public class Carrier extends AbstractLongEntity {
    @Column(name = "user_id")
    private Long userId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    List<Region> regions;

    public List<Region> getRegions() {
        if (regions == null) regions = new ArrayList<>();
        return regions;
    }

    public List<Place> getPlaces() {
        List<Place> places = new ArrayList<>();
        getRegions().forEach(r -> places.addAll(r.getPlaces()));
        return places;
    }
}
