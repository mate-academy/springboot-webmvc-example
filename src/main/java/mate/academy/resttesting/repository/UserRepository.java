package mate.academy.resttesting.repository;

import mate.academy.resttesting.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
