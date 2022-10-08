import edu.princeton.cs.algs4.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
public class test<Key extends Comparable<Key> , Value> {
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

    public int size(Node x) {
        if (x == null)
            return 0;
        else
            return x.size;
    }

    //return the value associates with the given key
    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (key == null)
            throw new IllegalArgumentException("calls get() with a null key");
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return get(x.left, key);
        else if (cmp > 0)
            return get(x.right, key);
        else
            return x.val;
    }

    public void put(Key key, Value val) {
        if (key == null)
            throw new IllegalArgumentException("calls put() with a null key");
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null)
            return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = put(x.left, key, val);
        else if (cmp > 0)
            x.right = put(x.right, key, val);
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
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
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
    public Key select(int rank) {
        if (rank < 0 || rank >= size()) {
            throw new IllegalArgumentException("argument to select() is invalid: " + rank);
        }
        return select(root, rank);
    }

    // Return key in BST rooted at x of given rank.
    // Precondition: rank is in legal range.
    private Key select(Node x, int rank) {
        if (x == null) return null;
        int leftSize = size(x.left);
        if (leftSize > rank) return select(x.left, rank);
        else if (leftSize < rank) return select(x.right, rank - leftSize - 1);
        else return x.key;
    }

    public Key revers(int rank) {
        if (rank < 0 || rank >= size()) {
            throw new IllegalArgumentException("argument to select() is invalid: " + rank);
        }
        return revers(root, rank);
    }

    private Key revers(Node x, int rank) {
        if (x == null) return null;
        int leftSize = size(x.right);
        if (leftSize > rank) return revers(x.right, rank);
        else if (leftSize < rank) return revers(x.left, rank - leftSize - 1);
        else return x.key;
    }

    public static boolean isletter(String a) {
        char[] c = a.toCharArray();//returns h.
        for (int i = 0; i < c.length; i++) {
            if (!Character.isLetter(c[i])) {
                c[i] = ' ';
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args)
            throws Exception {
        test<String, Integer> st = new test<String, Integer>();
        Scanner scanner = new Scanner(new File("C:\\Users\\marui\\IdeaProjects\\lab3\\src\\txt.txt"));
        int range = 200;//begränsa antalet
        for (int i = 0; scanner.hasNext() & range != 0; i++) {
            String key = scanner.next();
            key = key.toLowerCase();
            if (isletter(key))
                st.put(key, i);//put in key i index
            range--;
        }
        Scanner in = new Scanner(System.in);

        boolean istrue = true;
        while (istrue) {
            StdOut.println(" 1 - alphabetic order \n" + " 2 - reverse alphabetic order \n" + " Otherwise end !\n" + " Your choice :\n");
            int choice = in.nextInt();
            if (choice == 1){
                for (int i = 0; i < st.size(); i++)
                    StdOut.println(i + " " + st.revers(i));
                StdOut.println();
            }
            else if (choice == 2){
                for (int i = 0; i < st.size(); i++)
                    StdOut.println(i + " " + st.select(i));
                StdOut.println();
            }
            else{
                StdOut.println("Bye !");
                istrue = false;
            }
        }
    }
}