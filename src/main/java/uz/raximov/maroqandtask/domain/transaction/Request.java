package uz.raximov.maroqandtask.domain.transaction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import uz.raximov.maroqandtask.domain.region.Place;
import uz.raximov.maroqandtask.domain.template.AbstractLongEntity;

@Getter
@Setter
@Entity
public class Request extends AbstractLongEntity {
    @Column(unique = true, nullable = false)
    private String requestId;

    @Column(name = "place_id", nullable = false)
    private Long placeId;
    @JoinColumn(name = "place_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Place place;

    @Column(nullable = false)
    private String productId;
}
