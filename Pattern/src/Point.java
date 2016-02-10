import java.util.Comparator;

public class Point implements Comparable<Point>
{
    public final Comparator<Point> SLOPE_ORDER;
    
    private class SlopeOrder implements Comparator<Point>
    {
        public int compare(Point p1, Point p2)
        {
            double s1 = slopeTo(p1);
            double s2 = slopeTo(p2);
            if (s1 < s2) return -1;
            if (s1 > s2) return +1;
            return 0;
        }
    }
    
    private final int x;
    private final int y;
    
    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
        SLOPE_ORDER = new SlopeOrder();
    }
    
    public void draw()
    {
        StdDraw.point(x, y);
    }
    
    public void drawTo(Point that)
    {
        StdDraw.line(x, y, that.x, that.y);
    }
    
    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }
    
    public int compareTo(Point that)
    {
        if (y > that.y) return 1;
        if (y < that.y) return -1;
        if (x > that.x) return 1;
        if (x < that.x) return -1;
        return 0;
    }
    
    public double slopeTo(Point that)
    {
        if (that.y == y)
        {
            if (that.x == x) return Double.NEGATIVE_INFINITY;
            return +0;
        }
        if (that.x == x)
            return Double.POSITIVE_INFINITY;
        return (double) (that.y - y) / (double) (that.x - x);
    }
    
    public static void main(String[] args)
    {
        StdDraw.setXscale(0, 10);
        StdDraw.setYscale(0, 10);
        Point[] points = new Point[4];
        points[0] = new Point(2, 2);
        points[1] = new Point(8, 2);
        points[2] = new Point(2, 8);
        points[3] = new Point(8, 8);
        
        for (int i = 0; i < points.length; i++)
        {
            points[i].draw();
            StdOut.printf("%f ", points[0].slopeTo(points[i]));
        }
        StdOut.println();
        
        for (int i = 1; i < points.length; i++)
        {
            points[0].drawTo(points[i]);
        }
    }

}
