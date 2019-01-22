package com.example.gracheva.maria.gocat.animation;

import java.util.function.Consumer;

public class Curve {
    private Point startingPoint;
    private Point middlePoint;
    private Point endPoint;
    private Point center;
    private Double radius;
    private long delay;


    public Curve(Point startingPoint, Point middlePoint, Point endPoint, long delay) {
        this.startingPoint = startingPoint;
        this.middlePoint = middlePoint;
        this.endPoint = endPoint;
        this.delay = delay;
        this.center = getCenter();
        this.radius = getRadius();
    }

    public void animate(Consumer<Point> draw) throws InterruptedException {
        double startingAngle = getAngle(startingPoint);
        double middleAngle = getAngle(middlePoint);
        double endAngle = getAngle(endPoint);
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
            Point coordinates = nextCoordinates(startingAngle + diff * directionFactor);
            draw.accept(coordinates);
            Thread.sleep(delay);
            diff += 0.1;
        }
    }

    // return angle from -3PI/2 to PI/2
    private double getAngle(Point point) {
        double angle = Math.asin((point.getY() - center.getY()) / radius);
        if (Math.acos((point.getX() - center.getX()) / radius) > Math.PI / 2) {
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

    private Point getCenter() {
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
