package com.adaptionsoft.games.trivia.board;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.adaptionsoft.games.trivia.player.Player;

public class SetPenaltyBoxTest {

  @Test
  public void should_add_player_to_penalty_box() {
    // Given
    Player playerOne = new Player("one");
    Player playerTwo = new Player("two");
    PenaltyBox penaltyBox = new SetPenaltyBox();

    // When
    penaltyBox.add(playerOne);

    // Then
    assertTrue(penaltyBox.hasPenalty(playerOne));
    assertFalse(penaltyBox.hasPenalty(playerTwo));
  }

  @Test
  public void should_remove_players_from_penalty_box() {
    // Given
    Player playerOne = new Player("one");
    Player playerTwo = new Player("two");
    PenaltyBox penaltyBox = new SetPenaltyBox();

    // When
    penaltyBox.add(playerOne);
    penaltyBox.add(playerTwo);
    penaltyBox.remove(playerOne);

    // Then
    assertFalse(penaltyBox.hasPenalty(playerOne));
    assertTrue(penaltyBox.hasPenalty(playerTwo));
  }

}
