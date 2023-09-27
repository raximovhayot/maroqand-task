package uz.raximov.maroqandtask.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.raximov.maroqandtask.domain.auth.User;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
