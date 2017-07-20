/* This class demonstrates drawing stuff on a window using the AWT classes.
 * This is the oldest and most painful of the 3 ways to do GUI related things in Java, since each and every aspect has to be laid out.
 * It is used now, pretty much only to draw figures.
 */

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
//TODO  implement visible score, crashes?

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


class game extends JPanel {

    tank p1 = new tank(new Coordinate(40, 150, 30), Color.RED);
    tank p2 = new tank(new Coordinate(350, 150, 30), Color.BLUE);
    Coordinate p1location = new Coordinate();
    Coordinate p2location = new Coordinate();

    int myX = p1.getCoordinate().getXcord();
    int myY = p1.getCoordinate().getYcord();
    int yourX = p2.getCoordinate().getXcord();
    int yourY = p2.getCoordinate().getYcord();
    int bullet1X = 500;
    int bullet1Y = 500;
    int bullet2X = 500;
    int bullet2Y = 500;
    boolean shoot1 = false;
    boolean shoot2 = false;
    int player1score =0;
    int player2score=0;
    boolean [] keylist = new boolean [10];  //A,S,W,D,R,Left,Down,Up,Right,Enter
    public int getPlayer1score()
    {
        return player1score;
    }
    public int getPlayer2score()
    {
        return player2score;
    }
    JLabel redscoreboard = new JLabel();
    JLabel bluescoreboard = new JLabel( );

    public boolean checkcollision(Coordinate a, Coordinate b, int side1A, int side2A, int side1B, int side2B) {
        Rectangle rect1 = new Rectangle(a.getXcord(), a.getYcord(), side1A, side2A);
        Rectangle rect2 = new Rectangle(b.getXcord(), b.getYcord(), side1B, side2B);
        if (rect1.intersects(rect2)) {
            return true;
        } else
            return false;
    }


    public game() {

        super(new BorderLayout());



        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {   //taken from github example
                getPress(evt);
            }

        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                getRelease(evt);
            }

        });

        redscoreboard.setText("Red Tank Score: " + getPlayer1score() );
        bluescoreboard.setText("Blue Tank Score: " + getPlayer2score() + "\n");
        add(redscoreboard, BorderLayout.NORTH);
        add(bluescoreboard, BorderLayout.SOUTH);


    }

    public void getPress(KeyEvent evt)
    {
        int key = evt.getKeyCode();

        if(key == KeyEvent.VK_A)
        {
            keylist[0] = true;

        }
        else if(key == KeyEvent.VK_S)
        {
            keylist[1] = true;
        }
        else if(key == KeyEvent.VK_W)
        {
            keylist[2] = true;
        }
        else if(key == KeyEvent.VK_D)
        {
            keylist[3] = true;
        }
        else if(key == KeyEvent.VK_R)
        {
            keylist[4] = true;
        }
        else if(key == KeyEvent.VK_LEFT)
        {
            keylist[5] = true;
        }
        else if(key == KeyEvent.VK_DOWN)
        {
            keylist[6] = true;
        }
        else if(key == KeyEvent.VK_UP)
        {
            keylist[7] = true;
        }
        else if(key == KeyEvent.VK_RIGHT)
        {
            keylist[8] = true;
        }
        else if(key == KeyEvent.VK_ENTER)
        {
            keylist[9] = true;
        }

    }

    public void getRelease(KeyEvent evt)
    {
        int key = evt.getKeyCode();

        if(key == KeyEvent.VK_A)
        {
            keylist[0] = false;


        }
        else if(key == KeyEvent.VK_S)
        {
            keylist[1] = false;
        }
        else if(key == KeyEvent.VK_W)
        {
            keylist[2] = false;
        }
        else if(key == KeyEvent.VK_D)
        {
            keylist[3] = false;
        }
        else if(key == KeyEvent.VK_R)
        {
            keylist[4] = false;
        }
        else if(key == KeyEvent.VK_LEFT)
        {
            keylist[5] = false;
        }
        else if(key == KeyEvent.VK_DOWN)
        {
            keylist[6] = false;
        }
        else if(key == KeyEvent.VK_UP)
        {
            keylist[7] = false;
        }
        else if(key == KeyEvent.VK_RIGHT)
        {
            keylist[8] = false;
        }
        else if(key == KeyEvent.VK_ENTER)
        {
            keylist[9] = false;
        }

    }


    public void moveIt2() {

        int lastspotX = yourX;
        int lastspotY = yourY;

       if(keylist[1])     //S, player 2 down
       {
           if (yourY >= 350)
           {
               yourY = 350;
           } else
           {
               yourY += 1;
           }
       }
       if(keylist[2])
       {
           if (yourY <= 1) {
               yourY = 1;
           } else {
               yourY -= 1;
           }
       }
        if(keylist[0])
        {
            if (yourX <= 1)
            {
                yourX = 1;
            } else {
                yourX -= 1;
            }
        }
         if(keylist[3])
         {
             if (yourX >= 375) {
                 yourX = 375;
             } else {
                 yourX += 1;
             }

         }

        repaint();
        p2location.setCoordinate(yourX, yourY);
        if (keylist[0] || keylist[1] || keylist[2] || keylist[3]) {


            if (checkcollision(p2location, new Coordinate(320, 50), 30, 30, 20, 275) || checkcollision(p2location, new Coordinate(80, 50), 30, 30, 20, 275)) {

                yourX = lastspotX;
                yourY = lastspotY;

            }
        }
        p2location.setCoordinate(yourX, yourY);
        p2.setCoordinate(p2location);
        repaint();
    }

    public void moveIt() {

        int lastspotX = myX;
        int lastspotY = myY;


        if(keylist[6])
        {
            if (myY >= 350) {
                myY = 350;
            } else {
                myY += 1;
            }
        }
        if(keylist[7])
        {
            if (myY <= 1) {
                myY = 1;
            } else {
                myY -= 1;
            }
        }
        if(keylist[5])
        {
            if (myX <= 1) {
                myX = 1;
            } else {
                myX -= 1;
            }
        }
        if(keylist[8]) {
            if (myX >= 375) {
                myX = 375;
            } else {
                myX += 1;
            }

        }

        repaint();
        p1location.setCoordinate(myX, myY);
        if (keylist[5] || keylist[6]  || keylist[7]  || keylist[8]  ) {


            if (checkcollision(p1location, new Coordinate(320, 50), 30, 30, 20, 275) || checkcollision(p1location, new Coordinate(80, 50), 30, 30, 20, 275)) {

                myX = lastspotX;
                myY = lastspotY;

            }
        }
        p1location.setCoordinate(myX, myY);
        p1.setCoordinate(p1location);
        repaint();
    }

    public void ShootIt2() {

        if(keylist[4])
        {

                shoot2 = true;
                bullet2X = yourX - 15;
                bullet2Y = yourY + 15;

        }}


    public void ShootIt() {
       if(keylist[9])
       {

                shoot1 = true;
                bullet1X = myX + 30;
                bullet1Y = myY + 15;


        //repaint();
        // shoot = false;

    }}

    public void Checkwallcollision(tank t) {

    }

    //Here, we just write the commands to draw a bunch of stuff.

    public void newgame(Graphics g)
    {
        p1 = new tank(new Coordinate(40, 150, 30), Color.RED);
        p2 = new tank(new Coordinate(350, 150, 30), Color.BLUE);
        p1location = new Coordinate();
        p2location = new Coordinate();

        myX = p1.getCoordinate().getXcord();
        myY = p1.getCoordinate().getYcord();
        yourX = p2.getCoordinate().getXcord();
        yourY = p2.getCoordinate().getYcord();
        bullet1X = 500;
        bullet1Y = 500;
        bullet2X = 500;
        bullet2Y = 500;
        shoot1 = false;
        shoot2 = false;
        redscoreboard.setText("Red Tank Score: " + getPlayer1score() );
        bluescoreboard.setText("Blue Tank Score: " + getPlayer2score() + "\n");
        for(int i = 0; i < 10; i++)
        {
            keylist[i] = false;
        }

        g.setColor(Color.RED);
        g.fillRect(myX, myY, 30, 30);

        g.setColor(Color.BLUE);
        g.fillRect(p2.getCoordinate().getXcord(), p2.getCoordinate().getYcord(), 30, 30);


        g.setColor(Color.BLACK);
        g.fillRect(320, 50, 20, 275);
        g.fillRect(80, 50, 20, 275);
        repaint();
    }

    public void paintComponent(Graphics g) {

        g.setColor(Color.RED);
        g.fillRect(myX, myY, 30, 30);

        g.setColor(Color.BLUE);
        g.fillRect(p2.getCoordinate().getXcord(), p2.getCoordinate().getYcord(), 30, 30);


        g.setColor(Color.BLACK);
        g.fillRect(320, 50, 20, 275);
        g.fillRect(80, 50, 20, 275);

        moveIt();
        moveIt2();
        ShootIt();
        ShootIt2();
        repaint();

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


            player1score++;
            newgame(g);
            System.out.println("Tank 1: "+player1score);
        }
        if (checkcollision(c, p1.getCoordinate(), 5, 5, 30, 30) == true)

        {
            System.out.println("collision detected! Player 2 shot Player 1r");

            player2score++;
            newgame(g);
            System.out.println("Tank 2: "+player2score);

        }
        repaint();

        if (checkcollision(b, new Coordinate(320, 50), 5, 5, 20, 275) || checkcollision(b, new Coordinate(80, 50), 5, 5, 20, 275))
        {
            bullet1X = 500;
            bullet1Y = 500;
            g.clearRect(bullet1X, bullet1Y, 5, 5);

        }

        if (checkcollision(c, new Coordinate(320, 50), 5, 5, 20, 275) || checkcollision(c, new Coordinate(80, 50), 5, 5, 20, 275))
        {

            bullet2Y = 500;
            bullet2X = 500;
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
        window.setBounds(0,0, 400, 400);
        window.getContentPane().setBackground(Color.LIGHT_GRAY);
        //Attach the Canvas (the figures we just described) to the window
        window.getContentPane().add(ex);
        window.setResizable(false);
        //make the window visible
        window.setVisible(true);
        ex.requestFocus();


    }
}







