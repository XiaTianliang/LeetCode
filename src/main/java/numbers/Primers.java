package numbers;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by tianliangxia on 16-10-22.
 */
public class Primers {
    /***
     * 找到所有小于n的质数
     * 检验2*i + 1
     * 仅仅检验奇数
     * @param n
     * @return
     */
    public static int[] findAllPrimersLessThan(int n){
        if(n<2)
            return null;
        List<Integer> primers = new LinkedList<>();
        primers.add(2);
        for(int i=1;2*i+1 < n;i++){
            int candidate = 2*i+1;
            boolean isPrimer = true;
            for(int primer : primers){
                if(candidate%primer == 0){
                    isPrimer = false;
                    break;
                }
            }
            if(isPrimer)
                primers.add(candidate);
        }
        int[] res = new int[primers.size()];
        int k = 0;
        for (Integer primer : primers)
            res[k++] = primer;

        return res;
    }




}
