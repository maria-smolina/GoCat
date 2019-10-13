package com.example.gracheva.maria.gocat;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;

public class ButterflyView extends View implements ValueAnimator.AnimatorUpdateListener {
    private int canvasWidth;
    private int canvasHeight;
    private int butterflyPosition = 0;
    private int butterflyPositionCounter = 0;
    private Bitmap butterfly[] = new Bitmap[12];
    private Bitmap bgImage;
    private Paint scorePaint = new Paint();
    private Path path;
    private int angle = 0;

    public ButterflyView(Context context) {
        super(context);

        butterfly[0] = BitmapFactory.decodeResource(getResources(), R.drawable.butterfly_0);
        butterfly[1] = BitmapFactory.decodeResource(getResources(), R.drawable.butterfly_1);
        butterfly[2] = BitmapFactory.decodeResource(getResources(), R.drawable.butterfly_2);
        butterfly[3] = BitmapFactory.decodeResource(getResources(), R.drawable.butterfly_3);
        butterfly[4] = BitmapFactory.decodeResource(getResources(), R.drawable.butterfly_4);
        butterfly[5] = BitmapFactory.decodeResource(getResources(), R.drawable.butterfly_5);
        butterfly[6] = BitmapFactory.decodeResource(getResources(), R.drawable.butterfly_6);
        butterfly[7] = BitmapFactory.decodeResource(getResources(), R.drawable.butterfly_7);
        butterfly[8] = BitmapFactory.decodeResource(getResources(), R.drawable.butterfly_8);
        butterfly[9] = BitmapFactory.decodeResource(getResources(), R.drawable.butterfly_9);
        butterfly[10] = BitmapFactory.decodeResource(getResources(), R.drawable.butterfly_10);
        butterfly[11] = BitmapFactory.decodeResource(getResources(), R.drawable.butterfly_11);

//        bgImage = BitmapFactory.decodeResource(getResources(), R.drawable.wallpaper);

        scorePaint.setColor(Color.CYAN);
        scorePaint.setTextSize(32);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
//        setZ(100);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();
//        canvas.drawBitmap(bgImage, 0, 0, null);
        canvas.drawText("Score : 0", 20, 60, scorePaint);

        Bitmap bitmap = butterfly[butterflyPosition];

        Matrix matrix = new Matrix();
        matrix.setRotate(angle, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
//        angle += 1;
//        if (angle >= 360) {
//            angle = 0;
//        }


        final Bitmap shadow = addShadow(bitmap, bitmap.getHeight(), bitmap.getWidth(), Color.BLACK, 10, 30, 70);

        canvas.drawBitmap(shadow, matrix, null);
//        canvas.drawBitmap(shadow, 0, 0, null);

        if (butterflyPositionCounter++ % 3 == 0) {
            butterflyPosition++;
        }
        if (butterflyPosition >= 12) {
            butterflyPosition = 0;
        }
    }

    public Bitmap addShadow(final Bitmap bm, final int dstHeight, final int dstWidth, int color, int size, float dx, float dy) {
        final Bitmap mask = Bitmap.createBitmap(dstWidth, dstHeight, Bitmap.Config.ALPHA_8);

        final Matrix scaleToFit = new Matrix();
        final RectF src = new RectF(0, 0, bm.getWidth(), bm.getHeight());
        final RectF dst = new RectF(0, 0, dstWidth - dx, dstHeight - dy);
        scaleToFit.setRectToRect(src, dst, Matrix.ScaleToFit.CENTER);

        final Matrix dropShadow = new Matrix(scaleToFit);
        dropShadow.postTranslate(dx, dy);

        final Canvas maskCanvas = new Canvas(mask);
        final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        maskCanvas.drawBitmap(bm, scaleToFit, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        maskCanvas.drawBitmap(bm, dropShadow, paint);

        final BlurMaskFilter filter = new BlurMaskFilter(size, BlurMaskFilter.Blur.NORMAL);
        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setAlpha(20);
        paint.setMaskFilter(filter);
        paint.setFilterBitmap(true);

        final Bitmap ret = Bitmap.createBitmap(dstWidth, dstHeight, Bitmap.Config.ARGB_8888);
        final Canvas retCanvas = new Canvas(ret);
        retCanvas.drawBitmap(mask, 0,  0, paint);
        retCanvas.drawBitmap(bm, scaleToFit, null);
        mask.recycle();
        return ret;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        float x = event.getX();
//        float y = event.getY();
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                Check if the x and y position of the touch is inside the point
//                if (x > currentPosition.getX() && x < currentPosition.getX() + POINT_WIDTH
//                        && y > currentPosition.getY() && y < currentPosition.getY() + POINT_HEIGHT) {
//                    score++;
//                }
//                return true;
//        }
//        return false;



        return true;
    }

}
