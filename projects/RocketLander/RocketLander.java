import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class RocketLander extends Application {
    // Canvas
    private static final int W = 600;
    private static final int H = 800;
    private static final double GROUND_Y = H - 80;

    // Physics state
    private double x, y; // position (px)
    private double vx, vy; // velocity (px/s)
    private double angle; // radians (0 -> facing right; we’ll start pointing up)
    private boolean thrusting = false;
    private boolean leftHeld = false, rightHeld = false;

    // Game params
    private double gravity = 50; // px/s^2 downward
    private double thrust = 150; // px/s^2 along rocket nose
    private double rotSpeed = Math.toRadians(120); // rad/s
    private double fuel = 100; // %
    private boolean gameOver = false;
    private String message = "";
    private double safeLandingSpeed = 50; // px/s
    private double safeLandingAngle = Math.toRadians(15); // from upright
    private double safeFuelMargin = 30; // %

    // Landing pad
    private double padX, padW;

    // Timing
    private long lastTimeNs;

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(W, H);
        GraphicsContext g = canvas.getGraphicsContext2D();

        Group root = new Group(canvas);
        Scene scene = new Scene(root, W, H, Color.web("#0b1021"));

        // Input
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT)
                leftHeld = true;
            if (e.getCode() == KeyCode.RIGHT)
                rightHeld = true;
            if (e.getCode() == KeyCode.UP)
                thrusting = true;

            if (e.getCode() == KeyCode.R)
                reset();
        });
        scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.LEFT)
                leftHeld = false;
            if (e.getCode() == KeyCode.RIGHT)
                rightHeld = false;
            if (e.getCode() == KeyCode.UP)
                thrusting = false;
        });

        stage.setTitle("Rocket Lander - Arrow keys to fly, R to reset");
        stage.setScene(scene);
        stage.show();

        reset(); // start state
        lastTimeNs = System.nanoTime();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                double dt = (now - lastTimeNs) / 1_000_000_000.0; // seconds
                // Clamp dt to avoid huge jumps if window stalls
                if (dt > 0.05)
                    dt = 0.05;
                lastTimeNs = now;

                update(dt);
                render(g);
            }
        }.start();
    }

    private void reset() {
        // Start near top center, facing up
        x = W / 2.0;
        y = 120;
        vx = 0;
        vy = 0;
        angle = -Math.PI / 2; // -90° (up)
        fuel = 100;
        gameOver = false;
        message = "";

        padW = 120;
        padX = Math.random() * (W - padW - 40) + 20; // avoid edges
    }

    private void update(double dt) {
        if (gameOver)
            return;

        // Rotation
        if (leftHeld)
            angle -= rotSpeed * dt;
        if (rightHeld)
            angle += rotSpeed * dt;

        // Acceleration
        double ax = 0;
        double ay = gravity; // downwards is +y

        if (thrusting && fuel > 0) {
            ax += Math.cos(angle) * thrust;
            ay += Math.sin(angle) * thrust;
            fuel -= 20 * dt; // burn rate
            if (fuel < 0)
                fuel = 0;
        }

        // Integrate velocity & position
        vx += ax * dt;
        vy += ay * dt;
        x += vx * dt;
        y += vy * dt;

        // Screen wrap horizontally (keeps it playful)
        if (x < -20)
            x = W + 20;
        if (x > W + 20)
            x = -20;

        // Ground collision
        if (y >= GROUND_Y) {
            y = GROUND_Y;
            // Evaluate landing
            double speed = Math.hypot(vx, vy);
            double angleDeg = Math.toDegrees(angle);
            // Consider "upright" if within ±15 degrees of up (-90°)
            double uprightError = Math.abs(normalizeAngleDeg(angleDeg + 90));
            gameOver = true;

            if (speed > safeLandingSpeed) {
                message = "CRASH!  Speed: " + (int) speed + " px/s";
                return;
            }

            if (Math.abs(uprightError) > Math.toDegrees(safeLandingAngle)) {
                message = String.format("CRASH!  BAD ANGLE! %.1f°", uprightError);
                return;
            }

            if (x < padX || x > padX + padW) {
                message = "CRASH!  Missed Pad";
                return;
            }

            message = "SAFE LANDING!";

        }
    }

    // Normalize angle to [-180, 180]
    private double normalizeAngleDeg(double a) {
        a = a % 360;
        if (a > 180)
            a -= 360;
        if (a < -180)
            a += 360;
        return a;
    }

    private void render(GraphicsContext g) {
        // Background
        g.setFill(Color.web("#0b1021"));
        g.fillRect(0, 0, W, H);

        // Stars
        g.setFill(Color.web("#b2c1ff", 0.5));
        for (int i = 0; i < 80; i++) {
            double sx = (i * 97) % W;
            double sy = (i * 53) % (H - 100);
            g.fillRect(sx, sy, 2, 2);
        }

        // Ground
        g.setFill(Color.web("#1f2b3e"));
        g.fillRect(0, GROUND_Y, W, H - GROUND_Y);
        g.setStroke(Color.web("#3c557a"));
        g.setLineWidth(2);
        g.strokeLine(0, GROUND_Y, W, GROUND_Y);

        // Landing pad (optional target)
        g.setFill(Color.web("#354f6b"));
        g.fillRect(padX, GROUND_Y - 6, padW, 6);

        // Rocket
        drawRocket(g);

        // HUD
        g.setFill(Color.WHITE);
        g.setFont(Font.font("Consolas", 18));
        g.fillText("Controls: ← → rotate, ↑ thrust, R reset", 16, 32);

        double speed = Math.hypot(vx, vy);
        double angleDeg = Math.toDegrees(angle + Math.PI / 2);

        g.setFill(fuel > safeFuelMargin ? Color.LIME : Color.ORANGERED);
        g.fillText("Fuel: " + (int) fuel + "%", x + 24, y);
        g.setFill(speed < safeLandingSpeed ? Color.LIME : Color.ORANGERED);
        g.fillText(String.format("Speed: %.0f px/s", Math.hypot(vx, vy)), x + 24, y + 16);
        g.setFill(Math.abs(normalizeAngleDeg(angleDeg)) < Math.toDegrees(safeLandingAngle) ? Color.LIME
                : Color.ORANGERED);
        g.fillText("Angle: " + (int) angleDeg + "°", x + 24, y + 32);

        if (gameOver) {
            g.setFont(Font.font("Consolas", 32));
            g.setFill(message.startsWith("SAFE") ? Color.LIME : Color.ORANGERED);
            g.fillText(message, 16, 140);
            g.setFont(Font.font("Consolas", 20));
            g.setFill(Color.WHITE);
            g.fillText("Press R to try again.", 16, 160);
        }
    }

    private void drawRocket(GraphicsContext g) {
        // Simple triangle+legs; scale ~ 26 px tall
        double r = 14; // half-width
        double h = 26; // height
        // Triangle points in local space (nose at (0,-h/2))
        double[] px = { 0, -r, r };
        double[] py = { -h / 2, h / 2, h / 2 };

        // Rotate & translate
        double ca = Math.cos(angle + Math.PI / 2); // +90° to point up
        double sa = Math.sin(angle + Math.PI / 2);
        double[] sx = new double[3];
        double[] sy = new double[3];
        for (int i = 0; i < 3; i++) {
            sx[i] = x + px[i] * ca - py[i] * sa;
            sy[i] = y + px[i] * sa + py[i] * ca;
        }

        g.setFill(Color.SILVER);
        g.fillPolygon(sx, sy, 3);
        g.setStroke(Color.BLACK);
        g.setLineWidth(1.5);
        g.strokePolygon(sx, sy, 3);

        // Flame when thrusting
        if (thrusting && fuel > 0 && !gameOver) {
            double fx = x + (0) * ca - (h / 2 + 8) * sa;
            double fy = y + (0) * sa + (h / 2 + 8) * ca;
            g.setFill(Color.web("#ffcc33"));
            g.fillOval(fx - 6, fy - 6, 12, 12);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
