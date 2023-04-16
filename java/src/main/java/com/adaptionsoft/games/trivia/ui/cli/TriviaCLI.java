package com.adaptionsoft.games.trivia.ui.cli;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import com.adaptionsoft.games.trivia.player.Player;
import com.adaptionsoft.games.trivia.question.Question;
import com.adaptionsoft.games.trivia.ui.TriviaUI;

public class TriviaCLI implements TriviaUI {

  private final Scanner scanner;
  private final PrintStream output;

  public TriviaCLI(InputStream input, PrintStream output) {
    scanner = new Scanner(input);
    this.output = output;
  }

  @Override
  public void showCurrentPlayer(Player currentPlayer) {
    String message = String.format("Next player is %s", currentPlayer);
    output.println(message);
  }

  @Override
  public void showDiceRoll(int roll) {
    String message = String.format("Player rolled the dice and got %d", roll);
    output.println(message);
  }

  @Override
  public void moveToPlayer(Player currentPlayer) {
    String message = String.format("Moving to player %s", currentPlayer);
    output.println(message);
  }

  @Override
  public String promptQuestion(Question question) {
    String message = String.format("Next question:%n%s%n", question);
    output.println(message);
    String answer = scanner.nextLine();
    return answer;
  }

  @Override
  public void onPlayerMove(Player player, int roll, int newLocation) {
    String message = String.format(
        "Player %s moved %d places. New location is %d",
        player,
        roll,
        newLocation);
    output.println(message);
  }

  @Override
  public void onPenaltyBox(Player player) {
    String message = String.format("Player %s is on penalty box", player);
    output.println(message);
  }

  @Override
  public void onCorrectAnswer(Player player) {
    String message = String.format("Player %s answered correctly", player);
    output.println(message);
  }

  @Override
  public void onWrongAnswer(Player player) {
    String message = String.format("Player %s answered incorrectly", player);
    output.println(message);
  }

  @Override
  public void onScoreUpdate(Player player, int score) {
    String message = String.format("Player %s score is %d", player, score);
    output.println(message);
  }

  @Override
  public void onOutOfPenaltyBox(Player player) {
    String message = String.format("Player %s got out of penalty box%n", player);
    output.println(message);
  }

  @Override
  public void onWinner(Player player) {
    String message = String.format("Player %s won, congrats!", player);
    output.println(message);
  }

}
