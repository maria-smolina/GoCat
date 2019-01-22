package com.example.gracheva.maria.gocat.animation;

public class CircleUtils {
    private Point startingPoint;
    private Point center;
    private double radius;

    public CircleUtils(Point startingPoint, Point center) {
        this.startingPoint = startingPoint;
        this.center = center;
        this.radius = getRadius();
    }

    private double getRadius() {
        return Math.sqrt(
                Math.pow(startingPoint.getX() - center.getX(), 2)
                        + Math.pow(startingPoint.getY() - center.getY(), 2));
    }

    // return angle from -3PI/2 to PI/2
    protected double getAngle(Point point) {
        double angle = Math.asin((point.getY() - center.getY()) / radius);
        if (Math.acos((point.getX() - center.getX()) / radius) > Math.PI / 2) {
            angle = -1 * (angle + Math.PI);
        }
        return angle;
    }

    protected Point nextCoordinates(Double angle) {
        double nextX = Math.cos(angle) * radius + center.getX();
        double nextY = Math.sin(angle) * radius + center.getY();
        return new Point(nextX, nextY);
    }
}
