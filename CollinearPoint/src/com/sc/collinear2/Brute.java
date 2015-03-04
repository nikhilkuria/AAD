package com.sc.collinear2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.princeton.stdlib.In;
import com.princeton.stdlib.StdDraw;

public class Brute
{

    private static final String POINT_SEPARATOR = " -> ";
    
    private static void initilizeCanvas()
    {
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setXscale(0, 40);
        StdDraw.setYscale(0, 40);
        StdDraw.setPenRadius(.008);
    }

    private static Point[] generatePointList(String filename)
    {
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

    private static void findCollinearPoints(Point[] points)
    {
        int pointCount = points.length;
        for (int firstIndex = 0; firstIndex < pointCount; firstIndex++) {
            for (int secondIndex = firstIndex+1; secondIndex < pointCount; secondIndex++) {
                for (int thirdIndex = secondIndex+1; thirdIndex < pointCount; thirdIndex++) {
                    for (int fouthIndex = thirdIndex+1; fouthIndex < pointCount; fouthIndex++) {
                        Point firstPoint = points[firstIndex];
                        Point secondPoint = points[secondIndex];
                        Point thirdPoint = points[thirdIndex];
                        Point fourthPoint = points[fouthIndex];
                        if((firstPoint.slopeTo(secondPoint)==firstPoint.slopeTo(thirdPoint))&&(firstPoint.slopeTo(thirdPoint)==firstPoint.slopeTo(fourthPoint))){
                            List<Point> linePoints = Arrays.asList(new Point[]{firstPoint,secondPoint,thirdPoint,fourthPoint});
                            Collections.sort(linePoints);
                            linePoints.get(0).drawTo(linePoints.get(3));
                            printLine(linePoints);
                        }
                    }
                }
            }
        }
    }

    private static void printLine(List<Point> linePoints)
    {
     System.out.print(linePoints.get(0));
     System.out.print(POINT_SEPARATOR);
     System.out.print(linePoints.get(1));
     System.out.print(POINT_SEPARATOR);
     System.out.print(linePoints.get(2));
     System.out.print(POINT_SEPARATOR);
     System.out.println(linePoints.get(3));

    }

    public static void main(String args[])
    {
        initilizeCanvas();
        String filename = args[0];
        Point[] points = generatePointList(filename);
        findCollinearPoints(points);

    }

}
