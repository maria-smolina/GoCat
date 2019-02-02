package com.example.gracheva.maria.gocat.animation.figure;

import com.example.gracheva.maria.gocat.animation.geometry.Point;

import java.util.function.Consumer;

public abstract class Figure {
    protected Point startingPoint;
    protected Point endPoint;
    protected long delay;

    protected Figure(Point startingPoint, Point endPoint, long delay) {
        this.startingPoint = startingPoint;
        this.endPoint = endPoint;
        this.delay = delay;
    }

    public abstract void animate(Consumer<Point> draw) throws InterruptedException;
}
