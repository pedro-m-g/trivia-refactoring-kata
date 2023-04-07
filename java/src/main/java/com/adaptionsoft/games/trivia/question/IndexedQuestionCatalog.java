package com.adaptionsoft.games.trivia.question;

public class IndexedQuestionCatalog implements QuestionCatalog {

  private int index = 0;

  @Override
  public Question getNextQuestion(QuestionCategory questionCategory) {
    Question question = new Question(questionCategory + " question " + index);
    index++;
    return question;
  }

}
