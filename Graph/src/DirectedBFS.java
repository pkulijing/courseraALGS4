
public class DirectedBFS 
{
	private boolean[] marked;
	private int[] edgeTo;
	
	//single source
	public DirectedBFS(Diagraph G, int s) 
	{
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		Queue<Integer> queue = new Queue<Integer>();
		queue.enqueue(s);
		while(!queue.isEmpty())
		{
			int v = queue.dequeue();
			for(int w : G.adj(v))
			if(!marked[w])
			{
				queue.enqueue(w);
				marked[w] = true;
				edgeTo[w] = v;
			}
		}
	}
	
	//multiple source
	public DirectedBFS(Diagraph G, Iterable<Integer> s)
	{
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		Queue<Integer> queue = new Queue<Integer>();
		for(int v : s)
			queue.enqueue(v);
		while(!queue.isEmpty())
		{
			int v = queue.dequeue();
			for(int w : G.adj(v))
			if(!marked[w])
			{
				queue.enqueue(w);
				marked[w] = true;
				edgeTo[w] = v;
			}
		}
	}
}
