package com.adaptionsoft.games.trivia.engine;

public interface TriviaEngine {

  void doTurn();
  void onCorrectAnswer();
  void onWrongAnswer();
  boolean currentPlayerWon();

}
