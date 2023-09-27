package uz.raximov.maroqandtask.domain.region;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import uz.raximov.maroqandtask.domain.template.AbstractLongEntity;

import java.util.List;

@Getter
@Setter
@Entity
public class Region extends AbstractLongEntity {

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "regions", fetch = FetchType.LAZY)
    List<Carrier> carriers;
}
