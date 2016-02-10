
public class Graph 
{
	private int V;
	private Bag<Integer>[] adjs;
	
	public Graph(int V)
	{
		this.V = V;
		adjs = (Bag<Integer>[]) new Bag[V];
		for (int v = 0; v < V; v++)
			adjs[v] = new Bag<Integer>();
	}
	
	public Graph(In in)
	{
		this.V = in.readInt();
		adjs = (Bag<Integer>[]) new Bag[V];
		for (int v = 0; v < V; v++)
			adjs[v] = new Bag<Integer>();
		
		int E = in.readInt();
		for (int i = 0; i < E; i++)
		{
			int v = in.readInt();
			int w = in.readInt();
			addEdge(v, w);
		}
	}
	
	public void addEdge(int v, int w)
	{
		adjs[v].add(w);
		adjs[w].add(v);
	}
	
	public Iterable<Integer> adj(int v) { return adjs[v]; }
	
	public int V() { return V; }
	
	public int E()
	{
		int res = 0;
		for (int v = 0; v < V; v++)
			res += adjs[v].size();
		return res / 2;
	}
	
	public String toString()
	{
		String res = ((Integer)V).toString();
		res += "\n" + 2 * E() + "\n";
		for(int v = 0; v < V; v++)
		{
			for(int w : adj(v))
				res += v + " " + w + "\n";
		}
		return res;
	}
	
	public static int countSelfLoops(Graph G) {
		int count = 0;
		for(int i = 0; i < G.V(); i++) {
			for(int w : G.adj(i)) {
				StdOut.println(i + "-" + w);
				if(i == w) count++;
			}
		}
		return count / 2;
	}
	
	public static void main(String[] args) {
		Graph G = new Graph(10);
		G.addEdge(1, 1);
		G.addEdge(2, 2);
		G.addEdge(2, 3);
		G.addEdge(3, 3);
		G.addEdge(4, 6);
		G.addEdge(5, 6);
		G.addEdge(6, 6);
		StdOut.println(countSelfLoops(G));
		
	}
	

}
