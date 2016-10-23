package linkList;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tianliangxia on 16-10-22.
 */
public class ListNodesTest {

    @Test
    public void constructTest(){
        int[] vals = new int[]{1,2,3,4,5};
        ListNode node = ListNodes.construct(vals);
        assertEquals(node.toString(), "1->2->3->4->5");
    }

    @Test
    public void constructTest2(){
        ListNode node = ListNodes.construct("1->2->3->4->5");
        assertEquals(node.toString(), "1->2->3->4->5");

        node = ListNodes.construct("1,2,3,4,5");
        assertEquals(node.toString(), "1->2->3->4->5");

        node = ListNodes.construct("[1,2,3,4,5]");
        assertEquals(node.toString(), "1->2->3->4->5");

        node = ListNodes.construct("[1->2->3->4->5]");
        assertEquals(node.toString(), "1->2->3->4->5");

        node = ListNodes.construct("1");
        assertEquals(node.toString(), "1");

        node = ListNodes.construct("[1]");
        assertEquals(node.toString(), "1");

    }


    @Test
    public void testSort(){
        ListNode root = ListNodes.construct("5,4,3,2,1");
        ListNode res = ListNodes.sort(root);
        assertEquals("1->2->3->4->5",res.toString());
    }

    @Test
    public void mergeTest(){
        ListNode l1 = ListNodes.construct("1,3");
        ListNode l2 = ListNodes.construct("2,4,6");
        ListNode merge = ListNodes.merge(l1, l2);
        assertEquals("1->2->3->4->6", merge.toString());

        l2 = ListNodes.construct("2,4,6");
        merge = ListNodes.merge(null, l2);
        assertEquals("2->4->6", merge.toString());
    }

    @Test
    public void removeDuplicationTest(){
        ListNode root = ListNodes.construct("1,2,2,3,3,3,4");
        ListNodes.removeDuplication(root);
        assertEquals("1->2->3->4", root.toString());

        root = ListNodes.construct("1,1");
        ListNodes.removeDuplication(root);
        assertEquals("1", root.toString());
    }

    @Test
    public void removeDuplication2Test(){
        ListNode root = ListNodes.construct("1,2,2,3,3,3,4");
        ListNodes.removeDuplication2(root);
        assertEquals("1->4", root.toString());
    }

    @Test
    public void checkRing1Test(){
        //ListNode line = ListNodes.construct("1,2,3");
        ListNode ring = ListNodes.construct("4,5,6,7,8");
        ListNode p = ring;
        while (p.next != null)
            p = p.next;
        p.next = ring;

        assertEquals(true, ListNodes.checkRing(ring));
        assertEquals(true, ListNodes.checkRing2(ring));
    }

    @Test
    public void checkRing1Test2(){
        ListNode line = ListNodes.construct("1,2,3");
        ListNode ring = ListNodes.construct("4,5,6,7,8");
        ListNode p = ring;
        while (p.next != null)
            p = p.next;
        p.next = ring;
        p=line;
        while (p.next != null)
            p=p.next;
        p.next  = ring;

        assertEquals(true, ListNodes.checkRing(line));
        assertEquals(true, ListNodes.checkRing2(line));
    }

    @Test
    public void findRingEntryTest(){
        ListNode line = ListNodes.construct("1,2,3");
        ListNode ring = ListNodes.construct("4,5,6,7,8");
        ListNode p = ring;
        while (p.next != null)
            p = p.next;
        p.next = ring;
        p=line;
        while (p.next != null)
            p=p.next;
        p.next  = ring;
        assertEquals(ring, ListNodes.findRingEntry(line));
    }



}