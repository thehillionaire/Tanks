/**
 * Created by Nedap on 7/11/2017.
 */
public class Coordinate {

    private int xcord;
    private int ycord;
    private int maxRadius;

    static int XMAX = 1000;
    static int YMAX = 1000;


    public Coordinate()
    {
        xcord = 0;
        ycord = 0;
        maxRadius = 0;
    }

    public Coordinate(int a, int b)
    {
        if(a < XMAX && a >= 0)
            xcord = a;
        else
            xcord = 0;

        if(b < YMAX && b >= 0)
            ycord = b;
        else
            ycord = 0;
    }

    public Coordinate(int a, int b, int size)
    {
        if(a < XMAX && a >= 0)
            xcord = a;
        else
            xcord = 0;

        if(b < YMAX && b >= 0)
            ycord = b;
        else
            ycord = 0;
        if (size >= 0)
            maxRadius = size;
        else
            maxRadius = 0;
    }

    int getMaxRadius()
    {
        return this.maxRadius;
    }

    int getXcord()
    {
        return this.xcord;
    }

    int getYcord()
    {
        return this.ycord;
    }

    void setMaxRadius(int input)
    {
        this.maxRadius = input;
    }

    void setXcord(int input)
    {
        this.xcord = input;
    }

    void setYcord(int input)
    {
        this.ycord = input;
    }

    void setCoordinate(int x, int y)
    {
        this.xcord = x;
        this.ycord = y;
    }

    boolean intersect(Coordinate obj1, Coordinate obj2)
    {
        int max1x = obj1.getXcord() + obj1.getMaxRadius();
        int max2x = obj2.getXcord() + obj2.getMaxRadius();
        int max1y = obj1.getYcord() + obj1.getMaxRadius();
        int max2y = obj2.getYcord() + obj2.getMaxRadius();

        int min1x = obj1.getXcord() - obj1.getMaxRadius();
        int min2x = obj2.getXcord() - obj2.getMaxRadius();
        int min1y = obj1.getYcord() - obj1.getMaxRadius();
        int min2y = obj2.getXcord() - obj2.getMaxRadius();

        if(max1x > min2x && max1x < max2x)
        {
            if(max1y > min2y && max1y < max2y)
                return true;
            else
                return false;
        }
        else
            return false;

    }

}
