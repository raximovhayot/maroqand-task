package uz.raximov.maroqandtask.domain.region;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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

    @OneToMany(mappedBy = "region")
    private List<Place> places;
}
