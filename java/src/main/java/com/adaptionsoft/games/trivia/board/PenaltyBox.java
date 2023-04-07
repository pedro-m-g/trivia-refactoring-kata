package com.adaptionsoft.games.trivia.board;

import com.adaptionsoft.games.trivia.player.Player;

public interface PenaltyBox {

  void add(Player player);
  void remove(Player player);
  boolean hasPenalty(Player player);

}
