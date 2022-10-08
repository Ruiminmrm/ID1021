//Code is inspired by the book from Algorithms,4th ed.by Robert Sedgewick, Kevin Wanye.
package lab2;
import java.util.Random;
import edu.princeton.cs.algs4.*;
public class lab24{
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
//insertion
    public static void insertion (int[] arr)
    {
        for (int i = 0 ; i < arr.length ; i++)
        {
            for(int j = i ; j > 0 && (arr[j] < arr[j-1])  ; j--)
            {
                exch(arr, j, j-1);
            }
            //print(arr);  
        }
    }
//mergesort
    public static void merge(int[] arr , int[] aux  , int low , int mid, int high)
    {
        //copia till aux[]
        for( int k = low ; k <= high ; k++ )
            aux[ k ] = arr [ k ];
            
        //merge back to arr[low ...high]
        int i = low , j = mid + 1 ;
        for ( int k = low ; k <= high ; k++)
        {
            if( i > mid )
                arr[k] = aux[j++];
            else if( j >  high)
                arr[k] = aux[i++];
            else if( aux[j] < aux[i])
                arr[k] = aux[j++];
            else 
                arr[k] = aux[i++];
        }
    }
    public static void mergesort(int[] arr , int[] aux, int low , int high)
    {
        int mid = (low + high) / 2;//dela array till 2 
        if( low < high)
        {
            //dela till vänster delen
            mergesort( arr , aux , low , mid );
            //dela till höger delen
            mergesort( arr , aux , mid + 1 , high);
            //mergar dem
            merge (arr , aux , low , mid , high);
        }
        else
            return;
        //print(arr);
    } 
//quicksort
    // subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
    public static int partition(int a[], int low , int high)
    {
        //partition into a[lo...i-1] , a[i] , a[i+1...hi]
        int i = low, j = high + 1 ;
        int temp = a[low];
        while (true)
        {
            //hitta alla tal som är mindre än temp 
            while (a[++i] < temp)
                if( i == high)// i == high ----> a[i] sista element 
                    break;
            //hitta alla tal som är större än temp
            while( temp < a[--j])
                if( j == low )// j == low ----> a[j] första element 
                    break;
            //check om pointer cross
            if( i >= j)
                break;
            exch( a , i , j );
        }
        //put temp = a[j] into position a
        exch( a , low , j ); 
        //print(a);
        return j;//a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
    }
    //quicksort the subarray from a[low] to a[high]
    public static void quicksort (int[] a , int low, int high)
    {
        if( high <= low)//low och high är index .  
                        // low >= high--->det finns en element eller det inte finns element alls 
            return;
        int j  = partition (a , low , high);
        quicksort(a , low , j - 1);//quick sort subarray 
        quicksort(a , j + 1 , high );//quick sort subarray
    }

    public static void main (String[] args)
    {
        Random ran = new Random(10);
        StdOut.println("Give a length : ");
        int length = StdIn.readInt();
        int a[] = new int [length];
        int b[] = new int [length];
        int c[] = new int [length];
        int n[] = new int [length];
        for(int i = 0 ; i < a.length ; i++)
        {
            a[i] = ran.nextInt(100);
            b[i] = a[i];
            c[i] = a[i];
        }  

        long insertion = System.currentTimeMillis();
        insertion(a);
        long insertionend  = System.currentTimeMillis();
        StdOut.println("Insertion time : " + ( insertionend - insertion ) + "ms" );

        long mergesort = System.currentTimeMillis();
        mergesort(b, n , 0 , b.length-1);
        long mergesortend  = System.currentTimeMillis();
        StdOut.println("Mergesort time : " + ( mergesortend - mergesort ) + "ms" );

        long quicksort = System.currentTimeMillis();
        quicksort(c, 0 , c.length-1);
        long quicksortend  = System.currentTimeMillis();
        StdOut.println("Quicksort time : " + ( quicksortend - quicksort ) + "ms" );
    }
}
/*
Give a length : 
100
Insertion time : 0ms
Mergesort time : 0ms
Quicksort time : 0ms

Give a length : 
1000
Insertion time : 4ms
Mergesort time : 1ms
Quicksort time : 0ms

Give a length : 
10000
Insertion time : 48ms
Mergesort time : 3ms
Quicksort time : 1ms

Give a length : 
100000
Insertion time : 4420ms
Mergesort time : 17ms
Quicksort time : 10ms

Give a length : 
200000
Insertion time : 17615ms
Mergesort time : 22ms
Quicksort time : 26ms
*/