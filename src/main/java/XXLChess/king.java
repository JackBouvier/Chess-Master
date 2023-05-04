package XXLChess;
import processing.core.PImage;
import java.util.ArrayList;

/**
 * The king class represents a king chess piece in the game of chess.
 * It extends the chessPiece class and provides king-specific movement logic.
 */
public class king extends chessPiece{
    public static chessPiece whiteKing;
    public static chessPiece blackKing;

    /**
     * Constructor for the king class.
     *
     * @param x     The x-coordinate of the king on the board.
     * @param y     The y-coordinate of the king on the board.
     * @param image The image of the king.
     * @param color The color of the king (either "white" or "black").
     */
    public king(int x, int y, PImage image, String color){
        super(x, y, image, color, 1000);
        if (color.equals("white")){
            whiteKing = this;
        }
        else if (color.equals("black")){
            blackKing = this;
        }
    }
    
    /**
     * Gets the valid moves for the king piece based on its position on the board
     * without checking if the king is in check.
     *
     * @param x     The x-coordinate of the king on the board.
     * @param y     The y-coordinate of the king on the board.
     * @param Array The current state of the chess board.
     * @return An ArrayList of valid chessTile objects that the king can move to without checking for check.
     */
    public ArrayList<chessTile> getMovesWithoutCheckingKing(int x, int y, Tuple<chessPiece, chessTile>[][] Array) {
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
        return validMoves;
    }

    /**
     * Gets the valid moves for the king piece based on its position on the board,
     * taking into account whether the king is in check.
     *
     * @param x     The x-coordinate of the king on the board.
     * @param y     The y-coordinate of the king on the board.
     * @param Array The current state of the chess board.
     * @return An ArrayList of valid chessTile objects that the king can move to.
     */
    public ArrayList<chessTile> getMoves(int x, int y, Tuple<chessPiece, chessTile>[][] Array) {
        ArrayList<chessTile> validMoves = getMovesWithoutCheckingKing(x, y, Array);
        @SuppressWarnings("unchecked")
        Tuple<chessPiece, chessTile>[][] testArray = new Tuple[14][14];
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 14; j++) {
                testArray[i][j] = new Tuple<>(App.boardArray[i][j].getPiece(), App.boardArray[i][j].getTile());
            }
        }
        testArray[y][x] = new Tuple<>(null, App.boardArray[y][x].getTile());
        ArrayList<chessTile> whiteAttackTiles2 = new ArrayList<>();
        ArrayList<chessTile> blackAttackTiles2 = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 14; j++) {
                if (testArray[i][j].getPiece() != null) {
                    if (testArray[i][j].getPiece().getPieceColor().equals("white")) {
                        if (testArray[i][j].getPiece() instanceof king) {
                            whiteAttackTiles2.addAll(((king) testArray[i][j].getPiece()).getMovesWithoutCheckingKing(j, i, testArray));
                        } else {
                            whiteAttackTiles2.addAll(testArray[i][j].getPiece().getMoves(j, i, testArray));
                        }
                    }
                    if (testArray[i][j].getPiece().getPieceColor().equals("black")) {
                        if (testArray[i][j].getPiece() instanceof king) {
                            blackAttackTiles2.addAll(((king) testArray[i][j].getPiece()).getMovesWithoutCheckingKing(j, i, testArray));
                        } else {
                            blackAttackTiles2.addAll(testArray[i][j].getPiece().getMoves(j, i, testArray));
                        }
                    }
                }
            }
        }
        // Castling functionality
        if (!moved) {
            // White king
            if (color.equals("white")) {
                chessPiece leftRook = Array[13][0].getPiece();
                chessPiece rightRook = Array[13][13].getPiece();
                boolean leftRookNotMoved = leftRook instanceof rook && leftRook.getPieceColor().equals("white") && !leftRook.moved;
                boolean rightRookNotMoved = rightRook instanceof rook && rightRook.getPieceColor().equals("white") && !rightRook.moved;

                if (leftRookNotMoved && Array[13][1].getPiece() == null && Array[13][2].getPiece() == null && Array[13][3].getPiece() == null && Array[13][4].getPiece() == null && Array[13][5].getPiece() == null && Array[13][6].getPiece() == null) {
                    if (!blackAttackTiles2.contains(App.boardArray[13][5].getTile()) && !blackAttackTiles2.contains(App.boardArray[13][6].getTile())) {
                        validMoves.add(Array[13][5].getTile());
                    }
                }
                if (rightRookNotMoved && Array[13][12].getPiece() == null && Array[13][11].getPiece() == null && Array[13][10].getPiece() == null && Array[13][9].getPiece() == null && Array[13][8].getPiece() == null) {
                    if (!blackAttackTiles2.contains(App.boardArray[13][8].getTile()) && !blackAttackTiles2.contains(App.boardArray[13][9].getTile())) {
                        validMoves.add(Array[13][9].getTile());
                    }
                }
            }
            // Black king
            else if (color.equals("black")) {
                chessPiece leftRook = Array[0][0].getPiece();
                chessPiece rightRook = Array[0][13].getPiece();
                boolean leftRookNotMoved = leftRook instanceof rook && leftRook.getPieceColor().equals("black") && !leftRook.moved;
                boolean rightRookNotMoved = rightRook instanceof rook && rightRook.getPieceColor().equals("black") && !rightRook.moved;

                if (leftRookNotMoved && Array[0][1].getPiece() == null && Array[0][2].getPiece() == null && Array[0][3].getPiece() == null && Array[0][4].getPiece() == null && Array[0][5].getPiece() == null && Array[0][6].getPiece() == null) {
                    if (!whiteAttackTiles2.contains(App.boardArray[0][5].getTile()) && !whiteAttackTiles2.contains(App.boardArray[0][6].getTile())) {
                        validMoves.add(Array[0][5].getTile());
                    }
                }
                if (rightRookNotMoved && Array[0][12].getPiece() == null && Array[0][11].getPiece() == null && Array[0][10].getPiece() == null && Array[0][9].getPiece() == null && Array[0][8].getPiece() == null) {
                    if (!whiteAttackTiles2.contains(App.boardArray[0][8].getTile()) && !whiteAttackTiles2.contains(App.boardArray[0][9].getTile())) {
                        validMoves.add(Array[0][9].getTile());
                    }
                }
            }
        }
        for (int i = 0; i < validMoves.size(); i++){
            if (this.getPieceColor().equals("white")){
                if (blackAttackTiles2.contains(validMoves.get(i))){
                    validMoves.remove(i);
                    i--;
                }
            }
            else if (this.getPieceColor().equals("black")){
                if (whiteAttackTiles2.contains(validMoves.get(i))){
                    validMoves.remove(i);
                    i--;
                }
            }
        }
        return validMoves;
    }
}