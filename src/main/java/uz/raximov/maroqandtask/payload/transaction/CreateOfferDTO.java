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
public class CreateOfferDTO {
    @NotEmpty(message = "RequestId ko'rsatilishi shart!")
    private String offerId;
    @NotEmpty(message = "Joy nomi ko'rsatilishi shart!")
    private String placeName;
    @NotEmpty(message = "ProductId ko'rsatilishi shart!")
    private String productId;
}
