package com.sg.thread;

/**
 * Created by shara on 26-07-2017.
 */

class WorkerAThread extends Thread {
    public WorkerAThread(String name){
        this.setName(name);
    }
    public void run(){
        System.out.println(Thread.currentThread().getName());
        System.out.println("I am inside worker thread");
    }

    //Try commenting out below start method
    public void start(){
        System.out.println(Thread.currentThread().getName());
        System.out.println("This is still main thread.");
    }

}

public class ThreadsStartMethod {
    public static void main(String[] args) {
        WorkerAThread workerA = new WorkerAThread("Worker Thread");
        workerA.start();
    }

}
