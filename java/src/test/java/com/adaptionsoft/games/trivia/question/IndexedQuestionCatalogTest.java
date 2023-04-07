package com.adaptionsoft.games.trivia.question;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

public class IndexedQuestionCatalogTest {

  @Test
  public void should_get_next_question() {
    QuestionCatalog questionCatalog = new IndexedQuestionCatalog();

    Question question0 = questionCatalog.getNextQuestion(QuestionCategory.POP);
    assertNotNull(question0);
    assertThat(question0.toString(), containsString("0"));
    assertThat(question0.toString(), containsString("Pop"));

    Question question1 = questionCatalog.getNextQuestion(QuestionCategory.ROCK);
    assertNotNull(question1);
    assertThat(question1.toString(), containsString("1"));
    assertThat(question1.toString(), containsString("Rock"));

    Question question2 = questionCatalog.getNextQuestion(QuestionCategory.SCIENCE);
    assertNotNull(question2);
    assertThat(question2.toString(), containsString("2"));
    assertThat(question2.toString(), containsString("Science"));
  }

}
