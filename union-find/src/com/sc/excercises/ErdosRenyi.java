package com.sc.excercises;

import java.util.Random;

import com.princeton.stdlib.StdIn;
import com.princeton.stdlib.StdOut;
import com.princeton.stdlib.StdStats;
import com.princeton.unionfind.WeightedQuickUnionUF;

public class ErdosRenyi {

	public static double count(int dimension){
		double connections = 0;
		WeightedQuickUnionUF grid = new WeightedQuickUnionUF(dimension);
		Random randomGenerator = new Random();
		while(grid.count()>1){
			int source = randomGenerator.nextInt(dimension);
			int destination = randomGenerator.nextInt(dimension);
			grid.union(source, destination);
			connections++;
		}
		return connections;
	}
	
	public static void main(String args[]){
		int dimension = StdIn.readInt();	
		int trials = StdIn.readInt();
		double results[] = new double[trials];
		for (int count = 0; count < trials; count++) {
			results[count] = count(dimension);
		}
        StdOut.println("1/2 N ln N = " + 0.5 * dimension * Math.log(dimension));
        StdOut.println("mean       = " + StdStats.mean(results));
        StdOut.println("stddev     = " + StdStats.stddev(results));
	}
	
}
