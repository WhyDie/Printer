package org.example;

public class Printer implements Runnable {
    private PrintServer printServer;

    public Printer(PrintServer printServer) {
        this.printServer = printServer;
    }

    @Override
    public void run() {
        while (true) {
            printServer.printTask();
        }
    }
}
