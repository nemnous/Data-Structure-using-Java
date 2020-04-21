package com.nemnous.datastructures; 
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.nemnous.datastructures.exceptions.StackUnderflowException;
import com.nemnous.datastructures.interfaces.Stackable;


/**
 * 
 *  @author nemnous.
 *  @author 
 *
 *  @param <T> the generic type of an item in this stack
 */
public class Stack<T> implements Iterable<T>, Stackable<T>{
    private Node<T> first;     
    private int size;

    // helper Node class
    private static class Node<T> {
        private T item;
        private Node<T> next;
    }

    /**
     * Initializes an empty stack.
     */
    public Stack() {
        first = null;
        size = 0;
    }

    /**
     * Returns true if this stack is empty.
     *
     * @return true if this stack is empty; false otherwise
     */
    @Override
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of items in this stack.
     *
     * @return 
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Adds the item to this stack.
     *
     * @param 
     */
    @Override
    public void push(T item) {
        Node<T> oldfirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldfirst;
        size++;
    }

    /**
     * Removes and returns the item most recently added to this stack.
     *
     * @return the item most recently added
     * @throws StackUnderflowException if this stack is empty
     */
    @Override
    public T pop() {
        if (isEmpty()) throw new StackUnderflowException("Stack underflow");
        T item = first.item;        // save item to return
        first = first.next;            // delete first node
        size--;
        return item;                   // return the saved item
    }


    /**
     * Returns a string representation of this stack.
     *
     * @return elements of stack separated by space.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (T item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }
       

    /**
     * Returns an iterator to this stack that iterates through the items in LIFO order.
     *
     * @return 
     */
    public Iterator<T> iterator() {
        return new StackIterator(first);
    }

    // an iterator, doesn't implement remove() since it's optional
    private class StackIterator implements Iterator<T> {
        private Node<T> current;

        public StackIterator(Node<T> first) {
            current = first;
        }
        
        @Override
        public boolean hasNext() {
            return current != null;
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
        
        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T item = current.item;
            current = current.next; 
            return item;
        }
    }
}


