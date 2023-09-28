package uz.raximov.maroqandtask.payload.region;

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
public class CreateRegionDTO {
    @NotBlank(message = "Hudud nomi ko'rsatilishi kerak!")
    private String name;
    private Set<String> places;
}
