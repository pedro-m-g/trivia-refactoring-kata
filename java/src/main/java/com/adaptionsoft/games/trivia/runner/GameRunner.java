
package com.adaptionsoft.games.trivia.runner;

import java.util.Random;

import com.adaptionsoft.games.uglytrivia.TriviaGame;

public class GameRunner {

	private static boolean notAWinner;

	public static void main(String[] args) {
		TriviaGame triviaGame = new TriviaGame();

		triviaGame.add("Chet");
		triviaGame.add("Pat");
		triviaGame.add("Sue");

		Random rand = new Random();

		do {

			triviaGame.roll(rand.nextInt(5) + 1);

			if (rand.nextInt(9) == 7) {
				notAWinner = triviaGame.wrongAnswer();
			} else {
				notAWinner = triviaGame.wasCorrectlyAnswered();
			}

		} while (notAWinner);

	}
}
