
public class BetterInsertion extends Sort {
	
	public static void sort(Comparable[] a)
	{
		int n = a.length;		
		for(int i = 1; i < n; i++)
		{
			Comparable v = a[i];
			int j = i - 1;
			while(j >= 0 && less(v,a[j])) {
				a[j + 1] = a[j];
				j--;
			}	
			a[j + 1] = v;

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
