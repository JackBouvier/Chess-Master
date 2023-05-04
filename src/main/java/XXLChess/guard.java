package XXLChess;
import processing.core.PImage;
import java.util.ArrayList;

/**
 * The guard class represents a custom chess piece in the game of chess,
 * combining the movement of a king and a knight.
 * It extends the chessPiece class and provides guard-specific movement logic.
 */
public class guard extends chessPiece {
    
    /**
     * Constructor for the guard class.
     *
     * @param x     The x-coordinate of the guard on the board.
     * @param y     The y-coordinate of the guard on the board.
     * @param image The image of the guard.
     * @param color The color of the guard (either "white" or "black").
     */
    public guard(int x, int y, PImage image, String color) {
        super(x, y, image, color, 5);
    }

    /**
     * Gets the valid moves for the guard piece based on its position on the board.
     *
     * @param x     The x-coordinate of the guard on the board.
     * @param y     The y-coordinate of the guard on the board.
     * @param Array The current state of the chess board.
     * @return An ArrayList of valid chessTile objects that the guard can move to.
     */
    public ArrayList<chessTile> getMoves(int x, int y, Tuple<chessPiece, chessTile>[][] Array) {
        ArrayList<chessTile> validMoves = new ArrayList<>();
        // King movement rules
        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                // Skip the current position
                if (dx == 0 && dy == 0) {
                    continue;
                }
    
                int newX = x + dx;
                int newY = y + dy;
    
                if (newX >= 0 && newX < 14 && newY >= 0 && newY < 14) {
                    if (Array[newY][newX].getPiece() == null ||
                        !Array[newY][newX].getPiece().getPieceColor().equals(this.color)) {
                        validMoves.add(Array[newY][newX].getTile());
                    }
                }
            }
        }
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
        return validMoves;
    }
}
