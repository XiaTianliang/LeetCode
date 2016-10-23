package dp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tianliangxia on 16-10-23.
 */
public class LightBoardTest {

    @Test
    public void test(){
        int r=5,c=6;
        LightBoard lg = new LightBoard(5,6);
        int[][] board={
                {0,1,1,0,1,0},
                {1,0,0,1,1,1},
                {0,0,1,0,0,1},
                {1,0,0,1,0,1},
                {0,1,1,1,0,0,},
        };
        for(int i=0;i<board.length;i++)
            for(int j=0;j<board[0].length;j++)
                if(board[i][j] == 1)
                    lg.setLight(i,j);

        lg.solve();

        int[][] press = {
                {1,0,1,0,0,1},
                {1,1,0,1,0,1},
                {0,0,1,0,1,1},
                {1,0,0,1,0,0},
                {0,1,0,0,0,0}};
//        int[][] display = lg.display();
//        for(int i=0;i<display.length;i++) {
//            for (int j = 0; j < display[0].length; j++)
//                System.out.print(display[i][j] + " ");
//            System.out.println();
//        }
        assertArrayEquals(press, lg.display());
    }

}