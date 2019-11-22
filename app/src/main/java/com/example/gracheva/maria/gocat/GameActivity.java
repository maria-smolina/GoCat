package com.example.gracheva.maria.gocat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class GameActivity extends AppCompatActivity {
    ImageButton buttonButterfly;
    ImageButton buttonBird;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        buttonButterfly = findViewById(R.id.butterfly_button);
        buttonBird = findViewById(R.id.bird_button);

        View.OnClickListener buttonButterflyOnClick = v -> {
            Intent openButterflyGameIntent = new Intent(v.getContext(), ButterflyActivity.class);
            startActivityForResult(openButterflyGameIntent, 0);
        };
        buttonButterfly.setOnClickListener(buttonButterflyOnClick);

        View.OnClickListener buttonBirdOnClick = v -> {
            Intent openBirdGameIntent = new Intent(v.getContext(), BirdActivity.class);
            startActivityForResult(openBirdGameIntent, 0);
        };
        buttonBird.setOnClickListener(buttonBirdOnClick);
    }

}
