package com.nemnous.datastructures.interfaces;

public interface Mappable<T1, T2> {
	public int size();
	public boolean isEmpty();
	public T2 get(T1 key);
	public void put(T1 key, T2 val);
	public void delete(T1 key);
	
}
