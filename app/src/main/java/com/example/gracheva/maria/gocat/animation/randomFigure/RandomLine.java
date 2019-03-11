package com.example.gracheva.maria.gocat.animation.randomFigure;

import com.example.gracheva.maria.gocat.animation.figure.Figure;
import com.example.gracheva.maria.gocat.animation.figure.Line;
import com.example.gracheva.maria.gocat.animation.geometry.Point;

import java.util.Random;

public class RandomLine extends RandomFigure {

    public RandomLine(Random random, Long delay, Point startingPoint, Point screenScale) {
        super(random, delay, startingPoint, screenScale);
    }

    @Override
    public Figure createFigure() {
        endPoint = new Point(random.nextDouble(), random.nextDouble());
        endPoint.scale(screenScale);
        return new Line(startingPoint, endPoint, delay);
    }
}
