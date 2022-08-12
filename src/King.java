/**
 * A King object that is a subclass of Piece, with a colour, type and position in a Board
 *
 * @author Amy Li
 * @version 1.0
 * @since 2021-06-23
 */

class King extends Piece {

    // King only has 9 possible movements
    private final int[][] POSSIBLE_MOVES = {{0, 1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {1, -1}, {1, 0}, {1, 1}};

    /** Creates a King object with a color, type and position
     *
     * @param isWhite a boolean to represent if it is a white piece or a black piece
     * @param row the row of the board object where the King is found
     * @param col the column of the board object where the King is found
     */

    public King(boolean isWhite, int row, int col) {
        super(isWhite, row, col);
        this.setType("King");
    }

    /** Gets the name of the Piece
     * @return the name of the Piece ("K" followed by the first letter of its colour capitalized)
     */
    @Override
    public String toString() {
        if (this.getIsWhite()) {
            return "KW";
        }
        return "KB";
    }

    /** checks if the King is able to move to a specific position in the board
     * @param board the board containing the Piece object
     * @param newRow the row of the new position
     * @param newCol the column of the new position
     * @return true if the King can move to the new position and the new position is not the same as the original position, and false otherwise
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

        // true if the king only moves 1 space at a time
        if (!(changeInRow == 1 && changeInCol == 1 || changeInRow == 0 && changeInCol == 1 || changeInRow == 1 && changeInCol == 0)) {
            return false;
        }

        // checks if the king is in check
        return !this.isCheck(board, newRow, newCol);

    }

    /** Checks if the path between the current and new positions is blocked
     * @param board the board containing the Piece object
     * @param newRow the row of the new position
     * @param newCol the column of the new position
     * @return always false since the King can only move 1 space at a time
     */

    @Override
    public boolean isPathBlocked(Board board, int newRow, int newCol) {
        return false;
    }

    /** Counts the number of valid moves for the King
     * @param board the Board containing the King
     * @return the number of possible moves the King can make without putting itself in check
     */

    public int numPossibleMoves(Board board) {

        int[][] positions = POSSIBLE_MOVES.clone();

        // calculates all possible new coordinates, including those not on the board
        for (int i = 0; i < POSSIBLE_MOVES.length; i++) {
            positions[i][0]+= this.getRow();
            positions[i][1]+=this.getCol();
        }

        int validMoves = 0; // counts the number of valid positions

        for (int j = 0; j < positions.length; j++) {

            // keeps track of how many valid moves there are
            if (positions[j][0] >= 0 && positions[j][0] < board.getSIZE() && positions[j][1] >= 0 && positions[j][1] < board.getSIZE()) {
                if (this.isMoveValid(board, positions[j][0], positions[j][1]) && !isCheck(board, positions[j][0], positions[j][1])) {
                    validMoves++;
                }
            }
        }

        return validMoves;
    }

    /** Checks if a new position will place the king in check
     * @param board the board containing the King piece
     * @param newRow the row of the new position of the piece
     * @param newCol the column of the new position of the piece
     * @return true if the King will not be in check, false otherwise
     */

    public boolean isCheck(Board board, int newRow, int newCol) {

        boolean check = false;

        // stores the original position of the king
        int ogRow = this.getRow();
        int ogCol = this.getCol();
        Piece possiblePiece = board.getPiece(newRow,newCol);

        // goes through every piece on the board
        for (int i = 0; i < board.getSIZE() && !check; i++) {
            for (int j = 0; j < board.getSIZE() && !check; j++) {

                // king can only be checked by a piece of a different colour
                if (board.getPiece(i, j)!= null) {

                    if(this.getIsWhite() != board.getPiece(i, j).getIsWhite()) {

                        // temporarily switches the king to the new position to check for check
                        if (ogRow != newRow || ogCol != newCol) {
                            // moves the king to the new position
                            board.movePiece(ogRow, ogCol, newRow, newCol);
                        }

                        // if the opponent's piece can move to the new position, it will result in a check
                        if (board.getPiece(i, j).isMoveValid(board, newRow, newCol)) {
                            check = true;
                        }

                        if (ogRow != newRow || ogCol != newCol) {
                            // switches the king back to its original position if a switch took place
                            board.movePiece(newRow, newCol, ogRow, ogCol);
                            board.setPiece(newRow, newCol, possiblePiece);
                        }
                    }
                }
            }
        }

        return check;
    }

    /** Checks if the King is in checkmate
     * @param board the board containing the King piece
     * @return true if the King is in checkmate, false if not
     */

    public boolean isCheckmated(Board board) {

        // king is in checkmate if it is in check and has no valid moves left
        if (this.isCheck(board, this.getRow(), this.getCol()) && numPossibleMoves(board) == 0) {
            return true;
        }
        return false;
    }
}