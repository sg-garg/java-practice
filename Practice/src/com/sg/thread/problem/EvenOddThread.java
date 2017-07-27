package com.sg.thread.problem;

/**
 * Created by shara on 27-07-2017.
 */




class PrinterThread implements  Runnable{
    Integer counter;
    Boolean isEven;
    public PrinterThread(Integer counter, Boolean isEven){
        this.counter = counter;
        this.isEven = isEven;
    }

    @Override
    public void run() {
        while (counter<=10){
            if(isEven){
                printEven();
            }else{
                printOdd();
            }
            counter+=2;
        }
    }

    public void printEven(){
        synchronized (counter){
            while(!isEven){
                try {
                    System.out.println(Thread.currentThread().getName()+" waiting");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+": "+counter);
            notify();
        }
    }

    public void printOdd(){
        synchronized (counter){
            while(isEven){
                try {
                    System.out.println(Thread.currentThread().getName()+" waiting");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+": "+counter);
            notify();
        }
    }
}
class EvenThread implements Runnable{
    Integer counter;
    public EvenThread(Integer counter){
        this.counter = counter;
    }
    @Override
    public void run() {
        while(counter<=10){
        synchronized (counter){

                System.out.println(Thread.currentThread().getName());
                if(counter%2==0){
                    System.out.println(Thread.currentThread().getName()+" : "+counter++);
                    notify();
                }else{
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            }
        }
    }
}

class OddThread implements Runnable{
    Integer counter;
    public OddThread(Integer counter){
        this.counter = counter;
    }
    @Override
    public void run() {
        while(counter<=10){
        synchronized (counter){
                System.out.println(Thread.currentThread().getName());
                if(counter%2==1){
                    System.out.println(Thread.currentThread().getName()+" : "+counter++);
                    notify();
                }else{
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}

public class EvenOddThread {
    public static void main(String[] args) {
        Integer counter = new Integer(1);

        PrinterThread even = new PrinterThread(counter,true);
        PrinterThread odd = new PrinterThread(counter, false);

        Thread evenThread = new Thread(even, "Even Thread");
        Thread oddThread = new Thread(odd, "Odd Thread");
        evenThread.start();
        oddThread.start();


    }
}
