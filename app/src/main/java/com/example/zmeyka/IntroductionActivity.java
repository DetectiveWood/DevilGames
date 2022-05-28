package com.example.zmeyka;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class IntroductionActivity extends AppCompatActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        Button button = findViewById(R.id.button3);

        TextView text = findViewById(R.id.textView2);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(button.getText().equals("Узнать правила"))
                {
                    text.setText("Если враг встанет на клетку рядом с тобой, ты проиграл. " + "\n" +
                            "Все могут передвигаться по диагонали. " + "\n" +
                            "Можно поставить блок на клетку возле себя (длинное нажатие)." + "\n" +
                            "Можно передвинуться на клетку рядом с собой." + "\n" +
                            "Цель - окружить себя со всех сторон блоками");
                    button.setText("Начать Играть");
                }
                else
                {
                    Intent intent = new Intent(IntroductionActivity.this, Game.class);
                    startActivity(intent);
                }
            }
        });
    }
}