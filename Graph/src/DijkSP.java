
public class DijkSP 
{
	private double[] distTo;
	private DirectedEdge[] edgeTo;
	private IndexMinPQ<Double> pq;
	
	public DijkSP(EdgeWeightDigraph G, int s)
	{
		distTo = new double[G.V()];
		for(int i = 0; i < G.V(); i++)
			distTo[i] = Double.POSITIVE_INFINITY;
		distTo[s] = 0.0;
		edgeTo = new DirectedEdge[G.V()];
		pq = new IndexMinPQ<Double>(G.V());
		
		pq.insert(s, 0.0);
		while(!pq.isEmpty())
		{
			int v = pq.delMin();
			for(DirectedEdge e : G.adj(v))
				relax(e);
		}
		
	}
	private void relax(DirectedEdge e)
	{
		int v = e.from(), w = e.to();
		if(distTo[w] > distTo[v] + e.weight())
		{
			distTo[w] = distTo[v] + e.weight();
			edgeTo[w] = e;
			if(pq.contains(w)) pq.decreaseKey(w, distTo[w]);
			else pq.insert(w, distTo[w]);
		}
	}
	
	public double distTo(int v) { return distTo(v); }
	
	public Iterable<DirectedEdge> pathTo(int v)
	{
		Stack<DirectedEdge> s = new Stack<DirectedEdge>();
		while(edgeTo[v] != null)
		{
			s.push(edgeTo[v]);
			v = edgeTo[v].from();
		}
		return s;
	}

}
