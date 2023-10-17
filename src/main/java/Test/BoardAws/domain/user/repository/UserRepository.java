package Test.BoardAws.domain.user.repository;

import Test.BoardAws.domain.user.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @EntityGraph(attributePaths = "authorities") // 쿼리를 수행할 때 Eager 조회로 authorities 정보를 가져옴
    Optional<User> findOneWithAuthoritiesByEmail(String email);

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
