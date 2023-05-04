package XXLChess;
import processing.core.PImage;
import java.util.ArrayList;

/**
 * The queen class represents a queen chess piece in the game of chess.
 * It extends the chessPiece class and provides queen-specific movement logic.
 */
public class queen extends chessPiece {
    
    /**
     * Constructor for the queen class.
     *
     * @param x     The x-coordinate of the queen on the board.
     * @param y     The y-coordinate of the queen on the board.
     * @param image The image of the queen.
     * @param color The color of the queen (either "white" or "black").
     */
    public queen(int x, int y, PImage image, String color) {
        super(x, y, image, color, 9.5);
    }

    /**
     * Gets the valid moves for the queen piece based on its position on the board.
     *
     * @param x     The x-coordinate of the queen on the board.
     * @param y     The y-coordinate of the queen on the board.
     * @param Array The current state of the chess board.
     * @return An ArrayList of valid chessTile objects that the queen can move to.
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
