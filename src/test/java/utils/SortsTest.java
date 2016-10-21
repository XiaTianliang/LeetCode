package utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import static utils.Sorts.insertSort;
import static utils.Sorts.mergeSort;
import static utils.Sorts.quickSort;

/**
 * Created by tianliangxia on 16-10-21.
 */

public class SortsTest {
    int[] ints,cp;
    @Before
    public void before(){
        ints = Randoms.ints(50);
        cp = Arrays.copyOf(ints,ints.length);
    }

    @Test
    public void insertSortTest(){
        insertSort(ints, 0 , ints.length-1);
        Arrays.sort(cp);
        Assert.assertArrayEquals(ints, cp);
    }

    @Test
    public void quickSortTest(){
        quickSort(ints, 0 , ints.length-1);
        Arrays.sort(cp);
        Assert.assertArrayEquals(ints, cp);
    }

    @Test
    public void mergeSortTest(){
        mergeSort(ints, 0 , ints.length-1);
        Arrays.sort(cp);
        Assert.assertArrayEquals(ints, cp);
    }
}