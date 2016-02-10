public final class BinarySearchOST<Key extends Comparable<Key>, Value> extends
		OST<Key, Value> 
{
	private Key[] keys;
	private Value[] vals;
	private int N = 0;
	
	public BinarySearchOST() 
	{
		keys = (Key[]) new Comparable[1];
		vals = (Value[]) new Object[1];
	}	
	
	public BinarySearchOST(int capacity) 
	{
		keys = (Key[]) new Comparable[capacity];
		vals = (Value[]) new Object[capacity];
	}

	private void resize(int n)
	{
		assert(n >= N);

		Key[] tkeys = (Key[]) new Comparable[n];
		Value[] tvals = (Value[]) new Object[n];
		for(int i = 0; i < N; i++)
		{
			tkeys[i] = keys[i];
			tvals[i] = vals[i];
		}
		keys = tkeys;
		vals = tvals;
	}
	
	@Override
	public Key min() { return keys[0]; }

	@Override
	public Key max() { return keys[N - 1]; }

	@Override
	public Key floor(Key key) 
	{
		int i = rank(key);
		if(i < N && keys[i].compareTo(key) == 0)
			return key;
		return keys[i - 1];
	}

	@Override
	public Key ceiling(Key key) {
		int i = rank(key);
		return keys[i];
	}

	@Override
	//Core logic: if the argument is not in the array keys, lo always ends up at the first
	//key larger than the argument.
	public int rank(Key key) 
	{
		int lo = 0, hi = N - 1;
		while(lo <= hi)
		{
			int mid = lo + (hi - lo) / 2;
			int cmp = keys[mid].compareTo(key);
			if(cmp == 0)
				return mid;
			else if(cmp < 0)
				lo = mid + 1;
			else
				hi = mid - 1;
		}
		return lo;
	}

	@Override
	public Key select(int k) { return keys[k]; }

	@Override
	public Iterable<Key> keys(Key lo, Key hi) 
	{
		int il = rank(lo), ih = rank(hi);
		Queue<Key> queue = new Queue<Key>();
		for(int i = il; i < ih; i++)
			queue.enqueue(keys[i]);
		if(ih < N && keys[ih].compareTo(hi) == 0)
			queue.enqueue(hi);
		return queue;
	}

	@Override
	public void put(Key key, Value val) 
	{
		int i = rank(key);
		if(i < N && keys[i].compareTo(key) == 0)
			vals[i] = val;
		else
		{
			if(N == keys.length)
				resize(2 * N);
			for(int j = N - 1; j >= i; j--)
			{
				vals[j + 1] = vals[j];
				keys[j + 1] = keys[j];
			}
			vals[i] = val;
			keys[i] = key;
			N++;
		}
	}

	@Override
	public Value get(Key key) 
	{
		if(isEmpty())
			return null;
		int i = rank(key);
		if(i < N && keys[i].compareTo(key) == 0)
			return vals[i];
		return null;
	}

	@Override
	public int size() {	return N; }
	
	@Override
	public void delete(Key key)
	{
		if(isEmpty())
			return;
		int i = rank(key);
		if(i < N && keys[i].compareTo(key) == 0)
		{
			if(N > 0 && N == keys.length / 4)
				resize(keys.length / 2);
			for(int j = i; j < N - 1; j++)
			{
				vals[j] = vals[j + 1];
				keys[j] = keys[j + 1];
			}
			vals[N - 1] = null;
			keys[N - 1] = null;
			N--;
		}
	}
	
	public static void main(String[] args)
	{
		int minlen = Integer.parseInt(args[0]);
		BinarySearchOST<String, Integer> st = new BinarySearchOST<String, Integer>();
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
