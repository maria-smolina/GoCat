package com.example.gracheva.maria.gocat;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class BirdActivity extends AppCompatActivity {
    private BirdView birdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        old
//        setContentView(new PointSurface(getApplicationContext()));


        birdView = new BirdView(this);
        setContentView(birdView);


        float startX = 300;
        float startY = 500;
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("translationX", startX);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("translationY", startY);

        ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(birdView, pvhX, pvhY);
        animation.addUpdateListener(birdView);
        animation.setDuration(0);
        animation.start();

        float x = 0;
        float y = 800;
        pvhX = PropertyValuesHolder.ofFloat("translationX", x);
        pvhY = PropertyValuesHolder.ofFloat("translationY", y);

        float deltaX = x - startX;
        float deltaY = y - startY;
        int baseAngle = deltaX > 0 ? 180 : 360;
        birdView.setAngle(baseAngle - (int) Math.round(Math.toDegrees(Math.atan(-deltaY / deltaX))));
        animation = ObjectAnimator.ofPropertyValuesHolder(birdView, pvhX, pvhY);
        animation.addUpdateListener(birdView);
        animation.setDuration(5000);
        animation.start();


        // TODO for score

//        final ImageView imageview = (ImageView) findViewById(R.id.imageView);
//        final ValueAnimator translateAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
//        ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(imageview, "rotation", 0f, 90f);
//
//        final AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.playTogether(translateAnimator, rotateAnimator);
//        animatorSet.setDuration(4000);
//
//        final float x = imageview.getX();
//        final float y = imageview.getY();
//        translateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                float t = (Float) translateAnimator.getAnimatedValue();
//                imageview.setTranslationX(x + t*100);    // do your own
//                imageview.setTranslationY(y + t*100);    // thing here
//
//            }
//        });
//
//        imageview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                animatorSet.start();
//            }
//        });


//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                handler.post(() -> gameView.invalidate());
//            }
//        }, 0, TIMER_INTERVAL);
    }
}
