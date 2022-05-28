package com.example.zmeyka;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileReader;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity
{
    int introduction = 1;
    MediaPlayer media;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FileReader read = null;
        try
        {
            read = new FileReader("data.txt");

            Scanner scanner = new Scanner(read);

            int level = scanner.nextInt();

            if(level>0)
            {
                introduction = 0;
            }

            read.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        media = MediaPlayer.create(this, R.raw.menum);

        media.start();

        media.setVolume(2000,2000);

        Button but = findViewById(R.id.but);

        but.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                if(introduction == 0)
                {
                    Intent intent = new Intent(MainActivity.this, Game.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(MainActivity.this, IntroductionActivity.class);
                    media.stop();
                    startActivity(intent);
                }

            }
        });
    }
}