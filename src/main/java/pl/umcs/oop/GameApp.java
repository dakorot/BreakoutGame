package pl.umcs.oop;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameApp extends Application {

    public static void main(String[] args) {
        launch(args); // Launches the JavaFX application
    }

    @Override
    public void start(Stage primaryStage) {
        GameCanvas gameCanvas = new GameCanvas(800, 600);
        gameCanvas.draw(); // Call draw to render something

        StackPane root = new StackPane(gameCanvas);
        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("Game Canvas Example");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public class GameCanvas extends Canvas {
        private GraphicsContext graphicsContext;

        public GameCanvas(int width, int height) {
            super(height, width);
            graphicsContext = this.getGraphicsContext2D();
        }

        public void draw() {
            graphicsContext.setFill(Color.BLACK);
            graphicsContext.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}


