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

public class BirdView extends View implements ValueAnimator.AnimatorUpdateListener {
    private int canvasWidth;
    private int canvasHeight;
    private int birdPosition = 0;
    private int birdPositionCounter = 0;
    private Bitmap bird[] = new Bitmap[12];
    private Bitmap bgImage;
    private Paint scorePaint = new Paint();
    private Path path;
    private int angle = 0;

    public BirdView(Context context) {
        super(context);

        bird[0] = BitmapFactory.decodeResource(getResources(), R.drawable.bird_0);
        bird[1] = BitmapFactory.decodeResource(getResources(), R.drawable.bird_1);
        bird[2] = BitmapFactory.decodeResource(getResources(), R.drawable.bird_2);
        bird[3] = BitmapFactory.decodeResource(getResources(), R.drawable.bird_3);
        bird[4] = BitmapFactory.decodeResource(getResources(), R.drawable.bird_4);
        bird[5] = BitmapFactory.decodeResource(getResources(), R.drawable.bird_5);
        bird[6] = BitmapFactory.decodeResource(getResources(), R.drawable.bird_6);
        bird[7] = BitmapFactory.decodeResource(getResources(), R.drawable.bird_7);
        bird[8] = BitmapFactory.decodeResource(getResources(), R.drawable.bird_8);
        bird[9] = BitmapFactory.decodeResource(getResources(), R.drawable.bird_9);
        bird[10] = BitmapFactory.decodeResource(getResources(), R.drawable.bird_10);
        bird[11] = BitmapFactory.decodeResource(getResources(), R.drawable.bird_11);

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

        Bitmap bitmap = bird[birdPosition];

        Matrix matrix = new Matrix();
        matrix.setRotate(angle, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
//        angle += 1;
//        if (angle >= 360) {
//            angle = 0;
//        }


        final Bitmap shadow = addShadow(bitmap, bitmap.getHeight(), bitmap.getWidth(), Color.BLACK, 10, 30, 70);

        canvas.drawBitmap(shadow, matrix, null);
//        canvas.drawBitmap(shadow, 0, 0, null);

        if (birdPositionCounter++ % 3 == 0) {
            birdPosition++;
        }
        if (birdPosition >= 12) {
            birdPosition = 0;
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
