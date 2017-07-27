package com.sg.thread.problem;

/**
 * Created by shara on 27-07-2017.
 */

class Printer {
    Boolean isEvenPrint = false;
    public Printer(){
    }
    public synchronized  void printEven(int i){
        while (!isEvenPrint){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        
        System.out.println(Thread.currentThread().getName()+" : "+i);
        isEvenPrint = false;
        notifyAll();
    }

    public synchronized  void printOdd(int i){
        while (isEvenPrint){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println(Thread.currentThread().getName()+" : "+i);
        isEvenPrint = true;
        notifyAll();
    }
}

class Even implements Runnable{
    Printer printer;
    public Even(Printer printer){
        this.printer = printer;

    }
    @Override
    public void run() {
        for (int i = 2; i <= 10; i+=2) {
            printer.printEven(i);
        }
    }
}

class Odd implements Runnable{
    Printer printer;
    public Odd(Printer printer){
        this.printer = printer;

    }
    @Override
    public void run() {
        for (int i = 1; i <= 10; i+=2) {
            printer.printOdd(i);
        }
    }
}

public class EvenOdd {
    public static void main(String[] args) {
        Printer printer = new Printer();
        Even even = new Even(printer);
        Odd odd = new Odd(printer);
        new Thread(even, "even").start();
        new Thread(odd, "odd").start();


    }
}
