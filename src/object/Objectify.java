/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

/**
 *
 * @author naree1878
 */
public class Objectify {

    public int gridX(int x, int i) {
        int pos = (int) Math.floor(x / 31);
        if (pos < 38) {
            return (pos);
            
        } else {
            return (i);
        }
    }
     public int gridY(int y, int i) {
        int pos = (int) Math.floor(y / 31);
        if (pos < 20) {
            return (pos);
            
        } else {
            return (i);
        }
    }
}
