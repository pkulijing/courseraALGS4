
public class Diagraph 
{
	public final int V;
	public final Bag<Integer>[] adjs;
	public Diagraph(int V) 
	{
		this.V = V;
		this.adjs = (Bag<Integer>[] ) new Bag[V];
	}
	
	public Diagraph(In in)
	{
		this.V = in.readInt();
		this.adjs = (Bag<Integer>[] ) new Bag[V];
		int E = in.readInt();
		for(int i = 0; i < E; i++)
		{
			int v = in.readInt();
			int w = in.readInt();
			addEdge(v, w);
		}
	}
	
	public void addEdge(int v, int w) { adjs[v].add(w); }
	
	public Iterable<Integer> adj(int v) { return adjs[v]; }
	
	public int V() { return V; }
	
	public int E() 
	{
		int res = 0;
		for(int i = 0; i < V; i++)
			res += adjs[i].size();
		return res;
	}
	
	public Diagraph reverse() 
	{
		Diagraph GR = new Diagraph(this.V);
		for(int v = 0; v < this.V; v++)
			for(int w : this.adjs[v])
				GR.addEdge(w, v);
		return GR; 
	}

}
