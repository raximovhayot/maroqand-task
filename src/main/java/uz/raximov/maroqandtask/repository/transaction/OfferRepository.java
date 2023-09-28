package uz.raximov.maroqandtask.repository.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.raximov.maroqandtask.domain.transaction.Offer;

import java.util.Optional;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    boolean existsByOfferId(String offerId);

    Optional<Offer> findByOfferId(String requestId);
}
