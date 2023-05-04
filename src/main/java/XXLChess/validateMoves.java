package XXLChess;
import java.util.ArrayList;

/**
 * The validateMoves class is responsible for validating the possible moves
 * for a given chess piece on the board.
 */
public class validateMoves {
    
    /**
     * Validates the possible moves for a given chess piece based on the
     * current state of the board and the piece's position.
     * @param tempPiece   The chess piece whose possible moves are to be validated.
     * @param validMoves  An ArrayList of chessTile objects representing the valid moves.
     * @return An ArrayList of valid chessTile objects after validating the given piece's possible moves.
     */
    public static ArrayList<chessTile> validate(chessPiece tempPiece, ArrayList<chessTile> validMoves){
        if (tempPiece != null) {
            int whiteKingX = -1;
            int whiteKingY = -1;
            int blackKingX = -1;
            int blackKingY = -1;
            ArrayList<chessTile> possibleMoves = tempPiece.getMoves(tempPiece.getPieceX(), tempPiece.getPieceY(), App.boardArray);
            for (chessTile move : possibleMoves) {
                int moveX = move.getTileX();
                int moveY = move.getTileY();
                try{
                    whiteKingX = king.whiteKing.getPieceX();
                    whiteKingY = king.whiteKing.getPieceY();
                    blackKingX = king.blackKing.getPieceX();
                    blackKingY = king.blackKing.getPieceY();
                }
                catch(Exception e){}
                @SuppressWarnings("unchecked")
                Tuple<chessPiece, chessTile>[][] testArray2 = new Tuple[14][14];
                for (int i = 0; i < 14; i++) {
                    for (int j = 0; j < 14; j++) {
                        testArray2[i][j] = new Tuple<>(App.boardArray[i][j].getPiece(), App.boardArray[i][j].getTile());
                    }
                }
                testArray2[tempPiece.getPieceY()][tempPiece.getPieceX()] = new Tuple<>(null, App.boardArray[tempPiece.getPieceY()][tempPiece.getPieceX()].getTile());
                testArray2[moveY][moveX] = new Tuple<>(tempPiece, move);
                if (tempPiece.getPieceValue() == 1000){
                    if (tempPiece.getPieceColor().equals("white")){
                        whiteKingX = moveX;
                        whiteKingY = moveY;
                    }
                    if (tempPiece.getPieceColor().equals("black")){
                        blackKingX = moveX;
                        blackKingY = moveY;
                    }
                }
                ArrayList<chessTile> whiteAttackTiles3 = new ArrayList<>();
                ArrayList<chessTile> blackAttackTiles3 = new ArrayList<>();
                for (int i = 0; i < 14; i++) {
                    for (int j = 0; j < 14; j++) {
                        if (testArray2[i][j].getPiece() != null) {
                            if (testArray2[i][j].getPiece().getPieceColor().equals("white")) {
                                if (testArray2[i][j].getPiece() instanceof king) {
                                    whiteAttackTiles3.addAll(((king) testArray2[i][j].getPiece()).getMovesWithoutCheckingKing(j, i, testArray2));
                                } else {
                                    whiteAttackTiles3.addAll(testArray2[i][j].getPiece().getMoves(j, i, testArray2));
                                }
                            }
                            if (testArray2[i][j].getPiece().getPieceColor().equals("black")) {
                                if (testArray2[i][j].getPiece() instanceof king) {
                                    blackAttackTiles3.addAll(((king) testArray2[i][j].getPiece()).getMovesWithoutCheckingKing(j, i, testArray2));
                                } else {
                                    blackAttackTiles3.addAll(testArray2[i][j].getPiece().getMoves(j, i, testArray2));
                                }
                            }
                        }
                    }
                }
                if (blackKingX != -1 && blackKingY != -1){
                    if (!(whiteAttackTiles3.contains(App.boardArray[blackKingY][blackKingX].getTile()) && App.isWhiteTurn == false)){
                        App.inCheck = false;
                        validMoves.add(move);
                    }
                }
                else if (whiteKingX != -1 && whiteKingY != -1){
                    if (!(blackAttackTiles3.contains(App.boardArray[whiteKingY][whiteKingX].getTile()) && App.isWhiteTurn == true)){
                        App.inCheck = false;
                        validMoves.add(move);
                    }
                }
            }
        } 
        return validMoves;
    }
}
