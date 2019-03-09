package com.example.gracheva.maria.gocat.animation.randomFigure;

import com.example.gracheva.maria.gocat.animation.figure.Circle;
import com.example.gracheva.maria.gocat.animation.figure.CircleUtils;
import com.example.gracheva.maria.gocat.animation.geometry.Point;

import java.util.Random;
import java.util.function.BiConsumer;

public class RandomCircle {
    private Circle circle;

    public RandomCircle(Random random, long delay) {
        Point center = new Point(random.nextDouble(), random.nextDouble());
        Point startingPoint = new Point(random.nextDouble(), random.nextDouble());
        double radius = CircleUtils.INSTANCE.getRadius(startingPoint, center);
        double wallDistance = Math.min(center.getX(),
                Math.min(center.getY(), Math.min(1. - center.getX(), 1. - center.getY())));
        if (radius - wallDistance > 0) {
            double scaleCoef = wallDistance / radius;
            startingPoint.setX(center.getX() + (startingPoint.getX() - center.getX()) * scaleCoef);
            startingPoint.setY(center.getY() + (startingPoint.getY() - center.getY()) * scaleCoef);
        }
        circle = new Circle(startingPoint, center, random.nextBoolean(), delay, true);
    }

    public void animate(BiConsumer<Boolean, Point> draw) throws InterruptedException {
        circle.animate(draw);
    }
}
