package com.sc.percolation;

import com.princeton.stdlib.StdRandom;
import com.princeton.stdlib.StdStats;

public class PercolationStats {
	
	private double[] percolationThresholds;
	private int experiments;
	
	// perform T independent computational experiments on an N-by-N grid
	public PercolationStats(int size, int experiments) {
		percolationThresholds = new double[experiments];
		this.experiments = experiments;
		double totalCells = size*size;
		//Perform the simulation experiments amout of time
		for (int expIndex = 0; expIndex < experiments; expIndex++) {
			Percolation percolation = new Percolation(size);
			double openCells = 0;
			double percolationThreshold = 0;
			do{
				//Create a random position in the percolation Grid
				int xOrdinate = StdRandom.uniform(1,size+1);
				int yOrdinate = StdRandom.uniform(1,size+1);
				if(!percolation.isOpen(xOrdinate, yOrdinate)){
					percolation.open(xOrdinate, yOrdinate);
					openCells ++ ;
				}
				percolation.isFull(xOrdinate, yOrdinate);
			}while(!percolation.percolates());
			percolationThreshold = openCells/totalCells;
			percolationThresholds[expIndex]=percolationThreshold;
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
		return (mean()-((1.96*stddev())/Math.sqrt(experiments)));
	}

	// returns upper bound of the 95% confidence interval
	public double confidenceHi() {
		return (mean()+((1.96*stddev())/Math.sqrt(experiments)));
		
	}

	// test client, described below
	public static void main(String[] args) {
		int gridSize = Integer.valueOf(args[0]);
		int experiments = Integer.valueOf(args[1]);
		if((gridSize<=0)||(experiments<=0)){
			throw new IllegalArgumentException("Illegal input");
		}
		PercolationStats percolationStats= new PercolationStats(gridSize, experiments);
		System.out.println("mean				="+percolationStats.mean());
		System.out.println("stddev				="+percolationStats.stddev());
		System.out.println("95% confidence interval		="+percolationStats.confidenceLo()+", "+percolationStats.confidenceHi());
	}
}