public class SAP 
{
    private static int INF = Integer.MAX_VALUE;
    private Digraph G;
    private int len, anc;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G)
    {
        this.G = new Digraph(G);
        this.len = -1;
        this.anc = -1;
    }

    private void findSAP(int v, int w)
    {   
        SET<Integer> vSet = new SET<Integer>(), wSet = new SET<Integer>();
        vSet.add(v);
        wSet.add(w);
        findSAP(vSet, wSet);
    }   
   
    private void findSAP(Iterable<Integer> v, Iterable<Integer> w)
    {
        len = -1;
        anc = -1;
        BreadthFirstDirectedPaths bfsv = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths bfsw = new BreadthFirstDirectedPaths(G, w);
        for (int i = 0; i < G.V(); i++)
        {
            int dv = bfsv.distTo(i);
            int dw = bfsw.distTo(i);
            if (dv < INF && dw < INF)
            {
                if (len == -1 || dv + dw < len)
                {
                    len = dv + dw;
                    anc = i;
                }
            }
        }
     }
    // length of shortest ancestral path between v and w;
    //-1 if no such path
    public int length(int v, int w)
    {
        findSAP(v, w);
        return len;
    }

    // a common ancestor of v and w that participates in a 
    //shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w)
    {
        findSAP(v, w);
        return anc;
    }

    // length of shortest ancestral path between any vertex 
    //in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w)
    {
        findSAP(v, w);
        return len;
    }

    // a common ancestor that participates in shortest ancestral path;
    //-1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
    {
        findSAP(v, w);
        return anc;
    }

    // do unit testing of this class
    public static void main(String[] args)
    {
         In in = new In(args[0]);
         Digraph G = new Digraph(in);
         SAP sap = new SAP(G);
         while (!StdIn.isEmpty()) 
         {
             int v = StdIn.readInt();
             int w = StdIn.readInt();
             int length   = sap.length(v, w);
             int ancestor = sap.ancestor(v, w);
             StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
         }
    }
}