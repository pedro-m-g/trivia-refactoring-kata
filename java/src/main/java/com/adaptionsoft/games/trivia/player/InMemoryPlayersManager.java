package com.adaptionsoft.games.trivia.player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class InMemoryPlayersManager implements PlayersManager {

  private final List<Player> players;

  private int currentPlayerTurn;

  public InMemoryPlayersManager() {
    players = new ArrayList<>();
    currentPlayerTurn = 0;
  }

  @Override
  public void addPlayer(String playerName) {
    players.add(new Player(playerName));
  }

  @Override
  public int getPlayersCount() {
    return players.size();
  }

  @Override
  public Collection<Player> getPlayers() {
    return players;
  }

  @Override
  public Player getCurrentPlayer() {
    if (players.size() <= currentPlayerTurn) {
      return null;
    }
    return players.get(currentPlayerTurn);
  }

  @Override
  public void moveToNextPlayer() {
    currentPlayerTurn++;
    if (currentPlayerTurn >= players.size()) {
      currentPlayerTurn = 0;
    }
  }



}
