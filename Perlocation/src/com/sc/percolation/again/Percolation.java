package com.sc.percolation.again;

import com.princeton.stdlib.StdIn;

public class Percolation {
	
	private int[] grid;
	private int[] componentHeight;
	
	public Percolation(int gridSize) {
		grid = new int[gridSize*gridSize];
		componentHeight = new int[gridSize*gridSize];
		for (int index = 0; index < gridSize*gridSize; index++) {
			componentHeight[index] = 0;
			grid[index] = index;
		}
	}

	public void open(int i, int j) {
		
	}

	public boolean isOpen(int i, int j) {
		return true;
	}

	public boolean isFull(int i, int j) {
		return true;	
	}

	public boolean percolates() {
		return true;
	}
	
	private void connect(int sourceNode, int destinationNode){
		int sourceComponentRoot = getRoot(sourceNode);
		int destinationComponentRoot = getRoot(destinationNode);
		if(sourceComponentRoot==destinationComponentRoot){
			return;
		}
		int sourceComponentHeight = componentHeight[sourceComponentRoot];
		int destinationComponentHeight = componentHeight[destinationComponentRoot];
		if(sourceComponentHeight>destinationComponentHeight){
			grid[destinationComponentRoot] = sourceComponentRoot;
		}else if(destinationComponentHeight>sourceComponentHeight){
			grid[sourceComponentRoot] = destinationComponentRoot;
		}else{
			grid[destinationComponentRoot] = sourceComponentRoot;
			componentHeight[sourceComponentRoot]++;
		}
	}
	
	private int getRoot(int node){
		int root = -1;
		if(node!=grid[node]){
			root = getRoot(grid[node]);
			grid[node] = root;
			return root;
		}
		return node;
	}
	
	private boolean connected(int origin, int destination) {
		return getRoot(origin)==getRoot(destination);
	}
	
	private void printGrid() {
		for (int element : grid) {
			System.out.print(element+" ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
        int gridSize = StdIn.readInt();
        Percolation percolation = new Percolation(gridSize);
        while (!StdIn.isEmpty()) {
            int origin = StdIn.readInt();
            int destination = StdIn.readInt();
            if (percolation.connected(origin, destination)) continue;
            percolation.connect(origin, destination);
            percolation.printGrid();
        }
	}
}