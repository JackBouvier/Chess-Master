package XXLChess;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import processing.core.PApplet;
import processing.core.PImage;

public class BoardArrayTest {

    @Test
    public void testChessTile() {
        chessTile tile = new chessTile(0, 0, "white");

        assertEquals(0, tile.getTileX());
        assertEquals(0, tile.getTileY());
        assertEquals("white", tile.getTileColor());

        tile.setTileColor("green");
        assertEquals("green", tile.getTileColor());
    }

    @Test
    public void testChessPiece() {
        PImage mockImage = Mockito.mock(PImage.class);
        chessPiece piece = new chessPiece(0, 0, mockImage, "white", 1.0);

        assertEquals(0, piece.getPieceX());
        assertEquals(0, piece.getPieceY());
        assertEquals("white", piece.getPieceColor());
        assertEquals(1.0, piece.getPieceValue());
    }

    @Test
    public void testTuple() {
        chessPiece mockPiece = Mockito.mock(chessPiece.class);
        chessTile mockTile = Mockito.mock(chessTile.class);

        Tuple<chessPiece, chessTile> tuple = new Tuple<>(mockPiece, mockTile);

        assertEquals(mockPiece, tuple.getPiece());
        assertEquals(mockTile, tuple.getTile());

        chessPiece newMockPiece = Mockito.mock(chessPiece.class);
        chessTile newMockTile = Mockito.mock(chessTile.class);

        tuple.setPiece(newMockPiece);
        tuple.setTile(newMockTile);

        assertEquals(newMockPiece, tuple.getPiece());
        assertEquals(newMockTile, tuple.getTile());
    }

    @Test
    public void testBoardArray() {
        BoardArray boardArray = new BoardArray();
        Tuple<chessPiece, chessTile>[][] array = boardArray.getBoardArray();

        assertNotNull(array);
        assertEquals(14, array.length);
        assertEquals(14, array[0].length);

        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 14; j++) {
                assertNotNull(array[i][j]);
                assertNotNull(array[i][j].getTile());
                assertEquals((i + j) % 2 == 0 ? "white" : "green", array[i][j].getTile().getTileColor());
            }
        }
    }

    @Test
    public void testDrawTile() {
        PApplet mockApp = Mockito.mock(PApplet.class);
        chessTile tile = new chessTile(0, 0, "white");

        tile.draw(mockApp);

        Mockito.verify(mockApp).fill(255);
        Mockito.verify(mockApp).rect(0, 0, 48, 48);
    }

    @Test
    public void testDrawPiece() {
        PApplet mockApp = Mockito.mock(PApplet.class);
        PImage mockImage = Mockito.mock(PImage.class);
        chessPiece piece = new chessPiece(0, 0, mockImage, "white", 1.0);

        piece.draw(mockApp);

        Mockito.verify(mockApp).image(mockImage, 0, 0);
    }
}
