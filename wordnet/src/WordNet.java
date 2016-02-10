public class WordNet 
{
    private RedBlackBST<String, SET<Integer>> index;
    private String[] record;
    private Digraph net;
    private SAP pathFinder;
    
    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms)
    {
        index = new RedBlackBST<String, SET<Integer>>(); 
        In in = new In(synsets);
        record = in.readAllLines();
        int N = record.length;
        for (int i = 0; i < N; i++)
        {
            String[] segs = record[i].split(",");
            record[i] = segs[1];
            String[] words = segs[1].split("\\s+");
            for (String word : words)
            {
                SET<Integer> ints = index.get(word);
                if (ints != null)
                    ints.add(i);
                else
                {
                    ints = new SET<Integer>();
                    ints.add(i);
                    index.put(word, ints);
                }
            }
        }
        net = new Digraph(N);
        in = new In(hypernyms);
        while (!in.isEmpty())
        {
            String[] segs = in.readLine().split(",");
            int v = Integer.parseInt(segs[0]);
            for (int j = 1; j < segs.length; j++)
            {
                int w = Integer.parseInt(segs[j]);
                net.addEdge(v, w);
            }
        }
        
        DirectedCycle cycle = new DirectedCycle(net);
        if (cycle.hasCycle())
            throw new IllegalArgumentException("Has cycle!");
        
        DepthFirstOrder dforder = new DepthFirstOrder(net);
        int root = -1;
        for (int v : dforder.post())
        {
            root = v;
            break;
        }
        BreadthFirstDirectedPaths rbfs = 
                new BreadthFirstDirectedPaths(net.reverse(), root);
        for (int v = 0; v < net.V(); v++)
        {
            if (!rbfs.hasPathTo(v))
                throw new IllegalArgumentException("Not rooted!");
        }
        pathFinder = new SAP(net);
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() { return index.keys(); }

    // is the word a WordNet noun?
    public boolean isNoun(String word) { return index.get(word) != null; } 

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB)
    {    
        Iterable<Integer> ia = index.get(nounA), ib = index.get(nounB);
        if (ia == null || ib == null)
            throw new IllegalArgumentException();
        return pathFinder.length(ia, ib);
    }

    // a synset (second field of synsets.txt) that is the common
    //ancestor of nounA and nounB in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) 
    {
        Iterable<Integer> ia = index.get(nounA), ib = index.get(nounB);
        if (ia == null || ib == null)
            throw new IllegalArgumentException();
        int i = pathFinder.ancestor(ia, ib);
        return record[i];
    } 

    // do unit testing of this class
    public static void main(String[] args) 
    {
        WordNet net = new WordNet(args[0], args[1]);
        String s1, s2;
        while (true)
        {
            s1 = StdIn.readString();
            s2 = StdIn.readString();
            StdOut.printf("%s %s, distance = %d, sap = %s\n",
                s1, s2, net.distance(s1, s2), net.sap(s1, s2));
        }
    }
}
