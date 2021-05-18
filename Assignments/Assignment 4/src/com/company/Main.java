package com.company;

public class Main {

    static int summation(int[] insertList)
    {
        int counter = 0;
        for (int x: insertList)
        {
            counter += insertList[x];
        }
        return counter;
    }

    public static void main(String[] args)
    {
        int[] numArray = {2, 2, 1, 3, 4};
        System.out.println(summation(numArray));
    }

}
