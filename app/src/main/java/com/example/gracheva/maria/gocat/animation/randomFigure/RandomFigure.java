package com.example.gracheva.maria.gocat.animation.randomFigure;

import com.example.gracheva.maria.gocat.animation.figure.Figure;
import com.example.gracheva.maria.gocat.animation.geometry.Point;

import java.util.Random;
import java.util.function.Consumer;

public abstract class RandomFigure {
    protected Random random;
    protected Long delay;
    protected Point startingPoint;
    protected Point endPoint;
    protected Point screenScale;
    protected Figure figure;

    public RandomFigure(Random random, Long delay, Point startingPoint, Point screenScale) {
        this.random = random;
        this.delay = delay;
        this.startingPoint = startingPoint;
        this.endPoint = startingPoint;
        this.screenScale = screenScale;
        this.figure = createFigure();
    }

    public void animate(Consumer<Point> draw) throws InterruptedException {
        figure.animate(draw);
    }

    public Point getEndPoint() {
        return endPoint;
    }

    protected abstract Figure createFigure();
}
