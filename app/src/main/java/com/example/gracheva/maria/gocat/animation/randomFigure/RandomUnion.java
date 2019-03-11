package com.example.gracheva.maria.gocat.animation.randomFigure;

import com.example.gracheva.maria.gocat.animation.geometry.Point;

import java.util.Random;
import java.util.function.Supplier;

public class RandomUnion {
    private static final Long DELAY = 50L;
    private static final Class[] figures = new Class[]{ RandomLine.class, RandomCircle.class, RandomCurve.class };

    private Random random;
    private Point startingPoint;
    private Supplier<Point> scale;

    public RandomUnion(Supplier<Point> scale) {
        random = new Random();
        startingPoint = new Point(0, 0);
        this.scale = scale;
    }

    public RandomFigure next() throws Exception {
        Point screenScale = scale.get();
        RandomFigure figure = (RandomFigure) figures[random.nextInt(figures.length)]
                .getConstructor(Random.class, Long.class, Point.class, Point.class)
                .newInstance(random, DELAY, startingPoint, screenScale);
        startingPoint = figure.getEndPoint();
        return figure;
    }
}
