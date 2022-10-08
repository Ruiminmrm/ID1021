//Code is inspired by the book from Algorithms,4th ed.by Robert Sedgewick, Kevin Wanye.package lab2;
package lab2;
import java.util.Arrays;
import edu.princeton.cs.algs4.*;
public class lab27 {
    public static boolean less(int a, int b) {
        return a < b;
    }
    public static void sort (int[] arr)
    {
        int max;
        for (int i = 0 ; i < arr.length ; i++)
        {
            for(int j = i ; j > 0 && less(arr[j] , arr[j-1])  ; j--)
            { 
                max = arr[j];
                arr[j] = arr[j-1];
                arr[j-1] = max;
            }
            print(arr);   
            StdOut.println();  
        }
    }
    public static void print (int[] arr)
    {
        StdOut.print("{");
        for(int i = 0; i < arr.length ; i++)
            StdOut.print( (arr[i]) + "," );
        StdOut.print("}");
        StdOut.println();
    }

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
//ny
//gör alla element negativt
        for(int i = 0 ; i < length ; i++)
            arr[i] = 0 - StdIn.readInt();

        printinver(arr); 
        String st = Arrays.toString(arr);
        StdOut.println( "array :" + st);
//sortera alla negtiv element 
        sort(arr);
//när vi print , gör det till positiv igen 
        for(int i = 0 ; i < length ; i++)
            StdOut.print( " [ " + (0-arr[i]) +"] ,");
        
    }
}
/*
Length of array :
6
Give elements  of array :
123 435 67567 878 453 2 
Inversions : 
{[1 , -435],[0 , -123]}  
{[2 , -67567],[0 , -123]}
{[3 , -878],[0 , -123]}  
{[4 , -453],[0 , -123]}  
{[2 , -67567],[1 , -435]}
{[3 , -878],[1 , -435]}
{[4 , -453],[1 , -435]}
swap : 7
array :[-123, -435, -67567, -878, -453, -2]
{-123,-435,-67567,-878,-453,-2,}

{-435,-123,-67567,-878,-453,-2,}

{-67567,-435,-123,-878,-453,-2,}

{-67567,-878,-435,-123,-453,-2,}

{-67567,-878,-453,-435,-123,-2,}

{-67567,-878,-453,-435,-123,-2,}

[ 67567] , [ 878] , [ 453] , [ 435] , [ 123] , [ 2] ,
*/
