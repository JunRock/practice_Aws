package Test.BoardAws.domain.board.dto;

import Test.BoardAws.domain.board.entity.Board;
import Test.BoardAws.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BoardResponseDto {

    private String title;
    private String content;
    private String nickName;
    private LocalDateTime createDt;
    private LocalDateTime modifyDt;
    private String category;

    public BoardResponseDto(Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();
        this.nickName = board.getUser().getNickname(); //LAZY강제 초기화
        this.createDt = board.getCreateDt();
        this.modifyDt = board.getModifyDt();
        this.category=board.getCategory();
    }
}
