package dp;

/**
 * Created by tianliangxia on 16-10-23.
 */

/***
 * 有一个灯板，有m×n排灯，每按一盏灯，这盏灯及其上下左右的灯状态都会发生改变
 * 现在给一个灯板，灯的亮灭用1 0 表示，求一种方式按灯，使所有的灯灭掉
 *
 * 思路是枚举第一排的按灯的情况，按第二排的时候一定会想办法让第一排全部灭掉
 * 同理第三排时会让第二排灭掉，当最后一排按玩后，检验最后一排是否是全灭的
 *
 * 输入灯板的行数 列树 r c
 * 亮灯的个数 n
 * 接下来n行 亮灯的位置 r  c
 *
 * 输出 r×c的二维数组
 * 1 表示按
 * 0 表示不按
 */

public class LightBoard {
    private int r,c;
    private int[][] board;
    private int[][] press;

    public LightBoard(int r, int c){
        this.r = r;
        this.c = c;
        board = new int[r+1][c+2];
        press = new int[r+1][c+2];
    }

    /***
     *
     * @param r start from 0
     * @param c start form 0
     */
    public void setLight(int r, int c){
        board[r+1][c+1] = 1;
    }
    public void solve(){
        //枚举第一行的按的情况
        press[1][1]=0;
        while (!check()){
            press[1][1]++;
            int pos = 1;
            while (press[1][pos]>1){
                press[1][pos]=0;
                pos++;
                press[1][pos]++;
            }
        }
    }
    private boolean check(){
        for(int i=2;i<=r;i++){
            for(int j=1;j<=c;j++)
                press[i][j] = (board[i-1][j] + press[i-1][j-1] + press[i-1][j]
                + press[i-1][j+1] + press[i-2][j])%2;
        }
        for(int j=1;j<=c;j++)
            if((board[r][j] + press[r][j-1] + press[r][j]
            + press[r][j+1] + press[r-1][j])%2 == 1)
                return false;

        return true;
    }
    public int[][] display(){
        int[][] res = new int[r][c];
        for(int i=1;i<=r;i++)
            for(int j=1;j<=c;j++)
                res[i-1][j-1]=press[i][j];

        return res;
    }
}
