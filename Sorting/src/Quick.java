
public class Quick extends Sort {
	public static void sort(Comparable[] a)
	{
		StdRandom.shuffle(a);
		sort(a, 0, a.length - 1);
	}
	
	private static void sort(Comparable[] a, int lo, int hi)
	{
		if(lo >= hi)
			return;
		int mid = partition(a, lo, hi);
		sort(a, lo, mid - 1);
		sort(a, mid + 1, hi);
	}
	
	private static int partition(Comparable[] a, int lo, int hi)
	{
		Comparable v = a[lo];
		int i = lo, j = hi + 1;
		while(true)
		{
			while(less(a[++i], v)) if(i == hi) break;
			while(less(v, a[--j])) if(j == lo) break;
			if(i >= j ) break;
			exch(a, i, j);
		}
		exch(a, lo, j);
		return j;
	}
	
	public static void main(String[] args)
	{
		Integer[] test = new Integer[10];
		for(int i = 0; i < test.length; i++)
			test[i] = StdRandom.uniform(100);
		sort(test);
		if(!isSorted(test))
		{
			show(test);
			throw new IllegalStateException("Array not sorted.");	
		}

		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		Double[] nums = new Double[N];
		
		double total = 0.0;
		for(int t = 0; t < T; t++)
		{
			for(int i = 0; i < N; i++)
			nums[i] = StdRandom.uniform();
			Stopwatch timer = new Stopwatch();
			sort(nums);
			total += timer.elapsedTime();
		}
		StdOut.printf("%d tests of size %d took %f s,\n", T, N, total);
	}


}
