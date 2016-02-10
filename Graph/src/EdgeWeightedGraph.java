
public class EdgeWeightedGraph 
{
	private int V, E;
	private Bag<Edge>[] adjs;
	
	public EdgeWeightedGraph(int V)
	{
		this.V = V;
		this.E = 0;
		this.adjs = (Bag<Edge>[]) new Bag[V];
		for(int i = 0; i < V; i++)
			this.adjs[i] = new Bag<Edge>();
	}
	
	public EdgeWeightedGraph(In in)
	{
		V = in.readInt();
		int E = in.readInt();

		adjs = (Bag<Edge>[]) new Bag[V];
		for (int v = 0; v < V; v++)
			adjs[v] = new Bag<Edge>();
		
		for (int i = 0; i < E; i++)
		{
			int v = in.readInt();
			int w = in.readInt();
			double weight = in.readDouble();
			Edge e = new Edge(v, w, weight);
			addEdge(e);
		}
	}
	
	public void addEdge(Edge e)
	{
		int v = e.either();
		int w = e.other(v);
		adjs[v].add(e);
		adjs[w].add(e);
		E++;
	}
	
	public Iterable<Edge> adj(int v) { return adjs[v]; }
	
	public Iterable<Edge> edges()
	{
		Bag<Edge> b = new Bag<Edge>();
		for(int v = 0; v < V; v++)
			for(Edge e : adjs[v])
				if(e.other(v) > v)
					b.add(e);
		return b;	
	}
	
	public int V() { return V; }
	
	public int E() { return E; }
	
	public String toString()
	{
		String s = V + " " + E + '\n';
		for(Edge e : edges())
			s += e.toString() + '\n';
		return s;
	}

}
