public class KdTree 
{
    private static boolean ODD = true;
    private static boolean EVEN = false;
    private Node root;
    
    public KdTree() { }

    private class Node
    {
        private Point2D p;
        private int N;
        private Node left, right;
        private boolean parity;
        private RectHV rect;
        private Node(Point2D p, int N, boolean parity, RectHV rect)
        {
            this.p = p;
            this.N = N;
            this.parity = parity;
            this.rect = rect;
        }
    }
    
    private int size(Node n)
    {
        if (n == null)
            return 0;
        return n.N;
    }
       
    public boolean isEmpty() { return size(root) == 0; }
    public int size() { return size(root); }
    
    private Node insert(Point2D p, Node n, boolean parity, RectHV rect)
    {
        if (n == null)
            return new Node(p, 1, parity, rect);
        if(p.equals(n.p))
        	return n;
        
        if(n.parity == EVEN)
        {
        	if(p.x() < n.p.x())
        	{
        		RectHV leftRect;
        		if(n.left == null)
        			leftRect = new RectHV(rect.xmin(), rect.ymin(), n.p.x(), rect.ymax());
        		else
					leftRect = n.left.rect;
        		n.left = insert(p, n.left, ODD, leftRect);
        	}
        	else 
        	{
                RectHV rightRect;
                if(n.right == null)
                	rightRect = new RectHV(n.p.x(), rect.ymin(), rect.xmax(), rect.ymax());
                else
                	rightRect = n.right.rect;
                n.right = insert(p, n.right, ODD, rightRect);
			}
        }
        else 
        {
        	if(p.y() < n.p.y())
        	{
        		RectHV leftRect;
        		if(n.left == null)
        			leftRect = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), n.p.y());
        		else
        			leftRect = n.left.rect;
        		n.left = insert(p, n.left, EVEN, leftRect);
        	}
        	else 
        	{
                RectHV rightRect;
                if(n.right == null)
                	rightRect =  new RectHV(rect.xmin(), n.p.y(), rect.xmax(), rect.ymax());    
                else
                	rightRect = n.right.rect;
                n.right = insert(p, n.right, EVEN, rightRect);
			}
		}
        n.N = size(n.left) + size(n.right) + 1;
        return n;
    }
    
    public void insert(Point2D p) 
    { 
    	RectHV rect;
    	if(root == null)
    		rect = new RectHV(0, 0, 1, 1);
    	else
    		rect = root.rect;
    	root = insert(p, root, EVEN, rect); 
    }
    
    private boolean contains(Point2D p, Node n)
    {
        if (n == null)
            return false;
        if(p.equals(n.p))
        	return true;
        if(n.parity == EVEN)
        {
        	if(p.x() < n.p.x())
        		return contains(p, n.left);
        	else 
        		return contains(p, n.right);
        }
        else {
			if(p.y() < n.p.y())
				return contains(p, n.left);
			else
				return contains(p, n.right);
        }
    }
    
    public boolean contains(Point2D p) { return contains(p, root); }
        
    private void draw(Node n)
    {
        if (n == null)
            return;
        draw(n.left);
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLACK);
        n.p.draw();
        StdDraw.setPenRadius();
        if (n.parity == EVEN)
        {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(n.p.x(), n.rect.ymin(), n.p.x(), n.rect.ymax());
        }
        else
        {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(n.rect.xmin(), n.p.y(), n.rect.xmax(), n.p.y());
        }
        draw(n.right);
    }
    
    public void draw() { draw(root); }
    
    private void range(Stack<Point2D> s, Node n, RectHV rect)
    {
        if (n == null)
            return;
        if(!n.rect.intersects(rect))
        	return;
        range(s, n.left, rect);
        if (rect.contains(n.p))
            s.push(n.p);
        range(s, n.right, rect);
    }
    
    public Iterable<Point2D> range(RectHV rect)
    {
        Stack<Point2D> s = new Stack<Point2D>();
        range(s, root, rect);
        return s;
    }
    
    private Point2D nearest(Point2D p, Point2D current, Node n)
    {
        if (n == null)
            return current;
        if(n.rect.distanceSquaredTo(p) >= current.distanceSquaredTo(p))
        	return current;
        if (n.p.distanceSquaredTo(p) < current.distanceSquaredTo(p))
        	current = n.p;
        
    	if ((n.parity == EVEN && p.x() < n.p.x()) 
    			|| (n.parity == ODD && p.y() < n.p.y()))
    	{
    		current = nearest(p, current, n.left);
    		current = nearest(p, current, n.right);
    	}
    	else
    	{
    		current = nearest(p, current, n.right);
    		current = nearest(p, current, n.left);
    	}
        return current;    
    }
    
    public Point2D nearest(Point2D p) 
    {
        if (root == null)
            return null;
        return nearest(p, root.p, root);
    }
    
    public static void main(String[] args)
    {
        In in = new In(args[0]);
        KdTree kd = new KdTree();
        StdDraw.setPenRadius(0.01);
        while(!in.isEmpty())
        {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kd.insert(p);
        }
        kd.draw();
        RectHV rect = new RectHV(0.2, 0.2, 0.8, 0.8);
        StdDraw.setPenColor(StdDraw.BLUE);
        for (Point2D p : kd.range(rect))
        {
            p.draw();
        }
    }
}
