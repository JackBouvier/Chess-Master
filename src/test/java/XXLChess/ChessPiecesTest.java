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

class BishopTest {
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

        bishop testBishop = new bishop(7, 7, mockImage, "white");
        List<chessTile> validMoves = testBishop.getMoves(7, 7, mockArray);

        Set<chessTile> expectedMoves = new HashSet<>();
        for (int x = 0; x < 14; x++) {
            for (int y = 0; y < 14; y++) {
                if (x != 7 || y != 7) {
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

class CamelTest {
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

        camel testCamel = new camel(7, 7, mockImage, "white");
        List<chessTile> validMoves = testCamel.getMoves(7, 7, mockArray);

        Set<chessTile> expectedMoves = new HashSet<>();
        for (int x = 0; x < 14; x++) {
            for (int y = 0; y < 14; y++) {
                if (x != 7 || y != 7) {
                    // Camel movement
                    if ((Math.abs(x - 7) == 3 && Math.abs(y - 7) == 1) || (Math.abs(x - 7) == 1 && Math.abs(y - 7) == 3)) {
                        expectedMoves.add(mockArray[y][x].getTile());
                    }
                }
            }
        }

        assertEquals(expectedMoves.size(), validMoves.size());
        assertTrue(expectedMoves.containsAll(validMoves) && validMoves.containsAll(expectedMoves));
    }
}

class ChancellorTest {
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

        chancellor testChancellor = new chancellor(7, 7, mockImage, "white");
        List<chessTile> validMoves = testChancellor.getMoves(7, 7, mockArray);

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
                }
            }
        }

        assertEquals(expectedMoves.size(), validMoves.size());
        assertTrue(expectedMoves.containsAll(validMoves) && validMoves.containsAll(expectedMoves));
    }
}

class GuardTest {
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

        guard testGuard = new guard(7, 7, mockImage, "white");
        List<chessTile> validMoves = testGuard.getMoves(7, 7, mockArray);

        Set<chessTile> expectedMoves = new HashSet<>();
        for (int x = 0; x < 14; x++) {
            for (int y = 0; y < 14; y++) {
                if (x != 7 || y != 7) {
                    // King movement
                    if (Math.abs(x - 7) <= 1 && Math.abs(y - 7) <= 1) {
                        expectedMoves.add(mockArray[y][x].getTile());
                    }

                    // Knight movement
                    if ((Math.abs(x - 7) == 2 && Math.abs(y - 7) == 1) || (Math.abs(x - 7) == 1 && Math.abs(y - 7) == 2)) {
                        expectedMoves.add(mockArray[y][x].getTile());
                    }
                }
            }
        }

        assertEquals(expectedMoves.size(), validMoves.size());
        assertTrue(expectedMoves.containsAll(validMoves) && validMoves.containsAll(expectedMoves));
    }
}

class KingTest {
    @Test
    public void testGetMovesWithoutCheckingKing() {
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

        king testKing = new king(7, 7, mockImage, "white");
        List<chessTile> validMoves = testKing.getMovesWithoutCheckingKing(7, 7, mockArray);

        Set<chessTile> expectedMoves = new HashSet<>();
        for (int x = 0; x < 14; x++) {
            for (int y = 0; y < 14; y++) {
                if (x != 7 || y != 7) {
                    // King movement
                    if (Math.abs(x - 7) <= 1 && Math.abs(y - 7) <= 1) {
                        expectedMoves.add(mockArray[y][x].getTile());
                    }
                }
            }
        }

        assertEquals(expectedMoves.size(), validMoves.size());
        assertTrue(expectedMoves.containsAll(validMoves) && validMoves.containsAll(expectedMoves));
    }
}

class KnightTest {
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

        knight testKnight = new knight(7, 7, mockImage, "white");
        List<chessTile> validMoves = testKnight.getMoves(7, 7, mockArray);

        Set<chessTile> expectedMoves = new HashSet<>();
        for (int x = 0; x < 14; x++) {
            for (int y = 0; y < 14; y++) {
                // Knight movement
                int dx = Math.abs(x - 7);
                int dy = Math.abs(y - 7);
                if ((dx == 2 && dy == 1) || (dx == 1 && dy == 2)) {
                    expectedMoves.add(mockArray[y][x].getTile());
                }
            }
        }

        assertEquals(expectedMoves.size(), validMoves.size());
        assertTrue(expectedMoves.containsAll(validMoves) && validMoves.containsAll(expectedMoves));
    }
}

class PawnTest {
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

        pawn testPawn = new pawn(7, 7, mockImage, "white");
        List<chessTile> validMoves = testPawn.getMoves(7, 7, mockArray);

        Set<chessTile> expectedMoves = new HashSet<>();
        // One step forward
        expectedMoves.add(mockArray[6][7].getTile());
        // No double step since not on the starting row

        assertEquals(expectedMoves.size(), validMoves.size());
        assertTrue(expectedMoves.containsAll(validMoves) && validMoves.containsAll(expectedMoves));
    }

    @Test
    public void testGetMovesOnStartingRow() {
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

        pawn testPawn = new pawn(7, 12, mockImage, "white");
        List<chessTile> validMoves = testPawn.getMoves(7, 12, mockArray);

        Set<chessTile> expectedMoves = new HashSet<>();
        // One step forward
        expectedMoves.add(mockArray[11][7].getTile());
        // Two steps forward
        expectedMoves.add(mockArray[10][7].getTile());

        assertEquals(expectedMoves.size(), validMoves.size());
        assertTrue(expectedMoves.containsAll(validMoves) && validMoves.containsAll(expectedMoves));
    }
}

class QueenTest {
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

        queen testQueen = new queen(7, 7, mockImage, "white");
        List<chessTile> validMoves = testQueen.getMoves(7, 7, mockArray);

        Set<chessTile> expectedMoves = new HashSet<>();
        // Rook movement
        for (int i = 0; i < 14; i++) {
            if (i != 7) {
                expectedMoves.add(mockArray[7][i].getTile());
                expectedMoves.add(mockArray[i][7].getTile());
            }
        }
        // Bishop movement
        for (int dx = -1; dx <= 1; dx += 2) {
            for (int dy = -1; dy <= 1; dy += 2) {
                int i = 7 + dx;
                int j = 7 + dy;
                while (i >= 0 && i < 14 && j >= 0 && j < 14) {
                    expectedMoves.add(mockArray[j][i].getTile());
                    i += dx;
                    j += dy;
                }
            }
        }

        assertEquals(expectedMoves.size(), validMoves.size());
        assertTrue(expectedMoves.containsAll(validMoves) && validMoves.containsAll(expectedMoves));
    }
}

class RookTest {
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

        rook testRook = new rook(7, 7, mockImage, "white");
        List<chessTile> validMoves = testRook.getMoves(7, 7, mockArray);

        Set<chessTile> expectedMoves = new HashSet<>();
        // Rook movement
        for (int i = 0; i < 14; i++) {
            if (i != 7) {
                expectedMoves.add(mockArray[7][i].getTile());
                expectedMoves.add(mockArray[i][7].getTile());
            }
        }

        assertEquals(expectedMoves.size(), validMoves.size());
        assertTrue(expectedMoves.containsAll(validMoves) && validMoves.containsAll(expectedMoves));
    }
}