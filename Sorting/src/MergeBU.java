
public class MergeBU extends Sort 
{
	private static Comparable[] aux;

	private static void merge(Comparable[] a, int lo, int mid, int hi)
	{	
		for(int k = lo; k <= hi; k++)
			aux[k] = a[k];
		
		int i = lo, j = mid + 1;
		for(int k = lo; k <= hi; k++)
		{
			if(i > mid)
				a[k] = aux[j++];
			else if(j > hi)
				a[k] = aux[i++];
			else if (less(aux[j], aux[i]))
				a[k] = aux[j++];
			else
				a[k] = aux[i++];
		}
	}
	
	public static void sort(Comparable[] a)
	{
		int n = a.length;

		aux = (Comparable[]) new Comparable[n];
		for(int i = 1; i < n; i *= 2)
		{
			for(int j = i; j < n; j += 2 * i)
			{
				merge(a, j - i, j - 1, Math.min(j + i - 1, n - 1));
			}
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
