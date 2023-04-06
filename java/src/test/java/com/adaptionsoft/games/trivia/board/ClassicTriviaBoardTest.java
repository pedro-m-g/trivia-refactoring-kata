package com.adaptionsoft.games.trivia.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.adaptionsoft.games.trivia.player.Player;

public class ClassicTriviaBoardTest {

  @Test
  public void should_start_at_zero() {
    // Given
    Player playerOne = new Player("one");
    Player playerTwo = new Player("two");

    // When
    TriviaBoard triviaBoard = new ClassicTriviaBoard(10, List.of(
      playerOne,
      playerTwo
    ));

    // Then
    assertEquals(0, triviaBoard.getPlayerLocation(playerOne));
    assertEquals(0, triviaBoard.getPlayerLocation(playerTwo));
  }

  @ParameterizedTest
  @MethodSource
  public void should_move_players(int places, int expectedLocation) {
    // Given
    Player player = new Player("one");

    // When
    TriviaBoard triviaBoard = new ClassicTriviaBoard(10, List.of(player));
    triviaBoard.movePlayer(player, places);

    // Then
    assertEquals(expectedLocation, triviaBoard.getPlayerLocation(player));
  }

  public static Stream<Arguments> should_move_players() {
    return Stream.of(
      Arguments.of(0, 0),
      Arguments.of(3, 3),
      Arguments.of(9, 9),
      Arguments.of(10, 0),
      Arguments.of(13, 3),
      Arguments.of(19, 9)
    );
  }

  public void should_get_question_category_in_location() {
    // Whem
    TriviaBoard triviaBoard = new ClassicTriviaBoard(10, List.of());

    // Then
    assertNotNull(triviaBoard.getQuestionCategoryAt(0));
    assertNotNull(triviaBoard.getQuestionCategoryAt(1));
    assertNotNull(triviaBoard.getQuestionCategoryAt(2));
    assertNotNull(triviaBoard.getQuestionCategoryAt(3));
    assertNotNull(triviaBoard.getQuestionCategoryAt(4));
    assertNotNull(triviaBoard.getQuestionCategoryAt(5));
    assertNotNull(triviaBoard.getQuestionCategoryAt(6));
    assertNotNull(triviaBoard.getQuestionCategoryAt(7));
    assertNotNull(triviaBoard.getQuestionCategoryAt(8));
    assertNotNull(triviaBoard.getQuestionCategoryAt(9));
  }

}
