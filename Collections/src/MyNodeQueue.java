import java.util.Iterator;
public class MyNodeQueue<Item> implements Iterable<Item> 
{
	private class Node
	{
		Item item;
		Node next;
	}
	
	private int N;
	private Node first = null;
	private Node last = null;

	public int size() { return N; }
	public boolean isEmpty() { return N == 0;}
	
	public void enqueue(Item item)
	{
		Node oldlast = last;
		last = new Node();
		last.item = item;
		
		if(isEmpty())
			first = last;
		else 
			oldlast.next = last;
		N++;
	}
	
	public Item dequeue()
	{
		Item item = first.item;
		first = first.next;
		N--;
		return item;
	}
	
	public Iterator<Item> iterator()
	{
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item>
	{
		Node current = first;
		public boolean hasNext() { return current != null; }
		public Item next() 
		{ 
			Item item = current.item;
			current = current.next;
			return item;
		}
		public void remove(){}
	}
	
	public static void main(String[] args)
	{
		int n = Integer.parseInt(args[0]);
		MyNodeQueue<Integer> ints = new MyNodeQueue<Integer>();
		for(int i = 0; i < n; i++)
			ints.enqueue(i);
		for(int i = 0; i < n; i++)
			StdOut.printf("%d ",ints.dequeue());
		StdOut.println();
		
		StdOut.printf("Number of items left in queue: %d.\n",ints.size());
		
		for(int i = 0; i < n; i++)
			ints.enqueue(i);
		for(int i = 0; i < n; i++)
			StdOut.printf("%d ",ints.dequeue());
		StdOut.println();
	}
}
