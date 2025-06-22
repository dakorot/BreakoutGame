package pl.umcs.oop;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Brick extends GraphicsItem {
    private static int gridRows = 20;
    private static int gridCols = 10;
    private Color colour;

    public enum CrushType {
        NoCrush,
        HorizontalCrush,
        VerticalCrush
    }

    public Brick(int x, int y, String colour) {
        this.width = 800 / gridCols;
        this.height = 600 / gridRows;
        this.x = y * width;
        this.y = x * height;
        this.colour = Color.web(colour);
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(colour);
        graphicsContext.fillRect(x, y, width, height);
        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.setLineWidth(4);
        graphicsContext.strokeRect(x, y, width, height);
    }

    public void setGridRows(int rows) {
        gridRows = rows;
    }
    public void setGridCols(int cols) {
        gridCols = cols;
    }


    public CrushType collision(double ballTop, double ballRight, double ballBottom, double ballLeft) {
        double brickTop = this.y;
        double brickRight = this.x + this.width;
        double brickBottom = this.y + this.height;
        double brickLeft = this.x;

        boolean ballIntersectsBrick = ballTop < brickBottom &&
                ballRight > brickLeft &&
                ballBottom > brickTop &&
                ballLeft < brickRight;

        if(!ballIntersectsBrick)
            return CrushType.NoCrush;

        double overlapTop = ballBottom - brickTop;
        double overlapRight = brickRight - ballLeft;
        double overlapBottom = brickBottom - ballTop;
        double overlapLeft = ballRight - brickLeft;

        double minOverlap = Math.min(Math.min(overlapTop, overlapBottom), Math.min(overlapRight, overlapLeft));

        if(minOverlap == overlapLeft || minOverlap == overlapRight)
            return CrushType.HorizontalCrush;
        else
            return CrushType.VerticalCrush;
    }
}
