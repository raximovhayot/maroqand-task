package uz.raximov.maroqandtask.payload.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransactionDTO {
    @NotEmpty(message = "transactionId bo'sh bo'lmasligi kerak!")
    private String transactionId;
    @NotEmpty(message = "carrierName bo'sh bo'lmasligi kerak!")
    private String carrierName;
    @NotEmpty(message = "requestId bo'sh bo'lmasligi kerak!")
    private String requestId;
    @NotEmpty(message = "offerId bo'sh bo'lmasligi kerak!")
    private String offerId;
}
