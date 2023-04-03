package com.adaptionsoft.games.trivia.question;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;

public class IndexedQuestionCatalogTest {

  @Test
  public void should_get_next_question() {
    QuestionCatalog questionCatalog = new IndexedQuestionCatalog();

    Question question0 = questionCatalog.getNextQuestion();
    assertThat("Question is not null", question0, notNullValue());
    assertThat("Question contains index 0", question0.toString(), containsString("0"));

    Question question1 = questionCatalog.getNextQuestion();
    assertThat("Question is not null", question1, notNullValue());
    assertThat("Question contains index 1", question1.toString(), containsString("1"));

    Question question2 = questionCatalog.getNextQuestion();
    assertThat("Question is not null", question2, notNullValue());
    assertThat("Question contains index 2", question2.toString(), containsString("2"));
  }

}
