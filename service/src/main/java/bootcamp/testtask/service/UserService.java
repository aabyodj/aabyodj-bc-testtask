package bootcamp.testtask.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

public interface UserService {

    List<UserBriefDto> getBriefsList(Pageable pageable);

    UserDto save(UserDto user);
}
