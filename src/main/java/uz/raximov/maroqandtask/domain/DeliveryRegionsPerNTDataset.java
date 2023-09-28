package uz.raximov.maroqandtask.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.raximov.maroqandtask.domain.region.Region;

@Getter
@Setter
@Entity(name = "delivery_regions_per_nt_dataset")
@NoArgsConstructor
public class DeliveryRegionsPerNTDataset {

    @Id
    @Column(name = "region_id")
    private Long regionId;
    @JoinColumn(name = "region_id", insertable = false, updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private Region region;

    @Column(name = "transaction_count")
    private Integer transactionCount;

    public DeliveryRegionsPerNTDataset(Long regionId) {
        this.regionId = regionId;
    }

    public void incrementTransactionCount() {
        this.transactionCount = this.transactionCount + 1;
    }
}
