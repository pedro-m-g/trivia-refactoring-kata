package com.adaptionsoft.games.trivia.question;

public class Question {

  private final String text;

  public Question(String text) {
    this.text = text;
  }

  public boolean isCorrect(String answer) {
    return true;
  }

  @Override
  public String toString() {
    return text;
  }

}
