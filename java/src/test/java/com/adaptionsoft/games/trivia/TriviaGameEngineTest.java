package com.adaptionsoft.games.trivia;

import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.adaptionsoft.games.trivia.question.QuestionCatalog;

public class TriviaGameEngineTest {

  @Test
  public void should_create_a_new_engine() {
    QuestionCatalog catalog = mock(QuestionCatalog.class);
    new TriviaGameEngine(catalog);
  }

}
