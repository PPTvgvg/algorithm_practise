package com.kuaka.cn.algorithm.list0;


/**
 * @author liqiang.chen
 * @time 2023/3/16 20:25
 **/
public class ReverseList {
    public static Node reverse(Node head) {
//        if (head == null)

        Node next;
        Node pre = null;
        Node curr = head;

        while (curr != null) {
            next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }

        return pre;
    }

    public static void main(String[] args) {
        Node one = new Node();
        one.value = 1;
        Node tow = new Node();
        tow.value = 2;
        one.next = tow;
        Node three = new Node();
        three.value = 3;
        tow.next= three;
        Node fore = new Node();
        fore.value = 4;
        three.next = fore;
        fore.next = null;

        Node reverse = reverse(one);

        while (reverse != null) {
            System.out.println(reverse.value);
            reverse = reverse.next;
        }
    }
}
