package Test.BoardAws.domain.user.controller;

import Test.BoardAws.auth.dto.UserDto;
import Test.BoardAws.auth.utils.SecurityUtil;
import Test.BoardAws.domain.user.dto.UserResponseDto;
import Test.BoardAws.domain.user.dto.UserUpdateDto;
import Test.BoardAws.domain.user.repository.UserRepository;
import Test.BoardAws.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/signup") //유저 회원가입
    public ResponseEntity<UserDto> signup(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.signup(userDto));
    }

    @GetMapping("/user") //현재 로그인한 유저 정보 조회
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<UserResponseDto> getMyUserInfo() {
        String email= SecurityUtil.getCurrentUsername();
        UserResponseDto responseDto=userService.findByEmail(email);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/user/{email}") //특정 유저 정보 조회
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<UserResponseDto> getUserInfo(@PathVariable String email) {
        UserResponseDto responseDto=userService.findByEmail(email);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/update") //유저 수정
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<UserResponseDto> update(@RequestBody UserUpdateDto updateDto){
        String email= SecurityUtil.getCurrentUsername();
        UserResponseDto responseDto = userService.updateUser(updateDto, email);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/delete/{userId}") //유저 삭제
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public String deleteUser(@PathVariable Long userId){
        userRepository.deleteById(userId);
        return userId+"가 삭제 되었습니다!";
    }
}
