package uz.raximov.maroqandtask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.raximov.maroqandtask.payload.auth.AuthenticationResponse;
import uz.raximov.maroqandtask.payload.auth.SignInRequest;
import uz.raximov.maroqandtask.payload.auth.SignUpRequest;
import uz.raximov.maroqandtask.payload.response.ApiResult;
import uz.raximov.maroqandtask.service.auth.AuthenticationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/sign-in")
    public ApiResult<AuthenticationResponse> signIn(@Valid @RequestBody SignInRequest request) {
        return ApiResult.successResponse(
                authenticationService.signIn(request)
        );
    }

    /**
     * Bu yerdan ro'yxatdan o'tgan user admin rolida bo'ladi. Va aktiv ham bo'ladi.
     * Berilgan docda mavhum yozilgan aynan qanday bo'lishi
     * */
    @PostMapping("/sign-up")
    public ApiResult<AuthenticationResponse> signUp(@RequestBody SignUpRequest request) {
        return ApiResult.successResponse(
                authenticationService.signUp(request)
        );
    }
}
