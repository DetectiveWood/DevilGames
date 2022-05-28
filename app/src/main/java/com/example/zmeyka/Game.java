package com.example.zmeyka;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.appcompat.app.AppCompatActivity;

public class Game extends AppCompatActivity
{
    ImageView[][] field;

    Logic level = new Logic();

    int[][] map = level.map();

    int InitLevel = 1;

    MediaPlayer media;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initField(8,7);

        media = MediaPlayer.create(this, R.raw.fon);

        media.start();

        media.setVolume(2000,2000);
    }
    public void map(int[][] map)
    {
        for (int i = 0; i < map.length; i++)
        {
            for (int j = 0; j < map[i].length; j++)
            {
                if(map[i][j] == 0)
                {
                    field[i][j].setImageDrawable(null);
                    field[i][j].setMaxHeight(50);
                    field[i][j].setMaxWidth(50);
                }
                if (map[i][j] == 1)
                {
                    field[i][j].setImageResource(R.drawable.king);
                    field[i][j].setMaxHeight(50);
                    field[i][j].setMaxWidth(50);
                }
                else if(map[i][j] == 2)
                {
                    field[i][j].setImageResource(R.drawable.devil);
                    field[i][j].setMaxHeight(50);
                    field[i][j].setMaxWidth(50);
                }
                else if(map[i][j] == 3)
                {
                    field[i][j].setImageResource(R.drawable.wall);
                    field[i][j].setMaxHeight(50);
                    field[i][j].setMaxWidth(50);
                }
            }
        }
    }

    public void PlayerMove(String direction)
    {
        level.hero(direction);
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    private void initField(int height, int width)
    {
        field = new ImageView[height][width];

        TableLayout stk = findViewById(R.id.field);
        stk.setWeightSum(height);

        TableLayout.LayoutParams param = new TableLayout.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT,
                1.0f
        );

        TableRow.LayoutParams rowparam = new TableRow.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT,
                1.0f
        );

        for (int i = 0; i < height; i++)
        {
            TableRow tbrow = new TableRow(this);
            tbrow.setWeightSum(width);
            tbrow.setLayoutParams(param);

            for (int j = 0; j < width; j++)
            {
                field[i][j] = new ImageView(this);
                tbrow.addView(field[i][j]);
                field[i][j].setLayoutParams(rowparam);
                field[i][j].setId(i * height + j);
                //j=id%height
                //i=id//height
                if (i % 2 == 0)
                {
                    if (j % 2 == 0)
                    {
                        //field[i][j].setImageResource(R.drawable.white);
                        field[i][j].setBackground(getDrawable(R.drawable.white));
                    }
                    if (j % 2 == 1)
                    {
                        //field[i][j].setImageResource(R.drawable.grey);
                        field[i][j].setBackground(getDrawable(R.drawable.grey));
                    }
                }
                if (i % 2 == 1)
                {
                    if (j % 2 == 0)
                    {
                        //field[i][j].setImageResource(R.drawable.grey);
                        field[i][j].setBackground(getDrawable(R.drawable.grey));
                    }
                    if (j % 2 == 1)
                    {
                        //field[i][j].setImageResource(R.drawable.white);
                        field[i][j].setBackground(getDrawable(R.drawable.white));
                    }
                }
                field[i][j].setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        ImageView imageView = (ImageView) view;

                        int x = imageView.getId()%height;

                        int y = imageView.getId()/height;

                        int x2 = level.heroX - x;

                        int y2 = level.heroY - y;

                        if(1 >= Math.abs(x2) && 1 >= Math.abs(y2) && map[y][x] == 0)
                        {
                            if(x2 == 1 && y2 == 1)
                            {
                                level.hero("up-left");
                            }
                            else if(x2 == -1 && y2 == -1)
                            {
                                level.hero("down-right");
                            }
                            else if(x2 == -1 && y2 == 1)
                            {
                                level.hero("up-right");
                            }
                            else if(x2 == 1 && y2 == -1)
                            {
                                level.hero("down-left");
                            }
                            else if(x2 == 1)
                            {
                                level.hero("left");
                            }
                            else if(x2 == -1)
                            {
                                level.hero("right");
                            }
                            else if(y2 == -1)
                            {
                                level.hero("down");
                            }
                            else if(y2 == 1)
                            {
                                level.hero("up");
                            }
                            level.enemy(0);
                            level.enemy(1);
                            if(level.IsEndBad(0) || level.IsEndBad(1))
                            {
                                map = level.start(InitLevel);
                            }
                            else if(level.IsEndGood())
                            {
                                if(InitLevel==2)
                                {
                                    Intent intent = new Intent(Game.this, VictoryActivity.class);
                                    media.stop();
                                    startActivity(intent);
                                }
                                else
                                {
                                    map = level.start(InitLevel+1);
                                    level.heroX = 3;
                                    level.heroY = 3;
                                    InitLevel += 1;
                                }
                            }
                            else
                            {
                                map = level.map;
                            }
                            map(map);
                        }
                    }

                });
                field[i][j].setOnLongClickListener(new View.OnLongClickListener()
                {
                    public boolean onLongClick(View view)
                    {
                        longClick(view);
                        return true;
                    }
                    public void longClick(View view)
                    {
                        ImageView imageView = (ImageView) view;

                        int x = imageView.getId()%height;

                        int y = imageView.getId()/height;

                        int x2 = level.heroX - x;

                        int y2 = level.heroY - y;

                        if(1 >= Math.abs(x2) && 1 >= Math.abs(y2) && map[y][x] == 0)
                        {
                            level.map[y][x] = 3;
                            map[y][x] = 3;
                            level.enemy(0);
                            level.enemy(1);
                            map = level.map;
                            if(level.IsEndBad(0) || level.IsEndBad(1))
                            {
                                map = level.start(InitLevel);
                            }
                            else if(level.IsEndGood())
                            {
                                if(InitLevel==2)
                                {
                                    Intent intent = new Intent(Game.this, VictoryActivity.class);
                                    media.stop();
                                    startActivity(intent);
                                }
                                else
                                {
                                    map = level.start(InitLevel+1);
                                    level.heroX = 3;
                                    level.heroY = 3;
                                    InitLevel += 1;
                                }
                            }
                            else
                            {
                                map = level.map;
                            }
                            map(map);
                        }
                    }
                });
            }
            stk.addView(tbrow);
        }
        stk.setWeightSum(height);

        map = level.start(1);

        map(map);

    }
}