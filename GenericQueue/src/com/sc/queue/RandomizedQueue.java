package com.sc.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.princeton.stdlib.StdRandom;
import com.princeton.stdlib.Stopwatch;

public class RandomizedQueue<Item> implements Iterable<Item>
{

    private final int ARRAY_SIZE_FACTOR = 20;

    private Item[] queue;
    private int size;
    private int head;

    public RandomizedQueue()
    {
        queue = (Item[]) new Object[ARRAY_SIZE_FACTOR];
    }

    public boolean isEmpty()
    {
        return size == 0 ? true : false;

    }

    public int size()
    {
        return size;

    }

    public void enqueue(Item item)
    {
        validateItem(item);
        if (head == queue.length) {
            resize(queue.length * 2);
        }
        queue[head] = item;
        head++;
        size++;
    }

    public Item dequeue()
    {
        if (size() < 1) {
            throw new NoSuchElementException();
        }
   /*     if (size < queue.length / 4) {
            resize(queue.length / 2);
        }*/
        int randomIndex = getRandomNumber();
        size--;
        Item randomItem = queue[randomIndex];
        while(randomItem==null){
            randomIndex = getRandomNumber();
            randomItem = queue[randomIndex];
        }
        removeItem(randomIndex);
        // System.out.println("Removing element from index :"+randomIndex);

        return randomItem;

    }

    public Item sample()
    {
        if (size() < 1) {
            throw new NoSuchElementException();
        }
        int randomIndex = getRandomNumber();
        Item randomItem = queue[randomIndex];
        return randomItem;

    }

    public Iterator<Item> iterator()
    {
        return new RandomQueueIterator();

    }

    public static void main(String[] args)
    {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        Stopwatch watch = new Stopwatch();
        queue.enqueue(100001);
        queue.dequeue();
        queue.enqueue(1);
        queue.dequeue();


		System.out.println(watch.elapsedTime());
    }

    private void resize(int length)
    {
        // System.out.println("Resizing Array : "+queue.length+" to "+length);
        Item[] tempQueue = (Item[]) new Object[length];
        for (int index = 0; index < size(); index++) {
            tempQueue[index] = queue[index];
        }
        queue = tempQueue;
    }

    private int getRandomNumber()
    {
        return StdRandom.uniform(0, queue.length);
    }

    private void removeItem(int removeIndex)
    {
/*       for (int index = removeIndex; index < size(); index++) {
            Item newValue = queue[index + 1];
            if (newValue == null) {
                return;
            }
            queue[index] = newValue;
        }
        queue[size()] = null;*/
        queue[removeIndex] = null;
    }

    private void validateItem(Item item)
    {
        if (item == null) {
            throw new NullPointerException(
                    "No place for foul creatures like you");
        }

    }

    private class RandomQueueIterator implements Iterator<Item>
    {

        int size;
        private int[] usedIndices;

        public RandomQueueIterator()
        {
            size = size();
            usedIndices = new int[queue.length];
            for (int i = 0; i < size; i++) {
                usedIndices[i] = -1;
            }
        }

        public boolean hasNext()
        {
            return size == 0 ? false : true;
        }

        public Item next()
        {
            if(hasNext()){
                int index = getRandomNumber();
                Item item = queue[index];
                while (item == null) {
                    index = getRandomNumber();
                    item = queue[index];
                }
                usedIndices[index] = index;
                size--;
                return item;
            }        else {
                throw new NoSuchElementException();
            }
           
            
        }

        private int getRandomNumber()
        {
            int randomIndex = StdRandom.uniform(0, queue.length);
            while (usedIndices[randomIndex] == randomIndex) {
                randomIndex = StdRandom.uniform(0, queue.length);
            }
            return randomIndex;
        }

        public void remove()
        {
            throw new UnsupportedOperationException();

        }

    }
}
