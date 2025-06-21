package pl.umcs.oop;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball extends GraphicsItem {
    private Point2D moveVector;
    private double velocity;

    public void setPosition(Point2D point2D) {
        this.x = 400.0;
        this.y = 300.0;
        this.height = 15.0;
        this.width = 15.0;
        this.moveVector = new Point2D(1, 1);
        this.velocity = 5.0;
    }

    public void setPosition(Paddle paddle) {
        this.x = paddle.getX() + paddle.getWidth() / 2 - this.width / 2;
        this.y = paddle.getY() - this.height;
    }

    public void updatePosition() {
        this.x += moveVector.getX() * velocity;
        this.y += moveVector.getY() * velocity;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillOval(x, y, width, height);
    }
}
