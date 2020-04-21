package com.nemnous.datastructures;

import java.util.Arrays;

import com.nemnous.datastructures.exceptions.IndexOutOfBoundsException;
 
public class ArrayList<T> {
 
    private T[] array;
    private int size = 0;
     
    /**
     * Constructor to initialize the Array List.
     */
    @SuppressWarnings("unchecked")
	public ArrayList(){
        this.array = (T[]) new Object[10];
    }
    
    /**
     * Get method to Array List
     * @param index - of the element you want.
     * @return element present at the index
     */
    public T get(int index){
    	if(index > size) {
    		throw new IndexOutOfBoundsException("Index out of Bounds");
    	}
    	return array[index];
    }
     
    /**
     * Adds the item given to the array.
     * @param item
     */
    public void add(T item){
        if(size > array.length/2){
            resize();
        }
        array[size++] = item;
    }
    
    /**
     * removes the item present at the index of array.
     * @param index
     * @return
     */
    public T remove(int index){
    	if(index > size) {
    		throw new IndexOutOfBoundsException("Index out of Bounds");
    	}
    	
        T item = array[index];
        array[index] = null;
        for( int i = index; i < size; i++) {
        	array[i] = array[i + 1];
            array[i + 1] = null;
        }
        size--;
        return item;
         
    }
    /**
     * returns the size of array
     * @return integer,  size of array.
     */
    public int size(){
        return size;
    }
    
    /**
     * resizes array to twice the size of existing size of array.
     */
    private void resize(){
        array = Arrays.copyOf(array, array.length*2);
    }

}