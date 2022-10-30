package bootcamp.testtask.service;

import java.util.List;

import org.springframework.data.domain.Sort;

public interface UserService {

    List<UserDto> getAll(Sort sort);

    UserDto save(UserDto user);
}
