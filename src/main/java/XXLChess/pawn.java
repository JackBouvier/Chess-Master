package XXLChess;
import processing.core.PImage;
import java.util.ArrayList;

/**
 * The pawn class represents a pawn chess piece in the game of chess.
 * It extends the chessPiece class and provides pawn-specific movement logic.
 */
public class pawn extends chessPiece {
    
    /**
     * Constructor for the pawn class.
     *
     * @param x     The x-coordinate of the pawn on the board.
     * @param y     The y-coordinate of the pawn on the board.
     * @param image The image of the pawn.
     * @param color The color of the pawn (either "white" or "black").
     */
    public pawn(int x, int y, PImage image, String color) {
        super(x, y, image, color, 1);
    }

    /**
     * Gets the valid moves for the pawn piece based on its position on the board.
     *
     * @param x     The x-coordinate of the pawn on the board.
     * @param y     The y-coordinate of the pawn on the board.
     * @param Array The current state of the chess board.
     * @return An ArrayList of valid chessTile objects that the pawn can move to.
     */
    public ArrayList<chessTile> getMoves(int x, int y, Tuple<chessPiece, chessTile>[][] Array) {
        ArrayList<chessTile> validMoves = new ArrayList<>();
        // Pawn movement rules
        int direction = this.color.equals("white") ? -1 : 1;
        int startRow = this.color.equals("white") ? 12 : 1;
        // Forward movement (single step)
        if (y + direction >= 0 && y + direction < 14 && Array[y + direction][x].getPiece() == null) {
            validMoves.add(Array[y + direction][x].getTile());
            // Double step from the starting position
            if (y == startRow && Array[y + 2 * direction][x].getPiece() == null) {
                validMoves.add(Array[y + 2 * direction][x].getTile());
            }
        }
        // Capturing diagonally
        for (int dx = -1; dx <= 1; dx += 2) {
            int newX = x + dx;
            int newY = y + direction;
    
            if (newX >= 0 && newX < 14 && newY >= 0 && newY < 14) {
                if (Array[newY][newX].getPiece() != null &&
                    !Array[newY][newX].getPiece().getPieceColor().equals(this.color)) {
                    validMoves.add(Array[newY][newX].getTile());
                }
            }
        }
        return validMoves;
    }
}
