package com.nemnous.datastructures.interfaces;

/**
 * Interface for Stack.
 * @author nemnous
 *
 * @param <T> generic
 */
public interface Stackable<T> {
	public void push(T item);
	public T pop();
	public boolean isEmpty();
	public int size();	
}
