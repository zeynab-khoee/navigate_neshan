package com.neshan.navigate_neshan.Service;


import com.neshan.navigate_neshan.Enum.RoleType;
import com.neshan.navigate_neshan.Mapper.UserMapper;
import com.neshan.navigate_neshan.Model.AuthenticationRequest;
import com.neshan.navigate_neshan.Model.AuthenticationResponse;
import com.neshan.navigate_neshan.Model.RegisterRequest;
import com.neshan.navigate_neshan.Model.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = UserInfo.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(RoleType.USER)
                .build();
        userService.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userService.findUser(request.getEmail());
        var jwtToken = jwtService.generateToken(UserMapper.INSTANCE.userDtoToUsers(user));
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}