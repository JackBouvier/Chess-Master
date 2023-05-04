package XXLChess;
import processing.core.PImage;
import java.util.ArrayList;

public class archbishop extends chessPiece{
    public archbishop(int x, int y, PImage image, String color) {
        super(x, y, image, color, 7.5);
    }

    /**
     * Returns the list of valid moves for the archbishop at the specified position.
     *
     * @param x      the x-coordinate of the archbishop
     * @param y      the y-coordinate of the archbishop
     * @param Array  the 2D array representing the game board
     * @return       the list of valid moves for the archbishop
     */
    public ArrayList<chessTile> getMoves(int x, int y, Tuple<chessPiece, chessTile>[][] Array) {
        ArrayList<chessTile> validMoves = new ArrayList<>();
        //Knight movement
        int[] knightMovesX = {2, 1, -1, -2, -2, -1, 1, 2};
        int[] knightMovesY = {1, 2, 2, 1, -1, -2, -2, -1};
        for (int k = 0; k < knightMovesX.length; k++) {
            int newX = x + knightMovesX[k];
            int newY = y + knightMovesY[k];

            if (newX >= 0 && newX < 14 && newY >= 0 && newY < 14) {
                if (Array[newY][newX].getPiece() == null ||
                    !Array[newY][newX].getPiece().getPieceColor().equals(this.color)) {
                    validMoves.add(Array[newY][newX].getTile());
                }
            }
        }
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
