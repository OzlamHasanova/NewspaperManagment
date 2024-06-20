package com.example.newspapermanagment.service.impl;

import com.example.newspapermanagment.dto.request.UserLoginRequest;
import com.example.newspapermanagment.dto.request.UserRegisterRequest;
import com.example.newspapermanagment.dto.response.UserResponse;
import com.example.newspapermanagment.entity.User;
import com.example.newspapermanagment.enums.UserRole;
import com.example.newspapermanagment.repository.UserRepository;
import com.example.newspapermanagment.service.JwtService;
import com.example.newspapermanagment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;


    @Override
    public UserResponse save(UserRegisterRequest userRegisterRequest) {
        try {
            User user = User.builder()
                    .username(userRegisterRequest.getUsername())
                    .password(passwordEncoder.encode(userRegisterRequest.getPassword()))
                    .email(userRegisterRequest.getEmail())
                    .role(UserRole.ADMIN).build();
            userRepository.save(user);
            String token = jwtService.generateToken(user);
            return UserResponse.builder().token(token).build();
//        }catch (WeatherProjectException ex){
//            log.error(messageSource.getMessage("user.register.error", null,
//                    LocaleContextHolder.getLocale()), ex);
//            throw new WeatherProjectException(ExceptionConstants.INVALID_REQUEST_DATA,messageSource.getMessage("INVALID_REQUEST_DATA", null,
//                    LocaleContextHolder.getLocale()));
        } finally {

        }

    }

    @Override
    public UserResponse auth(UserLoginRequest userRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userRequest.getUsername(),
                    userRequest.getPassword()));
            User user = userRepository.findByEmail(userRequest.getUsername()).orElseThrow();
            String token = jwtService.generateToken(user);
            return UserResponse.builder().token(token).build();

//        }catch (WeatherProjectException ex){
//            log.error(messageSource.getMessage("user.login.error", null,
//                    LocaleContextHolder.getLocale()), ex);
//            throw new WeatherProjectException(ExceptionConstants.INVALID_REQUEST_DATA,messageSource.getMessage("INVALID_REQUEST_DATA", null,
//                    LocaleContextHolder.getLocale()));
//        }

        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        userRepository.findById(id);
    }
}
