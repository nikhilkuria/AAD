package com.sc.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private Node startNode;
	private Node endNode;
	private int dequeLength;

	public Deque() {
	}

	public boolean isEmpty() {
		return dequeLength == 0 ? true : false;
	}

	public int size() {
		return dequeLength;
	}

	public void addFirst(Item item) {
		validateItem(null);
		Node node = new Node<Item>(item);
		if (startNode == null) {
			sanitizeDeque(node);
		} else {
			node.nextNode = startNode;
			startNode.previousNode = node;
			startNode = node;
		}
		dequeLength++;
	}

	public void addLast(Item item) {
		validateItem(item);
		Node node = new Node<>(item);
		if (endNode == null) {
			sanitizeDeque(node);
		} else {
			node.previousNode = endNode;
			endNode.nextNode = node;
			endNode = node;
		}
		dequeLength++;
	}

	public Item removeFirst() {
		if (size() < 1) {
			throw new NoSuchElementException();
		}
		// Should revisit this cast
		Item itemToReturn = (Item) startNode.item;
		startNode = startNode.nextNode;
		startNode.previousNode = null;
		dequeLength--;
		return itemToReturn;
	}

	public Item removeLast() {
		if (size() < 1) {
			throw new NoSuchElementException();
		}
		Item itemToRetuen = (Item) endNode.item;
		endNode = endNode.previousNode;
		endNode.nextNode = null;
		dequeLength--;
		return itemToRetuen;
	}

	public Iterator<Item> iterator() {
		return new DequeIterator();
	}

	private void sanitizeDeque(Node node) {
		if (startNode == null) {
			startNode = node;
			endNode = node;
			startNode.nextNode = endNode;
			endNode.previousNode = startNode;
		}

	}

	private void validateItem(Item item) {
		if (item == null) {
			throw new NoSuchElementException(
					"No place for foul creatures like you");
		}

	}

	public static void main(String[] args) {
		Deque<Integer> deque = new Deque<>();
		deque.addFirst(1);
		deque.addFirst(2);
		deque.addLast(99);
		deque.removeFirst();
		deque.removeLast();
		for (Integer val : deque) {
			System.out.println(val);
		}

	}

	private class Node<Item> {
		Node() {

		}

		Node(Item item) {
			this.item = item;
		}

		Node previousNode;
		Node nextNode;
		Item item;
	}

	private class DequeIterator implements Iterator<Item> {

		Node header = startNode;

		public boolean hasNext() {
			return header != null;
		}

		public Item next() {
			if (hasNext()) {
				Item item = (Item) header.item;
				header = header.nextNode;
				return item;
			} else {
				throw new NoSuchElementException();
			}
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

}
