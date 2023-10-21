package Test.BoardAws.domain.board.service;

import Test.BoardAws.domain.board.entity.Board;
import Test.BoardAws.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class BoardServiceAop {
    private final BoardRepository boardRepository;

    @Before("execution(* Test.BoardAws.domain.board.service.BoardService.board*(..))")
    public Board get1Board(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Board board=boardRepository.findById((Long) args[0]).orElseThrow(()->
                new IllegalArgumentException("존재하지 않는 게시판"));
        System.out.println("AOP적용");
        return board;
    }
}
