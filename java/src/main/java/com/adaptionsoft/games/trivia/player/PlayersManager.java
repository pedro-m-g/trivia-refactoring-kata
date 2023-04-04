package com.adaptionsoft.games.trivia.player;

public interface PlayersManager {

  void addPlayer(String name);

  void moveToNextPlayer();

  Player getCurrentPlayer();

  int getPlayersCount();

}
