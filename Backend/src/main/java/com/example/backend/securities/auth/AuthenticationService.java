package com.example.backend.securities.auth;

import com.example.backend.enums.RoleEnum;
import com.example.backend.enums.UserStatusEnum;
import com.example.backend.securities.user.User;
import com.example.backend.securities.user.RoleRepository;
import com.example.backend.securities.user.UserRepository;
import com.example.backend.securities.config.JwtService;
import com.example.backend.securities.token.Token;
import com.example.backend.securities.token.TokenRepository;
import com.example.backend.securities.token.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    public String register(RegisterRequest registerRequest){
        var user = User.builder()
                .userAccount(registerRequest.getUserAccount())
                .userEmail(registerRequest.getUserEmail())
                .userPassword(passwordEncoder.encode(registerRequest.getUserPassword()))
                .userCreatedAt(LocalDateTime.now())
                .userUpdatedAt(LocalDateTime.now())
                .userStatus(UserStatusEnum.ACTIVE)
                .role(roleRepository.findByRoleName(RoleEnum.ROLE_USER))
                .build();

        repository.save(user);

        return "Register successfully!";
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUserEmail(), authenticationRequest.getUserPassword()));
        User user = repository.findByUserEmail(authenticationRequest.getUserEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        // Chặn bỏ sự hoạt động của các tài khoản unactive
        if(user.getUserStatus().equals(UserStatusEnum.UNACTIVE)) return null;

        UserDetails userDetails = new UserDetailsImpl(user);
        var jwtToken = jwtService.generateToken(userDetails);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .userAccount(user.getUserAccount())
                .token(jwtToken)
                .build();
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokensByUser(user.getUserID());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(t -> {
            t.setExpired(1);
            t.setRevoked(1);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(0)
                .expired(0)
                .build();
        tokenRepository.save(token);
    }
}

