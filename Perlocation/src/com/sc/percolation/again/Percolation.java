package com.sc.percolation.again;

import com.princeton.stdlib.StdIn;

public class Percolation {
	
	private int[] grid;
	private int[] componentHeight;
	private int initialNode;
	private int finalNode;
	
	public Percolation(int gridDimension) {
		int totalGridSize = gridDimension*gridDimension;
		grid = new int[totalGridSize+2];
		componentHeight = new int[totalGridSize+2];
		initialNode = totalGridSize;
		finalNode = totalGridSize+1;
		for (int index = 0; index < totalGridSize+2; index++) {
			componentHeight[index] = 0;
			grid[index] = index;
		}
		performInitialLinks(gridDimension);
	}

	private void performInitialLinks(int gridDimension) {
		//Link Initial Node to top row
		for (int topRowNode = 0; topRowNode < gridDimension; topRowNode++) {
			connect(topRowNode, initialNode);
		}
		//Link bottom row to final Node
		for (int bottomRowNode = (gridDimension*(gridDimension-1)); bottomRowNode < gridDimension*gridDimension; bottomRowNode++) {
			connect(bottomRowNode, finalNode);
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