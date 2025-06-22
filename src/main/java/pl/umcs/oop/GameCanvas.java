package pl.umcs.oop;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class GameCanvas extends Canvas {
    private final GraphicsContext graphicsContext;
    private static int width = 800;
    private static int height = 600;
    private List<Brick> bricks;

    public GameCanvas() {
        super(width, height);
        graphicsContext = this.getGraphicsContext2D();
        bricks = new ArrayList<>();
        loadLevel();
    }

    public List<Brick> getBricks() {
        return bricks;
    }

    public void draw() {
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0, 0, getWidth(), getHeight());
        for (Brick brick : bricks) {
            brick.draw(graphicsContext);
        }

    }

    public boolean shouldBallBounceHorizontally() {
        return false;
    }

    public boolean shouldBallBounceVertically() {
        return false;
    }

    public boolean shouldBallBounceFromPaddle() {
        return false;
    }

    public void loadLevel() {
        int gridRows = 20;
        int gridCols = 10;

        Color[] colours = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE};

        for(int x=2; x<7; ++x) {
            for(int y=0; y<gridCols; ++y) {
                bricks.add(new Brick(x, y, colours[x-2].toString()));
            }
        }
    }

    public void resetBricks() {
        bricks.clear();
        loadLevel();
    }
}