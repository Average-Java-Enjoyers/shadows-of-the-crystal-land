package com.averagejavaenjoyers.game;

public class GameLogic {
    private Position startPosition;
    private WinConditions winCondition;
    private int enemies;


    public GameLogic(Position start, WinConditions winC, int number){
        startPosition = start;
        winCondition = winC;
        enemies = number;
    }

}
