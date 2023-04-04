package com.adaptionsoft.games.trivia.player;

import java.util.Objects;

public class Player {

  private final String name;

  public Player(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object otherObject) {
    if (otherObject == null || (otherObject instanceof Player)) {
      return false;
    }
    Player otherPlayer = (Player) otherObject;
    return this.name.equals(otherPlayer.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  @Override
  public String toString() {
    return name;
  }

}
