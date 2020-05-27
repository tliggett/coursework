//4_12_2019
//Homework 8
//StrRadixSort.java

/*

    Class StrRadixSort sorts an arraylist in ascending order
    Documentation listed above each method

 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class StrRadixSort {
    //Pre:	arr contains lowercase strings to be sorted.
    //Post:	arr sorted in ascending order
    public static void strRadixSort(ArrayList<String> arr) {
        Queue<String>[] subQ = (Queue<String>[]) new LinkedList[27];  //line 9
        int maxLength = findMaxLength(arr);
        appendBlanks(arr);
        for (int i = 0; i < subQ.length; i++)
            subQ[i] = (Queue<String>) new LinkedList<String>();
        for (int pass = 0; pass < maxLength; pass++) {
            distribute(arr, subQ, pass);
            collect(subQ, arr);
        }
        trimArray(arr);
    }

    // Pre  : 	arr contains Strings to be distributed, all of the same length.
    //	subQ[0]..subQ[26] are empty.
    //	pass >=0
    // Post : 	The elements of arr pushed into one of the subQ[0..26] based on the pass char from the
    //	right of each element.  E.g. if pass is 0, then abe is pushed into subQ[5], if pass is 1, then
    //	abe is pushed into subQ[2].
    private static void distribute(ArrayList<String> arr, Queue<String>[] subQ, int pass) {
        int index = findMaxLength(arr) - pass - 1;
        for (String s : arr)
        {
            int c = s.charAt(index);
            if(c == 32) subQ[0].offer(s);       /*If char is blank (ASCII: 32) offer to first queue*/
            else subQ[c-96].offer(s);           /*a is ASCII 97, will be set at subQ[1]*/
        }
    }

    // Post : 	Each subQ is emptied starting from subQ[0], then subQ[1], …, subQ[9].  The elements
    //	popped are sequentially added to arr
    private static void collect(Queue<String>[] subQ, ArrayList<String> arr) {

        while(arr.size() > 0) arr.remove(arr.size()-1);
        for (int i = 0; i < subQ.length; i++) {
            while (!subQ[i].isEmpty()) {
                arr.add(subQ[i].poll());
            }
        }
    }

    //Post: Blanks on the end of Strings in arr are trimmed
    private static void trimArray(ArrayList<String> arr){
        for(int i = 0; i < arr.size(); ++i) arr.set(i, arr.get(i).trim());
    }

    //Post: An array in which all strings in arr are appended with blanks to become same length
    private static void appendBlanks(ArrayList<String> arr){
        int maxLength = findMaxLength(arr);
        for(int i = 0; i<arr.size(); ++i){
            String append = arr.get(i);
            while(append.length() < maxLength) append += " ";
            arr.set(i, append);
        }
    }


    //Return: The maximum length of a String in arr
    private static int findMaxLength(ArrayList<String> arr){
        int maxLength = 0;
        for(String s : arr) if(maxLength < s.length()) maxLength = s.length();
        return maxLength;
    }


}
