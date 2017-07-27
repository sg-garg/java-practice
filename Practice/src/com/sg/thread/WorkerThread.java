package com.sg.thread;

/**
 * Created by shara on 27-07-2017.
 */
public class WorkerThread implements Runnable {
    String command;
    public WorkerThread(String command) {
        this.command = command;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" Doing some work");
        processCommand();
    }

    public void processCommand(){
        System.out.println("Performing....."+this.command);
    }

    @Override
    public String toString() {
        return this.command;
    }
}
