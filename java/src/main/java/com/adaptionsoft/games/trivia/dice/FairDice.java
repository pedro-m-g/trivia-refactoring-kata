package com.adaptionsoft.games.trivia.dice;

import java.util.Random;

public class FairDice implements Dice {

  private final Random random;
  private final int numberOfFaces;

  public FairDice(int numberOfFaces) {
    random = new Random();
    this.numberOfFaces = numberOfFaces;
  }

  @Override
  public int roll() {
    return random.nextInt(numberOfFaces) + 1;
  }

}
