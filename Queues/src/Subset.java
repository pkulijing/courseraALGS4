
public class Subset {
    
    public static void main(String[] args) 
    {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> strings = new RandomizedQueue<String>();
        while (!StdIn.isEmpty())
        {
            strings.enqueue(StdIn.readString());
        }
        for (int i = 0; i < k; i++)
        {
            StdOut.println(strings.dequeue());
        }
    }

}
