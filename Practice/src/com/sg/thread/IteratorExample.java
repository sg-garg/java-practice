package com.sg.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by shara on 02-08-2017.
 */

class IteratorRemoveThread implements  Runnable{
    List<Integer> employeeIds;
    public IteratorRemoveThread(List<Integer> employeeIds){
        this.employeeIds = employeeIds;
    }
    @Override
    public void run() {
        synchronized(employeeIds) {
            Iterator<Integer> iterator = employeeIds.iterator();
            while (iterator.hasNext()) {
                System.out.println(Thread.currentThread().getName() + "- Removing..." + iterator.next());
                iterator.remove();
            }
        }
    }
}

class IteratorGetThread implements  Runnable{
    List<Integer> employeeIds;
    public IteratorGetThread(List<Integer> employeeIds){
        this.employeeIds = employeeIds;
    }
    @Override
    public void run() {
        synchronized(employeeIds) {
            Iterator<Integer> iterator = employeeIds.iterator();
            while (iterator.hasNext()) {
                System.out.println(Thread.currentThread().getName() + "- Printing..." + iterator.next());
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class IteratorExample {
    public static void main(String[] args) {
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < 5000; i++) {
            ids.add(i);
        }

        List<Integer> synids= Collections.synchronizedList(ids);
        IteratorRemoveThread remove = new IteratorRemoveThread(synids);
        IteratorGetThread get = new IteratorGetThread(synids);
        Thread t1 = new Thread(remove, "Remove");
        Thread t2 = new Thread(get, "Get");
        t1.start();
        t2.start();


    }


}
