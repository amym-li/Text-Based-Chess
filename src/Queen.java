/**
 * A Queen object that is a subclass of Piece, with a colour, type and position in a Board
 *
 * @author Amy Li
 * @version 1.0
 * @since 2021-06-23
 */

class Queen extends Piece {

    /** Creates a Piece class with a colour, and position within a Board
     * @param isWhite a boolean to represent if it is a white piece or a black piece
     * @param row the row of the board object where the Queen object is found
     * @param col the column of the board object where the Queen object is found
     */

    public Queen(boolean isWhite, int row, int col) {
        super(isWhite, row, col);
        this.setType("Queen");
    }

    /** checks if the Queen is able to move to a specific position in the board
     * @param board the board containing the Piece object
     * @param newRow the row of the new position
     * @param newCol the column of the new position
     * @return true if the Queen can move to the new position and the new position is not the same as the original position, and false otherwise
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

        boolean diagonal = changeInRow != 0 && changeInRow == changeInCol;
        boolean straight = changeInRow == 0 && changeInCol > 0 || changeInCol == 0 && changeInRow > 0;

        if (diagonal || straight) {
            // move is valid if the path is unblocked
            return !isPathBlocked(board, newRow, newCol);
        }
        return false;
    }

    /** checks if the path between the current and new positions is blocked
     * @param board the board containing the Piece object
     * @param newRow the row of the new position
     * @param newCol the column of the new position
     * @return true if the path the Queen takes to the new position is empty, false otherwise.
     */

    @Override
    public boolean isPathBlocked(Board board, int newRow, int newCol) {

        int changeInRow = newRow - this.getRow();
        int changeInCol = newCol - this.getCol();

        // checks the path of diagonal moves
        if (Math.abs(changeInRow) == Math.abs(changeInCol)) {

            // stores the direction of movement (+1 for up and right, -1 for down and left)
            int upOrDown = Integer.signum(changeInRow);
            int leftOrRight = Integer.signum(changeInCol);

            for (int i = 1; i < Math.abs(changeInRow); i++) {
                if (board.getPiece(this.getRow()+(i*upOrDown), this.getCol()+(i*leftOrRight)) != null) {
                    return true;
                }
            }
        }

        // checks the path of straight moves
        else {

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
        }

        return false;
    }

}