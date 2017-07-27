package com.sg.practice.ds;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by shara on 23-07-2017.
 * http://www.geeksforgeeks.org/convert-an-array-to-reduced-form-set-1-simple-and-hashing/
 */
public class SubArray {

    public static void convertArrayToReduce1(Integer[] a){

        for (int i = 0; i < a.length; i++) {
            int min =Integer.MAX_VALUE;
            int minPostion = i;
            for (int j = 0; j < a.length; j++) {
                if((a[j]< a[i] || a[i]< i) && a[j] < min && a[j]>i){
                    min = a[j];
                    minPostion = j;
                }
            }
            System.out.println("minPostion = "+minPostion + " min="+min);
            a[minPostion] = i;
        }
        System.out.println(Arrays.toString(a));
        return;
    }

    public static  void convertArrayToReduce2(Integer[] inputArr){
        Integer[] temp = new Integer[inputArr.length];
        temp = inputArr.clone();
        Arrays.sort(temp);
        //System.out.println(Arrays.toString(inputArr));
        //System.out.println(Arrays.toString(temp));
        Map<Integer, Integer> umap = new HashMap<Integer, Integer>();


        for (int i = 0; i < inputArr.length; i++) {
            umap.put(temp[i], i);
        }

        for (int i = 0; i <inputArr.length; i++) {
            inputArr[i] = umap.get(inputArr[i]);
        }

        System.out.println(Arrays.toString(inputArr));

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Size of Array=");
        Integer sizeOfArray = scanner.nextInt();
        System.out.println("Enter Array Values: ");
        Integer [] inputArr = new Integer[sizeOfArray];
        for (int i =0; i<sizeOfArray; i++) {
            inputArr[i] = scanner.nextInt();
        }

        System.out.println(Arrays.toString(inputArr));
        //Solution 1
        //convertArrayToReduce1(inputArr);

        //Solution 2
        convertArrayToReduce2(inputArr);
        System.out.println("Done");
    }
}
