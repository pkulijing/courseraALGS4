import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> 
{
    private int N;
    private Item[] items;
    
    public RandomizedQueue()
    {
        N = 0;
        items = (Item[]) new Object[1];
    }
    
    private void resize(int newsize)
    {
        Item[] temp = (Item[]) new Object[newsize];
        for (int i = 0; i < N; i++)
        {
            temp[i] = items[i];
        }
        items = temp;
    }

    public Iterator<Item> iterator()
    {
        return new ArrayRandomIterator();
    }
    
    private class ArrayRandomIterator implements Iterator<Item>
    {
        private int current;
        private int[] index;
        public ArrayRandomIterator() 
        {
            current = 0;
            index = new int[N];
            for (int i = 0; i < N; i++)
                index[i] = i;
            StdRandom.shuffle(index);
        }
        public boolean hasNext() { return current != N; }
        public Item next() 
        {
            if (!hasNext())
                throw new NoSuchElementException("No more next.");
            return items[index[current++]];
        }
        public void remove() {
            throw new UnsupportedOperationException("Cannot remove from iterator.");
        }
    }
    public boolean isEmpty() { return N == 0; }
    public int size() { return N; }
    
    public void enqueue(Item item)
    {
        if (item == null)
            throw new NullPointerException("Cannot enqueue a null item.");
        if (N == items.length)
            resize(2 * size());
        items[N++] = item;
    }
    
    public Item dequeue()
    {
        if (isEmpty())
            throw new NoSuchElementException("Cannot dequeue from an empty queue.");
        int i = StdRandom.uniform(N);
        Item temp = items[i];
        items[i] = items[N - 1];
        items[--N] = null;
        if (N > 0 && N == items.length / 4)
            resize(items.length / 2);
        return temp;
    }
    public Item sample()
    {
        if (isEmpty())
            throw new NoSuchElementException("Cannot sample from an empty queue.");
        return items[StdRandom.uniform(N)];
    }

    public static void main(String[] args)
    {
        RandomizedQueue<Integer> ints = new RandomizedQueue<Integer>();
        for (int i = 0; i < 10; i++)
            ints.enqueue(i);
        
        for (int i:ints)
            StdOut.print(i);
        StdOut.println();    
        
        for (int i:ints)
            StdOut.print(i);
        StdOut.println();
            
    }

}
