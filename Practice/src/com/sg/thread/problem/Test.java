package com.sg.thread.problem;

import java.util.*;

class IteratorGetThread implements  Runnable{
    List<Integer> employeeIds;
    public IteratorGetThread(List<Integer> employeeIds){
        this.employeeIds = employeeIds;
    }
    @Override
    public void run() {
        Iterator<Integer> iterator = employeeIds.iterator();
        while (iterator.hasNext()){
            System.out.println(Thread.currentThread().getName()+"- Printing..."+iterator.next());
        }
    }
}
public class Test {
    public void showSize(List<?> list) {
        System.out.println(list.size());
    }
    public static void main(String [] args) {

        System.out.println(2>>1);
        try {
            Class.forName("abb");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}