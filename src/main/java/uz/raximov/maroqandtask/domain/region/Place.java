package uz.raximov.maroqandtask.domain.region;

import jakarta.persistence.Column;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.SqlResultSetMappings;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import uz.raximov.maroqandtask.domain.template.AbstractLongEntity;
import uz.raximov.maroqandtask.payload.NameItem;

@Getter
@Setter
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "region_place", columnNames = {"name", "region_id"})})
@SqlResultSetMappings(value = {
        @SqlResultSetMapping(name = "NameItemMapper",
        classes = {
                @ConstructorResult(targetClass = NameItem.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "name", type = String.class)
                })
        })
})
public class Place extends AbstractLongEntity {

    @Column
    private String name;

    @Column(name = "region_id")
    private Long regionId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", insertable = false, updatable = false)
    private Region region;
}
