package bootcamp.testtask.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import bootcamp.testtask.repository.User;
import bootcamp.testtask.repository.UserRepository;

class UserServiceImplTest {

    private static final Sort SORT_BY_EMAIL_ASC = Sort.by("email");

    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService= new UserServiceImpl(userRepository);
    }

    @Test
    void testGetAll() {
        List<User> entities = List.of(
                User.builder()
                        .id(2L).surname("Doe").name("John").patronymic("Davidson")
                        .email("johndoe@example.com").role(User.Role.CUSTOMER).build(),
                User.builder()
                        .id(1L).surname("Smith").name("John").patronymic("Ivanovich")
                        .email("jsmith@example.com").role(User.Role.ADMIN).build()
        );
        List<UserDto> dtos = List.of(
                UserDto.builder()
                        .id(2L).surname("Doe").name("John").patronymic("Davidson")
                        .email("johndoe@example.com").role(User.Role.CUSTOMER).build(),
                UserDto.builder()
                        .id(1L).surname("Smith").name("John").patronymic("Ivanovich")
                        .email("jsmith@example.com").role(User.Role.ADMIN).build()
        );

        when(userRepository.findAll(SORT_BY_EMAIL_ASC)).thenReturn(entities);
        assertIterableEquals(dtos, userService.getAll(SORT_BY_EMAIL_ASC));
    }

    @Test
    void testSave() {
        User entity = User.builder()
                .id(null).surname("Smith").name("John").patronymic("Ivanovich")
                .email("jsmith@example.com").role(User.Role.ADMIN).build();
        UserDto rawDto = UserDto.builder()
                .id(null).surname("Smith").name("John").patronymic("Ivanovich")
                .email("jsmith@example.com").role(User.Role.ADMIN).build();
        UserDto savedDto = UserDto.builder()
                .id(1L).surname("Smith").name("John").patronymic("Ivanovich")
                .email("jsmith@example.com").role(User.Role.ADMIN).build();

        when(userRepository.save(entity)).then(invocation -> {
            User e = invocation.getArgument(0, User.class);
            e.setId(1L);
            return e;
        });
        assertEquals(savedDto, userService.save(rawDto));
    }

}
