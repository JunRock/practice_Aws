package Test.BoardAws.auth.controller;

import Test.BoardAws.auth.dto.LoginDto;
import Test.BoardAws.auth.dto.TokenDto;
import Test.BoardAws.auth.service.LoginAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private final LoginAuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginDto loginDto) {
        TokenDto tokenDto = authService.login(loginDto);
        return ResponseEntity.ok(tokenDto);
    }
}
