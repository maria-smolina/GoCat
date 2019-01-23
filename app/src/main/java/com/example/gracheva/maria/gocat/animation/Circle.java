package com.example.gracheva.maria.gocat.animation;

import java.util.function.Consumer;

public class Circle extends Figure {
    private Point center;
    private double radius;
    private int directionFactor;

    public Circle(Point startingPoint, Point center, boolean clockwise, long delay) {
        super(startingPoint, null, delay);
        this.directionFactor = clockwise ? 1 : -1;
        this.center = center;
        this.radius = CircleUtils.INSTANCE.getRadius(startingPoint, center);
    }

    @Override
    public void animate(Consumer<Point> draw) throws InterruptedException {
        double angle = CircleUtils.INSTANCE.getAngle(startingPoint, center, radius);
        double diff = 0.;
        while (diff < 2 * Math.PI) {
            Point coordinates = CircleUtils.INSTANCE.nextCoordinates(
                    angle + diff * directionFactor,
                    center,
                    radius);
            draw.accept(coordinates);
            Thread.sleep(delay);
            diff += 0.1;
        }
    }
}
