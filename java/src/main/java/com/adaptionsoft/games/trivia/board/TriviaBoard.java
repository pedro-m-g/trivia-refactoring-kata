package com.adaptionsoft.games.trivia.board;

import com.adaptionsoft.games.trivia.player.Player;
import com.adaptionsoft.games.trivia.question.QuestionCategory;

public interface TriviaBoard {

  void movePlayer(Player player, int places);

  int getPlayerLocation(Player player);

  QuestionCategory getQuestionCategoryAt(int location);

}
