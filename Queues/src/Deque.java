import java.util.Iterator;
import java.util.NoSuchElementException;
public class Deque<Item> implements Iterable<Item> 
{
    private int N;
    private Node first;
    private Node last;
    
    private class Node
    {
        Item item;
        Node previous;
        Node next;
    }
    public Deque()
    {
        N = 0;
        first = null;
        last = null;
    }
    
    public boolean isEmpty() { return N == 0; }
    public int size() { return N; }
    
    public void addFirst(Item item)
    {
        if (item == null)
            throw new NullPointerException("Cannot add an null item.");
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        if (isEmpty())
            last = first;
        else 
            oldfirst.previous = first;
        N++;
    }
    
    public void addLast(Item item)
    {
        if (item == null)
            throw new NullPointerException("Cannot add an null item.");
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.previous = oldLast;
        if (isEmpty())
            first = last;
        else
            oldLast.next = last;
        N++;
    }
    
    public Item removeFirst()
    {
        if (isEmpty())
            throw new NoSuchElementException("Cannot remove from an empty deque.");
        Item item = first.item;
        first = first.next;
        N--;
        if (isEmpty())
            last = null;
        else
            first.previous = null;
        return item;
    }
    
    public Item removeLast()
    {
        if (isEmpty())
            throw new NoSuchElementException("Cannot remove from an empty deque.");
        Item item = last.item;
        last = last.previous;
        N--;
        if (isEmpty())
            first = null;
        else
            last.next = null;
        return item;
    }
    
    public Iterator<Item> iterator()
    {
        return new DoubleListIterator();
    }
    private class DoubleListIterator implements Iterator<Item>
    {
        private Node current = first;
        public boolean hasNext() { return current != null; }
        public Item next() 
        {
            if (!hasNext())
                throw new NoSuchElementException("No more next.");
            Item item = current.item;
            current = current.next;
            return item; 
        }
        public void remove() 
        {
            throw new UnsupportedOperationException("Cannot remove an iterator."); 
        }
    }
    
    public static void main(String[] args)
    {
        Deque<Integer> ints = new Deque<Integer>();
        for (int i = 0; i < 5; i++) 
            ints.addFirst(i);
        for (int i = 5; i < 10; i++) 
            ints.addLast(i);
        for (int i:ints) 
            StdOut.printf("%d ", i);
        StdOut.println();
        
        for (int i = 0; i < 3; i++) 
            StdOut.printf("%d ", ints.removeFirst());        
        StdOut.println();

        for (int i = 0; i < 3; i++) 
            StdOut.printf("%d ", ints.removeLast());
        StdOut.println();
    }
}
