package com.example.gracheva.maria.gocat.animation.figure;

import com.example.gracheva.maria.gocat.animation.geometry.Point;
import com.example.gracheva.maria.gocat.animation.geometry.Vector;

import java.util.function.BiConsumer;

public class Line extends Figure {
    public Line(Point startingPoint, Point endPoint, long delay, boolean relativeCoordinates) {
        super(startingPoint, endPoint, delay, relativeCoordinates);
    }

    public void animate(BiConsumer<Boolean, Point> draw) throws InterruptedException {
        final int speed = 30;
        Vector vector = new Vector(startingPoint, endPoint);
        double xDiff = speed * vector.getX() / vector.length();
        double yDiff = speed * vector.getY() / vector.length();
        int count = (int) vector.length() / speed;
        Point current = new Point(startingPoint.getX(), startingPoint.getY());
        for (int i = 0; i < count; i++) {
            draw.accept(relativeCoordinates, current);
            Thread.sleep(delay);
            current.setX(current.getX() + xDiff);
            current.setY(current.getY() + yDiff);
        }
    }
}
