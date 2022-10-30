package bootcamp.testtask.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<UserBriefDto> getBriefsList(Pageable pageable);

    UserDto save(UserDto user);
}
