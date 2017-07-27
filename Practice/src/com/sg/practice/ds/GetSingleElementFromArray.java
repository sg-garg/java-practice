package com.sg.practice.ds;

/**
 * Created by shara on 23-07-2017.
 */
class GetSingleElementFromArray
{
    // Method to find the element that occur only once
    static int getSingle(int arr[], int n)
    {
        int ones = 0, twos = 0;
        int common_bit_mask;

        for(int i=0; i<n; i++ )
        {
            /* The expression "one & arr[i]" gives the bits that are
           there in both 'ones' and new element from arr[].  We
           add these bits to 'twos' using bitwise OR

           Value of 'twos' will be set as 0, 3, 3 and 1 after 1st,
           2nd, 3rd and 4th iterations respectively */
            System.out.println("two="+twos +" ones="+ones +" arr["+i+"]"+arr[i]);
            twos = twos | (ones & arr[i]);

            /* XOR the new bits with previous 'ones' to get all bits
           appearing odd number of times

           Value of 'ones' will be set as 3, 0, 2 and 3 after 1st,
           2nd, 3rd and 4th iterations respectively */
            System.out.println("two="+twos +" ones="+ones +" arr["+i+"]"+arr[i]);
            ones = ones ^ arr[i];

            /* The common bits are those bits which appear third time
            So these bits should not be there in both 'ones' and 'twos'.
            common_bit_mask contains all these bits as 0, so that the bits can
            be removed from 'ones' and 'twos'*/
            System.out.println("two="+twos +" ones="+ones +" arr["+i+"]"+arr[i]);
            common_bit_mask = ~(ones & twos);
            System.out.println("two="+twos +" ones="+ones +" arr["+i+"]"+arr[i] +" common_bit_mask="+common_bit_mask);
            /*Remove common bits (the bits that appear third time) from 'ones'*/
            ones &= common_bit_mask;
            System.out.println("two="+twos +" ones="+ones +" arr["+i+"]"+arr[i] +" common_bit_mask="+common_bit_mask);
            /*Remove common bits (the bits that appear third time) from 'twos'*/
            twos &= common_bit_mask;
            System.out.println("two="+twos +" ones="+ones +" arr["+i+"]"+arr[i] +" common_bit_mask="+common_bit_mask);
        }
        return ones;
    }

    //  Driver method
    public static void main(String args[])
    {
        int arr[] = {3, 3, 2, 3};
        int n = arr.length;
        System.out.println("The element with single occurrence is " + getSingle(arr, n));
    }
}