package dp;

/**
 * Created by tianliangxia on 16-10-23.
 */

/***
 * 连连看游戏
 * 给一个棋盘  1表示有东西  0表示空
 * 给出两个点的坐标  判断两个点能否通过空的位置连接
 * 有效点像半导体课上学习的迷宫算法
 * 深度或者宽度搜索均可
 */

enum Direction{
    up,down,left,right;

    int [] move(){
        int[] res= new int[2];
        switch (this){
            case up:
                res[0]=-1;
                return res;
            case down:
                res[0]=1;
                return res;
            case left:
                res[1]=-1;
                return res;
            case right:
                res[1]=1;
                return res;
            default:
                throw  new IllegalArgumentException("Direction");
        }
    }
}
public class Game {
    int row, col;
    int[][] board;
    int[][] mark;//原始数据与走的数据分开保存
    int minLen;

    public void setSize(int r, int c){
        row = r;
        col = c;
    }
    public void setBoard(int[][] board){
        this.board = new int[row+2][col+2];
        for(int i=0;i<board.length;i++)
//            for (int j=1;j<=Game.col;j++){
//                Game.board[i][j]=board[i-1][j-1];
//            }
        System.arraycopy(board[i],0,this.board[i+1],1,board[i].length);
    }

    /***
     * if shortest path found return path length
     * otherwise return -1
     * @return
     */
    public  void solve(int src_r, int src_c, int des_r, int des_c){
        minLen = Integer.MAX_VALUE;
        mark = new int[row+2][col+2];
        help(src_r, src_c, des_r, des_c, 0, null);
    }

    private void help(int src_r, int src_c, int des_r, int des_c,int step, Direction direction){
//        if(step>=minLen)
//            return;
//        if(src_c == des_c && src_r == des_r){
//            minLen = step;
//            return;
//        }
//        //不能走
//        if(board[src_r][src_c] == 1)
//            return;
        for (Direction d : Direction.values()){
            int[] tmp = d.move();
            tmp[0]+=src_r;
            tmp[1]+=src_c;
            //判断条件
            //有没有到目的地
            //走过没有mark
            //范围之内
            if( !(tmp[0]>=0 && tmp[0]<row+2 && tmp[1]>=0 && tmp[1]<col+2) )
                continue;

            if(tmp[0] == des_r && tmp[1] == des_c){
                if(direction == null)
                    minLen = 1;
                else {
                    if(!direction.equals(d)){
                        step++;
                    }
                    if(step < minLen)
                        minLen = step;
                }
                continue;
            }

            if(board[tmp[0]][tmp[1]] == 0 && mark[tmp[0]][tmp[1]] == 0){
                mark[tmp[0]][tmp[1]] = 1;
                if(direction == null){
                    step = 1;
                }else if(!direction.equals(d)){
                    step++;
                }
                help(tmp[0], tmp[1], des_r, des_c, step, d);
                mark[tmp[0]][tmp[1]] = 0;
            }
        }
    }

    public int getMinLen(){
        if(minLen == Integer.MAX_VALUE)
            return -1;
        return minLen;
    }

}
