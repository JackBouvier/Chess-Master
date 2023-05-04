package XXLChess;
import processing.core.PImage;
import java.util.ArrayList;

/**
 * The knight class represents a knight chess piece in the game of chess.
 * It extends the chessPiece class and provides knight-specific movement logic.
 */
public class knight extends chessPiece {
    
    /**
     * Constructor for the knight class.
     *
     * @param x     The x-coordinate of the knight on the board.
     * @param y     The y-coordinate of the knight on the board.
     * @param image The image of the knight.
     * @param color The color of the knight (either "white" or "black").
     */
    public knight(int x, int y, PImage image, String color) {
        super(x, y, image, color, 2);
    }

    /**
     * Gets the valid moves for the knight piece based on its position on the board.
     *
     * @param x     The x-coordinate of the knight on the board.
     * @param y     The y-coordinate of the knight on the board.
     * @param Array The current state of the chess board.
     * @return An ArrayList of valid chessTile objects that the knight can move to.
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
        return validMoves;
    }
}
