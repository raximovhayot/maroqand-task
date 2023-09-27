package uz.raximov.maroqandtask.domain.region;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import uz.raximov.maroqandtask.domain.auth.User;
import uz.raximov.maroqandtask.domain.template.AbstractLongEntity;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
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
}
