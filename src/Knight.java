/**
 * A Knight object that is a subclass of Piece, with a colour, type and position in a Board
 *
 * @author Amy Li
 * @version 1.0
 * @since 2021-06-23
 */

class Knight extends Piece {

    /** Creates a Knight object with a color, type and position
     *
     * @param isWhite a boolean to represent if it is a white piece or a black piece
     * @param row the row of the board object where the Knight is found
     * @param col the column of the board object where the Knight is found
     */

    public Knight(boolean isWhite, int row, int col) {
        super(isWhite, row, col);
        this.setType("Knight");
    }

    /** Gets the name of the Piece
     * @return a string containing "N" to represent the Piece, followed by the first letter of its colour
     */

    @Override
    public String toString() {
        if (this.getIsWhite()) {
            return "Nw";
        }
        return "Nb";
    }

    /** checks if the knight is able to move to a specific position in the board
     * @param board the board containing the Piece object
     * @param newRow the row of the new position
     * @param newCol the column of the new position
     * @return true if the knight can move to the new position and the new position is not the same as the original position, and false otherwise
     */

    @Override
    public boolean isMoveValid(Board board, int newRow, int newCol) {

        Piece possiblePiece = board.getPiece(newRow, newCol);
        int changeInRow = Math.abs(newRow - this.getRow());
        int changeInCol = Math.abs(newCol - this.getCol());

        // checks if new position is empty, if not, checks if the piece being attacked is a different colour
        if (possiblePiece != null) {
            if (!isDiffColour(possiblePiece)) {
                return false;
            }
        }

        // returns true if the piece moves two to the left/right and one up/down, or two up/down and one to the left/right
        return (changeInRow == 2 && changeInCol == 1 || changeInRow == 1 && changeInCol == 2);
    }

    /** checks if the path between the current and new positions is blocked
     * @param board the board containing the Piece object
     * @param newRow the row of the new position
     * @param newCol the column of the new position
     * @return always false because the knight can jump over other pieces
     */

    @Override
    public boolean isPathBlocked(Board board, int newRow, int newCol) {
        return false;
    }

}