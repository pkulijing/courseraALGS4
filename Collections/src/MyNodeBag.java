import java.util.Iterator;

public class MyNodeBag<Item> implements Iterable<Item> 
{
	private int N;
	private Node first;
	private class Node
	{
		Item item;
		Node next;
	}
	
	public void add(Item item)
	{
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		N++;
	}
	
	public boolean isEmpty() { return N == 0;}
	
	public int size() { return N;}
	
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
		public void remove() {}
	}
	public static void main(String[] args)
	{
		int n = Integer.parseInt(args[0]);
		MyNodeBag<Integer> ints = new MyNodeBag<Integer>();
		for(int i = 0; i < n; i++)
			ints.add(i);
		for(Integer i:ints)
		{
			StdOut.printf("%d ", i);
		}
		StdOut.println();
		StdOut.printf("Number of items left in bag: %d.\n",ints.size());
	}


}
