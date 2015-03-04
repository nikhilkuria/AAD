package com.sc.collinear2;

/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

import com.princeton.stdlib.StdDraw;

public class Point implements Comparable<Point>
{

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>()
    {

        @Override
        public int compare(Point o1, Point o2)
        {
            double slope1 = slopeTo(o1);
            double slope2 = slopeTo(o2);

            if (slope1 < slope2) {
                return -1;
            }
            else if (slope1 > slope2) {
                return +1;
            }
            else {
                return 0;
            }
        }
    };

    private final int x; // x coordinate
    private final int y; // y coordinate

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void draw()
    {
        StdDraw.point(x, y);
    }

    public void drawTo(Point that)
    {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public double slopeTo(Point that)
    {
        if (this.compareTo(that) == 0) {
            return Double.NEGATIVE_INFINITY;
        }
        if (this.y == that.y) {
            return 0;
        }
        if (this.x == that.x) {
            return Double.POSITIVE_INFINITY;
        }
        double slope = (double)(that.y - this.y) / (double)(that.x - this.x);
        return slope;
    }

    public int compareTo(Point that)
    {
        if (this.y < that.y) {
            return -1;
        }
        else if (this.y > that.y) {
            return +1;
        }
        else {
            if (this.x < that.x) {
                return -1;
            }
            else if (this.x > that.x) {
                return +1;
            }
            else {
                return 0;
            }
        }
    }

    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }

    public static void main(String[] args)
    {
    }
}
