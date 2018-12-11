package com.example.gracheva.maria.gocat.animation;

import java.util.function.Consumer;

public class Circle {
    private Point radius;
    private Point center;
    private long delay;

    public Circle(Point radius, Point center, long delay) {
        this.radius = radius;
        this.center = center;
        this.delay = delay;
    }

    public void animate(Consumer<Point> draw) throws InterruptedException {
        Double angle = 0.;
        while (angle < 2 * Math.PI) {
            Point coordinates = nextCoordinates(radius, center, angle);
            draw.accept(coordinates);
            Thread.sleep(delay);
            angle += 0.1;
        }
    }

    private Point nextCoordinates(Point radius, Point center, Double angle) {
        double nextX = Math.cos(angle) * radius.getX() + center.getX();
        double nextY = Math.sin(angle) * radius.getY() + center.getY();
        return new Point(nextX, nextY);
    }
}
