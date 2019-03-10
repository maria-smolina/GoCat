package com.example.gracheva.maria.gocat.animation.randomFigure;

import com.example.gracheva.maria.gocat.animation.geometry.Point;

import java.util.function.BiConsumer;

public abstract class RandomFigure {
    protected Point startingPoint;

    protected RandomFigure(Point startingPoint) {
        this.startingPoint = startingPoint;
    }

    public abstract void animate(BiConsumer<Boolean, Point> draw) throws InterruptedException;
}
