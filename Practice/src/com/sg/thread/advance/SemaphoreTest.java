package com.sg.thread.advance;

import java.util.concurrent.Semaphore;

/**
 * Created by shara on 04-08-2017.
 */

class EvenThread implements  Runnable{
    Semaphore evenSemaphore;
    Semaphore oddSemaphore;

    EvenThread(Semaphore evenSemaphore, Semaphore oddSemaphore){
        this.evenSemaphore = evenSemaphore;
        this.oddSemaphore = oddSemaphore;
    }

    @Override
    public void run() {
        int i=2;
        while (i<=10){
            //System.out.println( Thread.currentThread().getName() +"- "+ evenSemaphore.availablePermits() + " "+ oddSemaphore.availablePermits());
            if(i%2==0){
                try {
                    evenSemaphore.acquire();
                    System.out.println(Thread.currentThread().getName() +"- "+i);
                    i+=2;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    oddSemaphore.release();
                }
            }
        }
    }
}


class OddThread implements  Runnable{
    Semaphore evenSemaphore;
    Semaphore oddSemaphore;

    OddThread(Semaphore evenSemaphore, Semaphore oddSemaphore){
        this.evenSemaphore = evenSemaphore;
        this.oddSemaphore = oddSemaphore;
    }

    @Override
    public void run() {
        int i=1;
        //System.out.println( Thread.currentThread().getName() +"- "+ evenSemaphore.availablePermits() + " "+ oddSemaphore.availablePermits());
        while (i<=10){
            if(i%2==1){
                try {
                    oddSemaphore.acquire();
                    System.out.println(Thread.currentThread().getName() +"- "+i);
                    i+=2;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    evenSemaphore.release();
                }
            }
        }
    }
}

public class SemaphoreTest {

    public static void main(String[] args) {
        Semaphore evenSemaphore= new Semaphore(0);
        Semaphore oddSemaphore = new Semaphore(1);
        Thread oddThread = new Thread(new OddThread(evenSemaphore,oddSemaphore), "Odd");
        Thread evenThread = new Thread(new EvenThread(evenSemaphore,oddSemaphore), "Even");
        oddThread.start();
        evenThread.start();


    }


}
