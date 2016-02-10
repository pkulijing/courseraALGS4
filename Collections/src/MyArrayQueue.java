import java.util.Iterator;

public class MyArrayQueue<Item> implements Iterable<Item> 
{
	private int begin = 0;
	private int end = 0;
	private Item[] a = (Item[]) new Object[1];
	
	private void resize (int newsize)
	{
		StdOut.printf("Resize from %d to %d\n", a.length, newsize);
		Item[] temp = (Item[]) new Object[newsize];
		for(int i = begin; i < end; i++)
		{
			temp[i - begin] = a[i];
		}
		a = temp;
		end = size();
		begin = 0;
	}

	public Iterator<Item> iterator()
	{
		return new ArrayIterator();
	}
	
	private class ArrayIterator implements Iterator<Item>
	{
		private int i = begin;
		public boolean hasNext() { return i != end; }
		public Item next() {return a[--i];}
		public void remove() {}
	}
	public boolean isEmpty() { return begin == end; }
	public int size() { return end - begin; }
	
	public void enqueue(Item item)
	{
		if(isEmpty())
			resize(1);
		else if(end == a.length)
			resize(2 * size());
		a[end++] = item;
	}
	
	public Item dequeue()
	{
		Item item = a[begin];
		a[begin++] = null;
		if(size() > 0 && size() == a.length / 4)
			resize(a.length / 2);
		return item;
	}
	public static void main(String[] args)
	{
		int n = Integer.parseInt(args[0]);
		MyArrayQueue<Integer> ints = new MyArrayQueue<Integer>();
		for(int i = 0; i < n; i++)
			ints.enqueue(i);
		for(int i = 0; i < n; i++)
			StdOut.printf("%d ",ints.dequeue());
		StdOut.println();
		
		StdOut.printf("Number of items left in queue: %d.\n",ints.size());
		for(int i = 0; i < n; i++)
			ints.enqueue(i);
	}


}
