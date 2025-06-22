package pl.umcs.oop;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.scene.Scene;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static pl.umcs.oop.GraphicsItem.setCanvasHeight;
import static pl.umcs.oop.GraphicsItem.setCanvasWidth;

public class GameApp extends Application implements ActionListener {
    private int width = 800;
    private int height = 600;
    private boolean gameIsOn = false;

    public void startGame() {
        this.gameIsOn = true;
    }

    private void displayWinMessage(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.setFont(new Font("Arial", 48));
        graphicsContext.setTextAlign(TextAlignment.CENTER);
        graphicsContext.setTextBaseline(VPos.CENTER);
        graphicsContext.fillText("Wygrałeś!", graphicsContext.getCanvas().getWidth() / 2, graphicsContext.getCanvas().getHeight() / 2);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            GameCanvas gameCanvas = new GameCanvas();
            setCanvasWidth(width);
            setCanvasHeight(height);

            StackPane root = new StackPane(gameCanvas);
            Scene scene = new Scene(root, 800, 600);
            Paddle paddle = new Paddle();
            Ball ball = new Ball();

            gameCanvas.draw();
            paddle.draw(gameCanvas.getGraphicsContext2D());
            gameCanvas.setOnMouseMoved(event -> {
                double mouseX = event.getX();
                paddle.setX(mouseX - paddle.getWidth() / 2);
            });

            paddle.draw(gameCanvas.getGraphicsContext2D());

            primaryStage.setTitle("Breakout");
            primaryStage.setScene(scene);
            primaryStage.show();

            AnimationTimer animationTimer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    gameCanvas.draw();
                    paddle.draw(gameCanvas.getGraphicsContext2D());
                    ball.draw(gameCanvas.getGraphicsContext2D());

                    if (!gameIsOn) {
                        ball.setPosition(paddle);
                        scene.setOnMouseClicked(event -> {
                            startGame();
                        });
                    } else {
                        ball.updatePosition();
                    }
                    if (ball.getLeft() <= 0 || ball.getRight() >= gameCanvas.getWidth()) {
                        ball.bounceHorizontally();
                    }
                    else if (ball.getTop() <= 0) {
                        ball.bounceVertically();
                    }
                    else if (ball.getBottom() >= gameCanvas.getHeight()) {
                        gameIsOn = false;
                        gameCanvas.resetBricks();
                    }

                    if (gameCanvas.getBricks().isEmpty()) {
                        gameIsOn = false;
                        displayWinMessage(gameCanvas.getGraphicsContext2D());}

                    List<Brick> bricks = gameCanvas.getBricks();
                    for (int i = 0; i < bricks.size(); ++i) {
                        Brick brick = bricks.get(i);
                        Brick.CrushType crush = brick.collision(ball.getTop(), ball.getRight(), ball.getBottom(), ball.getLeft());
                        if (crush != Brick.CrushType.NoCrush) {
                            if (crush == Brick.CrushType.HorizontalCrush) {
                                ball.bounceHorizontally();
                            } else if (crush == Brick.CrushType.VerticalCrush) {
                                ball.bounceVertically();
                            }
                            bricks.remove(i);
                            --i;
                        }
                    }
                    if (paddle.collision(ball)) {
                        ball.bounceVertically();
                        ball.setPosition(new Point2D(ball.getLeft(), paddle.getY() - ball.getHeight() - 1));
                    }
                }
                        };
                        animationTimer.start();
                }
        catch(
                Exception e)

                {
                    e.printStackTrace();
                }
            }

                public static void main(String[] args) {
                    launch(args);
                }

                @Override
                public void actionPerformed(ActionEvent event) {
                }
            }