package Test.BoardAws.domain.board.repository;

import Test.BoardAws.domain.board.entity.Board;
import Test.BoardAws.domain.board.entity.QBoard;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.util.StringUtils;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;

import static Test.BoardAws.domain.board.entity.QBoard.board;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardQueryRepository{
    private final EntityManager em;

    @Override
    public List<Board> getBoardList(String category) {
        JPAQueryFactory jpaQueryFactory=new JPAQueryFactory(em);
        QBoard board= QBoard.board;
        return jpaQueryFactory.selectFrom(board)
                .where(eqCategory(category))
                .fetch();
    }

    public BooleanExpression eqCategory(String category) {
        if (category == null) {
            return null;
        }
        return board.category.eq(category);
    }
}
