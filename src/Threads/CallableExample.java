package Threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("Printed from callable");
                Thread.sleep(2000);
                return "Value from callable";
            }
        };

       // new Thread(callable).start(); --wot work thread expects only runnable

        //create new API for executor service

        java.util.concurrent.ExecutorService es =  Executors.newFixedThreadPool(1);

       // es.submit(callable); // it accepts runnable too
        //How to access the string value


        Future<String> future = es.submit(callable);
        System.out.println("Executing something in mean time");
        //Thread.sleep(2000);
        System.out.println("Executing something in mean time 2");
        String retString = future.get(); //it will wait for the thread to complete until value is returned..i.e: Thread.sleep(2000) in our case

        System.out.println(retString);
        System.out.println("waiting");
    }
}
