package com.example.gracheva.maria.gocat.animation.figure;

import com.example.gracheva.maria.gocat.animation.geometry.Point;

import java.util.function.BiConsumer;

public class Circle extends Figure {
    private Point center;
    private double radius;
    private int directionFactor;

    public Circle(Point startingPoint, Point center, boolean clockwise, long delay, boolean relativeCoordinates) {
        super(startingPoint, startingPoint, delay, relativeCoordinates);
        this.directionFactor = clockwise ? 1 : -1;
        this.center = center;
        this.radius = CircleUtils.INSTANCE.getRadius(startingPoint, center);
    }

    @Override
    public void animate(BiConsumer<Boolean, Point> draw) throws InterruptedException {
        double angle = CircleUtils.INSTANCE.getAngle(startingPoint, center, radius);
        double diff = 0.;
        while (diff < 2 * Math.PI) {
            Point coordinates = CircleUtils.INSTANCE.nextCoordinates(
                    angle + diff * directionFactor,
                    center,
                    radius);
            draw.accept(relativeCoordinates, coordinates);
            Thread.sleep(delay);
            diff += 0.1;
        }
    }
}
