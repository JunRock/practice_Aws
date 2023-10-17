package Test.BoardAws.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserUpdateDto {
    @NotNull
    private String email;

    @NotNull
    private String nickname;
}
