package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.dice.Dice;
import com.adaptionsoft.games.trivia.board.PenaltyBox;
import com.adaptionsoft.games.trivia.board.ScoreBoard;
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
	private final PenaltyBox penaltyBox;
	private final ScoreBoard scoreBoard;
	private final Dice dice;

	public TriviaGameEngine(
		QuestionCatalog questionCatalog,
		PlayersManager playersManager,
		TriviaBoard triviaBoard,
		PenaltyBox penaltyBox,
		ScoreBoard scoreBoard,
		Dice dice
	) {
		this.questionCatalog = questionCatalog;
		this.playersManager = playersManager;
		this.triviaBoard = triviaBoard;
		this.penaltyBox = penaltyBox;
		this.scoreBoard = scoreBoard;
		this.dice = dice;
	}

	public void runTurn() {
		int roll = dice.roll();
		Player currentPlayer = playersManager.getCurrentPlayer();
		System.out.println(currentPlayer + " is the current player");
		System.out.println("They have rolled a " + roll);
		checkForPenalty(roll, currentPlayer);
		triviaBoard.movePlayer(currentPlayer, roll);
		int newPlayerLocation = triviaBoard.getPlayerLocation(currentPlayer);
		System.out.println(currentPlayer + "'s new location is " + newPlayerLocation);
		QuestionCategory questionCategory = triviaBoard.getQuestionCategoryAt(newPlayerLocation);
		System.out.println("The category is " + questionCategory);
		askQuestion(questionCategory);
	}

	private void checkForPenalty(int roll, Player currentPlayer) {
		if (!penaltyBox.hasPenalty(currentPlayer)) {
			return;
		}
		if (shouldGetOutOfPenaltyBox(roll)) {
			System.out.println(currentPlayer + " is getting out of the penalty box");
			penaltyBox.remove(currentPlayer);
		} else {
			System.out.println(currentPlayer + " is not getting out of the penalty box");
		}
	}

	private boolean shouldGetOutOfPenaltyBox(int roll) {
		return roll % 2 == 1;
	}

	private void askQuestion(QuestionCategory questionCategory) {
		Question question = questionCatalog.getNextQuestion(questionCategory);
		System.out.println(question);
	}

	public boolean onCorrectAnswer() {
		Player currentPlayer = playersManager.getCurrentPlayer();
		if (penaltyBox.hasPenalty(currentPlayer)) {
			playersManager.moveToNextPlayer();
			return true;
		}
		System.out.println("Answer was correct!!!!");
		scoreBoard.acquireGoldCoin(currentPlayer);
		int score = scoreBoard.getScore(currentPlayer);
		System.out.println(currentPlayer + " now has " + score + " Gold Coins.");
		playersManager.moveToNextPlayer();
		return didPlayerWin();
	}

	public void onWrongAnswer() {
		Player currentPlayer = playersManager.getCurrentPlayer();
		System.out.println("Question was incorrectly answered");
		System.out.println(currentPlayer + " was sent to the penalty box");
		penaltyBox.add(currentPlayer);
		playersManager.moveToNextPlayer();
	}

	private boolean didPlayerWin() {
		return scoreBoard.isWinner(playersManager.getCurrentPlayer());
	}
}
