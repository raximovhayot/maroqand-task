package uz.raximov.maroqandtask.configuration.auditing;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import uz.raximov.maroqandtask.domain.auth.User;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<User> {

    @Override
    public @NotNull Optional<User> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication == null || !authentication.isAuthenticated()
                || "anonymousUser".equals(String.valueOf(authentication.getPrincipal())))) {
            return Optional.of(((User) authentication.getPrincipal()));
        }
        return Optional.empty();
    }
}