package com.ouywl.牛客;

import java.util.HashSet;

/**
 * @description: 环形链表II 返回第一个环的头节点
 * @author: oywl https://leetcode.cn/problems/linked-list-cycle-ii/
 * @time: 2022-7-19 16:45
 */

public class linked_list_cycle_ii {
    //迭代的形式;
    public ListNode detectCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        ListNode temp = head;
        while (temp != null) {
            if (set.contains(temp)) {
                return temp;
            }
            set.add(temp);
            temp = temp.next;
        }
        return null;
    }

    //快慢指针的解法
    /*
     * 假设存在环，那么设起点到环头结点的距离为x,环头结点到 快慢指针相遇的节点的距离为y，相遇节点到头结点的距离为z
     * 因为:速度上fast = 2 slow; 当slow 到了环的头结点为，fast指针 最多距离slow  y+z-1距离，因为当slow静止不动时,fast以每秒一个节点的速度追赶slow
     * 所以:在slow走完一圈前，fast必定会追上slow； y+z-1<y+z;
     *
     * 设相遇前快指针已经走了n圈环
     * 因为fast=2*slow
     * 所以2(x+y)=x+y+n(y+z)
     *   x+y=n(y+z)
     *    x=n(y+z)-y
     *    x=(n-1)(y+z)+z
     *   由x=(n-1)(y+z)+z得出：当slow和fast相遇时，将一个新指针prev指向链表的头节点，慢指针此时处于相遇节点上，
     *   所以：当prev走到环的头节点时候，慢指针也一定到了环的头节点。 （x = z +(n-1)*环的长度）*
     *  设环长=3 ，x=5,z=2,n=2 y=1  1 2 3 4 5 6 7  5
     * */
    public ListNode detectCycle2(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (true) {
            if (fast == null && fast.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
            //如果存在快指针和慢指针相遇的情况，那么根据以下的计算返回 环的头节点
            if (fast == slow) {
                break;
            }
            //将一个新指针prev指向链表的头节点 或者fast =head;
            ListNode prev = head;
            //x=(n-1)(y+z)+z   （x = z +(n-1)*环的长度）
            while (prev != slow) {
                prev = prev.next;
                slow = slow.next;
            }
            return prev;
        }
        return null;
    }

}
