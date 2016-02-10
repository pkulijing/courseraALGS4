
public class ResizableHeapPQ<Key extends Comparable<Key>>
{	
	private int N = 0;
	private Key[] pq = (Key[]) new Comparable[2];
	public ResizableHeapPQ() {}
	
	public ResizableHeapPQ(int maxN)
	{
		pq = (Key[]) new Comparable[maxN + 1];
	}
	
	public ResizableHeapPQ(Key[] a)
	{
		for(int i = 0; i < a.length; i++)
		{
			insert(a[i]);
		}
	}
	
	public void insert(Key v)
	{
		if(N == 0)
			resize(1);
		if(N == capacity())
			resize(2 * N);
		pq[++N] = v;
		swim(N);
	}
	
	public Key max() { return pq[1]; }
	
	public Key delMax()
	{
		Key temp = pq[1];
		exch(1, N--);
		sink(1);
		pq[N + 1] = null;
		if(N > 0 && N == capacity() / 4)
			resize(capacity() / 2);
		return temp;
	}
	
	boolean isEmpty() { return N == 0; }
	
	public int size() { return N; }
	
	public int capacity() { return pq.length - 1; }
	
	private boolean less(int i, int j)
	{	
		return pq[i].compareTo(pq[j]) < 0; 
	}
	
	private void exch(int i, int j)
	{
		Key temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
	}
	
	private void swim(int k)
	{		
		while(k > 1 && less(k / 2, k))
		{
			exch(k, k / 2);
			k /= 2;
		}
	}
	
	private void sink(int k)
	{
		while(2 * k <= N)
		{
			int j = 2 * k;
			if (j < N && less(j, j + 1))
				j++;
			if(!less(k, j))
				break;
			exch(k, j);
			k = j;
		}
	}
	
	//Make capacity() equal to sz
	private void resize(int sz)
	{
		assert(sz >= N);
		Key[] pqnew = (Key[]) new Comparable[sz + 1];
		for(int i = 1; i <= N; i++)
		{
			pqnew[i] = pq[i];
		}
		pq = pqnew;
	}
	
	public static void main(String[] args)
	{
		int N = Integer.parseInt(args[0]);
		Integer[] a = new Integer[N];
		for(int i = 0; i < N; i++)
		{
			a[i] = StdRandom.uniform(10 * N);
		}
		ResizableHeapPQ<Integer> intPq = new ResizableHeapPQ<Integer>(a);
		while(intPq.size()!= 0)
		{
			StdOut.printf("%d ", intPq.delMax());
		}
		
	}

}
