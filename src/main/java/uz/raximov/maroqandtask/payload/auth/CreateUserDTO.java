package uz.raximov.maroqandtask.payload.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.raximov.maroqandtask.domain.auth.Role;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO {
    @NotBlank(message = "Ism/Familyani kiritish majburiy!")
    private String name;

    @Email(message = "To'g'ri formatdagi email kiriting!")
    private String email;

    @NotBlank(message = "Parolni kiritish majburiy!")
    private String password;

    @NotNull(message = "User roli ko'rsatilishi kerak!")
    private Role role;
}
