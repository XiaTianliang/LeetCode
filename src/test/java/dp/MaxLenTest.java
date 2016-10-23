package dp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tianliangxia on 16-10-23.
 */
public class MaxLenTest {

    @Test
    public void test(){
        String str1 = "abcdef";
        String str2 = "acf";
        assertEquals(3, MaxLen.solve(str1, str2));

        str1 = "asdfghjkl";
        str2 = "avcfgvd";
        assertEquals(3, MaxLen.solve(str1, str2));
    }

    @Test
    public void test2(){
        String str1 = "abcdef";
        String str2 = "acf";
        assertEquals(3, MaxLen.solve2(str1, str2));

        str1 = "asdfghjkl";
        str2 = "avcfgvd";
        assertEquals(3, MaxLen.solve2(str1, str2));
    }

    @Test
    public void test3(){
        String str1 = "abcdef";
        String str2 = "acf";
        assertEquals(3, MaxLen.solve3(str1, str2));

        str1 = "asdfghjkl";
        str2 = "avcfgvd";
        assertEquals(3, MaxLen.solve3(str1, str2));
    }
}