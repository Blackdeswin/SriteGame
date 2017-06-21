package sample;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;


public class Main extends Application {
    private HashMap<KeyCode, Boolean> keys = new HashMap<>();
    public static ArrayList<Rectangle> bonuses = new ArrayList<>();
    Image image = new Image(getClass().getResourceAsStream("1.png"));
    ImageView imageView = new ImageView(image);
    Character player = new Character(imageView);
    static BorderPane borderPane= new BorderPane();
    static Pane root = new Pane();
    private static java.lang.String getScore;
    static Label label = new Label(getScore);


public void bonus(){
    int random = (int)Math.floor(Math.random()*100);
    int x = (int)Math.floor(Math.random()*600);
    int y = (int)Math.floor(Math.random()*600);
    if(random==5){
        Rectangle rect = new Rectangle(20,20,Color.RED);
        rect.setX(x);
        rect.setY(y);
        bonuses.add(rect);
        root.getChildren().addAll(rect);
    }
}

public void update(){
    if(isPressed(KeyCode.UP)){
        player.animation.play();
        player.animation.setOffsetY(96);
        player.moveY(-2);
    } else if(isPressed(KeyCode.DOWN)){
        player.animation.play();
        player.animation.setOffsetY(0);
        player.moveY(2);
    }
    else if (isPressed(KeyCode.RIGHT)){
        player.animation.play();
        player.animation.setOffsetY(64);
        player.moveX(2);
    } else if(isPressed(KeyCode.LEFT)){
        player.animation.play();
        player.animation.setOffsetY(32);
        player.moveX(-2);
    }
    else {player.animation.stop();}
}

public boolean isPressed(KeyCode key){
    return keys.getOrDefault(key,false);
}
    @Override
    public void start(Stage primaryStage) throws Exception {
        root.setPrefSize(600,600);
        root.getChildren().addAll(player);
        borderPane.setCenter(root);

        borderPane.setRight(label);
        Scene scene = new Scene(borderPane);
        scene.setOnKeyPressed(event -> keys.put(event.getCode(),true));
        scene.setOnKeyReleased(event -> {keys.put(event.getCode(),false);});

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                bonus();
            }
        };
        timer.start();



        primaryStage.setTitle("Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}