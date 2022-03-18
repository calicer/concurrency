package Threads;

public class Main {

//    MyRunnable myRn = new MyRunnable();
//
//    //lambda for Runnable
//    Runnable r = ()-> System.out.println("lambda run");
//
//    //direct injection in thread
//
//    Thread t = new Thread(()-> System.out.println("in thread"));




}

//A Thread is a single sequential flow of control in a program --unit of work
/*
    When u start a java app there is a JVM is a single process
    consists of various threads
    Application threads - running the main method

    Single application thread

    Need of more threads -- created using java API

    The Thread class
    Creating new Threads
    Runnable is an interface in java -- something that can be run
    It contains run method -- should be impl by any class whose instances are intended to be executed by thread


    Identify the piece of code that u want to run in the separate thread
    Put that into Runnable run method
    Create a new Thread from that runnable
    start the thread
    //if u run directly without creating a thread then no new thread is created
    
    JVM depends on OS for operating threads JVM only tracks threads

    When does thread ends
    -run method returns
    -an exception is thrown

    //extends thread and impl run and use .start() method
    //recommended is using Runnable interface

    /Daemon threads
    ends when app ends
    user threads -- main application thread

    Thread life cycle
    --states

    Just created  -- NEW
    Running      --  Runnable
    Blocked     --   Blocked, Waiting, Timed Waiting
    Terminated  --   Terminated

    JOIN
    wait for a thread to complete -- then execute


//Barrier SYnc
waiting all threads to finish and then application terminate

.....stopping a thread  by interrupt...thread just clean up and shut down calmly
   check thread is interrupted...u can throw interrupted exception

   while(true){
        Run task
        if(Thread.interrupted()){
            throw new InterruptedException;
        }
   }



Synchronization - data not code.....it is a lock and key pattern it locks the particular piece of code execution so that it is executed by only one thread at a time
to make sure that two threads dont simultaneaously access same data element

Programmer marks a data element as a lock
Programmer marks a piece of code to be accessible by the lock holder

JVM creates a lock for the data element
Thread tries to acquire a lock
if it acquires a lock then it can execute the synchronized code
Thread finishes executing the block and release the lock


Thread tries to get access to monitor lock
If thread gets it, it executes the block
Releases the monitor lock when exiting the block
Any other thread needs to wait (can't get monitor lock)

*/