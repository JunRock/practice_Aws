package Test.BoardAws.domain.board.controller;

import Test.BoardAws.auth.utils.SecurityUtil;
import Test.BoardAws.domain.board.dto.BoardRequestDto;
import Test.BoardAws.domain.board.dto.BoardResponseDto;
import Test.BoardAws.domain.board.entity.Board;
import Test.BoardAws.domain.board.repository.BoardRepository;
import Test.BoardAws.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final BoardRepository boardRepository;
    @PostMapping("/write-board") //글 작성
    public ResponseEntity<Long> writeBoard(@RequestBody BoardRequestDto requestDto){
        String email= String.valueOf(SecurityUtil.getCurrentUsername()); //현재 인증된 객체
        return ResponseEntity.ok(boardService.write(requestDto,email));
    }

    @GetMapping("/board-list") //글 조회
    public ResponseEntity<List<BoardResponseDto>> boardList(){
        List<BoardResponseDto> allBoard = boardService.findAllBoard();
        return ResponseEntity.ok(allBoard);
    }

    @PatchMapping("/board-update/{boardId}") //글 수정
    public ResponseEntity<String> updateBoard(@PathVariable Long boardId, @RequestBody BoardRequestDto requestDto){
        return ResponseEntity.ok(boardService.update(boardId,requestDto));
    }

    @DeleteMapping("/delete-board/{boardId}") //글 삭제
    public String deleteBoard(@PathVariable Long boardId){
        boardRepository.deleteById(boardId);
        ConcurrentHashMap
        return boardId+"가 삭제되었습니다!";
    }

    @GetMapping("/board-details/{boardId}") //글 세부 조회
    public ResponseEntity<BoardResponseDto> boardDetail(@PathVariable Long boardId){
        return ResponseEntity.ok(boardService.findBoard(boardId));
    }

    @GetMapping("/category-board")
    public ResponseEntity<List<BoardResponseDto>> boardCategory(@Nullable @RequestParam("category") String category){
        return ResponseEntity.ok(boardService.findCategory(category));
    }
}
