package com.sg.thread;

/**
 * Created by shara on 26-07-2017.
 */
public class ThreadLocalTest extends Thread {
    ThreadLocal<Integer> i;
    public ThreadLocalTest(String name, ThreadLocal<Integer> counter){
        this.setName(name);
        this.i = counter;
    }

    public  static ThreadLocal<String> threadStatus = new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return "Starting...";
        }
    };

    @Override
    public void run() {
        System.out.println(i.get()+":"+Thread.currentThread().getName() + " " +threadStatus.get());
        threadStatus.set("Ending...");
        i.set(i.get()+1);
        System.out.println(i.get()+":"+Thread.currentThread().getName() + " " +threadStatus.get());
        threadStatus.set("Ended");
    }


    public static void main(String[] args) {
        ThreadLocal<Integer> counter = new ThreadLocal<Integer>(){
            @Override
            protected  Integer initialValue(){
                return 1;
            }
        };

        for (int i = 0; i < 5; i++) {
            ThreadLocalTest test = new ThreadLocalTest(""+i, counter);
            test.start();
            //After adding below line of code it would sequencial processing...

            try {
                test.join();
                //Thread Local u will see intial value outside the scope.
                System.out.println("In main thread" + test.getName() + test.threadStatus.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

}
