package bernardi.spaceInvaders;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.security.Key;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;

public class TestingBullets extends Application {
    private static Pane root = new Pane();

    @Override
    public void start(Stage primaryStage) {
        final int width = 700;
        final int height = 700;
        root.setPrefSize(width,height);
        Scene scene = new Scene(root);

        String style = "-fx-background-color: WHITE;";
        root.setStyle(style);

        final int numberOfShips = 10;
        final int widthShip = 50;
        final int heightShip = 50;
        final int spacing = 50;

        ArrayList<SpaceShip> ships = new ArrayList<SpaceShip>();
        SpaceShip ship1 = new SpaceShip(100,100,widthShip,heightShip,Color.GREEN,width,
                height,
                1000);
        SpaceShip ship2 = new SpaceShip(200,100,widthShip,heightShip,Color.GREEN,width,
                height,
                1000);
        SpaceShip ship3 = new SpaceShip(300,100,widthShip,heightShip,Color.GREEN,width,
                height,
                1000);
        SpaceShip ship4 = new SpaceShip(400,100,widthShip,heightShip,Color.GREEN,width,
                height,
                1000);
        SpaceShip ship5 = new SpaceShip(500,100,widthShip,heightShip,Color.GREEN,width,
                height,
                1000);
        SpaceShip ship6 = new SpaceShip(600,100,widthShip,heightShip,Color.GREEN,width,
                height,
                1000);
        SpaceShip ship7 = new SpaceShip(100,200,widthShip,heightShip,Color.GREEN,width,
                height,
                1000);
        SpaceShip ship8 = new SpaceShip(200,200,widthShip,heightShip,Color.GREEN,width,
                height,
                1000);
        SpaceShip ship9 = new SpaceShip(300,200,widthShip,heightShip,Color.GREEN,width,
                height,
                1000);
        SpaceShip ship10 = new SpaceShip(400,200,widthShip,heightShip,Color.GREEN,width,
                height,
                1000);
        SpaceShip ship11 = new SpaceShip(500,200,widthShip,heightShip,Color.GREEN,width,
                height,
                1000);
        SpaceShip ship12 = new SpaceShip(600,200,widthShip,heightShip,Color.GREEN,width,
                height,
                1000);

        ships.add(ship1);
        ships.add(ship2);
        ships.add(ship3);
        ships.add(ship4);
        ships.add(ship5);
        ships.add(ship6);
        ships.add(ship7);
        ships.add(ship8);
        ships.add(ship9);
        ships.add(ship10);
        ships.add(ship11);
        ships.add(ship12);

        for(int i = 0; i < ships.size(); i ++) {
            root.getChildren().add(ships.get(i));
        }

        root.setOnMouseClicked(event -> {
            Bullet newBullet = new Bullet(event.getX(), event.getY(), 5, Color.BLUE,
                    width, height, 'U', 25);
            root.getChildren().add(newBullet);
        });

        KeyFrame kf = new KeyFrame(Duration.millis(300), e -> deleteOldBullets());
        Timeline timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        KeyFrame kf2 = new KeyFrame(Duration.millis(20), event -> {
            for(int i = 0; i < ships.size(); i ++) {
                for(int j = 0; j < root.getChildren().size(); j ++) {
                    if(root.getChildren().get(j) instanceof Bullet) {
                        if(ships.get(i).intersects(((Bullet)(root.getChildren().get(j))).getBoundsInLocal())) {
                            ships.get(i).shipHit();
                            root.getChildren().remove(root.getChildren().get(j));
                            }
                        }
                    }
                if(ships.get(i).getNumberOfHits() >= 3) {
                    root.getChildren().remove(i);
                    ships.remove(i);
                }
            }
        });

        Timeline timeline2 = new Timeline(kf2);
        timeline2.setCycleCount(Animation.INDEFINITE);
        timeline2.play();


        primaryStage.setScene(scene);
        primaryStage.setTitle("Testing Bullets");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     *  Method that iterates through the list of children of the root Pane and checks
     *  each node (Bullet) to see if it is in still inside the boundaries of the game
     *  grid. If the bullet is not in the game grid, it is removed from the list of
     *  children of the root pane.
     */
    private static void deleteOldBullets() {
        for(int i = 0; i < root.getChildren().size(); i ++) {

            if(root.getChildren().get(i) instanceof Bullet) {
                if (((Bullet) (root.getChildren().get(i))).isBulletInBounds() == false) {
                    root.getChildren().remove(i);
                }
            }
        }
    }

}
