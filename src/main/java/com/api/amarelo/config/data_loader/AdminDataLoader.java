package com.api.amarelo.config.data_loader;

import com.api.amarelo.model.PaymentStatus;
import com.api.amarelo.model.Role;
import com.api.amarelo.model.SeatType;
import com.api.amarelo.model.User;
import com.api.amarelo.model.enums.PaymentStatusEnum;
import com.api.amarelo.model.enums.RoleEnum;
import com.api.amarelo.model.enums.SeatTypeEnum;
import com.api.amarelo.repository.PaymentStatusRepository;
import com.api.amarelo.repository.RoleRepository;
import com.api.amarelo.repository.SeatTypeRepository;
import com.api.amarelo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Configuration
@Transactional
public class AdminDataLoader implements CommandLineRunner {

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.password}")
    private String adminPassword;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PaymentStatusRepository paymentStatusRepository;

    @Autowired
    private SeatTypeRepository seatTypeRepository;

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(RoleEnum.values()).map(
                roleEnum -> new Role(roleEnum.getId(), roleEnum.getRole())
        ).forEach(roleRepository::save);

        Arrays.stream(PaymentStatusEnum.values()).map(
                paymentStatusEnum -> new PaymentStatus(paymentStatusEnum.getId(), paymentStatusEnum.getStatus())
        ).forEach(paymentStatusRepository::save);

        Arrays.stream(SeatTypeEnum.values()).map(
                seatTypeEnum -> new SeatType(seatTypeEnum.getId(), seatTypeEnum.getType())
        ).forEach(seatTypeRepository::save);

        Optional<User> userOptional = userRepository.findByEmail(adminEmail);
        if (userOptional.isEmpty()) {
            User user = new User();
            user.setEmail(adminEmail);
            user.setPassword(bCryptPasswordEncoder.encode(adminPassword));
            Set<Role> roles = new HashSet<>(roleRepository.findAll());
            user.setRoles(roles);
            user.setCreatedAt(Instant.now());
            user.setUpdatedAt(Instant.now());
            user.setName("admin");
            user.setEnabled(true);
            userRepository.save(user);
        }
    }
}
