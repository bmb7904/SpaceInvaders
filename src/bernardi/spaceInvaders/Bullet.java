package bernardi.spaceInvaders;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import sun.security.krb5.internal.SeqNumber;

/**
 * A custom derived class that extends the Circle class. Represents a bullet. A bullet
 * will have fields and methods that allow it to be fired and travel through the playing
 * area. Assumes the bullet travels in a straight line depending on initial direction.
 *
 * @author Brett Bernardi
 */

public class Bullet extends Circle {

    // the distance in pixels that the bullet will move each frame
    private static final int DISTANCEMOVEDEACHFRAME = 10;

    // the coordinates of the bullet in (x,y) of the center of the circle
    private double x;
    private double y;

    // the size of the container or play area. The lower left hand corner of the
    // rectangular play area is on the origin in the x,y coordinate plane
    private int containerWidth; // width in pixels
    private int containerHeight; // height in pixels

    // The change in both x and y. Will indicate the direction the bullet will move
    private int dx;
    private int dy;

    private boolean bulletInBounds;

    // updates coordinates of bullet in discrete time intervals
    private final Timeline timeline;

    /**
     *  Constructor for the derived Bullet class.
     * @param x - The initial x position of the bullet
     * @param y - The initial x position of the bullet
     * @param radius - radius of bullet
     * @param c - color of bullet
     * @param containerWidth - width of playing area in pixels
     * @param containerHeight - height of playing area in pixels
     * @param direction - direction bullet is fired in (U,D,L,R)
     * @param time - how often to update animation
     */
    public Bullet(double x, double y, int radius, Color c, int containerWidth,
                  int containerHeight, char direction, int time) {
        super(x,y,radius,c); // calls the super constructor and creates circle object
        this.containerHeight = containerHeight;
        this.containerWidth = containerWidth;
        pointGun(direction);
        bulletInBounds = true;

        KeyFrame keyFrame = new KeyFrame(Duration.millis(time), e -> fire());
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    /**
     *  Sets the direction that the bullet will travel depending on where the gun if
     *  aimed.
     */
    public void pointGun(char dir) {
        switch(dir) {
            case 'U':
                this.dx = 0;
                this.dy = -DISTANCEMOVEDEACHFRAME;
                break;
            case 'D':
                this.dx = 0;
                this.dy = DISTANCEMOVEDEACHFRAME;
            case 'L':
                this.dx = -DISTANCEMOVEDEACHFRAME;
                this.dy = 0;
                break;
            case 'R':
                this.dx = DISTANCEMOVEDEACHFRAME;
                this.dy = 0;
                break;
            default:
                System.out.println("Invalid Direction!");
        }

    }

    /**
     *  Fires the gun
     */
    private void fire() {
        x = getCenterX();
        y = getCenterY();
        double radius = getRadius();


        if((x - radius) <= containerWidth && (x + radius) >= 0){
            x += dx;
        }
        else {
            bulletInBounds = false;
        }

        if((y + radius) >= 0 && (y - radius) <= containerHeight) {
            y += dy;
        }
        else {
            bulletInBounds = false;
        }

        setCenterX(x);
        setCenterY(y);

    }

    /**
     * Returns true if ball is currently inside the boudaries of the play field. False
     * otherwise.
     */
    public boolean isBulletInBounds() {
        return bulletInBounds;
    }

    /**
     * Stops the animation for the bullet.
     */
    public void stopBullet() {
        timeline.stop();
    }




}
