package com.adaptionsoft.games.trivia.question;

public class IndexedQuestionCatalog implements QuestionCatalog {

  private int index = 0;

  @Override
  public Question getNextQuestion() {
    Question question = new Question("Pop question " + index);
    index++;
    return question;
  }

}
