package com.sc.collinear;

import java.util.Arrays;
import java.util.Comparator;

import com.princeton.stdlib.In;
import com.princeton.stdlib.StdDraw;
import com.sc.sort.elementary.Insertion;

public class Fast {

	private static final String POINT_SEPARATOR = " -> ";
	
	public static void main(String args[]){
		initilizeCanvas();
		String filename = args[0];
		Point[] points = generatePointList(filename);
		findCollinearPoints(points);
	}
	
	private static void findCollinearPoints(Point[] points) {
		for (int originIndex = 0; originIndex < points.length; originIndex++) {
			Point originPoint = points[originIndex];
			SlopeComparator slopeComparator = new SlopeComparator(originPoint);
			Arrays.sort(points, slopeComparator);
			findLinesFromOrigin(originPoint,points);
		}
	}

	private static void findLinesFromOrigin(Point originPoint, Point[] points) {
		double[] slopes = new double[points.length];
		StringBuilder line = new StringBuilder();
		boolean lineFound = false;
		int pointsInLine=0;
		for (int index = 0; index < points.length; index++) {
			slopes[index] = originPoint.slopeTo(points[index]);
		}
		line.append(originPoint.toString()+POINT_SEPARATOR);
		for (int index = 0; index < slopes.length-1; index++) {
			if(points[index]==originPoint){
				continue;
			}
			if(!lineFound){
				line.append(points[index].toString()+ POINT_SEPARATOR);
				pointsInLine++;
			}
			if(slopes[index]==slopes[index+1]){
				lineFound = true;
				pointsInLine++;
				line.append(points[index+1].toString() + POINT_SEPARATOR);
			}else{
				lineFound=false;
				if(pointsInLine>3){
					System.out.println(line);
				}
				pointsInLine = 0;
				line.setLength(0);
				line.append(originPoint.toString()+POINT_SEPARATOR);
			}
		}
	}
	
	private static void printSlopes(Point[] points, Point originPoint) {
		for (Point point : points) {
			System.out.print(originPoint.slopeTo(point)+" ");
		}
		System.out.println();
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
	
	static class SlopeComparator implements Comparator<Point>{
		private Point originPoint;
		public SlopeComparator(Point originPoint){
			this.originPoint = originPoint;
		}
		@Override
		public int compare(Point pointOne, Point pointTwo) {
			double slopeOne = originPoint.slopeTo(pointOne);
			double slopeTwo = originPoint.slopeTo(pointTwo);
			if(slopeOne == slopeTwo){
				return 0;
			}
			return slopeOne>slopeTwo ? 1 : -1; 
		}
	}
}
