package com.adaptionsoft.games.trivia.player;

import java.util.Collection;

public interface PlayersManager {

  void addPlayer(String name);

  int getPlayersCount();

  Collection<Player> getPlayers();

  void moveToNextPlayer();

  Player getCurrentPlayer();

}
