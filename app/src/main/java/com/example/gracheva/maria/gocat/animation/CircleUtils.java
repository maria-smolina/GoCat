package com.example.gracheva.maria.gocat.animation;

public class CircleUtils {
    public static final CircleUtils INSTANCE = new CircleUtils();

    private CircleUtils() {
    }

    public double getRadius(Point startingPoint, Point center) {
        return Math.sqrt(
                Math.pow(startingPoint.getX() - center.getX(), 2)
                        + Math.pow(startingPoint.getY() - center.getY(), 2));
    }

    // return angle from -3PI/2 to PI/2
    public double getAngle(Point point, Point center, double radius) {
        double angle = Math.asin((point.getY() - center.getY()) / radius);
        if (Math.acos((point.getX() - center.getX()) / radius) > Math.PI / 2) {
            angle = -1 * (angle + Math.PI);
        }
        return angle;
    }

    public Point nextCoordinates(Double angle, Point center, double radius) {
        double nextX = Math.cos(angle) * radius + center.getX();
        double nextY = Math.sin(angle) * radius + center.getY();
        return new Point(nextX, nextY);
    }
}
