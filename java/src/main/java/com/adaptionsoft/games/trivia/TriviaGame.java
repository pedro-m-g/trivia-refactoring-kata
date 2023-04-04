
package com.adaptionsoft.games.trivia;

import java.util.Random;

import com.adaptionsoft.games.trivia.player.InMemoryPlayersManager;
import com.adaptionsoft.games.trivia.player.PlayersManager;
import com.adaptionsoft.games.trivia.question.IndexedQuestionCatalog;
import com.adaptionsoft.games.trivia.question.QuestionCatalog;

public class TriviaGame {

	public static void main(String[] args) {
		QuestionCatalog questionCatalog = new IndexedQuestionCatalog();
		PlayersManager playersManager = new InMemoryPlayersManager();
		TriviaGameEngine triviaGameEngine = new TriviaGameEngine(questionCatalog, playersManager);

		triviaGameEngine.addPlayer("Chet");
		triviaGameEngine.addPlayer("Pat");
		triviaGameEngine.addPlayer("Sue");

		Random rand = new Random();
		boolean notAWinner;
		do {
			triviaGameEngine.runTurn(rand.nextInt(5) + 1);
			if (rand.nextInt(9) == 7) {
				notAWinner = triviaGameEngine.wrongAnswer();
			} else {
				notAWinner = triviaGameEngine.wasCorrectlyAnswered();
			}
		} while (notAWinner);
	}
}
