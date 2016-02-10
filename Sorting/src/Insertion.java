
public class Insertion extends Sort
{
	public static void sort(Comparable[] a)
	{
		for(int i = 1; i < a.length; i++)
		{
			for(int j = i; j > 0 && less(a[j], a[j - 1]); j--)
			{
				exch(a, j , j - 1);
			}
		}
	}
	
	public static void main(String[] args)
	{
		int n = Integer.parseInt(args[0]);
		Integer[] nums = new Integer[n];
		for(int i = 0; i < n; i++)
			nums[i] = StdRandom.uniform(0, n);

		Stopwatch timer = new Stopwatch();
		sort(nums);
		StdOut.println(timer.elapsedTime());
		StdOut.println(isSorted(nums));

	}

}
