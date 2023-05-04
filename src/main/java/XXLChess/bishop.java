package XXLChess;
import processing.core.PImage;
import java.util.ArrayList;

public class bishop extends chessPiece {
    public bishop(int x, int y, PImage image, String color) {
        super(x, y, image, color, 3.625);
    }

    /**
     * Returns the list of valid moves for the bishop at the specified position.
     *
     * @param x      the x-coordinate of the bishop
     * @param y      the y-coordinate of the bishop
     * @param Array  the 2D array representing the game board
     * @return       the list of valid moves for the bishop
     */
    public ArrayList<chessTile> getMoves(int x, int y, Tuple<chessPiece, chessTile>[][] Array) {
        ArrayList<chessTile> validMoves = new ArrayList<>();
        //Bishop movement
        for (int dx = -1; dx <= 1; dx += 2) {
            for (int dy = -1; dy <= 1; dy += 2) {
                int i = x + dx;
                int j = y + dy;
                while (i >= 0 && i < 14 && j >= 0 && j < 14) {
                    if (Array[j][i].getPiece() == null) {
                        validMoves.add(Array[j][i].getTile());
                    } else if (!Array[j][i].getPiece().getPieceColor().equals(this.color)) {
                        validMoves.add(Array[j][i].getTile());
                        break;
                    } else {
                        break;
                    }
                    i += dx;
                    j += dy;
                }
            }
        }
        return validMoves;
    }
}
