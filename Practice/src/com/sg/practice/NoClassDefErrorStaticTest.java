package com.sg.practice;


import com.sg.thread.problem.Test;

/**
 * Created by shara on 03-08-2017.
 */

class TestClass {
    public static TestClass instance = init();

    public static TestClass init() {
        String a = null;
        a.charAt(0); //force a null point exception;
        return new TestClass();
    }
}

public class NoClassDefErrorStaticTest {
//    static int i = getMe();
//    private static int getMe() {
//        throw new RuntimeException("Ha ha");
//    }

    static public void main(String[] args) {
        try {
            System.out.println(TestClass.instance);
        }catch (Throwable e){
            e.printStackTrace();
        }

        System.out.println(new TestClass());
        //accessStatic(); // a ExceptionInInitializerError raised cause by NullPointer
        //accessStatic(); //now a NoClassDefFoundError occurs;

        // But the class of TestClass is still available; why?
        System.out.println("TestClass.class=" + TestClass.class);
    }

    static void accessStatic() {
        TestClass a;

        try {
            a = TestClass.instance;
        } catch(Throwable e) {
            e.printStackTrace();
        }
    }

}
