package com.nemnous.datastructures;

import java.util.LinkedList;
import java.util.Queue;

import com.nemnous.datastructures.exceptions.InvalidArgumentException;
import com.nemnous.datastructures.interfaces.Mappable;

/**
 *  Using Linear Probing method to avoid the collision.
 *  In linear probing the index is generated to the key using hash function
 *  if the index is empty the value is assigned in the same index.
 *  if not the successive index + 1, index + 2, .... is looked into and sets at
 *  empty index.
 *  @author nemnous.
 */
public class HashMap<T1, T2> implements Mappable<T1, T2>{
    private static final int INIT_CAPACITY = 4;

    private int size; // number of key-value pairs in the hash table
    private int tableSize; // size of hash table
    private T1[] keys;
    private T2[] vals;


    /**
     * Initializes an empty hash table.
     */
    public HashMap() {
        this(INIT_CAPACITY);
    }

    /**
     * Initializes an empty hash table with the specified initial capacity.
     * used when user want to initialize to specific capacity.
     * or when resizing.
     * @param 
     */
    @SuppressWarnings("unchecked")
    public HashMap(int capacity) {
        tableSize = capacity;
        size = 0;
        keys = (T1[])   new Object[tableSize];
        vals = (T2[]) new Object[tableSize];
    }

    /**
     * Returns the number of key-value pairs in this hash table.
     *
     * @return 
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns true if this hash table is empty.
     *
     * @return 
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns true if this hash table contains the specified key.
     *
     * @param  
     * @return 
     * @throws InvalidArgumentException if key is null
     */
    @Override
    public boolean contains(T1 key) {
        if (key == null) {
        	throw new InvalidArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }

    // hash function for keys - returns value between 0 and M-1
    /**
     * Converting a hashCode() to an array index.
     * Since our goal is an array index, not a 32-bit integer, The code masks off the sign bit
     * to turn the 32-bit integer into a 31-bit nonnegative integer
     * and then computing the remainder when dividing by tableSize, as in modular hashing.
     * @param key
     * @return
     */
    private int hash(T1 key) {
        return (key.hashCode() & 0x7fffffff) % tableSize;
    }

    // resizes the hash table to the given capacity by re-hashing all of the keys
    private void resize(int capacity) {
        HashMap<T1, T2> hashtable = new HashMap<>(capacity);
        for (int index = 0; index < tableSize; index++) {
            if (keys[index] != null) {
            	hashtable.put(keys[index], vals[index]);
            }
        }
        keys = hashtable.keys;
        vals = hashtable.vals;
        tableSize    = hashtable.tableSize;
    }

    /**
     * Inserts the specified key-value pair into the hash table, overwriting the old 
     * value with the new value if the hash table already contains the specified key.
     * Deletes the specified key (and its associated value) from this hash table
     * if the specified value is null.
     *
     * @param  key the key
     * @param  value the value
     * @throws InvalidArgumentException if key is null
     */
    @Override
    public void put(T1 key, T2 value) {
        if (key == null) {
        	throw new InvalidArgumentException("first argument to put() is null");
        }

        if (value == null) {
            delete(key);
            return;
        }

        // double table size if 50% full
        if (size >= tableSize/2) {
        	resize(2*tableSize);
        }

        int index;
        for (index = hash(key); keys[index] != null; index = (index + 1) % tableSize) {
            if (keys[index].equals(key)) {
                vals[index] = value;
                return;
            }
        }
        keys[index] = key;
        vals[index] = value;
        size++;
    }

    /**
     * Returns the value associated with the specified key.
     * @param key the key
     * @return the value associated with key;
     *         null if no such value
     * @throws InvalidArgumentException if key is null
     */
    @Override
    public T2 get(T1 key) {
        if (key == null) {
        	throw new InvalidArgumentException("argument to get() is null");
        }
        
        for (int index = hash(key); keys[index] != null; index = (index + 1) % tableSize) {
            if (keys[index].equals(key)) {
                return vals[index];	
            }
        }
        return null;
    }

    /**
     * Removes the specified key and its associated value from this hash table     
     * (if the key is in this hash table).    
     *
     * @param  key the key
     * @throws InvalidArgumentException if key is null
     */
    @Override
    public void delete(T1 key) {
        if (key == null) {
        	throw new InvalidArgumentException("argument to delete() is null");
        }
        
        if (!contains(key)) {
        	return;
        }

        // find position index of key
        int index = hash(key);
        while (!key.equals(keys[index])) {
            index = (index + 1) % tableSize;
        }
        // delete key and associated value
        keys[index] = null;
        vals[index] = null;

        // rehash all keys
        index = (index + 1) % tableSize;
        while (keys[index] != null) {
            // delete keys[index] an vals[index] and reinsert
            T1   keyToRehash = keys[index];
            T2 valToRehash = vals[index];
            keys[index] = null;
            vals[index] = null;
            size--;
            put(keyToRehash, valToRehash);
            index = (index + 1) % tableSize;
        }

        size--;

        // halves size of array if it reaches tableSize/8.
        if (size > 0 && size <= tableSize/8) {
        	resize(tableSize/2);
        }

    }

    /**
     * Returns all keys in this hash table as an Iterable.
     * To iterate over all of the keys in the hash table.
     *
     * @return all keys in this hash table
     */
    @Override
    public Iterable<T1> keys() {
        Queue<T1> queue = new LinkedList<>();
        for (int index = 0; index < tableSize; index++)
            if (keys[index] != null) {
            	queue.add(keys[index]);
            }
        return queue;
    }

}
