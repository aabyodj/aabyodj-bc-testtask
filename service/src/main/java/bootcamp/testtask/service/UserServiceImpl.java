package bootcamp.testtask.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import bootcamp.testtask.repository.User;
import bootcamp.testtask.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserDto> getAll(Sort sort) {
        return userRepository.findAll(sort)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private UserDto toDto(User entity) {
        return UserDto.builder()
                .id(entity.getId())
                .surname(entity.getSurname())
                .name(entity.getName())
                .patronymic(entity.getPatronymic())
                .email(entity.getEmail())
                .role(entity.getRole())
                .build();
    }

    @Override
    public UserDto save(UserDto dto) {
        User entity = toEntity(dto);
        entity = userRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    private User toEntity(UserDto dto) {
        return User.builder()
                .id(dto.getId())
                .surname(dto.getSurname())
                .name(dto.getName())
                .patronymic(dto.getPatronymic())
                .email(dto.getEmail())
                .role(dto.getRole())
                .build();
    }

}
