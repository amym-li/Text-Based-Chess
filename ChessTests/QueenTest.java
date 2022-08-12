import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class QueenTest {

    @Test
    void isMoveValidTest1() {
        Board board = new Board();
        assertEquals(false, board.getPiece(0,3).isMoveValid(board,2,2));
    }

    @Test
    void isMoveValidTest2() {
        Board board = new Board();
        assertEquals(false, board.getPiece(0,3).isMoveValid(board,0,-7));
    }

    @Test
    void isMoveValidTest3() {
        Board board = new Board();
        assertEquals(false, board.getPiece(0,3).isMoveValid(board,1,4));
    }

    @Test
    void isMoveValidTest4() {
        Board board = new Board();
        assertEquals(false, board.getPiece(0,3).isMoveValid(board,0,2));
    }

    @Test
    void isMoveValidTest5() {
        Board board = new Board();
        assertEquals(false, board.getPiece(0,3).isMoveValid(board,4,7));
    }

    @Test
    void isMoveValidTest6() {
        Board board = new Board();
        assertEquals(false, board.getPiece(7,3).isMoveValid(board,9,0));
    }

    @Test
    void isMoveValidTest7() {
        Board board = new Board();
        assertEquals(false, board.getPiece(7,3).isMoveValid(board,-9,0));
    }

    @Test
    void isMoveValidTest8() {
        Board board = new Board();
        assertEquals(false, board.getPiece(7,3).isMoveValid(board,-9,-76));
    }

    @Test
    void isMoveValidTest9() {
        Board board = new Board();
        assertEquals(false, board.getPiece(7,3).isMoveValid(board,0,0));
    }

    @Test
    void isMoveValidTest10() {
        Board board = new Board();
        assertEquals(false, board.getPiece(7,3).isMoveValid(board,7,0));
    }

}
