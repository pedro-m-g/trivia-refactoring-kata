package com.adaptionsoft.games.trivia.ui.simplecli;

import java.util.Scanner;
import java.util.logging.Logger;

import com.adaptionsoft.games.trivia.player.Player;
import com.adaptionsoft.games.trivia.question.Question;
import com.adaptionsoft.games.trivia.ui.TriviaUI;

public class SimpleTriviaCLI implements TriviaUI {

  private static final Logger LOGGER = Logger.getLogger(SimpleTriviaCLI.class.getName());
  private static final int DIVIDER_LENGTH = 40;

  private final Scanner scanner;

  public SimpleTriviaCLI() {
    scanner = new Scanner(System.in);
  }

  @Override
  public void showCurrentPlayer(Player currentPlayer) {
    String message = String.format("Next player is %s", currentPlayer);
    LOGGER.info(message);
  }

  @Override
  public void showDiceRoll(int roll) {
    String message = String.format("Player rolled the dice and got %d", roll);
    LOGGER.info(message);
  }

  @Override
  public void moveToPlayer(Player currentPlayer) {
    String message = String.format("Moving to player %s", currentPlayer);
    LOGGER.info(message);
  }

  @Override
  public String promptQuestion(Question question) {
    printDivider();
    String message = String.format("Next question:%n%s%n", question);
    LOGGER.info(message);
    String answer = scanner.nextLine();
    printDivider();
    return answer;
  }

  @Override
  public void onPlayerMove(Player player, int roll, int newLocation) {
    String message = String.format(
        "Player %s moved %d places. New location is %d",
        player,
        roll,
        newLocation);
    LOGGER.info(message);
  }

  @Override
  public void onPenaltyBox(Player player) {
    String message = String.format("Player %s is on penalty box", player);
    LOGGER.info(message);
  }

  @Override
  public void onCorrectAnswer(Player player) {
    String message = String.format("Player %s answered correctly", player);
    LOGGER.info(message);
  }

  @Override
  public void onWrongAnswer(Player player) {
    String message = String.format("Player %s answered incorrectly", player);
    LOGGER.info(message);
  }

  @Override
  public void onScoreUpdate(Player player, int score) {
    String message = String.format("Player %s score is %d", player, score);
    LOGGER.info(message);
  }

  @Override
  public void onOutOfPenaltyBox(Player player) {
    String message = String.format("Player %s got out of penalty box%n", player);
    LOGGER.info(message);
  }

  @Override
  public void onWinner(Player player) {
    String message = String.format("Player %s won, congrats!", player);
    LOGGER.info(message);
  }

  private void printDivider() {
    String message = "-".repeat(DIVIDER_LENGTH);
    LOGGER.info(message);
  }

}
