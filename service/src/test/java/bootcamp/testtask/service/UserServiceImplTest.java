package bootcamp.testtask.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import bootcamp.testtask.repository.User;
import bootcamp.testtask.repository.UserRepository;

@SpringBootTest
class UserServiceImplTest {

    private static final Sort SORT_BY_EMAIL_ASC = Sort.by("email");

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void testGetAll() {
        List<User> entities = List.of(
                User.builder()
                        .id(null).surname("Doe").name("John").patronymic("Davidson")
                        .email("johndoe@example.com").role(User.Role.CUSTOMER).build(),
                User.builder()
                        .id(null).surname("Smith").name("John").patronymic("Ivanovich")
                        .email("jsmith@example.com").role(User.Role.ADMIN).build()
        );
        userRepository.saveAll(entities);
        PageRequest request = PageRequest.of(0, 10, SORT_BY_EMAIL_ASC);
        List<UserDto> expected = userRepository.findAll(request).stream()
                .map(entity -> UserDto.builder()
                        .id(entity.getId())
                        .surname(entity.getSurname())
                        .name(entity.getName())
                        .patronymic(entity.getPatronymic())
                        .email(entity.getEmail())
                        .role(entity.getRole())
                        .build())
                .collect(Collectors.toList());
        assertIterableEquals(expected, userService.getAll(request));
    }

    @Test
    void testSave() {
        UserDto dto = UserDto.builder()
                .id(null).surname("Smith").name("John").patronymic("Ivanovich")
                .email("jsmith@example.com").role(User.Role.ADMIN).build();
        System.out.println(userRepository.count());;
        dto = userService.save(dto);
        Optional<User> expected = userRepository.findById(dto.getId());
        assertEquals(expected, userRepository.findById(dto.getId()));
    }

}
