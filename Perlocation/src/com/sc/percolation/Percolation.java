package com.sc.percolation;

public class Percolation {

	private WeightedQuickUnionUF weightedQuickUnionUF;
	private int size;
	private int originCell;
	private int finalCell;
	
	public Percolation(int size) {
		this.size = size;
		originCell = 0;
		finalCell = size*size+1;
		weightedQuickUnionUF = new WeightedQuickUnionUF(size*size+2);
		
		//link to the imaginary origin node to the top row
		for (int index = 1; index <= size; index++) {
			weightedQuickUnionUF.union(originCell, index);
		}
		//link to the imaginary final node to the bottom row
		for (int index = size*(size-1)+1; index <= size*size;  index ++){
			weightedQuickUnionUF.union(index, finalCell);
		}
	}

	public void open(int xOrdinate, int yOrdinate) {
		// open site (row i, column j) if it is not already
		int cellIndex = getIndex(xOrdinate, yOrdinate);
		for (int[] neighbour : getNeighbours(xOrdinate, yOrdinate)) {
			if(isOrdinatesOutBounds(neighbour[0], neighbour[1])){
				int neighbourIndex = getIndex(neighbour[0], neighbour[1]);
				weightedQuickUnionUF.union(cellIndex, neighbourIndex);
			}
		}
	}

	public boolean isOpen(int xOrdinate, int yOrdinate) {
		int cellIndex = getIndex(xOrdinate, yOrdinate);
		for (int[] neighbour : getNeighbours(xOrdinate, yOrdinate)) {
			if(isOrdinatesOutBounds(neighbour[0], neighbour[1])){
				int neighbourIndex = getIndex(neighbour[0], neighbour[1]);
				if(weightedQuickUnionUF.connected(cellIndex, neighbourIndex)){
					return true;
				}
			}
		}
		return false;

	}

	public boolean isFull(int xOrdinate, int yOrdinate) {
		int cellIndex = getIndex(xOrdinate, yOrdinate);
		return weightedQuickUnionUF.connected(cellIndex, originCell);

	}

	public boolean percolates() {
		return weightedQuickUnionUF.connected(originCell, finalCell);
	}

	private int[][] getNeighbours(int xOrdinate, int yOrdinate) {
		int[][] neighbours = new int[4][];
		neighbours[0] = new int[]{xOrdinate+1,yOrdinate};
		neighbours[1] = new int[]{xOrdinate-1,yOrdinate};
		neighbours[2] = new int[]{xOrdinate,yOrdinate+1};
		neighbours[3] = new int[]{xOrdinate,yOrdinate-1};
		return neighbours;
	}

	private int getIndex(int xOrdinate, int yOrdinate) {
		// return the index position based on ordinates
		return (size * xOrdinate) + yOrdinate + 1;
	}

	
	private boolean isOrdinatesOutBounds(int xOrdinate, int yOrdinate) {
		return isIndexOutBounds(xOrdinate) && isIndexOutBounds(yOrdinate);
	}
	
	private boolean isIndexOutBounds(int ordinate) {
		if((ordinate<0) || (ordinate>size-1)){
			return false;
		}
		return true;
	}
}