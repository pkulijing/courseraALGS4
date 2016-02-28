import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
public class QuickUnion {
	
	private int[] id;
	private int count;
	
	public int count() { return count;}
	
	public QuickUnion(int n)
	{
		count = n;
		id = new int[n];
		for(int i = 0; i < n; i++)
			id[i] = i;
	}
	
	public boolean connected(int p, int q) { return find(p) == find(q);}

	public int find(int p) 
	{
		while(id[p] != p)
			p = id[p];
		return p;
	}
	
	
	public void union(int p, int q)
	{	
		int rootP = find(p);
		int rootQ = find(q);
		if(rootP == rootQ)
			return;
		
		id[rootP] = rootQ;
		count--;
	}
	
	public static void main(String[] args)
	{
		int n = StdIn.readInt();
		QuickUnion qu = new QuickUnion(n);
		while(!StdIn.isEmpty())
		{
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if(qu.connected(p, q))
				continue;
			
			qu.union(p, q);
			StdOut.println(p + " " + q);
		}
		StdOut.println(qu.count() + " components");
	}

}
