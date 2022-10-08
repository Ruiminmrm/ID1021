//Code is inspired by the book from Algorithms,4th ed.by Robert Sedgewick, Kevin Wanye.
package lab2;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
public class lab21 {
    public static boolean less(int a, int b) 
    {
        return a < b;
    }
    public static void sort (int[] arr)
    {
        int min;
        int swap = 0;
        for (int i = 0 ; i < arr.length ; i++)
        {
            for(int j = i ; j > 0 && less(arr[j] , arr[j-1])  ; j--)
            {
                min = arr[j];
                arr[j] = arr[j-1];
                arr[j-1] = min; 
                swap++; 
            }
            print(arr);    
            StdOut.println();  
        }
        StdOut.println("Swap " + swap + " times");
    }
    //print array
    public static void print (int[] arr)
    {
        StdOut.print("{");
        for(int i = 0; i < arr.length ; i++)
            StdOut.print( arr[i] + "," );
        StdOut.print("}");
        StdOut.println();
    }
    public static void main (String[] args)
    {
        StdOut.println( "Length of array :" );
        int length = StdIn.readInt();

        int[] arr = new int[length];
        StdOut.println( "Give elements  of array :" );
        for(int i = 0 ; i < length ; i++)
            arr[i] = StdIn.readInt();
        String st = Arrays.toString(arr);
        StdOut.println( "array :" + st);
        sort(arr);
    }
}
/*
Length of array :
6
Give elements  of array :
1
2
5
3
4
0
array :[1, 2, 5, 3, 4, 0]
{1,2,5,3,4,0,}

{1,2,5,3,4,0,}

{1,2,5,3,4,0,}

{1,2,3,5,4,0,}

{1,2,3,4,5,0,}

{0,1,2,3,4,5,}

Swap 7 times
*/