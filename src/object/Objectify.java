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
            c = new Color(255, 255, 255);
        } else if (i == 1) {
            c = new Color(0, 128, 0);
        } else if (i == 2) {
            c = new Color(0, 0, 205);
        } else if (i == 3) {
            c = new Color(128, 0, 0);
        } else if (i == 4) {
            c = new Color(0, 255, 255);
        } else if (i == 5) {
            c = new Color(255, 0, 255);
        } else if (i == 6) {
            c = new Color(127, 207, 12);
        } else if (i == 7) {
            c = new Color(12, 127, 127);
        } else if (i == 8) {
            c = new Color(127, 12, 127);
        } else if (i == 9) {
            c = new Color(200, 20, 93);
        } else if (i == 10) {
            c = new Color(102, 32, 92);
        } else {
            c = new Color(0, 0, 0);
        }
        return (c);
    }
}
