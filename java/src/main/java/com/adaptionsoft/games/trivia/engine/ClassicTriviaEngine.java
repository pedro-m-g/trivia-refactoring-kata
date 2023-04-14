package com.adaptionsoft.games.trivia.engine;

import com.adaptionsoft.games.trivia.board.PenaltyBox;
import com.adaptionsoft.games.trivia.board.ScoreBoard;
import com.adaptionsoft.games.trivia.board.TriviaBoard;
import com.adaptionsoft.games.trivia.dice.Dice;
import com.adaptionsoft.games.trivia.player.Player;
import com.adaptionsoft.games.trivia.player.PlayersManager;
import com.adaptionsoft.games.trivia.question.Question;
import com.adaptionsoft.games.trivia.question.QuestionCatalog;
import com.adaptionsoft.games.trivia.question.QuestionCategory;
import com.adaptionsoft.games.trivia.ui.TriviaUI;

public class ClassicTriviaEngine implements TriviaEngine {

	private final QuestionCatalog questionCatalog;
	private final PlayersManager playersManager;
	private final TriviaBoard triviaBoard;
	private final PenaltyBox penaltyBox;
	private final ScoreBoard scoreBoard;
	private final Dice dice;
	private final TriviaUI triviaUI;

	public ClassicTriviaEngine(
			QuestionCatalog questionCatalog,
			PlayersManager playersManager,
			TriviaBoard triviaBoard,
			PenaltyBox penaltyBox,
			ScoreBoard scoreBoard,
			Dice dice,
			TriviaUI triviaUI) {
		this.questionCatalog = questionCatalog;
		this.playersManager = playersManager;
		this.triviaBoard = triviaBoard;
		this.penaltyBox = penaltyBox;
		this.scoreBoard = scoreBoard;
		this.dice = dice;
		this.triviaUI = triviaUI;
	}

	@Override
	public void doTurn() {
		int roll = dice.roll();
		Player currentPlayer = playersManager.getCurrentPlayer();
		triviaUI.showCurrentPlayer(currentPlayer);
		triviaUI.showDiceRoll(roll);

		checkForPenalty(roll, currentPlayer);

		triviaBoard.movePlayer(currentPlayer, roll);
		int newPlayerLocation = triviaBoard.getPlayerLocation(currentPlayer);
		triviaUI.onPlayerMove(currentPlayer, roll, newPlayerLocation);

		QuestionCategory questionCategory = triviaBoard.getQuestionCategoryAt(newPlayerLocation);
		Question question = questionCatalog.getNextQuestion(questionCategory);
		String answer = triviaUI.promptQuestion(question);

		if (question.isCorrect(answer)) {
			onCorrectAnswer();
		} else {
			onWrongAnswer();
		}
	}

	@Override
	public void onCorrectAnswer() {
		Player currentPlayer = playersManager.getCurrentPlayer();
		if (penaltyBox.hasPenalty(currentPlayer)) {
			playersManager.moveToNextPlayer();
			triviaUI.onPenaltyBox(currentPlayer);
			triviaUI.moveToPlayer(currentPlayer);
			return;
		}
		triviaUI.onCorrectAnswer(currentPlayer);

		scoreBoard.acquireGoldCoin(currentPlayer);
		int score = scoreBoard.getScore(currentPlayer);
		triviaUI.onScoreUpdate(score);

		playersManager.moveToNextPlayer();
		triviaUI.moveToPlayer(currentPlayer);
	}

	@Override
	public void onWrongAnswer() {
		Player currentPlayer = playersManager.getCurrentPlayer();
		triviaUI.onWrongAnswer(currentPlayer);

		penaltyBox.add(currentPlayer);
		triviaUI.onPenaltyBox(currentPlayer);

		playersManager.moveToNextPlayer();
		triviaUI.moveToPlayer(currentPlayer);
	}

	@Override
	public boolean currentPlayerWon() {
		return scoreBoard.isWinner(playersManager.getCurrentPlayer());
	}

	private void checkForPenalty(int roll, Player currentPlayer) {
		if (!penaltyBox.hasPenalty(currentPlayer)) {
			return;
		}
		if (shouldGetOutOfPenaltyBox(roll)) {
			penaltyBox.remove(currentPlayer);
			triviaUI.onOutOfPenaltyBox(currentPlayer);
		} else {
			triviaUI.onPenaltyBox(currentPlayer);
		}
	}

	private boolean shouldGetOutOfPenaltyBox(int roll) {
		return roll % 2 == 1;
	}

}
