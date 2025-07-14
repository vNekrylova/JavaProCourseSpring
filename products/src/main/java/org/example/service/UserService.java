package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.dto.UserDto;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserDto(user.getUsername()))
                .toList();
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return new UserDto(user.getUsername());
    }

    public void createUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.username());
        userRepository.save(user);
    }

    public void updateUser(Long id, UserDto userDto) {
        User userFind = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        userFind.setUsername(userDto.username());
        userRepository.save(userFind);
    }

    public void deleteUser(Long id) {
        User userFind = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        userRepository.delete(userFind);
    }
}
