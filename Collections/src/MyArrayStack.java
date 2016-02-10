import java.util.Iterator;

public class MyArrayStack<Item> implements Iterable<Item>
{
	private int N = 0;
	private Item[] a = (Item[]) new Object[1];
	
	private void resize (int newsize)
	{
		//StdOut.printf("Resize from %d to %d\n", a.length, newsize);
		Item[] temp = (Item[]) new Object[newsize];
		for(int i = 0; i < N; i++)
		{
			temp[i] = a[i];
		}
		a = temp;
	}

	public Iterator<Item> iterator()
	{
		return new ReverseArrayIterator();
	}
	
	private class ReverseArrayIterator implements Iterator<Item>
	{
		private int i = N;
		public boolean hasNext() { return i > 0; }
		public Item next() {return a[--i];}
		public void remove() {}
	}
	public boolean isEmpty() { return N == 0; }
	public int size() { return N; }
	
	public void push(Item item)
	{
		if (N == a.length)
			resize(2 * N);
		a[N++] = item; 
	}
	
	public Item pop()
	{
		Item item = a[--N];
		a[N] = null;
		
		if(N > 0 && N == a.length / 4) 
			resize(a.length / 2);
		return item;
	}	
	
	public static void main(String[] args)
	{
		int n = Integer.parseInt(args[0]);
		MyArrayStack<Integer> ints = new MyArrayStack<Integer>();
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
