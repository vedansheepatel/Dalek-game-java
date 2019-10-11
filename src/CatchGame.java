
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
    //create instance varibales for board, doctor and 3 daleks
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
        //create gameboard
        this.board = new Board(12, 12);
        //randomly generate doctor start position
        int docRow = (int) (Math.random() * (12));
        int docCol = (int) (Math.random() * (12));
        
        //make doctor using doctor class
        this.doc = new Doctor(docRow, docCol);
        
        //randomly generate daleks start positions 
        int dalek1Row = (int) (Math.random() * (12));
        int dalek1Col = (int) (Math.random() * (12));
        int dalek2Row = (int) (Math.random() * (12));
        int dalek2Col = (int) (Math.random() * (12));
        int dalek3Row = (int) (Math.random() * (12));
        int dalek3Col = (int) (Math.random() * (12));
        
        //create 3 daleks using dalek class
        this.dalek1 = new Dalek(dalek1Row, dalek1Col);
        this.dalek2 = new Dalek(dalek2Row, dalek2Col);
        this.dalek3 = new Dalek(dalek3Row, dalek3Col);
        
        //put peg at doctor's and dalek's start positions as their character on screen
        board.putPeg(Color.BLACK, dalek1Row, dalek1Col);
        board.putPeg(Color.BLACK, dalek2Row, dalek2Col);
        board.putPeg(Color.BLACK, dalek3Row, dalek3Col);
        board.putPeg(Color.GREEN, docRow, docCol);
        
        if (dalek1Row == dalek2Row && dalek1Col == dalek2Col){
            board.putPeg(Color.RED, dalek1.getRow(), dalek1.getCol());
            dalek1.crash();
            dalek2.crash();
        }
        if (dalek1Row == dalek3Row && dalek1Col == dalek3Col){
            board.putPeg(Color.RED, dalek3.getRow(), dalek3.getCol());
            dalek1.crash();
            dalek3.crash();
        }
        if (dalek3Row == dalek2Row && dalek3Col == dalek2Col){
            board.putPeg(Color.RED, dalek3.getRow(), dalek3.getCol());
            dalek3.crash();
            dalek2.crash();
        }
       
        
    }

    /**
     * The playGame method begins and controls a game: deals with when the user
     * selects a square, when the Daleks move, when the game is won/lost.
     */
    public void playGame() {
        
        //set win and dead boolean to false
        boolean win = false;
        boolean dead = false;
        //in order to play the game tell the player to click the board
        board.displayMessage("Click on the board");
        
        //while the player hasn't won or died keep getting clicks on board
        while (!win && !dead ) {
            //get click
            if ((doc.getRow() == dalek1.getRow() && doc.getCol() == dalek1.getCol()) || (doc.getRow() == dalek2.getRow() && doc.getCol() == dalek2.getCol()) || (doc.getRow() == dalek3.getRow() && doc.getCol() == dalek3.getCol())){
                board.displayMessage("You have lost");
                board.putPeg(Color.YELLOW, doc.getRow(), doc.getCol());
                dead = true;
            }
            Coordinate click = board.getClick();
            int docRow = click.getRow();
            int docCol = click.getCol();
            
            //remove previous position of doctor and move the doctor to the click position
            board.removePeg(doc.getRow(), doc.getCol());
            doc.move(docRow, docCol);
            board.putPeg(Color.GREEN, doc.getRow(), doc.getCol());
            
            //remove previous positions of the daleks and move them towards the doctor
            board.removePeg(dalek1.getRow(), dalek1.getCol());
            board.removePeg(dalek2.getRow(), dalek2.getCol());
            board.removePeg(dalek3.getRow(), dalek3.getCol());
           
            dalek1.advanceTowards(doc);
            dalek2.advanceTowards(doc);
            dalek3.advanceTowards(doc);
            
            //put pegs for daleks at new positon
            board.putPeg(Color.BLACK, dalek1.getRow(), dalek1.getCol());
            board.putPeg(Color.BLACK, dalek2.getRow(), dalek2.getCol());
            board.putPeg(Color.BLACK, dalek3.getRow(), dalek3.getCol());
            
           
            //if dalek1 and dalek2 position is the same then make them both crashed aka dead 
            //put red peg to mark the crash location 
            if (dalek1.getRow() == dalek2.getRow() && dalek1.getCol() == dalek2.getCol()) {
            
               board.putPeg(Color.RED, dalek2.getRow(), dalek2.getCol());
                dalek1.crash();
                dalek2.crash();

            }
            //if dalek1 and dalek3 position is the same then make them both crashed aka dead 
            //put red peg to mark the crash location 
            if (dalek1.getRow() == dalek3.getRow() && dalek1.getCol() == dalek3.getCol()) {
              
               board.putPeg(Color.RED, dalek3.getRow(), dalek3.getCol());
                dalek1.crash();
                dalek3.crash();
            }
            //if dalek3 and dalek2 position is the same then make them both crashed aka dead 
            //put red peg to mark the crash location 
            if (dalek3.getRow() == dalek2.getRow() && dalek3.getCol() == dalek2.getCol()) {
               
               board.putPeg(Color.RED, dalek3.getRow(), dalek3.getCol());
                dalek3.crash();
                dalek2.crash();

            }
            //if all 3 daleks have crashed together then the player wins
            if (dalek1.hasCrashed() && dalek2.hasCrashed() && dalek3.hasCrashed()){
                win = true;
                board.displayMessage("You have won!");
            }
            //if doctor crashes into a dalek then player dies and loses
            //put yellow peg to mark the location 
            if ((doc.getRow() == dalek1.getRow() && doc.getCol() == dalek1.getCol()) || (doc.getRow() == dalek2.getRow() && doc.getCol() == dalek2.getCol()) || (doc.getRow() == dalek3.getRow() && doc.getCol() == dalek3.getCol())) {
                board.displayMessage("You have lost");
                board.putPeg(Color.YELLOW, doc.getRow(), doc.getCol());
                dead = true;
               
            }
        }
    }

}
