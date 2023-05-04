package XXLChess;
import processing.core.PApplet;
import processing.data.JSONObject;
import processing.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import processing.core.PFont;

public class App extends PApplet {
    public static final int SPRITESIZE = 480;
    public static final int CELLSIZE = 48;
    public static final int SIDEBAR = 120;
    public static final int BOARD_WIDTH = 14;
    public static int WIDTH = CELLSIZE*BOARD_WIDTH+SIDEBAR;
    public static int HEIGHT = BOARD_WIDTH*CELLSIZE;
    public static final int FPS = 60;
    public String configPath;
    public static BoardArray Board_Array;
    public static Tuple<chessPiece, chessTile>[][] boardArray;
    public static boardSetup boardSetup;
    public static boolean isWhiteTurn = true;
    public static boolean Ai;
    public static chessPiece selectedPiece;
    public static chessTile selectedTile;
    public static boolean firstClick = true;
    public static boolean move = false;
    public static int savedPieceX;
    public static int savedPieceY;
    public static int savedTileX;
    public static int savedTileY;
    public static double whiteScore = 0;
    public static double blackScore = 0;
    public static boolean whiteWin = false;
    public static boolean blackWin = false;
    public static boolean stalemate = false;
    public static String whiteTimeRemaining = "";
    public static String blackTimeRemaining = "";
    public static int whiteTickCount = 0;
    public static int blackTickCount = 0;
    public static  TimerAndScore timerAndScore;
    public static ArrayList<chessTile> blackAttackTiles;
    public static ArrayList<chessTile> whiteAttackTiles;
    public static boolean inCheck = false;
    public static chessPiece tempPiece;
    public static chessTile tempTile;
    public static ArrayList<chessTile> validMoves = new ArrayList<>();
    public static boolean titleScreen = true;
    public static int AiTick;
    
    //java script inputs
    public static JSONObject json = loadJSONObject(new File("config.json"));
    public static int whiteTotalSeconds = json.getJSONObject("time_controls").getJSONObject("player").getInt("seconds");
    public static int blackTotalSeconds = json.getJSONObject("time_controls").getJSONObject("cpu").getInt("seconds");
    public static int whiteTimeBack = json.getJSONObject("time_controls").getJSONObject("player").getInt("increment");
    public static int blackTimeBack = json.getJSONObject("time_controls").getJSONObject("cpu").getInt("increment");

    /**
     * Initialise the setting of the window size.
    */
    public void settings() {
        size(WIDTH, HEIGHT);
    }

    /**
     * Load all resources such as images. Initialise the elements such as the player, enemies and map elements.
    */
    public void setup() {
        frameRate(FPS);
        Board_Array = new BoardArray();
        boardArray = Board_Array.getBoardArray(); 
        timerAndScore = new TimerAndScore(this);  
        String layoutPath = json.getString("layout");
        boardSetup = new boardSetup(this);
        String[][] pieceInput = boardSetup.readPieceInput(layoutPath);
        boardSetup.initialPieces(pieceInput, boardArray);
    }

    /**
     * Receive key pressed signal from the keyboard.
    */
    public void keyPressed(){
        if (whiteWin == true || blackWin == true || stalemate == true){
            if (key == 'r'){
                whiteTotalSeconds = json.getJSONObject("time_controls").getJSONObject("player").getInt("seconds");
                blackTotalSeconds = json.getJSONObject("time_controls").getJSONObject("cpu").getInt("seconds");
                isWhiteTurn = true;
                Ai = false;
                firstClick = true;
                move = false;
                whiteScore = 0;
                blackScore = 0;
                whiteWin = false;
                blackWin = false;
                stalemate = false;
                whiteTimeRemaining = "";
                blackTimeRemaining = "";
                whiteTickCount = 0;
                blackTickCount = 0;
                inCheck = false;
                titleScreen = true;
                setup();
            }
        }
        if (titleScreen == true){
            if (key == 'p'){
                setup();
                titleScreen = false;
                Ai = false;
            }
            if (key == 'c'){
                setup();
                titleScreen = false;
                Ai = true;
            }
        }
    }
    
    /**
     * Receive mouse clicks.
    */
    @Override
    public void mouseClicked(MouseEvent e) {
        int selectedX = (int) (mouseX / 48);
        int selectedY = (int) (mouseY / 48);
        tempPiece = boardArray[selectedY][selectedX].getPiece();
        tempTile = boardArray[selectedY][selectedX].getTile();   
        inCheck = false;
        if (move == false && (isWhiteTurn || Ai == false)) {  
            if (tempPiece != null && tempPiece.getPieceColor().equals("white") && isWhiteTurn == true) {
                validMoves.clear();
                if (blackAttackTiles.contains(boardArray[king.whiteKing.getPieceY()][king.whiteKing.getPieceX()].getTile())) {
                    inCheck = true;
                    if (boardArray[king.whiteKing.getPieceY()][king.whiteKing.getPieceX()].getTile().getTileColor().equals("white")){
                        boardArray[king.whiteKing.getPieceY()][king.whiteKing.getPieceX()].getTile().setTileColor("DarkRed1");
                    }
                    if (boardArray[king.whiteKing.getPieceY()][king.whiteKing.getPieceX()].getTile().getTileColor().equals("green")){
                        boardArray[king.whiteKing.getPieceY()][king.whiteKing.getPieceX()].getTile().setTileColor("DarkRed2");
                    }
                }
            }
            if (tempPiece != null && tempPiece.getPieceColor().equals("black") && isWhiteTurn == false) {
                validMoves.clear();
                if (whiteAttackTiles.contains(boardArray[king.blackKing.getPieceY()][king.blackKing.getPieceX()].getTile())) {
                    inCheck = true;
                    if (boardArray[king.blackKing.getPieceY()][king.blackKing.getPieceX()].getTile().getTileColor().equals("white")){
                        boardArray[king.blackKing.getPieceY()][king.blackKing.getPieceX()].getTile().setTileColor("DarkRed1");
                    }
                    if (boardArray[king.blackKing.getPieceY()][king.blackKing.getPieceX()].getTile().getTileColor().equals("green")){
                        boardArray[king.blackKing.getPieceY()][king.blackKing.getPieceX()].getTile().setTileColor("DarkRed2");
                    }
                }
            } 
            validMoves = validateMoves.validate(tempPiece, validMoves);
            if (firstClick && tempPiece != null){
                if (tempPiece.getPieceColor().equals("white") && isWhiteTurn == true){
                    selectedPiece = tempPiece;                   
                    TileColour.showMoves(validMoves, selectedPiece);
                    firstClick = false;
                }
                if (tempPiece.getPieceColor().equals("black") && isWhiteTurn == false){
                    selectedPiece = tempPiece;                   
                    TileColour.showMoves(validMoves, selectedPiece);
                    firstClick = false;
                }
            }
            else if (tempPiece != null && !firstClick && tempPiece.getPieceColor().equals(selectedPiece.getPieceColor())){
                if (tempPiece.getPieceColor().equals("white") && isWhiteTurn == true){
                    TileColour.refreshBoard();
                    selectedPiece = tempPiece;
                    TileColour.showMoves(validMoves, selectedPiece);
                    firstClick = false;
                }
                if (tempPiece.getPieceColor().equals("black") && isWhiteTurn == false){
                    TileColour.refreshBoard();
                    selectedPiece = tempPiece;
                    TileColour.showMoves(validMoves, selectedPiece);
                    firstClick = false;
                }
            }
            else if (selectedPiece != null && !firstClick && (validMoves.contains(tempTile))) {
                TileColour.refreshBoard();
                TileColour.removeCheck();
                selectedTile = tempTile;
                savedPieceX = selectedPiece.getPieceX();
                savedPieceY = selectedPiece.getPieceY();
                savedTileX = selectedTile.getTileX();
                savedTileY = selectedTile.getTileY();
                firstClick = true;
                move = true;
            }
            else {
                TileColour.refreshBoard();
                validMoves.clear();
                firstClick = true;
            }
        }
    }
        
    /**
     * Draw all elements in the game by current frame. 
    */
    public void draw() {

        // Draw board
        Board_Array.DrawTiles(this);
        Board_Array.DrawPieces(this);

        // Check for checkmate and stalemate
        staleAndCheck.checkCheck();

        // Ai
        if(whiteWin == false && blackWin == false && stalemate == false){
            AI.Ai();
        }
        
        // Moves
        if (move){
            if (selectedPiece.getPieceColor().equals("white")){
                isWhiteTurn = false;
            }
            else if (selectedPiece.getPieceColor().equals("black")){
                isWhiteTurn = true;
            }
            selectedPiece.tick(selectedTile);

            // Special moves
            if (selectedPiece.getPieceValue() == 1 && selectedPiece.getPieceColor().equals("white") && selectedPiece.getPieceY() == 6) {
                boardArray[selectedPiece.getPieceY()][selectedPiece.getPieceX()] = new Tuple<>(new queen(selectedPiece.getPieceX(), selectedPiece.getPieceY(), loadImage("src/main/resources/XXLChess/w-queen.png"), "white"), boardArray[selectedPiece.getPieceY()][selectedPiece.getPieceX()].getTile());
            } else if (selectedPiece.getPieceValue() == 1 && selectedPiece.getPieceColor().equals("black") && selectedPiece.getPieceY() == 7) {
                boardArray[selectedPiece.getPieceY()][selectedPiece.getPieceX()] = new Tuple<>(new queen(selectedPiece.getPieceX(), selectedPiece.getPieceY(), loadImage("src/main/resources/XXLChess/b-queen.png"), "black"), boardArray[selectedPiece.getPieceY()][selectedPiece.getPieceX()].getTile());
            }

            // Castling
            if (selectedPiece.getPieceValue() == 1000)
                selectedPiece.moved = true;
            if (selectedPiece.getPieceValue() == 5.25)
                selectedPiece.moved = true;
            if (selectedPiece instanceof king) {
                int deltaX = Math.abs(savedTileX - savedPieceX);
                if (deltaX == 2) {
                    if (selectedPiece.getPieceColor().equals("white")) {
                        if (savedTileX == 5) { // Castling with the left rook
                            boardArray[13][0] = new Tuple<>(null, boardArray[13][0].getTile());
                            boardArray[13][6] = new Tuple<>(new rook(6, 13, loadImage("src/main/resources/XXLChess/w-rook.png"), "white"), boardArray[13][6].getTile());
                        } else if (savedTileX == 9) { // Castling with the right rook
                            boardArray[13][13] = new Tuple<>(null, boardArray[13][13].getTile());
                            boardArray[13][8] = new Tuple<>(new rook(8, 13, loadImage("src/main/resources/XXLChess/w-rook.png"), "white"), boardArray[13][8].getTile());
                        }
                    } else if (selectedPiece.getPieceColor().equals("black")) {
                        if (savedTileX == 5) { // Castling with the left rook
                            boardArray[0][0] = new Tuple<>(null, boardArray[0][0].getTile());
                            boardArray[0][6] = new Tuple<>(new rook(6, 0, loadImage("src/main/resources/XXLChess/b-rook.png"), "black"), boardArray[0][6].getTile());
                        } else if (savedTileX == 9) { // Castling with the right rook
                            boardArray[0][13] = new Tuple<>(null, boardArray[0][13].getTile());
                            boardArray[0][8] = new Tuple<>(new rook(8, 0, loadImage("src/main/resources/XXLChess/b-rook.png"), "black"), boardArray[0][8].getTile());
                        }
                    }
                }
            }
        }

        // Draw timer and score
        timerAndScore.drawTimerAndScore();

        // Title screen
        if (titleScreen == true){
            fill(192, 192, 192);
            rect(-1, -1, HEIGHT*2, WIDTH*2);
            fill(2, 88, 57);
            PFont myFont = createFont("Arial", 40);
            textFont(myFont);
            text("Welcome to XXL Chess !!!", 3*52+20, 5*44);
            text("Click 'p' to play", 3*52+20, 5*48+50);
            text("against a person", 3*52+20, 5*52+70);
            text("Click 'c' to play against", 3*52+20, 7*48+90);
            text("a basic AI", 3*52+20, 7*52+100);
        }
        // Draw game over screen
        if (whiteWin == true || blackWin == true || stalemate == true){
            fill(192, 192, 192);
            rect(14*48, 0, 3*48, 16*48);
            PFont myFont = createFont("Arial", 20);
            textFont(myFont);
            if (whiteWin == true && blackTotalSeconds <= 0){
                fill(0);
                text("White Wins", 13*52+7, 5*48+30);
                text("on time !", 13*52+7, 5*52+30);
                text("Click 'r'", 13*52+7, 7*48+30);
                text("to restart", 13*52+7, 7*52+30);
            }
            else if (blackWin == true && whiteTotalSeconds <= 0){
                fill(0);
                text("Black Wins", 13*52+7, 5*48+30);
                text("on time !", 13*52+7, 5*52+30);
                text("Click 'r'", 13*52+7, 7*48+30);
                text("to restart", 13*52+7, 7*52+30);
            }
            else if (whiteWin == true){
                fill(0);
                text("Checkmate", 13*52+7, 5*48+30);
                text("white wins", 13*52+7, 5*52+30);
                text("Click 'r'", 13*52+7, 7*48+30);
                text("to restart", 13*52+7, 7*52+30);
            }
            else if (blackWin == true){
                fill(0);
                text("checkmate", 13*52+7, 5*48+30);
                text("black wins", 13*52+7, 5*52+30);
                text("Click 'r'", 13*52+7, 7*48+30);
                text("to restart", 13*52+7, 7*52+30);
            }
            else if (stalemate == true){
                fill(0);
                text("Stalemate", 13*52+7, 5*48+30);
                text("Click 'r'", 13*52+7, 6*48+30);
                text("to restart", 13*52+7, 6*52+30);
            }
        }
    }
	
    /**
     * The main method to start the application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        PApplet.main("XXLChess.App");
    }
}