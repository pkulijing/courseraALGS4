import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Percolation 
{
    private int edge;
    private boolean[][] grid;
    private WeightedQuickUnionUF uf, ufnoend;
    
    public Percolation(int N)
    {
        if (N <= 0)
            throw new IllegalArgumentException("Edge length N should be positive.");
        edge = N;
        grid = new boolean[N][N];
        ufnoend = new WeightedQuickUnionUF(N * N + 1);
        uf = new WeightedQuickUnionUF(N * N + 2);
    }
    
    private int index(int i, int j) { return (i - 1) * edge + j; }
    
    private void  outOfBoundTest(int i)
    {
        if (i <= 0 || i > edge)
            throw new IndexOutOfBoundsException("Index " + i + " is out of bound.");
    }
    
    public void open(int i, int j)
    {
        outOfBoundTest(i);
        outOfBoundTest(j);
        grid[i - 1][j - 1] = true;
        if (i == 1)
        {
            uf.union(0, j);
            ufnoend.union(0, j);
        }
        if (i == edge)
        {
            uf.union(index(i, j), edge * edge + 1);
        }
        if (i > 1 && isOpen(i - 1, j))
        {
            uf.union(index(i, j), index(i - 1, j));
            ufnoend.union(index(i, j), index(i - 1, j));
        }
        if (i < edge && isOpen(i + 1, j))
        {
            uf.union(index(i, j), index(i + 1, j));
            ufnoend.union(index(i, j), index(i + 1, j));
        }
        if (j > 1 && isOpen(i, j - 1))
        {
            uf.union(index(i, j), index(i, j - 1));
            ufnoend.union(index(i, j), index(i, j - 1));
        }
        if (j < edge && isOpen(i, j + 1))
        {
            uf.union(index(i, j), index(i, j + 1));
            ufnoend.union(index(i, j), index(i, j + 1));
        }
    }
    
    public boolean isOpen(int i, int j)
    {
        outOfBoundTest(i);
        outOfBoundTest(j);
        return grid[i - 1][j - 1]; 
    }
    
    public boolean isFull(int i, int j)
    {
        outOfBoundTest(i);
        outOfBoundTest(j);
        return ufnoend.connected(0, index(i, j));
    }
    
    public boolean percolates() 
    {
        return uf.connected(0, edge * edge + 1); 
    }
    
    public static void main(String[] args)
    {
        In in = new In(args[0]);
        int N = in.readInt();
        Percolation p = new Percolation(N);
        while (!in.isEmpty())
        {
            int i = in.readInt();
            int j = in.readInt();
            p.open(i, j);
        }
        StdOut.println(p.percolates());    
    }
}
