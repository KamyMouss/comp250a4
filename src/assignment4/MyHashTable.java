package assignment4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class MyHashTable<K,V> implements Iterable<HashPair<K,V>>{
    // num of entries to the table
    private int numEntries;
    // num of buckets 
    private int numBuckets;
    // load factor needed to check for rehashing 
    private static final double MAX_LOAD_FACTOR = 0.75;
    // ArrayList of buckets. Each bucket is a LinkedList of HashPair
    private ArrayList<LinkedList<HashPair<K,V>>> buckets; 
    
    // constructor
    public MyHashTable(int initialCapacity) {
        // ADD YOUR CODE BELOW THIS
        this.buckets = new ArrayList<LinkedList<HashPair<K,V>>>();
        
        // initialize buckets
        for (int i = 0; i < initialCapacity; i++) {
        	this.buckets.add(new LinkedList<HashPair<K, V>>());   
        }
        
        this.numBuckets = initialCapacity;
        this.numEntries = 0;
        //ADD YOUR CODE ABOVE THIS
    }
    
    public int size() {
        return this.numEntries;
    }
    
    public int numBuckets() {
        return this.numBuckets;
    }
    
    /**
     * Returns the buckets vairable. Usefull for testing  purposes.
     */
    public ArrayList<LinkedList< HashPair<K,V> > > getBuckets(){
        return this.buckets;
    }
    /**
     * Given a key, return the bucket position for the key. 
     */
    public int hashFunction(K key) {
        int hashValue = Math.abs(key.hashCode())%this.numBuckets;
        return hashValue;
    }
    /**
     * Takes a key and a value as input and adds the corresponding HashPair
     * to this HashTable. Expected average run time  O(1)
     */
    public V put(K key, V value) {
        //  ADD YOUR CODE BELOW HERE   	
    	int compressedHash = Math.abs(key.hashCode()) % numBuckets;
        
        LinkedList<HashPair<K,V>> bucket = buckets.get(compressedHash);
        
        
//        if (bucket == null) 
//        {
//        	// Bucket is empty, create new bucket
//        	bucket = new LinkedList<HashPair<K, V>>();     	
//        } 
       
    	// Search if key already exists in bucket
    	for (HashPair<K,V> pair: bucket) 
    	{
    		// if key exists, update value and return previous value
    		if (pair.getKey().equals(key)) 
    		{
    			V previousValue = pair.getValue(); 
    			pair.setValue(value);
    			return previousValue;
    		}
    	}
    	
    	// Key is new, add it to bucket
    	bucket.add(new HashPair<K, V>(key,value));
    	this.numEntries++;
    	
    	// rehash if load factor exceeded
    	double currLoadFactor = (double)this.numEntries / (double) this.numBuckets;
    	if (currLoadFactor > this.MAX_LOAD_FACTOR ) 
    	{
    		//System.out.println(currLoadFactor + " rehashed " + this.MAX_LOAD_FACTOR);
    		this.rehash();      	
    		compressedHash = Math.abs(key.hashCode()) % numBuckets;
    	}


    	
        return null;
        //  ADD YOUR CODE ABOVE HERE
    }
    
    
    /**
     * Get the value corresponding to key. Expected average runtime = O(1)
     */
    
    public V get(K key) {
        //ADD YOUR CODE BELOW HERE
    	int compressedHash = Math.abs(key.hashCode()) % numBuckets;
    	
    	LinkedList<HashPair<K,V>> bucket = buckets.get(compressedHash);
    	
    	for (HashPair<K,V> pair: bucket) 
    	{
    		if (pair.getKey().equals(key)) 
    		{
    			return pair.getValue();
    		}
    	}
    	
    	return null;
        //ADD YOUR CODE ABOVE HERE
    }
    
    /**
     * Remove the HashPair correspoinding to key . Expected average runtime O(1) 
     */
    public V remove(K key) {
        //ADD YOUR CODE BELOW HERE
    	int compressedHash = Math.abs(key.hashCode()) % numBuckets;
    	LinkedList<HashPair<K,V>> bucket = buckets.get(compressedHash);
    
    	for (HashPair<K,V> pair: bucket) 
    	{
    		if (pair.getKey().equals(key)) 
    		{
    			bucket.remove(pair);
    			this.numEntries--;
    			return pair.getValue();
    		}
    	}
    	
        return null;
        
        //ADD YOUR CODE ABOVE HERE
    }
    
    // Method to double the size of the hashtable if load factor increases
    // beyond MAX_LOAD_FACTOR.
    // Made public for ease of testing.
    
    public void rehash() {
        //ADD YOUR CODE BELOW HERE
    	
    	// Copy current buckets in temp, and initialize new buckets
    	ArrayList<LinkedList<HashPair<K,V>>> tempBuckets = this.buckets;
    	this.buckets = new ArrayList<LinkedList<HashPair<K,V>>>();
    	
    	this.numBuckets *= 2;
    	this.numEntries = 0;
    	
    	for (int i = 0; i < this.numBuckets; i++) 
    	{	
    		this.buckets.add(new LinkedList<HashPair<K, V>>());   
    	}
    
    	
    	// Rehash every key in temp buckets in new buckets
    	for (LinkedList<HashPair<K,V>> bucket: tempBuckets) 
    	{
    		for (HashPair<K,V> pair: bucket) 
    		{
    			this.put(pair.getKey(), pair.getValue());
    		}
    		 
    	}    	
        
    	
    	//ADD YOUR CODE ABOVE HERE
    }
    
    
    /**
     * Return a list of all the keys present in this hashtable.
     */
    
    public ArrayList<K> keys() {
        //ADD YOUR CODE BELOW HERE
    	 ArrayList<K> keys = new ArrayList<K>();

    	 for (LinkedList<HashPair<K,V>> bucket: this.buckets) 
    	 {
     		for (HashPair<K,V> pair: bucket) 
     		{
     			keys.add(pair.getKey());
     		}
     		 
     	}    	
    	 
    	 return keys;//remove
        
        //ADD YOUR CODE ABOVE HERE
    }
    
    /**
     * Returns an ArrayList of unique values present in this hashtable.
     * Expected average runtime is O(n)
     */
    public ArrayList<V> values() {
        //ADD CODE BELOW HERE
    	
    	MyHashTable <V,K> valueHash = new MyHashTable<>(this.numBuckets);
    	ArrayList<V> values = new ArrayList<>();
    	
    	for (HashPair pair : this) {
    		valueHash.put((V)pair.getValue(), (K)pair.getKey());
    	}
    	
    	for(HashPair pair : valueHash) {
    		values.add((V)pair.getKey());
    	}
    	
    	return values;
    	
//    	ArrayList<V> values = new ArrayList<V>();
//    	
//    	
//	   	for (LinkedList<HashPair<K,V>> bucket: this.buckets) 
//	   	{
//	    	for (HashPair<K,V> pair: bucket) 
//	    	{
//	    			V value = pair.getValue();
//	    			// make sure they are unique
//	    			// TODO make sure this check is needed, i think the values in the table are already unique?
//	    			if (!values.contains(value)) values.add(value);
//	    				values.add(value);
//	    	}	 
//	    }    	
//	   	 
//	   	 return values;
        //ADD CODE ABOVE HERE
    }
    
    
	    @Override
	    public MyHashIterator iterator() {
	    	return new MyHashIterator();
	    }
	
	
	    private class MyHashIterator implements Iterator<HashPair<K,V>> {
	    private LinkedList<HashPair<K,V>> pairs;
	
	    private MyHashIterator() {
	    	//ADD YOUR CODE BELOW HERE
		    pairs = new LinkedList<HashPair<K,V>>();
		    for(LinkedList<HashPair<K,V>> bucket : buckets){
			    for(HashPair<K,V> pair : bucket){
			    	
			    	this.pairs.add(pair);
			    }
		    }
		    //ADD YOUR CODE ABOVE HERE
	    }
	
	    @Override
	    public boolean hasNext() {
		    //ADD YOUR CODE BELOW HERE
		    return (this.pairs.size() >= 1);
		    //ADD YOUR CODE ABOVE HERE
	    }
	
	    @Override
	    public HashPair<K,V> next() {
		    //ADD YOUR CODE BELOW HERE
		    return this.pairs.remove();
		    //ADD YOUR CODE ABOVE HERE
	    }
	
	    }
	        
    
}
