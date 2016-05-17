/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author naree1878
 */
public class Object extends JApplet implements ActionListener, KeyListener, MouseListener, MouseMotionListener {

    Objectify m = new Objectify();
    Graphics2D myPic;
    Image dbImage, master;
    private Graphics dbg;
    Timer timer;
    int x, y, mX, mY, col;
    String[] picz = new String[5];
    Image[] img = new Image[5];

    int[][][] grid = new int[36][20][1];

    public Object() {//program name

        timer = new Timer(60, this);
        timer.setInitialDelay(100);     //starts timer
        timer.start();
        /**
         * @param args the command line arguments
         */

        try {//READ
            FileReader fr = new FileReader("save.txt"); //reads from text file (located in "files"
            BufferedReader br = new BufferedReader(fr);
            //read and puts each line in the text document into a variable
            for (int i = 0; i < picz.length; i++) {
                picz[i] = br.readLine();
                img[i] = new ImageIcon(picz[i]).getImage();
            }
            fr.close();
            br.close();
        } catch (IOException a) {
            System.out.println("Couldn't Load");//if it fails
        }

        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);

        Timer run = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        }
        );
        run.setRepeats(true);
        run.start();
    }

    public static void main(String[] args) {
        JFrame f = new JFrame(""); //name on program
        JApplet applet = new Object();        //sets up the window
        f.getContentPane().add("Center", applet);
        applet.init();
        f.pack();

        f.setVisible(true); //makes it visible
        f.setResizable(false);//makes in unsizable
        f.setBounds(25, 25, 1185, 649);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //stops program if you x out the window
    }
// <editor-fold defaultstate="collapsed" desc="paint">

    public void paint(Graphics g) {
        dbImage = createImage(getWidth(), getHeight());      //creats and image the size of the screen
        dbg = dbImage.getGraphics();        //double buffers the panel
        paintComponent(dbg);
        g.drawImage(dbImage, 0, 0, this);
    }
// </editor-fold>

    public void paintComponent(Graphics g) {
        myPic = (Graphics2D) g;
        for (int c = 0; c < 36; c++) {
            for (int r = 0; r < 20; r++) {
                myPic.setColor(Color.black);
                myPic.drawRect(c * 31, r * 31, 31, 31);

                myPic.setColor(m.getColour(grid[c][r][0]));
                myPic.fillRect(c * 31 + 1, r * 31 + 1, 30, 30);

            }
        }
        for (int i = 5; i < 15; i++) {
            myPic.setColor(Color.black);
            myPic.drawRect(1147, i * 31, 31, 31);

            myPic.setColor(m.getColour(i - 5));
            myPic.fillRect(1148, i * 31 + 1, 30, 30);
        }

        myPic.setColor(Color.black);
        myPic.drawRect(1127, 485, 51, 17);
        myPic.drawString("Wipe", 1140, 498);
        myPic.drawRect(1127, 512, 51, 17);
        myPic.drawString("Save", 1140, 525);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        requestFocus();
        setFocusTraversalKeysEnabled(false);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = m.gridX(e.getX());
        y = m.gridY(e.getY());
        Rectangle m = new Rectangle(e.getX(), e.getY());
        for (int y = 5; y < 15; y++) {
            Rectangle r = new Rectangle(1147, y * 31, 31, 31);
            if (m.intersects(r)) {
                col = y - 5;                
            }
        }
        if (m.intersects(1127, 485, 51, 17)) {
            for (int c = 0; c < 36; c++) {
                for (int r = 0; r < 20; r++) {
                    grid[c][r][0]=0;
                }
            }
        } else if (m.intersects(1127, 512, 51, 17)) {

        }
        if (x != -1 && y != -1) {
            grid[x][y][0] = col;
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
// <editor-fold defaultstate="collapsed" desc="mouse Exit">

    @Override
    public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//</editor-fold>

    @Override
    public void mouseDragged(MouseEvent e) {
        x = m.gridX(e.getX());
        y = m.gridY(e.getY());

        Rectangle m = new Rectangle(e.getX(), e.getY());
        for (int y = 5; y < 15; y++) {
            Rectangle r = new Rectangle(1147, y * 31, 31, 31);
            if (m.intersects(r)) {
                col = y - 5;
            }
        }
        if (x != -1 && y != -1) {
            grid[x][y][0] = col;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
