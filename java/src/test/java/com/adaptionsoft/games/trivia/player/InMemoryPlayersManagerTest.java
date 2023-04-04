package com.adaptionsoft.games.trivia.player;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class InMemoryPlayersManagerTest {

  @Test
  public void should_add_players() {
    PlayersManager playersManager = new InMemoryPlayersManager();
    playersManager.addPlayer("One");
    playersManager.addPlayer("Two");
    playersManager.addPlayer("Three");

    int playersCount = playersManager.getPlayersCount();
    assertEquals(3, playersCount);
  }

  @Test
  public void should_handle_player_turns() {
    PlayersManager playersManager = new InMemoryPlayersManager();
    playersManager.addPlayer("One");
    playersManager.addPlayer("Two");
    playersManager.addPlayer("Three");

    Player playerOne = playersManager.getCurrentPlayer();
    assertEquals(new Player("One"), playerOne);

    playersManager.moveToNextPlayer();

    Player playerTwo = playersManager.getCurrentPlayer();
    assertEquals(new Player("Two"), playerTwo);
    playersManager.moveToNextPlayer();

    Player playerThree = playersManager.getCurrentPlayer();
    assertEquals(new Player("Three"), playerThree);

    playersManager.moveToNextPlayer();

    Player playerFour = playersManager.getCurrentPlayer();
    assertEquals(new Player("One"), playerFour);
  }

}
