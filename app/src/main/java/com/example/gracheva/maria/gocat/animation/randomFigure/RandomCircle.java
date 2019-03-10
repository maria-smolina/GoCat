package com.example.gracheva.maria.gocat.animation.randomFigure;

import com.example.gracheva.maria.gocat.animation.figure.Circle;
import com.example.gracheva.maria.gocat.animation.figure.CircleUtils;
import com.example.gracheva.maria.gocat.animation.geometry.Point;

import java.util.Random;
import java.util.function.BiConsumer;

public class RandomCircle extends RandomFigure {
    private Circle circle;

    public RandomCircle(Random random, Long delay, Point startingPoint) {
        super(startingPoint);
        Point center = new Point(random.nextDouble(), random.nextDouble());
        double radius = CircleUtils.INSTANCE.getRadius(startingPoint, center);
        double wallDistance = Math.min(center.getX(),
                Math.min(center.getY(), Math.min(1. - center.getX(), 1. - center.getY())));
        if (radius - wallDistance > 0) {
            double scaleCoef = wallDistance / radius;
            center.setX(startingPoint.getX() + (center.getX() - startingPoint.getX()) * scaleCoef);
            center.setY(startingPoint.getY() + (center.getY() - startingPoint.getY()) * scaleCoef);
        }
        circle = new Circle(startingPoint, center, random.nextBoolean(), delay, true);
    }

    @Override
    public void animate(BiConsumer<Boolean, Point> draw) throws InterruptedException {
        circle.animate(draw);
    }
}
