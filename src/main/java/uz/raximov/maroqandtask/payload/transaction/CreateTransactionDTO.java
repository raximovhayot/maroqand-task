package uz.raximov.maroqandtask.payload.transaction;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransactionDTO {
    @NotBlank(message = "transactionId bo'sh bo'lmasligi kerak!")
    private String transactionId;
    @NotBlank(message = "carrierName bo'sh bo'lmasligi kerak!")
    private String carrierName;
    @NotBlank(message = "requestId bo'sh bo'lmasligi kerak!")
    private String requestId;
    @NotBlank(message = "offerId bo'sh bo'lmasligi kerak!")
    private String offerId;
}
