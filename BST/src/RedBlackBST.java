
public class RedBlackBST<Key extends Comparable<Key>, Value> extends
		OST<Key, Value> 
{
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	private class Node
	{
		private Node(Key key, Value val, int N, boolean color)
		{
			this.key = key;
			this.val = val;
			this.N = N;
			this.color = color;
		}
		private Key key;
		private Value val;
		private Node left, right;
		private int N;
		private boolean color;
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

	private boolean isRed(Node node)
	{
		if(node == null)
			return false;
		return node.color == RED;
	}
	
	private Node rotateLeft(Node h)
	{
		assert(!isRed(h.left) && isRed(h.right));
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = size(h.left) + size(h.right) + 1;
		return x;
	}
	
	private Node rotateRight(Node h)
	{
		assert(isRed(h.left) && !isRed(h.right));
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = size(h.left) + size(h.right) + 1;
		return x;
	}
	
	private Node flipColors(Node node)
	{
		assert(!isRed(node) && isRed(node.left) && isRed(node.right));
		node.left.color = BLACK;
		node.right.color = BLACK;
		node.color = RED;
		return node;
	}
	
	private Node put(Key key, Value val, Node h)
	{
		if(h == null)
			return new Node(key, val, 1, RED);
		int cmp = key.compareTo(h.key);
		if(cmp < 0)
			h.left = put(key, val, h.left);
		else if (cmp > 0)
			h.right = put(key, val, h.right);
		else
			h.val = val;
		
		if(isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
		if(isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
		if(isRed(h.left) && isRed(h.right)) h = flipColors(h);
		
		h.N = size(h.left) + size(h.right) + 1;
		
		return h;
	}
	
	@Override
	public void put(Key key, Value val) 
	{
		root = put(key, val, root);
		root.color = BLACK;
	}

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
	
	@Override
	public void delete(Key key) {}

	

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
		
//		for(String word: st.keys())
//		{
//			st.delete(word);
//			StdOut.println(st.size());
//		}
	}

}
