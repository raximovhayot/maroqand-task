package uz.raximov.maroqandtask.payload.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarrierDTO {

    @NotBlank(message = "Kuriyerning ismini kiritishingiz shart!")
    private String name;
    private Set<String> regionNames;
}
