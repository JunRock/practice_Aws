package Test.BoardAws.domain.user;

import Test.BoardAws.domain.BaseTimeEntity;
import Test.BoardAws.domain.user.dto.UserUpdateDto;
import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseTimeEntity {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "email", length = 50, unique = true)
    private String email;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "nickname", length = 50)
    private String nickname;

    @Column(name = "activated")
    private boolean activated;

    @Enumerated(EnumType.STRING)
    @ElementCollection //컬렉션 객체임을 알려줌
    private Set<Authority> authorities;

    public void update(UserUpdateDto updateDto){
        this.email=updateDto.getEmail();
        this.nickname=updateDto.getNickname();
    }
}
