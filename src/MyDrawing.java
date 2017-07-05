/* This class demonstrates drawing stuff on a window using the AWT classes.
 * This is the oldest and most painful of the 3 ways to do GUI related things in Java, since each and every aspect has to be laid out.
 * It is used now, pretty much only to draw figures.
 */

import java.awt.event.*;
import javax.swing.JFrame;
import java.awt.*;


//The class that contains the paint method needs to inherit from Component, the class that contains the pain method and binds the component to the window.
class MyFigures extends Canvas
{

    int myX = 50;
    int myY = 50;

    public MyFigures() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                moveIt(evt);
            }
        });
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

        repaint();
    }



        //Here, we just write the commands to draw a bunch of stuff.
    public void paint(Graphics g)
    {

        g.setColor(Color.RED);
        g.fillOval(myX, myY, 30, 30);

        g.setColor(Color.BLACK);
        g.fillRect(320,50 , 20, 275);
        g.fillRect(80,50 , 20, 275);






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
        MyFigures ex = new MyFigures();
        //Set the boundaries of the window
        window.setBounds(60,60, 400, 400);
        window.getContentPane().setBackground(Color.LIGHT_GRAY);
        //Attach the Canvas (the figures we just described) to the window
        window.getContentPane().add(ex);
        window.pack();
        window.setResizable(false);
        //make the window visible
        window.setVisible(true);
        ex.requestFocus();
    }
}








