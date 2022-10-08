import edu.princeton.cs.algs4.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;
import java.util.Scanner;
public class lab37<Key extends Comparable<Key> , Value> {
    private Node root; // root of BST
    private class Node {
        private Key key;            //sorted by key
        private Value val;          //associated data
        private Node left, right;   //left and right subtrees
        private int size;           //number of node in subtree

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
    public int size(Node x){
        if( x == null)
            return 0;
        else
            return x.size;
    }
    //return the value associates with the given key
    public Value get(Key key){
        return get(root,key);
    }
    private Value get(Node x , Key key){
        if(key == null)
            throw new IllegalArgumentException("calls get() with a null key");
        if(x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if(cmp < 0)
            return get(x.left , key);
        else if(cmp > 0)
            return get(x.right, key);
        else
            return x.val;
    }
    public void put(Key key, Value val){
        if( key == null)
            throw new IllegalArgumentException("calls put() with a null key");
        root = put ( root , key , val);
    }
    private Node put (Node x, Key key , Value val){
        if( x == null)
            return new Node(key , val, 1);
        int cmp = key.compareTo(x.key);
        if(cmp < 0)
            x.left = put(x.left , key ,val);
        else if (cmp > 0)
            x.right = put(x.right ,key,val);
        else
            x.val = val;
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }
    public int rank(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to rank() is null");
        return rank(key, root);
    }
    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else              return size(x.left);
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
    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null)
            return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0)
            keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0)
            queue.add(x.key);
        if (cmphi > 0)
            keys(x.right, queue, lo, hi);
    }
    //ny
    public Iterable<Key> alpha() {// a b c d ...
        //skapa två linkedlist för node och key
        LinkedList<Node> s = new LinkedList<>();
        LinkedList<Key> q = new LinkedList<>();
        Node x = root;
        //orderna till rätt ordning (a,b,c,d...)
        while (x != null || !s.isEmpty()) {
            if (x != null) {
                s.push(x);
                x = x.left;
            }
            else {
                x = s.pop();
                q.add(x.key);
                x = x.right;
            }
        }
        return q;
    }

    public Iterable<Key> reverse() {//...d c b a
        //skapa två linkedlist för node och key
        LinkedList<Node> s = new LinkedList<>();
        LinkedList<Key> q = new LinkedList<>();
        Node x = root;
        //orderna till reverse ordning (...d,c,b,a)
        while (x != null || !s.isEmpty()) {
            if (x != null) {
                s.push(x);
                x = x.right;
            }
            else {
                x = s.pop();
                q.add(x.key);
                x = x.left;
            }
        }
        return q;
    }
    public static boolean isletter (String a){
        char[] c = a.toCharArray();//returns h.
        for(int i = 0; i < c.length ; i ++){
            if( !Character.isLetter(c[i])){
                c[i] =' ';
            return false;
            }
        }
        return  true;
    }
    public static void main(String [] args)
            throws Exception
    {
        lab37<String, Integer> st = new lab37<String, Integer>();
        Scanner scanner = new Scanner(new File("C:\\Users\\marui\\IdeaProjects\\lab3\\src\\txt.txt"));
        int range = 200;//begränsa antalet
        for(int i = 0; scanner.hasNext() & range != 0;i++)
        {
            String key = scanner.next();
            key = key.toLowerCase();
            if(isletter(key))
                st.put(key,i);//put in key i index
            range--;
        }
        Scanner in = new Scanner(System.in);

        boolean istrue = true;
        while(istrue){
            StdOut.println(" 1 - alphabetic order \n" + " 2 - reverse alphabetic order \n" + " Otherwise end !\n" + " Your choice :\n" );
            int choice = in.nextInt();
            if (choice == 1)
                for (String s : st.alpha())
                    StdOut.println(s);
            else if (choice == 2)
                for (String s : st.reverse())
                    StdOut.println(s);
            else{
                StdOut.println("Bye !");
                istrue = false;
            }
        }
    }
}


