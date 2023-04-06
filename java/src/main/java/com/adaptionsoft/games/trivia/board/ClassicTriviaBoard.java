package com.adaptionsoft.games.trivia.board;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.adaptionsoft.games.trivia.player.Player;
import com.adaptionsoft.games.trivia.question.QuestionCategory;

public class ClassicTriviaBoard implements TriviaBoard {

  private final int size;
  private final Map<Player, Integer> playerLocations;
  private final List<QuestionCategory> questionCategoryLocations;

  public ClassicTriviaBoard(int size, Collection<Player> players) {
    this.size = size;
    playerLocations = initPlayerLocations(players);
    questionCategoryLocations = initQuestionCategoryLocations(players);
  }

  private Map<Player, Integer> initPlayerLocations(Collection<Player> players) {
    return players
        .stream()
        .collect(Collectors.toMap(
            Function.identity(),
            player -> 0));
  }

  private List<QuestionCategory> initQuestionCategoryLocations(Collection<Player> players) {
    List<QuestionCategory> allQuestionCategories = QuestionCategory.getAllQuestionCategories();
    int questionCategoriesCount = allQuestionCategories.size();
    return IntStream
        .range(0, size)
        .boxed()
        .map(location -> location % questionCategoriesCount)
        .map(index -> allQuestionCategories.get(index))
        .collect(Collectors.toList());
  }

  @Override
  public void movePlayer(Player player, int places) {
    int currentLocation = getPlayerLocation(player);
    int newLocation = (currentLocation + places) % size;
    playerLocations.put(player, newLocation);
  }

  @Override
  public int getPlayerLocation(Player player) {
    return playerLocations.get(player);
  }

  @Override
  public QuestionCategory getQuestionCategoryAt(int location) {
    return questionCategoryLocations.get(location);
  }

}
