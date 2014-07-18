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
		for (int originIndex = points.length-1; originIndex >= 0; originIndex--) {
			Point originPoint = points[originIndex];
			Point[] sortPoints = Arrays.copyOf(points, points.length);
			printsLinesFromOrigin(originPoint, sortPoints);
		}
		
	}

	private static void printsLinesFromOrigin(Point originPoint, Point[] points) {
		Arrays.sort(points, new SlopeComparator(originPoint));
		StringBuilder lineBuilder = new StringBuilder(originPoint.toString()+POINT_SEPARATOR);
		int pointsCount = 1;
		for (int index = 0; index < points.length; index++) {
			Point point = points[index];
			if(pointsCount==1){
				lineBuilder.append(point.toString()+POINT_SEPARATOR);
				pointsCount++;
				continue;
			}
			Point previousPoint = points[index-1];
			if(originPoint.slopeTo(point)==originPoint.slopeTo(previousPoint)){
				pointsCount++;
				lineBuilder.append(point.toString()+POINT_SEPARATOR);
			}else{
				if(pointsCount>3){
					System.out.println(lineBuilder);
				}
				pointsCount=2;
				lineBuilder.setLength(0);
				lineBuilder.append(originPoint.toString()+POINT_SEPARATOR);
				lineBuilder.append(point.toString()+POINT_SEPARATOR);
			}
		}
		if(pointsCount>3){
			System.out.println(lineBuilder);
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
