package XXLChess;
import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;

// Chess Tile class --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
/**
 * Chess Tile class
 */
class chessTile {
    private int x;
    private int y;
    private String inputColor;
    private static int width = 48;
    private static int height = 48;

    /**
     * Constructor for the chessTile class.
     *
     * @param x          x-coordinate of the tile
     * @param y          y-coordinate of the tile
     * @param inputColor color of the tile
     */
    public chessTile(int x, int y, String inputColor) {
        this.x = x;
        this.y = y;
        this.inputColor = inputColor;
    }

    /**
     * Draws the tile on the given PApplet instance.
     *
     * @param app PApplet instance on which to draw the tile
     */
    public void draw(PApplet app) {
        if (this.inputColor.equals("white")) {
            app.fill(255);
        }
        if (this.inputColor.equals("green")) {
            app.fill(2, 88, 57);
        }
        if (this.inputColor.equals("blue1") || this.inputColor.equals("blue2")) {
            app.fill(134, 197, 218);
        }
        if (this.inputColor.equals("lightGreen1") || this.inputColor.equals("lightGreen2")) {
            app.fill(75, 200, 107);
        }
        if (this.inputColor.equals("red1") || this.inputColor.equals("red2")) {
            app.fill(255, 114, 111);
        }
        if (this.inputColor.equals("yellow1") || this.inputColor.equals("yellow2")) {
            app.fill(238, 255, 168);
        }
        if (this.inputColor.equals("DarkRed1") || this.inputColor.equals("DarkRed2")) {
            app.fill(139, 0, 0);
        }
        app.rect(this.x, this.y, width, height);
    }

    public void setTileColor(String newColor) {
        this.inputColor = newColor;
    }

    public String getTileColor() {
        return this.inputColor;
    }

    public int getTileX() {
        return this.x / 48;
    }

    public int getTileY() {
        return this.y / 48;
    }
}

// Chess Piece class -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
/**
 * Chess Piece class
 */
class chessPiece{
    protected int x;
    protected int y;
    protected PImage image;
    protected String color;
    protected double value;
    protected boolean moved;

    /**
     * Constructor for the chessPiece class.
     *
     * @param x      x-coordinate of the piece
     * @param y      y-coordinate of the piece
     * @param image  image of the piece
     * @param color  color of the piece
     * @param value  value of the piece
     */
    public chessPiece(int x, int y, PImage image, String color, double value) {
        this.image = image;
        this.color = color;
        this.image.resize(48, 48);
        this.value = value;
        this.x = x*48;
        this.y = y*48;
        this.moved = false;
    }

    public void tick(chessTile selectedTile) {
        int x = selectedTile.getTileX();
        int y = selectedTile.getTileY();
        int dx = x*48;
        int dy = y*48;
        int speed = App.json.getInt("piece_movement_speed");
        for (int i = 0; i < speed;i++){
            if (App.boardArray[App.savedTileY][App.savedTileX].getPiece() != null){
                if (App.selectedPiece.getPieceColor().equals("white")){
                   App.whiteScore +=  App.boardArray[App.savedTileY][App.savedTileX].getPiece().getPieceValue();
                }
                else {
                    App.blackScore +=  App.boardArray[App.savedTileY][App.savedTileX].getPiece().getPieceValue();
                }
                App.boardArray[App.savedTileY][App.savedTileX].setPiece(null);;
            }
            if (this.x < dx){
                this.x ++;
            }
            if (this.y < dy){
                this.y ++;
            }
            if (this.x > dx){
                this.x --;
            }
            if (this.y > dy){
                this.y --;
            }
        }
        if (this.x == dx && this.y == dy){
            App.boardArray[App.savedPieceY][App.savedPieceX].setPiece(null);
            App.boardArray[y][x].setPiece(App.selectedPiece);
            TileColour.removeTrail();
            if (App.boardArray[y][x].getTile().getTileColor().equals("white")){
                App.boardArray[y][x].getTile().setTileColor("yellow1");
            }
            else if (App.boardArray[y][x].getTile().getTileColor().equals("green")){
                App.boardArray[y][x].getTile().setTileColor("yellow2");
            }
            if (App.boardArray[App.savedPieceY][App.savedPieceX].getTile().getTileColor().equals("white")){
                App.boardArray[App.savedPieceY][App.savedPieceX].getTile().setTileColor("yellow1");
            }
            else if (App.boardArray[App.savedPieceY][App.savedPieceX].getTile().getTileColor().equals("green")){
                App.boardArray[App.savedPieceY][App.savedPieceX].getTile().setTileColor("yellow2");
            }
            if (App.selectedPiece.getPieceColor().equals("white")){
                App.whiteTotalSeconds = App.whiteTotalSeconds + App.whiteTimeBack;
            }
            else {
                App.blackTotalSeconds = App.blackTotalSeconds + App.blackTimeBack;
            }
            App.move = false;
        }
    }

    public void draw(PApplet app) {
        app.image(this.image, this.x, this.y);
    }

    public String getPieceColor() {
        return this.color;
    }

    public double getPieceValue() {
        return this.value;
    }

    public int getPieceX() {
        return this.x / 48;
    }

    public int getPieceY() {
        return this.y / 48;
    }

    public ArrayList<chessTile> getMoves(int x, int y, Tuple<chessPiece, chessTile>[][] boardArray){
        return null;
    }
}

// Tupe class --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
/**
 * Tuple class
 */
class Tuple<Piece, Tile> {
    private Piece first;
    private Tile second;

    /**
     * Constructor for the Tuple class.
     *
     * @param first  first element in the tuple
     * @param second second element in the tuple
     */
    public Tuple(Piece first, Tile second) {
        this.first = first;
        this.second = second;
    }

    public Piece getPiece() {
        return first;
    }

    public Tile getTile() {
        return second;
    }

    public void setPiece(Piece first) {
        this.first = first;
    }

    public void setTile(Tile second) {
        this.second = second;
    }
}

// Board Array class -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
/**
 * Board Array class
 */

public class BoardArray {
    @SuppressWarnings("unchecked")
    private Tuple<chessPiece, chessTile>[][] BoardArray = new Tuple[14][14];

    /**
     * Constructor for the BoardArray class.
     */
    public BoardArray() {
        String currentColor;
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 14; j++) {
                if ((i + j) % 2 == 0) {
                    currentColor = "white";
                } else {
                    currentColor = "green";
                }
                chessPiece piece = null;
                chessTile tile = new chessTile(j * 48, i * 48, currentColor);
                Tuple<chessPiece, chessTile> tuple = new Tuple<>(piece, tile);
                BoardArray[i][j] = tuple;
            }
        }
    }

     /**
     * Draws the tiles on the given PApplet instance.
     *
     * @param app PApplet instance on which to draw the tiles
     */
    public void DrawTiles(PApplet app) {
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 14; j++) {
                BoardArray[i][j].getTile().draw(app);
            }
        }
    }

    /**
     * Draws the pieces on the given PApplet instance.
     *
     * @param app PApplet instance on which to draw the pieces
     */
    public void DrawPieces(PApplet app) {
        App.whiteAttackTiles = new ArrayList<chessTile>();
        App.blackAttackTiles = new ArrayList<chessTile>();
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 14; j++) {
                if (BoardArray[i][j].getPiece() != null) {
                    if (BoardArray[i][j].getPiece().getPieceColor().equals("white")){
                        App.whiteAttackTiles.addAll(BoardArray[i][j].getPiece().getMoves(j, i, App.boardArray));
                    }
                    if (BoardArray[i][j].getPiece().getPieceColor().equals("black")){
                        
                        App.blackAttackTiles.addAll(BoardArray[i][j].getPiece().getMoves(j, i, App.boardArray));
                    }
                    BoardArray[i][j].getPiece().draw(app);
                }
            }
        }
    }

    public  Tuple<chessPiece, chessTile>[][] getBoardArray(){
        return this.BoardArray;
    }
}