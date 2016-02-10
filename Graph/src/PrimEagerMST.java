
public class PrimEagerMST 
{
	private Edge[] edgeTo;
	private double[] distTo;
	private boolean[] marked;
	private IndexMinPQ<Double> pq;
	public PrimEagerMST(EdgeWeightedGraph G) 
	{
		edgeTo = new Edge[G.V()];
		distTo = new double[G.V()];
		for(int i = 0; i < G.V(); i++)
			distTo[i] = Double.POSITIVE_INFINITY;
		marked = new boolean[G.V()];
		pq = new IndexMinPQ<Double>(G.V());
		
		distTo[0] = 0;
		pq.insert(0, 0.0);
		
		while(!pq.isEmpty())
			visit(G, pq.delMin());
	}
	
	private void visit(EdgeWeightedGraph G, int v)
	{
		marked[v] = true;
		for(Edge e : G.adj(v))
		{
			int w = e.other(v);
			if(marked[w]) continue;
			if(e.weight() < distTo[w])
			{
				if(pq.contains(w))
					pq.changeKey(w, e.weight());
				else
					pq.insert(w, e.weight());
				distTo[w] = e.weight();
				edgeTo[w] = e;
			}
		}
	}
	
	public Iterable<Edge> edges()
	{
		Queue<Edge> mst = new Queue<Edge>();
		for(int i = 1; i < edgeTo.length; i++)
			mst.enqueue(edgeTo[i]);
		return mst;
	}
	
	public double weight()
	{
		double res = 0.0;
		for(int i = 0; i < distTo.length; i++)
			res += distTo[i];
		return res;
	}

}
