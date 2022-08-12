/**
 * A Rook object that is a subclass of Piece, with a colour, type and position in a Board
 *
 * @author Amy Li
 * @version 1.0
 * @since 2021-06-23
 */

class Rook extends Piece {

    /** Creates a Rook object with a color, type and position
     *
     * @param isWhite a boolean to represent if it is a white piece or a black piece
     * @param row the row of the board object where the Rook is found
     * @param col the column of the board object where the Rook is found
     */

    public Rook(boolean isWhite, int row, int col) {
        super(isWhite, row, col);
        this.setType("Rook");
    }

    /** checks if the Rook is able to move to a specific position in the board
     * @param board the board containing the Piece object
     * @param newRow the row of the new position
     * @param newCol the column of the new position
     * @return true if the Rook can move to the new position and the new position is not the same as the original position, and false otherwise
     */

    @Override
    public boolean isMoveValid(Board board, int newRow, int newCol) {

        Piece possiblePiece = board.getPiece(newRow, newCol);
        int changeInRow = newRow - this.getRow();
        int changeInCol = newCol - this.getCol();

        // checks if new position is empty, if not, checks if the piece being attacked is a different colour
        if (possiblePiece != null) {
            if (!isDiffColour(possiblePiece)) {
                return false;
            }
        }

        // rook can only move in one direction, either horizontally or vertically, per turn
        if (changeInRow == 0 && Math.abs(changeInCol) > 0 || changeInCol == 0 && Math.abs(changeInRow) > 0) {

            // move is valid if the path is unblocked
            return !isPathBlocked(board, newRow, newCol);
        }
        return false;
    }

    /** checks if the path between the current and new positions is blocked
     *
     * @param board the board containing the Piece object
     * @param newRow the row of the new position
     * @param newCol the column of the new position
     * @return true if the path the Rook takes to the new position is empty, false otherwise.
     */

    @Override
    public boolean isPathBlocked(Board board, int newRow, int newCol) {

        int changeInRow = newRow - this.getRow();
        int changeInCol = newCol - this.getCol();

        // if there is no change in column, the queen moves up and down
        if (changeInCol == 0) {

            // calculates the unit value of the move (+1 for up, -1 for down)
            int unitVertical = changeInRow/Math.abs(changeInRow);

            for (int i = this.getRow()+unitVertical; i != newRow; i+=unitVertical) {
                if (board.getPiece(i, newCol) != null) {
                    return true;
                }
            }
        }

        // if queen moves left and right
        else {

            // calculates the unit value of the move (+1 for right, -1 for left)
            int unitHorizontal = changeInCol/Math.abs(changeInCol);

            for (int i = this.getCol()+unitHorizontal; i != newCol; i+=unitHorizontal) {
                if (board.getPiece(newRow, i) != null) {
                    return true;
                }
            }
        }

        return false;
    }
}