package com.ouywl.牛客;

import com.ouywl.牛客.entity.ListNode;

import java.math.BigDecimal;

/**
 * @description:  反转链表
 * @author: oywl https://leetcode.cn/problems/reverse-linked-list/
 * @time: 2022-7-8 13:58
 */

public class revert_linked_list {
    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("10041002");
        System.out.println(bigDecimal);
        String s1= "estimated_return_time";
        String s2= "actual_lending_time";
        String s3= "actual_return_time";
        String s4= "actual_use_days";
        System.out.println(s1.toUpperCase());
        System.out.println(s2.toUpperCase());
        System.out.println(s3.toUpperCase());
        System.out.println(s4.toUpperCase());
    }
    public ListNode reverseList(ListNode head) {
//        //迭代 : 保存前后两个节点
//        ListNode prev = null;
//        //定义当前节点
//        while (head!=null){
//            ListNode next = head.next;
//            head.next=prev;
//            prev=head;
//            head=next;
//        }
//
//
//        return prev;

        //每次迭代都会创建一个新的节点，该节点的next是上一个循环创建的新节点
        ListNode returnNode =null;
        while(head!=null){
            returnNode=new ListNode(head.val,returnNode);
            head=head.next;
        }
        return returnNode;

        //递归想不出来
    }

}
