package com.sc.percolation;

import com.princeton.stdlib.StdOut;
import com.princeton.stdlib.StdRandom;
import com.princeton.stdlib.StdStats;

public class PercolationStatsNew {

	private double[] thresholds;
    private int experiments;
	public PercolationStatsNew(int N, int T) {
		// perform T independent computational experiments on an N-by-N grid
        experiments = T;
		if (T <= 0 || N <= 0) {
			throw new IllegalArgumentException("N and T should be positive!");
		}
		thresholds = new double[T];
		for (int i = 0; i < T; i++) {
			Percolation percolation = new Percolation(N);
			double openSitesCount = 0d;
			while (!percolation.percolates()) {
				int x = StdRandom.uniform(1, N + 1);
				int y = StdRandom.uniform(1, N + 1);
				if (!percolation.isOpen(x, y)) {
					percolation.open(x, y);
					openSitesCount++;
				}
			}
			thresholds[i] = openSitesCount / (N*N);
		}
	}

	public double mean() {
		// sample mean of percolation threshold
		return StdStats.mean(thresholds);
	}

	public double stddev() {
		// sample standard deviation of percolation threshold
		if (thresholds.length > 1) {
			return StdStats.stddev(thresholds);
		} else {
			return Double.NaN;
		}
	}
    
    public double confidenceLo() {
		return (mean()-((1.96*stddev())/Math.sqrt(experiments)));
	}

	// returns upper bound of the 95% confidence interval
	public double confidenceHi() {
		return (mean()+((1.96*stddev())/Math.sqrt(experiments)));
		
	}

	public static void main(String[] args) {
		int T = Integer.parseInt(args[1]);
		PercolationStats stats = new PercolationStats(
				Integer.parseInt(args[0]), T);
		double mean = stats.mean();
		StdOut.println("mean = " + mean);
		double stddev = stats.stddev();
		StdOut.println("stddev = " + stddev);
		double d = (1.96 * stddev) / Math.sqrt(T);
		StdOut.println("95% confidence interval = " + (mean - d) + ", "
				+ (mean + d));
	}

}