package com.adaptionsoft.games.trivia;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.adaptionsoft.games.trivia.board.TriviaBoard;
import com.adaptionsoft.games.trivia.player.PlayersManager;
import com.adaptionsoft.games.trivia.question.QuestionCatalog;

public class TriviaGameEngineTest {

  @Test
  public void should_create_a_new_engine() {
    QuestionCatalog questionCatalog = mock(QuestionCatalog.class);
    PlayersManager playersManager = mock(PlayersManager.class);
    TriviaBoard triviaBoard = mock(TriviaBoard.class);
    TriviaGameEngine triviaGameEngine = new TriviaGameEngine(questionCatalog, playersManager, triviaBoard);

    assertNotNull(triviaGameEngine);
  }

}
