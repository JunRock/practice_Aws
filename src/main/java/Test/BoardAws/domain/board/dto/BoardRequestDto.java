package Test.BoardAws.domain.board.dto;

import Test.BoardAws.domain.board.entity.Board;
import Test.BoardAws.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequestDto {
    private String title;
    private String content;
    private String category;
    public Board toEntity(User user){
        return Board.builder()
                .title(title)
                .content(content)
                .category(category)
                .user(user)
                .build();
    }
}

