
/** This class models a Delek in the game. A Delek has
 *  a position and can advance towards the Doctor.
 */
public class Dalek {
    //instance variables
    private int row, col;
    private boolean hasCrashed;

    /**
     * Initializes the variables for a Dalek.
     *
     * @param theRow The row this Dalek starts at.
     * @param theCol The column this Dalek starts at.
     */
    //constructor to call when dalek is made
    public Dalek(int theRow, int theCol) {
        this.row = theRow;
        this.col = theCol;
        this.hasCrashed = false;
    }

    /**
     * Attempts to move the Dalek towards the Doctor by the most direct route,
     * moving up, down, right, left or diagonally. For example, if the Doctor is
     * above and to the right of a Dalek, it will move diagonally. If the Doctor
     * is directly below a Dalek, it will move down.
     *
     * @param doc The Doctor to move towards.
     */
    public void advanceTowards(Doctor doc) {
        //if the dalek is still alive
        //if doctors row is greater than the doctor, increase dalek's row by 1 to move closer to doctor
        if (!this.hasCrashed) {
            if (doc.getRow() > row) {
                row++;
            }
            //if doctors row is less than the doctor, decrease dalek's row by 1 to move closer to doctor
            if (doc.getRow() < row) {
                row--;
            }
             //if doctors col is greater than the doctor, increase dalek's col by 1 to move closer to doctor
            if (doc.getCol() > col) {
                col++;
            }
             //if doctors col is less than the doctor, decrease dalek's col by 1 to move closer to doctor
            if (doc.getCol() < col) {
                col--;
            }
            
        }

    }

    /**
     * Returns the row of this Dalek.
     *
     * @return This Dalek's row.
     */
    //accessor method to get dalek's row and col
    public int getRow() {
        return this.row;
    }

    /**
     * Returns the column of this Dalek.
     *
     * @return This Dalek's column.
     */
    public int getCol() {
        return this.col;
    }

    /**
     * Sets the Dalek to be in a crashed state.
     */
    //get state of dalek
    //if crash then dalek is dead
    public void crash() {
        this.hasCrashed = true;
    }

    /**
     * Returns whether or not this Dalek has crashed.
     *
     * @return true if this Dalek has crashed, false otherwise
     */
    //returns true if dalek has died
    public boolean hasCrashed() {
        return this.hasCrashed;
    }

}
