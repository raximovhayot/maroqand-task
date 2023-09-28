package uz.raximov.maroqandtask.repository.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.raximov.maroqandtask.domain.transaction.Transaction;
import uz.raximov.maroqandtask.payload.statistics.NTPerProduct;
import uz.raximov.maroqandtask.payload.statistics.ScorePerCarrier;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    boolean existsByRequestIdOrOfferId(Long requestId, Long offerId);

    Optional<Transaction> findByTransactionId(String transactionId);

    @Query(value = """
        select t.carrier_id as id, u.name, sum(t.score) as score from transaction t 
        left join carrier c on t.carrier_id = c.id 
        left join users u on c.user_id = u.id
        where t.score > :minimumScore group by t.carrier_id, u.name
    """, nativeQuery = true)
    List<ScorePerCarrier> scorePerCarrier(@Param("minimumScore") int minimumScore);

    @Query(value = """
        select r.product_id as productId, count(t) as transactionCount from transaction t left join request r on t.request_id = r.id group by r.product_id 
    """, nativeQuery = true)
    List<NTPerProduct> nTPerProduct();
}
