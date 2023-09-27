package uz.raximov.maroqandtask.repository.auth;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import uz.raximov.maroqandtask.domain.auth.Token;

import java.util.Optional;

public interface TokenRepository extends CrudRepository<Token, Long> {

    @Modifying
    @Query("update token t set t.expired = true, t.revoked = true where t.userId = :userId")
    void revokeAllUserToken(@Param("userId") Long userId);

    @Modifying
    @Query("update token t set t.expired = true, t.revoked = true where t.userToken = :token")
    void revokeAllUserToken(@Param("token") String token);

    Optional<Token> findByUserToken(String userToken);
}
