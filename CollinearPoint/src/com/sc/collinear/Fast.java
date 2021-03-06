package com.sc.collinear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.princeton.stdlib.In;
import com.princeton.stdlib.StdDraw;

public class Fast {

	private static final String POINT_SEPARATOR = " -> ";
	private static Set<List<Point>> lines = new HashSet<>();
	
	public static void main(String args[]){
		//initilizeCanvas();
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
		Point startPoint = null;
		Point endPoint = null;
		for (List<Point> line : lines) {
		    Point[] lineArray = line.toArray(new Point[line.size()]);
		    Point currentStartPoint = lineArray[0];
	        Point currentEndPoint = lineArray[lineArray.length-1];
            if(startPoint==null){
                startPoint = currentStartPoint;
                endPoint = currentEndPoint;
            }else{
                if((currentEndPoint.slopeTo(endPoint)==Double.NEGATIVE_INFINITY) && (currentStartPoint.slopeTo(startPoint)==Double.NEGATIVE_INFINITY)){
                    continue;
                }
            }
		    //currentStartPoint.drawTo(currentEndPoint);
			for (int index = 0; index < lineArray.length; index++) {
				System.out.print(lineArray[index].toString());
				if(index<lineArray.length-1){
					System.out.print(POINT_SEPARATOR);
				}
			}
			System.out.println();
		}
		lines.clear();
		
	}

	private static void printsLinesFromOrigin(Point originPoint, Point[] points) {
		Arrays.sort(points, new SlopeComparator(originPoint));
		List<Point> line = new ArrayList<>();
		line.add(originPoint);
		int pointsCount = 1;
		for (int index = 0; index < points.length; index++) {
			Point point = points[index];
			if(point==originPoint){
				continue;
			}
			if(pointsCount==1){
				line.add(point);
				pointsCount++;
				continue;
			}
			Point previousPoint =null;
			int backtrackIndex = 0;
			do{
			     backtrackIndex++;
		         previousPoint = points[index-backtrackIndex];
			}while(previousPoint==originPoint);
			if(point.slopeTo(previousPoint)==Double.NEGATIVE_INFINITY){
			    continue;
			}
			backtrackIndex = 0;
			if(originPoint.slopeTo(point)==originPoint.slopeTo(previousPoint)){
				pointsCount++;
				line.add(point);
			}else{
				if(pointsCount>3){
					Collections.sort(line);
					lines.add(line);
				}
				pointsCount=2;
				line = new ArrayList<>();
				line.add(originPoint);
				line.add(point);
			}
		}
		if(pointsCount>3){
			Collections.sort(line);
			lines.add(line);
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
			//point.draw();
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
	
	private static class SlopeComparator implements Comparator<Point>{
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
