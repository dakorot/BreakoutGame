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
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        int width = 800;
        int height = 600;
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

        primaryStage.setTitle("Breakout");
        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameCanvas.draw();
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


