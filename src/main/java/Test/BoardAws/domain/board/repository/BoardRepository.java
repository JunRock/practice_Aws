package Test.BoardAws.domain.board.repository;

import Test.BoardAws.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long>,BoardQueryRepository{
    
}
