import java.awt.Color;

public class SeamCarver 
{
    private Picture pic;
    public SeamCarver(Picture picture)  
    {
        pic = new Picture(picture);
    }
    public Picture picture() { return pic; }                        
    public int width() { return pic.width(); }         
    public int height() { return pic.height(); }   
    
    private int square(int x) { return x * x; }
    
    public  double energy(int x, int y) 
    {
        if (x < 0 || x > width() - 1 || y < 0 || y > height() - 1)
            throw new IndexOutOfBoundsException();
        if (x == 0 || y == 0 || x == width() - 1 || y == height() - 1)
            return 195075;
        Color left = pic.get(x - 1, y), right = pic.get(x + 1, y);
        Color up = pic.get(x, y - 1), down = pic.get(x, y + 1);
        double res = square(left.getRed() - right.getRed())
                + square(left.getGreen() - right.getGreen())
                + square(left.getBlue() - right.getBlue())
                + square(up.getRed() - down.getRed())
                + square(up.getGreen() - down.getGreen())
                + square(up.getBlue() - down.getBlue());
        return res;
        
    }
    public int[] findHorizontalSeam()
    {
        int[] res = new int[width()];
        double[][] distTo = new double[width()][height()];
        int[][] previous = new int[width()][height()];
        
        for (int y = 0; y < height(); y++)
            distTo[0][y] = energy(0, y);
        
        for (int x = 1; x < width(); x++)
        {
            for (int y = 0; y < height(); y++)
            {
                int pre = y;
                double dis = distTo[x - 1][y];
                if (y > 0 && distTo[x - 1][y - 1] < dis)
                {
                    pre = y - 1;
                    dis = distTo[x - 1][y - 1];
                }
                if (y < height() - 1 && distTo[x - 1][y + 1] < dis)
                {
                    pre = y + 1;
                    dis = distTo[x - 1][y + 1];
                }
                previous[x][y] = pre;
                distTo[x][y] = dis + energy(x, y);
            }
        }
        
        int ymin = 0;
        for (int y = 1; y < height(); y++)
            if (distTo[width() - 1][y] < distTo[width() - 1][ymin])
                ymin = y;
        res[width() - 1] = ymin;
        for (int x = width() - 1; x > 0; x--)
            res[x - 1] = previous[x][res[x]];
        return res;
    }
    public int[] findVerticalSeam() 
    {
        int[] res = new int[height()];
        double[][] distTo = new double[width()][height()];
        int[][] previous = new int[width()][height()];
        
        for (int x = 0; x < width(); x++)
            distTo[x][0] = energy(x, 0);
        
        for (int y = 1; y < height(); y++)
        {
            for (int x = 0; x < width(); x++)
            {
                int pre = x;
                double dis = distTo[x][y - 1];
                if (x > 0 && distTo[x - 1][y - 1] < dis)
                {
                    pre = x - 1;
                    dis = distTo[x - 1][y - 1];
                }
                if (x < width() - 1 && distTo[x + 1][y - 1] < dis)
                {
                    pre = x + 1;
                    dis = distTo[x + 1][y - 1];
                }
                previous[x][y] = pre;
                distTo[x][y] = dis + energy(x, y);
            }
        }
        
        int xmin = 0;
        for (int x = 1; x < width(); x++)
            if (distTo[x][height() - 1] < distTo[xmin][height() - 1])
                xmin = x;
        res[height() - 1] = xmin;
        for (int y = height() - 1; y > 0; y--)
            res[y - 1] = previous[res[y]][y];
        return res;
        
    }
    public void removeHorizontalSeam(int[] seam) 
    {
        if (height() <= 1)
            throw new IllegalArgumentException();
        if (seam.length != width())
            throw new IllegalArgumentException();
        for (int i = 0; i < seam.length; i++)
        {
            if (seam[i] > height() - 1 || seam[i] < 0)
                throw new IllegalArgumentException();
            if (i > 0 && (seam[i] - seam[i - 1] > 1 ||  seam[i] - seam[i - 1] < -1))
                throw new IllegalArgumentException();
        }
        Picture newPic = new Picture(width(), height() - 1);
        for (int x = 0; x < width(); x++)
        {
            for (int y = 0; y < seam[x]; y++)
                newPic.set(x, y, pic.get(x, y));
            for (int y = seam[x]; y < height() - 1; y++)
                newPic.set(x, y, pic.get(x, y + 1));
        }
        pic = newPic;
    }
    public void removeVerticalSeam(int[] seam) 
    {
        if (width() <= 1)
            throw new IllegalArgumentException();
        if (seam.length != height())
            throw new IllegalArgumentException();
        Picture newPic = new Picture(width() - 1, height());
        for (int y = 0; y < height(); y++)
        {
            for (int x = 0; x < seam[y]; x++)
                newPic.set(x, y, pic.get(x, y));
            for (int x = seam[y]; x < width() - 1; x++)
                newPic.set(x, y, pic.get(x + 1, y));
        }
        pic = newPic;
    }
}
