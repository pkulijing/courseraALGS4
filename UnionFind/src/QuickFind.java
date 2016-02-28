import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
public class QuickFind {
	
	private int[] id;
	private int count;
	
	public int count() { return count;}
	
	public QuickFind(int n)
	{
		count = n;
		id = new int[n];
		for(int i = 0; i < n; i++)
			id[i] = i;
	}
	
	public int find(int p) { return id[p];}
	
	public boolean connected(int p, int q) { return find(p) == find(q);}
	
	public void union(int p, int q)
	{	
		int pid = find(p);
		int qid = find(q);
		
		if(pid == qid)
			return;
		
		for(int i = 0; i < id.length; i++)
		{
			if(id[i] == pid) 
				id[i] = qid;
		}
		count--;
	}
	
	public static void main(String[] args)
	{
		int n = StdIn.readInt();
		QuickFind qf = new QuickFind(n);
		while(!StdIn.isEmpty())
		{
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if(qf.connected(p, q))
				continue;
			
			qf.union(p, q);
			StdOut.println(p + " " + q);
		}
		StdOut.println(qf.count() + " components");
	}

}
