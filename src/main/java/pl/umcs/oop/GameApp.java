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
        GameCanvas gameCanvas = new GameCanvas(width, height);
        GraphicsItem.setCanvasWidth(width);
        GraphicsItem.setCanvasHeight(height);
        gameCanvas.draw();

        StackPane root = new StackPane(gameCanvas);
        Scene scene = new Scene(root, 800, 600);

        Paddle paddle = new Paddle();
        gameCanvas.setOnMouseMoved(event -> {
            double mouseX = event.getX();
            double mouseY = event.getY();
            paddle.setX(mouseX - paddle.getWidth() / 2);
        });

        Ball ball = new Ball();
        while(!gameIsOn) {
            ball.setPosition(paddle);
        }

        scene.setOnMouseClicked(event -> { startGame(); });

        while(gameIsOn) {
            ball.updatePosition();
        }

        primaryStage.setTitle("Breakout");
        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
//                ball.draw(paddle);
                paddle.draw(gameCanvas.getGraphicsContext2D());

            }
        };
        animationTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent event) {}

    public static class GameCanvas extends Canvas {
        private final GraphicsContext graphicsContext;

        public GameCanvas(int width, int height) {
            super(width, height);
            graphicsContext = this.getGraphicsContext2D();
        }

        public void draw() {
            graphicsContext.setFill(Color.BLACK);
            graphicsContext.fillRect(0, 0, getWidth(), getHeight());
        }


    }
}


