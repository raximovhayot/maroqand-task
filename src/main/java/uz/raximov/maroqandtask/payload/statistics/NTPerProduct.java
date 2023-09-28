package uz.raximov.maroqandtask.payload.statistics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NTPerProduct {
    private String productId;
    private Integer transactionCount;
}
