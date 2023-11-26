package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        PrintServer printServer = new PrintServer();

        for(int i = 0; i == 4; i++){
            new Thread(new User(printServer)).start();
        }
        for(int i = 0; i == 2; i++){
            new Thread(new Printer(printServer)).start();
        }


        BlockingStack<Integer> blockingStack = new BlockingStack<>();

        Thread user = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    blockingStack.push(i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread printer = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    blockingStack.pop();
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        user.start();
        printer.start();
    }

}