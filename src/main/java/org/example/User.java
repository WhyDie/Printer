package org.example;

public class User implements Runnable {
    private PrintServer printServer;
    public User(PrintServer printServer) {
        this.printServer = printServer;
    }
    @Override
    public void run() {
        int tasksCount = (int) (Math.random() * 6) + 2;
        for (int i = 0; i < tasksCount; i++) {
            String task = "Завдання " + (i + 1) + " від користувача " + Thread.currentThread().getId();
            printServer.addTask(task);
        }
    }
}
