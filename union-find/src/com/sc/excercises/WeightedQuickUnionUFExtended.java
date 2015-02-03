package com.sc.excercises;

import com.princeton.stdlib.StdIn;
import com.princeton.stdlib.StdOut;
import com.princeton.stdlib.Stopwatch;

public class WeightedQuickUnionUFExtended {
	private int[] id; // id[i] = parent of i
	private int[] sz; // sz[i] = number of objects in subtree rooted at i
	private int[] largest;
	private int count; // number of components

	public WeightedQuickUnionUFExtended(int N) {
		count = N;
		id = new int[N];
		sz = new int[N];
		largest = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
			sz[i] = 1;
			largest[i] = i;
		}
	}

	public int count() {
		return count;
	}

	public int find(int p) {
		while (p != id[p])
			p = id[p];
		return p;
	}

	public int findLargest(int p) {
		while (p != id[p]) {
			p = id[p];
		}
		return largest[p];
	}

	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	public void union(int p, int q) {
		int rootP = find(p);
		int rootQ = find(q);
		if (rootP == rootQ)
			return;

		// make smaller root point to larger one
		if (sz[rootP] < sz[rootQ]) {
			id[rootP] = rootQ;
			if(largest[rootQ]<largest[rootP]){
				largest[rootQ] = largest[rootP];
			}
			sz[rootQ] += sz[rootP];
		} else {
			id[rootQ] = rootP;
			if(largest[rootP]<largest[rootQ]){
				largest[rootP] = largest[rootQ];
			}
			sz[rootP] += sz[rootQ];
		}
		count--;
	}

	public static void main(String[] args) {
		int N = StdIn.readInt();
		WeightedQuickUnionUFExtended uf = new WeightedQuickUnionUFExtended(N);
		Stopwatch stopwatch = new Stopwatch();
		while (!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if (uf.connected(p, q))
				continue;
			uf.union(p, q);
		}
		StdOut.println(uf.count() + " components");
		System.out.println("Took " + stopwatch.elapsedTime() + " seconds");
	}

}
