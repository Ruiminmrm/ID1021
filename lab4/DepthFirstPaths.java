package lab4;
public class DepthFirstPaths {
    private boolean[] marked;    // chekca om vertex är visited 
    private int[] edgeTo;        // sista vertex på s-v path, (from)
    private final int s;         // source vertex

    // skapa en path mellan s och de andra vertex i graph G 
    public DepthFirstPaths(Graph G, int s) {
        this.s = s; 
        edgeTo = new int[G.V()];    
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    // depth first search from v
    private void dfs(Graph G, int v) {
        marked[v] = true;           // sätta v = true --- börja från den vertex
        for (int w : G.adj(v)) {    
            if (!marked[w]) {       // om v's adjacency är inte markerat --- not visited 
                edgeTo[w] = v;      // lägg w i int[]
                dfs(G, w);          // recurtion
            }
        }
    }
    // boolean --- om det finns en path between vertex v och source s, return true 
    //         --- om det inte finns en path between vertex v och source s , return false
    public boolean hasPathTo(int v) {
        return marked[v];
    }
    
    // Returns a path between the source vertex and vertex
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v))  // om det inte finns en path mellan de 
            return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }
}
 