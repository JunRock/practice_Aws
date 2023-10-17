package Test.BoardAws.domain.board.service;

import Test.BoardAws.auth.utils.SecurityUtil;
import Test.BoardAws.domain.board.dto.BoardRequestDto;
import Test.BoardAws.domain.board.dto.BoardResponseDto;
import Test.BoardAws.domain.board.entity.Board;
import Test.BoardAws.domain.board.repository.BoardRepository;
import Test.BoardAws.domain.board.repository.BoardRepositoryImpl;
import Test.BoardAws.domain.user.User;
import Test.BoardAws.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserService userService;
    private final BoardRepositoryImpl repository;

    public Long write(BoardRequestDto requestDto,String email){
        User user=userService.findUser(email);
        return boardRepository.save(requestDto.toEntity(user)).getBoardId();
    }

    @Transactional(readOnly = true)
    public List<BoardResponseDto> findAllBoard() {
        return boardRepository.findAll().stream()
                .map(BoardResponseDto::new)
                .collect(Collectors.toList());
    }

    public String update(Long boardId, BoardRequestDto requestDto) {
        Board board = getBoard(boardId);
        User user=userService.findUser(SecurityUtil.getCurrentUsername());
        if(board.getUser().getUserId()==user.getUserId()){
            board.update(requestDto);
            return boardId+"가 수정되었습니다";
        }
        else
            return "글을 작성한 유저가 아니므로 수정할 수 없습니다.";
    }

    private Board getBoard(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(()->
                new IllegalArgumentException("존재하지 않는 게시판"));
    }

    public BoardResponseDto findBoard(Long boardId) {
        Board board=getBoard(boardId); //영속성 컨텍스트
        return new BoardResponseDto(board);
    }

    public List<BoardResponseDto> findCategory(String category) {
        return repository.getBoardList(category).stream()
                .map(BoardResponseDto::new)
                .collect(Collectors.toList());
    }


}
