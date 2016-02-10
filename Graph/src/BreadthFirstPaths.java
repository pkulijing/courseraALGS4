
public final class BreadthFirstPaths extends Paths 
{
	private boolean[] marked;
	private int[] edgeTo;
	private int[] distTo;
	private int s;
	
	public BreadthFirstPaths(Graph G, int s) 
	{
		this.marked = new boolean[G.V()];
		this.edgeTo = new int[G.V()];
		this.distTo = new int[G.V()];
		this.s = s;
		Queue<Integer> q = new Queue<Integer>();
		q.enqueue(s);
		marked[s] = true;
		distTo[s] = 0;
		while(!q.isEmpty())
		{
			int v = q.dequeue();
			for(int w : G.adj(v)) 
			{
				if(!marked[w])
				{
					q.enqueue(w);
					edgeTo[w] = v;
					marked[w] = true;
					distTo[w] = distTo[v] + 1;
				}
			}
		}
	}
	
	@Override
	public boolean hasPathTo(int v) { return marked[v]; }

	@Override
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
