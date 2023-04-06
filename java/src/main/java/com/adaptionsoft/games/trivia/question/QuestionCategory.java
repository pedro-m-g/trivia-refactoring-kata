package com.adaptionsoft.games.trivia.question;

import java.util.List;
import java.util.stream.Stream;

public enum QuestionCategory {
  POP("Pop"),
  SCIENCE("Science"),
  SPORTS("Sports"),
  ROCK("Rock");

  private String name;

  private QuestionCategory(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }

  public static List<QuestionCategory> getAllQuestionCategories() {
    return Stream.of(values()).toList();
  }

}
