import edu.princeton.cs.algs4.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class lab36<Key, Value> {
    private int n;// number of key-value pairs
    private int m;// hash table size
    private Node[] st;//array of  linked-list symbol tables

    //a helper linked list data type
    private static class Node {
        private Object key;
        private Object val;
        private lab36.Node next;

        public Node(Object key, Object val, lab36.Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    //create separate chaining hash table
    public lab36() {
        this(997);
    }

    //create separate chaining hash table with m lists
    public lab36(int m) {
        this.m = m;
        st = new lab36.Node[m];
    }

    //hash value between 0 and  m-1 ,
    private int hashCode(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    //return number of the key-value pairs in symbol table
    public int size() {
        return n;
    }

    //key in the symbol table or not
    public boolean contains(Key key) {

        if (key == null)
            throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    //return value associated with key
    public Value get(Key key) {
        if (key == null)
            throw new IllegalArgumentException(" argument to get() is null");
        int i = hashCode(key);
        for (Node x = st[i]; x != null; x = x.next)
            if (key.equals(x.key))
                return (Value) x.val;
        return null;
    }
    public void put(Key key, Value val) {
        if (key == null)
            throw new IllegalArgumentException("first argument to put() is null");
        int i = hashCode(key);
        if (!contains(key))
            n++;
        st[i] = new Node(key, val, st[i]);
    }

    //return alla keys as an iterable
    public Iterable<Key> keys() {
        LinkedList<Key> queue = new LinkedList<Key>();
        for (int i = 0; i < m; i++) {
            lab36.Node x = st[i];
            while (x != null) {
                queue.add((Key) x.key);
                x = x.next;
            }
        }
        return queue;
    }
    //ny
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        //scanner in file
        Scanner scanner = new Scanner(new File("C:\\Users\\marui\\IdeaProjects\\lab3\\src\\txt.txt"));
        //skapa hash table
        lab36<String, Integer> st = new lab36<String, Integer>();
        //put key
        for(int i = 0; scanner.hasNext() ;i++)
        {
            String key = scanner.next();
            st.put(key,i);
        }

        boolean choice = true;
        //för att skippa put när vi köra om, börjar vi med while(true)
        while(true){
            StdOut.println("choice 1. Hitta antal av ord");
            StdOut.println("choice 2. End");
            StdOut.println("Choose 1 or 2 !");
            int  i = in.nextInt();
            switch(i){
                case 1 :
                    int size = 0;// sätt size = 0 i början och varje gång när det köra om.
                    StdOut.println("Give a word :");
                    String str = in.next();
                    for (String s : st.keys()) {
                        if( st.get(s) == st.get(str))//if key.hashcode  == givet ord.hashcode
                        size++;
                    }
                    StdOut.println(size);
                case 2 :
                    choice = false;
                default:
                    StdOut.println("Bye!");
            }
        }
    }
}

