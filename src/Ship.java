/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author patev6618
 */
public class Ship {

    private Coordinate[] coords;
    private int hitsLeft;
    private boolean[] hitSpot;

    //constructor
    public Ship(Coordinate[] spots) {
        this.coords = spots;
        //hits = number of coordinates
        hitsLeft = this.coords.length;
        //initialize boat with no hits
        this.hitSpot = new boolean[this.hitsLeft];
        for (int i = 0; i < this.hitsLeft; i++) {
            this.hitSpot[i] = false;

        }
    }

    public boolean doesHit(int row, int col) {
        for (int i = 0; i < this.coords.length; i++) {
            //take a ship coordinate
            Coordinate c = this.coords[i];
            //check for a match
            if (c.getRow() == row && c.getCol() == col) {
                //if so - HIT!
                return true;
            }
        }
        //didnt find coordinate - MISS!
        return false;
    }

    public boolean isSunk() {
        return this.hitsLeft == 0;
    }

    public void shoot(int row, int col) {
        //go thorugh and find the spot on boat
        for (int i = 0; i < this.coords.length; i++) {
            Coordinate c = this.coords[i];
            //is this the coordiante
            if (c.getRow() == row && c.getCol() == col) {
                //make sure we didnt hit this already 
                if (this.hitSpot[i] == false) {
                    //register a hit
                    this.hitSpot[i] = true;
                    this.hitsLeft--;
                    break;
                }
            }

        }

    }

}
