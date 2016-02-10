
public abstract class Sort 
{
	public static boolean less(Comparable a,Comparable b)
	{
		return a.compareTo(b) < 0;
	}
	
	public static void exch(Comparable[] a, int i, int j)
	{
		Comparable swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	
	public static boolean isSorted(Comparable[] a) 
	{
		for(int i = 1; i < a.length; i++)
			if(less(a[i], a[i - 1]))
				return false;
		return true;
	}
	
	public static void show(Comparable[] a)
	{
		for(int i = 0; i < a.length; i++)
		{
			StdOut.print(a[i]);
			StdOut.print(' ');
		}
		StdOut.println();
	}
	
	public static void main(String[] args)
	{
		StdOut.println("This is main() of Sort, which should never be called.");
	}
}
