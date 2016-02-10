
public final class BST<Key extends Comparable<Key>, Value> extends
		OST<Key, Value> 
{
	private class Node
	{
		private Key key;
		private Value val;
		private Node left, right;
		private int N;
		
		public Node(Key key, Value val, int N)
		{
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}
	
	private Node root;

	private Node min(Node node)
	{
		if(node.left == null)
			return node;
		return min(node.left);
	}
	
	@Override
	public Key min() { return min(root).key; }

	private Node max(Node node)
	{
		if(node.right == null)
			return node;
		return max(node.right);
	}
	
	@Override
	public Key max() { return max(root).key; }

	private Node floor(Key key, Node node)
	{
		if(node == null)
			return null;
		int cmp = key.compareTo(node.key);
		if(cmp < 0)
			return floor(key, node.left);
		if(cmp > 0)
		{
			Node t = floor(key, node.right);
			if(t != null)
				return t;
		}
		return node;
	}
	
	@Override
	public Key floor(Key key) { return floor(key, root).key; }

	private Node ceiling(Key key, Node node)
	{
		if(node == null)
			return null;
		int cmp = key.compareTo(node.key);
		if(cmp < 0)
		{
			Node t = ceiling(key, node.left);
			if(t != null)
				return t;
		}
		if(cmp > 0)
			return ceiling(key, node.right);
		return node;
	}
	
	@Override
	public Key ceiling(Key key) { return ceiling(key, root).key; }

	private int rank(Key key, Node node)
	{
		if(node == null)
			return 0;
		int cmp = key.compareTo(node.key);
		if(cmp < 0)
			return rank(key,node.left);
		if(cmp > 0)
			return rank(key, node.right) + size(node.left) + 1;
		return size(node.left);
	}
	
	@Override
	public int rank(Key key) { return rank(key, root); }

	private Node select(int k, Node node)
	{
		if(node == null)
			return null;
		int lsz = size(node.left);
		if(lsz > k)
			return select(k, node.left);
		if(lsz < k)
			return select(k - lsz - 1, node.right);
		return node;
	}
	
	@Override
	public Key select(int k) { return select(k, root).key; }

	private void keys(Queue<Key> queue, Node node, Key lo, Key hi)
	{
		if(node == null)
			return;
		int cmplo = node.key.compareTo(lo), cmphi = node.key.compareTo(hi);
		if(cmplo > 0)
			keys(queue, node.left, lo, hi);
		if(cmplo >= 0 && cmphi <= 0)
			queue.enqueue(node.key);
		if(cmphi < 0)
			keys(queue, node.right, lo, hi);
	}
	
	@Override
	public Iterable<Key> keys(Key lo, Key hi) 
	{
		Queue<Key> queue = new Queue<Key>();
		keys(queue, root, lo, hi);
		return queue;
	}

	private Node put(Key key, Value val, Node node)
	{
		if(node == null)
			return new Node(key, val, 1);
		int cmp = key.compareTo(node.key);
		if(cmp > 0)
			node.right = put(key, val, node.right);
		else if(cmp < 0)
			node.left = put(key, val, node.left);
		else
			node.val = val;
		node.N = size(node.left) + size(node.right) + 1;
		return node;
	}
	
	@Override
	public void put(Key key, Value val) { root = put(key, val, root); }

	private Value get(Key key, Node node)
	{
		if(node == null)
			return null;
		int cmp = node.key.compareTo(key);
		if(cmp > 0)
			return get(key,node.left);
		if(cmp < 0)
			return get(key,node.right);
		return node.val;	
	}
	
	@Override
	public Value get(Key key) { return get(key, root); }

	private int size(Node node)
	{
		if(node == null)
			return 0;
		return node.N;
	}
	
	@Override
	public int size() {	return size(root); }
	
	private Node delete(Key key, Node node)
	{
		if(node == null)
			return null;
		int cmp = key.compareTo(node.key);
		if(cmp > 0)
			node.right = delete(key, node.right);
		else if(cmp < 0)
			node.left = delete(key, node.left);
		else
		{
			if(node.left == null)
				return node.right;
			else if(node.right == null)
				return node.left;
			else
			{
				Node rmin = min(node.right);
				node.key = rmin.key;
				node.val = rmin.val;
				node.right = delete(rmin.key, node.right);
			}	
		}
		node.N = size(node.left) + size(node.right) + 1;
		return node;
	}
	
	@Override
	public void delete(Key key) { delete(key, root); }

	private Node deleteMin(Node node)
	{
		if(node == null)
			return null;
		if(node.left == null)
			return node.right;
		node.left = deleteMin(node.left);
		node.N = size(node.left) + size(node.right) + 1;
		return node;
	}
	
	@Override
	public void deleteMin() { deleteMin(root); }
	
	private Node deleteMax(Node node)
	{
		if(node == null)
			return null;
		if(node.right == null)
			return node.left;
		node.right = deleteMax(node.right);
		node.N = size(node.left) + size(node.right) + 1;
		return node;
	}
	
	@Override
	public void deleteMax() { deleteMax(root); }
	
	public static void main(String[] args)
	{
		int minlen = Integer.parseInt(args[0]);
		BST<String, Integer> st = new BST<String, Integer>();
		while(!StdIn.isEmpty())
		{
			String word = StdIn.readString();
			if(word.length() < minlen)
				continue;
			if(!st.contains(word))
			{
				st.put(word, 1);
			}
			else
			{
				st.put(word, st.get(word) + 1);
			}
		}

		String max = "";
		st.put(max, 0);
		for(String word : st.keys())
		{
			if(st.get(word) > st.get(max))
				max = word;
		}
		
		StdOut.println(max + " " + st.get(max));
		
		for(String word: st.keys())
		{
			st.delete(word);
			StdOut.println(st.size());
		}
	}


}
