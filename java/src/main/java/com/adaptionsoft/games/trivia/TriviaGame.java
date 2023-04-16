
package com.adaptionsoft.games.trivia;

import java.util.Collection;

import com.adaptionsoft.games.trivia.board.ClassicTriviaBoard;
import com.adaptionsoft.games.trivia.board.MapScoreBoard;
import com.adaptionsoft.games.trivia.board.PenaltyBox;
import com.adaptionsoft.games.trivia.board.ScoreBoard;
import com.adaptionsoft.games.trivia.board.SetPenaltyBox;
import com.adaptionsoft.games.trivia.board.TriviaBoard;
import com.adaptionsoft.games.trivia.dice.Dice;
import com.adaptionsoft.games.trivia.dice.FairDice;
import com.adaptionsoft.games.trivia.engine.TriviaEngine;
import com.adaptionsoft.games.trivia.player.InMemoryPlayersManager;
import com.adaptionsoft.games.trivia.player.Player;
import com.adaptionsoft.games.trivia.player.PlayersManager;
import com.adaptionsoft.games.trivia.question.IndexedQuestionCatalog;
import com.adaptionsoft.games.trivia.question.QuestionCatalog;
import com.adaptionsoft.games.trivia.ui.TriviaUI;
import com.adaptionsoft.games.trivia.ui.cli.TriviaCLI;

public class TriviaGame {

	private static final int BOARD_SIZE = 12;
	private static final int WINNING_SCORE = 6;
	private static final int NUMBER_OF_FACES = 6;

	public static void main(String[] args) {
		QuestionCatalog questionCatalog = new IndexedQuestionCatalog();

		PlayersManager playersManager = new InMemoryPlayersManager();
		playersManager.addPlayer("Chet");
		playersManager.addPlayer("Pat");
		playersManager.addPlayer("Sue");

		Collection<Player> players = playersManager.getPlayers();
		TriviaBoard triviaBoard = new ClassicTriviaBoard(BOARD_SIZE, players);
		PenaltyBox penaltyBox = new SetPenaltyBox();
		ScoreBoard scoreBoard = new MapScoreBoard(WINNING_SCORE, players);
		Dice dice = new FairDice(NUMBER_OF_FACES);
		TriviaUI triviaUI = new TriviaCLI(System.in, System.out);

		TriviaEngine triviaEngine = new TriviaEngine(
			questionCatalog,
			playersManager,
			triviaBoard,
			penaltyBox,
			scoreBoard,
			dice,
			triviaUI
		);

		triviaEngine.run();
	}
}
