package bootcamp.testtask.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bootcamp.testtask.service.UserDto;
import bootcamp.testtask.service.UserService;
import bootcamp.testtask.service.autolog.AutoLogged;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/users", produces = "application/json")
@RequiredArgsConstructor
public class UserController {

    private static final Sort SORT_BY_EMAIL_ASC = Sort.by("email");

    private final UserService userService;
    private final RestProperties restProperties;

    @GetMapping
    @AutoLogged
    public List<UserDto> getAll(@RequestParam(defaultValue = "1") int page) {
        page = Integer.max(page - 1, 0);
        PageRequest request = PageRequest.of(page, restProperties.getPageSize(), SORT_BY_EMAIL_ASC);
        return userService.getAll(request);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @AutoLogged
    public UserDto addNewUser(@RequestBody @Valid UserDto user) {
        user.setId(null);
        return userService.save(user);
    }
}
