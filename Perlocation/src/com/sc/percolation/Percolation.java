package com.sc.percolation;

public class Percolation {

	private WeightedQuickUnionUF weightedQuickUnionUF;
	private int size;
	private int originCell;
	private int finalCell;
	private boolean[] openCells;
	
	public Percolation(int size) {
		this.size = size;
		originCell = 0;
		finalCell = size*size+1;
		weightedQuickUnionUF = new WeightedQuickUnionUF(size*size+2);
		openCells = new boolean[size*size+1];
	}

	public void open(int xOrdinate, int yOrdinate) {
		// open site (row i, column j) if it is not already
		int cellIndex = getIndex(xOrdinate, yOrdinate);
		openCells[cellIndex] = true;
		//System.out.println(cellIndex);
		connectToMockNodes(cellIndex);
		for (int[] neighbour : getNeighbours(xOrdinate, yOrdinate)) {
			if(isOrdinatesOutBounds(neighbour[0], neighbour[1])){
				int neighbourIndex = getIndex(neighbour[0], neighbour[1]);
				//System.out.println("Connecting "+cellIndex+" to "+neighbourIndex);
				if(isOpen(neighbour[0], neighbour[1])){
					weightedQuickUnionUF.union(cellIndex, neighbourIndex);
				}
				//connectToMockNodes(neighbourIndex);
			}
		}
		
	}

	private void connectToMockNodes(int cellIndex) {
		//Connecting to mock initial node
		if(cellIndex<=size){
			weightedQuickUnionUF.union(originCell, cellIndex);
			//System.out.println("Connecting "+cellIndex+" to "+originCell);
		}
		//Connection to mock final node
		if(cellIndex>(size*(size-1))){
			weightedQuickUnionUF.union(cellIndex, finalCell);
			//System.out.println("Connecting "+cellIndex+" to "+finalCell);

		}
	}

	public boolean isOpen(int xOrdinate, int yOrdinate) {
/*		int cellIndex = getIndex(xOrdinate, yOrdinate);
		for (int[] neighbour : getNeighbours(xOrdinate, yOrdinate)) {
			if(isOrdinatesOutBounds(neighbour[0], neighbour[1])){
				int neighbourIndex = getIndex(neighbour[0], neighbour[1]);
				if(weightedQuickUnionUF.connected(cellIndex, neighbourIndex)){
					return true;
				}
			}
		}*/
		return openCells[getIndex(xOrdinate, yOrdinate)];

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