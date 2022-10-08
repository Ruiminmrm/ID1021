//Code is inspired by the book from Algorithms,4th ed.by Robert Sedgewick, Kevin Wanye.
package lab2;
import java.util.Arrays;
import edu.princeton.cs.algs4.*;
public class lab22 {
    public static boolean less(int a, int b) {
        return a < b;
    }
    public static void sort (int[] arr)
    {
        int min;
        for (int i = 0 ; i < arr.length ; i++)
        {
            for(int j = i ; j > 0 && less(arr[j] , arr[j-1])  ; j--)
            { 
                min = arr[j];
                arr[j] = arr[j-1];
                arr[j-1] = min;
            }
            print(arr);   
            StdOut.println();  
        }
    }
    public static void print (int[] arr)
    {
        StdOut.print("{");
        for(int i = 0; i < arr.length ; i++)
            StdOut.print( arr[i] + "," );
        StdOut.print("}");
        StdOut.println();
    }
    //ny
    public static void printinver(int[] arr)
    {
        StdOut.println("Inversions : ");
        int swap = 0; 
        for(int j = 0 ; j < arr.length ; j++)
            for(int i = j + 1 ; i < arr.length ; i++)
                if(less(arr[i],arr[j]))
                {
                    StdOut.println("{[" + i +" , " + arr[i] + "],[" + j + " , " + arr[j] + "]}"); 
                    swap++;
                }
        StdOut.println("swap : " + swap);
    }
    public static void main (String[] args)
    {
        StdOut.println( "Length of array :" );
        int length = StdIn.readInt();

        int[] arr = new int[length];
        StdOut.println( "Give elements  of array :" );
        for(int i = 0 ; i < length ; i++)
            arr[i] = StdIn.readInt();
        printinver(arr); 
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
Inversions :     
{[5 , 0],[0 , 1]}
{[5 , 0],[1 , 2]}
{[3 , 3],[2 , 5]}
{[4 , 4],[2 , 5]}
{[5 , 0],[2 , 5]}
{[5 , 0],[3 , 3]}
{[5 , 0],[4 , 4]}
swap : 7
array :[1, 2, 5, 3, 4, 0]
{1,2,5,3,4,0,}

{1,2,5,3,4,0,}

{1,2,5,3,4,0,}

{1,2,3,5,4,0,}

{1,2,3,4,5,0,}

{0,1,2,3,4,5,}
    1, How you calculate its time complexity?
    - T(n) = O(n^2). There are 2 loop in the method printinver.Each loop goes through the array with T(n) = O(n) 
*/
