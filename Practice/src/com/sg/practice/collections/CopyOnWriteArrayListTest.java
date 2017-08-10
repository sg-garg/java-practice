package com.sg.practice.collections;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by shara on 04-08-2017.
 */
public class CopyOnWriteArrayListTest {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<String>();

        copyOnWriteArrayList.add("Sharad");
        copyOnWriteArrayList.add("Adhya");
        copyOnWriteArrayList.add("Shilpi");
        copyOnWriteArrayList.add("Unwanted");

        //You use any iterator below exception will come.
        //Iterator<String> it = copyOnWriteArrayList.iterator();
        ListIterator<String> it = copyOnWriteArrayList.listIterator();
        while (it.hasNext()){
            String name = it.next();
            System.out.println("Element:"+name);
            if(name.equals("Unwanted")){
                it.remove(); //I will get unsupportedoperation exception here.
            }
        }



    }
}
