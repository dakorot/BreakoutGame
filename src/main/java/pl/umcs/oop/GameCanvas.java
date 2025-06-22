package pl.umcs.oop;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameCanvas extends Canvas {
    private final GraphicsContext graphicsContext;
    private static int width = 800;
    private static int height = 600;

    public GameCanvas() {
        super(width, height);
        graphicsContext = this.getGraphicsContext2D();
    }

    public void draw() {
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0, 0, getWidth(), getHeight());
    }
}
