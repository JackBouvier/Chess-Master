package XXLChess;
import processing.core.PImage;
import java.util.ArrayList;

public class amazon extends chessPiece {
    public amazon(int x, int y, PImage image, String color) {
        super(x, y, image, color, 12);
    }

    /**
     * Returns the list of valid moves for the amazon at the specified position.
     *
     * @param x      the x-coordinate of the amazon
     * @param y      the y-coordinate of the amazon
     * @param Array  the 2D array representing the game board
     * @return       the list of valid moves for the amazon
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
