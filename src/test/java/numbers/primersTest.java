package numbers;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tianliangxia on 16-10-22.
 */
public class primersTest {

    @Test
    public void findAllPrimersLessThanTest(){
        assertArrayEquals(new int[]{2,3,5,7,11,13,17,19}, Primers.findAllPrimersLessThan(20));
    }

}