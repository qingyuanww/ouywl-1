package com.ouywl.牛客;

import com.ouywl.牛客.entity.ListNode;

/**
 * @description:  因为是减法，所以必须用到链表的反转，这里要正确理解值传递和引用传递！
 *  反转后通过计算 或者，后续还要将结果继续反转
 * @author: oywl
 * @time: 2023-6-28 15:35
 */
public class SubtractLinkedList {
    public static void main(String[] args) {
        // 创建链表 l1: 12345
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);
        l1.next.next.next.next = new ListNode(5);

        // 创建链表 l2: 67
        ListNode l2 = new ListNode(6);
        l2.next = new ListNode(7);

        SubtractLinkedList solution = new SubtractLinkedList();

        // 计算相减结果链表
        ListNode result = solution.subtractTwoNumbers(l1, l2);

        // 打印结果链表
        printLinkedList(result);
    }

    public ListNode subtractTwoNumbers(ListNode l1, ListNode l2) {
        // 反转链表
        l1 = reverseLinkedList(l1);
        l2 = reverseLinkedList(l2);

        // 相减操作
        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        int borrow = 0;

        while (l1 != null || l2 != null) {
            int val1 = l1 != null ? l1.val : 0;
            int val2 = l2 != null ? l2.val : 0;
            int diff = val1 - val2 - borrow;

            if (diff < 0) {
                diff += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }

            curr.next = new ListNode(diff);
            curr = curr.next;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        // 反转链表
        ListNode result = reverseLinkedList(dummyHead.next);

        return result;
    }

    // 反转链表
    private ListNode reverseLinkedList(ListNode head) {
        ListNode prev = null;  // 前一个节点初始化为null
        ListNode curr = head;  // 当前节点初始化为head

        //正确理解值引用
        while (curr != null) {
            ListNode nextTemp = curr.next;  // 暂存当前节点的下一个节点
            //这里是值引用赋值，已经将引用的值赋值够curr.next
            curr.next = prev;  // 将当前节点指向前一个节点，完成反转

            prev = curr;  // 更新前一个节点为当前节点,为下一个指向做准备

            curr = nextTemp;  // 将当前节点移动到其原本的下一个节点
        }

        //每次迭代都会创建一个新的节点，该节点的next是上一个循环创建的新节点
        ListNode returnNode =null;
        while(head!=null){
            returnNode=new ListNode(head.val,returnNode);
            head=head.next;
        }
//        return returnNode;  // 返回反转后的链表的头节点
        return prev;  // 返回反转后的链表的头节点
    }

    // 打印链表
    private static void printLinkedList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }
}
