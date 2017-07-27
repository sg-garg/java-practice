package com.sg.practice.ds;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by shara on 23-07-2017.
 *
 * Find the maximum element in an array which is first increasing and then decreasing
 * http://www.geeksforgeeks.org/find-the-maximum-element-in-an-array-which-is-first-increasing-and-then-decreasing/
 */
public class FindMaximumInArray {
    public static int findMAX(int[] inputArr, int start, int end){
        if(start== end){
            return inputArr[start];
        }

        if(end==start+1 && inputArr[start]>= inputArr[end]){
            return inputArr[start];
        }

        if (end== start+1 && inputArr[end]> inputArr[start]){
            return inputArr[end];
        }

        int mid = (start+end)/2;

        if(inputArr[mid]>inputArr[mid-1] && inputArr[mid]> inputArr[mid+1]){
            return inputArr[mid];
        }

        if(inputArr[mid-1]> inputArr[mid] && inputArr[mid] >inputArr[mid+1]){
            return findMAX(inputArr, start, mid-1);
        }else{
            return findMAX(inputArr, mid+1, end);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter input array length=");
        int n = scanner.nextInt();
        int[] inputArray = new int[n];
        for (int i = 0; i < n; i++) {
            inputArray[i] = scanner.nextInt();
        }

        System.out.println("Input Array="+ Arrays.toString(inputArray));

        int maxNo = findMAX(inputArray,0,n-1);
        System.out.println("Max Value= "+maxNo);

    }

}
