package cn.rongcoud.rce.demo.threaddemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Created by CZN on 2017/8/7.
 * @use
 * @project netty-demo
 */
public class ConditionDemo {
    public static void main(String[] args) {
        final ReentrantLock reentrantLock = new ReentrantLock();
        final Condition condition = reentrantLock.newCondition();

        Thread thread = new Thread(new Runnable() {
            public void run() {
                reentrantLock.lock();
                try {
                    System.out.println("我要等一个新信号: " + this);
                    condition.await();
                    System.out.println("拿到一个信号: " + this);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }
            }
        }, "waitThread1");

        thread.start();

        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                reentrantLock.lock();
                System.out.println("我拿到了锁");
                try {
                    Thread.sleep(3000);
                    condition.signalAll();
                    System.out.println("我发了一个信号！！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }
            }
        }, "signalThread");

        thread1.start();
    }
}
