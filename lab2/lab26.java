//Code is inspired by the book from Algorithms,4th ed.by Robert Sedgewick, Kevin Wanye.
package lab2;
import java.util.Random;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
public class lab26 {
    private static final int cutoff = 5;
    public static void print (int[] arr)
    {
        StdOut.print("{");
        for(int i = 0; i < arr.length ; i++)
            StdOut.print("["+ arr[i] + "] , " );
        StdOut.print("}\n");
    }
    public static void exch(int[] arr, int a, int b)
    {
        int n = arr[a] ;
        arr[a] = arr[b] ;
        arr[b] = n ;
    }
    public static boolean less(int a, int b) 
    {
        return a < b;
    }
    public static int median(int[] a , int i , int j , int k )
    { // hittar en median 
        return (less(a[i], a[j]) ?
               (less(a[j], a[k]) ? j : less(a[i], a[k]) ? k : i) :
               (less(a[k], a[j]) ? j : less(a[k], a[i]) ? k : i));
    }
    public static void sortm(int[] a)
    {
        quicksortm(a, 0, a.length - 1);
    }
    public static void sort(int[] a)
    {
        quicksort(a, 0, a.length - 1);
    }
//insertion sort
    public static void insertionSort(int[] a, int lo, int hi) 
    {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a[j], a[j-1]); j--)
                exch(a, j, j-1);
    }
//samma partition från quicksort 
    public static int partition(int a[], int low , int high)
    {
        int i = low, j = high + 1 ;
        int temp = a[low];
        while ( true )
        {
            //hitta tal på low och swap
            while (less(a[++i] , temp))
                if( i == high)
                    break;
            //hitta tal på high och swap
            while( less(temp ,  a[--j]))
                if( j == low )
                    break;
            //check om pointer cross
            if( i >= j)
                break;
            exch( a , i , j );
        }
        //flytta alla elemnt from temp till a
        exch( a , low , j );
        //print(a);
        //a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }
 //quicksortm
    public static void quicksortm (int[] a , int low, int high)
    {
        int n = high - low + 1;
        if( n <= cutoff)
        {
            insertionSort( a , low , high );
           // print(a);
            return;
        }
        int  m = median ( a ,low, low + n/2 , high);
        exch(a, m ,low);//byte median med low 

        int j = partition( a , low, high);
        quicksortm(a , low , j-1 );
        quicksortm(a , j+1 , high);
    }
//quicksort
    public static void quicksort (int[] a , int low, int high)
    {
        if( high <= low)
            return;
        int j  = partition (a , low , high);
        quicksort(a , low , j - 1);
        quicksort(a , j + 1 , high );
    }

    public static void main (String[] args)
    {
        Random ran = new Random();
        StdOut.println("Give a length : ");
        int length = StdIn.readInt();
        int[] arr = new int [length];
        for(int i = 0 ; i < length ; i++)
        {
            arr[i] = ran.nextInt(100);
           // StdOut.print("["+arr[i] + "]");
        }
       // StdOut.println();

        long startm = System.currentTimeMillis();
        sortm(arr);
        long endm  = System.currentTimeMillis();
        StdOut.println("median time : " + ( endm - startm ) + "ms" );

        long start = System.currentTimeMillis();
        sort(arr);
        long end  = System.currentTimeMillis();

        StdOut.println("time : " + ( end - start ) + "ms" );
    }
}
