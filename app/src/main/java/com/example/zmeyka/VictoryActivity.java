package com.example.zmeyka;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class VictoryActivity extends AppCompatActivity
{
    MediaPlayer media;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victory);

        media = MediaPlayer.create(this, R.raw.good);

        media.start();

        media.setVolume(2000,2000);
    }
}