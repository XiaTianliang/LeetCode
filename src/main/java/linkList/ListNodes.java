package linkList;

import utils.StringUtils;

/**
 * Created by tianliangxia on 16-10-22.
 */
public class ListNodes {

    public static ListNode construct(int[] vals){
        if(vals == null || vals.length == 0)
            return null;
        ListNode root = new ListNode(0);
        ListNode head = root;
        for(int val: vals){
            head.next = new ListNode(val);
            head = head.next;
        }
        return root.next;
    }

    public static ListNode construct(String str){
        if(StringUtils.isEmpty(str))
            return null;
        String[] vals;
        if(str.charAt(0) == '[' && str.charAt(str.length()-1)==']'){
            str = str.substring(1, str.length()-1);
        }
        if(str.contains("->")){
            vals = str.split("->");
        }else if(str.contains(",")){
            vals = str.split(",");
        }else{
            vals = new String[1];
            vals[0]=str;
        }

        int[] ints = new int[vals.length];
        for(int i=0;i<ints.length;i++){
            ints[i] = Integer.valueOf(vals[i]);
        }

        return construct(ints);
    }

    public static ListNode sort(ListNode root){
        if(root == null || root.next == null)
            return root;
        ListNode mid = partition(root);
        root = sort(root);
        mid = sort(mid);
        return merge(root, mid);
    }

    private static ListNode partition(ListNode head){
        int count = 0;
        ListNode p = head;
        while (p!=null){
            count++;
            p=p.next;
        }
        count/=2;
        p=head;
        for(int i=0;i<count-1;i++){
            p=p.next;
        }
        ListNode tmp = p.next;
        p.next = null;
        return tmp;
    }
    public static ListNode merge(ListNode left, ListNode right){
        ListNode root = new ListNode(0);
        ListNode head = root;
        while (left!=null && right!=null){
            if(left.val <= right.val){
                head.next=left;
                head=head.next;
                left=left.next;
            }else {
                head.next=right;
                head=head.next;
                right=right.next;
            }
        }
        if(left!=null){
            head.next=left;
        }
        if(right!=null){
            head.next=right;
        }
        return root.next;
    }

    /***
     * 1->2->2->3  =>  1->2->3
     * @param head
     * @return
     */
    public static void removeDuplication(ListNode head){
        if(head == null || head.next == null)
            return ;

        ListNode p = head;
        while (p != null && p.next != null){
            if(p.val == p.next.val){
                p.next = p.next.next;
            }else {
                p=p.next;
            }
        }
    }

    /***
     * 1->2->2->3   => 1->3
     * @param head
     * @return
     */

    public static ListNode removeDuplication2(ListNode head){
        if(head == null || head.next == null)
            return head;

        ListNode root = new ListNode(0);
        root.next = head;
        ListNode pre = root;
        while (head != null && head.next != null){
            ListNode p = head.next;
            while (p != null && p.val == head.val){
                p=p.next;
            }
            if(p == null){
                pre.next = p;
            }
            if(p == head.next){
                head = head.next;
                pre = pre.next;
            }else {
                pre.next = p;
                head = p.next;
            }
        }

        return root.next;
    }

    /***
     * 判断是否有环
     * @param root
     * @return
     */
    public static boolean checkRing(ListNode root){
        if(root == null || root.next == null)
            return false;
        ListNode fast = root, slow = root;
        while (fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow)
                return true;
        }
        return false;
    }

    /***
     * 修改链表指向
     * @param head
     * @return
     */
    public static boolean checkRing2(ListNode head){
        if(head == null)
            return false;
        ListNode root = new ListNode(0);
        ListNode pre = root, post;
        while (head != null){
            post = head.next;
            head.next = pre;
            pre = head;
            head = post;
            if(head == root)
                return true;
        }
        return false;
    }

    /***
     * 找到环的入口
     * @param head
     * @return
     */
    public static ListNode findRingEntry(ListNode head){
        if(head == null || head.next == null)
            return null;
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow)
                break;
        }
        if(fast == null || fast.next == null)
            return null;

        fast = head;
        while (fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

}
