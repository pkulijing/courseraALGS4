public class Selection extends Sort
{
	public static void sort(Comparable[] a)
	{
		for (int i = 0; i < a.length; i++)
		{
			int min = i;
			for (int j = i + 1; j < a.length; j++)
			{
				if(less(a[j], a[min]))
					min = j;
			}
			exch(a, i, min);
		}
	}
	
	public static void main(String[] args)
	{
		int n = Integer.parseInt(args[0]);
		Integer[] nums = new Integer[n];
		for(int i = 0; i < n; i++)
			nums[i] = StdRandom.uniform(0, 10 * n);

		Stopwatch timer = new Stopwatch();
		sort(nums);
		StdOut.println(timer.elapsedTime());
	}

}
