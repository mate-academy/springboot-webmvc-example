package mate.academy.resttesting.repository;

import java.util.List;
import mate.academy.resttesting.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(
        locations = "classpath:application-test.properties")
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void save_Ok() {
        User bob = new User();
        userRepository.save(bob);
        List<User> actual = userRepository.findAll();
        assertNotNull(actual);
        assertEquals(1, actual.size());
    }

    @Test
    void findAllByNameContains_Ok() {
        User bob = new User("Bob");
        User alice = new User("Alice");
        User boris = new User("Boris");
        User bruce = new User("Bruce");
        userRepository.save(bob);
        userRepository.save(alice);
        userRepository.save(boris);
        userRepository.save(bruce);

        List<User> actual = userRepository.findAll();
        assertNotNull(actual);
        assertEquals(4, actual.size());

        List<User> usersWithNameContainsBo = userRepository.findAllByNameContains("Bo");
        assertNotNull(usersWithNameContainsBo);
        assertEquals(2, usersWithNameContainsBo.size());
        assertTrue(usersWithNameContainsBo.contains(bob));
        assertTrue(usersWithNameContainsBo.contains(boris));

        List<User> usersWithNameContainsA = userRepository.findAllByNameContains("A");
        assertNotNull(usersWithNameContainsA);
        assertEquals(1, usersWithNameContainsA.size());
        assertTrue(usersWithNameContainsA.contains(alice));
    }
}
