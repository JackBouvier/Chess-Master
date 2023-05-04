package XXLChess;
import processing.core.PApplet;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The boardSetup class is responsible for initializing the chess board
 * by reading the piece positions from a text file and setting up the pieces
 * on the board accordingly.
 */
public class boardSetup {
    private PApplet parent;
    
    /**
     * Constructor for the boardSetup class.
     *
     * @param parent The PApplet instance from the main class.
     */
    public boardSetup(PApplet parent) {
        this.parent = parent;
    }

    /**
     * Reads the piece layout from a text file and returns a 2D String array
     * representing the board.
     *
     * @param layoutPath The file path of the text file containing the piece layout.
     * @return A 2D String array representing the board layout.
     */
    public String[][] readPieceInput(String layoutPath) {
        String[][] pieceInput = new String[14][14];
        try {
            File f = new File(layoutPath);
            Scanner scan = new Scanner(f);
            for (int i = 0; i < 14; i++) {
                String[] tempArray;
                try {
                    tempArray = scan.nextLine().split("");
                    for (int k = tempArray.length; k < 14; k++) {
                        String[] newArray = new String[tempArray.length + 1];
                        System.arraycopy(tempArray, 0, newArray, 0, tempArray.length);
                        newArray[tempArray.length] = " ";
                        tempArray = newArray;
                    }
                } catch (Exception e) {
                    tempArray = new String[14];
                    for (int k = 0; k < 14; k++) {
                        tempArray[k] = " ";
                    }
                }
                pieceInput[i] = tempArray;
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return pieceInput;
    }

    /**
     * Sets up the initial pieces on the chess board based on the 2D String array
     * representing the board layout.
     *
     * @param pieceInput The 2D String array representing the board layout.
     * @param boardArray The 2D Tuple array representing the current state of the chess board.
     */
    public void initialPieces(String[][] pieceInput, Tuple<chessPiece, chessTile>[][] boardArray) {
    for (int i = 0; i < 14; i++) {
        for (int j = 0; j < 14; j++) {
            if (pieceInput[i][j] != null && boardArray[i][j] != null) {
                String pieceCode = pieceInput[i][j];
                chessPiece piece = null;
                    switch (pieceCode) {
                        case "a":
                            piece = new amazon(j, i, parent.loadImage("src/main/resources/XXLChess/w-amazon.png"), "white");
                            break;
                        case "A":
                            piece = new amazon(j, i, parent.loadImage("src/main/resources/XXLChess/b-amazon.png"), "black");
                            break;
                        case "h":
                            piece = new archbishop(j, i, parent.loadImage("src/main/resources/XXLChess/w-archbishop.png"), "white");
                            break;
                        case "H":
                            piece = new archbishop(j, i, parent.loadImage("src/main/resources/XXLChess/b-archbishop.png"), "black");
                            break;
                        case "b":
                            piece = new bishop(j, i, parent.loadImage("src/main/resources/XXLChess/w-bishop.png"), "white");
                            break;
                        case "B":
                            piece = new bishop(j, i, parent.loadImage("src/main/resources/XXLChess/b-bishop.png"), "black");
                            break;
                        case "c":
                            piece = new camel(j, i, parent.loadImage("src/main/resources/XXLChess/w-camel.png"), "white");
                            break;
                        case "C":
                            piece = new camel(j, i, parent.loadImage("src/main/resources/XXLChess/b-camel.png"), "black");
                            break;
                        case "e":
                            piece = new chancellor(j, i, parent.loadImage("src/main/resources/XXLChess/w-chancellor.png"), "white");
                            break;
                        case "E":
                            piece = new chancellor(j, i, parent.loadImage("src/main/resources/XXLChess/b-chancellor.png"), "black");
                            break;
                        case "k":
                            piece = new king(j, i, parent.loadImage("src/main/resources/XXLChess/w-king.png"), "white");
                            break;
                        case "K":
                            piece = new king(j, i, parent.loadImage("src/main/resources/XXLChess/b-king.png"), "black");
                            break;
                        case "g":
                            piece = new guard(j, i, parent.loadImage("src/main/resources/XXLChess/w-knight-king.png"), "white");
                            break;
                        case "G":
                            piece = new guard(j, i, parent.loadImage("src/main/resources/XXLChess/b-knight-king.png"), "black");
                            break;
                        case "n":
                            piece = new knight(j, i, parent.loadImage("src/main/resources/XXLChess/w-knight.png"), "white");
                            break;
                        case "N":
                            piece = new knight(j, i, parent.loadImage("src/main/resources/XXLChess/b-knight.png"), "black");
                            break;
                        case "p":
                            piece = new pawn(j, i, parent.loadImage("src/main/resources/XXLChess/w-pawn.png"), "white");
                            break;
                        case "P":
                            piece = new pawn(j, i, parent.loadImage("src/main/resources/XXLChess/b-pawn.png"), "black");
                            break;
                        case "q":
                            piece = new queen(j, i, parent.loadImage("src/main/resources/XXLChess/w-queen.png"), "white");
                            break;
                        case "Q":
                            piece = new queen(j, i, parent.loadImage("src/main/resources/XXLChess/b-queen.png"), "black");
                            break;
                        case "r":
                            piece = new rook(j, i, parent.loadImage("src/main/resources/XXLChess/w-rook.png"), "white");
                            break;
                        case "R":
                            piece = new rook(j, i, parent.loadImage("src/main/resources/XXLChess/b-rook.png"), "black");
                            break;
                    }
                    if (piece != null) {
                        boardArray[i][j].setPiece(piece);
                    }
                }
            }
        }
    }
}
