package com.adaptionsoft.games.trivia;

import java.util.List;
import java.util.stream.Collectors;

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
	private List<Integer> pursesList;
	boolean[] inPenaltyBox = new boolean[6];

	int currentPlayerTurn = 0;
	boolean isGettingOutOfPenaltyBox;

	public TriviaGameEngine(QuestionCatalog questionCatalog, PlayersManager playersManager, TriviaBoard triviaBoard) {
		this.questionCatalog = questionCatalog;
		this.playersManager = playersManager;
		this.triviaBoard = triviaBoard;

		pursesList = playersManager
			.getPlayers()
			.stream()
			.map(player -> 0)
			.collect(Collectors.toList());
	}

	public boolean addPlayer(String playerName) {
		playersManager.addPlayer(playerName);

		int nextIndex = playersManager.getPlayersCount();
		inPenaltyBox[nextIndex] = false;

		System.out.println(playerName + " was added");
		System.out.println("They are player number " + playersManager.getPlayersCount());
		return true;
	}

	public void runTurn(int roll) {
		Player currentPlayer = playersManager.getCurrentPlayer();
		System.out.println(currentPlayer + " is the current player");
		System.out.println("They have rolled a " + roll);

		if (inPenaltyBox[currentPlayerTurn]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;

				System.out.println(currentPlayer + " is getting out of the penalty box");
				triviaBoard.movePlayer(currentPlayer, roll);
				int newPlayerLocation = triviaBoard.getPlayerLocation(currentPlayer);
				System.out.println(currentPlayer + "'s new location is " + newPlayerLocation);
				QuestionCategory questionCategory = triviaBoard.getQuestionCategoryForPlayer(currentPlayer);
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
			QuestionCategory questionCategory = triviaBoard.getQuestionCategoryForPlayer(currentPlayer);
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

		if (inPenaltyBox[currentPlayerTurn]) {
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");

				int currentPurses = pursesList.get(currentPlayerTurn);
				pursesList.set(currentPlayerTurn, currentPurses);

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
		System.out.println("Question was incorrectly answered");
		System.out.println(playersManager.getCurrentPlayer() + " was sent to the penalty box");
		inPenaltyBox[currentPlayerTurn] = true;

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
