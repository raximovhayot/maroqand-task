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
public class CreateOfferDTO {
    @NotBlank(message = "RequestId ko'rsatilishi shart!")
    private String offerId;
    @NotBlank(message = "Joy nomi ko'rsatilishi shart!")
    private String placeName;
    @NotBlank(message = "ProductId ko'rsatilishi shart!")
    private String productId;
}
