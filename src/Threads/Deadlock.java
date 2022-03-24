package Threads;

public class Deadlock implements Runnable{


    @Override
    public void run() {
        synchronized (this){
            System.out.println("Printing");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        Deadlock c = new Deadlock();

        Deadlock c2 = new Deadlock();

        Runnable r =
                () -> {
                    synchronized (c) {
                        System.out.println("New Printing " + Thread.currentThread().getName());
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        synchronized (c2){
                            System.out.println("New Printing c2 "  + Thread.currentThread().getName());
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    synchronized (c2) {
                        System.out.println("Next New Printing " + Thread.currentThread().getName());
                        try {
                            Thread.sleep(100000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        synchronized (c){
                            System.out.println("Next New Printing c2 "  + Thread.currentThread().getName());
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                };

        Thread t = new Thread(r);
        t.start();
        //t.setDaemon(true);

        Thread t2 = new Thread(r);
        t2.start();
        // t2.setDaemon(true);
    }
}
