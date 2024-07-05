package com.api.amarelo.service;

import com.api.amarelo.dto.*;
import com.api.amarelo.exception.EntityNotFoundException;
import com.api.amarelo.exception.UserAlreadyExistsException;
import com.api.amarelo.model.ConfirmationToken;
import com.api.amarelo.model.Role;
import com.api.amarelo.model.User;
import com.api.amarelo.repository.ConfirmationTokenRepository;
import com.api.amarelo.repository.RoleRepository;
import com.api.amarelo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private MappingService mappingService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private TokenService tokenService;

    public void register(RegisterDTO registerDTO) {
        if (userRepository.existsByEmail(registerDTO.getEmail())) {
            throw new UserAlreadyExistsException("User already exists");
        }

        User user = mappingService.toModel(registerDTO);
        Role role = roleRepository.findById(2L).orElseThrow();
        user.setPassword(bCryptPasswordEncoder.encode(registerDTO.getPassword()));
        user.setEnabled(false);
        user.setRoles(Set.of(role));
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());
        userRepository.save(user);

        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setUser(user);
        confirmationToken.setToken(UUID.randomUUID().toString());
        confirmationToken.setCreatedAt(Instant.now());
        confirmationToken.setExpiresAt(Instant.now().plusSeconds(600));
        confirmationTokenRepository.save(confirmationToken);

        String link = "http://localhost:8080/api/users/confirm?token=" + confirmationToken.getToken();
        Email email = new Email(
                user.getEmail(),
                "Email confirm",
                "Link to confirm your registration: " + link
        );
        emailService.send(email);
    }

    public TokenDTO login(LoginDTO loginDTO) {
        User user = userRepository.findByEmail(loginDTO.email()).orElseThrow(
                () -> new BadCredentialsException("Invalid email")
        );
        if (!bCryptPasswordEncoder.matches(loginDTO.password(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        String token = tokenService.generateToken(user);
        return new TokenDTO(token, Instant.now().plusSeconds(300));
    }

    public void confirmEmail(String token) {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token).orElseThrow(
                () -> new EntityNotFoundException("Token not found")
        );
        User user = confirmationToken.getUser();
        if (confirmationToken.getExpiresAt().isBefore(Instant.now())) {
            throw new AccessDeniedException("Token expired");
        }
        user.setEnabled(true);
        userRepository.save(user);
    }

    public UserDTO getById(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User not found with this id: " + userId)
        );
        return mappingService.toDto(user);
    }

    public void delete(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User not found with this id: " + userId)
        );
        userRepository.delete(user);
    }

    public Page<UserDTO> getAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(
                user -> mappingService.toDto(user)
        );
    }

}
