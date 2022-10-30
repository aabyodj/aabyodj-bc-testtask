package bootcamp.testtask.service;

import bootcamp.testtask.repository.User.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserBriefDto {

    private Long id;
    private String fullName;
    private String email;
    private Role role;
}
