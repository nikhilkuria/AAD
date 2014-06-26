package com.sc.percolation;

public class Percolation {
	
	private int[] percolationMatrix;
	private int[] matrixWeight;
	
	public Percolation(int size) {
		// create N-by-N grid, with all sites blocked
		percolationMatrix = new int[size];
		matrixWeight = new int[size];
		for (int index = 0; index < size; index++) {
			percolationMatrix[index] = index;
			matrixWeight[index] = 1;
		}
		//Add the start and end nodes as maximum and negative values
		percolationMatrix[size] = Integer.MIN_VALUE;
		matrixWeight[size] = 1;
		percolationMatrix[size+1] = Integer.MAX_VALUE;
		matrixWeight[size+1] = 1;
	}

	public void open(int xOrdinate, int yOrdinate) {
		// open site (row i, column j) if it is not already
		int[] neighbours = getNeighbours(xOrdinate, yOrdinate);
		for (int neighbour : neighbours) {
			//Add the neighbours to the connection
		}
	}

	public boolean isOpen(int xOrdinate, int yOrdinate) {
		// is site (row i, column j) open?
		return false;
	}

	public boolean isFull(int xOrdinate, int yOrdinate) {
		// is site (row i, column j) full?
		return false;
	}

	public boolean percolates() {
		// does the system percolate?
		return false;
	}
	
	private int[] getNeighbours(int xOrdinate, int yOrdinate) {
		// TODO Auto-generated method stub
		return null;
	}
}