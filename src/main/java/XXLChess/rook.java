package XXLChess;
import processing.core.PImage;
import java.util.ArrayList;

/**
 * The rook class represents a rook chess piece in the game of chess.
 * It extends the chessPiece class and provides rook-specific movement logic.
 */
public class rook extends chessPiece {
    
    /**
     * Constructor for the rook class.
     *
     * @param x     The x-coordinate of the rook on the board.
     * @param y     The y-coordinate of the rook on the board.
     * @param image The image of the rook.
     * @param color The color of the rook (either "white" or "black").
     */
    public rook(int x, int y, PImage image, String color) {
        super(x, y, image, color, 5.25);
    }

    /**
     * Gets the valid moves for the rook piece based on its position on the board.
     *
     * @param x     The x-coordinate of the rook on the board.
     * @param y     The y-coordinate of the rook on the board.
     * @param Array The current state of the chess board.
     * @return An ArrayList of valid chessTile objects that the rook can move to.
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
        return validMoves;
    }
}
