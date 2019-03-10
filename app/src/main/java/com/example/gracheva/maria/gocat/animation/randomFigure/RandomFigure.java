package com.example.gracheva.maria.gocat.animation.randomFigure;

import com.example.gracheva.maria.gocat.animation.geometry.Point;

import java.util.function.BiConsumer;

public abstract class RandomFigure {
    protected Point startingPoint;
    protected Point endPoint;

    protected RandomFigure(Point startingPoint) {
        this.startingPoint = startingPoint;
        this.endPoint = startingPoint;
    }

    public abstract void animate(BiConsumer<Boolean, Point> draw) throws InterruptedException;

    public Point getEndPoint() {
        return endPoint;
    }
}
