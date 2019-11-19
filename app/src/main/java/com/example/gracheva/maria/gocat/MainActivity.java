package com.example.gracheva.maria.gocat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainActivity extends Activity {

    ImageButton buttonGame;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonGame = findViewById(R.id.play_button);

        OnClickListener buttonGameOnClick = v -> {
            Intent openGameIntent = new Intent(v.getContext(), GameActivity.class);
            startActivityForResult(openGameIntent, 0);
        };

        buttonGame.setOnClickListener(buttonGameOnClick);

    }
}