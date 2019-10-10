
import java.awt.Color;

/**
 * This class manages the interactions between the different pieces of the game:
 * the Board, the Daleks, and the Doctor. It determines when the game is over
 * and whether the Doctor won or lost.
 */
public class CatchGame {

    /**
     * Instance variables go up here Make sure to create a Board, 3 Daleks, and
     * a Doctor
     */
    private Board board;

    private Doctor doc;

    private Dalek dalek1;

    private Dalek dalek2;

    private Dalek dalek3;
    

    /**
     * The constructor for the game. Use it to initialize your game variables.
     * (create people, set positions, etc.)
     */
    public CatchGame() {
        this.board = new Board(12, 12);

        int docRow = (int) (Math.random() * (12));
        int docCol = (int) (Math.random() * (12));

        this.doc = new Doctor(docRow, docCol);

        int dalek1Row = (int) (Math.random() * (12));
        int dalek1Col = (int) (Math.random() * (12));
        int dalek2Row = (int) (Math.random() * (12));
        int dalek2Col = (int) (Math.random() * (12));
        int dalek3Row = (int) (Math.random() * (12));
        int dalek3Col = (int) (Math.random() * (12));

        this.dalek1 = new Dalek(dalek1Row, dalek1Col);
        this.dalek2 = new Dalek(dalek2Row, dalek2Col);
        this.dalek3 = new Dalek(dalek3Row, dalek3Col);

        board.putPeg(Color.BLACK, dalek1Row, dalek1Col);
        board.putPeg(Color.BLACK, dalek2Row, dalek2Col);
        board.putPeg(Color.BLACK, dalek3Row, dalek3Col);
        board.putPeg(Color.GREEN, docRow, docCol);
    }

    /**
     * The playGame method begins and controls a game: deals with when the user
     * selects a square, when the Daleks move, when the game is won/lost.
     */
    public void playGame() {
        boolean win = false;
        boolean dead = false;
        board.displayMessage("Click on the board");
        
        while (!win && !dead ) {
            Coordinate click = board.getClick();
            int docRow = click.getRow();
            int docCol = click.getCol();
            board.removePeg(doc.getRow(), doc.getCol());

            doc.move(docRow, docCol);
            board.putPeg(Color.GREEN, doc.getRow(), doc.getCol());

            board.removePeg(dalek1.getRow(), dalek1.getCol());
            board.removePeg(dalek2.getRow(), dalek2.getCol());
            board.removePeg(dalek3.getRow(), dalek3.getCol());
           
            dalek1.advanceTowards(doc);
            dalek2.advanceTowards(doc);
            dalek3.advanceTowards(doc);
            
            board.putPeg(Color.BLACK, dalek1.getRow(), dalek1.getCol());
            board.putPeg(Color.BLACK, dalek2.getRow(), dalek2.getCol());
            board.putPeg(Color.BLACK, dalek3.getRow(), dalek3.getCol());
            
           
            
            if (dalek1.getRow() == dalek2.getRow() && dalek1.getCol() == dalek2.getCol()) {
            
               board.putPeg(Color.RED, dalek2.getRow(), dalek2.getCol());
                dalek1.crash();
                dalek2.crash();

            }
            if (dalek1.getRow() == dalek3.getRow() && dalek1.getCol() == dalek3.getCol()) {
              
               board.putPeg(Color.RED, dalek3.getRow(), dalek3.getCol());
                dalek1.crash();
                dalek3.crash();
            }
            if (dalek3.getRow() == dalek2.getRow() && dalek3.getCol() == dalek2.getCol()) {
               
               board.putPeg(Color.RED, dalek3.getRow(), dalek3.getCol());
                dalek3.crash();
                dalek2.crash();

            }
            if ((doc.getRow() == dalek1.getRow() && doc.getCol() == dalek1.getCol()) || (doc.getRow() == dalek2.getRow() && doc.getCol() == dalek2.getCol()) || (doc.getRow() == dalek3.getRow() && doc.getCol() == dalek3.getCol())) {
                board.displayMessage("You have lost");
                board.putPeg(Color.YELLOW, doc.getRow(), doc.getCol());
               
                dead = true;
               
            }
        }
    }

}
