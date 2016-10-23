package dp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tianliangxia on 16-10-23.
 */
public class HanMoTaTest {

    @Test
    public void test(){
        String res = "src->des,";
        StringBuilder stringBuilder = new StringBuilder();
        HanMoTa.solve(1, stringBuilder, "src", "mid", "des");
        assertEquals(res, stringBuilder.toString());

        res = "src->mid,src->des,mid->des,";
        stringBuilder = new StringBuilder();
        HanMoTa.solve(2, stringBuilder, "src", "mid", "des");
        assertEquals(res, stringBuilder.toString());
    }

    @Test
    public void test2(){
        StringBuilder stringBuilder1 = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        int n = 10;
        HanMoTa.solve(n, stringBuilder1, "src","mid","des");
        //System.out.println(stringBuilder1.toString());
        HanMoTa.solveWithStack(n, stringBuilder2, "src","mid","des");
        assertEquals(stringBuilder1.toString(), stringBuilder2.toString());
    }

}