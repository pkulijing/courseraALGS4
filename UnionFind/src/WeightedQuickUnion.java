import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
public class WeightedQuickUnion {
	
	private int[] id;
	private int[] size;
	private int count;
	
	public int count() { return count;}
	
	public WeightedQuickUnion(int n) {
		count = n;
		id = new int[n];
		size = new int[n];
		for(int i = 0; i < n; i++) {
			id[i] = i;
			size[i] = 1;
		}
	}
	
	public boolean connected(int p, int q) { return find(p) == find(q); }

	public int find(int p)  {
		while(id[p] != p)
			p = id[p];
		return p;
	}
	
	
	public void union(int p, int q) {	
		int rootp = find(p);
		int rootq = find(q);
		
		if(rootp == rootq) return;
		
		if(size[rootp] < size[rootq])
		{
			id[rootp] = rootq;
			size[rootq] += size[rootp];
		}
		else 
		{
			id[rootq] = rootp;
			size[rootp] += size[rootq];
		}
		count--;
	}
	
	public static void main(String[] args)
	{
		int n = StdIn.readInt();
		WeightedQuickUnion wqu = new WeightedQuickUnion(n);
		while(!StdIn.isEmpty())
		{
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if(wqu.connected(p, q))
				continue;
			
			wqu.union(p, q);
			StdOut.println(p + " " + q);
		}
		StdOut.println(wqu.count() + " components");
	}

}
