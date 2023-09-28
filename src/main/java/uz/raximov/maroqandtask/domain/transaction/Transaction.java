package uz.raximov.maroqandtask.domain.transaction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import uz.raximov.maroqandtask.domain.region.Carrier;
import uz.raximov.maroqandtask.domain.template.AbstractLongEntity;

@Entity
@Getter
@Setter
public class Transaction extends AbstractLongEntity {
    @Column(nullable = false)
    private String transactionId;

    @Column(name = "carrier_id")
    private Long carrierId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrier_id", insertable = false, updatable = false)
    private Carrier carrier;

    @Column(name = "request_id")
    private Long requestId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id", insertable = false, updatable = false)
    private Request request;

    @Column(name = "offer_id")
    private Long offerId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offer_id", insertable = false, updatable = false)
    private Offer offer;
}
