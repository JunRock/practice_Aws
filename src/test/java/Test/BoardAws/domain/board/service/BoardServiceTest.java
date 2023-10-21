package Test.BoardAws.domain.board.service;

import Test.BoardAws.auth.dto.UserDto;
import Test.BoardAws.auth.service.LoginAuthService;
import Test.BoardAws.domain.board.dto.BoardRequestDto;
import Test.BoardAws.domain.board.dto.BoardResponseDto;
import Test.BoardAws.domain.board.entity.Board;
import Test.BoardAws.domain.board.entity.QBoard;
import Test.BoardAws.domain.board.repository.BoardRepository;
import Test.BoardAws.domain.user.repository.UserRepository;
import Test.BoardAws.domain.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class BoardServiceTest {
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    BoardService boardService;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LoginAuthService authService;


    @BeforeEach
    public void deleteUser(){
        userRepository.deleteAll();
    }
    @Test
    public void 글등록() throws Exception
    {
        //given
        getUser();

        getBoard();

        //then
        assertThat(boardRepository.count()).isEqualTo(1);
    }

    @Test
    public void 글목록조회() throws Exception
    {
        //given
        getUser();
        getBoard();
        getBoard();
        getBoard();
        //when
        List<BoardResponseDto> allBoard = boardService.findAllBoard();
        //then
        assertThat(allBoard.size()).isEqualTo(3);
    }

    @Test
    public void 글수정() throws Exception
    {
        //given
        getUser();
        getBoard();
        //when
        BoardRequestDto build = BoardRequestDto.builder()
                .content("수정")
                .title("수정제목")
                .build();

        boardService.boardUpdate(1L,build);
        //then
        assertThat("수정").isEqualTo(build.getContent());
        assertThat("수정제목").isEqualTo(build.getTitle());
    }

    private void getUser() {
        UserDto build1 = UserDto.builder()
                .email("email")
                .password("password")
                .nickname("nickname3")
                .build();
        userService.signup(build1);
    }

    private void getBoard() {
        BoardRequestDto build = BoardRequestDto.builder()
                .title("title")
                .content("content")
                .build();
        //when
        boardService.write(build,"email");
    }

    @Test
    public void 카테고리_검색() throws Exception
    {
        //given

        //when

        //then
    }
}