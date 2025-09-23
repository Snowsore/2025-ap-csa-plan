package com.example.fx;

import com.example.core.*;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;

public class FxAdapters {

    public static class FxShip {
        public final Group node = new Group();
        public final Box body = new Box(30, 12, 80);
        public final Cylinder nose = new Cylinder(8, 20);
        public final Rotate ry = new Rotate(0, Rotate.Y_AXIS);
        public final Rotate rx = new Rotate(0, Rotate.X_AXIS);
        public final Rotate rz = new Rotate(0, Rotate.Z_AXIS);

        public FxShip() {
            body.setMaterial(new PhongMaterial(Color.web("#5eead4")));
            nose.setMaterial(new PhongMaterial(Color.web("#a7f3d0")));
            nose.setRotationAxis(Rotate.X_AXIS);
            nose.setRotate(90);
            nose.setTranslateZ(-50);
            node.getChildren().addAll(body, nose);
            node.getTransforms().addAll(ry, rx, rz);
        }

        public void sync(Ship s) {
            node.setTranslateX(s.pose.p.x());
            node.setTranslateY(s.pose.p.y());
            node.setTranslateZ(s.pose.p.z());
            rx.setAngle(s.pose.pitch);
            ry.setAngle(s.pose.yaw);
            rz.setAngle(s.pose.roll);
        }
    }

    public static class FxAsteroid {
        public final Sphere sphere;
        public final Rotate ry = new Rotate(0, Rotate.Y_AXIS);
        public final Rotate rx = new Rotate(0, Rotate.X_AXIS);
        public final Rotate rz = new Rotate(0, Rotate.Z_AXIS);

        public FxAsteroid(double r) {
            sphere = new Sphere(r);
            sphere.setMaterial(new PhongMaterial(Color.gray(0.6)));
            sphere.getTransforms().addAll(ry, rx, rz);
        }

        public void sync(Asteroid a) {
            sphere.setTranslateX(a.pose.p.x());
            sphere.setTranslateY(a.pose.p.y());
            sphere.setTranslateZ(a.pose.p.z());
            rx.setAngle(a.pose.pitch);
            ry.setAngle(a.pose.yaw);
            rz.setAngle(a.pose.roll);
        }
    }
}
