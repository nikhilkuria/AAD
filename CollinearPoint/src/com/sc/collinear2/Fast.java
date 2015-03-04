package com.sc.collinear2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.princeton.stdlib.In;
import com.princeton.stdlib.StdDraw;

public class Fast
{
    private static void initilizeCanvas()
    {
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setXscale(0, 40000);
        StdDraw.setYscale(0, 40000);
        StdDraw.setPenRadius(.008);
    }
    
    private static void findCollinearPoints(Point[] points)
    {
        List<Point> pointList = Arrays.asList(points);
        Point[] sortedPoints = new Point[points.length];
        System.arraycopy(points, 0, sortedPoints, 0, points.length);
        
        List<Point> pointListSorted = Arrays.asList(sortedPoints);
        
        for (Point originPoint : pointList) {
            Collections.sort(pointListSorted, originPoint.SLOPE_ORDER);
            List<Point> linePoints = new ArrayList<>();
            resetLinePoint(linePoints);
            for (Point point : pointListSorted) {
                double slope = originPoint.slopeTo(point);
                if (slope == Double.NEGATIVE_INFINITY) {
                    continue;
                }
                if ((linePoints.isEmpty())
                        || (linePoints.get(0).slopeTo(originPoint) == slope)) {
                    if(originPoint.compareTo(point)==1){
                        linePoints.clear();
                        break;
                    }
                    linePoints.add(point);
                }
                else {
                    if (linePoints.size() > 2) {
                        printLine(originPoint, linePoints);
                    }
                    resetLinePoint(linePoints);
                    if(originPoint.compareTo(point)!=1){
                        linePoints.add(point);
                    }

                }
            }
            if (linePoints.size() > 2) {
                printLine(originPoint, linePoints);
            }
            resetLinePoint(linePoints);
        }

    }

    private static void
            resetLinePoint(List<Point> linePoints)
    {
        linePoints.clear();
    }

    private static void printLine(Point originPoint, List<Point> linePoints)
    {
        linePoints.add(originPoint);
        Collections.sort(linePoints);
        System.out.print(linePoints.get(0));
        for (int pointIndex = 1; pointIndex < linePoints.size(); pointIndex++) {
            System.out.print(" -> ");
            System.out.print(linePoints.get(pointIndex));
        }
        System.out.println();
        originPoint.drawTo(linePoints.get(linePoints.size()-1));
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

    public static void main(String[] args)
    {
        initilizeCanvas();
        String filename = args[0];
        Point[] points = generatePointList(filename);
        findCollinearPoints(points);
    }

}
