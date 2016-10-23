package dp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tianliangxia on 16-10-23.
 */
public class GameTest {

    @Test
    public void test(){
        Game game = new Game();
        int row = 4, col = 5;
        game.setSize(row, col);
        int [][] board={
                {1,1,1,1,1},
                {1,0,0,0,1},
                {1,1,1,0,1},
                {0,1,1,1,0}
        };
        game.setBoard(board);
        //game.solve(3,2,3,5);
        //assertEquals(4, game.getMinLen());
        game.solve(3,1,4,4);
        assertEquals(3, game.getMinLen());
        //game.solve(3,2,4,3);
        //assertEquals(-1, game.getMinLen());

    }

}