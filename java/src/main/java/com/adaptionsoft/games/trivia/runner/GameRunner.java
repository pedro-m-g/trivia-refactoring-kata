
package com.adaptionsoft.games.trivia.runner;

import java.util.Random;

import com.adaptionsoft.games.uglytrivia.TriviaGame;

public class GameRunner {

	private final static int NUMBER_OF_QUESTIONS = 50;

	public static void main(String[] args) {
		TriviaGame triviaGame = new TriviaGame(NUMBER_OF_QUESTIONS);

		triviaGame.addPlayer("Chet");
		triviaGame.addPlayer("Pat");
		triviaGame.addPlayer("Sue");

		Random rand = new Random();
		boolean notAWinner;
		do {
			triviaGame.runTurn(rand.nextInt(5) + 1);
			if (rand.nextInt(9) == 7) {
				notAWinner = triviaGame.wrongAnswer();
			} else {
				notAWinner = triviaGame.wasCorrectlyAnswered();
			}
		} while (notAWinner);
	}
}
