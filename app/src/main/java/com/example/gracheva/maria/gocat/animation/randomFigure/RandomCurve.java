package com.example.gracheva.maria.gocat.animation.randomFigure;

import com.example.gracheva.maria.gocat.animation.figure.Curve;
import com.example.gracheva.maria.gocat.animation.geometry.Point;

import java.util.Random;
import java.util.function.BiConsumer;

public class RandomCurve extends RandomFigure {
    private Curve curve;

    public RandomCurve(Random random, Long delay, Point startingPoint) {
        super(startingPoint);
        Point middlePoint = new Point(random.nextDouble(), random.nextDouble());
        this.endPoint = new Point(random.nextDouble(), random.nextDouble());
        curve = new Curve(startingPoint, middlePoint, endPoint, delay, true);
    }

    @Override
    public void animate(BiConsumer<Boolean, Point> draw) throws InterruptedException {
        curve.animate(draw);
    }
}
