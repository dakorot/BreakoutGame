package pl.umcs.oop;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball extends GraphicsItem {
    private Point2D moveVector;
    private final double velocity;

    public Ball() {
        this.width = 25.0;
        this.height = 25.0;
        this.moveVector = new Point2D(1, -1);
        this.velocity = 0.4;
    }

    public void setPosition(Point2D point2D) {
        this.x = point2D.getX();
        this.y = point2D.getY();
    }

    public void setPosition(Paddle paddle) {
        this.x = paddle.getX() + paddle.getWidth() / 2 - this.width / 2;
        this.y = paddle.getY() - this.height - 15;
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

    public void bounceHorizontally() {
        this.moveVector = new Point2D(-moveVector.getX(), moveVector.getY());
    }

    public void bounceVertically() {
        this.moveVector = new Point2D(moveVector.getX(), -moveVector.getY());
    }

    public double getTop() {
        return y;
    }

    public double getRight() {
        return x + width;
    }

    public double getBottom() {
        return y + height;
    }

    public double getLeft() {
        return x;
    }
}
