
public class AcyclicSP 
{
	private double[] distTo;
	private DirectedEdge[] edgeTo;
	
	public AcyclicSP(EdgeWeightedDigraph G, int s)
	{
		distTo = new double[G.V()];
		edgeTo = new DirectedEdge[G.V()];
		for(int i = 0; i < G.V(); i++)
			distTo[i] = Double.POSITIVE_INFINITY;
		distTo[s] = 0;
		Topological t = new Topological(G);
		
		for(int v : t.order())
		{
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
		}
	}

}
