package uz.raximov.maroqandtask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.raximov.maroqandtask.payload.statistics.NTPerProduct;
import uz.raximov.maroqandtask.payload.statistics.ScorePerCarrier;
import uz.raximov.maroqandtask.repository.transaction.TransactionRepository;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final TransactionRepository transactionRepository;

    public List<ScorePerCarrier> scorePerCarrier(int minimumScore) {
        List<ScorePerCarrier> score = transactionRepository.scorePerCarrier(minimumScore);
        return score.stream().sorted(Comparator.comparing(ScorePerCarrier::getName)).toList();
    }

    public List<NTPerProduct> nTPerProduct() {
        List<NTPerProduct> products = transactionRepository.nTPerProduct();
        return products.stream().filter(nt -> nt.getTransactionCount() > 0).toList();
    }
}
