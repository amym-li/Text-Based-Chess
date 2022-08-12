import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class KingTest {

    @Test
    void isMoveValidTest1() {
        Board board = new Board();
        King king = (King) board.getPiece(0, 4);
        assertEquals(king, board.getWhiteKing());
    }

    @Test
    void isMoveValidTest3() {
        Board board = new Board();
        assertEquals(false, board.getPiece(0,4).isMoveValid(board,1,4));
    }

    @Test
    void isMoveValidTest4() {
        Board board = new Board();
        assertEquals(false, board.getPiece(0,4).isMoveValid(board,0,2));
    }

    @Test
    void isMoveValidTest5() {
        Board board = new Board();
        assertEquals(false, board.getPiece(0,4).isMoveValid(board,4,7));
    }


    @Test
    void isMoveValidTest9() {
        Board board = new Board();
        assertEquals(false, board.getPiece(7,4).isMoveValid(board,0,0));
    }

    @Test
    void isMoveValidTest10() {
        Board board = new Board();
        assertEquals(false, board.getPiece(7,4).isMoveValid(board,7,0));
    }

}
