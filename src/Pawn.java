/**
 * A Pawn object that is a subclass of Piece, with a colour, type and position in a Board
 *
 * @author Amy Li
 * @version 1.0
 * @since 2021-06-23
 */

class Pawn extends Piece {

    private boolean firstMove;

    /** Creates a Piece class with a colour, and position within a Board
     * @param isWhite a boolean to represent if it is a white piece or a black piece
     * @param row the row of the board object where the Queen object is found
     * @param col the column of the board object where the Queen object is found
     */

    public Pawn(boolean isWhite, int row, int col) {
        super(isWhite, row, col);
        this.setType("Pawn");
        this.firstMove = true;
    }

    /** checks if the Pawn is able to move to a specific position in the board
     * @param board the board containing the Piece object
     * @param newRow the row of the new position
     * @param newCol the column of the new position
     * @return true if the Pawn can move to the new position and the new position is not the same as the original position, and false otherwise
     */

    @Override
    public boolean isMoveValid(Board board, int newRow, int newCol) {

        Piece possiblePiece = board.getPiece(newRow, newCol);
        int changeInRow = newRow - this.getRow();
        int changeInCol = newCol - this.getCol();

        boolean isValid = false;

        // when pawn is white and moving forward on the board, the changeInRow is a positive number
        // when pawn is black and moving forward on the board, the changeInRow is a negative number
        // this multiplies changeInRow by -1 so that when the black pawn is moving forward, changeInRow is positive
        if (!this.getIsWhite()) {
            changeInRow*= -1;
        }

        // new position is occupied so this checks if the pawn can attack
        if (possiblePiece != null) {

            // checks if the pawn moves forward diagonally one space
            if (isDiffColour(possiblePiece) && Math.abs(changeInCol) == 1 && changeInRow == 1) {
                isValid = true;
            }
        }

        else {

            // when pawn is not attacking, it's column stays the same and changes row only
            if (changeInCol == 0) {

                // checks if it is the pawn's first move
                if (this.firstMove) {

                    // pawns can only move forward 1 space or 2 spaces
                    if (changeInRow == 1 || (changeInRow == 2 && !isPathBlocked(board, newRow, newCol))) {
                        this.firstMove = false;
                        isValid = true;
                    }
                }

                // if it is not the pawn's first move
                else {
                    if (changeInRow == 1) {
                        isValid = true;
                    }
                }
            }
        }

        return isValid;
    }

    /** Checks if the path of the piece between the starting and end position is blocked
     * @param board the board containing the Piece object
     * @param newRow the row of the new position
     * @param newCol the column of the new position
     * @return true if the path is blocked, and false if not
     */

    @Override
    public boolean isPathBlocked(Board board, int newRow, int newCol) {

        if (this.getIsWhite()) {
            return board.getPiece(newRow - 1, newCol) != null;
        }

        return board.getPiece(newRow + 1, newCol) != null;
    }

}