package com.adaptionsoft.games.trivia.question;

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

  public static QuestionCategory pickRandom() {
    QuestionCategory[] allCategories = QuestionCategory.values();
    int randomIndex = Double
      .valueOf(Math.floor(Math.random() * allCategories.length))
      .intValue();
    return allCategories[randomIndex];
  }

}
