package bootcamp.testtask.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

public interface UserService {

    List<UserDto> getAll(Pageable pageable);

    UserDto save(UserDto user);
}
