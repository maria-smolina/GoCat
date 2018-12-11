package com.example.gracheva.maria.gocat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

public class PointSurface extends SurfaceView {
    SurfaceHolder holder;
    Paint paint;
    Drawable wallpaper;

    public PointSurface(Context context) {
        super(context);
        holder = getHolder();
        paint = new Paint();
        wallpaper = getResources().getDrawable(R.drawable.wallpaper);
        new PointAnimation().start();
    }

    private class PointAnimation extends Thread {
        private static final int DELAY = 100;
        private static final int POINT_WIDTH = 170;
        private static final int POINT_HEIGHT = 170;

        @Override
        public void run() {
            try {
                while (true) {
                    draw();
                    sleep(DELAY);
                }
            } catch (InterruptedException e) {
                System.out.println("Point animation is interrupted");
            }

        }

        private void draw() {
            Canvas canvas = holder.lockCanvas();
            if (canvas != null) {
                int width = getWidth();
                int height = getHeight();
                wallpaper.setBounds(0, 0, width, height);
                wallpaper.draw(canvas);
                Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.point);
                bitmap = Bitmap.createScaledBitmap(bitmap, POINT_WIDTH, POINT_HEIGHT, false);
                Point next = nextCoordinates(width, height, bitmap.getWidth(), bitmap.getHeight());
                canvas.drawBitmap(bitmap, next.getX(), next.getY(), paint);
                holder.unlockCanvasAndPost(canvas);
            }
        }

        private Point nextCoordinates(int width, int height, int bitmapWidth, int bitmapHeight) {
            return new Point(new Random().nextInt(width - bitmapWidth), new Random().nextInt(height - bitmapHeight));
        }

    }

    private static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
