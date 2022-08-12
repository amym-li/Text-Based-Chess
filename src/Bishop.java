/**
 * A bishop object that is a subclass of Piece, with a colour, type and position in a Board
 *
 * @author Amy Li
 * @version 1.0
 * @since 2021-06-23
 */

class Bishop extends Piece {

    /**
     * Creates a Bishop object with a color, type and position
     *
     * @param isWhite a boolean to represent if it is a white piece or a black piece
     * @param row     the row of the board object where the bishop is found
     * @param col     the column of the board object where the bishop is found
     */

    public Bishop(boolean isWhite, int row, int col) {
        super(isWhite, row, col);
        this.setType("Bishop");
    }

    /**
     * checks if the bishop is able to move to a specific position in the board
     *
     * @param board  the board containing the Piece object
     * @param newRow the row of the new position
     * @param newCol the column of the new position
     * @return true if the bishop can move to the new position and the new position is not the same as the original position, and false otherwise
     */

    @Override
    public boolean isMoveValid(Board board, int newRow, int newCol) {

        Piece possiblePiece = board.getPiece(newRow, newCol);
        int changeInRow = newRow - this.getRow();
        int changeInCol = newCol - this.getCol();

        // returns false if the piece stays in the same position or does not move diagonally
        if (changeInRow == 0 || Math.abs(changeInRow) != Math.abs(changeInCol)) {
            return false;
        }

        // checks if the piece is attacking a piece of the same colour
        if (possiblePiece != null) {
            if (!isDiffColour(possiblePiece)) {
                return false;
            }
        }

        // move is valid if the path is not blocked
        return !isPathBlocked(board, newRow, newCol);
    }

    /**
     * checks if the path between the current and new positions is blocked
     *
     * @param board  the board containing the Piece object
     * @param newRow the row of the new position
     * @param newCol the column of the new position
     * @return true if the path the Bishop takes to the new position is empty, false otherwise.
     */

    @Override
    public boolean isPathBlocked(Board board, int newRow, int newCol) {

        int changeInRow = newRow - this.getRow();
        int changeInCol = newCol - this.getCol();

        // stores the direction of movement (+1 for up and right, -1 for down and left)
        int upOrDown = Integer.signum(changeInRow);
        int leftOrRight = Integer.signum(changeInCol);

        for (int i = 1; i < Math.abs(changeInRow); i++) {
            if (board.getPiece(this.getRow() + (i * upOrDown), this.getCol() + (i * leftOrRight)) != null) {
                return true;
            }
        }

        return false;
    }
}