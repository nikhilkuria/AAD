package com.sc.collinear;

import com.princeton.stdlib.In;
import com.princeton.stdlib.StdDraw;

public class Fast {

	public static void main(String args[]){
		initilizeCanvas();
		String filename = args[0];
		Point[] points = generatePointList(filename);
		findCollinearPoints(points);
	}
	
	private static void findCollinearPoints(Point[] points) {
		for (int originIndex = 0; originIndex < points.length; originIndex++) {
			Point originPoint = points[originIndex];
			Double[] slopes = new Double[points.length];
			for (int index = 0; index < points.length; index++) {
				if(originIndex!=index){
					Point point = points[index];
					double slope = originPoint.slopeTo(point);
					slopes[index] = slope;
				}
			}
		}
	}

	private static Point[] generatePointList(String filename) {
		In in = new In(filename);
		int size = in.readInt();
		Point[] points = new Point[size];
		for (int i = 0; i < size; i++) {
			int x = in.readInt();
			int y = in.readInt();
			Point point = new Point(x, y);
			points[i] = point;
			point.draw();
		}
		return points;
	}

	private static void initilizeCanvas() {
		StdDraw.clear();
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.setXscale(0, 40);
		StdDraw.setYscale(0, 40);
		StdDraw.setPenRadius(.008);
	}
}
