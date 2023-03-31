
package com.adaptionsoft.games.trivia;

import java.util.Random;

public class TriviaGame {

	private final static int NUMBER_OF_QUESTIONS = 50;

	public static void main(String[] args) {
		TriviaGameEngine triviaGameEngine = new TriviaGameEngine(NUMBER_OF_QUESTIONS);

		triviaGameEngine.addPlayer("Chet");
		triviaGameEngine.addPlayer("Pat");
		triviaGameEngine.addPlayer("Sue");

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
