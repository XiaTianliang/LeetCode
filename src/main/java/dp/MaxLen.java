package dp;

/**
 * Created by tianliangxia on 16-10-23.
 */

import utils.StringUtils;

import java.util.Arrays;
import java.util.Objects;

/***
 * 最大公共子序列
 */
public class MaxLen {

    public static int solve(String str1, String str2){
        if(StringUtils.isEmpty(str1) || StringUtils.isEmpty(str2))
            return 0;
        return help(str1, str2, str1.length()-1, str2.length()-1);
    }

    public static int help(String str1, String str2, int i, int j){
        if(i == -1 || j == -1)
            return 0;
        if(str1.charAt(i) == str2.charAt(j))
            return 1 + help(str1, str2, i-1, j-1);
        else {
            return Math.max(help(str1, str2, i-1, j), help(str1, str2, i,j-1));
        }
    }

    //性能问题

    public static int[][] maxlen;

    public static int solve2(String str1, String str2){
        if(StringUtils.isEmpty(str1) || StringUtils.isEmpty(str2))
            return 0;
        maxlen = new int[str1.length()][str2.length()];
        for (int[] i : maxlen){
            Arrays.fill(i, -1);
        }
        return help2(str1, str2, str1.length()-1, str2.length()-1);
    }

    public static int help2(String str1, String str2, int i, int j){
        if(i == -1 || j == -1)
            return 0;
        if(str1.charAt(i) == str2.charAt(j)){
            if(maxlen[i-1][j-1] == -1 ){
                maxlen[i-1][j-1]  = help(str1, str2, i-1, j-1);
            }
            return 1 +  maxlen[i-1][j-1] ;
        }

        else {
            if(maxlen[i-1][j] == -1){
                maxlen[i-1][j] = help(str1, str2, i-1, j);
            }
            if(maxlen[i][j-1] == -1){
                maxlen[i][j-1] = help(str1, str2, i,j-1);
            }
            return Math.max(maxlen[i-1][j], maxlen[i][j-1]);
        }
    }


    public static int[][] maxlen3;
    public static int solve3(String str1, String str2){
        if(StringUtils.isEmpty(str1) || StringUtils.isEmpty(str2))
            return 0;

        maxlen3 = new int[str1.length() + 1][str2.length() + 1];

        for(int i=1;i<=str1.length();i++)
            for (int j=1;j<=str2.length();j++)
                if(str1.charAt(i-1) == str2.charAt(j-1))
                    maxlen3[i][j] = 1 + maxlen3[i-1][j-1];
                else {
                    maxlen3[i][j] = Math.max(maxlen3[i][j-1], maxlen3[i-1][j]);
                }


        return maxlen3[str1.length()][str2.length()];
    }





}
