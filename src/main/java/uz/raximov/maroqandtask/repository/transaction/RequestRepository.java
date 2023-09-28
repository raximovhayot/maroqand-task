package uz.raximov.maroqandtask.repository.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.raximov.maroqandtask.domain.transaction.Request;

import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Long> {
    boolean existsByRequestId(String requestId);

    Optional<Request> findByRequestId(String requestId);
}
