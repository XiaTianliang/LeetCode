package binarytree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.transaction.TransactionRequiredException;

import static org.junit.Assert.*;

/**
 * Created by tianliangxia on 16-10-22.
 */
public class TreeNodeTest {
    private TreeNode treeNode;

    @Before
    public void before(){
        treeNode = new TreeNode(0);
    }

    @Test
    public void toStringTest(){
        TreeNode root = new TreeNode(0);
        String res = TreeNode.toString(root);
        Assert.assertEquals(res,"[0,#,#]");
        root.left = new TreeNode(1);
        res= TreeNode.toString(root);
        Assert.assertEquals(res,"[0,1,#,#,#]");

    }

    @Test
    public void constructTest(){
        String str="[0,#,#]";
        TreeNode root = TreeNode.construct(str);
        String res = TreeNode.toString(root);
        assertEquals(str,res);

        str="[1,2,3,4,#,#,6,#,#,#,#]";
        root = TreeNode.construct(str);
        res = TreeNode.toString(root);
        assertEquals(str, res);
    }

    @Test
    public void preOrderTravelTest(){
        TreeNode root = TreeNode.construct("[1,2,3,4,#,#,6,#,#,#,#]");
        assertEquals(TreeNode.preOrderTravel(root), "1,2,4,3,6");

        root = TreeNode.construct("[1,2,3,8,4,5,#,#,#,6,#,9,7,#,#,#,#,#,#]");
        assertEquals(TreeNode.preOrderTravel(root), "1,2,8,4,6,3,5,9,7");

    }

    @Test
    public void preOrderTravelTest2(){
        TreeNode root = TreeNode.construct("[1,2,3,4,#,#,6,#,#,#,#]");
        assertEquals(TreeNode.preOrderTravel2(root), "1,2,4,3,6");

        root = TreeNode.construct("[1,2,3,8,4,5,#,#,#,6,#,9,7,#,#,#,#,#,#]");
        assertEquals(TreeNode.preOrderTravel2(root), "1,2,8,4,6,3,5,9,7");

    }

    @Test
    public void inOrderTravelTest(){
        TreeNode root = TreeNode.construct("[1,2,3,4,#,#,5,#,#,#,#]");
        assertEquals(TreeNode.inOrderTravel(root), "4,2,1,3,5");

        root = TreeNode.construct("[1,2,3,8,4,5,#,#,#,6,#,9,7,#,#,#,#,#,#]");
        assertEquals(TreeNode.inOrderTravel(root), "8,2,6,4,1,9,5,7,3");

    }

    @Test
    public void inOrderTravelTest2(){
        TreeNode root = TreeNode.construct("[1,2,3,4,#,#,5,#,#,#,#]");
        assertEquals(TreeNode.inOrderTravel2(root), "4,2,1,3,5");

        root = TreeNode.construct("[1,2,3,8,4,5,#,#,#,6,#,9,7,#,#,#,#,#,#]");
        assertEquals(TreeNode.inOrderTravel2(root), "8,2,6,4,1,9,5,7,3");
    }

    @Test
    public void PostOrderTravelTest(){
        TreeNode root = TreeNode.construct("[1,2,3,4,#,#,5,#,#,#,#]");
        assertEquals(TreeNode.postOrderTravel(root), "4,2,5,3,1");

        root = TreeNode.construct("[1,2,3,8,4,5,#,#,#,6,#,9,7,#,#,#,#,#,#]");
        assertEquals(TreeNode.postOrderTravel(root), "8,6,4,2,9,7,5,3,1");
    }

    @Test
    public void reverseTest(){
        TreeNode root = TreeNode.construct("[1,2,3,8,4,5,#,#,#,6,#,9,7,#,#,#,#,#,#]");
        TreeNode.reverse(root);
        assertEquals(TreeNode.toString(root), "[1,3,2,#,5,4,8,7,9,#,6,#,#,#,#,#,#,#,#]");
    }

    @Test
    public void matchTest(){
        TreeNode root = TreeNode.construct("[1,2,3,8,4,5,#,#,#,6,#,9,7,#,#,#,#,#,#]");
        TreeNode pattern = TreeNode.construct("[2,8,4,#,#,6,#,#,#]");
        assertEquals(true, TreeNode.match(root, pattern));

        pattern = TreeNode.construct("[2,#,4,6,#,#,#]");
        assertEquals(true, TreeNode.match(root, pattern));
    }

    @Test
    public void constructWithPreAndInOrderTest(){
        TreeNode root = TreeNode.construct("[1,2,3,8,4,5,#,#,#,6,#,9,7,#,#,#,#,#,#]");
        TreeNode node = TreeNode.constructWithPreAndInOrder(new int[]{1,2,8,4,6,3,5,9,7},
                new int[]{8,2,6,4,1,9,5,7,3});
        assertEquals(TreeNode.toString(root), TreeNode.toString(node));
    }
    @Test
    public void constructWithInAndPostOrderTest(){
        TreeNode root = TreeNode.construct("[1,2,3,8,4,5,#,#,#,6,#,9,7,#,#,#,#,#,#]");
        TreeNode node = TreeNode.constructWithInAndPostOrder(new int[]{8,2,6,4,1,9,5,7,3},
                new int[]{8,6,4,2,9,7,5,3,1});
        assertEquals(TreeNode.toString(root), TreeNode.toString(node));
    }

}