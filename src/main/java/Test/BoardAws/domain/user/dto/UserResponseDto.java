package Test.BoardAws.domain.user.dto;

import Test.BoardAws.domain.user.Authority;
import Test.BoardAws.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private String email;

    private String password;

    private String nickname;

    private boolean activated;

    private Set<Authority> authorities;
    private LocalDateTime createDt;
    private LocalDateTime modifyDt;

}
