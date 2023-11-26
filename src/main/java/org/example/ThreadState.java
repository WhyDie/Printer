package org.example;

import java.util.concurrent.TimeUnit;

public class ThreadState {
    public static void main(String[] args) throws InterruptedException {
        Thread mainThread = Thread.currentThread();
        Thread minorThread = new Thread(() -> {
            try {
                demo();
                TimeUnit.SECONDS.sleep(1);
                mainThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread blockThread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                demo();
                Status(minorThread);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });
        Status(minorThread);
        minorThread.start();
        Status(minorThread);
        blockThread.start();
        TimeUnit.SECONDS.sleep(3);
        Status(blockThread);
        Status(minorThread);
        TimeUnit.SECONDS.sleep(2);
        Status(minorThread);
    }
    private static void Status(Thread thread){
        System.out.println(thread.getState());
    }
    private static synchronized void demo() throws InterruptedException{
        for (int i = 0; i < 10; i++){
            TimeUnit.SECONDS.sleep(1);
            System.out.println(i);
        }

    }
}
