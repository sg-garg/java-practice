package com.sg.practice;

/**
 * Created by shara on 05-08-2017.
 */
public abstract class  AbstractClassMain {
    // abstract method need to have abstract keyword otherwise compile time error will come
    public abstract void f1();

    public static void f(){
        System.out.println("I am from abstract");

    }

    //Abstract class can have main method
    public static void main(String[] args) {
        System.out.println("In abstract main");
        AbstractClassMain.f();
    }

}
