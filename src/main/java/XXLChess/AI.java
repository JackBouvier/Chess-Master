package XXLChess;
import java.util.Random;
import java.util.ArrayList;

public class AI {
    
     /**
     * Performs the AI move based on the current game state.
     */
    public static void Ai(){
        App.AiTick++;
        if (!App.move && App.Ai && App.AiTick >= 200 && !App.isWhiteTurn){
            
            // Random time delay
            Random random = new Random();
            App.AiTick = random.nextInt(100 - 0 + 1) + 0;

            // Get valid pieces
            ArrayList<chessPiece> validPieces = new ArrayList<chessPiece>();
            for(int i = 0; i < 14; i++){
                for(int j = 0; j < 14; j++){
                    if(App.boardArray[i][j].getPiece() != null){
                        ArrayList<chessTile> validMovesForPiece = new ArrayList<>();
                        if(App.boardArray[i][j].getPiece().getPieceColor().equals("black") && !App.isWhiteTurn){
                            if(validateMoves.validate(App.boardArray[i][j].getPiece(), validMovesForPiece).size() != 0){
                                validPieces.add(App.boardArray[i][j].getPiece());
                            }
                        }
                    }
                }
            }

            // Find the best piece and best move
            double greatestDifference = -1;
            for (int i = 0; i < validPieces.size(); i++){
                ArrayList<chessTile> validMovesForPiece = new ArrayList<>();
                validMovesForPiece = validateMoves.validate(validPieces.get(i), validMovesForPiece);
                for (int j = 0; j < validMovesForPiece.size(); j++){
                    chessPiece tempPiece = validPieces.get(i);
                    chessTile tempTile = validMovesForPiece.get(j);
                    if (App.boardArray[tempTile.getTileY()][tempTile.getTileX()].getPiece() != null && App.boardArray[tempTile.getTileY()][tempTile.getTileX()].getPiece().getPieceColor().equals("white")){   
                        double difference = App.boardArray[tempTile.getTileY()][tempTile.getTileX()].getPiece().getPieceValue();
                        @SuppressWarnings("unchecked")
                        Tuple<chessPiece, chessTile>[][] testArray2 = new Tuple[14][14];
                        for (int o = 0; o < 14; o++) {
                            for (int p = 0; p < 14; p++) {
                                testArray2[o][p] = new Tuple<>(App.boardArray[o][p].getPiece(), App.boardArray[o][p].getTile());
                            }
                        }
                        testArray2[tempPiece.getPieceY()][tempPiece.getPieceX()] = new Tuple<>(null, App.boardArray[tempPiece.getPieceY()][tempPiece.getPieceX()].getTile());
                        testArray2[tempTile.getTileY()][tempTile.getTileX()] = new Tuple<>(tempPiece, tempTile);
                        ArrayList<chessTile> whiteAttackTiles3 = new ArrayList<>();
                        for (int o = 0; o < 14; o++) {
                            for (int p = 0; p < 14; p++) {
                                if (testArray2[o][p].getPiece() != null) {
                                    if (testArray2[o][p].getPiece().getPieceColor().equals("white")) {
                                        if (testArray2[o][p].getPiece() instanceof king) {
                                            whiteAttackTiles3.addAll(((king) testArray2[o][p].getPiece()).getMovesWithoutCheckingKing(p, o, testArray2));
                                        } else {
                                            whiteAttackTiles3.addAll(testArray2[o][p].getPiece().getMoves(p, o, testArray2));
                                        }
                                    }
                                }
                            }
                        }
                        if (whiteAttackTiles3.contains(App.boardArray[tempTile.getTileY()][tempTile.getTileX()].getTile())) {
                            difference = difference - tempPiece.getPieceValue();
                        }
                        if (difference >= greatestDifference){
                            greatestDifference = difference;
                            App.selectedPiece = tempPiece;
                            App.selectedTile = tempTile;
                            App.savedPieceX = App.selectedPiece.getPieceX();
                            App.savedPieceY = App.selectedPiece.getPieceY();
                            App.savedTileX = App.selectedTile.getTileX();
                            App.savedTileY = App.selectedTile.getTileY();
                            App.firstClick = true;
                            App.move = true;
                        }
                    }
                }
            }
            if (greatestDifference == -1) {
                chessPiece highestValuePiece = null;
                chessTile bestSafeMove = null;
                double highestValue = 0;
                for (int i = 0; i < validPieces.size(); i++) {
                    chessPiece currentPiece = validPieces.get(i);
                    if (!App.whiteAttackTiles.contains(App.boardArray[currentPiece.getPieceY()][currentPiece.getPieceX()].getTile())) {
                        continue;
                    }
                    ArrayList<chessTile> validMovesForPiece = new ArrayList<>();
                    validMovesForPiece = validateMoves.validate(currentPiece, validMovesForPiece);
                    ArrayList<chessTile> safeMoves = new ArrayList<>();   
                    for (chessTile move : validMovesForPiece) {
                        if (!App.whiteAttackTiles.contains(move) && App.boardArray[move.getTileY()][move.getTileX()].getPiece() == null) {
                            safeMoves.add(move);
                        }
                    }
                    if (!safeMoves.isEmpty() && (highestValuePiece == null || currentPiece.getPieceValue() > highestValue)) {
                        highestValuePiece = currentPiece;
                        highestValue = highestValuePiece.getPieceValue();
                        bestSafeMove = safeMoves.get(random.nextInt(safeMoves.size()));
                    }
                }
                if (highestValuePiece != null && bestSafeMove != null) {
                    App.selectedPiece = highestValuePiece;
                    App.selectedTile = bestSafeMove;
                } else {
                    int randomPiece = random.nextInt(validPieces.size());
                    ArrayList<chessTile> validMovesForPiece = new ArrayList<>();
                    validMovesForPiece = validateMoves.validate(validPieces.get(randomPiece), validMovesForPiece);
                    int randomMove = random.nextInt(validMovesForPiece.size());
                    App.selectedPiece = validPieces.get(randomPiece);
                    App.selectedTile = validMovesForPiece.get(randomMove);
                }
                App.savedPieceX = App.selectedPiece.getPieceX();
                App.savedPieceY = App.selectedPiece.getPieceY();
                App.savedTileX = App.selectedTile.getTileX();
                App.savedTileY = App.selectedTile.getTileY();
                App.firstClick = true;
                App.move = true;
            }
        }
    }
}