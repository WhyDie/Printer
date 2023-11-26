package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class PrintServer  {
    private Queue<String> printQueue = new LinkedList<>();
    private static final int MAX_QUEUE_SIZE = 10;
    public synchronized void addTask(String task) {
        while (printQueue.size() == MAX_QUEUE_SIZE) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        printQueue.offer(task);
        System.out.println("User: " + task);
        notifyAll();
    }
    public synchronized void printTask() {
        while (printQueue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        String task = printQueue.poll();
        System.out.println("Printer: " + task);
        notifyAll();
    }
}
