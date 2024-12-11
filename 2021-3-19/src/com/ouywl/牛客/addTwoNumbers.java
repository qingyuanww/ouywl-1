package com.ouywl.牛客;

import com.ouywl.牛客.entity.ListNode;

/**
 * @description: 321 421 返回247
 * @author: oywl
 * @time: 2022-6-21 18:15
 */

public class addTwoNumbers {
    public static void main(String[] args) {

//342 + 465 = 807
        ListNode node3 = new ListNode(3,null);
        ListNode node2 = new ListNode(4,node3);
        ListNode node1 = new ListNode(2,node2);

        ListNode node6 = new ListNode(4,null);
        ListNode node5 = new ListNode(6,node6);
        ListNode node4 = new ListNode(5,node5);


        ListNode listNode = new addTwoNumbers().addTwoNumbers(node1, node4);
        System.out.println(listNode);

        System.out.println(1/10);
        System.out.println(123%10);
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while(l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;

            carry = sum / 10;  //除法是几倍，3除以10=0；15除以10=1
            sum = sum % 10;//余数 7%10=7 17%10=7
            cur.next = new ListNode(sum);

            cur = cur.next;
            if(l1 != null)
                l1 = l1.next;
            if(l2 != null)
                l2 = l2.next;
        }
        if(carry == 1) {//如果最后有，那么进位1 ，十倍
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }



}

