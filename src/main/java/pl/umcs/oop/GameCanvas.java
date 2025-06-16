package pl.umcs.oop;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.paint.Color;

import static javafx.application.Application.launch;

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
