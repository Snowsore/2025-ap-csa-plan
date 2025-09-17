import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class RocketLander extends Application {
    // Canvas
    private static final int W = 1000;
    private static final int H = 800;
    private static final double GROUND_Y = H - 80;

    // Rocket geometry
    private static final double ROCKET_R = 14; // half-width
    private static final double ROCKET_H = 26; // height

    // Physics state
    private double x, y; // position (px)
    private double vx, vy; // velocity (px/s)
    private double angle; // radians (0 -> facing right; we’ll start pointing up)
    private boolean thrusting = false;
    private boolean leftHeld = false, rightHeld = false;
    private boolean stabilizer = false;

    // Game params
    private double gravity = 50; // px/s^2 downward
    private double thrust = 700; // px/s^2 along rocket nose
    private double rotSpeed = Math.toRadians(120); // rad/s
    private double fuel = 100; // %
    private boolean gameOver = false;
    private String message = "";
    private double safeLandingSpeed = 40; // px/s
    private double safeLandingAngle = Math.toRadians(10); // from upright
    private double safeFuelMargin = 30; // %
    private double fuelConsumptionRate = 1; // % per second at full thrust
    private double timer = 0;
    private double defaultTimer = 120;

    // Landing pad
    private double padX, padW;

    // Timing
    private long lastTimeNs;

    // --------- Particles ----------
    private static class Particle {
        double x, y;
        double vx, vy;
        double size;
        double age, life; // seconds
        double startSize, endSize;
        Color startColor, endColor;

        Particle(double x, double y, double vx, double vy,
                double startSize, double endSize,
                double life, Color startColor, Color endColor) {
            this.x = x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
            this.size = startSize;
            this.startSize = startSize;
            this.endSize = endSize;
            this.life = life;
            this.startColor = startColor;
            this.endColor = endColor;
        }

        boolean update(double dt) {
            age += dt;
            x += vx * dt;
            y += vy * dt;
            double t = Math.min(Math.max(age / life, 0), 1);
            size = startSize + (endSize - startSize) * t;
            return age < life;
        }

        void render(GraphicsContext g) {
            double t = Math.min(Math.max(age / life, 0), 1);
            double a = 1.0 - t; // fade out
            Color c = startColor.interpolate(endColor, t);
            g.setGlobalAlpha(a);
            g.setFill(c);
            g.fillOval(x - size * 0.5, y - size * 0.5, size, size);
            g.setGlobalAlpha(1.0);
        }
    }

    private final List<Particle> particles = new ArrayList<>();
    private final Random rnd = new Random();

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
            if (e.getCode() == KeyCode.S)
                stabilizer = true;
        });
        scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.LEFT)
                leftHeld = false;
            if (e.getCode() == KeyCode.RIGHT)
                rightHeld = false;
            if (e.getCode() == KeyCode.UP)
                thrusting = false;
            if (e.getCode() == KeyCode.S)
                stabilizer = false;
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
                if (dt > 0.05)
                    dt = 0.05; // clamp
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
        particles.clear();

        padW = 60;
        padX = Math.random() * (W - padW - 40) + 20; // avoid edges

        timer = defaultTimer;
    }

    private void update(double dt) {
        if (gameOver)
            return;

        timer -= dt;
        if (timer <= 0) {
            message = "OUT OF TIME!";
            gameOver = true;
            return;
        }

        // Auto-stabilizer (hold down S)
        if (stabilizer && fuel > 0) {
            double angleDeg = Math.toDegrees(angle + Math.PI / 2);
            double uprightError = normalizeAngleDeg(angleDeg);
            if (Math.abs(uprightError) > 1) {
                if (uprightError > 0)
                    angle -= rotSpeed * dt;
                else
                    angle += rotSpeed * dt;
                fuel -= fuelConsumptionRate * 0.5 * dt;
                if (fuel < 0)
                    fuel = 0;
            }
        }

        // Rotation
        if (leftHeld)
            angle -= rotSpeed * dt;
        if (rightHeld)
            angle += rotSpeed * dt;

        // Acceleration
        double ax = 0, ay = gravity; // downwards is +y
        if (thrusting && fuel > 0) {
            ax += Math.cos(angle) * thrust;
            ay += Math.sin(angle) * thrust;
            fuel -= fuelConsumptionRate * dt; // burn rate
            if (fuel < 0)
                fuel = 0;
        }

        // Integrate velocity & position
        vx += ax * dt;
        vy += ay * dt;
        x += vx * dt;
        y += vy * dt;

        // Spawn exhaust particles
        spawnExhaust(dt);

        // Update particles
        updateParticles(dt);

        // Ground collision
        if (y >= GROUND_Y) {
            y = GROUND_Y;
            double speed = Math.hypot(vx, vy);
            double angleDeg = Math.toDegrees(angle);
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

    private void spawnExhaust(double dt) {
        double speed = Math.hypot(vx, vy);
        boolean moving = speed > 5;

        if ((!moving && !(thrusting && fuel > 0)) || gameOver)
            return;

        // Nozzle world position & tail direction
        double[] nozzle = getNozzlePosition();
        double nx = nozzle[0], ny = nozzle[1];

        double ca = Math.cos(angle + Math.PI / 2);
        double sa = Math.sin(angle + Math.PI / 2);
        double tx = -sa; // tail/outflow direction
        double ty = ca;

        double baseRate = thrusting && fuel > 0 ? 140 : 35; // particles/s
        int count = (int) Math.ceil(baseRate * dt);

        for (int i = 0; i < count; i++) {
            double jitterR = 3.0;
            double px = nx + (rnd.nextDouble() * 2 - 1) * jitterR;
            double py = ny + (rnd.nextDouble() * 2 - 1) * jitterR;

            double push = thrusting && fuel > 0 ? (160 + rnd.nextDouble() * 60) : (40 + rnd.nextDouble() * 40);
            double inherit = 0.25;
            double lateral = (rnd.nextDouble() * 2 - 1) * 40;

            double lx = ca;
            double ly = sa;

            double pvx = tx * push + vx * inherit + lx * lateral;
            double pvy = ty * push + vy * inherit + ly * lateral;

            double life = (thrusting && fuel > 0 ? 3.6 : 0.45) * (0.75 + rnd.nextDouble() * 0.5);
            double s0 = 4 + rnd.nextDouble() * 3;
            double s1 = 1 + rnd.nextDouble() * 1.5;

            Color c0 = thrusting && fuel > 0 ? Color.web("#ffd166") : Color.web("#a8b3ff");
            Color c1 = thrusting && fuel > 0 ? Color.web("#ff4d4d") : Color.web("#6f7cff");

            particles.add(new Particle(px, py, pvx, pvy, s0, s1, life, c0, c1));
        }

        if (particles.size() > 1200) {
            particles.subList(0, particles.size() - 1200).clear();
        }
    }

    private void updateParticles(double dt) {
        double drag = 0.85;
        Iterator<Particle> it = particles.iterator();
        while (it.hasNext()) {
            Particle p = it.next();
            p.vx *= Math.pow(drag, dt * 60);
            p.vy *= Math.pow(drag, dt * 60);
            if (!p.update(dt))
                it.remove();
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

    // Get world position of engine nozzle (just under the tail)
    private double[] getNozzlePosition() {
        double ca = Math.cos(angle + Math.PI / 2);
        double sa = Math.sin(angle + Math.PI / 2);
        double nx = x + (0) * ca - (ROCKET_H / 2 + 8) * sa;
        double ny = y + (0) * sa + (ROCKET_H / 2 + 8) * ca;
        return new double[] { nx, ny };
    }

    private void render(GraphicsContext g) {
        // Background (screen space)
        g.setFill(Color.web("#0b1021"));
        g.fillRect(0, 0, W, H);

        // === CAMERA: translate world so the rocket is at screen center ===
        double camX = W / 2.0 - x;
        double camY = H / 2.0 - y;

        g.save();
        g.translate(camX, camY);

        // ------ World-space rendering (moves with camera) ------

        // Stars (simple pseudo field)
        g.setFill(Color.web("#b2c1ff", 0.5));
        for (int i = 0; i < 80; i++) {
            double sx = (i * 97) % W; // demo star positions
            double sy = (i * 53) % (H - 100);
            g.fillRect(sx, sy, 2, 2);
        }

        // Ground & pad
        g.setFill(Color.web("#1f2b3e"));
        g.fillRect(-10_000, GROUND_Y, 20_000, 10_000); // wide ground strip
        g.setStroke(Color.web("#3c557a"));
        g.setLineWidth(2);
        g.strokeLine(-10_000, GROUND_Y, 10_000, GROUND_Y);

        g.setFill(Color.web("#354f6b"));
        g.fillRect(padX, GROUND_Y - 6, padW, 6);

        // Particles
        for (Particle p : particles)
            p.render(g);

        // Rocket
        drawRocket(g);

        g.restore(); // === end camera transform ===

        // ------ HUD (screen space; does not move) ------
        g.setFill(Color.WHITE);
        g.setFont(Font.font("Consolas", 18));
        g.fillText("Controls: ← → rotate, ↑ thrust, S stabilizer, R reset", 16, 32);

        double speed = Math.hypot(vx, vy);
        double angleDeg = Math.toDegrees(angle + Math.PI / 2);

        g.setFill(fuel > safeFuelMargin ? Color.LIME : Color.ORANGERED);
        g.fillText("Fuel: " + (int) fuel + "%", 16, 64);

        g.setFill(speed < safeLandingSpeed ? Color.LIME : Color.ORANGERED);
        g.fillText(String.format("Speed: %.0f px/s", speed), 16, 86);

        g.setFill(Math.abs(normalizeAngleDeg(angleDeg)) < Math.toDegrees(safeLandingAngle) ? Color.LIME
                : Color.ORANGERED);
        g.fillText("Angle: " + (int) angleDeg + "°", 16, 108);

        g.setFill(timer > 10 ? Color.LIME : Color.ORANGERED);
        g.fillText("Time: " + (int) timer + "s", 16, 130);

        g.setFill(Color.WHITE);
        g.fillText("Distance to ground: " + (int) (GROUND_Y - y) + " px", 16, 152);

        if (gameOver) {
            g.setFont(Font.font("Consolas", 32));
            g.setFill(message.startsWith("SAFE") ? Color.LIME : Color.ORANGERED);
            g.fillText(message, 16, 170);
            g.setFont(Font.font("Consolas", 20));
            g.setFill(Color.WHITE);
            g.fillText("Press R to try again.", 16, 196);
        }

    }

    private void drawRocket(GraphicsContext g) {
        // Triangle points in local space (nose at (0,-h/2))
        double[] pxs = { 0, -ROCKET_R, ROCKET_R };
        double[] pys = { -ROCKET_H / 2, ROCKET_H / 2, ROCKET_H / 2 };

        // Rotate & translate
        double ca = Math.cos(angle + Math.PI / 2); // +90° to point up
        double sa = Math.sin(angle + Math.PI / 2);
        double[] sx = new double[3];
        double[] sy = new double[3];
        for (int i = 0; i < 3; i++) {
            sx[i] = x + pxs[i] * ca - pys[i] * sa;
            sy[i] = y + pxs[i] * sa + pys[i] * ca;
        }

        g.setFill(Color.SILVER);
        g.fillPolygon(sx, sy, 3);
        g.setStroke(Color.BLACK);
        g.setLineWidth(1.5);
        g.strokePolygon(sx, sy, 3);

        // Core flame when thrusting
        if (thrusting && fuel > 0 && !gameOver) {
            double fx = x + (0) * ca - (ROCKET_H / 2 + 8) * sa;
            double fy = y + (0) * sa + (ROCKET_H / 2 + 8) * ca;
            g.setFill(Color.web("#ffcc33"));
            g.fillOval(fx - 6, fy - 6, 12, 12);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
