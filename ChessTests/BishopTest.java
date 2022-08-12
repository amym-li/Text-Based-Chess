import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BishopTest {

    @Test
    void isMoveValidTest1() {
        Board board = new Board();
        assertEquals(false, board.getPiece(0,2).isMoveValid(board,2,4));
    }

    @Test
    void isMoveValidTest2() {
        Board board = new Board();
        assertEquals(false, board.getPiece(7,2).isMoveValid(board,6,3));
    }

    @Test
    void isMoveValidTest3() {
        Board board = new Board();
        assertEquals(false, board.getPiece(7,5).isMoveValid(board,0,0));
    }

    @Test
    void isMoveValidTest4() {
        Board board = new Board();
        assertEquals(false, board.getPiece(7,5).isMoveValid(board,-7,7));
    }

    @Test
    void isMoveValidTest5() {
        Board board = new Board();
        assertEquals(false, board.getPiece(0,5).isMoveValid(board,8,900));
    }

    @Test
    void isMoveValidTest6() {
        Board board = new Board();
        assertEquals(false, board.getPiece(0,2).isMoveValid(board,-9,1));
    }

    @Test
    void isMoveValidTest7() {
        Board board = new Board();
        assertEquals(false, board.getPiece(0,2).isMoveValid(board,4,6));
    }

}
