package com.ouywl.牛客;

import com.ouywl.牛客.entity.ListNode;

/** 链表的中间节点
 * @description:https://leetcode.cn/problems/middle-of-the-linked-list/
 * @author: oywl
 * @time: 2022-7-19 14:50
 */

public class middle_of_the_linked_ist {
    public ListNode middleNode(ListNode head) {
        ListNode p =head,q=head;
        while(q!=null&&q.next!=null){
            q=q.next.next;
            p=p.next;
        }
        return p;
    }
}
