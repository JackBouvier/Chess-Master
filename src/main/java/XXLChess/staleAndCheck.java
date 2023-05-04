package XXLChess;
import java.util.ArrayList;

/**
 * staleAndCheck class is responsible for checking check, stalemate, and checkmate conditions
 * in the game of chess.
 */
public class staleAndCheck {
    
     /**
     * Checks if there is a check, stalemate, or checkmate situation on the board.
     * Updates the game state accordingly.
     */
    public static void checkCheck(){
        // Get valid pieces
        ArrayList<chessPiece> validPieces = new ArrayList<chessPiece>();
        for(int i = 0; i < 14; i++){
            for(int j = 0; j < 14; j++){
                if(App.boardArray[i][j].getPiece() != null){
                    ArrayList<chessTile> validMovesForPiece = new ArrayList<>();
                    if(App.boardArray[i][j].getPiece().getPieceColor().equals("white") && App.isWhiteTurn){
                        if(validateMoves.validate(App.boardArray[i][j].getPiece(), validMovesForPiece).size() != 0){
                            validPieces.add(App.boardArray[i][j].getPiece());
                        }
                    }
                    else if(App.boardArray[i][j].getPiece().getPieceColor().equals("black") && !App.isWhiteTurn){
                        if(validateMoves.validate(App.boardArray[i][j].getPiece(), validMovesForPiece).size() != 0){
                            validPieces.add(App.boardArray[i][j].getPiece());
                        }
                    }
                }
            }
        }
        try{
             // Check for check
            boolean checkCheck = false;
            if (App.blackAttackTiles.contains(App.boardArray[king.whiteKing.getPieceY()][king.whiteKing.getPieceX()].getTile())) {
                checkCheck = true;
            }
            if (App.whiteAttackTiles.contains(App.boardArray[king.blackKing.getPieceY()][king.blackKing.getPieceX()].getTile())) {
                checkCheck = true;
            }
             // Check for stalemate
            if (validPieces.size() == 0 && checkCheck == false){
                App.stalemate = true;
            }
            // Check for checkmate
            if (validPieces.size() == 0 && checkCheck == true && App.isWhiteTurn == true){
                App.blackWin = true;
            }
            else if (validPieces.size() == 0 && checkCheck == true && App.isWhiteTurn == false){
                App.whiteWin = true;
            }
        }
        catch(Exception e){}
    }
}
