package com.adaptionsoft.games.trivia.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.adaptionsoft.games.trivia.player.Player;

public class MapScoreBoardTest {

  @Test
  public void should_start_scores_at_zero() {
    // Given
    Player playerOne = new Player("one");
    Player playerTwo = new Player("two");

    // When
    ScoreBoard scoreBoard = new MapScoreBoard(6, List.of(
      playerOne,
      playerTwo
    ));

    // Then
    assertEquals(0, scoreBoard.getScore(playerOne));
    assertEquals(0, scoreBoard.getScore(playerTwo));
  }

  @Test
  public void should_acquire_gold_coins() {
    // Given
    Player playerOne = new Player("one");
    Player playerTwo = new Player("two");

    // When
    ScoreBoard scoreBoard = new MapScoreBoard(6, List.of(
      playerOne,
      playerTwo
    ));
    scoreBoard.acquireGoldCoin(playerOne);
    scoreBoard.acquireGoldCoin(playerOne);
    scoreBoard.acquireGoldCoin(playerOne);
    scoreBoard.acquireGoldCoin(playerTwo);
    scoreBoard.acquireGoldCoin(playerTwo);

    // Then
    assertEquals(3, scoreBoard.getScore(playerOne));
    assertEquals(2, scoreBoard.getScore(playerTwo));
  }

  @Test
  public void should_detect_winner() {
    // Given
    Player playerOne = new Player("one");
    Player playerTwo = new Player("two");

    // When
    ScoreBoard scoreBoard = new MapScoreBoard(6, List.of(
      playerOne,
      playerTwo
    ));
    scoreBoard.acquireGoldCoin(playerOne);
    scoreBoard.acquireGoldCoin(playerOne);
    scoreBoard.acquireGoldCoin(playerOne);
    scoreBoard.acquireGoldCoin(playerOne);
    scoreBoard.acquireGoldCoin(playerOne);
    scoreBoard.acquireGoldCoin(playerOne);
    scoreBoard.acquireGoldCoin(playerTwo);
    scoreBoard.acquireGoldCoin(playerTwo);
    scoreBoard.acquireGoldCoin(playerTwo);
    scoreBoard.acquireGoldCoin(playerTwo);
    scoreBoard.acquireGoldCoin(playerTwo);

    // Then
    assertEquals(6, scoreBoard.getScore(playerOne));
    assertTrue(scoreBoard.isWinner(playerOne));
    assertFalse(scoreBoard.isWinner(playerTwo));
  }

}
