package lab4;
public class BreadthFirstPaths {
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;  // checka om verrtex är visited 
    private int[] edgeTo;      // sista edge på s-v path (kortast path), (from)
    private int[] distTo;      // (to)

    // skapa en kortast path mellan s och de andra vertex i graph G
    public BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];    
        edgeTo = new int[G.V()];    
        bfs(G, s);
    }

    // breadth-first search from a single source
    private void bfs(Graph G, int s) {
        Queue<Integer> q = new Queue<Integer>();
        for (int v = 0; v < G.V(); v++)
            distTo[v] = INFINITY; 
        distTo[s] = 0;
        marked[s] = true;                       // markera den source
        q.enqueue(s);                           // lägg source i queue

        while (!q.isEmpty()) {                  // om queue inte är tomt 
            int v = q.dequeue();                // ta bort nästa edge från the queue
            for (int w : G.adj(v)) {
                if (!marked[w]) {               // om vertex inet är markerat 
                    edgeTo[w] = v;              // spara den edge i den kortast path
                    distTo[w] = distTo[v] + 1;  // till next edge
                    marked[w] = true;           // makera edge
                    q.enqueue(w);               // addera edge i queue 
                }
            }
        }
    }

    // boolean --- om det finns en path between vertex v och source s, return true 
    //         --- om det inte finns en path between vertex v och source s , return false
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    //returnera antalet av edges i en kortaste path mellan v och s 
    public int distTo(int v) {
        return distTo[v];
    }

    // Returns a shortest path between the source vertex {@code s} (or sources)
    public Iterable<Integer> pathTo(int v) {
     //   validateVertex(v);
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x])
            path.push(x);
        path.push(x);
        return path;
    }
}
