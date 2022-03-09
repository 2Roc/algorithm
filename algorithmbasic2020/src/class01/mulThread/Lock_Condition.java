package class01.mulThread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lock_Condition {
    public static void main(String[] args) {
//        static Thread t1 = null,t2 =  null;
        char[] cha1 = "123456789".toCharArray();
        char[] cha2 = "ABCDEFGHI".toCharArray();
        useLock_Condition(cha1,cha2);
        for (int i = 0; i < 1000; i++) {
            useSync_notify_wait(cha1,cha2);
            System.out.println();
        }
        use_join(cha1,cha2);
//        useSync_notify_wait(cha1,cha2);
        useLockSupport(cha1,cha2);
    }

    private static void use_join(char[] cha1, char[] cha2) {
       Thread t1 =  new Thread(()-> {
           for (int i = 0; i < cha1.length; i++) {
               System.out.println(cha1[i]);
           }
       },"t2");


        Thread t2 =  new Thread(()->{
            for (int i = 0; i < cha2.length; i++) {
                System.out.println(cha2[i]);

            }

        },"t2");
    }

    private static void useLockSupport(char[] cha1, char[] cha2) {
    }

    private static void useSync_notify_wait(char[] cha1, char[] cha2) {
        Object oo = new Object();
        new Thread(()-> {
            synchronized (oo){
                try {
                    for(char c1:cha1) {
                        System.out.print(c1);
                        oo.notify();//随机唤醒等待队列中的一个线程
                        oo.wait();//进入该锁伴随生成的等待队列
                    }
                    oo.notify();//最后唤醒等待状态的线程
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        },"t1").start();

        new Thread(()->{
            synchronized (oo){
                try {
                    for(char c2:cha2) {
                        oo.notify();
                        System.out.print(c2);
                        oo.wait();
                    }
                    oo.notify();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        },"t2").start();
    }

    //使用Lock和Condition，可以精确唤醒某个等待队中的线程
    private static void useLock_Condition(char[] cha1, char[] cha2) {
        Lock lock = new ReentrantLock();
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();

        new Thread(() -> {
            lock.lock();
            try {
                for (char c : cha1) {
                    System.out.print(c);//用print保证每一组数字和字母在同一行
                    c2.signal();
                    c1.await();
                }
                c2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            try {
                for (char c : cha2) {
                    System.out.println(c);
                    c1.signal();
                    c2.await();
                }
                c1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }
    
}
