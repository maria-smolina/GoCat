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
    public PointSurface(Context context) {
        super(context);
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
            SurfaceHolder holder = getHolder();
            Canvas canvas = holder.lockCanvas();
            if (canvas != null) {
                Drawable d = getResources().getDrawable(R.drawable.wallpaper);
                int width = getWidth();
                int height = getHeight();
                d.setBounds(0, 0, width, height);
                d.draw(canvas);
                Paint paint = new Paint();
                Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.point);
                bitmap = Bitmap.createScaledBitmap(bitmap, POINT_WIDTH, POINT_HEIGHT, false);
                canvas.drawBitmap(bitmap, new Random().nextInt(width - bitmap.getWidth()), new Random().nextInt(height - bitmap.getHeight()), paint);
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }
}
