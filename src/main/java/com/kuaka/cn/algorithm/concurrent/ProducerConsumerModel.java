package com.kuaka.cn.algorithm.concurrent;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liqiang.chen
 * @time 2023/3/29 17:11
 **/
public class ProducerConsumerModel {
    private static final int MAX_CONTAIN = 100;
    private static final LinkedList<Integer> list = new LinkedList<>();
    private static final AtomicInteger adder = new AtomicInteger(0);

    public static void producer() {
        while (true) {
            synchronized (list) {
                if (list.size() < MAX_CONTAIN) {
                    list.add(adder.getAndIncrement());
                    System.out.println(Thread.currentThread().getName() + "：生产了一个产品，共有" + list.size() + "个产品。");

                } else {
                    System.out.println(Thread.currentThread().getName() + "：库存满了，我要休息了。");
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (list.size() > 0) {
                    System.out.println(Thread.currentThread().getName() + "：消费者可以消费了。");
                    list.notifyAll();
                }
            }
        }

    }

    public static void consumer() {
        while (true) {
            synchronized (list) {
                if (list.size() > 0) {
                    list.remove();
                    System.out.println(Thread.currentThread().getName() + "：消费了一个产品，剩下" + list.size() + "个产品。");

                } else {
                    System.out.println(Thread.currentThread().getName() + "：生产者可以开始生产了。");
                    list.notifyAll();
                }
                if (list.size() == 0) {
                    System.out.println(Thread.currentThread().getName() + "：库存没了，我要休息了。");
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread p0 = new Thread(ProducerConsumerModel::producer, "P-0");
        Thread p1 = new Thread(ProducerConsumerModel::producer, "P-1");
        Thread p2 = new Thread(ProducerConsumerModel::producer, "P-2");

        Thread c0 = new Thread(ProducerConsumerModel::consumer, "C-0");
        Thread c1 = new Thread(ProducerConsumerModel::consumer, "C-1");
//        Thread c0 = new Thread(ProducerConsumerModel::consumer, "C-0");

        c0.start();
        c1.start();

        p0.start();
        p1.start();
        p2.start();

    }
}
