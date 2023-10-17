package Test.BoardAws.domain.user.service;

import Test.BoardAws.auth.dto.UserDto;
import Test.BoardAws.domain.user.User;
import Test.BoardAws.domain.user.dto.UserUpdateDto;
import Test.BoardAws.domain.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void init(){
        userRepository.deleteAll();
    }

    @Test
    public void 회원가입() throws Exception
    {
        //given
        UserDto build1 = UserDto.builder()
                .email("email")
                .password("password")
                .nickname("nickname")
                .build();

        UserDto build2 = UserDto.builder()
                .email("email")
                .password("password")
                .nickname("nickname")
                .build();


        //when
        userService.signup(build1);
        userService.signup(build2);
        //then
        fail("중복 회원");
    }

    @Test
    public void 회원정보수정() throws Exception
    {
        //given
        UserDto userDto=new UserDto();
        userDto.setEmail("email");
        userDto.setPassword("pw");
        userDto.setNickname("nickname");


        //when
        User user = userDto.toEntity();
        UserUpdateDto updateDto=new UserUpdateDto();
        UserUpdateDto build = updateDto.builder()
                .email("modify")
                .nickname("mod")
                .build();

        user.update(build);
        //then
        assertThat(user.getEmail()).isEqualTo("modify");
        assertThat(user.getNickname()).isEqualTo("mod");
    }

    
}