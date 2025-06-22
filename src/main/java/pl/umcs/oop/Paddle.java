package pl.umcs.oop;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Paddle extends GraphicsItem {
    public Paddle() {
        this.width = 80;
        this.height = 15;
        this.x = (canvasWidth - width) / 2;
        this.y = canvasHeight - height - 10;
    }

    public void setX(double x) {
        if(x < 0 )
            this.x = 10;
        else if(x + width > canvasWidth)
            this.x = canvasWidth - width - 10;
        else
            this.x = x;
    }

    public boolean collision(Ball ball) {
        double ballTop = ball.getTop();
        double ballRight = ball.getRight();
        double ballBottom = ball.getBottom();
        double ballLeft = ball.getLeft();

        double paddleTop = this.getY();
        double paddleRight = this.getX() + this.getWidth();
        double paddleBottom = this.getY() + this.getHeight();
        double paddleLeft = this.getX();

        return (ballTop < paddleTop && ballBottom >= paddleTop) &&
                (ballRight > paddleLeft && ballLeft < paddleRight);
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRect(x, y, width, height);
    }
}
