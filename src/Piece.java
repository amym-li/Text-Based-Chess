/**
 * An abstract Piece class - a template for the different chess pieces
 *
 * @author Amy Li
 * @version 1.0
 * @since 2021-06-23
 */


abstract class Piece {

    private String type;
    private boolean isWhite;
    private int row;
    private int col;

    /** Creates a Piece class with a colour, and position within a Board
     * @param isWhite a boolean to represent if it is a white piece or a black piece
     * @param row the row of the board object where the Piece is found
     * @param col the column of the board object where the Piece is found
     */

    public Piece(boolean isWhite, int row, int col) {
        this.isWhite = isWhite;
        this.row = row;
        this.col = col;
    }

    /** Checks the colour of the piece
     * @return true if the piece is white and false if black
     */

    public boolean getIsWhite() {
        return this.isWhite;
    }

    /** Gets the type of Piece it is
     * @return the name of the type of Piece
     */

    public String getType() {
        return this.type;
    }

    /** Sets the type of the Piece
     * @param type the type of Piece ("Pawn", "Knight", "Bishop", "Rook", "Queen", or "King")
     */

    public void setType(String type) {
        this.type = type;
    }

    /** Gets the row of the position of the Piece on the board
     * @return row in the board containing the Piece
     */

    public int getRow() {
        return this.row;
    }

    /** Sets the row of the position of the Piece on the board
     * @param row the new row of the Piece
     */

    public void setRow(int row) {
        this.row = row;
    }

    /** Gets the column of the position of the Piece on the board
     * @return column in the board containing the Piece
     */

    public int getCol () {
        return this.col;
    }

    /** Sets the column of the position of the Piece on the board
     * @param col the new column of the Pieces
     */

    public void setCol(int col) {
        this.col = col;
    }

    /** Gets the name of the Piece
     * @return a string representation of the Piece (the first letter of its type followed by the first letter of its colour)
     */

    @Override
    public String toString() {
        if (this.isWhite) {
            return this.type.charAt(0) + "w";
        }
        return this.type.charAt(0) + "b";
    }

    /** An abstract method for determining if the Piece can move to a given location on the Board
     * @param board the board containing the Piece object
     * @param newRow the row of the new position
     * @param newCol the column of the new position
     * @return true if the piece can move to the new position and the new position is not the same as the original position, and false otherwise
     */

    abstract boolean isMoveValid(Board board, int newRow, int newCol);

    /** An abstract method for checking if the path between the current and new positions is blocked
     * @param board the board containing the Piece object
     * @param newRow the row of the new position
     * @param newCol the column of the new position
     * @return true if the path the Piece takes to the new position is empty, false otherwise.
     */

    abstract boolean isPathBlocked(Board board, int newRow, int newCol);

    /** Checks if two pieces have different colours
     * @param toBeEaten the piece being compared to
     * @return true if they have different colours and false if they have the same colour
     */
    public boolean isDiffColour(Piece toBeEaten) {
        return toBeEaten.getIsWhite() != this.getIsWhite();
    }

}