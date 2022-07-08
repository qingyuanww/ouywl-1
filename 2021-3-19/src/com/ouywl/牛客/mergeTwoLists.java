package com.ouywl.牛客;

/**
 * @description: 合并两个有序链表
 * @author: oywl https://leetcode.cn/problems/merge-two-sorted-lists/
 *
 * 关于递归的理解：首先，一个逻辑（或者一个循环）结束，下一个开始同样的逻辑循环; 循环循环，一直循环，但是逻辑是一样的
 * 只是指向的对象发生了变化，比如链表的下一个指向，数组的下一个对象（重点是有序？）
 * 第二：有最终的结束，或者说终止逻辑；
 *
 * 要把人脑的逻辑 转为计算机的逻辑
 * @time: 2022-7-7 17:46
 */

public class mergeTwoLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        //谁先指向null,指针指向另一个不为null的链表
        if(list1==null){
            return list2;
        }
        if(list2==null){
            return list1;
        }
        if(list1.val>list2.val){ //指针指向小的那个链表，然后将小的链表的下一个与大的做比较 ,注意，这个在人脑逻辑和计算机逻辑中 顺序是相反的！！！
            list2.next = mergeTwoLists(list1,list2.next);//将小的链表的下一个与大的做比较
            return list2;//指针指向小的那个链表
        }else {
            list1.next=mergeTwoLists(list1.next,list2);
            return list1;
        }
    }
//     * Definition for singly-linked list.
      public class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }
}
