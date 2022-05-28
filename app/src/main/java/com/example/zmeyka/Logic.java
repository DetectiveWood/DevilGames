package com.example.zmeyka;

public class Logic
{
    int[][] map = new int[0][0];
    int heroX = 3;
    int heroY = 4;
    int[] Ex = {3, 5};
    int[] Ey = {0, 7};
    public int[][] start(int level)
    {
        //присылаем расстоновку уровня (врагов и героя)
        if(level == 1)
        {
            heroX = 3;
            heroY = 4;
            Ex[0] = 3;
            Ex[1] = 5;
            Ey[0] = 0;
            Ey[1] = 7;
            map = new int[][]{
                    {0, 0, 0, 2, 0 ,0 ,0},
                    {0, 0, 0, 0, 0 ,0 ,0},
                    {0, 0, 0, 0, 0 ,0 ,0},
                    {0, 0, 0, 0, 0 ,0 ,0},
                    {0, 0, 0, 1, 0 ,0 ,0},
                    {0, 3, 0, 3, 0 ,0 ,0},
                    {0, 0, 0, 0, 0 ,0 ,0},
                    {0, 0, 0, 0, 0 ,2 ,0},

            };

            return map;

        }
        if(level == 2)
        {
            heroX = 3;
            heroY = 3;
            Ex[0] = 0;
            Ex[1] = 5;
            Ey[0] = 0;
            Ey[1] = 7;
            map = new int[][]{
                    {2, 0, 3, 3, 0 ,0 ,0},
                    {0, 3, 0, 0, 0 ,0 ,0},
                    {0, 0, 0, 0, 3 ,0 ,0},
                    {0, 0, 0, 1, 3 ,0 ,0},
                    {0, 0, 3, 0, 0 ,0 ,0},
                    {3, 0, 0, 0, 0 ,0 ,0},
                    {0, 0, 0, 0, 0 ,0 ,0},
                    {0, 0, 0, 0, 0 ,2 ,0},

            };

            return map;

        }
        else
        {
            return new int[0][0];
        }
    }

    public int getHeroX()
    {
        for (int i = 0; i < map.length; i++)
        {
            for (int j = 0; j < map[i].length; j++)
            {
                if(map[i][j] == 1)
                {
                    return j;
                }
            }
        }
        return -1;
    }
    public int getHeroY()
    {
        for (int i = 0; i < map.length; i++)
        {
            for (int j = 0; j < map[i].length; j++)
            {
                if(map[i][j] == 1)
                {
                    return i;
                }
            }
        }
        return -1;
    }

    public void hero(String direction)
    {
        map[heroY][heroX] = 0;
        switch (direction) {
            case "right":
                map[heroY][heroX+1] = 1;
                heroX = heroX+1;
                break;
            case "left":
                map[heroY][heroX-1] = 1;
                heroX = heroX-1;
                break;
            case "up":
                map[heroY - 1][heroX] = 1;
                heroY = heroY - 1;
                break;
            case "down":
                map[heroY + 1][heroX] = 1;
                heroY = heroY + 1;
                break;
            case "up-left":
                map[heroY - 1][heroX - 1] = 1;
                heroX = heroX-1;
                heroY = heroY-1;
                break;
            case "up-right":
                map[heroY - 1][heroX + 1] = 1;
                heroX = heroX+1;
                heroY = heroY-1;
                break;
            case "down-left":
                map[heroY + 1][heroX - 1] = 1;
                heroX = heroX-1;
                heroY = heroY+1;
                break;
            case "down-right":
                map[heroY + 1][heroX + 1] = 1;
                heroX = heroX+1;
                heroY = heroY+1;
                break;
        }
    }
    public int[][] enemy(int i)
    {
        int Xd = heroX-Ex[i];
        int Yd = heroY- Ey[i];

        map[Ey[i]][Ex[i]] = 0;

        if(Xd<0)
        {
            if(Yd<0)
            {
                if(map[Ey[i]-1][Ex[i]-1] == 0)
                {
                    map[Ey[i]-1][Ex[i]-1] = 2;
                    Ex[i] = Ex[i] - 1;
                    Ey[i] = Ey[i] - 1;
                }
                else if(map[Ey[i]][Ex[i]-1] == 0)
                {
                    map[Ey[i]][Ex[i]-1] = 2;
                    Ex[i] = Ex[i] - 1;
                }
                else if(map[Ey[i]-1][Ex[i]] == 0)
                {
                    map[Ey[i]-1][Ex[i]] = 2;
                    Ey[i] = Ey[i] - 1;
                }
                else if(map[Ey[i]-1][Ex[i]-1] != 0)
                {
                    map = AnotherWay(Ey[i], Ex[i],i);
                }
            }
            if(Yd>0)
            {
                if(map[Ey[i]+1][Ex[i]-1] == 0)
                {
                    map[Ey[i]+1][Ex[i]-1] = 2;
                    Ex[i] = Ex[i] - 1;
                    Ey[i] = Ey[i] + 1;
                }
                else if(map[Ey[i]+1][Ex[i]] == 0)
                {
                    map[Ey[i]+1][Ex[i]] = 2;
                    Ey[i] = Ey[i] + 1;
                }
                else if(map[Ey[i]][Ex[i]-1] == 0)
                {
                    map[Ey[i]][Ex[i]-1] = 2;
                    Ex[i] = Ex[i] - 1;
                }
                else if(map[Ey[i]+1][Ex[i]-1] != 0)
                {
                    map = AnotherWay(Ey[i], Ex[i],i);
                }
            }
            if(Yd == 0)
            {
                if(map[Ey[i]][Ex[i]-1] == 0)
                {
                    map[Ey[i]][Ex[i]-1] = 2;
                    Ex[i] = Ex[i] - 1;
                }
                else if(map[Ey[i]][Ex[i]-1] != 0)
                {
                    map = AnotherWay(Ey[i], Ex[i],i);
                }
            }

        }
        else if(Xd>0)
        {
            if(Yd<0)
            {
                if(map[Ey[i]-1][Ex[i]+1] == 0)
                {
                    map[Ey[i]-1][Ex[i]+1] = 2;
                    Ex[i] = Ex[i] + 1;
                    Ey[i] = Ey[i] - 1;
                }
                else if(map[Ey[i]-1][Ex[i]] == 0)
                {
                    map[Ey[i]-1][Ex[i]] = 2;
                    Ey[i] = Ey[i] - 1;
                }
                else if(map[Ey[i]][Ex[i]+1] == 0)
                {
                    map[Ey[i]][Ex[i]+1] = 2;
                    Ex[i] = Ex[i] + 1;
                }
                else if(map[Ey[i]-1][Ex[i]+1] != 0)
                {
                    map = AnotherWay(Ey[i], Ex[i],i);
                }
            }
            if(Yd>0)
            {
                if(map[Ey[i]+1][Ex[i]+1] == 0)
                {
                    map[Ey[i]+1][Ex[i]+1] = 2;
                    Ex[i] = Ex[i] + 1;
                    Ey[i] = Ey[i] + 1;
                }
                else if(map[Ey[i]+1][Ex[i]] == 0)
                {
                    map[Ey[i]+1][Ex[i]] = 2;
                    Ey[i] = Ey[i] + 1;
                }
                else if(map[Ey[i]][Ex[i]+1] == 0)
                {
                    map[Ey[i]][Ex[i]+1] = 2;
                    Ex[i] = Ex[i] + 1;
                }
                else if(map[Ey[i]+1][Ex[i]+1] != 0)
                {
                    map = AnotherWay(Ey[i], Ex[i],i);
                }
            }
            if(Yd == 0)
            {
                if(map[Ey[i]][Ex[i] + 1] == 0)
                {
                    map[Ey[i]][Ex[i]+1] = 2;
                    Ex[i] = Ex[i] + 1;
                }
                else if(map[Ey[i]][Ex[i]+1] != 0)
                {
                    map = AnotherWay(Ey[i], Ex[i], i);
                }
            }
        }
        else if(Xd == 0)
        {
            if(Yd>0 && map[Ey[i]+1][Ex[i]] == 0)
            {
                map[Ey[i]+1][Ex[i]] = 2;
                Ey[i] = Ey[i] + 1;
            }
            else if(map[Ey[i]][Ex[i]-1] == 0)
            {
                map[Ey[i]][Ex[i]-1] = 2;
                Ex[i] = Ex[i] - 1;
            }
            else if(map[Ey[i]][Ex[i]+1] == 0)
            {
                map[Ey[i]][Ex[i]+1] = 2;
                Ex[i] = Ex[i] + 1;
            }
            else if(Yd<0 && map[Ey[i]-1][Ex[i]] == 0)
            {
                map[Ey[i]-1][Ex[i]] = 2;
                Ey[i] = Ey[i] - 1;
            }
            else
            {
                map = AnotherWay(Ey[i], Ex[i], i);
            }
        }
        else if(Yd == 0)
        {
            if(Xd>0 && map[Ey[i]][Ex[i] + 1] == 0)
            {
                map[Ey[i]][Ex[i]+1] = 2;
                Ex[i] = Ex[i] + 1;
            }
            else if(Xd<0 && map[Ey[i]][Ex[i] - 1] == 0)
            {
                map[Ey[i]][Ex[i]-1] = 2;
                Ex[i] = Ex[i] - 1;
            }
            else
            {
                map = AnotherWay(Ey[i], Ex[i], i);
            }
        }
        return map;
    }

    public int[][] AnotherWay(int EnemyY, int EnemyX, int i)
    {
        if( EnemyX - 1 >= 0 && EnemyY-1 >= 0 && map[EnemyY-1][EnemyX-1] == 0)
        {
            map[EnemyY-1][EnemyX-1] = 2;
            Ex[i] = Ex[i] - 1;
            Ey[i] = Ey[i] - 1;
            map[EnemyY][EnemyX] = 0;
        }
        else if(EnemyX - 1 >= 0 && EnemyY-1 >= 0 && map[EnemyY-1][EnemyX+1] == 0)
        {
            map[EnemyY-1][EnemyX+1] = 2;
            Ex[i] = Ex[i] + 1;
            Ey[i] = Ey[i] - 1;
            map[EnemyY][EnemyX] = 0;
        }
        else if(EnemyX - 1 >= 0 && EnemyY-1 >= 0 && map[EnemyY-1][EnemyX] == 0)
        {
            map[EnemyY-1][EnemyX] = 2;
            Ey[i] = Ey[i] - 1;
            map[EnemyY][EnemyX] = 0;
        }
        else if(EnemyX + 1 <= 6 && EnemyY+1 <= 7 && map[EnemyY][EnemyX+1] == 0)
        {
            map[EnemyY][EnemyX+1] = 2;
            Ex[i] = Ex[i] + 1;
            map[EnemyY][EnemyX] = 0;
        }
        else if(EnemyX + 1 <= 6 && EnemyY+1 <= 7 && map[EnemyY+1][EnemyX+1] == 0)
        {
            map[EnemyY+1][EnemyX+1] = 2;
            Ex[i] = Ex[i] + 1;
            Ey[i] = Ey[i] + 1;
            map[EnemyY][EnemyX] = 0;
        }
        else if(EnemyX - 1 >= 0 && EnemyY-1 >= 0 && map[EnemyY][EnemyX-1] == 0)
        {
            map[EnemyY][EnemyX-1] = 2;
            Ex[i] = Ex[i] - 1;
            map[EnemyY][EnemyX] = 0;
        }
        else if(EnemyX + 1 <= 6 && EnemyY-1 >= 0 && map[EnemyY+1][EnemyX-1] == 0)
        {
            map[EnemyY+1][EnemyX-1] = 2;
            Ex[i] = Ex[i] - 1;
            Ey[i] = Ey[i] + 1;
            map[EnemyY][EnemyX] = 0;
        }
        return map;
    }

    public boolean IsEndBad(int i)
    {
        return Math.abs(heroX - Ex[i]) <= 1 && Math.abs(heroY - Ey[i]) <= 1;
    }
    public boolean IsEndGood()
    {
        return map[heroY][heroX+1] == 3 && map[heroY][heroX-1] == 3 && map[heroY-1][heroX+1] == 3 && map[heroY-1][heroX-1] == 3 && map[heroY+1][heroX+1] == 3 && map[heroY+1][heroX-1] == 3;
    }

    public int[][] map()
    {
        return map;
    }




}

