package com.example.gracheva.maria.gocat.animation.randomFigure;

import com.example.gracheva.maria.gocat.animation.geometry.Point;

import java.util.Random;

public class RandomUnion {
    private static final Long DELAY = 25L;
    private static final Class[] figures = new Class[]{ RandomCircle.class };

    private Random random;
    private Point startingPoint;

    public RandomUnion() {
        random = new Random();
        startingPoint = new Point(random.nextDouble(), random.nextDouble());
    }

    public RandomFigure next() throws Exception {
        return (RandomFigure) figures[random.nextInt(figures.length)]
                .getConstructor(Random.class, Long.class, Point.class)
                .newInstance(random, DELAY, startingPoint);
    }
}
