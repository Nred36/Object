/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.awt.Color;

/**
 *
 * @author naree1878
 */
public class Objectify {

    public int gridX(int x, int m, int p) {
        int pos = (int) Math.floor(x / 31);
        if (m == 3 || (m == 4 && p == 2)) {
            if (pos < 37) {
                return (pos);

            } else {
                return (-1);
            }
        } else {
            if (pos < 36) {
                return (pos);

            } else {
                return (-1);
            }
        }
    }

    public int gridY(int y, int m, int p) {
        int pos = (int) Math.floor(y / 31);
        if (m == 3 || (m == 4 && p == 1)) {
            if (pos < 21) {
                return (pos);

            } else {
                return (-1);
            }
        } else {
            if (pos < 20) {
                return (pos);

            } else {
                return (-1);
            }
        }
    }

    public Color getColour(int i) {
        Color c;
        if (i == 0) {
            c = new Color(0, 128, 0);//grass
        } else if (i == 1) {
            c = new Color(230, 231, 232);//stone
        } else if (i == 2) {
            c = new Color(0, 0, 205);//sea water
        } else if (i == 3) {
            c = new Color(255, 150, 50);//copper
        } else if (i == 4) {
            c = new Color(0, 205, 255);//fresh water
        } else if (i == 5) {
            c = new Color(45, 45, 45);//coal
        } else if (i == 6) {
            c = new Color(127, 207, 12);//forest
        } else if (i == 7) {
            c = new Color(175, 175, 175);//iron
        } else if (i == 8) {
            c = new Color(237, 201, 175);//sand
        } else if (i == 9) {
            c = new Color(200, 20, 93);//start
        } else if (i == 10) {
            c = new Color(102, 32, 92);
        } else {
            c = new Color(0, 0, 0);
        }
        return (c);
    }

    public String getName(int i) {
        String c;
        if (i == 0) {
            c = "Grass";
        } else if (i == 1) {
            c = "Stone";
        } else if (i == 2) {
            c = "Sea";
        } else if (i == 3) {
            c = "Copper";
        } else if (i == 4) {
            c = "Water";
        } else if (i == 5) {
            c = "Coal";
        } else if (i == 6) {
            c = "Forest";
        } else if (i == 7) {
            c = "Iron";
        } else if (i == 8) {
            c = "Sand";
        } else if (i == 9) {
            c = "Start";
        } else if (i == 10) {
            c = "";
        } else {
            c = "";
        }
        return (c);
    }
}
