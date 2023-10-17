package Test.BoardAws.domain.user.service;

import Test.BoardAws.auth.dto.UserDto;
import Test.BoardAws.domain.user.Authority;
import Test.BoardAws.domain.user.User;
import Test.BoardAws.domain.user.dto.UserResponseDto;
import Test.BoardAws.domain.user.dto.UserUpdateDto;
import Test.BoardAws.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDto signup(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        User user = User.builder()
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nickname(userDto.getNickname())
                .authorities(Collections.singleton(Authority.ROLE_USER))
                .activated(true)
                .build();
        userRepository.save(user);
        return userDto;
    }

    @Transactional(readOnly = true)
    public UserResponseDto findByEmail(String email) {
        User user = findUser(email);
        return getBuild(user);
    }


    public UserResponseDto updateUser(UserUpdateDto updateDto, String name) {
        User user=findUser(name);
        user.update(updateDto);
        return getBuild(user);
    }

    public User findUser(String email){
        return userRepository.findByEmail(email).orElseThrow(()->
                new IllegalArgumentException("존재하지 않는 이유"));
    }

    private UserResponseDto getBuild(User user) {
        return UserResponseDto.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .activated(user.isActivated())
                .authorities(user.getAuthorities())
                .createDt(user.getCreateDt())
                .modifyDt(user.getModifyDt())
                .build();
    }
}
