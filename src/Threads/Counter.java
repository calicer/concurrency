package Threads;

public class Counter {
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


class F_Synch{

    public static void main(String[] args) {
        Counter c = new Counter();
        Integer i = 10;
        Runnable r = new Runnable() {
            @Override
            public void run() {
             synchronized (i){
                 c.increment();
                 try {
                     Thread.sleep(3000);
                     c.decrement();
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }

                 //System.out.println(c.getValue() + " " + Thread.currentThread().getName());
             }
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


    }

}
