
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
public class boardexample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //creates a 12 by 12 board
        Board gameBoard = new Board(12, 12);
        //put down a red peg
        gameBoard.putPeg(Color.RED, 5, 2);
        gameBoard.putPeg(Color.RED, 5, 5);
        //remove peg
        gameBoard.removePeg(5, 2);

        //display message on the baord
        gameBoard.displayMessage("Click on the board");
        while (true) {
            //get a click
            Coordinate click = gameBoard.getClick();
            int clickRow = click.getRow();
            int clickCol = click.getCol();
            gameBoard.putPeg(Color.YELLOW, clickRow, clickCol);
        }
    }

}
