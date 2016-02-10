
public class PrimLazyMST 
{
	private Queue<Edge> mst;
	private MinPQ<Edge> pq;
	private boolean[] marked;
	public PrimLazyMST(EdgeWeightedGraph G)
	{
		mst = new Queue<Edge>();
		pq = new MinPQ<Edge>();
		marked = new boolean[G.V()];
		
		visit(G, 0);
		
		while(!pq.isEmpty() && mst.size() < G.V() - 1)
		{
			Edge e = pq.delMin();
			int v = e.either(), w = e.other(v);
			if(marked[v] && marked[w]) continue;
			mst.enqueue(e);
			if(!marked[v]) visit(G, v);
			if(!marked[w]) visit(G, w);
		}
	}
	
	private void visit(EdgeWeightedGraph G, int v)
	{
		marked[v] = true;
		for(Edge e : G.adj(v))
		{
			int w = e.other(v);
			if(!marked[w])
				pq.insert(e);
		}
	}
	
	public Iterable<Edge> edges() { return mst; }
	
	public double weight()
	{
		double res = 0;
		for(Edge e : edges())
			res += e.weight();
		return res;
	}
}
