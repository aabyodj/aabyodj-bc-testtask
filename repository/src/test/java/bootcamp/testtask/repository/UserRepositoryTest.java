package bootcamp.testtask.repository;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import bootcamp.testtask.repository.User.Role;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testNullConstraints() {
        User user = new User();
        assertThatExceptionOfType(DataIntegrityViolationException.class)
                .isThrownBy(() -> userRepository.save(user));
        user.setName("John");
        assertThatExceptionOfType(DataIntegrityViolationException.class)
                .isThrownBy(() -> userRepository.save(user));
        user.setSurname("Silver");
        assertThatExceptionOfType(DataIntegrityViolationException.class)
                .isThrownBy(() -> userRepository.save(user));
        user.setPatronymic("Johnson");
        assertThatExceptionOfType(DataIntegrityViolationException.class)
                .isThrownBy(() -> userRepository.save(user));
        user.setEmail("johnsilver@example.com");
        assertThatExceptionOfType(DataIntegrityViolationException.class)
                .isThrownBy(() -> userRepository.save(user));
        user.setRole(Role.values()[0]);
        assertThatNoException()
                .isThrownBy(() -> {
                    Long id = userRepository.save(user).getId();
                    userRepository.deleteById(id);
                });
    }

    @Test
    void testLengthConstraints() {
        User user = new User();
        user.setName("John");
        user.setSurname("Silver");
        user.setPatronymic("Johnson");
        user.setEmail("johnsilver@example.com");
        user.setRole(Role.values()[0]);
        user.setSurname("a".repeat(41));
        assertThatExceptionOfType(DataIntegrityViolationException.class)
                .isThrownBy(() -> userRepository.save(user));
        user.setSurname("a");
        user.setName("a".repeat(21));
        assertThatExceptionOfType(DataIntegrityViolationException.class)
                .isThrownBy(() -> userRepository.save(user));
        user.setName("a");
        user.setPatronymic("a".repeat(41));
        assertThatExceptionOfType(DataIntegrityViolationException.class)
                .isThrownBy(() -> userRepository.save(user));
        user.setPatronymic("a");
        user.setEmail("a".repeat(51));
        assertThatExceptionOfType(DataIntegrityViolationException.class)
                .isThrownBy(() -> userRepository.save(user));
        user.setEmail("a");
        assertThatNoException()
                .isThrownBy(() -> {
                    Long id = userRepository.save(user).getId();
                    userRepository.deleteById(id);
                });
    }

}
