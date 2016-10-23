package dp;

/**
 * Created by tianliangxia on 16-10-23.
 */

import java.util.Arrays;

/***
 * 讨厌的青蛙
 * 输入是稻田被踩倒的稻子的位置
 * 输出是最长的路径数
 * 判断未路径的依据
 * 至少有三个点
 * 起始点在稻田的外面
 * 每一步的间距相等
 * 在一条直线上
 */
class Pos implements Comparable<Pos>{
    int x, y;
    public Pos(int x, int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public int compareTo(Pos p){
        if(this.x == p.x)
            return this.y - p.y;
        return this.x - p.x;
    }
}
public class GrogPath {
    private Pos[] poses;
    private int n,row,col;
    private int cur;
    public GrogPath(int n, int row, int col){
        this.n = n;
        this.row = row;
        this.col = col;
        poses = new Pos[n];
        cur = 0;
    }
    public void setPos(int x, int y){
        poses[cur++] = new Pos(x, y);
    }

    /***
     * 局部枚举
     * 枚举路径起始的两个点
     * 验证
     * 前一个点未稻田外
     * 等步长在稻田内可以找到
     * @return
     */
    public int solve(){
        Arrays.sort(poses);
        int max_step = 0;
        for(int i=0;i<poses.length-1;i++)
            for(int j=i+1;j<poses.length;j++){
                Pos first = poses[i], second = poses[j];
                int dx = second.x - first.x;
                int dy = second.y - first.y;
                Pos pre = new Pos(first.x - dx, first.y - dy);
                if((pre.x <1 || pre.x > row) ||  (pre.y <1 || pre.y >col)){  // || not && 判断在外面 满足一个条件就可以了
                    Pos third = new Pos(second.x + dx, second.y + dy);
                    if(third.x > row)
                        break;
                    if(third.y < 1 || third.y > col)
                        continue;
                    if(Arrays.binarySearch(poses, third) < 0)  //找不到返回的不一定是-1  只能保证是负数  忘记判断第三个点是否在poses中
                        continue;;

                    int step = 3;
                    Pos next = new Pos(third.x + dx, third.y + dy);
                    while (Arrays.binarySearch(poses, next)>=0){
                        step++;
                        next.x+=dx;
                        next.y+=dy;
                    }
                    if(step > max_step)
                        max_step = step;

                }
            }
        return max_step;
    }
}
