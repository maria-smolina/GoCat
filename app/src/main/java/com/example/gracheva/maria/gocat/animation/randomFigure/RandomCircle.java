package com.example.gracheva.maria.gocat.animation.randomFigure;

import com.example.gracheva.maria.gocat.animation.figure.Circle;
import com.example.gracheva.maria.gocat.animation.figure.CircleUtils;
import com.example.gracheva.maria.gocat.animation.figure.Figure;
import com.example.gracheva.maria.gocat.animation.geometry.Point;

import java.util.Random;

public class RandomCircle extends RandomFigure {

    public RandomCircle(Random random, Long delay, Point startingPoint, Point screenScale) {
        super(random, delay, startingPoint, screenScale);
    }

    @Override
    public Figure createFigure() {
        Point center = new Point(random.nextDouble(), random.nextDouble());
        center.scale(screenScale);
        double radius = CircleUtils.INSTANCE.getRadius(startingPoint, center);
        double wallDistance = Math.min(center.getX(),
                Math.min(center.getY(),
                Math.min(screenScale.getX() - center.getX(), screenScale.getY() - center.getY())));
        if (radius - wallDistance > 0) {
            double scaleCoef = wallDistance / radius;
            center.setX(startingPoint.getX() + (center.getX() - startingPoint.getX()) * scaleCoef);
            center.setY(startingPoint.getY() + (center.getY() - startingPoint.getY()) * scaleCoef);
        }
        return new Circle(startingPoint, center, random.nextBoolean(), delay);
    }
}
