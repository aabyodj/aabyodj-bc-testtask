package bootcamp.testtask.service;

import java.util.List;
import java.util.StringJoiner;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import bootcamp.testtask.repository.User;
import bootcamp.testtask.repository.UserRepository;
import bootcamp.testtask.service.autolog.AutoLogged;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @AutoLogged
    @Override
    public List<UserBriefDto> getBriefsList(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(this::toBriefDto)
                .toList();
    }

    private UserBriefDto toBriefDto(User entity) {
        return UserBriefDto.builder()
                .id(entity.getId())
                .fullName(new StringJoiner(" ")
                        .add(entity.getSurname())
                        .add(entity.getName())
                        .add(entity.getPatronymic())
                        .toString())
                .email(entity.getEmail())
                .role(entity.getRole())
                .build();
    }

    @AutoLogged
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
