
public final class DepthFirstPaths extends Paths
{
	private boolean[] marked;
	private int[] edgeTo;
	private int s;
	public DepthFirstPaths(Graph G, int s) 
	{
		this.marked = new boolean[G.V()];
		this.edgeTo = new int[G.V()];
		this.s = s;
		dfs(G, s); 
	}
	
	private void dfs(Graph G, int v)
	{
		marked[v] = true;
		for(int w : G.adj(v))
			if(!marked[w])
			{
				dfs(G, w);
				edgeTo[w] = v;
			}
	}
	
	public boolean hasPathTo(int v) { return marked[v]; }
	
	public Iterable<Integer> pathTo(int v)
	{
		if(!hasPathTo(v)) return null;
		Stack<Integer> path = new Stack<Integer>();
		while(v != s)
		{
			path.push(v);
			v = edgeTo[v];
		}
		path.push(s);
		return path;
	}
}
