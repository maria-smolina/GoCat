package com.example.gracheva.maria.gocat.animation.figure;

import com.example.gracheva.maria.gocat.animation.geometry.Point;

import java.util.function.BiConsumer;

public abstract class Figure {
    protected Point startingPoint;
    protected Point endPoint;
    protected long delay;
    protected boolean relativeCoordinates;

    protected Figure(Point startingPoint, Point endPoint, long delay, boolean relativeCoordinates) {
        this.startingPoint = startingPoint;
        this.endPoint = endPoint;
        this.delay = delay;
        this.relativeCoordinates = relativeCoordinates;
    }

    public abstract void animate(BiConsumer<Boolean, Point> draw) throws InterruptedException;
}
