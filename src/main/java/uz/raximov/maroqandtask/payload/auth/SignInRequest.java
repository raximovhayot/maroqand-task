package uz.raximov.maroqandtask.payload.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequest {
    @Email(message = "To'g'ri formatdagi email kiriting!")
    private String email;
    @NotBlank(message = "Parolni kiritish majburiy!")
    private String password;
}