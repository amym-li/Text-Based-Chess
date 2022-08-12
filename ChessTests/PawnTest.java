import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PawnTest {

    @Test
    void isMoveValidTest1() {
        Board board = new Board();
        assertEquals(true, board.getPiece(1,0).isMoveValid(board,2,0));
    }

    @Test
    void isMoveValidTest2() {
        Board board = new Board();
        assertEquals(true, board.getPiece(1,3).isMoveValid(board,3,3));
    }

    @Test
    void isMoveValidTest3() {
        Board board = new Board();
        assertEquals(true, board.getPiece(1,7).isMoveValid(board,2,7));
    }

    @Test
    void isMoveValidTest4() {
        Board board = new Board();
        assertEquals(true, board.getPiece(1,7).isMoveValid(board,3,7));
    }

    @Test
    void isMoveValidTest5() {
        Board board = new Board();
        assertEquals(false, board.getPiece(1,0).isMoveValid(board,2,2));
    }

    @Test
    void isMoveValidTest6() {
        Board board = new Board();
        assertEquals(false, board.getPiece(1,7).isMoveValid(board,4,7));
    }

    @Test
    void isMoveValidTest7() {
        Board board = new Board();
        assertEquals(false, board.getPiece(1,7).isMoveValid(board,0,7));
    }

    @Test
    void isMoveValidTest8() {
        Board board = new Board();
        assertEquals(true, board.getPiece(6,0).isMoveValid(board,4,0));
    }

    @Test
    void isMoveValidTest9() {
        Board board = new Board();
        assertEquals(true, board.getPiece(6,0).isMoveValid(board,5,0));
    }

    @Test
    void isMoveValidTest10() {
        Board board = new Board();
        assertEquals(true, board.getPiece(6,4).isMoveValid(board,5,4));
    }

    @Test
    void isMoveValidTest11() {
        Board board = new Board();
        assertEquals(true, board.getPiece(6,4).isMoveValid(board,4,4));
    }

    @Test
    void isMoveValidTest12() {
        Board board = new Board();
        assertEquals(false, board.getPiece(6,4).isMoveValid(board,7,4));
    }

    @Test
    void isMoveValidTest13() {
        Board board = new Board();
        assertEquals(false, board.getPiece(6,4).isMoveValid(board,3,4));
    }

    @Test
    void isMoveValidTest14() {
        Board board = new Board();
        assertEquals(false, board.getPiece(6,1).isMoveValid(board,6,2));
    }

    @Test
    void isMoveValidTest15() {
        Board board = new Board();
        assertEquals(false, board.getPiece(6,1).isMoveValid(board,5,0));
    }

    @Test
    void isMoveValidTest16() {
        Board board = new Board();
        assertEquals(false, board.getPiece(6,1).isMoveValid(board,6,1));
    }

    @Test
    void isMoveValidTest17() {
        Board board = new Board();
        assertEquals(false, board.getPiece(6,1).isMoveValid(board,89,5));
    }

    @Test
    void isMoveValidTest18() {
        Board board = new Board();
        assertEquals(false, board.getPiece(6,6).isMoveValid(board,-4,68));
    }


}
