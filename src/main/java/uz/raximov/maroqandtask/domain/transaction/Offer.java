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
public class Offer extends AbstractLongEntity {
    @Column(unique = true, nullable = false)
    private String offerId;

    @Column(name = "place_id", nullable = false)
    private Long placeId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id", insertable = false, updatable = false)
    private Place place;

    @Column(nullable = false)
    private String productId;
}
