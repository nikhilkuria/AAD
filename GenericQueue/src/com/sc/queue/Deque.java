package com.sc.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item>
{
    private class Node<Item>
    {
        Node(Item item){
            this.item = item;
        }
        Node previousNode;
        Node nextNode;
        Item item;
    }
    
    private class DequeIterator implements Iterator<Item>{

        @Override
        public boolean hasNext()
        {
            return startNode.nextNode != null;
        }

        @Override
        public Item next()
        {
            if(hasNext()){
                return (Item) startNode.nextNode.item;
            }else{
                throw new UnsupportedOperationException();
            }
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
        
    }
    
    private Node startNode;
    private Node endNode;
    private int dequeLength;

    public Deque()
    {
    }

    public boolean isEmpty()
    {
        return dequeLength==0? true : false;
    }

    public int size()
    {
        return dequeLength;
    }

    public void addFirst(Item item)
    {
        validateItem(item);
        Node node = new Node<Item>(item);
        node.nextNode = startNode;
        startNode = node;
    }

    public void addLast(Item item)
    {
        validateItem(item);
        Node node = new Node<Item>(item);
        endNode.nextNode = node;
        endNode = node;
    }

    public Item removeFirst()
    {
        //Should revisit this cast
        Item itemToReturn = (Item)startNode.item;
        startNode = startNode.nextNode;
        return itemToReturn;
    }

    public Item removeLast()
    {
        Item itemToRetuen = (Item)endNode.item;
        endNode = endNode.previousNode;
        return itemToRetuen;
    }

    public Iterator<Item> iterator()
    {
        return new DequeIterator();
    }

    private void validateItem(Item item)
    {
        if (item == null) {
            throw new NoSuchElementException(
                    "No place for foul creatures like you");
        }

    }

    public static void main(String[] args)
    {
        Deque<Integer> deque = new Deque<>();
        
    }
    
}
