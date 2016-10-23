package dp;

/**
 * Created by tianliangxia on 16-10-23.
 */

/***
 * 汗莫塔问题
 * 给定三个柱子  src mid des
 * 输出将n个圆盘从src移动到des上的路径
 */

class StackElement{
    public String src, mid, des;
    public int n;
    public StackElement(int n, String src, String mid, String des){
        this.n = n;
        this.src = src;
        this.mid = mid;
        this.des = des;
    }
}

public class HanMoTa {
    public static void solve(int n, StringBuilder stringBuilder, String src, String mid, String des){
        if(stringBuilder == null)
            throw new NullPointerException("stringBuilder is null");
        if(n == 1){
            stringBuilder.append(src+"->"+des+",");
            return;
        }

        solve(n-1, stringBuilder, src, des, mid);
        stringBuilder.append(src+"->"+des+",");
        solve(n-1, stringBuilder,mid, src, des);
    }

    public static void solveWithStack(int n, StringBuilder stringBuilder, String src, String mid, String des){
        if(stringBuilder == null)
            throw new NullPointerException("stringBuilder is null");

        MyStack<StackElement> stack = new MyStack<>();
        stack.push(new StackElement(n, src, mid, des));
        StackElement element;
        while (!stack.isEmpty()){
            element = stack.pop();
            if(element.n == 1){
                stringBuilder.append(element.src+"->"+element.des+",");
            }else {
                stack.push(new StackElement(element.n-1, element.mid, element.src, element.des));
                stack.push(new StackElement(1, element.src, element.mid, element.des));
                //stringBuilder.append(element.src+"->"+element.des+",");   递归时上面返回才会执行这一步  stack会直接执行
                stack.push(new StackElement(element.n-1, element.src, element.des, element.mid));
            }
        }
    }


}
