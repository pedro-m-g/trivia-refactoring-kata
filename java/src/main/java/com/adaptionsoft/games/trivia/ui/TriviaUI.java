package com.adaptionsoft.games.trivia.ui;

import com.adaptionsoft.games.trivia.player.Player;
import com.adaptionsoft.games.trivia.question.Question;

public interface TriviaUI {

  void showCurrentPlayer(Player currentPlayer);

  void showDiceRoll(int roll);

  void moveToPlayer(Player currentPlayer);

  String promptQuestion(Question question);

  void onPlayerMove(Player player, int roll, int newLocation);

  void onPenaltyBox(Player player);

  void onCorrectAnswer(Player currentPlayer);

  void onWrongAnswer(Player currentPlayer);

  void onScoreUpdate(Player player, int score);

  void onOutOfPenaltyBox(Player currentPlayer);

  void onWinner(Player player);

}
