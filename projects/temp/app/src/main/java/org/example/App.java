package org.example.app;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;

public class App extends Application {
    private static final int W = 800, H = 600;

    private final Set<KeyCode> keys = new HashSet<>();
    private double px = 100, py = 100; // player position
    private double speed = 220; // px/s

    // enemy (simple bouncing ball)
    private double ex = 400, ey = 300;
    private double evx = 180, evy = 140; // px/s

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(W, H);
        GraphicsContext g = canvas.getGraphicsContext2D();

        Scene scene = new Scene(new Group(canvas), W, H, Color.BLACK);
        scene.setOnKeyPressed(e -> keys.add(e.getCode()));
        scene.setOnKeyReleased(e -> keys.remove(e.getCode()));

        stage.setTitle("JavaFX Mini Game");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        final long[] last = { System.nanoTime() };

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                double dt = (now - last[0]) / 1_000_000_000.0;
                last[0] = now;

                update(dt);
                render(g);
            }
        }.start();
    }

    private void update(double dt) {
        // input
        double vx = 0, vy = 0;
        if (keys.contains(KeyCode.W) || keys.contains(KeyCode.UP))
            vy -= 1;
        if (keys.contains(KeyCode.S) || keys.contains(KeyCode.DOWN))
            vy += 1;
        if (keys.contains(KeyCode.A) || keys.contains(KeyCode.LEFT))
            vx -= 1;
        if (keys.contains(KeyCode.D) || keys.contains(KeyCode.RIGHT))
            vx += 1;

        // normalize
        double len = Math.hypot(vx, vy);
        if (len > 0) {
            vx /= len;
            vy /= len;
        }

        px += vx * speed * dt;
        py += vy * speed * dt;

        // bounds for player (40x40 box)
        px = Math.max(0, Math.min(px, W - 40));
        py = Math.max(0, Math.min(py, H - 40));

        // enemy bounce
        ex += evx * dt;
        ey += evy * dt;
        if (ex < 10 || ex > W - 10)
            evx = -evx;
        if (ey < 10 || ey > H - 10)
            evy = -evy;

        // simple collision (player box vs enemy circle radius 10)
        double cx = Math.max(px, Math.min(ex, px + 40));
        double cy = Math.max(py, Math.min(ey, py + 40));
        double dist2 = (cx - ex) * (cx - ex) + (cy - ey) * (cy - ey);
        if (dist2 < 10 * 10) {
            // knock player back a bit
            px -= Math.signum(evx) * 60 * dt;
            py -= Math.signum(evy) * 60 * dt;
        }
    }

    private void render(GraphicsContext g) {
        g.setFill(Color.BLACK);
        g.fillRect(0, 0, W, H);

        // player
        g.setFill(Color.WHITE);
        g.fillRect(px, py, 40, 40);

        // enemy
        g.setFill(Color.CORNFLOWERBLUE);
        g.fillOval(ex - 10, ey - 10, 20, 20);

        // HUD
        g.setFill(Color.LIGHTGREEN);
        g.fillText("WASD / Arrows to move", 12, 20);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
