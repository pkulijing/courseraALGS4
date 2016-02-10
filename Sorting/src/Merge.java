public class Merge extends Sort 
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
	
	private static void sort(Comparable[] a, int lo, int hi)
	{
		//size of subarray small, use insertion sort
		//This "optimization" harms the performance here.
//		if(hi - lo < 10)
//		{
//			for(int i = lo + 1; i <= hi; i++)
//			{
//				for(int j = i; j > lo; j--)
//				{
//					if(less(a[j], a[j - 1]))
//						exch(a, j, j - 1);
//				}
//			}
//			return;
//		}
		
		//size of subarray large
		if(lo >= hi)
			return;
		
		int mid = lo + (hi - lo) / 2;
		sort(a, lo, mid);
		sort(a, mid + 1, hi);
		//Avoid merge when already sorted.
		//This "optimization" harms the performance here.
//		if(less(a[mid + 1], a[mid]))  
			merge(a, lo, mid, hi);
	}
	
	public static void sort(Comparable[] a)
	{
		aux = (Comparable[]) new Comparable[a.length];
		sort(a, 0, a.length - 1);
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
