/**
 * A ChessGame object that controls the flow of the chess game.
 *
 * @author Amy Li
 * @version 1.0
 * @since 2021-06-23
 */

import java.util.*;

class ChessGame {

    private Board board;
    private boolean status;
    private int movesSinceKill;
    private boolean whitesTurn;
    private String winner;
    private final String LEGEND = "\nLegend:\n" +
            "W - White\n" +
            "B - Black\n\n" +
            "P - Pawn\n" +
            "R - Rook\n" +
            "N - Knight\n" +
            "B - Bishop\n" +
            "Q - Queen\n" +
            "K - King";

    /** Creates a new ChessGame with a default Board object.
     * Sets the first turn of the game to white and the number of moves since a piece was taken to zero.
     */

    public ChessGame() {
        this.status = true;
        this.board = new Board();
        this.whitesTurn = true;
        this.movesSinceKill = 0;
    }

    /** Gets the Board
     * @return the Board object
     */

    public Board getBoard() {
        return this.board;
    }

    /** Gets the status of the game
     * @return the current status of the game. true if ongoing and false if a stalemate or checkmate occurs
     */

    public boolean getStatus() {
        return this.status;
    }

    /** Controls the flow of the game. Alternates the turns between the two players and outputs the final result.
     */

    public void play() {

        System.out.println("CHESS");
        System.out.println(this.LEGEND);

        while (status) {
            this.board.printBoard();
            turn();
            this.whitesTurn = !this.whitesTurn; // switch turn
            updateGameStatus(); // checks for stalemate and checkmate
        }

        // outputs final results
        this.board.printBoard();
        getWinner();
    }

    /** Contains the procedure for a single turn
     */

    public void turn() {

        // checks whose turn it is
        King king;
        if (this.whitesTurn) {
            System.out.println("White's Turn:");
            king = this.board.getWhiteKing();
        }
        else {
            System.out.println("Black's Turn:");
            king = this.board.getBlackKing();
        }

        // tells the player if their King is in check
        if (king.isCheck(this.board, king.getRow(), king.getCol())) {
            System.out.println("Your King is in check!");
        }

        // user input for starting and ending positions
        int[] coordinates = getPositionsFromUser();
        Piece selected = this.board.getPiece(coordinates[0], coordinates[1]);

        // prompts player for new positions if the given ones were invalid
        while (!isMoveLegal(selected, coordinates[2], coordinates[3])) {
            System.out.println("Invalid move. Try again.");
            coordinates = getPositionsFromUser();
            selected = this.board.getPiece(coordinates[0], coordinates[1]);
        }

        Piece possiblePiece = this.board.getPiece(coordinates[2], coordinates[3]);
        // checks if the new location is empty
        // if the new location is occupied, it resets the number of moves since a piece was taken
        if (possiblePiece != null) {
            this.movesSinceKill = 0;
        }
        else {
            this.movesSinceKill++;
        }

        // moves the Piece from the first location to the new one
        this.board.movePiece(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);

    }

    /** Asks the user for starting and ending coordinates that exist on the Board
     * @return an int[] of the coordinates in terms of the Board array
     */

    public int[] getPositionsFromUser() {

        Scanner sc = new Scanner(System.in);
        String position;
        String newPosition;

        do {
            System.out.print("Enter the position of the piece you want to move followed by a space and its new position (E.g. A1 A2): ");
            position = sc.next().trim().toUpperCase();
            newPosition = sc.next().trim().toUpperCase();

            while (!this.board.doesCoordinateExist(position) || !this.board.doesCoordinateExist(newPosition)) {
                System.out.print("Enter the position of the piece you want to move followed by a space and its new position (E.g. A1 A2): ");
                position = sc.next().trim().toUpperCase();
                newPosition = sc.next().trim().toUpperCase();
            }

        } while (!isSelectedPieceValid(position));

        // subtracts column letter by 'A' since 'A' is row 0 in the board array
        // subtracts row number by 1 since row 1 is row 0 in the board array
        return new int[] {position.charAt(1) - '1', position.charAt(0) - 'A', newPosition.charAt(1) - '1', newPosition.charAt(0) - 'A'};

    }

    /** Checks if the move is legal
     * @param selected a Piece that is to be moved
     * @param newRow the row where the Piece is to be moved
     * @param newCol the column where the Piece is to be moved
     * @return True if the Piece is able to make it to the new position in one move and if the move does not result in their King being placed in check. False otherwise.
     */

    public boolean isMoveLegal(Piece selected, int newRow, int newCol) {

        // checks if the piece can get to the new position in one move
        if (!selected.isMoveValid(this.board, newRow, newCol)) {
            return false;
        }

        // checks if this move will place the player's king in check
        boolean valid = true;

        // creates a copy of the piece in the original and the piece in the new position
        Piece copy = selected;
        Piece possiblePiece = this.board.getPiece(newRow, newCol);

        // stores the original position of the selected piece
        int ogRow = selected.getRow();
        int ogCol = selected.getCol();

        // moves the selected piece to the new position
        this.board.movePiece(ogRow, ogCol, newRow, newCol);

        King king = this.board.getBlackKing();
        if (this.whitesTurn) {
            king = this.board.getWhiteKing();
        }

        // checks if the player's king is in check
        if (king.isCheck(this.board, king.getRow(), king.getCol())) {
            valid = false;
        }

        // switches the selected piece back to its original position
        this.board.movePiece(newRow, newCol, ogRow, ogCol);
        this.board.setPiece(newRow, newCol, possiblePiece);

        return valid;

    }

    /** Checks if the piece selected by the Player is valid
     * @param coordinate the coordinate of the Piece to be moved
     * @return true if a Piece object exists at the specified coordinate in the board and if the Piece belongs to the same side as the player, false otherwise.
     */

    public boolean isSelectedPieceValid(String coordinate) {

        Piece selectedPiece = this.board.getPiece(coordinate.charAt(1) - '1', coordinate.charAt(0) - 'A');

        // checks if a piece exists at the given position
        if (selectedPiece == null) {
            System.out.println("No piece was selected. Try again.");
            return false;
        }

        // checks if the piece is the same colour as the player
        if (selectedPiece.getIsWhite() != this.whitesTurn) {
            System.out.println("You cannot choose a piece that belongs to the other player. Try again.");
            return false;
        }

        return true;
    }

    /** Updates the status of the game.
     * Sets the status of the game to 'false' if a stalemate or checkmate occurs.
     */

    public void updateGameStatus() {

        King king = this.board.getBlackKing();

        if (this.whitesTurn) {
            king = this.board.getWhiteKing();
        }

        this.status = !(stalemate() || checkMate(king));
    }

    /** Checks if a stalemate has been reached
     * @return true if it has been 50 moves since a piece was last taken, and false if not.
     */

    public boolean stalemate() {
        if (this.movesSinceKill >= 50) {
            this.winner = "No one";
            return true;
        }
        return false;
    }

    /** Checks if a player has been checkmated
     * @param king the king of the current player
     * @return true of the player's king is in check and has no possible moves left
     */

    public boolean checkMate(King king) {
        if (king.isCheckmated(this.board)) {

            if (king.getIsWhite()) {
                this.winner = "Checkmate! Black";
            }
            else {
                this.winner = "Checkmate! White";
            }

            return true;
        }
        return false;
    }

    /** Outputs the winner of the game
     */

    public void getWinner() {
        System.out.println(this.winner + " wins!");
    }
}