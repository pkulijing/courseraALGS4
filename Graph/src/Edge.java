
public class Edge implements Comparable<Edge> 
{
	private int v, w;
	private double weight;
	public Edge(int v, int w, double weight)
	{
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	
	public int either() { return v; }
	public int other(int vertex)
	{
		if(vertex == v) return w;
		if(vertex == w) return v;
		throw new RuntimeException(vertex + " is not a vertex of " + toString());
	}
	
	public int compareTo(Edge other)
	{
		if(this.weight > other.weight) return 1;
		if(this.weight < other.weight) return -1;
		return 0;
	}
	
	public double weight() { return weight; }
	
	public String toString()
	{
		return String.format("%d-%d %.5f", v, w, weight);
	}

}
