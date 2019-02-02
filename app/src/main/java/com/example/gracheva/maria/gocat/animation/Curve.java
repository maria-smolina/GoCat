package com.example.gracheva.maria.gocat.animation;

import com.example.gracheva.maria.gocat.animation.geometry.Point;

import java.util.function.Consumer;

public class Curve extends Figure {
    private Point middlePoint;
    private Point center;
    private double radius;

    public Curve(Point startingPoint, Point middlePoint, Point endPoint, long delay) {
        super(startingPoint, endPoint, delay);
        this.middlePoint = middlePoint;
        this.center = calculateCenter();
        this.radius = CircleUtils.INSTANCE.getRadius(startingPoint, center);
    }

    @Override
    public void animate(Consumer<Point> draw) throws InterruptedException {
        double startingAngle = CircleUtils.INSTANCE.getAngle(startingPoint, center, radius);
        double middleAngle = CircleUtils.INSTANCE.getAngle(middlePoint, center, radius);
        double endAngle = CircleUtils.INSTANCE.getAngle(endPoint, center, radius);
        double diffAngle = endAngle - startingAngle;
        if (diffAngle < 0) {
            diffAngle *= -1;
        }
        if (middleAngle > startingAngle && middleAngle > endAngle
                || middleAngle < startingAngle && middleAngle < endAngle) {
            diffAngle = 2 * Math.PI - diffAngle;
        }
        int directionFactor = 1;
        if (startingAngle < endAngle && endAngle < middleAngle
                || middleAngle < startingAngle && startingAngle < endAngle
                || endAngle < middleAngle && middleAngle < startingAngle) {
            directionFactor = -1;
        }
        double diff = 0.;
        while (diff < diffAngle) {
            Point coordinates = CircleUtils.INSTANCE.nextCoordinates(
                    startingAngle + diff * directionFactor,
                    center,
                    radius);
            draw.accept(coordinates);
            Thread.sleep(delay);
            diff += 0.1;
        }
    }

    private Point calculateCenter() {
        double m1 = (middlePoint.getY() - startingPoint.getY()) / (middlePoint.getX() - startingPoint.getX());
        double m2 = (endPoint.getY() - middlePoint.getY()) / (endPoint.getX() - middlePoint.getX());
        if (m1 == m2) {
            throw new IllegalArgumentException("Three points on one straight line");
        }
        double x = (m1 * m2 * (startingPoint.getY() - endPoint.getY())
                + m2 * (startingPoint.getX() + middlePoint.getX())
                - m1 * (middlePoint.getX() + endPoint.getX()))
                / (2 * (m2 - m1));
        double y = -1 / m1 * (x - (startingPoint.getX() + middlePoint.getX()) / 2)
                + (startingPoint.getY() + middlePoint.getY()) / 2;
        return new Point(x, y);
    }
}
