package com.sg.thread.problem;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by shara on 27-07-2017.
 */

class Message{
    String msg;
    public Message(String msg){
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}

class Producer implements  Runnable{
    public  Producer(BlockingQueue<Message> blockingQueue){
        this.blockingQueue= blockingQueue;
    }
    BlockingQueue<Message> blockingQueue;
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Message m= new Message(""+i);
            try {
                blockingQueue.put(m);
                System.out.println("Produced Message="+m.getMsg());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Message exitMessage = new Message("EXIT");
        try {
            blockingQueue.put(exitMessage);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Produced Message="+exitMessage.getMsg());
    }
}

class Consumer implements Runnable{
    BlockingQueue<Message> blockingQueue;
    public Consumer(BlockingQueue<Message> blockingQueue){
        this.blockingQueue = blockingQueue;
    }
    @Override
    public void run() {
        Message m=null;
        try {
            while (!(m = blockingQueue.take()).getMsg().endsWith("EXIT")){
                System.out.println(Thread.currentThread().getName()+"- ConsumedMessage= "+m.getMsg());
            }
            System.out.println(Thread.currentThread().getName()+"- ConsumedMessage= "+m.getMsg());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ProducerConsumerUsingBQ {
    public static void main(String[] args) {
        BlockingQueue<Message> blockingQueue = new ArrayBlockingQueue<Message>(10);
        Thread producerThread = new Thread(new Producer(blockingQueue), "Producer");
        Thread consumerThread = new Thread(new Consumer(blockingQueue), "Consumer");
        producerThread.start();
        consumerThread.start();
    }
}
