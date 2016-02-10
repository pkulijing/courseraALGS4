
public class LSDStringSort 
{
	public static void sort(String[] a, int W)
	{
		int n = a.length;
		int R = 256;
		String[] aux = new String[n];
		for(int d = W - 1; d >= 0; d--)
		{
			int[] count = new int[R + 1];
			for(int j = 0; j < n; j++)
				count[a[j].charAt(d) + 1]++;
			//count[i] (i > 0): number of characters equal to i - 1
			
			for(int j = 0; j < R; j++)
				count[j + 1] += count[j];
			//count[i] (i > 0): number of characters <= i - 1, thus index of the first i
			
			for(int j = 0; j < n; j++)
				aux[count[a[j].charAt(d)]++] = a[j];
			
			for(int j = 0; j < n; j++)
				a[j] = aux[j];
		}
	}

}
