package XXLChess;
import java.util.ArrayList;

/**
 * TileColour class is responsible for managing the tile colors of the chessboard,
 * including refreshing, removing trails and checks, and showing valid moves.
 */
public class TileColour {
    
    /**
     * Refreshes the board by resetting tile colors to their original state.
     */
    public static void refreshBoard(Tuple<chessPiece, chessTile>[][] boardArray){
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 14; j++) {
                chessTile tile = boardArray[i][j].getTile();
                if (tile.getTileColor().equals("blue1")){
                    boardArray[i][j].getTile().setTileColor("white");
                }
                else if (tile.getTileColor().equals("blue2")){
                    boardArray[i][j].getTile().setTileColor("green");
                }
                else if (tile.getTileColor().equals("red1")){
                    boardArray[i][j].getTile().setTileColor("white");
                }
                else if (tile.getTileColor().equals("red2")){
                    boardArray[i][j].getTile().setTileColor("green");
                }
                else if (tile.getTileColor().equals("lightGreen1")){
                    boardArray[i][j].getTile().setTileColor("white");
                }
                else if (tile.getTileColor().equals("lightGreen2")){
                    boardArray[i][j].getTile().setTileColor("green");
                }
            }
        }
    }

    /**
     * Removes the trail of the last moved piece by resetting the tile colors.
     */
    public static void removeTrail(Tuple<chessPiece, chessTile>[][] boardArray){
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 14; j++) {
                chessTile tile = boardArray[i][j].getTile();
                if (tile.getTileColor().equals("yellow1")){
                    boardArray[i][j].getTile().setTileColor("white");
                }
                else if (tile.getTileColor().equals("yellow2")){
                    boardArray[i][j].getTile().setTileColor("green");
                }
            }
        }
    }

    /**
     * Removes the check indication by resetting the tile colors.
     */
    public static void removeCheck(Tuple<chessPiece, chessTile>[][] boardArray){
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 14; j++) {
                chessTile tile = boardArray[i][j].getTile();
                if (tile.getTileColor().equals("DarkRed1")){
                    boardArray[i][j].getTile().setTileColor("white");
                }
                else if (tile.getTileColor().equals("DarkRed2")){
                    boardArray[i][j].getTile().setTileColor("green");
                }
            }
        }
    }

    /**
     * Shows the valid moves of the selected piece by changing the tile colors.
     *
     * @param validMoves      an ArrayList of valid chessTile moves for the selected piece
     * @param selectedPiece   the chessPiece that is selected
     */
    public static void showMoves(ArrayList<chessTile> validMoves, chessPiece selectedPiece) {
        int pieceX = selectedPiece.getPieceX();
        int pieceY = selectedPiece.getPieceY();
        if (App.boardArray[pieceY][pieceX].getTile().getTileColor().equals("white")) {
            App.boardArray[pieceY][pieceX].getTile().setTileColor("lightGreen1");
        } else if (App.boardArray[pieceY][pieceX].getTile().getTileColor().equals("green")) {
            App.boardArray[pieceY][pieceX].getTile().setTileColor("lightGreen2");
        }
        if (validMoves != null) {
            for (chessTile tile : validMoves) {
                int tileX = tile.getTileX();
                int tileY = tile.getTileY();
                chessPiece targetPiece = App.boardArray[tileY][tileX].getPiece();
    
                // Check if the target square has a piece with a different color than the selected piece
                if (targetPiece != null && !targetPiece.getPieceColor().equals(selectedPiece.getPieceColor())) {
                    if (App.boardArray[tileY][tileX].getTile().getTileColor().equals("white")) {
                        App.boardArray[tileY][tileX].getTile().setTileColor("red1");
                    } else if (App.boardArray[tileY][tileX].getTile().getTileColor().equals("green")) {
                        App.boardArray[tileY][tileX].getTile().setTileColor("red2");
                    } else if (App.boardArray[tileY][tileX].getTile().getTileColor().equals("yellow1")) {
                        App.boardArray[tileY][tileX].getTile().setTileColor("red1");
                    } else if (App.boardArray[tileY][tileX].getTile().getTileColor().equals("yellow2")) {
                        App.boardArray[tileY][tileX].getTile().setTileColor("red2");
                    }
                } else {
                    if (App.boardArray[tileY][tileX].getTile().getTileColor().equals("white")) {
                        App.boardArray[tileY][tileX].getTile().setTileColor("blue1");
                    } else if (App.boardArray[tileY][tileX].getTile().getTileColor().equals("green")) {
                        App.boardArray[tileY][tileX].getTile().setTileColor("blue2");
                    } else if (App.boardArray[tileY][tileX].getTile().getTileColor().equals("yellow1")) {
                        App.boardArray[tileY][tileX].getTile().setTileColor("blue1");
                    } else if (App.boardArray[tileY][tileX].getTile().getTileColor().equals("yellow2")) {
                        App.boardArray[tileY][tileX].getTile().setTileColor("blue2");
                    }
                }
            }
        }
    }    
}



