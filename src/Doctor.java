
/** This class models the Doctor in the game. A Doctor has
 *  a position and can move to a new position.
 */
public class Doctor {
    
    //instance variables
    private Board board;
    private int row, col;

    /**
     * Initializes the variables for a Doctor.
     *
     * @param theRow The row this Doctor starts at.
     * @param theCol The column this Doctor starts at.
     */
    //constructor to call when doctor is made
    public Doctor(int theRow, int theCol) {
        this.row = theRow;
        this.col = theCol;
    }

    /**
     * Move the Doctor. If the player clicks on one of the squares immediately
     * surrounding the Doctor, the peg is moved to that location. Clicking on
     * the Doctor does not move the peg, but instead allows the Doctor to wait
     * in place for a turn. Clicking on any other square causes the Doctor to
     * teleport to a random square (perhaps by using a �sonic screwdriver�).
     * Teleportation is completely random.
     *
     * @param newRow The row the player clicked on.
     * @param newCol The column the player clicked on.
     */
    
    public void move(int newRow, int newCol) {
        //if click's row is greater or less than doctors current position by 1 change the row to the click Row
        //if click's row is not surrounding the docotrs current position, teleport somewhere randomly
        if (newRow != row){
            if (newRow == row + 1 || newRow == row - 1) {
                row = newRow;
            }else if (newRow > row + 1 || newRow > row ) {
                row = (int) (Math.random() * (12));
            }
        }
        //if click's col is greater or less than doctors current position by 1 change the col to the click col
        //if click's col is not surrounding the doctors current position, teleport somewhere randomly
       if (newCol != col){
            if (newCol == col + 1 || newCol == col - 1) {
                col = newCol;
            }else if (newCol > col + 1 || newCol > col ) {
                col = (int) (Math.random() * (12));
            }
        
       }
       
       
    }

    /**
     * Returns the row of this Doctor.
     *
     * @return This Doctor's row.
     */
    //accessor methods to get doctors row and col
    public int getRow() {
        return this.row;
    }

    /**
     * Returns the column of this Doctor.
     *
     * @return This Doctor's column.
     */
    public int getCol() {
        return this.col;
    }

}
