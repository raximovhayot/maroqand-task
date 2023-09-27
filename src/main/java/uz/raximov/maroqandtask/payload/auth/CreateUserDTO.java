package uz.raximov.maroqandtask.payload.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.raximov.maroqandtask.domain.auth.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO {
    @NotEmpty(message = "Ism/Familyani kiritish majburiy!")
    private String name;

    @Email(message = "To'g'ri formatdagi email kiriting!")
    private String email;

    @NotEmpty(message = "Parolni kiritish majburiy!")
    private String password;

    @NotNull(message = "User roli ko'rsatilishi kerak!")
    private Role role;
}
