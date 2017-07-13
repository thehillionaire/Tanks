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
    //protected int health;
   // protected Color look;

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
      // look = c;
       // health = 3;
    }
}


class game extends Canvas {

    tank p1 = new tank(new Coordinate(50, 50, 30), Color.RED);
    tank p2 = new tank(new Coordinate(200, 200, 30), Color.BLUE);
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

    public boolean checkcollision(Coordinate a, Coordinate b, int side1A, int side2A, int side1B, int side2B) {
        Rectangle rect1 = new Rectangle(a.getXcord(), a.getYcord(), side1A, side2A);
        Rectangle rect2 = new Rectangle(b.getXcord(), b.getYcord(), side1B, side2B);
        if (rect1.intersects(rect2)) {
            return true;
        } else
            return false;
    }

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

    public void moveIt2(KeyEvent evt) {

        int lastspotX = yourX;
        int lastspotY = yourY;

        switch (evt.getKeyCode()) {
            case KeyEvent.VK_S:
                if (yourY >= 350) {
                    yourY = 350;
                } else {
                    yourY += 25;
                }
                break;
            case KeyEvent.VK_W:
                if (yourY <= 1) {
                    yourY = 1;
                } else {
                    yourY -= 25;
                }
                break;
            case KeyEvent.VK_A:
                if (yourX <= 1) {
                    yourX = 1;
                } else {
                    yourX -= 25;
                }
                break;
            case KeyEvent.VK_D:
                if (yourX >= 375) {
                    yourX = 375;
                } else {
                    yourX += 25;
                }

                break;
        }
        repaint();
        p2location.setCoordinate(yourX, yourY);
        if (evt.getKeyCode() == KeyEvent.VK_W || evt.getKeyCode() == KeyEvent.VK_S || evt.getKeyCode() == KeyEvent.VK_A || evt.getKeyCode() == KeyEvent.VK_D) {


            if (checkcollision(p2location, new Coordinate(320, 50), 30, 30, 20, 275) || checkcollision(p2location, new Coordinate(80, 50), 30, 30, 20, 275)) {
                System.out.println("collision detected with wall!");
                yourX = lastspotX;
                yourY = lastspotY;

            }
        }
        p2location.setCoordinate(yourX, yourY);
        p2.setCoordinate(p2location);
        repaint();
    }

    public void moveIt(KeyEvent evt) {

        int lastspotX = myX;
        int lastspotY = myY;


        switch (evt.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                if (myY >= 350) {
                    myY = 350;
                } else {
                    myY += 25;
                }
                break;
            case KeyEvent.VK_UP:
                if (myY <= 1) {
                    myY = 1;
                } else {
                    myY -= 25;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (myX <= 1) {
                    myX = 1;
                } else {
                    myX -= 25;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (myX >= 375) {
                    myX = 375;
                } else {
                    myX += 25;
                }

                break;
        }
        repaint();
        p1location.setCoordinate(myX, myY);
        if (evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_LEFT || evt.getKeyCode() == KeyEvent.VK_RIGHT) {


            if (checkcollision(p1location, new Coordinate(320, 50), 30, 30, 20, 275) || checkcollision(p1location, new Coordinate(80, 50), 30, 30, 20, 275)) {
                System.out.println("collision detected with wall!");
                myX = lastspotX;
                myY = lastspotY;

            }
        }
        p1location.setCoordinate(myX, myY);
        p1.setCoordinate(p1location);
        repaint();
    }

    public void ShootIt2(KeyEvent evt) {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_R:

                shoot2 = true;
                bullet2X = yourX - 15;
                bullet2Y = yourY + 15;

        }
    }

    public void ShootIt(KeyEvent evt) {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_ENTER:

                shoot1 = true;
                bullet1X = myX + 30;
                bullet1Y = myY + 15;

        }
        //repaint();
        // shoot = false;

    }

    public void Checkwallcollision(tank t) {

    }

    //Here, we just write the commands to draw a bunch of stuff.
    public void paint(Graphics g) {

        g.setColor(Color.RED);
        g.fillRect(myX, myY, 30, 30);

        g.setColor(Color.BLUE);
        g.fillRect(p2.getCoordinate().getXcord(), p2.getCoordinate().getYcord(), 30, 30);


        g.setColor(Color.BLACK);
        g.fillRect(320, 50, 20, 275);
        g.fillRect(80, 50, 20, 275);

        if (shoot1 == true) {

            g.fillOval(bullet1X, bullet1Y, 5, 5);
            bullet1X = bullet1X + 1;


        }

        if (shoot2 == true) {
            g.fillOval(bullet2X, bullet2Y, 5, 5);
            bullet2X = bullet2X - 1;


        }

        Coordinate b = new Coordinate(bullet1X, bullet1Y, 5);
        Coordinate c = new Coordinate(bullet2X, bullet2Y, 5);

        if (checkcollision(b, p2.getCoordinate(), 5, 5, 30, 30) == true) {
            System.out.println("collision detected! Player 1 shot Player 2");
            g.clearRect(p2.getCoordinate().getXcord(), p2.getCoordinate().getYcord(), 30, 30);
            bullet1X = bullet1X - 1;
            g.clearRect(bullet1X, bullet1Y, 5, 5);

        }
        if (checkcollision(c, p1.getCoordinate(), 5, 5, 30, 30) == true)

        {
            System.out.println("collision detected! Player 2 shot player 1");
            g.clearRect(p1.getCoordinate().getXcord(), p1.getCoordinate().getYcord(), 30, 30);
            bullet1X = bullet1X;
            g.clearRect(bullet2X, bullet2Y, 5, 5);

        }
        repaint();

        if (checkcollision(b, new Coordinate(320, 50), 5, 5, 20, 275) || checkcollision(b, new Coordinate(80, 50), 5, 5, 20, 275))
        {
            bullet1X = bullet1X - 1;
            g.clearRect(bullet1X, bullet1Y, 5, 5);

        }

        if (checkcollision(c, new Coordinate(80, 50), 5, 5, 20, 275) || checkcollision(c, new Coordinate(80, 50), 5, 5, 20, 275))
        {
            bullet1X = bullet1X + 1;
            g.clearRect(bullet2X, bullet2Y, 5, 5);

        }
        repaint();

    }

}

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







