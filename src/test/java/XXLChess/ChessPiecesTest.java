package XXLChess;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import processing.core.PImage;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class AmazonTest {
    @Test
    public void testGetMoves() {
        PImage mockImage = Mockito.mock(PImage.class);
        @SuppressWarnings("unchecked")
        Tuple<chessPiece, chessTile>[][] mockArray = new Tuple[14][14];

        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 14; j++) {
                @SuppressWarnings("unchecked")
                Tuple<chessPiece, chessTile> tuple = Mockito.mock(Tuple.class);
                chessTile mockTile = Mockito.mock(chessTile.class);
                Mockito.when(tuple.getTile()).thenReturn(mockTile);
                mockArray[j][i] = tuple;
            }
        }

        amazon testAmazon = new amazon(7, 7, mockImage, "white");
        List<chessTile> validMoves = testAmazon.getMoves(7, 7, mockArray);

        Set<chessTile> expectedMoves = new HashSet<>();
        for (int x = 0; x < 14; x++) {
            for (int y = 0; y < 14; y++) {
                if (x != 7 || y != 7) {
                    // Rook movement
                    if (x == 7 || y == 7) {
                        expectedMoves.add(mockArray[y][x].getTile());
                    }

                    // Knight movement
                    if ((Math.abs(x - 7) == 2 && Math.abs(y - 7) == 1) || (Math.abs(x - 7) == 1 && Math.abs(y - 7) == 2)) {
                        expectedMoves.add(mockArray[y][x].getTile());
                    }

                    // Bishop movement
                    if (Math.abs(x - 7) == Math.abs(y - 7)) {
                        expectedMoves.add(mockArray[y][x].getTile());
                    }
                }
            }
        }

        assertEquals(expectedMoves.size(), validMoves.size());
        assertTrue(expectedMoves.containsAll(validMoves) && validMoves.containsAll(expectedMoves));
    }
}

class ArchbishopTest {
    @Test
    public void testGetMoves() {
        PImage mockImage = Mockito.mock(PImage.class);
        @SuppressWarnings("unchecked")
        Tuple<chessPiece, chessTile>[][] mockArray = new Tuple[14][14];

        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 14; j++) {
                @SuppressWarnings("unchecked")
                Tuple<chessPiece, chessTile> tuple = Mockito.mock(Tuple.class);
                chessTile mockTile = Mockito.mock(chessTile.class);
                Mockito.when(tuple.getTile()).thenReturn(mockTile);
                mockArray[j][i] = tuple;
            }
        }

        archbishop testArchbishop = new archbishop(7, 7, mockImage, "white");
        List<chessTile> validMoves = testArchbishop.getMoves(7, 7, mockArray);

        Set<chessTile> expectedMoves = new HashSet<>();
        for (int x = 0; x < 14; x++) {
            for (int y = 0; y < 14; y++) {
                if (x != 7 || y != 7) {
                    // Knight movement
                    if ((Math.abs(x - 7) == 2 && Math.abs(y - 7) == 1) || (Math.abs(x - 7) == 1 && Math.abs(y - 7) == 2)) {
                        expectedMoves.add(mockArray[y][x].getTile());
                    }

                    // Bishop movement
                    if (Math.abs(x - 7) == Math.abs(y - 7)) {
                        expectedMoves.add(mockArray[y][x].getTile());
                    }
                }
            }
        }

        assertEquals(expectedMoves.size(), validMoves.size());
        assertTrue(expectedMoves.containsAll(validMoves) && validMoves.containsAll(expectedMoves));
    }
}








