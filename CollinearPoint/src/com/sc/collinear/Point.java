package com.sc.collinear;

import java.util.Comparator;

import com.princeton.stdlib.StdDraw;

public class Point implements Comparable<Point> {

    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>()
    {
        @Override
        public int compare(Point point1, Point point2)
        {
            double slope1 = slopeTo(point1);
            double slope2 = slopeTo(point2);
            
            //Comparing the slopes
            if(slope1<slope2){
                return -1;
            }else if(slope1>slope2){
                return 1;
            }else{
                return 0;
            }
        }
    }; 
    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public double slopeTo(Point that) {
        return 0;
    }

    public int compareTo(Point that) {
        return 0;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }

}