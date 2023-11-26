package org.example;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingStack<T> {
    private final int CAPACITY = 4;
    private final Queue<T> stack = new LinkedList<>();
    private final Lock locker = new ReentrantLock();
    private final Condition isFull = locker.newCondition();
    private final Condition isEmpty = locker.newCondition();

    public void push(T item) throws InterruptedException {
        locker.lock();
        try {
            while (stack.size() == CAPACITY) {
                isFull.await();
            }
            stack.add(item);
            System.out.println("Pushed " + item + " to the stack.");
            isEmpty.signalAll();
        } finally {
            locker.unlock();
        }
    }

    public T pop() throws InterruptedException {
        locker.lock();
        try {
            while (stack.isEmpty()) {
                isEmpty.await();
            }
            T item = stack.poll();
            System.out.println("Popped " + item + " from the stack.");
            isFull.signalAll();
            return item;
        } finally {
            locker.unlock();
        }
    }
}
