package com.sg.practice;

import java.util.HashSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.prefs.Preferences;

public class Main {

    public static void main(String[] args) {
        String s1 = "Cat";
        String s2 = "Cat";
        String s3 = new String("Cat");

        System.out.println("s1 == s2 :"+(s1==s2));
        System.out.println("s1 == s3 :"+(s1==s3));
        String s4 = s3;
        s3= s3.intern();
        System.out.println("s1 == s3 :"+(s1==s3));
        System.out.println("s4 == s3 :"+(s4==s3));

        String s5 = "Cat";
        System.out.println("s5 == s3 :"+(s5==s3));
        LinkedBlockingQueue
    }


}
