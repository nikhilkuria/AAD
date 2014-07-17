package com.sc.collinear;

import com.princeton.stdlib.In;
import com.princeton.stdlib.StdDraw;
import com.sc.sort.elementary.Insertion;

public class Brute {

	private static final String POINT_SEPARATOR = " -> ";

	public static void main(String args[]) {
		initilizeCanvas();
		String filename = args[0];
		Point[] points = generatePointList(filename);
		findCollinearPoints(points);
		
	}

	private static void findCollinearPoints(Point[] points) {
		Insertion.sort(points);
		for (int firstIndex = 0; firstIndex < points.length; firstIndex++) {
			Point[] outputString = new Point[4];
			Point firstPoint = points[firstIndex];
			outputString[0] = firstPoint;
			for (int secondIndex = firstIndex+1; secondIndex < points.length; secondIndex++) {
				Point secondPoint = points[secondIndex];
				Double lineSlope = firstPoint.slopeTo(secondPoint);
				outputString[1] = secondPoint;
				for (int thirdIndex = secondIndex+1; thirdIndex < points.length; thirdIndex++) {
					Point thirdPoint = points[thirdIndex];
					if(firstPoint.slopeTo(thirdPoint)==lineSlope){
						outputString[2] = thirdPoint;
						for (int fourthIndex = thirdIndex+1; fourthIndex < points.length; fourthIndex++) {
							Point fourthPoint = points[fourthIndex];
							if(firstPoint.slopeTo(fourthPoint)==lineSlope){
								outputString[3] = fourthPoint;
								printLine(outputString);
							}
						}
					}
				}
			}
		}
		
	}

	private static void printLine(Point[] outputString) {
		Insertion.sort(outputString);
		int index = 1;
		System.out.print(outputString[0].toString());
		do{
			System.out.print(POINT_SEPARATOR);
			Point point = outputString[index];
			System.out.print(point.toString());
			index++;
		}while(index<outputString.length);
		outputString[0].drawTo(outputString[3]);
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

}
