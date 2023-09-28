package uz.raximov.maroqandtask.service.auth;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import uz.raximov.maroqandtask.configuration.security.JwtTokenConfig;
import uz.raximov.maroqandtask.domain.auth.Role;
import uz.raximov.maroqandtask.domain.auth.Token;
import uz.raximov.maroqandtask.domain.auth.TokenType;
import uz.raximov.maroqandtask.domain.auth.User;
import uz.raximov.maroqandtask.mapper.UserMapper;
import uz.raximov.maroqandtask.payload.auth.AuthenticationResponse;
import uz.raximov.maroqandtask.payload.auth.CreateUserDTO;
import uz.raximov.maroqandtask.payload.auth.SignInRequest;
import uz.raximov.maroqandtask.payload.auth.SignUpRequest;
import uz.raximov.maroqandtask.repository.auth.TokenRepository;
import uz.raximov.maroqandtask.repository.auth.UsersRepository;

@Service
@Validated
@RequiredArgsConstructor
public class AuthenticationService {
    private final UsersRepository usersRepository;
    private final TokenRepository tokenRepository;
    private final JwtTokenConfig jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final UserService userService;

    @Transactional
    public AuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = usersRepository.findByUsername(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        tokenRepository.revokeAllUserToken(user.getId());
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                        .accessToken(jwtToken)
                        .accountDto(userMapper.toAccountDTO(user))
                        .build();
    }

    public AuthenticationResponse signUp(SignUpRequest request) {
        User user = userService.create(CreateUserDTO.builder()
                        .name(request.getName())
                        .email(request.getEmail())
                        .password(request.getPassword())
                        .role(Role.ROLE_ADMIN)
                .build());
        var jwtToken = jwtService.generateToken(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .accountDto(userMapper.toAccountDTO(user))
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .userId(user.getId())
                .user(user)
                .userToken(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }
}