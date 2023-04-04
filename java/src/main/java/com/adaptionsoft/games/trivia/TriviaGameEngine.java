package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.player.Player;
import com.adaptionsoft.games.trivia.player.PlayersManager;
import com.adaptionsoft.games.trivia.question.Question;
import com.adaptionsoft.games.trivia.question.QuestionCatalog;

public class TriviaGameEngine {

	private final QuestionCatalog questionCatalog;
	private final PlayersManager playersManager;

	int[] places = new int[6];
	int[] purses = new int[6];
	boolean[] inPenaltyBox = new boolean[6];

	int currentPlayerTurn = 0;
	boolean isGettingOutOfPenaltyBox;

	public TriviaGameEngine(QuestionCatalog questionCatalog, PlayersManager playersManager) {
		this.questionCatalog = questionCatalog;
		this.playersManager = playersManager;
	}

	public boolean addPlayer(String playerName) {
		playersManager.addPlayer(playerName);

		int nextIndex = playersManager.getPlayersCount();
		places[nextIndex] = 0;
		purses[nextIndex] = 0;
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
				places[currentPlayerTurn] = places[currentPlayerTurn] + roll;
				if (places[currentPlayerTurn] > 11)
					places[currentPlayerTurn] = places[currentPlayerTurn] - 12;

				System.out.println(currentPlayer + "'s new location is " + places[currentPlayerTurn]);
				System.out.println("The category is " + currentCategory());
				askQuestion();
			} else {
				System.out.println(currentPlayer + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
			}
		} else {
			places[currentPlayerTurn] = places[currentPlayerTurn] + roll;
			if (places[currentPlayerTurn] > 11)
				places[currentPlayerTurn] = places[currentPlayerTurn] - 12;

			System.out.println(currentPlayer + "'s new location is " + places[currentPlayerTurn]);
			System.out.println("The category is " + currentCategory());
			askQuestion();
		}

	}

	private void askQuestion() {
		Question question = questionCatalog.getNextQuestion();
		System.out.println(question);
	}

	private String currentCategory() {
		if (places[currentPlayerTurn] == 0)
			return "Pop";
		if (places[currentPlayerTurn] == 4)
			return "Pop";
		if (places[currentPlayerTurn] == 8)
			return "Pop";
		if (places[currentPlayerTurn] == 1)
			return "Science";
		if (places[currentPlayerTurn] == 5)
			return "Science";
		if (places[currentPlayerTurn] == 9)
			return "Science";
		if (places[currentPlayerTurn] == 2)
			return "Sports";
		if (places[currentPlayerTurn] == 6)
			return "Sports";
		if (places[currentPlayerTurn] == 10)
			return "Sports";
		return "Rock";
	}

	public boolean wasCorrectlyAnswered() {
		Player currentPlayer = playersManager.getCurrentPlayer();
		int playersCount = playersManager.getPlayersCount();

		if (inPenaltyBox[currentPlayerTurn]) {
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				purses[currentPlayerTurn]++;
				System.out.println(currentPlayer + " now has " + purses[currentPlayerTurn] + " Gold Coins.");

				boolean winner = didPlayerWin();
				currentPlayerTurn++;
				if (currentPlayerTurn == playersCount) {
					currentPlayerTurn = 0;
				}
				return winner;
			} else {
				currentPlayerTurn++;
				if (currentPlayerTurn == playersCount) {
					currentPlayerTurn = 0;
				}
				return true;
			}
		} else {
			System.out.println("Answer was corrent!!!!");
			purses[currentPlayerTurn]++;
			System.out.println(currentPlayer + " now has " + purses[currentPlayerTurn] + " Gold Coins.");

			boolean winner = didPlayerWin();
			currentPlayerTurn++;
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
		if (currentPlayerTurn == playersManager.getPlayersCount()) {
			currentPlayerTurn = 0;
		}

		return true;
	}

	private boolean didPlayerWin() {
		return !(purses[currentPlayerTurn] == 6);
	}
}
