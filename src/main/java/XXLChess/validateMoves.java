package XXLChess;
import java.util.ArrayList;

public class validateMoves {
    public static ArrayList<chessTile> validate(chessPiece tempPiece, ArrayList<chessTile> validMoves){
        if (tempPiece != null) {
            ArrayList<chessTile> possibleMoves = tempPiece.getMoves(tempPiece.getPieceX(), tempPiece.getPieceY(), App.boardArray);
            for (chessTile move : possibleMoves) {
                int moveX = move.getTileX();
                int moveY = move.getTileY();
                int whiteKingX = king.whiteKing.getPieceX();
                int whiteKingY = king.whiteKing.getPieceY();
                int blackKingX = king.blackKing.getPieceX();
                int blackKingY = king.blackKing.getPieceY();
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
                if (!(blackAttackTiles3.contains(App.boardArray[whiteKingY][whiteKingX].getTile()) && App.isWhiteTurn == true) && !(whiteAttackTiles3.contains(App.boardArray[blackKingY][blackKingX].getTile()) && App.isWhiteTurn == false)) {
                    App.inCheck = false;
                    validMoves.add(move);
                }
            }
        } 
        return validMoves;
    }
}