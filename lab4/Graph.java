package lab4;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.*;
public class Graph {
     private final int V;       // antalet vertices 
     private int E;             // antalet edges
     private Bag<Integer>[] adj;// adjacency list
     
     public Graph(int V) {
         if (V < 0) 
            throw new IllegalArgumentException("Number of vertices must be non-negative");
         this.V = V;
         this.E = 0;                          
         adj = (Bag<Integer>[]) new Bag[V]; // skapa en array av lister
         for (int v = 0; v < V; v++)        // initialserae alla lister till tomt list
             adj[v] = new Bag<Integer>();
     }
 
     public Graph(In in) {
         if (in == null)          // om det inte finns input , throw exception                          
            throw new IllegalArgumentException("argument is null");
         try {
             this.V = in.readInt(); // läsa antalet av vertices och bygger upp grafen 
             if (V < 0)         // om det finns negativt antal av vertices , throw exception
                throw new IllegalArgumentException("number of vertices in a Graph must be non-negative");

             adj = (Bag<Integer>[]) new Bag[V]; // skapa en array av lister
             for (int v = 0; v < V; v++)        // initialserae alla lister till tomt list
                 adj[v] = new Bag<Integer>();
        
             int E = in.readInt();              // läsa edge
             if (E < 0)                         // om antal av edges är negativ , throw exception 
                throw new IllegalArgumentException("number of edges in a Graph must be non-negative");

             for (int i = 0; i < E; i++) {      // addera en edge 
                 int v = in.readInt();          //läsa en vertex v
                 int w = in.readInt();          //läsa en vertex
                 addEdge(v, w);                 // addera edge mellan v  och w
             }
         }
         catch (NoSuchElementException e) {
             throw new IllegalArgumentException("invalid input format in Graph constructor", e);
         }
     }
     public int V() {
         return V;
     }
 
     public int E() {
         return E;
     }

     public void addEdge(int v, int w) {
         E++;                           // antalet edges ++
         adj[v].add(w);                 //addera w till v's list
         adj[w].add(v);                 //addera v till w's list
     }
 
     public Iterable<Integer> adj(int v) {
         return adj[v];
     }
 }
 
     

