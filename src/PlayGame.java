
import java.awt.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author patev6618
 */
public class PlayGame {

    private Board board;
    private Ship[] ships;

    public PlayGame() {
        //create 10 x 10 board
        this.board = new Board(10, 10);

        //collision detection
        boolean[][] grid = new boolean[10][10];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = false;

            }

        }

        this.ships = new Ship[2];
        //for each ship
        for (int i = 0; i < this.ships.length; i++) {
            int shipSize = (int) (Math.random() * 4 + 2);
            //boolean to control ship coord generation
            boolean success = false;
            while (!success) {
                int direction = (int) (Math.random() * 2);
                //generate random row and col
                int startRow = (int) (Math.random() * 10);
                int startCol = (int) (Math.random() * 10);
                //make sure it fits right - left
                if (direction == 0 && startCol + shipSize < 10) {

                    //check it doesnt hit another ship
                    boolean allGood = true;
                    for (int j = 0; j < shipSize; j++) {
                        int row = startRow;
                        int col = startCol + j;
                        if (grid[row][col]) {
                            allGood = false;
                            break;
                        }
                    }
                    //if we are good to go make the ship
                    if (allGood) {
                        Coordinate[] spots = new Coordinate[shipSize];
                        for (int j = 0; j < shipSize; j++) {
                            if (direction == 0) {
                                int row = startRow;
                                int col = startCol + j;
                                //flag spot as used
                                grid[row][col] = true;
                                //set coordinates of the ship 
                                Coordinate c = new Coordinate(row, col);
                                spots[j] = c;
                            }
                        }
                        //make the ship
                        Ship s = new Ship(spots);
                        //put it in the ships array
                        this.ships[i] = s;
                        success = true;
                    }
                } else if (direction == 1 && startRow + shipSize < 10) {
                    //check it doesnt hit another ship
                    boolean allGood = true;
                    for (int j = 0; j < shipSize; j++) {
                        int row = startRow + j;
                        int col = startCol;
                        if (grid[row][col]) {
                            allGood = false;
                            break;
                        }
                    }
                    //if we are good to go make the ship
                    if (allGood) {
                        Coordinate[] spots = new Coordinate[shipSize];
                        for (int j = 0; j < shipSize; j++) {
                            if (direction == 1) {
                                int row = startRow + j;
                                int col = startCol;
                                //flag spot as used
                                grid[row][col] = true;
                                //set coordinates of the ship 
                                Coordinate c = new Coordinate(row, col);
                                spots[j] = c;
                            }
                        }
                        //make the ship
                        Ship s = new Ship(spots);
                        //put it in the ships array
                        this.ships[i] = s;
                        success = true;
                    }
                }
            }

        }
    }

    public void playGame() {
        //keep track of sunk ships
        boolean win = false;
        while (!win) {
            //get a click
            Coordinate click = board.getClick();
            int shotRow = click.getRow();
            int shotCol = click.getCol();
            //see if it hit a boat
            //for each ship in the ships array
            //does shot hit boat s 
            boolean hit = false;
            for (Ship s : this.ships) {
                if (s.doesHit(shotRow, shotCol)) {
                    //registetr a hit
                    s.shoot(shotRow, shotCol);
                    hit = true;
                }
            }
            if (hit) {
                board.putPeg(Color.RED, shotRow, shotCol);

            } else {
                board.putPeg(Color.WHITE, shotRow, shotCol);
            }
        }
        int shipSunk = 0;
        for (Ship s : this.ships) {
            if (s.isSunk()) {
                shipSunk++;
            }
        }
        if (shipSunk == this.ships.length) {
            win = true;
        }
    }
}
