package com.adaptionsoft.games.trivia;

import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.adaptionsoft.games.trivia.player.PlayersManager;
import com.adaptionsoft.games.trivia.question.QuestionCatalog;

public class TriviaGameEngineTest {

  @Test
  public void should_create_a_new_engine() {
    QuestionCatalog questionCatalog = mock(QuestionCatalog.class);
    PlayersManager playersManager = mock(PlayersManager.class);
    new TriviaGameEngine(questionCatalog, playersManager);
  }

}
