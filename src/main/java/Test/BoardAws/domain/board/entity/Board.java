package Test.BoardAws.domain.board.entity;

import Test.BoardAws.domain.BaseTimeEntity;
import Test.BoardAws.domain.board.dto.BoardRequestDto;
import Test.BoardAws.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "board")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long boardId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "category")
    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void update(BoardRequestDto requestDto){
        this.title= requestDto.getTitle();
        this.content=requestDto.getContent();
        System.out.println("글 수정이 완료되었습니다");
    }
}
