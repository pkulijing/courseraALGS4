import java.util.Iterator;

import javax.print.attribute.Size2DSyntax;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

public class MyArrayBag<Item> implements Iterable<Item>
{
	private int N;
	private Item[] items = (Item[]) new Object[1];
	
	public boolean isEmpty() { return N == 0; }
	
	public int size() { return N; }
	
	public void add(Item item)
	{
		if(N == items.length)
			resize(2 * N);
		items[N++] = item;
	}
	
	private void resize (int newsize)
	{
		//StdOut.printf("Resize from %d to %d\n", a.length, newsize);
		Item[] temp = (Item[]) new Object[newsize];
		for(int i = 0; i < N; i++)
		{
			temp[i] = items[i];
		}
		items = temp;
	}

	public Iterator<Item> iterator()
	{
		return new ArrayIterator();
	}
	
	private class ArrayIterator implements Iterator<Item>
	{
		private int i = 0;
		public boolean hasNext() { return i != N; }
		public Item next() {return items[i++];}
		public void remove() {}
	}
	public static void main(String[] args)
	{
		int n = Integer.parseInt(args[0]);
		MyArrayBag<Integer> ints = new MyArrayBag<Integer>();
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
