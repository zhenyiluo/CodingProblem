import java.util.*;
public class LRUCache {
	static int capacity;

	LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true){
	@Override
	protected boolean removeEldestEntry(Map.Entry<Integer, Integer> entry){
	return size() > capacity;
}
};
    
    	public LRUCache(int capacity) {
        		this.capacity = capacity;
    	}
    
    	public int get(int key) {
        		if(!cache.containsKey(key)){
	return -1;
}
return cache.get(key);
    	}
    
    	public void set(int key, int value) {
        		cache.put(key, value);
    	}
}