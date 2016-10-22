package binarytree;

import sun.reflect.generics.tree.Tree;
import utils.ArrayUtils;

import java.util.*;

/**
 * Created by tianliangxia on 16-10-22.
 */

enum Direction{
    left,right
}

class Element{
    public TreeNode node;
    public Direction direction;
    public Element(TreeNode node, Direction direction){
        this.node = node;
        this.direction = direction;
    }
}

public class TreeNode {
    public int val;
    public TreeNode left=null, right=null;
    public TreeNode(int val){
        this.val=val;
    }

    /***
     * @param str [1,2,#] #->null
     * @return
     */
    public static TreeNode construct(String str){
        //构建树
        String[] values;
        if(str.charAt(0)=='[' && str.charAt(str.length()-1)==']'){
            values=str.substring(1,str.length()-1).split(",");
        }else{
            values=str.split(",");
        }
        //System.out.println(values);
        if(values.length<3){
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(values[0]));
        Queue<TreeNode> qu = new LinkedList<>();
        qu.add(root);
        TreeNode node;
        int count=0;
        while (!qu.isEmpty()){
            for(int i=0,j=qu.size();i<j;i++){
                node=qu.remove();
                if(!values[count+1].equals("#")){
                    node.left = new TreeNode(Integer.valueOf(values[count+1]));
                    qu.add(node.left);
                }
                if(!values[count+2].equals("#")){
                    node.right = new TreeNode(Integer.valueOf(values[count+2]));
                    qu.add(node.right);
                }
                count+=2;
            }
        }
        return root;
    }

    public static String toString(TreeNode root){
        //宽搜
        //用于比较结构是否
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        Queue<TreeNode> qu = new LinkedList<>();
        qu.add(root);
        TreeNode node;
        while (!qu.isEmpty()){
            for(int i=0,j=qu.size();i<j;i++){
                node=qu.remove();
                if(node == null){
                    stringBuilder.append("#,");
                }else {
                    stringBuilder.append(node.val+",");
                    qu.add(node.left);
                    qu.add(node.right);
                }
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public static String preOrderTravel(TreeNode root){
        if(root == null)
            return null;
        StringBuilder stringBuilder = new StringBuilder();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(null);
        while (root != null){
            stringBuilder.append(root.val+",");
            if(root.right != null){
                stack.push(root.right);
            }
            if(root.left != null){
                root = root.left;
            }else {
                root=stack.pop();
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }

    public static String preOrderTravel2(TreeNode root){
        if(root==null)
            return null;
        StringBuilder stringBuilder = new StringBuilder();
        Stack<TreeNode> stack = new Stack<>();
        while (root!= null || !stack.isEmpty()){
            if(root != null){
                stringBuilder.append(root.val+",");
                stack.push(root);
                root = root.left;
            }else {
                root=stack.pop();
                root=root.right;
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }

    public static String inOrderTravel(TreeNode root){
        if(root==null)
            return null;
        StringBuilder stringBuilder = new StringBuilder();
        Stack<TreeNode> stack = new Stack<>();
        while (root!= null || !stack.isEmpty()){
            if(root != null){
                stack.push(root);
                root = root.left;
            }else {
                root=stack.pop();
                stringBuilder.append(root.val+",");
                root=root.right;
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }

    public static String inOrderTravel2(TreeNode root){
        if(root==null)
            return null;
        StringBuilder stringBuilder = new StringBuilder();
        Stack<TreeNode> stack = new Stack<>();
        while (root!= null || !stack.isEmpty()){
            while (root!=null){
                stack.push(root);
                root = root.left;
            }
            root=stack.pop();
            stringBuilder.append(root.val+",");
            root=root.right;

        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }

    public static String postOrderTravel(TreeNode root){
        if(root==null)
            return null;
        StringBuilder stringBuilder = new StringBuilder();
        Stack<Element> stack = new Stack<>();
        Element element;
        while (root!= null || !stack.isEmpty()){
            while (root!=null){
                stack.push(new Element(root, Direction.left));
                root = root.left;
            }
            element=stack.pop();

            if(element.direction.equals(Direction.left)){
                element.direction = Direction.right;
                stack.push(element);
                root= element.node.right;
            }else{
                stringBuilder.append(element.node.val+",");
                root=null;
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }

    public static void reverse(TreeNode root){
        if(root!=null){
            reverse(root.left);
            reverse(root.right);
            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;
        }
    }

    /***
     * 判断pattern是否是root树结构中的一部分  网易游戏藏宝图
     * @param root
     * @param pattern
     * @return
     */
    public static boolean match(TreeNode root, TreeNode pattern){
        if(root == null && pattern == null)
            return true;
        if(root == null)
            return false;
        if(pattern == null)
            return true;

        return (root.val==pattern.val && (match(root.left, pattern.left) && match(root.right, pattern.right)
        || match(root.right, pattern.left) && match(root.left, pattern.right)))
                || match(root.left, pattern) || match(root.right, pattern);
    }

    /***
     * 由前序中序得到TreeNode
     * 数字不能重复
     * @param pre
     * @param in
     * @return
     */


    public static TreeNode constructWithPreAndInOrder(int[] pre, int[] in){
        if(pre == null || pre.length == 0 || in == null || in.length == 0)
            return null;
        int root_val = pre[0];
        int idx = ArrayUtils.indexOf(in, root_val);
        int left_len = idx;
        int right_len = pre.length-left_len-1;
        TreeNode root = new TreeNode(root_val);
        int[] pre_left = Arrays.copyOfRange(pre, 1, 1+left_len);
        int[] pre_right = Arrays.copyOfRange(pre, idx+1, idx+1+right_len);
        int[] in_left = Arrays.copyOfRange(in, 0, left_len);
        int[] in_right = Arrays.copyOfRange(in, idx+1, idx+1+right_len);

        root.left = constructWithPreAndInOrder(pre_left, in_left);
        root.right = constructWithPreAndInOrder(pre_right, in_right);
        return root;
    }

    public static TreeNode constructWithInAndPostOrder(int[] in, int[] post){
        if(post == null || post.length == 0 || in == null || in.length == 0)
            return null;
        int root_val = post[post.length-1];
        int idx = ArrayUtils.indexOf(in, root_val);
        int left_len = idx;
        int right_len = post.length-left_len-1;
        TreeNode root = new TreeNode(root_val);
        int[] post_left = Arrays.copyOfRange(post, 0, left_len);
        int[] post_right = Arrays.copyOfRange(post, left_len, post.length-1);
        int[] in_left = Arrays.copyOfRange(in, 0, left_len);
        int[] in_right = Arrays.copyOfRange(in, idx+1, idx+1+right_len);

        root.left = constructWithInAndPostOrder(in_left, post_left);
        root.right = constructWithInAndPostOrder(in_right, post_right);
        return root;
    }

}
