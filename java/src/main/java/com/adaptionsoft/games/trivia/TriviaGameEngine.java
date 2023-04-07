package com.adaptionsoft.games.trivia;

import java.util.List;
import java.util.stream.Collectors;

import com.adaptionsoft.games.trivia.board.PenaltyBox;
import com.adaptionsoft.games.trivia.board.TriviaBoard;
import com.adaptionsoft.games.trivia.player.Player;
import com.adaptionsoft.games.trivia.player.PlayersManager;
import com.adaptionsoft.games.trivia.question.Question;
import com.adaptionsoft.games.trivia.question.QuestionCatalog;
import com.adaptionsoft.games.trivia.question.QuestionCategory;

public class TriviaGameEngine {

	private final QuestionCatalog questionCatalog;
	private final PlayersManager playersManager;
	private final TriviaBoard triviaBoard;
	private final List<Integer> pursesList;
	private final PenaltyBox penaltyBox;

	int currentPlayerTurn = 0;
	boolean isGettingOutOfPenaltyBox;

	public TriviaGameEngine(
		QuestionCatalog questionCatalog,
		PlayersManager playersManager,
		TriviaBoard triviaBoard,
		PenaltyBox penaltyBox
	) {
		this.questionCatalog = questionCatalog;
		this.playersManager = playersManager;
		this.triviaBoard = triviaBoard;
		this.penaltyBox = penaltyBox;
		this.pursesList = playersManager
				.getPlayers()
				.stream()
				.map(player -> 0)
				.collect(Collectors.toList());
	}

	public void runTurn(int roll) {
		Player currentPlayer = playersManager.getCurrentPlayer();
		System.out.println(currentPlayer + " is the current player");
		System.out.println("They have rolled a " + roll);

		if (penaltyBox.hasPenalty(currentPlayer)) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;

				System.out.println(currentPlayer + " is getting out of the penalty box");
				triviaBoard.movePlayer(currentPlayer, roll);
				int newPlayerLocation = triviaBoard.getPlayerLocation(currentPlayer);
				System.out.println(currentPlayer + "'s new location is " + newPlayerLocation);
				QuestionCategory questionCategory = triviaBoard.getQuestionCategoryAt(newPlayerLocation);
				System.out.println("The category is " + questionCategory);
				askQuestion();
			} else {
				System.out.println(currentPlayer + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
			}
		} else {
			triviaBoard.movePlayer(currentPlayer, roll);
			int newPlayerLocation = triviaBoard.getPlayerLocation(currentPlayer);
			System.out.println(currentPlayer + "'s new location is " + newPlayerLocation);
			QuestionCategory questionCategory = triviaBoard.getQuestionCategoryAt(newPlayerLocation);
			System.out.println("The category is " + questionCategory);
			askQuestion();
		}

	}

	private void askQuestion() {
		Question question = questionCatalog.getNextQuestion();
		System.out.println(question);
	}

	public boolean wasCorrectlyAnswered() {
		Player currentPlayer = playersManager.getCurrentPlayer();
		int playersCount = playersManager.getPlayersCount();

		if (penaltyBox.hasPenalty(currentPlayer)) {
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");

				int currentPurses = pursesList.get(currentPlayerTurn);
				pursesList.set(currentPlayerTurn, currentPurses);
				penaltyBox.remove(currentPlayer);

				System.out.println(currentPlayer + " now has " + currentPurses + " Gold Coins.");

				boolean winner = didPlayerWin();
				currentPlayerTurn++;
				playersManager.moveToNextPlayer();
				if (currentPlayerTurn == playersCount) {
					currentPlayerTurn = 0;
				}
				return winner;
			} else {
				currentPlayerTurn++;
				playersManager.moveToNextPlayer();
				if (currentPlayerTurn == playersCount) {
					currentPlayerTurn = 0;
				}
				return true;
			}
		} else {
			System.out.println("Answer was correct!!!!");

			int newPurses = pursesList.get(currentPlayerTurn) + 1;
			pursesList.set(currentPlayerTurn, newPurses);

			System.out.println(currentPlayer + " now has " + newPurses + " Gold Coins.");

			boolean winner = didPlayerWin();
			currentPlayerTurn++;
			playersManager.moveToNextPlayer();
			if (currentPlayerTurn == playersCount) {
				currentPlayerTurn = 0;
			}
			return winner;
		}
	}

	public boolean wrongAnswer() {
		Player currentPlayer = playersManager.getCurrentPlayer();

		System.out.println("Question was incorrectly answered");
		System.out.println(currentPlayer + " was sent to the penalty box");

		penaltyBox.add(currentPlayer);
		currentPlayerTurn++;
		playersManager.moveToNextPlayer();
		if (currentPlayerTurn == playersManager.getPlayersCount()) {
			currentPlayerTurn = 0;
		}

		return true;
	}

	private boolean didPlayerWin() {
		return !(pursesList.get(currentPlayerTurn) == 6);
	}
}
