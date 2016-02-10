
public class Board 
{
    private int N;
    private int[][] blocks;
    private int i0, j0;
      
    public Board(int[][] blocks)
    {
        N = blocks.length;
        this.blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
            {
                if (blocks[i][j] == 0)
                {
                    this.i0 = i;
                    this.j0 = j;
                }
                this.blocks[i][j] = blocks[i][j];
            }
    }
    
    private boolean contains(int i, int j) 
    {
        return i >= 0 && j >= 0 && i < N && j < N; 
    }
    
    public int dimension() { return N; }
    
    //number at i, j in the goal board.
    private int goalAtPos(int i, int j)
    {
        if (i == N - 1 && j == N - 1)
            return 0;
        return i * N + j + 1;
    }
    
    private int iInGoal(int n) 
    {
        if (n == 0)
            return N - 1;
        return (n - 1) / N;
    }
    
    private int jInGoal(int n)
    {
        if (n == 0)
            return N - 1;
        return (n - 1) % N;
    }
    
    public int hamming() 
    {
        int result = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (blocks[i][j] != 0 && blocks[i][j] != goalAtPos(i, j))
                    result++;
        return result;    
    }
    
    public int manhattan()
    {
        int result = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (blocks[i][j] != 0)
                {
                    result += (Math.abs(iInGoal(blocks[i][j]) - i) 
                            + Math.abs(jInGoal(blocks[i][j]) - j));
                }
        return result;
    }
    
    public boolean isGoal() 
    {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (blocks[i][j] != goalAtPos(i, j))
                    return false;
        return true;
    }
    
    public Board twin()
    {
        if (i0 == 0)
            return exchanged(N - 1, N - 2, N - 1, N - 1);
        return exchanged(0, 0, 0, 1);
    }

    public boolean equals(Object o)
    {
        if (o == null)
            return false;
        if (o == this)
            return true;
        if (o.getClass() != this.getClass())
            return false;
        Board other = (Board) o;
        if (other.N != this.N)
            return false;
        for (int i = 0; i < this.N; i++)
            for (int j = 0; j < this.N; j++)
                if (other.blocks[i][j] != this.blocks[i][j])
                    return false;
        return true;
    }
        
    private Board exchanged(int i1, int j1, int i2, int j2)
    {
        Board board = new Board(this.blocks);
        board.blocks[i1][j1] = this.blocks[i2][j2];
        board.blocks[i2][j2] = this.blocks[i1][j1];
        if (i1 == i0 && j1 == j0)
        {
            board.i0 = i2;
            board.j0 = j2;
        }
        if (i2 == i0 && j2 == j0)
        {
            board.i0 = i1;
            board.j0 = j1;
        }        
        return board;
    }
    public Iterable<Board> neighbors()
    {
        Queue<Board> queue = new Queue<Board>();
        if (contains(i0 - 1, j0))
        {
            queue.enqueue(exchanged(i0, j0, i0 - 1, j0));    
        }
        if (contains(i0 + 1, j0))
        {
            queue.enqueue(exchanged(i0, j0, i0 + 1, j0));    
        }
        if (contains(i0, j0 - 1))
        {
            queue.enqueue(exchanged(i0, j0, i0, j0 - 1));    
        }
        if (contains(i0, j0 + 1))
        {
            queue.enqueue(exchanged(i0, j0, i0, j0 + 1));    
        }
        return queue;
    }
    
    public String toString()
    {
        String s = N + "\n";
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
                s = s + " " + blocks[i][j];
            s = s + "\n";
        }
        return s;
    }
    
    public static void main(String[] args)
    {
        
    }
}
