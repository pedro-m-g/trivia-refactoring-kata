package com.adaptionsoft.games.dice;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

import com.adaptionsoft.games.trivia.dice.Dice;
import com.adaptionsoft.games.trivia.dice.FairDice;

import static org.hamcrest.MatcherAssert.assertThat;

public class FairDiceTest {

  public void should_roll_dice() {
    // When
    Dice dice = new FairDice(6);
    int roll1 = dice.roll();
    int roll2 = dice.roll();
    int roll3 = dice.roll();
    int roll4 = dice.roll();
    int roll5 = dice.roll();
    int roll6 = dice.roll();
    int roll7 = dice.roll();
    int roll8 = dice.roll();
    int roll9 = dice.roll();
    int roll10 = dice.roll();

    // Then
    assertThat(roll1, allOf(greaterThan(0), lessThan(7)));
    assertThat(roll2, allOf(greaterThan(0), lessThan(7)));
    assertThat(roll3, allOf(greaterThan(0), lessThan(7)));
    assertThat(roll4, allOf(greaterThan(0), lessThan(7)));
    assertThat(roll5, allOf(greaterThan(0), lessThan(7)));
    assertThat(roll6, allOf(greaterThan(0), lessThan(7)));
    assertThat(roll7, allOf(greaterThan(0), lessThan(7)));
    assertThat(roll8, allOf(greaterThan(0), lessThan(7)));
    assertThat(roll9, allOf(greaterThan(0), lessThan(7)));
    assertThat(roll10, allOf(greaterThan(0), lessThan(7)));
  }

}
