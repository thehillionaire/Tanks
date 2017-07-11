/* This class demonstrates drawing stuff on a window using the AWT classes.
 * This is the oldest and most painful of the 3 ways to do GUI related things in Java, since each and every aspect has to be laid out.
 * It is used now, pretty much only to draw figures.
 */

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;


//The class that contains the paint method needs to inherit from Component, the class that contains the pain method and binds the component to the window.

class tank {

    protected Coordinate location;
    protected int health;
    protected Color look;

    public Coordinate getCoordinate()
    {
        return location;
    }

    public void setCoordinate(Coordinate c)
    {
        location = c;
    }

    tank(Coordinate start, Color c)
    {
        location = start;
        look = c;
        health = 3;
    }
}


class Coordinate {

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

    public boolean intersect(Coordinate obj1, Coordinate obj2)
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


class game extends Canvas
{

    tank p1 = new tank(new Coordinate(50,50, 30),Color.RED);
    tank p2 = new tank(new Coordinate(200,200, 30),Color.BLUE);
    Coordinate p1location = new Coordinate();
    Coordinate p2location = new Coordinate();

    int myX = p1.getCoordinate().getXcord();
    int myY = p1.getCoordinate().getYcord();
    int yourX = p2.getCoordinate().getXcord();
    int yourY = p2.getCoordinate().getYcord();
    int bullet1X;
    int bullet1Y;
    int bullet2X;
    int bullet2Y;
    boolean shoot1 = false;
    boolean shoot2 = false;

    public game() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                moveIt(evt);
            }

        });
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                ShootIt(evt);
            }

        });
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                moveIt2(evt);
            }

        });
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                ShootIt2(evt);
            }

        });
    }

    public void moveIt2(KeyEvent evt)
    {




        switch (evt.getKeyCode()) {
            case KeyEvent.VK_S:
                if(yourY >= 350)
                {
                    yourY = 350;
                }
                else
                {
                    yourY += 25;
                }
                break;
            case KeyEvent.VK_W:
                if(yourY <= 1)
                {
                    yourY = 1;
                }
                else {
                    yourY -= 25;
                }
                break;
            case KeyEvent.VK_A:
                if(yourX <= 1)
                {
                    yourX = 1;
                }
                else
                {
                    yourX -= 25;
                }
                break;
            case KeyEvent.VK_D:
                if(yourX >= 375)
                {
                    yourX = 375;
                }
                else
                {
                    yourX += 25;
                }

                break;
        }
        p2location.setCoordinate(yourX,yourY);
        p2.setCoordinate(p2location);
        repaint();
    }

    public void moveIt(KeyEvent evt) {




        switch (evt.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                if(myY >= 350)
                {
                    myY = 350;
                }
                else
                {
                    myY += 25;
                }
                break;
            case KeyEvent.VK_UP:
                if(myY <= 1)
                {
                    myY = 1;
                }
                else {
                    myY -= 25;
                }
                break;
            case KeyEvent.VK_LEFT:
                if(myX <= 1)
                {
                    myX = 1;
                }
                else
                {
                    myX -= 25;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(myX >= 375)
                {
                    myX = 375;
                }
                else
                {
                    myX += 25;
                }

                break;
        }
        p1location.setCoordinate(myX,myY);
        p1.setCoordinate(p1location);
        repaint();
    }

    public void ShootIt2(KeyEvent evt)
    {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_R:

                shoot2 = true;
                bullet2X = yourX - 15;
                bullet2Y = yourY + 15;
                System.out.println("shoot 2");
        }
    }

    public void ShootIt(KeyEvent evt)
    {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ENTER:

                shoot1 = true;
                bullet1X = myX + 30;
                bullet1Y = myY+ 15;
                System.out.println("shoot 1");
        }
        //repaint();
       // shoot = false;

    }


        //Here, we just write the commands to draw a bunch of stuff.
    public void paint(Graphics g)
    {

        g.setColor(Color.RED);
        g.fillRect(myX, myY, 30, 30);

        g.setColor(Color.BLUE);
        g.fillRect(p2.getCoordinate().getXcord(), p2.getCoordinate().getYcord(), 30, 30);


        g.setColor(Color.BLACK);
        g.fillRect(320,50 , 20, 275);
        g.fillRect(80,50 , 20, 275);

        if(shoot1 == true)
        {
            g.setColor(Color.BLACK);
            g.fillOval(bullet1X, bullet1Y,5,5);
            bullet1X = bullet1X + 1;

            Coordinate b = new Coordinate(bullet1X, bullet1Y, 5);
            if(b.intersect(b, p2.getCoordinate()) == true)
            {
                g.clearRect(p2.getCoordinate().getXcord(),p2.getCoordinate().getYcord(), 30,30);
                bullet1X = bullet1X -1;
                g.clearRect(bullet1X,bullet1Y,5,5);

            }
            repaint();

        }
         if(shoot2 == true )
         {
             g.setColor(Color.BLACK);
             g.fillOval(bullet2X, bullet2Y,5,5);
             bullet2X = bullet2X - 1;

             Coordinate b = new Coordinate(bullet1X, bullet1Y, 5);
             if(b.intersect(b, p1.getCoordinate()) == true)
             {
                 g.clearRect(p1.getCoordinate().getXcord(),p1.getCoordinate().getYcord(), 30,30);
                 bullet1X = bullet1X + 1;
                 g.clearRect(bullet2X,bullet2Y,5,5);

             }
             repaint();
         }




    }


}


//This is the main class the will contain the Window and all the aspects realted to the Window
// We will draw figures on to the window by creating an object of the class above.
public class MyDrawing
{
    public static void main(String [] args)
    {
        //Create a frame
        JFrame window = new JFrame("Tanks");
        //Make it so that the program dies when we click on the close button
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game ex = new game();
        //Set the boundaries of the window
        window.setBounds(60,60, 400, 400);
        window.getContentPane().setBackground(Color.LIGHT_GRAY);
        //Attach the Canvas (the figures we just described) to the window
        window.getContentPane().add(ex);
        window.setResizable(false);
        //make the window visible
        window.setVisible(true);
        ex.requestFocus();
    }
}








