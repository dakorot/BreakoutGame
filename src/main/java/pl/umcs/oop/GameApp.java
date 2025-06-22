package pl.umcs.oop;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static pl.umcs.oop.GraphicsItem.setCanvasHeight;
import static pl.umcs.oop.GraphicsItem.setCanvasWidth;

public class GameApp extends Application implements ActionListener {
    private int width = 800;
    private int height = 600;
    private boolean gameIsOn = false;

    public static void main(String[] args) {
        launch(args);
    }

    public void startGame() {
        this.gameIsOn = true;
    }

    @Override
    public void start(Stage primaryStage) {
        GameCanvas gameCanvas = new GameCanvas();
        setCanvasWidth(width);
        setCanvasHeight(height);

        StackPane root = new StackPane(gameCanvas);
        Scene scene = new Scene(root, 800, 600);
        Paddle paddle = new Paddle();
        Ball ball = new Ball();

        gameCanvas.draw();
        paddle.draw(gameCanvas.getGraphicsContext2D());
        gameCanvas.setOnMouseMoved(event -> {
            double mouseX = event.getX();
            paddle.setX(mouseX - paddle.getWidth() / 2);
        });
        paddle.draw(gameCanvas.getGraphicsContext2D());
        primaryStage.setTitle("Breakout");
        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameCanvas.draw();
                paddle.draw(gameCanvas.getGraphicsContext2D());
                ball.draw(gameCanvas.getGraphicsContext2D());

                if(!gameIsOn) {
                    ball.setPosition(paddle);
                    scene.setOnMouseClicked(event -> { startGame(); });
                }
                else {
                    ball.updatePosition();
                }
            }
        };
        animationTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent event) {}
}


