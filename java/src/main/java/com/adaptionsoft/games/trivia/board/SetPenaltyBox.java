package com.adaptionsoft.games.trivia.board;

import java.util.HashSet;
import java.util.Set;

import com.adaptionsoft.games.trivia.player.Player;

public class SetPenaltyBox implements PenaltyBox {

  private final Set<Player> playersInPenaltyBox;

  public SetPenaltyBox() {
    playersInPenaltyBox = new HashSet<>();
  }

  @Override
  public void add(Player player) {
    playersInPenaltyBox.add(player);
  }

  @Override
  public void remove(Player player) {
    playersInPenaltyBox.remove(player);
  }

  @Override
  public boolean hasPenalty(Player player) {
    return playersInPenaltyBox.contains(player);
  }

}
