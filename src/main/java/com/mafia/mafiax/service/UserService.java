package com.mafia.mafiax.service;

import com.mafia.mafiax.dto.UserDTO;
import com.mafia.mafiax.dto.UserRegistrationDTO;
import com.mafia.mafiax.entity.User;
import com.mafia.mafiax.exceptions.UserNotFoundException;
import com.mafia.mafiax.mapper.UserMapper;
import com.mafia.mafiax.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserDTO createUser(UserRegistrationDTO registrationDTO) {
        if (userRepository.findByUsername(registrationDTO.username()).isPresent()) {
            throw new DataIntegrityViolationException("Username already exists: " + registrationDTO.username());
        }

        String hashedPassword = passwordEncoder.encode(registrationDTO.password());

        User user = User.builder()
                .username(registrationDTO.username())
                .password(hashedPassword)
                .build();

        return userMapper.toDTO(userRepository.save(user));
    }

    public UserDTO findById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        return userMapper.toDTO(user);
    }

    // Метод для использования в UserDetailsService (Spring Security)
    @Transactional(readOnly = true)
    public User findUserEntityByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
    }
}