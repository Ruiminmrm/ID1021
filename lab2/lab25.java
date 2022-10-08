//Code is inspired by the book from Algorithms,4th ed.by Robert Sedgewick, Kevin Wanye.
package lab2;
import java.util.Random;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
public class lab25 {
    private static final int cut = StdIn.readInt();
    public static boolean less(int a, int b) 
    {
        return a < b;
    }
    public static void print (int[] arr)
    {
        for(int i = 0; i < arr.length ; i++)
            StdOut.print("["+ arr[i] + "] , " );
        StdOut.println();
    }
    public static void exch(int[] arr, int a, int b)
    {
        int n = arr[a] ;
        arr[a] = arr[b] ;
        arr[b] = n ;
    }
//insertion sort i subarray ----> börja low till high
    public static void insertion (int[] arr , int low , int high)
    {
        for (int i = low ; i <= high ; i++)
            for(int j = i ; j > low && less(arr[j] , arr[j-1])  ; j--)
                exch(arr, j, j-1);
        //print(arr); 
    }
//mergesort
    public static void merge(int[] arr ,int[] aux , int low , int mid, int high)
    {
        //copia till aux[]
        for( int k = low ; k <= high ; k++ )
            aux[ k ] = arr [ k ];

        //merge tillbaka till a[]
        int i = low , j = mid + 1 ;
        for ( int k = low ; k <= high ; k++)
        {
            if( i > mid )
                aux[k] = arr[j++];
            else if( j >  high)
                aux[k] = arr[i++];
            else if( less(arr[j] , arr[i]))
                aux[k] = arr[j++];
            else 
                aux[k] = arr[i++];
        }
        //print(arr);
    }
    public static void sort(int[] arr , int[] aux, int low , int high )
    { 
        //delar subarray
        if ( high <= low + cut) // x(low)xxxx(high) a(low)aaaa(high) c(low)cccc(high) 
        {
            insertion(aux, low, high);
            return;
        }
        int mid = low +  ( high - low ) / 2;
        //dela vänster delen till 2
        sort( aux , arr , low , mid);
        //dela höger delen till 2
        sort( aux , arr , mid + 1 , high );

        // arr[mid+1] >= arr[mid] ---> den delen arrayen är sorterat kart 
        if( !less(arr[mid+1], arr[mid]) )
        {
            for(int i = low ; i <= high ; i++)//kopia till aux[] och return.
                aux[i] = arr[i]; 
            return ; 
        }
        // mergar dem    
        merge(arr , aux , low , mid , high); 
    }
    //sortera om array 
    public static void sort(int[] arr)
    {
        int[] aux = arr.clone();
        sort(aux , arr , 0 , arr.length - 1);
    }

    public static void main (String[] args)
    {
        Random ran = new Random();
        StdOut.println("Give a length : ");
        int length = StdIn.readInt();
        int[] arr = new int [length];
        for(int i = 0 ; i < length ; i++)
            arr[i] = ran.nextInt(100);

        long start = System.currentTimeMillis();
        sort(arr);
        long end  = System.currentTimeMillis();
        StdOut.println("time : " + ( end - start ) + "ms" );
        //print(arr);
    }
}
/*
Array.length = 10 000 000
Number  Time(ms)
0       709
1       609
5       611 
10      551
15      573
20      626
25      576
30      623

Array.length = 1000 000
Number  Time(ms)
0       72
1       67
5       65
10      63  
15      64
20      64
25      68
30      77
1. Det är snabbare när det finns en subarray som har mer element sorteras av insertion sort. 
   Eftersom insertion sort  är effectivt med mindre data grupp. 
*/