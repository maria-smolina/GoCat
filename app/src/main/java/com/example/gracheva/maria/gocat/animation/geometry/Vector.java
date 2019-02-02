package com.example.gracheva.maria.gocat.animation.geometry;

public class Vector {
    private double x;
    private double y;
    private double length;

    public Vector(Point start, Point end) {
        this.x = end.getX() - start.getX();
        this.y = end.getY() - start.getY();
        this.length = Math.hypot(x, y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double length() {
        return length;
    }
}
