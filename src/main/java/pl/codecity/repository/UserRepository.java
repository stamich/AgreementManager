package pl.codecity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.codecity.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findOneById(Long id);
    User findOneForUpdateById(Long id);
    User findOneByLoginId(String loginId);
    User findOneByEmail(String email);
}
