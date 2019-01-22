package com.example.gracheva.maria.gocat.animation;

import java.util.function.Consumer;

public class Circle extends Figure {
    private int directionFactor;
    private CircleUtils circleUtils;

    public Circle(Point startingPoint, Point center, boolean clockwise, long delay) {
        super(startingPoint, null, delay);
        this.directionFactor = clockwise ? 1 : -1;
        circleUtils = new CircleUtils(startingPoint, center);
    }

    @Override
    public void animate(Consumer<Point> draw) throws InterruptedException {
        double angle = circleUtils.getAngle(startingPoint);
        double diff = 0.;
        while (diff < 2 * Math.PI) {
            Point coordinates = circleUtils.nextCoordinates(angle + diff * directionFactor);
            draw.accept(coordinates);
            Thread.sleep(delay);
            diff += 0.1;
        }
    }
}
