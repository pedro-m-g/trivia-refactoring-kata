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

	boolean isGettingOutOfPenaltyBox;

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
		if (penaltyBox.hasPenalty(currentPlayer)) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				System.out.println(currentPlayer + " is getting out of the penalty box");
				triviaBoard.movePlayer(currentPlayer, roll);
				int newPlayerLocation = triviaBoard.getPlayerLocation(currentPlayer);
				System.out.println(currentPlayer + "'s new location is " + newPlayerLocation);
				QuestionCategory questionCategory = triviaBoard.getQuestionCategoryAt(newPlayerLocation);
				System.out.println("The category is " + questionCategory);
				askQuestion(questionCategory);
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
			askQuestion(questionCategory);
		}
	}

	private void askQuestion(QuestionCategory questionCategory) {
		Question question = questionCatalog.getNextQuestion(questionCategory);
		System.out.println(question);
	}

	public boolean wasCorrectlyAnswered() {
		Player currentPlayer = playersManager.getCurrentPlayer();
		if (penaltyBox.hasPenalty(currentPlayer)) {
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				scoreBoard.acquireGoldCoin(currentPlayer);
				penaltyBox.remove(currentPlayer);
				int score = scoreBoard.getScore(currentPlayer);
				System.out.println(currentPlayer + " now has " + score + " Gold Coins.");
				boolean winner = didPlayerWin();
				playersManager.moveToNextPlayer();
				return winner;
			} else {
				playersManager.moveToNextPlayer();
				return true;
			}
		} else {
			System.out.println("Answer was correct!!!!");
			scoreBoard.acquireGoldCoin(currentPlayer);
			int score = scoreBoard.getScore(currentPlayer);
			System.out.println(currentPlayer + " now has " + score + " Gold Coins.");

			boolean winner = didPlayerWin();
			playersManager.moveToNextPlayer();
			return winner;
		}
	}

	public boolean wrongAnswer() {
		Player currentPlayer = playersManager.getCurrentPlayer();
		System.out.println("Question was incorrectly answered");
		System.out.println(currentPlayer + " was sent to the penalty box");
		penaltyBox.add(currentPlayer);
		playersManager.moveToNextPlayer();
		return true;
	}

	private boolean didPlayerWin() {
		return !scoreBoard.isWinner(playersManager.getCurrentPlayer());
	}
}
