package com.nemnous.datastructures.interfaces;


public interface Stackable<T> {
	public void push(T item);
	public T pop();
	public boolean isEmpty();
	public int size();	
}
