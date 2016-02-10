
//If we want the heap to start from 0, then children of i are 2 * i + 1
//and 1 * i + 2, parent of i is thus (i - 1) / 2
public class Heap extends Sort {
	//N is length (a[0] to a[N - 1]
	private static void sink(Comparable[] a, int i, int N)
	{
		while(2 * i + 1 <= N - 1)
		{
			int j = 2 * i + 1;
			if(j < N - 1 && less(a[j], a[j + 1]))
				j++;
			if(!less(a[i], a[j]))
				break;
			exch(a, i, j);
			i = j;
		}
	}
	public static void sort(Comparable[] a)
	{
		int N = a.length;
		for(int i = (N - 2) / 2; i >= 0; i--)
		{
			sink(a, i, N);
		}
		while(N >= 1)
		{
			exch(a, 0, N-- - 1);
			sink(a, 0, N);
		}
	}
	
	public static void main(String[] args)
	{		
		Integer[] test = new Integer[1000];
		for(int i = 0; i < test.length; i++)
			test[i] = StdRandom.uniform(10000);
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
