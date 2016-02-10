import java.util.HashMap;

public class BaseballElimination 
{
    private final int N;
    private final HashMap<String, Integer> index;
    private final String[] names;
    private final int[] win;
    private final int[] loss;
    private final int[] remaining;
    private final int[][] against;

    private int maxWinTeam;
    public BaseballElimination(String filename)
    {
        In in = new In(filename);
        N = in.readInt();
        index = new HashMap<String, Integer>(N);
        names = new String[N];
        win = new int[N];
        loss = new int[N];
        remaining = new int[N];
        against = new int[N][N];
        for (int i = 0; i < N; i++)
        {
            names[i] = in.readString();
            index.put(names[i], i);
            win[i] = in.readInt();
            loss[i] = in.readInt();
            remaining[i] = in.readInt();
            for (int j = 0; j < N; j++)
                against[i][j] = in.readInt();
        }
        maxWinTeam = 0;
        for (int i = 1; i < N; i++)
        {
            if (win[i] > win[maxWinTeam])
                maxWinTeam = i;
        }
    }
    
    private void verify(String team) 
    {
    	if(index.get(team) == null)
    		throw new IllegalArgumentException();
    }
    
    public int numberOfTeams() { return N; }
    
    public Iterable<String> teams() { return index.keySet(); }
    
    public int wins(String team) {
    	verify(team);
    	return win[index.get(team)]; 
    }
    
    public int losses(String team) {
    	verify(team);
    	return loss[index.get(team)]; 
    }
    
    public int remaining(String team)  {
    	verify(team);
    	return remaining[index.get(team)]; 
    }
    
    public int against(String team1, String team2)  {
    	verify(team1);
    	verify(team2);
    	return against[index.get(team1)][index.get(team2)]; 
    }
    
    private FlowNetwork network(String team)
    {
        int V = (N - 1) * N / 2 + 2;
        FlowNetwork G = new FlowNetwork(V);
        int x = index.get(team);
        int v = 1;
        
        int[][] vertex2 = new int[N][N];
        int[] vertex1 = new int[N];
        
        for (int i = 0; i < N; i++)
        {
            if (i == x) continue;
            for (int j = i + 1; j < N; j++)
            {
                if (j == x) continue;
                vertex2[i][j] = v++;
            }
        }
        for (int i = 0; i < N; i++)
            if (i != x)vertex1[i] = v++;
        assert (v == V - 1);
                
        for (int i = 0; i < N; i++)
        {
            if (i == x) continue;
            for (int j = i + 1; j < N; j++)
            {
                if (j == x) continue;
                G.addEdge(new FlowEdge(0, vertex2[i][j], against[i][j]));
                G.addEdge(new FlowEdge(vertex2[i][j], vertex1[i], Double.POSITIVE_INFINITY));
                G.addEdge(new FlowEdge(vertex2[i][j], vertex1[j], Double.POSITIVE_INFINITY));
            }
            G.addEdge(new FlowEdge(vertex1[i], V - 1, win[x] + remaining[x] - win[i]));
        }
        return G;

    }
    
    private FordFulkerson maxflow(String team)
    {
        FlowNetwork G = network(team);
        return new FordFulkerson(G, 0, G.V() - 1);
    }
    
    private int expectedFlow(String team)
    {
        int res = 0;
        int x = index.get(team);
        for (int i = 0; i < N; i++)
        {
            if (i == x) continue;
            for (int j = i + 1; j < N; j++)
            {
                if (j == x) continue;
                res += against[i][j];
            }
        }
        return res;

    }
    
    public boolean isEliminated(String team)
    {
    	verify(team);
        if (remaining(team) + wins(team) < win[maxWinTeam])     
            return true;
        return expectedFlow(team) != maxflow(team).value();
    }
    
    public Iterable<String> certificateOfElimination(String team)
    {
    	verify(team);
        Queue<String> queue = new Queue<String>();
        if (remaining(team) + wins(team) < win[maxWinTeam])     
        {
            queue.enqueue(names[maxWinTeam]);
            return queue;
        }
        int x = index.get(team);
        FordFulkerson ff = maxflow(team);
        int V = (N - 1) * N / 2 + 2;

        int[] vertex = new int[N - 1];
        for (int i = 0; i < x; i++) 
        	vertex[i] = i;
        for (int i = x; i < N - 1; i++) 
        	vertex[i] = i + 1;
        
        for (int i = V - N; i < V - 1; i++)
        {
            if (ff.inCut(i))
                queue.enqueue(names[vertex[i - V + N]]);
        }
        if (queue.isEmpty()) return null;
        return queue;
    }
    
    public static void main(String[] args) {
        BaseballElimination division = new BaseballElimination(args[0]);
        for (String team : division.teams()) {
            if (division.isEliminated(team)) {
                StdOut.print(team + " is eliminated by the subset R = { ");
                for (String t : division.certificateOfElimination(team)) {
                    StdOut.print(t + " ");
                }
                StdOut.println("}");
            }
            else {
                StdOut.println(team + " is not eliminated");
            }
        }
    }


}
