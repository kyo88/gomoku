/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gomoku;

import java.util.Scanner;

/**
 *
 * @author Pedro Coelho
 */
public class Main {

	public enum PLAYER {
		HUMAN, SEMI_RANDOM_AI, EVAL_AI
	}

	private static void printOptions() {
		System.out.println("1 (Default) -> Human");
		System.out.println("2 -> Semi Random AI");
		System.out.println("3 -> Evaluation AI");
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {

		Scanner myScan = new Scanner(System.in);
		PLAYER p1 = PLAYER.HUMAN;
		PLAYER p2 = PLAYER.HUMAN;
		int option;

		System.out.printf("Welcome to Gomoku\n\n\n");
		System.out.printf("Choose first player:\n\n");
		printOptions();
		option = myScan.nextInt();
		if (option == 2)
			p1 = PLAYER.SEMI_RANDOM_AI;
		else if (option == 3)
			p1 = PLAYER.EVAL_AI;

		System.out.printf("Choose second player:\n\n");
		printOptions();
		option = myScan.nextInt();
		if (option == 2)
			p2 = PLAYER.SEMI_RANDOM_AI;
		else if (option == 3)
			p2 = PLAYER.EVAL_AI;

		playGame(p1, p2);
		System.out.printf("\n\nGoodbye!\n\n");
	}

	private static void playGame(PLAYER p1, PLAYER p2) {
		int width = 19;
		int height = 19;
		Table gameTable = new Table(width, height);
		int win = 0;
		int validMove = 0;
		int[] move = new int[2];

		Player player1 = new Player(1);
		Player player2 = new Player(2);

		// playerOne
		if (p1 == PLAYER.SEMI_RANDOM_AI) {
			player1.setID(PLAYER.SEMI_RANDOM_AI);
		} else if (p1 == PLAYER.HUMAN) {
			player1.setID(PLAYER.HUMAN);
		} else if (p1 == PLAYER.EVAL_AI) {
			player1.setID(PLAYER.EVAL_AI);
		}

		// playerTwo
		if (p2 == PLAYER.SEMI_RANDOM_AI) {
			player2.setID(PLAYER.SEMI_RANDOM_AI);
		} else if (p2 == PLAYER.HUMAN) {
			player2.setID(PLAYER.HUMAN);
		} else if (p2 == PLAYER.EVAL_AI) {
			player2.setID(PLAYER.EVAL_AI);
		}

		while (true) {
			// p1 move
			gameTable.printTable();
			while (validMove != 1) {
				move = player1.getMove(gameTable.getStore());
				validMove = gameTable.makeMove(1, move[0], move[1]);
			}
			validMove = 0;

			// check win
			win = gameTable.checkWin();
			if (win == 1) {
				gameTable.printTable();
				System.out.println("Player one Wins");
				break;
			}

			// p2 move
			gameTable.printTable();
			while (validMove != 1) {
				move = player2.getMove(gameTable.getStore());
				validMove = gameTable.makeMove(2, move[0], move[1]);
			}
			validMove = 0;

			// check win
			win = gameTable.checkWin();
			if (win == 2) {
				gameTable.printTable();
				System.out.println("Player two Wins");
				break;
			}
		}

	}
}