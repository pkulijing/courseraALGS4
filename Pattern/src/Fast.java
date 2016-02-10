import java.util.Arrays;

public class Fast 
{
    private static void printAndDraw(Point[] ps)
    {
        int n = ps.length;
        ps[0].drawTo(ps[n - 1]);
        for (int ii = 0; ii < n - 1; ii++)
            StdOut.printf("%s -> ", ps[ii]);
        StdOut.printf("%s\n", ps[n - 1]);
    }
  
    public static void main(String[] args)
    {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        
        In in = new In(args[0]);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++)
        {
            points[i] = new Point(in.readInt(), in.readInt());
            points[i].draw();
        }
        
        for (int i = 0; i < N - 2; i++)
        {
            Arrays.sort(points);
            //This moves points[i] to points[0] since slopeTo itself is -INFINITY
            Arrays.sort(points, points[i].SLOPE_ORDER);
            double v = points[0].slopeTo(points[1]);
            int size = 1;
            for (int j = 2; j < N; j++)
            {
                double vnew = points[0].slopeTo(points[j]);
                if (v == vnew)
                    size++;
                else
                {
                    if (size > 2)
                    {
                        Point[] ps = new Point[size + 1];
                        for (int k = 0; k < size; k++)
                            ps[k] = points[j - 1 - k];
                        ps[size] = points[0];
                        //ps should be in descending order.
                        if (ps[size - 1].compareTo(ps[size]) > 0)
                            printAndDraw(ps);
                    }
                    v = vnew;
                    size = 1;
                }
            }
            if (size > 2)
            {
                Point[] ps = new Point[size + 1];
                for (int k = 0; k < size; k++)
                    ps[k] = points[N - 1 - k];
                ps[size] = points[0];
                //ps should be in descending order.
                if (ps[size - 1].compareTo(ps[size]) > 0)
                    printAndDraw(ps);
            }
        }
    }
}
