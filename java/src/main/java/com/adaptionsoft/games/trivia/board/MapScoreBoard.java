package com.adaptionsoft.games.trivia.board;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.adaptionsoft.games.trivia.player.Player;

public class MapScoreBoard implements ScoreBoard {

  private final int winningScore;
  private final Map<Player, Integer> scores;

  public MapScoreBoard(int winningScore, Collection<Player> players) {
    this.winningScore = winningScore;
    this.scores = initScores(players);
  }

  private Map<Player, Integer> initScores(Collection<Player> players) {
    return players
      .stream()
      .collect(Collectors.toMap(
        Function.identity(),
        player -> 0
      ));
  }

  @Override
  public int getScore(Player player) {
    return scores.get(player);
  }

  @Override
  public void acquireGoldCoin(Player player) {
    int newScore = scores.get(player) + 1;
    scores.put(player, newScore);
  }

  @Override
  public boolean isWinner(Player player) {
    return getScore(player) >= winningScore;
  }

}
