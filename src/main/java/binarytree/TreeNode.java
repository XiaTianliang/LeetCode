package binarytree;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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





}
