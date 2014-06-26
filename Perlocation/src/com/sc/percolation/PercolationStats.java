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
				int xOrdinate = StdRandom.uniform(size);
				int yOrdinate = StdRandom.uniform(size);
				if(!percolation.isOpen(xOrdinate, yOrdinate)){
					percolation.open(xOrdinate, yOrdinate);
					openCells ++ ;
				}
			}while(!percolation.percolates());
			percolationThreshold = openCells/totalCells;
			percolationThresholds[expIndex]=percolationThreshold;
		}
/*		for (int index : new int[]{21,7,11,18,22,15,12,10,7,19,2}) {
			Percolation percolation = new Percolation(size);
			int xCod = index/5;
			int yCod = (index-1)%5;
			percolation.open(xCod, yCod);
			if(percolation.percolates()){
				System.out.println("Done");
			}
			
		}*/
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
		PercolationStats percolationStats= new PercolationStats(2, 100000);
		System.out.println(percolationStats.mean());
		System.out.println(percolationStats.stddev());
		System.out.println(percolationStats.confidenceLo());
		System.out.println(percolationStats.confidenceHi());

	}
}