package com.sg.thread;

/**
 * Created by shara on 02-08-2017.
 */

class IntOperationThread implements  Runnable{
    int i;
    public IntOperationThread(int i){
        this.i= i;
    }
    @Override
    public void run() {
        while(i<50){
            System.out.println(Thread.currentThread().getName()+" - "+i);
            i++;
        }

    }
}

public class IntThread {
    public static void main(String[] args) throws InterruptedException {
        int i = 1;
        Thread t1 = new Thread(new IntOperationThread(i),"T1");
        Thread t2 = new Thread(new IntOperationThread(i),"T2");
        Thread t3 = new Thread(new IntOperationThread(i),"T3");
        t1.start();
        t2.start();
        t3.start();
        t3.join();
        System.out.println("Ended");

    }

}
