package utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import static utils.SortUtils.insertSort;
import static utils.SortUtils.mergeSort;
import static utils.SortUtils.quickSort;

/**
 * Created by tianliangxia on 16-10-21.
 */

public class SortUtilsTest {
    int[] ints,cp;
    @Before
    public void before(){
        ints = RandomUtils.ints(50);
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