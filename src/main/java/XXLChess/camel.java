package XXLChess;
import processing.core.PImage;
import java.util.ArrayList;

/**
 * The camel class represents a custom chess piece in the game of chess,
 * which moves in an extended L-shape pattern (3 steps in one direction
 * and 1 step in a perpendicular direction).
 * It extends the chessPiece class and provides camel-specific movement logic.
 */
public class camel extends chessPiece {
    
    /**
     * Constructor for the camel class.
     *
     * @param x     The x-coordinate of the camel on the board.
     * @param y     The y-coordinate of the camel on the board.
     * @param image The image of the camel.
     * @param color The color of the camel (either "white" or "black").
     */
    public camel(int x, int y, PImage image, String color) {
        super(x, y, image, color, 2);
    }

    /**
     * Gets the valid moves for the camel piece based on its position on the board.
     *
     * @param x     The x-coordinate of the camel on the board.
     * @param y     The y-coordinate of the camel on the board.
     * @param Array The current state of the chess board.
     * @return An ArrayList of valid chessTile objects that the camel can move to.
     */
    public ArrayList<chessTile> getMoves(int x, int y, Tuple<chessPiece, chessTile>[][] Array) {
        ArrayList<chessTile> validMoves = new ArrayList<>();
        //camel movement
        int[] knightMovesX = {3, 1, -1, -3, -3, -1, 1, 3};
        int[] knightMovesY = {1, 3, 3, 1, -1, -3, -3, -1};
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
