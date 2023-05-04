package XXLChess;
import processing.core.PApplet;
import processing.core.PFont;

/**
 * TimerAndScore class responsible for managing and displaying timers and scores
 * for both white and black players.
 */
public class TimerAndScore {
    private PApplet parent;

    /**
     * Constructor for TimerAndScore class.
     *
     * @param parent the parent PApplet instance
     */
    public TimerAndScore(PApplet parent) {
        this.parent = parent;
    }

     /**
     * Draws the timers and scores for both white and black players.
     * This method should be called within the draw() method of the parent PApplet.
     */
    public void drawTimerAndScore() {
        PFont myFont = parent.createFont("Arial", 32);
        parent.textFont(myFont);
        if (App.isWhiteTurn) {
            App.whiteTickCount++;
            if (App.whiteTickCount == 80) {
                App.whiteTotalSeconds = App.whiteTotalSeconds - 1;
                int minutes = App.whiteTotalSeconds / 60;
                int seconds = App.whiteTotalSeconds % 60;
                App.whiteTimeRemaining = String.format("%d:%02d", minutes, seconds);
                App.whiteTickCount = 0;
            }
            parent.fill(192, 192, 192);
            parent.rect(14 * 48, 0, 3 * 48, 16 * 48);
            parent.fill(0);
            parent.text(App.blackTimeRemaining, 14 * 50, 2 * 40);
            parent.text(String.format("%.2f", App.blackScore), 14 * 50, 3 * 40);
            parent.text(App.whiteTimeRemaining, 14 * 50, 12 * 48);
            parent.text(String.format("%.2f", App.whiteScore), 14 * 50, 13 * 48);
        } else if (!App.isWhiteTurn) {
            App.blackTickCount++;
            if (App.blackTickCount == 80) {
                App.blackTotalSeconds = App.blackTotalSeconds - 1;
                int minutes = App.blackTotalSeconds / 60;
                int seconds = App.blackTotalSeconds % 60;
                App.blackTimeRemaining = String.format("%d:%02d", minutes, seconds);
                App.blackTickCount = 0;
            }
            parent.fill(192, 192, 192);
            parent.rect(14 * 48, 0, 3 * 48, 16 * 48);
            parent.fill(0);
            parent.text(App.blackTimeRemaining, 14 * 50, 2 * 40);
            parent.text(String.format("%.2f", App.blackScore), 14 * 50, 3 * 40);
            parent.text(App.whiteTimeRemaining, 14 * 50, 12 * 48);
            parent.text(String.format("%.2f", App.whiteScore), 14 * 50, 13 * 48);
        }
        if (App.whiteTotalSeconds <= 0) {
            App.blackWin = true;
        } else if (App.blackTotalSeconds <= 0) {
            App.whiteWin = true;
        }
    }
}
