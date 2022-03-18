package Threads;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FileChecker {


    public static void main(String[] args) {

        ScheduledExecutorService ss = Executors.newScheduledThreadPool(1);

        Runnable rr = new Runnable() {
            @Override
            public void run() {
                try {
                    File myObj = new File("D:/ELK/File.txt");
                    Scanner myReader = new Scanner(myObj);
                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        System.out.println(data);
                    }
                    myReader.close();
                } catch (FileNotFoundException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }
        };

        ss.scheduleAtFixedRate(rr, 1,40, TimeUnit.SECONDS);
    }

}
