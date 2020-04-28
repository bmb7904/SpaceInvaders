package bernardi.spaceInvaders;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 *  A class that represents a Spaceship. Extends rectangle. Assumes rectangles travel
 *  in a downwards direction, relative to the play area. Has fields to keep track of
 *  position, container (play area), and number of hits
 */

public class SpaceShip extends Rectangle {

    private final int DISTANCETRAVELING = 5;

    // x,y coordinates of each rectangle (upper left of rectangle)
    private double x, y;

    // w and h of each rectangle
    double width; // in pixels
    double height; // in pixsels

    private int containerWidth;
    private int containerHeight;

    private boolean madeItToEdge;

    private Timeline timeline;

    private int numberOfHits;



    public SpaceShip(int x, int y, int width, int height, Color c, int containerWidth,
                     int containerHeight, int time) {
        super(x,y,width,height);
        super.setFill(c);

        this.containerHeight = containerHeight;
        this.containerWidth = containerWidth;

        if(y + height <= this.containerHeight) {
            this.madeItToEdge = false;
        }
        else {
            this.madeItToEdge = true;
        }

        this.numberOfHits = 0;

        KeyFrame kf = new KeyFrame(Duration.millis(time), e -> move());
        this.timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void move() {
        this.x = getX();
        this.y = getY();

        if(y + getHeight() >= containerHeight) {
            madeItToEdge = true;
        }
        else {
            y = y + DISTANCETRAVELING;
        }

        setX(x);
        setY(y);
    }

    public void shipHit() {
        numberOfHits ++;
    }

    public int getNumberOfHits() {
        return numberOfHits;
    }

    public void stop() {
        this.timeline.stop();
    }

    public boolean hitEdge() {
        return madeItToEdge;
    }
}
