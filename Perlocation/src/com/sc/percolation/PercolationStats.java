package com.sc.percolation;

import com.princeton.stdlib.StdRandom;
import com.princeton.stdlib.StdStats;

public class PercolationStats {
	
	private double[] percolationThresholds;
	
	// perform T independent computational experiments on an N-by-N grid
	public PercolationStats(int size, int experiments) {
		Percolation percolation = new Percolation(size);
		int totalCells = size*size;
		//Perform the simulation experiments amout of time
		for (int expIndex = 0; expIndex < experiments; expIndex++) {
			int openCells = 0;
			double percolationThreshold = 0;
			do{
				//Create a random position in the percolation Grid
				int xOrdinate = StdRandom.uniform(size);
				int yOrdinate = StdRandom.uniform(size);
				System.out.println("Opening Cell "+xOrdinate+", "+yOrdinate);
				percolation.open(xOrdinate, yOrdinate);
				openCells ++ ;
			}while(!percolation.percolates());
			System.out.println("-----------Perlocated-----------");
			percolationThreshold = openCells/totalCells;
			percolationThresholds[percolationThresholds.length]=percolationThreshold;
		}
	}

	// sample mean of percolation threshold
	public double mean() {
		return StdStats.mean(percolationThresholds);
	}

	// sample standard deviation of percolation threshold
	public double stddev() {
		return StdStats.stddev(percolationThresholds);
	}

	// returns lower bound of the 95% confidence interval
	public double confidenceLo() {
		return 0;
	}

	// returns upper bound of the 95% confidence interval
	public double confidenceHi() {
		return 0;
		
	}

	// test client, described below
	public static void main(String[] args) {
		PercolationStats percolationStats= new PercolationStats(3, 1);
	}
}