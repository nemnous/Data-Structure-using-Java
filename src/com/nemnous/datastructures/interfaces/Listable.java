package com.nemnous.datastructures.interfaces;

public interface Listable<T> {
	public T get(int index);
	public void add(T item);
	public T remove(int index);
	public int size();
}
