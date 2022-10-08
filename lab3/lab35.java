import edu.princeton.cs.algs4.*;
import java.util.*;
public class lab35<Key,Value> {
    private int n ;// number of key-value pairs
    private int m ;// hash table size
    private Node[] st ;//array of  linked-list symbol tables
    //a helper linked list data type
    private static class Node {
        private Object key;
        private Object val;
        private Node next;
        //constructor
        public Node(Object key , Object val , Node next){
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }
    //create separate chaining hash table
    public lab35(){
        this(997);//capacity 997
    }
    //create separate chaining hash table with m lists
    public lab35 (int m){
        this.m = m ;
        st =new Node[m];//bygga en node array som ha längde m
    }
    //hash value between 0 and  m-1 ,
    private int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % m;//hash table måste ha positiva heltal ( 0*7fffffff )
                                                 // %m --->  inte overflow och sätt key på rätt index
    }
    //return number of the key-value pairs in symbol table
    public int size(){
        return n;
    }
    //key in the symbol table or not
    public boolean contains(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }
    //return value associated with key
    public Value get(Key key){
        if( key == null)
            throw new IllegalArgumentException(" argument to get() is null" );
        int i = hash(key);// i = index , hitta rätt index för key i hash table
        for(Node x = st[i] ; x != null ; x = x.next) // hitta x i hash table och om det inte är null
            if(key.equals(x.key))//jämföra key med x.key
                return (Value)x.val;//return x value
        return null;
    }
    //insert key-value pair into the table
    public void put(Key key,Value val){
        if(key == null)
            throw new IllegalArgumentException("first argument to put() is null");
        int i = hash(key);// i = index , hitta rätt index för key i hash table
        for(Node x = st[i] ; x != null ; x  = x.next)//overwrite den value för x
           if(key.equals(x.key)){
               x.val = val;
               return;
           }
        if(!contains(key))
            n++;//resize
        st[i] = new Node(key , val , st[i]);//skriva om array
    }
    //return alla keys as an iterable
    public Iterable<Key> keys(){
        LinkedList<Key> queue = new LinkedList<Key>();
        for(int i = 0; i < m ; i++)
            for(Node x = st[i] ; x != null; x = x.next)
                queue.add((Key) x.key);
        return queue;
    }
    public void print(){
        for (int i = 0; i < st.length ; i++) {
            int size = 0;//rärkna antalet key i intervallet
            Node l = st[i];// l pekar på element i array
            while (l != null) {//om det är tomt array
                size++;
                l = l.next;//pekar på nästa element
            }
            System.out.println(size);
        }
    }
    public static void main(String[] args) {
        lab35<String, Integer> st = new lab35<String, Integer>(97); //hela hash table array delar till 97 intervallet
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        st.print();
    }
}
