package uz.raximov.maroqandtask.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import uz.raximov.maroqandtask.domain.auth.User;
import uz.raximov.maroqandtask.exceptions.RestException;
import uz.raximov.maroqandtask.payload.auth.CreateUserDTO;
import uz.raximov.maroqandtask.repository.auth.UsersRepository;

import jakarta.validation.Valid;

@Service
@Validated
@RequiredArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

   public User create(@Valid CreateUserDTO dto) {
       validateUserCreationDetails(dto);
       User user = new User();
       user.setName(dto.getName());
       user.setUsername(dto.getEmail());
       user.setRole(dto.getRole());
       user.setPassword(passwordEncoder.encode(dto.getPassword()));
       return usersRepository.save(user);
   }

   private void validateUserCreationDetails(CreateUserDTO dto) {
       if (usersRepository.findByUsername(dto.getEmail()).isPresent()) {
           throw RestException.restThrow("Ushbu email allaqachon ro'yxatdan o'tgan!", HttpStatus.BAD_REQUEST);
       }
   }
}
