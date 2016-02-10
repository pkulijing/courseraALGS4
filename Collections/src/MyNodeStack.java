import java.util.Iterator;
public class MyNodeStack<Item> implements Iterable<Item>
{
	private class Node
	{
		Item item;
		Node next;
	}
	private int N = 0;
	private Node first;
	
	public boolean isEmpty() { return N == 0; }
	
	public int size() { return N; }
	
	public void push(Item item)
	{
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		N++;
	}
	
	public Item pop()
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
		MyNodeStack<Integer> ints = new MyNodeStack<Integer>();
		for(int i = 0; i < n; i++)
			ints.push(i);
		for(int i = 0; i < n; i++)
			StdOut.printf("%d ",ints.pop());
		StdOut.println();
		
		StdOut.printf("Number of items left in stack: %d.\n",ints.size());
		
		for(int i = 0; i < n; i++)
			ints.push(i);
		for(int i = 0; i < n; i++)
			StdOut.printf("%d ",ints.pop());
		StdOut.println();
	}
}
