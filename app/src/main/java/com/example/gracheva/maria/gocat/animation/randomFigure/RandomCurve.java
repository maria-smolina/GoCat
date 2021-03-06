package com.example.gracheva.maria.gocat.animation.randomFigure;

import com.example.gracheva.maria.gocat.animation.figure.Curve;
import com.example.gracheva.maria.gocat.animation.figure.Figure;
import com.example.gracheva.maria.gocat.animation.geometry.Point;

import java.util.Random;

public class RandomCurve extends RandomFigure {

    public RandomCurve(Random random, Long delay, Point startingPoint, Point screenScale) {
        super(random, delay, startingPoint, screenScale);
    }

    @Override
    public Figure createFigure() {
        Point middlePoint = new Point(random.nextDouble(), random.nextDouble());
        middlePoint.scale(screenScale);
        endPoint = new Point(random.nextDouble(), random.nextDouble());
        endPoint.scale(screenScale);
        return new Curve(startingPoint, middlePoint, endPoint, delay);
    }
}
