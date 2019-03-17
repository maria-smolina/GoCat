package com.example.gracheva.maria.gocat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.gracheva.maria.gocat.animation.geometry.Point;
import com.example.gracheva.maria.gocat.animation.randomFigure.RandomUnion;

public class PointSurface extends SurfaceView {
    private static final int POINT_WIDTH = 170;
    private static final int POINT_HEIGHT = 170;
    private static final int TEXT_SIZE = 60;
    private static final String SCORE_MSG = "SCORE: ";

    volatile SurfaceHolder holder;
    private Paint paint;
    private Drawable wallpaper;
    private Point currentPosition;
    private Long score;
    private Paint fontPaint;

    public PointSurface(Context context) {
        super(context);
        holder = getHolder();
        paint = new Paint();
        wallpaper = getResources().getDrawable(R.drawable.wallpaper, null);
        currentPosition = new Point(-1000, -1000);
        score = 0L;
        fontPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        fontPaint.setTextSize(TEXT_SIZE);
        fontPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        fontPaint.setColor(Color.GRAY);
        new PointAnimation().start();
    }

    private class PointAnimation extends Thread {

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
                currentPosition = point;
                wallpaper.setBounds(0, 0, getWidth(), getHeight());
                wallpaper.draw(canvas);
                canvas.drawText(SCORE_MSG + score, 20, getHeight() - 20, fontPaint);
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //Check if the x and y position of the touch is inside the point
                if (x > currentPosition.getX() && x < currentPosition.getX() + POINT_WIDTH
                        && y > currentPosition.getY() && y < currentPosition.getY() + POINT_HEIGHT) {
                    score++;
                }
                return true;
        }
        return false;
    }
}
