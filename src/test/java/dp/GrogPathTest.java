package dp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tianliangxia on 16-10-23.
 */
public class GrogPathTest {

    @Test
    public void test(){
        GrogPath gp = new GrogPath(14, 6,7);
        gp.setPos(2,1);
        gp.setPos(6,6);
        gp.setPos(4,2);
        gp.setPos(2,5);
        gp.setPos(2,6);
        gp.setPos(2,7);
        gp.setPos(3,4);
        gp.setPos(6,1);
        gp.setPos(6,2);
        gp.setPos(2,3);
        gp.setPos(6,3);
        gp.setPos(6,4);
        gp.setPos(6,5);
        gp.setPos(6,7);
        assertEquals(7, gp.solve());
    }
}