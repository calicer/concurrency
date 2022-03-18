package Threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorService {
    public static int calculatePrime(int n) {
        int number;
        int numberOfPrimesFound;
        int i;
        number = 1;
        numberOfPrimesFound = 0;

        while (numberOfPrimesFound < n) {
            number++;
            for (i = 2; i <= number && number % i != 0; i++) {

            }
            if (i == number) {
                numberOfPrimesFound++;
            }
        }
        return number;
    }


    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();

        java.util.concurrent.ExecutorService es = Executors.newFixedThreadPool(3);

        //Fixed thread executor
        //Single thread executor -- only one thread all runnables have to wait for that thread
        //cached thread executor -- not decide how many, it creates as many as wanted but firsts check in pool for free threads
        //Scheduled thread pool executor -- run after some delay or time

       // java.util.concurrent.ExecutorService es = Executors.newCachedThreadPool();
        //java.util.concurrent.ExecutorService es = Executors.newSingleThreadExecutor();

        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(5000);
                        printThread(threads);
                    }
                } catch (InterruptedException e) {
                    System.out.println("Printing of thread states stopped..");
                }
            }
        };
        ScheduledExecutorService ess = Executors.newScheduledThreadPool(1);
        Runnable rr = ()-> System.out.println("running report .....");
        ess.scheduleAtFixedRate(rr, 1,5, TimeUnit.SECONDS);
        Thread pt = new Thread(r1);
        pt.start();
        while (true) {
            Scanner s = new Scanner(System.in);
            System.out.println("\n I can tell you the nth prime number. Enter n: ");
            int n = s.nextInt();

            if (n == 0) {
                pt.interrupt();
                System.out.println("Waiting for all threads to terminate");
                waitThreads(threads);
                System.out.println("Done! " + threads.size() + " primes calculated");
                break;
            }

            Runnable r = new Runnable() {
                @Override
                public void run() {
                    int number = calculatePrime(n);
                    System.out.println("\n Value of " + n + "th prime number: " + number);
                }
            };
//            Thread t = new Thread(r);
//            threads.add(t);
//            //t.setDaemon(true);
//            t.start();
                es.execute(r);
        }


    }

    public static void printThread(List<Thread> li) {
        li.forEach((threads) -> System.out.println(threads.getState() + " "));
    }

    public static void waitThreads(List<Thread> li) throws InterruptedException {
        for (Thread t : li) {
            t.join();
        }
    }

}
