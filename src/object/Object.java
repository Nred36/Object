/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.awt.Color;
import java.awt.Font;
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
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
    int x, y, mX, mY, col, mode, p = 1, turn = 0;
    String[] picz = new String[5];
    Image[] img = new Image[5];

    int[][][] grid = new int[37][21][5];

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
            br.readLine();
            for (int c = 0; c < 37; c++) {
                for (int r = 0; r < 21; r++) {
                    String temp = br.readLine();
                    String[] tempArray = temp.split(",");
                    for (int i = 0; i < tempArray.length; i++) {
                        grid[c][r][i] = Integer.parseInt(tempArray[i]);
                    }
                }
            }
            fr.close();
            br.close();
        } catch (IOException a) {
            System.out.println("Couldn't Load");//if it fails
        }

        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);

        Timer run = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                turn++;
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
        f.setBounds(25, 10, 1185, 711);
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
        /*
         mode 0: menu
         mode 1: game
         mode 2: map
         mode 3; place
         */
        if (mode != 0) {
            for (int c = 0; c < 37; c++) {
                for (int r = 0; r < 21; r++) {
                    if (c < 36 && r < 20) {
                        myPic.setColor(Color.black);
                        myPic.drawRect(c * 31, r * 31, 31, 31);

                        myPic.setColor(m.getColour(grid[c][r][0]));
                        myPic.fillRect(c * 31 + 1, r * 31 + 1, 30, 30);
                    }
                    myPic.setColor(Color.black);
                    if (grid[c][r][1] == 1) {
                        myPic.fillRect(c * 31 - 6, r * 31 - 6, 15, 15);
                    }
                    if (grid[c][r][2] == 1) {
                        myPic.fillRect(c * 31, r * 31 - 4, 32, 9);
                    } else if (grid[c][r][2] == 2) {
                        myPic.fillRect(c * 31 - 4, r * 31, 9, 32);
                    } else if (grid[c][r][2] == 3) {
                        myPic.fillRect(c * 31, r * 31 - 4, 32, 9);
                        myPic.fillRect(c * 31 - 4, r * 31, 9, 32);
                    }
                }
            }
        }
        if (mode == 2) {
            for (int i = 4; i < 15; i++) {
                myPic.setColor(Color.black);
                myPic.drawRect(1147, i * 31, 31, 31);

                myPic.setColor(m.getColour(i - 4));
                myPic.fillRect(1148, i * 31 + 1, 30, 30);
            }
            myPic.setColor(Color.black);
            myPic.drawRect(1127, 485, 51, 17);
            myPic.drawString("Wipe", 1140, 498);
            myPic.drawRect(1127, 512, 51, 17);
            myPic.drawString("Save", 1140, 525);

            myPic.drawRect(mX - 3, mY - 3, 16, 16);
            myPic.setColor(m.getColour(col));
            myPic.fillRect(mX - 2, mY - 2, 15, 15);
        } else if (mode == 3) {
            myPic.setColor(Color.black);
            myPic.fillRect(x * 31 - 6, y * 31 - 6, 15, 15);
        } else if (mode == 4) {
            myPic.setColor(Color.black);
            if (p == 1) {
                myPic.fillRect(x * 31, y * 31 - 4, 32, 9);
            } else {
                myPic.fillRect(x * 31 - 4, y * 31, 9, 32);
            }
        }
        myPic.setColor(Color.black);
        myPic.drawString("Turn: " + turn, 1120, 15);
        myPic.setFont(new Font("Dialog", Font.PLAIN, 15));
        myPic.drawString("Inventory: ", 5, 636);
        myPic.drawRect(2, 622, 390, 58);
        myPic.fillRect(3 * 31 - 15, 20 * 31 + 5, 15, 15);
        myPic.drawRect(394, 622, 390, 58);
        myPic.drawRect(786, 622, 391, 58);

        myPic.setColor(Color.white);
        myPic.fillRect(3, 623, 389, 57);
        myPic.fillRect(395, 623, 389, 57);
        myPic.fillRect(787, 623, 390, 57);

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
        if (e.getKeyCode() == KeyEvent.VK_0) {
            mode = 0;
        } else if (e.getKeyCode() == KeyEvent.VK_1) {
            mode = 1;
        } else if (e.getKeyCode() == KeyEvent.VK_2) {
            mode = 2;
        } else if (e.getKeyCode() == KeyEvent.VK_3) {
            mode = 3;
        } else if (e.getKeyCode() == KeyEvent.VK_4) {
            mode = 4;
        } else if (e.getKeyCode() == KeyEvent.VK_5) {
            mode = 5;
        } else if (e.getKeyCode() == KeyEvent.VK_6) {

        } else if (e.getKeyCode() == KeyEvent.VK_7) {

        }
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
        x = m.gridX(e.getX(), mode, p);
        y = m.gridY(e.getY(), mode, p);

        if (mode == 0) {
            mode = 1;
        } else if (mode == 1) {

        } else if (mode == 2 && e.getButton() == 1) {
            Rectangle m = new Rectangle(e.getX(), e.getY(), 1, 1);
            for (int y = 5; y < 15; y++) {
                Rectangle r = new Rectangle(1147, y * 31, 31, 31);
                if (m.intersects(r)) {
                    col = y - 4;
                }
            }
            Rectangle r1 = new Rectangle(1127, 485, 51, 17);
            Rectangle r2 = new Rectangle(1127, 512, 51, 17);
            if (m.intersects(r1)) {
                for (int c = 0; c < 36; c++) {
                    for (int r = 0; r < 20; r++) {
                        grid[c][r][0] = 0;
                    }
                }
                System.out.println("Wiped");
            } else if (m.intersects(r2)) {
                try {
                    FileReader fr = new FileReader("save.txt"); //reads from text file (located in "files"
                    BufferedReader br = new BufferedReader(fr);
                    //read and puts each line in the text document into a variable

                    FileWriter fw = new FileWriter("save.txt");//set place to write to in "Files"
                    PrintWriter pw = new PrintWriter(fw); //starts writing
                    for (int i = 0; i < picz.length; i++) {
                        pw.println(picz[i]);
                    }
                    pw.println("res, city, road");
                    for (int c = 0; c < 37; c++) {
                        for (int r = 0; r < 21; r++) {
                            pw.println(grid[c][r][0] + ",0,0");
                        }
                    }
                    pw.close();
                    fw.close();
                    fr.close();
                    br.close();
                    System.out.println("Saved");
                } catch (IOException a) {
                    System.out.println("ERROR");
                }
            }
            if (x != -1 && y != -1) {
                grid[x][y][0] = col;
            }
        } else if (mode == 3 && e.getButton() == 1) {
            if (x != -1 && y != -1) {
                grid[x][y][1] = 1;
            }
        } else if (mode == 4) {
            if (e.getButton() == 1) {
                if (x != -1 && y != -1) {
                    if ((grid[x][y][2] == 1 && p == 2) || (grid[x][y][2] == 2 && p == 1)) {
                        grid[x][y][2] = 3;
                    } else {
                        grid[x][y][2] = p;
                    }
                }
            } else if (e.getButton() == 3) {
                if (p == 1) {
                    p = 2;
                } else if (p == 2) {
                    p = 1;
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e
    ) {
    }

    @Override
    public void mouseEntered(MouseEvent e
    ) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
// <editor-fold defaultstate="collapsed" desc="mouse Exit">

    @Override
    public void mouseExited(MouseEvent e
    ) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//</editor-fold>

    @Override
    public void mouseDragged(MouseEvent e
    ) {
        mX = e.getX();
        mY = e.getY();
        x = m.gridX(e.getX(), mode, p);
        y = m.gridY(e.getY(), mode, p);

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
    public void mouseMoved(MouseEvent e
    ) {
        mX = e.getX();
        mY = e.getY();
        x = m.gridX(e.getX(), mode, p);
        y = m.gridY(e.getY(), mode, p);
    }
}
