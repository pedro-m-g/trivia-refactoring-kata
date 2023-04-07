package com.adaptionsoft.games.trivia.board;

import com.adaptionsoft.games.trivia.player.Player;

public interface ScoreBoard {

  int getScore(Player player);
  void acquireGoldCoin(Player player);
  boolean isWinner(Player player);

}
