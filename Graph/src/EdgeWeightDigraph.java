
public class EdgeWeightDigraph 
{
	private int V, E;
	private Bag<DirectedEdge>[] adjs;
	
	public EdgeWeightDigraph(int V)
	{
		this.V = V;
		this.E = 0;
		this.adjs = (Bag<DirectedEdge>[]) new Bag[V];
		for(int i = 0; i < V; i++)
			this.adjs[i] = new Bag<DirectedEdge>();
	}
	
	public EdgeWeightDigraph(In in)
	{
		V = in.readInt();
		int E = in.readInt();

		adjs = (Bag<DirectedEdge>[]) new Bag[V];
		for (int v = 0; v < V; v++)
			adjs[v] = new Bag<DirectedEdge>();
		
		for (int i = 0; i < E; i++)
		{
			int v = in.readInt();
			int w = in.readInt();
			double weight = in.readDouble();
			DirectedEdge e = new DirectedEdge(v, w, weight);
			addEdge(e);
		}
	}
	
	public void addEdge(DirectedEdge e)
	{
		int v = e.from();
		adjs[v].add(e);
		E++;
	}
	
	public Iterable<DirectedEdge> adj(int v) { return adjs[v]; }
	
	public int V() { return V; }
	public int E() { return E; }
	
	public Iterable<DirectedEdge> edges()
	{
		Queue<DirectedEdge> res = new Queue<DirectedEdge>();
		for(int i = 0; i < V; i++)
			for(DirectedEdge e : adjs[i])
				res.enqueue(e);
		return res;
	}
	
	public String toString()
	{
		String s = V + " " + E + '\n';
		for(DirectedEdge e : edges())
			s += e.toString() + '\n';
		return s;
	}
	
	

}
