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
All other threads that needs to execute the code block have to wait

//Monitors - object based isolations-

Thread tries to get access to monitor lock
If thread gets it, it executes the block
Releases the monitor lock when exiting the block
Any other thread needs to wait (can't get monitor lock)

What Synchronization achieves-
Visibility - value is read from memory before block execution, value is written to memory after execution completes, value not visible to others in system memory
Mutual exclusion -


Structured lock- Block structure using synchronized keyword, acquiring and releasing the locks are implicit

How to handle concurrency issues
1. Don't have shared state
2. Share only immutable values
3. Use synchronization

Mutex locks - choose the right object for the lock and synchronize the bare minimum code necessary.


Liveness -- your application is hang not making any progress, two threads invoking joi on each other, potentially waiting forever

Deadllock - you hang up first

Livelock - steps taken to avoid deadlock, one says i release the lock other also and loops goes on...pass the parcel

Starvation - thread priority too low that scheduler is not scheduling it.

Volatile Keyword -- I just want visibility, MARKS A VARIABLE AS DO NOT CACHE, access to other threads, see the latest value, write to latest value. read from system memory write to system memory

private volatile int value;
It doesn't guarantee mutual exclusion but it holds up visibility

Thread 1 writes variable to memory
Thread 2 reads variable from the memory---now it is guaranteed that it is going to get the updated value
Assuming this is the order of execution, but if thread 2 is reading the value while thread 1 is doing some execution there is no guarantee
All other values in the code where u used volatile also written to memory

Instruction ordering  -- done by JVM
hasValueUpdated = true
value = 20  // volatile
--------------------
value = 20
hasValueUpdated = true  // here no guarantee because of order above value is already written to memory.

Thread local;
Access variable in multiple places in a thread
Runnable r = () -> processData(userID);

public void processUserData(int userID){
    doSomething(); //needs userID
    doNow(); //needs userID
    otherDo(); //needs userID
}
Choices:
1.One way to pass around the userID in multiple places in thread  ... which is inconvinient
2.Use a class member variable...synch problem
3.Use a thread local variable -- visibiliy guranttee or access scope gurantee is to the particular thread
Scope is per-thread
Global in the context of thread instance.. any piece of code that runs in a particular thread has it own copy of that data element...other thread other local variable

ThreadLocal<Integer> userID = new ThreadLocal<>(); .// JVM creates thread local only when a new thread starts
userID.set....get functions provided

Unstructured lock--acquiring and releasing locks urself, Lock interface
.lock(), .unlock()
Rentrant lock is direct equivalant to synchronized lock..once u have aquired a lock at one place u can aquire it over and again


Methods in Lock interface
lock()
unlock()
trylock() -- tries to get the lock, if it doesnt get the lock it returns false, handy when u dont have to wait for long time and there are other things to do

while(true){
 if(lock.tryLock()){
    try{

    } finally {
         lock.unlock();
    }
        //do something
  } else {
    //perform alternative things
  }
}

3.InterupptedLock --acquire the lock if thread is not interuptted
4.tryLock(long time, TimeUnit unit); wait for specified time then return true false;
5. newCondition(); returns a new Condition instance that is bound to lock instance.. i want something to wait or proceed when somethig is that
example - i want this thread to wait there is some element in my array

signal()--> wakes up the waiting thread
await(); causes the current thread to await until signalled or interrupted


Executer service - high level API
creating new threads is resource intensive....
Re-use threads,, dont create new thread insance when one is done assign new runnable and so on....
Typically done with thread pool

Thread pool-> fixed instances of threads that gets created in beginning when tasks comes that needs to be done a new
thread instance is picked up fom the pool of threads and do the work, another task comes in another instance is picked up and so on
when all threads are picked up the other threads have to wait, when something gets done then that thread instance goes back
to thread pool and runs the waiting runnable

Executor service - manage thread pools, configure me give tasks/runnables.
provide extra services like how many threads?
enable results....runnable does not return anything but it can

 //Fixed thread executor
 //Single thread executor -- only one thread all runnables have to wait for that thread - has blocking que
 //cached thread executor -- not decide how many, it creates as many as wanted but firsts check in pool for free threads - has synchronous que
 //Scheduled thread pool executor -- run after some delay or time -- has delay que
 //work stealing thread pool executor

Callable
Runnable doesn't return anything but callable returns

It is a generic type Interface
Callable<String> c = new Callable........

......see code

FUTURE object -- how to get the value of a concurrently executed callable into a main thread without having main thread blocked



the things that wait can throw interupted exception



*/