import java.util.Arrays;

public class Brute 
{
     public static void main(String[] args)
    {
        In in = new In(args[0]);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++)
        {
            points[i] = new Point(in.readInt(), in.readInt());
            points[i].draw();
        }
        
        Arrays.sort(points);
        
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        
        Point[] testPoints = new Point[4];
        for (int i = 0; i < N; i++)
        {
            testPoints[0] = points[i];

            for (int j = i + 1; j < N; j++)
            {
                testPoints[1] = points[j];
                double s1 = testPoints[0].slopeTo(testPoints[1]);
                for (int k = j + 1; k < N; k++)
                {
                    testPoints[2] = points[k];
                    double s2 = testPoints[0].slopeTo(testPoints[2]);
                    for (int l = k + 1; l < N; l++)
                    {
                        testPoints[3] = points[l];
                        double s3 = testPoints[0].slopeTo(testPoints[3]);
                        if (s1 == s2 && s1 == s3)
                        {    
                            for (int ii = 0; ii < 3; ii++)
                                StdOut.printf("%s -> ", testPoints[ii]);
                            StdOut.printf("%s\n", testPoints[3]);
                            testPoints[0].drawTo(testPoints[3]);
                        }
                    }
                }
            }
        }
    }

}
