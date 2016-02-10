
public class PointSET 
{
    private SET<Point2D> ps;
    public PointSET() { ps = new SET<Point2D>(); }
    
    public boolean isEmpty() { return ps.size() == 0; }
    
    public int size() { return ps.size(); }
    
    public void insert(Point2D p) { ps.add(p); }
    
    public boolean contains(Point2D p) { return ps.contains(p); }
        
    public void draw() { for (Point2D p : ps) p.draw(); }
    
    public Iterable<Point2D> range(RectHV rect)
    {
        Stack<Point2D> s = new Stack<Point2D>();
        for (Point2D p : ps)
            if (rect.contains(p))
                s.push(p);
        return s;
    }
    
    public Point2D nearest(Point2D p)
    {
        Point2D res = null;
        for (Point2D pp : ps)
        {
            if (res == null)
                res = pp;
            else
            {
                if (p.distanceSquaredTo(pp) < p.distanceSquaredTo(res))
                    res = pp;
            }
        }
        return res;
    }

    public static void main(String[] args)
    {
        int n = Integer.parseInt(args[0]);
        PointSET ps = new PointSET();
        StdDraw.setPenRadius(0.01);
        for (int i = 0; i < n; i++)
        {
            double x = StdRandom.uniform();
            double y = StdRandom.uniform();
            Point2D p = new Point2D(x, y);
            ps.insert(p);
        }
        ps.draw();
        RectHV rect = new RectHV(0.2, 0.2, 0.8, 0.8);
        rect.draw();
        StdDraw.setPenColor(StdDraw.BLUE);
        for (Point2D p : ps.range(rect))
        {
            p.draw();
        }
    }
}
