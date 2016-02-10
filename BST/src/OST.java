
public abstract class OST<Key extends Comparable<Key>, Value> extends ST<Key, Value>
{
	public abstract Key min();
	public abstract Key max();
	
	public abstract Key floor(Key key);
	public abstract Key ceiling(Key key);
	
	public abstract int rank(Key key);
	
	public abstract Key select(int k);
	
	public abstract Iterable<Key> keys(Key lo, Key hi);

	
	public void deleteMin() { delete(min()); }
	
	public void deleteMax() { delete(max()); }
	
	public int size(Key lo, Key hi)
	{
		if(hi.compareTo(lo) < 0)
			return 0;
		if(contains(hi))
			return rank(hi) - rank(lo) + 1;
		return rank(hi) - rank(lo);
	}
	
	public Iterable<Key> keys() { return keys(min(), max()); }
}
