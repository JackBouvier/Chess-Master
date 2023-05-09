package XXLChess;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

class AppTest {

    @BeforeEach
    void setUp() {
        App.initializeForTesting();
    }

    @Test
    void testSettings() {
        assertEquals(App.CELLSIZE * App.BOARD_WIDTH + App.SIDEBAR, App.WIDTH);
        assertEquals(App.CELLSIZE * App.BOARD_WIDTH, App.HEIGHT);
    }

    @Test
    void testInitialSetup() {
        assertNotNull(App.Board_Array);
        assertNotNull(App.boardArray);
        assertNotNull(App.timerAndScore);
        assertNotNull(App.boardSetup);
    }

    @Test
    void testTitleScreen() {
        assertTrue(App.titleScreen);
    }

    @Test
    void testIsWhiteTurn() {
        assertTrue(App.isWhiteTurn);
    }

    @Test
    void testFirstClick() {
        assertTrue(App.firstClick);
    }

    @Test
    void testMove() {
        assertFalse(App.move);
    }

    @Test
    void testWhiteScore() {
        assertEquals(0, App.whiteScore);
    }

    @Test
    void testBlackScore() {
        assertEquals(0, App.blackScore);
    }

    @Test
    void testWhiteWin() {
        assertFalse(App.whiteWin);
    }

    @Test
    void testBlackWin() {
        assertFalse(App.blackWin);
    }

    @Test
    void testStalemate() {
        assertFalse(App.stalemate);
    }

    @Test
    void testInCheck() {
        assertFalse(App.inCheck);
    }

}
