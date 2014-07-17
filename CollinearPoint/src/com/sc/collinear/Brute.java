package com.sc.collinear;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.princeton.stdlib.In;
import com.princeton.stdlib.StdDraw;

public class Brute {

	public static void main(String args[]) {
		initilizeCanvas();
		String filename = args[0];
		List<Point> pointList = generatePointList(filename);
		List<Set<Point>> collinearPoints = findCollinearPoints(pointList);
		StdDraw.setPenRadius(.003);
		StdDraw.setPenColor(Color.BLACK);
		for (Set<Point> line : collinearPoints) {
			Point originpoint = null;
			for (Point point : line) {
				if (originpoint == null) {
					originpoint = point;
				} else {
					originpoint.drawTo(point);
					System.out.print(" -> ");
				}
				System.out.print(point.toString());
			}
			System.out.println();
		}
	}

	private static List<Set<Point>> findCollinearPoints(List<Point> pointList) {
		List<Set<Point>> collinearPointsList = new ArrayList<>();
		Point[] pointArray = pointList.toArray(new Point[pointList.size()]);
		for (int firstIndex = 0; firstIndex < pointArray.length; firstIndex++) {
			Point firstPoint = pointArray[firstIndex];
			// collinearSet.add(firstPoint);
			for (int secondIndex = firstIndex + 1; secondIndex < pointArray.length; secondIndex++) {
				Set<Point> collinearPoints = new HashSet<>();
				Point secondPoint = pointArray[secondIndex];
				Double slope = firstPoint.slopeTo(secondPoint);
				collinearPoints.add(secondPoint);
				collinearPoints.add(firstPoint);
				for (int thirdIndex = secondIndex + 1; thirdIndex < pointArray.length; thirdIndex++) {
					Point thirdPoint = pointArray[thirdIndex];
					if (firstPoint.slopeTo(thirdPoint) == slope) {
						collinearPoints.add(thirdPoint);
						for (int fourthIndex = thirdIndex + 1; fourthIndex < pointArray.length; fourthIndex++) {
							Point fourthPoint = pointArray[fourthIndex];
							if (firstPoint.slopeTo(fourthPoint) == slope) {
								collinearPoints.add(fourthPoint);
								collinearPointsList.add(collinearPoints);
								continue;
							}
						}
					}

				}
			}
		}
		return collinearPointsList;
	}

	private static List<Point> generatePointList(String filename) {
		In in = new In(filename);
		int N = in.readInt();
		List<Point> points = new ArrayList<Point>(N);
		for (int i = 0; i < N; i++) {
			int x = in.readInt();
			int y = in.readInt();
			Point point = new Point(x, y);
			points.add(point);
			point.draw();
		}
		return points;
	}

	private static void initilizeCanvas() {
		StdDraw.clear();
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.setXscale(0, 20000);
		StdDraw.setYscale(0, 20000);
		StdDraw.setPenRadius(.008);
	}

}
