
public class MSDStringSort 
{
	private static int R = 256;
	private static int M = 15; // small array cutoff.
	public static void sort(String[] a)
	{
		String[] aux = new String[a.length];
		sort(a, aux, 0, a.length - 1, 0);
	}
	
	public static void sort(String[] a, String[] aux, int lo, int hi, int d)
	{
		if(hi <= lo + M)
		{
			Insertion.sort(a, lo, hi, d);
		}
		int[] count = new int[R + 2];
		for(int i = lo; i <= hi; i++)
			count[charAt(a[i], d) + 2]++;
		//count[i] (i > 0): number of characters equal to i - 2
		
		for(int r = 0; r < R + 1; r++)
			count[r + 1] += count[r];
		//count[i] (i > 0): number of characters <= i - 2, thus index of the first i - 1
		
		for(int i = lo; i <= hi; i++)
			aux[count[charAt(a[i], d) + 1]++] = a[i];
		
		for(int i = lo; i <= hi; i++)
			a[i] = aux[i - lo];
		
		for(int r = 0; r < R; r++)
			sort(a, aux, lo + count[r], lo + count[r + 1] - 1, d + 1);
		
	}
	
	public static void main(String[] a)
	{
		
	}
	
	private static int charAt(String s, int d)
	{
		if(d < s.length()) return s.charAt(d);
		return -1;
	}

}
