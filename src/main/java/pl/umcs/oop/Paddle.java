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

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRect(x, y, width, height);
    }
}
