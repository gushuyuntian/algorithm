package com.study.algorithm.tree.twothreetree;

/**
 * Basic tester class to test the 2-3+
 * based Dictionary.
 * Created by: Gus Silva & Anil Jethani
 */
public class Tester {

    public static void main(String args[])
    {
    	
        Dictionary<Integer> D1 = new TwoThreePlusTree<>();

        Integer[] arr = {47,8,7,6,44,21,33,16,49,1,12};
        for(int i=0; i < arr.length; i++)
        {
            System.out.println("Inserting: " + arr[i]);
            try {
                D1.insert(arr[i]);
            }
            catch (Exception e)
            {
                System.out.println("EXCEPTION: " + e.getMessage());
            }
            D1.print();
        }

        System.out.println("Removing: " + 7);
        D1.remove(7);
        D1.print();

        System.out.println("Removing: " + 21);
        D1.remove(21);
        D1.print();


    }
}
