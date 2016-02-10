
public abstract class ST<Key, Value> 
{
	public abstract void put(Key key, Value val);
	
	public abstract Value get(Key key);
	
	public abstract int size();
	
	public abstract Iterable<Key> keys();
	
	
	
	public void delete(Key key) { put(key, null); }
	
	public boolean contains(Key key) { return get(key) != null; }

	public boolean isEmpty() { return size() == 0; }
	
}
