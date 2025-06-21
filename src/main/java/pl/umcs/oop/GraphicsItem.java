package pl.umcs.oop;

import javafx.scene.canvas.GraphicsContext;

public abstract class GraphicsItem {
    protected static int canvasWidth;
    protected static int canvasHeight;
    protected double x;
    protected double y;
    protected double width;
    protected double height;

    public static void setCanvasWidth(int canvasWidth) {
        GraphicsItem.canvasWidth = canvasWidth;
    }

    public static void setCanvasHeight(int canvasHeight) {
        GraphicsItem.canvasHeight = canvasHeight;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public abstract void draw(GraphicsContext graphicsContext);
}
