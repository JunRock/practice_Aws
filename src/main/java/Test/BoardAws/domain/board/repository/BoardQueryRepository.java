package Test.BoardAws.domain.board.repository;

import Test.BoardAws.domain.board.entity.Board;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardQueryRepository { //ISP분리 원칙을 지킴?
    List<Board> getBoardList(String email);
}
