/**
 * A Board object made up of chess Pieces
 *
 * @author Amy Li
 * @version 1.0
 * @since 2021-06-23
 */

import java.util.Scanner;

class Board {

    private final int SIZE = 8;
    private Piece[][] board;
    private int[] wKingIndex;
    private int[] bKingIndex;

    /** Creates an Board object with the standard starting setup of Pieces inside of the 8x8 array
     */

    public Board() {
        this.board = new Piece[SIZE][SIZE];

        // board is oriented such that A1 is at [0][0] of the board array and A8 is at [7][0]
        for (int i = 0; i < SIZE; i++) {
            this.board[1][i] = new Pawn(true, 1, i);
            this.board[6][i] = new Pawn(false, 6, i);
        }

        // white row
        this.board[0][0] = new Rook(true, 0, 0);
        this.board[0][1] = new Knight(true, 0, 1);
        this.board[0][2] = new Bishop(true, 0, 2);
        this.board[0][3] = new Queen(true, 0, 3);
        this.board[0][4] = new King(true, 0, 4);

        this.wKingIndex = new int[]{0, 4}; // tracks the index of the white King

        this.board[0][5] = new Bishop(true, 0, 5);
        this.board[0][6] = new Knight(true, 0, 6);
        this.board[0][7] = new Rook(true, 0, 7);

        // black row
        this.board[7][0] = new Rook(false, 7, 0);
        this.board[7][1] = new Knight(false, 7, 1);
        this.board[7][2] = new Bishop(false, 7, 2);
        this.board[7][3] = new Queen(false, 7, 3);
        this.board[7][4] = new King(false, 7, 4);

        this.bKingIndex = new int[]{7, 4}; // tracks the index of the black King

        this.board[7][5] = new Bishop(false, 7, 5);
        this.board[7][6] = new Knight(false, 7, 6);
        this.board[7][7] = new Rook(false, 7, 7);

    }

    /** Gets the size of the board
     * @return the size of the board
     */

    public int getSIZE() {
        return this.SIZE;
    }

    /** Gets a Piece object from the Board
     *
     * @param row row index of the piece in the board
     * @param col column index of the piece in the board
     * @return the Piece object located at the specified row and column
     */

    public Piece getPiece(int row, int col) {
        return this.board[row][col];
    }

    /** Places a Piece object into the Board array
     *
     * @param row row where the Piece is to be placed
     * @param col column where the Piece is to be placed
     * @param piece the Piece to be placed on the board
     */

    public void setPiece(int row, int col, Piece piece) {
        this.board[row][col] = piece;
    }

    /** Removes a Piece object from the board
     *
     * @param row row of the Piece to be removed
     * @param col column of the Piece to be removed
     */

    public void removePiece(int row, int col) {
        this.board[row][col] = null;
    }

    /** Gets the white King on the board
     * @return a King object containing the white King
     */

    public King getWhiteKing() {
        return (King) this.getPiece(this.wKingIndex[0], this.wKingIndex[1]);
    }

    /** Gets the black King on the board
     * @return a King object containing the black King
     */

    public King getBlackKing() {
        return (King) this.getPiece(this.bKingIndex[0], this.bKingIndex[1]);
    }

    /** Moves a Piece on the board from one position to another
     *
     * @param row starting row of the Piece
     * @param col starting column of the Piece
     * @param newRow row where the Piece is being moved to
     * @param newCol column where the Piece is being moved to
     */

    public void movePiece(int row, int col, int newRow, int newCol) {

        Piece moving = this.getPiece(row, col);

        // keeps track of the location of the kings
        if (moving instanceof King) {
            if (moving.getIsWhite()) {
                this.wKingIndex = new int[]{newRow, newCol};
            }
            else {
                this.bKingIndex = new int[]{newRow, newCol};
            }
        }

        // checks if the object being moved is a Pawn
        if (moving instanceof Pawn) {

            // if pawn reaches the other end of the board, it is promoted to another kind of Piece
            if (newRow == 0 || newRow == this.SIZE-1) {
                this.promotePawn(moving);
            }
        }

        // moves the specified piece to a new spot on the board
        this.board[newRow][newCol] = this.board[row][col];
        this.board[newRow][newCol].setRow(newRow);
        this.board[newRow][newCol].setCol(newCol);
        this.board[row][col] = null;
    }

    /** Asks the player if they want to promote a Pawn to a Queen, Rook, Bishop, or Knight.
     * Changes the datatype of the Piece from Pawn to the specified type.
     * @param pawn the pawn that is to be promoted
     */

    public void promotePawn(Piece pawn) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Yay! Your pawn has reached the other side! What would you like to promote it to?");

        String type;
        Piece promoteTo = null;
        boolean typeIsValid;

        do {

            System.out.println("Enter one of the following options: Queen, Rook, Bishop, or Knight");
            type = sc.nextLine().toLowerCase();
            typeIsValid = true;

            // copies the attributes of the pawn to the new Piece
            if (type.equals("queen")) {
                promoteTo = new Queen(pawn.getIsWhite(), pawn.getRow(), pawn.getCol());
            }
            else if (type.equals("rook")) {
                promoteTo = new Rook(pawn.getIsWhite(), pawn.getRow(), pawn.getCol());
            }
            else if (type.equals("bishop")) {
                promoteTo = new Bishop(pawn.getIsWhite(), pawn.getRow(), pawn.getCol());
            }
            else if (type.equals("knight")) {
                promoteTo = new Knight(pawn.getIsWhite(), pawn.getRow(), pawn.getCol());
            }
            else {
                typeIsValid = false;
            }

        } while (!typeIsValid); // if user input does not match any of the specified types, the user is prompted to answer again

        // replaces the Pawn on the board with the promoted piece
        this.setPiece(pawn.getRow(), pawn.getCol(), promoteTo);

    }

    /** Checks if the coordinate entered by the Player exists on the board
     * @param coordinate coordinate entered by the Player
     * @return true of the coordinate exits on the board, false otherwise
     */

    public boolean doesCoordinateExist(String coordinate) {

        coordinate = coordinate.toUpperCase(); // in case the Player enters the coordinates in lowercase

        // each coordinate is made up of a letter between 'A' and 'H' followed by a number between 1 and 8
        if (coordinate.length() == 2) {
            if (coordinate.charAt(0) >= 'A' && coordinate.charAt(0) <= 'H' && coordinate.charAt(1) >= '1' && coordinate.charAt(1) <= '8') {
                return true;
            }
        }

        System.out.println("Invalid coordinates. Try again.");
        return false;
    }

    /** Outputs the current layout of the board
     */

    public void printBoard() {

        System.out.println();

        // outputs from the last row in the array to the first since A1 is oriented at [0][0] and A8 is oriented at [7][0]
        for (int row = SIZE-1; row >= 0; row--) {

            System.out.print((row+1) + "   "); // outputs the row number to the left of the board

            for (int col = 0; col < SIZE; col++) {

                System.out.print("[");

                if (this.board[row][col] == null) {
                    System.out.print("  ");
                }
                else {
                    System.out.print(this.board[row][col].toString());
                }

                System.out.print("]");
            }
            System.out.println();
        }

        // outputs the letter coordinates along the bottom of the board
        System.out.println("\n     A   B   C   D   E   F   G   H\n");

    }

}