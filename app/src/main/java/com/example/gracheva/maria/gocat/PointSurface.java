package com.example.gracheva.maria.gocat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.gracheva.maria.gocat.animation.geometry.Point;
import com.example.gracheva.maria.gocat.animation.randomFigure.RandomUnion;

public class PointSurface extends SurfaceView {
    volatile SurfaceHolder holder;
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
        private static final int POINT_WIDTH = 170;
        private static final int POINT_HEIGHT = 170;

        @Override
        public void run() {
            try {
                Canvas canvas = holder.lockCanvas();
                while (canvas == null) {
                    Thread.sleep(500);
                    canvas = holder.lockCanvas();
                }
                holder.unlockCanvasAndPost(canvas);

                RandomUnion union = new RandomUnion(this::getScale);
                while (true) {
                    union.next().animate(this::draw);
                }
            } catch (InterruptedException e) {
                System.out.println("Point animation is interrupted");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void draw(Point point) {
            Canvas canvas = holder.lockCanvas();
            if (canvas != null) {
                wallpaper.setBounds(0, 0, getWidth(), getHeight());
                wallpaper.draw(canvas);
                Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.point);
                bitmap = Bitmap.createScaledBitmap(bitmap, POINT_WIDTH, POINT_HEIGHT, false);
                canvas.drawBitmap(bitmap, (float) point.getX(), (float) point.getY(), paint);
                holder.unlockCanvasAndPost(canvas);
            }
        }

        private Point getScale() {
            return new Point(getWidth(), getHeight());
        }
    }
}
