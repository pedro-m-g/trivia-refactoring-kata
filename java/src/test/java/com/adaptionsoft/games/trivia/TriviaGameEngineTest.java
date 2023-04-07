package com.adaptionsoft.games.trivia;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.adaptionsoft.games.dice.Dice;
import com.adaptionsoft.games.trivia.board.PenaltyBox;
import com.adaptionsoft.games.trivia.board.ScoreBoard;
import com.adaptionsoft.games.trivia.board.TriviaBoard;
import com.adaptionsoft.games.trivia.player.PlayersManager;
import com.adaptionsoft.games.trivia.question.QuestionCatalog;

public class TriviaGameEngineTest {

  @Test
  public void should_create_a_new_engine() {
    // Given
    QuestionCatalog questionCatalog = mock(QuestionCatalog.class);
    PlayersManager playersManager = mock(PlayersManager.class);
    TriviaBoard triviaBoard = mock(TriviaBoard.class);
    PenaltyBox penaltyBox = mock(PenaltyBox.class);
    ScoreBoard scoreBoard = mock(ScoreBoard.class);
    Dice dice = mock(Dice.class);

    // When
    TriviaGameEngine triviaGameEngine = new TriviaGameEngine(
      questionCatalog,
      playersManager,
      triviaBoard,
      penaltyBox,
      scoreBoard,
      dice
    );

    // Then
    assertNotNull(triviaGameEngine);
  }

}
