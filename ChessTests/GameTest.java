import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {
    @Test
    void isMoveValidTest3() {
        ChessGame game = new ChessGame();
        game.play();
        assertEquals(true, game.checkMate(game.getBoard().getWhiteKing()));
    }


}
