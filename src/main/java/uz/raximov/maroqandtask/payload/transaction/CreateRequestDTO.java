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
public class CreateRequestDTO {
    @NotEmpty(message = "RequestId ko'rsatilishi shart!")
    private String requestId;
    @NotEmpty(message = "Joy nomi ko'rsatilishi shart!")
    private String placeName;
    @NotEmpty(message = "ProductId ko'rsatilishi shart!")
    private String productId;
}
