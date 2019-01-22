package com.example.gracheva.maria.gocat.animation;

import android.util.Log;

import java.util.function.Consumer;

public class Circle {
    private Point startingPoint;
    private Point center;
    private Double radius;
    private int directionFactor;
    private long delay;

    public Circle(Point startingPoint, Point center, boolean clockwise, long delay) {
        this.startingPoint = startingPoint;
        this.center = center;
        this.radius = getRadius();
        this.directionFactor = clockwise ? 1 : -1;
        this.delay = delay;
    }

    public void animate(Consumer<Point> draw) throws InterruptedException {
        double angle = getAngle();
        double diff = 0.;
        while (diff < 2 * Math.PI) {
            Point coordinates = nextCoordinates(angle + diff * directionFactor);
            Log.i("", "Angle: " + (angle + diff));
            draw.accept(coordinates);
            Thread.sleep(delay);
            diff += 0.1;
        }
    }

    private double getAngle() {
        double angle = Math.asin((startingPoint.getY() - center.getY()) / radius);
        if (Math.acos((startingPoint.getX() - center.getX()) / radius) > Math.PI / 2) {
            angle = -1 * (angle + Math.PI);
        }
        return angle;
    }

    private Point nextCoordinates(Double angle) {
        double nextX = Math.cos(angle) * radius + center.getX();
        double nextY = Math.sin(angle) * radius + center.getY();
        return new Point(nextX, nextY);
    }

    private Double getRadius() {
        return Math.sqrt(
            Math.pow(startingPoint.getX() - center.getX(), 2)
            + Math.pow(startingPoint.getY() - center.getY(), 2));
    }
}
