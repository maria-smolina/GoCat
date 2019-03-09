package com.example.gracheva.maria.gocat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.gracheva.maria.gocat.animation.figure.Circle;
import com.example.gracheva.maria.gocat.animation.figure.Curve;
import com.example.gracheva.maria.gocat.animation.figure.Figure;
import com.example.gracheva.maria.gocat.animation.figure.Line;
import com.example.gracheva.maria.gocat.animation.geometry.Point;
import com.example.gracheva.maria.gocat.animation.randomFigure.RandomCircle;

import java.util.Random;

public class PointSurface extends SurfaceView {
    SurfaceHolder holder;
    Paint paint;
    Drawable wallpaper;

    public PointSurface(Context context) {
        super(context);
        holder = getHolder();
        paint = new Paint();
        wallpaper = getResources().getDrawable(R.drawable.wallpaper, null);
        new PointAnimation().start();
    }

    private class PointAnimation extends Thread {
        private static final int DELAY = 50;
        private static final int POINT_WIDTH = 170;
        private static final int POINT_HEIGHT = 170;

        @Override
        public void run() {
            try {
                Point point1 = new Point(300, 300);
                Point point2 = new Point(600, 600);
                Point point3 = new Point(300, 700);
                Point point4 = new Point(300, 1000);

                Point center = new Point(350, 350);
                Figure circle = new Circle(point1, center, false, DELAY, false);

                Point center2 = new Point(600, 600);
                Figure circle2 = new Circle(point1, center2, false, DELAY, false);

                Figure curve = new Curve(point1, point2, point3, DELAY, false);

                Figure line = new Line(point3, point1, DELAY, false);
                Figure line2 = new Line(point1, point4, DELAY, false);
                Figure line3 = new Line(point4, point1, DELAY, false);

                Random random = new Random();
                RandomCircle randomCircle = new RandomCircle(random, DELAY);

                while (true) {
//                    circle.animate(this::draw);
//                    circle2.animate(this::draw);
//                    curve.animate(this::draw);
//                    line.animate(this::draw);
//                    line2.animate(this::draw);
//                    line3.animate(this::draw);
                    randomCircle.animate(this::draw);
                }
            } catch (InterruptedException e) {
                System.out.println("Point animation is interrupted");
            }
        }

        private void draw(Boolean relativeCoordinates, Point point) {
            Canvas canvas = holder.lockCanvas();
                int width = getWidth();
                int height = getHeight();
            if (canvas != null) {
                if (relativeCoordinates) {
                    point.scale(width, height);
                }
                wallpaper.setBounds(0, 0, width, height);
                wallpaper.draw(canvas);
                Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.point);
                bitmap = Bitmap.createScaledBitmap(bitmap, POINT_WIDTH, POINT_HEIGHT, false);
                canvas.drawBitmap(bitmap, (float) point.getX(), (float) point.getY(), paint);
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }
}
