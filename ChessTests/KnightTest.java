import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class KnightTest {

    @Test
    void isMoveValidTest1() {
        Board board = new Board();
        assertEquals(true, board.getPiece(0,1).isMoveValid(board,2,2));
    }

    @Test
    void isMoveValidTest2() {
        Board board = new Board();
        assertEquals(true, board.getPiece(0,1).isMoveValid(board,2,0));
    }

    @Test
    void isMoveValidTest3() {
        Board board = new Board();
        assertEquals(false, board.getPiece(0,1).isMoveValid(board,2,7));
    }

    @Test
    void isMoveValidTest4() {
        Board board = new Board();
        assertEquals(false, board.getPiece(0,1).isMoveValid(board,-3,0));
    }

    @Test
    void isMoveValidTest5() {
        Board board = new Board();
        assertEquals(false, board.getPiece(0,1).isMoveValid(board,34,98));
    }

    @Test
    void isMoveValidTest6() {
        Board board = new Board();
        assertEquals(true, board.getPiece(7,6).isMoveValid(board,5,5));
    }

    @Test
    void isMoveValidTest7() {
        Board board = new Board();
        assertEquals(false, board.getPiece(7,6).isMoveValid(board,8,8));
    }

    @Test
    void isMoveValidTest8() {
        Board board = new Board();
        assertEquals(false, board.getPiece(7,6).isMoveValid(board,6,4));
    }

    @Test
    void isMoveValidTest9() {
        Board board = new Board();
        assertEquals(true, board.getPiece(7,1).isMoveValid(board,5,0));
    }

    @Test
    void isMoveValidTest10() {
        Board board = new Board();
        assertEquals(false, board.getPiece(7,1).isMoveValid(board,-5,-4));
    }

    @Test
    void isMoveValidTest11() {
        Board board = new Board();
        assertEquals(true, board.getPiece(0,6).isMoveValid(board,2,5));
    }

    @Test
    void isMoveValidTest12() {
        Board board = new Board();
        assertEquals(false, board.getPiece(0,6).isMoveValid(board,-3,4));
    }

    @Test
    void isMoveValidTest13() {
        Board board = new Board();
        assertEquals(false, board.getPiece(0,6).isMoveValid(board,-3,4));
    }

}
