import edu.princeton.cs.algs4.*;
import java.util.LinkedList;
public class lab32<Key extends Comparable<Key> , Value>
{
    private Key[] keys;//skapa en key array och en value array
    private Value[] values;
    private int n = 0;
    //Initializes an empty symbol table with the specified initial capacity
    public lab32(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[])new Object[capacity];
    }
    //returns the number of the key value pairs in this symbol table
    public int size(){
        return n;
    }
    //return true if this symbol is empty
    public boolean isEmpty(){
        return size() == 0;
    }
    //contain the given key in the symbol table or not
    public boolean contains (Key key){
        if (key == null)
            throw new IllegalArgumentException("argument to get() is null");
        return get(key) != null;
    }
    //return the value associated with the given key in this symbol table
    public Value get(Key key){
        if(key == null)//key == null --> det finns inte key
            throw new IllegalArgumentException("argument to get() is null");
        if(isEmpty())// tomt array
            return null;
        int i = rank(key); // i - index
        if( i < n && keys[i].compareTo(key) == 0)// jämföra keys index med size och checka att de somma key
            return values[i];// return value st table
        return null;
    }
    //return the number of keys in this symbol table strictly less than the key
    //ordered array
    public int rank(Key key){
        if(key == null)//key == null --> det finns inte key
            throw new IllegalArgumentException("argument to rank() is null");
        int low  =0 , high = n - 1;// A B C D E F G (A - low , G - high)
        while (low <= high){ //om A <= G
            int mid = low + (high -low) / 2;// D - mid
            int compare = key.compareTo(keys[mid]); // KEY jämföra med D
            // array dela till små delar och hitta plats för KEY
            if(compare < 0)//Key ligger på vänster delen
                high = mid - 1;
            else if ( compare > 0)//Key ligger på höger delen
                low = mid + 1;
            else//return mid
                return mid;
        }
        return low;
    }
    //insert the specified key value pair
    public void put(Key key , Value value){
        if(key == null)// det finns inte key
            throw new IllegalArgumentException("first argument to put() is null");
        int i = rank(key);// i - index
        //lägga key i table
        if( i < n && keys[i].compareTo(key) == 0){
            values[i] = value;
            return;
        }
    }
    //return all keys in this symbol table in the given range
    public Iterable<Key> keys (Key low , Key high){
        if(low == null)
            throw new IllegalArgumentException("first argument to keys() is null");
        if( high == null)
            throw new IllegalArgumentException("second argument to keys() is null");
        LinkedList<Key> queue = new LinkedList<>();
        if(low.compareTo(high) > 0)
            return queue;
        for(int i = rank(low) ; i < rank(high) ; i++)
            queue.add(keys[i]);
        if(contains(high))
            queue.add(keys[rank(high)]);
        return queue;
    }
    public static boolean isletter (String a){
        char[] c = a.toCharArray();//returns h.
        for(int i = 0; i < c.length ; i ++)
            if( !Character.isLetter(c[i])){
                c[i] =' ';
                return false;
            }
        return  true;
    }
}