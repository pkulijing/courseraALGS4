//Connected components
public class CC 
{
	private boolean[] marked;
	private int[] id;
	private int count;

	public CC(Graph G) 
	{
		marked = new boolean[G.V()];
		id = new int[G.V()];
		count = 0;
		for(int v = 0; v < G.V(); v++)
		{
			if(!marked[v])
			{
				id[v] = count++;
				dfs(G, v);
			}
		}
	}
	
	private void dfs(Graph G, int v)
	{
		marked[v] = true;
		for(int w : G.adj(v))
		{
			if(!marked[w])
			{
				id[w] = id[v];
				dfs(G, w);
			}
		}
	}
	
	public int count() { return count; }
	
	public int id(int v) { return id[v]; }
}
