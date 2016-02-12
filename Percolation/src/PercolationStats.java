import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats 
{
    private int n;
    private int t;
    private double[] threshold;
    
    public PercolationStats(int N, int T)
    {
        if (N <= 0 || T <= 0)
            throw new IllegalArgumentException("N and T should be positve.");
        n = N;
        t = T;
        threshold = new double[T];
        for (int k = 0; k < T; k++)
        {
            Percolation percolation = new Percolation(N);
            int num = 0;
            while (!percolation.percolates())
            {
                int i, j;
                do
                {
                    i = StdRandom.uniform(N) + 1;
                    j = StdRandom.uniform(N) + 1;
                }
                while(percolation.isOpen(i, j));
                percolation.open(i, j);
                num++;
            }
            threshold[k] = (double) num / (double) (n * n);
        }        
    }
    
    public double mean()  { return StdStats.mean(threshold); }
    
    public double stddev() 
    {
        if (t == 1)
            return Double.NaN;
        return StdStats.stddev(threshold); 
    }
    
    public double confidenceLo()
    {
        return mean() - 1.96 * stddev() / Math.sqrt((double) t); 
    }

    public double confidenceHi()
    { 
        return mean() + 1.96 * stddev() / Math.sqrt((double) t);
    }
    
    public static void main(String[] args)
    {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(N, T);
        StdOut.printf("mean = %.16f\n", stats.mean());
        StdOut.printf("stddev = %.16f\n", stats.stddev());
        StdOut.printf("95%% confidence interval = %.16f, %.16f\n",
                stats.confidenceLo(), stats.confidenceHi());
    }

}
