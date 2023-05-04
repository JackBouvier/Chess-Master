package XXLChess;
import processing.core.PImage;
import java.util.ArrayList;

/**
 * The chancellor class represents a custom chess piece in the game of chess,
 * combining the movement of a rook and a knight.
 * It extends the chessPiece class and provides chancellor-specific movement logic.
 */
public class chancellor extends chessPiece {
    
    /**
     * Constructor for the chancellor class.
     *
     * @param x     The x-coordinate of the chancellor on the board.
     * @param y     The y-coordinate of the chancellor on the board.
     * @param image The image of the chancellor.
     * @param color The color of the chancellor (either "white" or "black").
     */
    public chancellor(int x, int y, PImage image, String color) {
        super(x, y, image, color, 8.5);
    }

    /**
     * Gets the valid moves for the chancellor piece based on its position on the board.
     *
     * @param x     The x-coordinate of the chancellor on the board.
     * @param y     The y-coordinate of the chancellor on the board.
     * @param Array The current state of the chess board.
     * @return An ArrayList of valid chessTile objects that the chancellor can move to.
     */
    public ArrayList<chessTile> getMoves(int x, int y, Tuple<chessPiece, chessTile>[][] Array) {
        ArrayList<chessTile> validMoves = new ArrayList<>();
        // Rook movement
        int[] rookDirs = {-1, 1};
        for (int dir : rookDirs) {
            for (int i = x + dir; i >= 0 && i < 14; i += dir) {
                if (Array[y][i].getPiece() == null) {
                    validMoves.add(Array[y][i].getTile());
                } else if (!Array[y][i].getPiece().getPieceColor().equals(this.color)) {
                    validMoves.add(Array[y][i].getTile());
                    break;
                } else {
                    break;
                }
            }
            for (int i = y + dir; i >= 0 && i < 14; i += dir) {
                if (Array[i][x].getPiece() == null) {
                    validMoves.add(Array[i][x].getTile());
                } else if (!Array[i][x].getPiece().getPieceColor().equals(this.color)) {
                    validMoves.add(Array[i][x].getTile());
                    break;
                } else {
                    break;
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
