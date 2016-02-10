public final class SequentialSearchST<Key, Value> extends ST<Key, Value>
{
	private class Node
	{
		Key key;
		Value val;
		Node next;
		public Node(Key key, Value val, Node next)
		{
			this.key = key;
			this.val = val;
			this.next = next;
		}
	}
	
	private Node first;
	private int N = 0;
	
	public SequentialSearchST() {}
	public void put(Key key, Value val)
	{
		for(Node node = first; node != null; node = node.next)
		{
			if(node.key.equals(key)) 
			{
				node.val = val;
				return;
			}
		}
		first = new Node(key, val, first);
		N++;
	}
	
	public Value get(Key key)
	{
		for(Node node = first; node != null; node = node.next)
		{
			if(node.key.equals(key))
				return node.val;
		}
		return null;
	}
	
	public int size() { return N; }
	
	public Iterable<Key> keys()
	{
		Queue<Key> queue = new Queue<Key>();
		for(Node node = first; node != null; node = node.next)
		{
			queue.enqueue(node.key);
		}
		return queue;
	}
	
	public void delete(Key key)
	{
		if(first == null)
			return;
		if(first.key.equals(key))
			first = first.next;
		for(Node node = first.next, previous = first; node != null; node = node.next)
		{
			if(node.key.equals(key))
			{
				previous.next = node.next;
				return;
			}
			previous = node;
		}
	}
	
	public static void main(String[] args)
	{
		int minlen = Integer.parseInt(args[0]);
		SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
		while(!StdIn.isEmpty())
		{
			String word = StdIn.readString();
			if(word.length() < minlen)
				continue;
			if(!st.contains(word))
				st.put(word, 1);
			else
				st.put(word, st.get(word) + 1);
		}
		
		String max = "";
		st.put(max, 0);
		for(String word : st.keys())
		{
			if(st.get(word) > st.get(max))
				max = word;
		}
		
		StdOut.println(max + " " + st.get(max));
	}
}
