
package com.adaptionsoft.games.trivia;

import java.util.Collection;
import java.util.Random;

import com.adaptionsoft.games.trivia.board.ClassicTriviaBoard;
import com.adaptionsoft.games.trivia.board.MapScoreBoard;
import com.adaptionsoft.games.trivia.board.PenaltyBox;
import com.adaptionsoft.games.trivia.board.ScoreBoard;
import com.adaptionsoft.games.trivia.board.SetPenaltyBox;
import com.adaptionsoft.games.trivia.board.TriviaBoard;
import com.adaptionsoft.games.trivia.player.InMemoryPlayersManager;
import com.adaptionsoft.games.trivia.player.Player;
import com.adaptionsoft.games.trivia.player.PlayersManager;
import com.adaptionsoft.games.trivia.question.IndexedQuestionCatalog;
import com.adaptionsoft.games.trivia.question.QuestionCatalog;

public class TriviaGame {

	private static final int BOARD_SIZE = 12;
	private static final int WINNING_SCORE = 6;

	public static void main(String[] args) {
		QuestionCatalog questionCatalog = new IndexedQuestionCatalog();

		PlayersManager playersManager = new InMemoryPlayersManager();
		playersManager.addPlayer("Chet");
		playersManager.addPlayer("Pat");
		playersManager.addPlayer("Sue");

		Collection<Player> players = playersManager.getPlayers();

		TriviaBoard triviaBoard = new ClassicTriviaBoard(
			BOARD_SIZE,
			players
		);

		PenaltyBox penaltyBox = new SetPenaltyBox();

		ScoreBoard scoreBoard = new MapScoreBoard(WINNING_SCORE, players);

		TriviaGameEngine triviaGameEngine = new TriviaGameEngine(
			questionCatalog,
			playersManager,
			triviaBoard,
			penaltyBox,
			scoreBoard
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
