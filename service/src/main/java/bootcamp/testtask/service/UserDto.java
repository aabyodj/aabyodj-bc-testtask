package bootcamp.testtask.service;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import bootcamp.testtask.repository.User.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;

    @NotNull
    @Pattern(regexp = "[A-Za-z]{1,40}")
    private String surname;

    @NotNull
    @Pattern(regexp = "[A-Za-z]{1,20}")
    private String name;

    @NotNull
    @Pattern(regexp = "[A-Za-z]{1,40}")
    private String patronymic;

    @NotNull
    @Email(regexp = ".{3,50}")
    private String email;

    @NotNull
    private Role role;
    
}
