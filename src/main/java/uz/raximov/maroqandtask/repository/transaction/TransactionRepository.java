package uz.raximov.maroqandtask.repository.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.raximov.maroqandtask.domain.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    boolean existsByRequestIdOrOfferId(Long requestId, Long offerId);
}
