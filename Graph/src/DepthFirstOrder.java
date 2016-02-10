
public class DepthFirstOrder 
{
	private boolean[] marked;
	private Stack<Integer> reversePost;
	
	public DepthFirstOrder(Diagraph G) 
	{
		marked = new boolean[G.V()];
		reversePost = new Stack<Integer>();
		for(int v = 0; v < G.V(); v++)
			if(!marked[v])
				dfs(G, v);
	}
	
	void dfs(Diagraph G, int v)
	{
		marked[v] = true;
		for(int w : G.adj(v))
			if(!marked[w])
				dfs(G, w);
		reversePost.push(v);
	}
	
	public Iterable<Integer> reversePost() { return reversePost; }
}
