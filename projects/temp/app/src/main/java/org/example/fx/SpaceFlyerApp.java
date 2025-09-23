package org.example.fx;

import org.example.core.*;
import org.example.fx.FxAdapters.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.robot.Robot;
import javafx.scene.transform.Translate;

import javafx.stage.Stage;

import java.util.*;

public class SpaceFlyerApp extends Application {
    private final Set<KeyCode> keys = new HashSet<>();
    private final Random rnd = new Random(42);

    private World world;
    private FxShip fxShip;
    private final List<FxAsteroid> fxAsteroids = new ArrayList<>();

    // camera rig
    private final Group camRig = new Group();
    private final PerspectiveCamera cam = new PerspectiveCamera(true);

    private Group spawnStars;
    private Group root3d;

    private double lastMouseX = 0;
    private double lastMouseY = 0;
    private double accumulatedYaw = 0;
    private double accumulatedPitch = 0;
    private final double sensitivity = 0.3;

    private boolean fireThisFrame = false;

    private final List<FxBullet> fxBullets = new ArrayList<>();

    // Mouse lock emulation
    private Robot robot;
    private boolean pointerLocked = false;
    private boolean suppressMouseEvent = false;
    private double centerScreenX, centerScreenY;
    private double accumDX = 0, accumDY = 0;

    // If W moves you backward, set to -1
    private static final double THRUST_SIGN = +1.0;

    @Override
    public void start(Stage stage) {
        world = new World();
        spawnStars = buildStars(450, world.worldSize);
        world.asteroids.addAll(makeAsteroids(40, world.worldSize * 0.7));

        root3d = new Group();
        fxShip = new FxShip();
        root3d.getChildren().add(fxShip.node);

        for (Asteroid a : world.asteroids) {
            FxAsteroid fxA = new FxAsteroid(a.radius);
            fxAsteroids.add(fxA);
            fxA.sync(a);
            root3d.getChildren().add(fxA.sphere);
        }

        root3d.getChildren().add(spawnStars);

        var ambient = new AmbientLight(Color.color(0.35, 0.35, 0.45));
        var sun = new PointLight(Color.color(1, 1, 0.95));
        sun.getTransforms().add(new Translate(1500, -1200, -1500));
        root3d.getChildren().addAll(ambient, sun);

        cam.setNearClip(0.05);
        cam.setFarClip(50_000);
        cam.setFieldOfView(70);
        cam.setRotationAxis(javafx.geometry.Point3D.ZERO.add(0, 1, 0));
        cam.setRotate(180);
        camRig.getChildren().add(cam);

        SubScene sub = new SubScene(new Group(root3d, camRig), 1280, 720, true, SceneAntialiasing.BALANCED);
        sub.setCamera(cam);
        sub.setFill(Color.web("#04070C"));
        Scene scene = new Scene(new Group(sub));

        stage.setTitle("Space Flyer – FX (split modules)");
        stage.setScene(scene);
        stage.show();

        scene.widthProperty().addListener((o, ov, nv) -> sub.setWidth(nv.doubleValue()));
        scene.heightProperty().addListener((o, ov, nv) -> sub.setHeight(nv.doubleValue()));

        scene.setOnKeyPressed(e -> keys.add(e.getCode()));
        scene.setOnKeyReleased(e -> keys.remove(e.getCode()));

        new Loop().start();

        robot = new Robot();

        // Recompute scene center in **screen** coords when size/move changes
        Runnable recomputeCenter = () -> {
            var win = stage.getScene().getWindow();
            double sx = win.getX() + stage.getScene().getX() + stage.getWidth() / 2.0;
            double sy = win.getY() + stage.getScene().getY() + stage.getHeight() / 2.0;
            centerScreenX = sx;
            centerScreenY = sy;
        };
        stage.xProperty().addListener((o, a, b) -> recomputeCenter.run());
        stage.yProperty().addListener((o, a, b) -> recomputeCenter.run());
        scene.widthProperty().addListener((o, a, b) -> recomputeCenter.run());
        scene.heightProperty().addListener((o, a, b) -> recomputeCenter.run());
        recomputeCenter.run();

        // Click to lock; ESC to unlock
        scene.setOnMousePressed(e -> {
            if (!pointerLocked) {
                pointerLocked = true;
                scene.setCursor(Cursor.NONE);
                suppressMouseEvent = true;
                robot.mouseMove(centerScreenX, centerScreenY);
                accumDX = accumDY = 0;
            }

            if (e.getButton() == MouseButton.PRIMARY) {
                fireThisFrame = true;
            }
        });
        scene.setOnKeyPressed(e -> {
            keys.add(e.getCode());
            if (e.getCode() == KeyCode.ESCAPE) {
                pointerLocked = false;
                scene.setCursor(Cursor.DEFAULT);
            }
        });
        scene.setOnKeyReleased(e -> keys.remove(e.getCode()));

        // Accumulate deltas relative to screen-center
        scene.setOnMouseMoved(e -> {
            if (!pointerLocked)
                return;
            if (suppressMouseEvent) {
                suppressMouseEvent = false;
                return;
            }
            double dx = e.getScreenX() - centerScreenX;
            double dy = e.getScreenY() - centerScreenY;
            accumDX += dx;
            accumDY += dy;
            // Recenter immediately to emulate pointer lock
            suppressMouseEvent = true;
            robot.mouseMove(centerScreenX, centerScreenY);
        });
        scene.setOnMouseDragged(scene.getOnMouseMoved());

    }

    private Group buildStars(int count, double size) {
        Group g = new Group();
        var mat = new PhongMaterial(Color.WHITE);
        for (int i = 0; i < count; i++) {
            Sphere s = new Sphere(2.2);
            s.setMaterial(mat);
            s.setTranslateX(r(-size, size));
            s.setTranslateY(r(-size, size));
            s.setTranslateZ(r(-size, size));
            g.getChildren().add(s);
        }
        return g;
    }

    private List<Asteroid> makeAsteroids(int n, double spread) {
        List<Asteroid> out = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            double r = r(20, 120);
            out.add(new Asteroid(
                    r,
                    new Vec3(r(-spread, spread), r(-spread, spread), r(-spread, spread)),
                    new Vec3(r(-30, 30), r(-30, 30), r(-30, 30)),
                    new Vec3(r(-10, 10), r(-10, 10), r(-10, 10))));
        }
        return out;
    }

    private class Loop extends AnimationTimer {
        long last = 0;

        @Override
        public void handle(long now) {
            if (last == 0) {
                last = now;
                return;
            }
            double dt = (now - last) / 1_000_000_000.0;
            last = now;

            // Map input -> core
            InputState in = new InputState();

            // W/S control thrust (flip with THRUST_SIGN if needed)
            double thrust = 0;
            if (keys.contains(KeyCode.W))
                thrust += 1.0;
            if (keys.contains(KeyCode.S))
                thrust -= 1.0;
            if (keys.contains(KeyCode.SHIFT))
                thrust *= 2.0;
            in.thrust = thrust * THRUST_SIGN;

            // Roll on A/D
            in.roll = (keys.contains(KeyCode.A) ? +1 : 0) + (keys.contains(KeyCode.D) ? -1 : 0);

            // Mouse steers yaw/pitch (unclamped → 180° flips OK)
            final double mouseSensitivity = 0.030;
            in.yaw = -accumDX * mouseSensitivity; // right = yaw right
            in.pitch = -accumDY * mouseSensitivity; // up = pitch up
            accumDX = accumDY = 0;

            in.reset = keys.contains(KeyCode.R);

            // Set fire if clicked
            in.fire = fireThisFrame; // Assuming InputState has a fire field; add public boolean fire; if not
            fireThisFrame = false;

            world.update(dt, in);

            // Apply direct mouse rotation for quick response (enables fast flips like 180)
            world.ship.pose.yaw += accumulatedYaw;
            world.ship.pose.pitch += accumulatedPitch;
            accumulatedYaw = 0;
            accumulatedPitch = 0;

            // sync visuals
            fxShip.sync(world.ship);
            for (int i = 0; i < world.asteroids.size(); i++)
                fxAsteroids.get(i).sync(world.asteroids.get(i));

            // Update bullets
            for (FxBullet b : fxBullets) {
                b.pos = b.pos.add(b.vel.mul(dt));
                b.sphere.setTranslateX(b.pos.x());
                b.sphere.setTranslateY(b.pos.y());
                b.sphere.setTranslateZ(b.pos.z());
            }

            // Fire bullet if needed (moved here in case core doesn't handle it)
            if (in.fire) {
                FxBullet b = new FxBullet();
                b.pos = world.ship.pose.p.add(world.ship.forward().mul(50)); // Offset to avoid self-collision
                b.vel = world.ship.vel.add(world.ship.forward().mul(1000)); // High speed
                root3d.getChildren().add(b.sphere);
                fxBullets.add(b);
            }

            // camera follow
            updateCamera(dt);
        }
    }

    private void updateCamera(double dt) {
        Ship s = world.ship;

        double followDist = 450.0;
        double upOffset = 120.0;

        // Basis from core math
        Vec3 fwd = s.forward().norm(); // ship forward
        Vec3 up = s.up().norm(); // ship up
        Vec3 back = fwd.mul(-1);

        // Desired camera world position: behind + above the ship
        Vec3 camPosDesired = s.pose.p.add(back.mul(followDist)).add(up.mul(upOffset));

        // Smooth camera position (critically damped-ish)
        Vec3 curr = new Vec3(cam.getTranslateX(), cam.getTranslateY(), cam.getTranslateZ());
        double k = Math.min(1.0, 6.0 * dt);
        Vec3 next = curr.add(camPosDesired.add(curr.mul(-1)).mul(k));
        cam.setTranslateX(next.x());
        cam.setTranslateY(next.y());
        cam.setTranslateZ(next.z());

        // Build a look-at rotation so the camera faces the ship
        // Camera looks along its local -Z, so we align -Z to (ship - cam)
        Vec3 toTarget = s.pose.p.add(next.mul(-1)).norm(); // direction from cam -> ship
        // Orthonormal basis for camera
        Vec3 cz = toTarget.mul(-1); // camera +Z points backward from view; we want -Z to target
        Vec3 cx = up.cross(cz).norm(); // right
        Vec3 cy = cz.cross(cx).norm(); // true up

        // Convert basis to JavaFX rotates (Affine is overkill; three Euler rotates are
        // fine if we derive yaw/pitch)
        // Get yaw (Y), pitch (X), roll (Z) from basis:
        double yaw = Math.atan2(cx.z(), cz.z()); // Y
        double pitch = Math.asin(cz.y()); // X
        double roll = Math.atan2(cy.x(), cy.y()); // Z (approx; roll is not crucial here)

        cam.getTransforms().setAll(
                new javafx.scene.transform.Rotate(Math.toDegrees(yaw), javafx.scene.transform.Rotate.Y_AXIS),
                new javafx.scene.transform.Rotate(Math.toDegrees(pitch), javafx.scene.transform.Rotate.X_AXIS),
                new javafx.scene.transform.Rotate(Math.toDegrees(roll), javafx.scene.transform.Rotate.Z_AXIS));
    }

    private double r(double a, double b) {
        return a + rnd.nextDouble() * (b - a);
    }

    private static class FxBullet {
        Sphere sphere = new Sphere(5);
        PhongMaterial mat = new PhongMaterial(Color.RED);
        Vec3 pos = new Vec3(0, 0, 0);
        Vec3 vel = new Vec3(0, 0, 0);

        FxBullet() {
            sphere.setMaterial(mat);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}