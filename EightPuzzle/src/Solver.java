
public class Solver 
{
    private MinPQ<Node> pq, twinPq;
    
    private boolean solvable;
    
    private Node min, twinMin;
    
    private class Node implements Comparable<Node>
    {
        private Board board;
        private int num;
        private Node previous;
        private int p;
        
        private Node(Board board, Node previous)
        {
            this.board = board;
            this.previous = previous;
            if (previous == null)
                this.num = 0;
            else
                this.num = previous.num + 1;
            this.p = this.board.manhattan() + this.num;
        }
        
        public int compareTo(Node other)
        {
            if (this.p > other.p)
                return +1;
            if (this.p < other.p)
                return -1;
            return 0;
        }

    }
    
    public Solver(Board initial)
    {        
        pq = new MinPQ<Node>();

        twinPq = new MinPQ<Node>();
        
        Node firstNode = new Node(initial, null);        
        pq.insert(firstNode);
        
        Node twinFirstNode = new Node(initial.twin(), null);
        twinPq.insert(twinFirstNode);
        
        while (true)
        {
            min = pq.delMin();
            if (min.board.isGoal())
            {
                solvable = true;
                break;
            }
            
            for (Board neighbor : min.board.neighbors())
            {
                if (min.previous != null 
                        && neighbor.equals(min.previous.board))
                    continue;
                Node t = new Node(neighbor, min);
                pq.insert(t);
            }
            
            twinMin = twinPq.delMin();
            if (twinMin.board.isGoal())
            {
                solvable = false;
                break;
            }
            for (Board neighbor : twinMin.board.neighbors())
            {
                if (twinMin.previous != null 
                        && neighbor.equals(twinMin.previous.board))
                    continue;
                Node t = new Node(neighbor, twinMin);
                twinPq.insert(t);
            }
        }    
    }
    
    public boolean isSolvable() { return solvable; }
    
    public int moves() 
    {
        if (isSolvable())
            return min.num;
        return -1;
    }
    
    public Iterable<Board> solution() 
    {
        if (isSolvable())
        {
            Stack<Board> stack = new Stack<Board>();
            for (Node n = min; n != null; n = n.previous)
            {
                stack.push(n.board);
            }
            return stack;
        }
        return null;
    }
    
    public static void main(String[] args)
    {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
