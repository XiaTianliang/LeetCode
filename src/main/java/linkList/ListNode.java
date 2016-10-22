package linkList;

import binarytree.TreeNode;

/**
 * Created by tianliangxia on 16-10-22.
 */
public class ListNode {
    public int val;
    public ListNode next=null;
    public ListNode(int val){
        this.val = val;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        ListNode node = this;
        while (node!=null){
            stringBuilder.append(node.val+"->");
            node=node.next;
        }
        stringBuilder.delete(stringBuilder.length()-2,stringBuilder.length());
        return stringBuilder.toString();
    }
}
