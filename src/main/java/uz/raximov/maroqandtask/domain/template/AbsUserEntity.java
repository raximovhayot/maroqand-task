package uz.raximov.maroqandtask.domain.template;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.raximov.maroqandtask.domain.auth.User;

@Getter
@Setter
@MappedSuperclass
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class AbsUserEntity {

    @JoinColumn(name = "created_by", updatable = false)
    @CreatedBy
    @ManyToOne
    private User createdBy;

    @JoinColumn(name = "updated_by")
    @LastModifiedBy
    @ManyToOne
    private User updatedBy;
}