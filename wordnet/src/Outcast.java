
public class Outcast {
    private WordNet net;
    
    public Outcast(WordNet net)
    {
        this.net = net;
    }
    
    private int sumDis(String[] nouns, int i)
    {
        int d = 0;
        for (int j = 0; j < nouns.length; j++)
        {
            if (j == i)
                continue;
            d += net.distance(nouns[i], nouns[j]);
        }
        return d;
    }
    public String outcast(String[] nouns)
    {
        int d = sumDis(nouns, 0), index = 0;
        for (int i = 1; i < nouns.length; i++)
        {
            int di = sumDis(nouns, i);
            if (di > d)
            {
                d = di;
                index = i;
            }
        }
        return nouns[index];
    }
    
    public static void main(String[] args)
    {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }

}
