package bootcamp.testtask.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping
    @AutoLogged
    public List<UserDto> getAll() {
        return userService.getAll(SORT_BY_EMAIL_ASC);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @AutoLogged
    public UserDto addNewUser(@RequestBody @Valid UserDto user) {
        user.setId(null);
        return userService.save(user);
    }
}
