package com.nemnous.datastructures.interfaces;


/**
 * Interface for ArrayList.
 * @author nemnous
 *
 * @param <T> generic
 */
public interface Listable<T> {
	public T get(int index);
	public void add(T item);
	public T remove(int index);
	public int size();
}
