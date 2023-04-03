package com.adaptionsoft.games.trivia.question;

public class Question {

  private final String text;

  public Question(String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return text;
  }

}
