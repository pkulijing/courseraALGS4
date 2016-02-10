
public class SortCompare {
	
	public static double time(String algs,Double[] a)
	{
		Stopwatch timer = new Stopwatch();
		
		if(algs.equals("Insertion"))
			Insertion.sort(a);
		else if(algs.equals("BetterInsertion"))
			BetterInsertion.sort(a);
		else if(algs.equals("Selection"))
			Selection.sort(a);
		else if(algs.equals("Shell"))
			Shell.sort(a);
		else if(algs.equals("Merge"))
			Merge.sort(a);
		else if(algs.equals("MergeBU"))
			MergeBU.sort(a);
		else if(algs.equals("Quick")) 
			Quick.sort(a);
		else if(algs.equals("Heap"))
			Heap.sort(a);
		else
			throw new IllegalArgumentException("No such sorting method: " + algs);
		return timer.elapsedTime();
	}
	
	public static double timeRandomInput(String algs, int N, int T)
	{
		double total = 0.0;
		Double[] a = new Double[N];
		for(int i = 0; i < T; i++)
		{
			for(int j = 0; j < N; j++)
			{
				a[j] = StdRandom.uniform();
			}
			total += time(algs, a);
		}
		return total;
	}
	
	public static void main(String[] args)
	{
		String alg1 = args[0];
		String alg2 = args[1];
		int N = Integer.parseInt(args[2]);
		int T = Integer.parseInt(args[3]);
		StdOut.printf("%s took %f s for %d tests of size %d.\n", alg1, 
				timeRandomInput(alg1, N, T), T, N);
		StdOut.printf("%s took %f s for %d tests of size %d.\n", alg2, 
				timeRandomInput(alg2, N, T), T, N);
	}

}
