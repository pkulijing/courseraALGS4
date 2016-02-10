
//Knuth 3n + 1
public class Shell extends Sort 
{
	public static void sort(Comparable[] a)
	{
		int h = 1;
		int n = a.length;
		
		while(h < n / 3)
			h = h * 3 + 1;
		
		while(h >= 1)
		{
			for (int i = h; i < n; i++)
			{
				for (int j = i; j >= h && less(a[j], a[j - h]); j -= h)
				{
					exch(a, j, j - h);
				}
			}
			h /= 3;
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
