package com.example.gracheva.maria.gocat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.gracheva.maria.gocat.animation.Circle;
import com.example.gracheva.maria.gocat.animation.Point;

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
        private static final int DELAY = 2;
        private static final int POINT_WIDTH = 170;
        private static final int POINT_HEIGHT = 170;

        @Override
        public void run() {
            try {
                Point radius = new Point(200, 500);
                Point center = new Point(500, 500);
                Circle circle = new Circle(radius, center, DELAY);
                circle.animate(this::draw);
            } catch (InterruptedException e) {
                System.out.println("Point animation is interrupted");
            }
        }

        private void draw(Point point) {
            Canvas canvas = holder.lockCanvas();
            if (canvas != null) {
                int width = getWidth();
                int height = getHeight();
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
