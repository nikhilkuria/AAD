package com.sc.percolation.again;

import com.princeton.stdlib.StdIn;

public class Percolation {
	
	private final int INVALID_NODE = -1;
	
	private int[] grid;
	private int[] componentHeight;
	private int[] openNodes;
	private int initialNode;
	private int finalNode;
	private int dimension;
	
	public Percolation(int gridDimension) {
		int totalGridSize = gridDimension*gridDimension;
		grid = new int[totalGridSize+2];
		dimension = gridDimension;
		componentHeight = new int[totalGridSize+2];
		openNodes = new int[totalGridSize+2];
		initialNode = totalGridSize;
		finalNode = totalGridSize+1;
		for (int index = 0; index < totalGridSize+2; index++) {
			componentHeight[index] = 0;
			grid[index] = index;
			openNodes[index] = 0;
		}
		openNodes[initialNode] = 1;
		openNodes[finalNode] = 1;
		//performInitialLinks(gridDimension);
	}
	
	public void open(int xCordinate, int yCordinate) {
		validateArguments(xCordinate,yCordinate);
		if(isOpen(xCordinate, yCordinate)){
			return;
		}
		int cellIndex = getIndex(xCordinate, yCordinate);
		//open to bottom, up, left and right cells
		int upIndex = getIndex(xCordinate, yCordinate-1);
		int downIndex = getIndex(xCordinate, yCordinate+1);
		int leftIndex = getIndex(xCordinate-1, yCordinate);
		int rightIndex = getIndex(xCordinate+1, yCordinate);
		
		openNodes[cellIndex] = 1;
		
		if(upIndex!=INVALID_NODE){
			connect(cellIndex, upIndex);
		}
		if(downIndex!=INVALID_NODE){
			connect(cellIndex, downIndex);
		}
		if(leftIndex!=INVALID_NODE){
			connect(cellIndex, leftIndex);
		}
		if(rightIndex!=INVALID_NODE){
			connect(cellIndex, rightIndex);
		}
		
	}

	private void validateArguments(int xCordinate, int yCordinate) {
		xCordinate--;
		yCordinate--;
		if((yCordinate<0) || yCordinate>(dimension-1)){
			throw new IndexOutOfBoundsException();
		}
		if(xCordinate<0){
			throw new IndexOutOfBoundsException();
		}
		if(xCordinate>(dimension-1)){
			throw new IndexOutOfBoundsException();
		}
	}

	public boolean isOpen(int xCordinate, int yCordinate) {
		validateArguments(xCordinate,yCordinate);
		return openNodes[getIndex(xCordinate, yCordinate)]==1?true : false;
	}

	public boolean isFull(int xCordinate, int yCordinate) {
		validateArguments(xCordinate,yCordinate);
		return connected(initialNode, getIndex(xCordinate, yCordinate));	
	}

	public boolean percolates() {
		return connected(initialNode, finalNode);// && (openNodes[initialNode]==1) && (openNodes[finalNode]==1);
	}
	
	private int getIndex(int xCordinate, int yCordinate) {
		xCordinate--;
		yCordinate--;
		if((yCordinate<0) || yCordinate>(dimension-1)){
			return INVALID_NODE;
		}
		if(xCordinate<0){
			return initialNode;
		}
		if(xCordinate>(dimension-1)){
			return finalNode;
		}
		return (yCordinate*dimension) + xCordinate;
	}
	
	private void connect(int sourceNode, int destinationNode){
		if((openNodes[sourceNode]!=1)||(openNodes[destinationNode]!=1)){
			return;
		}
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
		for (int index = 0; index < openNodes.length-2; index++) {
			String status = openNodes[index] == 1? "O" : "X";
			System.out.print(status+" ");
			if((index+1)%dimension==0){
				System.out.println();
			}
		}
		
	}
	
	public static void main(String[] args) {
        int gridSize = StdIn.readInt();
        Percolation percolation = new Percolation(gridSize);
        while (!StdIn.isEmpty()) {
            int xCod = StdIn.readInt();
            int yCod = StdIn.readInt();
            percolation.open(xCod, yCod);
            //percolation.printArray();
            percolation.printGrid();
            System.out.println(percolation.percolates());
        }

	}
}