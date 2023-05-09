package XXLChess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import processing.core.PImage;

class TileColourTest {
    public static BoardArray board_Array;
    public static Tuple<chessPiece, chessTile>[][] boardArray;
    PImage mockImage = Mockito.mock(PImage.class);

    @BeforeEach
    void initializeTestBoard() {
        board_Array = new BoardArray();
        boardArray = board_Array.getBoardArray();
        boardArray[0][0].getTile().setTileColor("white");
        boardArray[1][1].getTile().setTileColor("green");
    }

    @Test
    void testRefreshBoard() {
        boardArray[0][0].getTile().setTileColor("blue1");
        boardArray[1][1].getTile().setTileColor("blue2");
        boardArray[2][2].getTile().setTileColor("red1");
        boardArray[3][3].getTile().setTileColor("red2");
        boardArray[4][4].getTile().setTileColor("lightGreen1");
        boardArray[5][5].getTile().setTileColor("lightGreen2");

        TileColour.refreshBoard(boardArray);

        assertEquals("white", boardArray[0][0].getTile().getTileColor());
        assertEquals("green", boardArray[1][1].getTile().getTileColor());
        assertEquals("white", boardArray[2][2].getTile().getTileColor());
        assertEquals("green", boardArray[3][3].getTile().getTileColor());
        assertEquals("white", boardArray[4][4].getTile().getTileColor());
        assertEquals("green", boardArray[5][5].getTile().getTileColor());
    }

    @Test
    void testRemoveTrail() {
        boardArray[0][0].getTile().setTileColor("yellow1");
        boardArray[1][1].getTile().setTileColor("yellow2");

        TileColour.removeTrail(boardArray);

        assertEquals("white", boardArray[0][0].getTile().getTileColor());
        assertEquals("green", boardArray[1][1].getTile().getTileColor());
    }

    @Test
    void testRemoveCheck() {
        boardArray[0][0].getTile().setTileColor("DarkRed1");
        boardArray[1][1].getTile().setTileColor("DarkRed2");

        TileColour.removeCheck(boardArray);

        assertEquals("white", boardArray[0][0].getTile().getTileColor());
        assertEquals("green", boardArray[1][1].getTile().getTileColor());
    }
}