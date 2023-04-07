package com.adaptionsoft.games.trivia.question;

public interface QuestionCatalog {

  Question getNextQuestion(QuestionCategory questionCategory);

}
