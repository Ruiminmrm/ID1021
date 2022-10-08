import java.util.LinkedList;
import edu.princeton.cs.algs4.*;

public class lab33 <Key extends Comparable<Key> , Value> {
    private Node root; // root of BST
    private class Node {
        private Key key;            //sorted by key
        private Value val;          //associated data
        private Node left, right;   //left and right subtrees
        private int size;           //number of node in subtree
        //constructor
        public Node(Key key, Value value, int size) {
            this.key = key;
            this.val = value;
            this.size = size;
        }
    }
    public boolean isEmpty() {
        return size() == 0;
    }
    public int size() {
        return size(root);
    }
    //return antal av Nodes
    public int size(Node x){
        if( x == null)
            return 0;
        else
            return x.size;
    }
    public boolean contains(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }
    //return the value associates with the given key
    public Value get(Key key){
        return get(root,key);
    }
    private Value get(Node x , Key key){
        if(key == null)// det finns inte key
            throw new IllegalArgumentException("calls get() with a null key");
        if(x == null)// det finns inte node
            return null;
        //compare key med x.key
        int cmp = key.compareTo(x.key);
        if(cmp < 0)// key är mindre än x.key ---> jämför key med x.left (ny x)
            return get(x.left , key);
        else if(cmp > 0)// key är större än x.key ---> jämför key med x.right (ny x)
            return get(x.right, key);
        else
            return x.val;// return the key när vi hittar rätt
    }
    //insert the specified key value pair into the st, overwriting the old value with the new value
    //if the symbol table already contains the specified key.
    public void put(Key key, Value val){
        if( key == null)
            throw new IllegalArgumentException("calls put() with a null key");
        root = put ( root , key , val);
    }
    private Node put ( Node x, Key key , Value val){
        if( x == null)// insert en ny node om det är tomt
            return new Node (key , val, 1);
        int cmp = key.compareTo(x.key);
        if(cmp < 0)// jämför x.left med key recursive
            x.left = put(x.left , key ,val);
        else if (cmp > 0)// jämför x.right med key recursive
            x.right = put(x.right ,key,val);
        else
            x.val = val;//overwrite the value
        x.size = 1 + size(x.left) + size(x.right);//ny size efter put
        return x;
    }
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null)
            throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null)
            throw new IllegalArgumentException("second argument to keys() is null");

        LinkedList<Key> q = new LinkedList<>();
        keys(root, q, lo, hi);
        return q;
    }
    private void keys(Node x, LinkedList<Key> queue, Key lo, Key hi) {
        if (x == null)// tomt node
            return;
        int cmplo = lo.compareTo(x.key);// key jämför med node high
        int cmphi = hi.compareTo(x.key);// key jämför med node low
        if (cmplo < 0)// key mindre än node low
            keys(x.left, queue, lo, hi);//köra keys metod igen med x.left istället för x
        if (cmplo <= 0 && cmphi >= 0)// low <= key <= high
            queue.add(x.key);//lägg key i list
        if (cmphi > 0)// key mindre än node low
            keys(x.right, queue, lo, hi);//köra keys metod igen med x.right istället för x
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
    public static void main(String [] args){
        int words = 0, range = 1000;
        lab33<String, Integer> st = new lab33<String, Integer>();
        Stopwatch timer = new Stopwatch();
        // compute frequency counts
        while (!StdIn.isEmpty() && range != 0) {
            String key = StdIn.readString();
            words++;
            range--;
            if(isletter(key)) {
                if (st.contains(key))
                    st.put(key, st.get(key) + 1);
                else
                    st.put(key, 1);
            }
        }
        double time = timer.elapsedTime();
        StdOut.println("words    = " + words);
        StdOut.println("time     = " + time);
    }
}

