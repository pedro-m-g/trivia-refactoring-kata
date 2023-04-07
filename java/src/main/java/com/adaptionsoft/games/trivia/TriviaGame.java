
package com.adaptionsoft.games.trivia;

import java.util.Random;

import com.adaptionsoft.games.trivia.board.ClassicTriviaBoard;
import com.adaptionsoft.games.trivia.board.PenaltyBox;
import com.adaptionsoft.games.trivia.board.SetPenaltyBox;
import com.adaptionsoft.games.trivia.board.TriviaBoard;
import com.adaptionsoft.games.trivia.player.InMemoryPlayersManager;
import com.adaptionsoft.games.trivia.player.PlayersManager;
import com.adaptionsoft.games.trivia.question.IndexedQuestionCatalog;
import com.adaptionsoft.games.trivia.question.QuestionCatalog;

public class TriviaGame {

	private static final int BOARD_SIZE = 12;

	public static void main(String[] args) {
		QuestionCatalog questionCatalog = new IndexedQuestionCatalog();

		PlayersManager playersManager = new InMemoryPlayersManager();
		playersManager.addPlayer("Chet");
		playersManager.addPlayer("Pat");
		playersManager.addPlayer("Sue");

		TriviaBoard triviaBoard = new ClassicTriviaBoard(
			BOARD_SIZE,
			playersManager.getPlayers()
		);

		PenaltyBox penaltyBox = new SetPenaltyBox();

		TriviaGameEngine triviaGameEngine = new TriviaGameEngine(
			questionCatalog,
			playersManager,
			triviaBoard,
			penaltyBox
		);

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
