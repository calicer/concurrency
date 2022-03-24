package Threads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CounterLock {

    int  value;
    public   void increment(){
        value++;
        System.out.println(value);
    }
    public   void decrement(){
        value--;
        System.out.println(value);
    }

    public  int getValue(){
        return value;
    }
}

class F_Synch1{

    public static void main(String[] args) {
        CounterLock c = new CounterLock();
         Lock l = new ReentrantLock();
        Integer i = 10;
        Runnable r = new Runnable() {
            @Override
            public void run() {
                l.lock(); // creating a new lock, acquiring a lock
                try {
                    c.increment();
                        Thread.sleep(3000);
                        c.decrement();
                        System.out.println(c.getValue() + " " + Thread.currentThread().getName());

                    } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    l.unlock(); // release lock in any case
                }

                    //System.out.println(c.getValue() + " " + Thread.currentThread().getName());

            }
        };

        Thread t = new Thread(r,"one");
        Thread t1 = new Thread(r,"two");
        Thread t2 = new Thread(r,"three");
        Thread t3 = new Thread(r,"four");

        t.start();
        t1.start();
        t2.start();
        t3.start();

        CounterLock c2 = new CounterLock();
         new Thread(r, "new").start();


    }

}

