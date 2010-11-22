/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gomoku;

/**
 *
 * @author Pedro Coelho
 */
public class Table {

	private int width, height;
	private int store[][];

	Table(int width, int height) {

		this.width = width;
		this.height = height;

		store = new int[width][height];

		for (int i = 0; i < width; i++) {
			for (int z = 0; z < height; z++) {
				store[i][z] = 0;
			}
		}
	}

	public int makeMove(int player, int x, int y) {
		try {
			if (store[x][y] != 0) {
				return 0;
			} else {
				store[x][y] = player;
				return 1;
			}
		} catch (Exception e) {
			return 0;
		}

	}

	//could improve a lot by using last position, currently a bit lazy to do that
	public int checkWin() {
		int playerOneCount = 0, playerTwoCount = 0, i, z, h, l;

		// Check North to South
		for (z = 0; z < width; z++) {
			playerOneCount = 0;
			playerTwoCount = 0;
			for (i = 0; i < height; i++) {
				if (store[z][i] == 0) {
					playerOneCount = 0;
					playerTwoCount = 0;
				} else {
					if (store[z][i] == 1) {
						playerOneCount += 1;
						playerTwoCount = 0;
					} else {
						if (store[z][i] == 2) {
							playerOneCount = 0;
							playerTwoCount += 1;
						}
					}
				}
				if (playerOneCount == 5) {
					System.out.println("N to S");
					return 1;
				} else {
					if (playerTwoCount == 5) {
						System.out.println("N to S");
						return 2;
					}
				}
			}
		}

		// Check West to East
		for (z = 0; z < width; z++) {
			playerOneCount = 0;
			playerTwoCount = 0;
			for (i = 0; i < height; i++) {
				if (store[i][z] == 0) {
					playerOneCount = 0;
					playerTwoCount = 0;
				} else {
					if (store[i][z] == 1) {
						playerOneCount += 1;
						playerTwoCount = 0;
					} else {
						if (store[i][z] == 2) {
							playerOneCount = 0;
							playerTwoCount += 1;
						}
					}
				}
				if (playerOneCount == 5) {
					System.out.println("W to E");
					return 1;
				} else {
					if (playerTwoCount == 5) {
						System.out.println("W to E");
						return 2;
					}
				}
			}
		}

		// Check NW to SE
		for (h = 0; h < height - 5; h++) {
			for (l = 0; l < width - 5; l++) {
				playerOneCount = 0;
				playerTwoCount = 0;
				for (z = h, i = l; z <= h + 5 && i <= l + 5; i++, z++) {
					if (store[z][i] == 0) {
						playerOneCount = 0;
						playerTwoCount = 0;
					} else if (store[z][i] == 1) {
						playerOneCount++;
						playerTwoCount = 0;
					} else if (store[z][i] == 2) {
						playerOneCount = 0;
						playerTwoCount++;
					}
					if (playerOneCount == 5) {
						System.out.println("NW to SE");
						return 1;
					}
					if (playerTwoCount == 5) {
						System.out.println("NW to SE");
						return 2;
					}
				}
			}
		}

		// Check SW to NE
		for (h = 0; h < height - 5; h++) {
			for (l = 0; l < width - 5; l++) {
				playerOneCount = 0;
				playerTwoCount = 0;
				for (z = h, i = l + 5; z <= h + 5 && i >= l - 5; i--, z++) {
					if (store[z][i] == 0) {
						playerOneCount = 0;
						playerTwoCount = 0;
					} else if (store[z][i] == 1) {
						playerOneCount++;
						playerTwoCount = 0;
					} else if (store[z][i] == 2) {
						playerOneCount = 0;
						playerTwoCount++;
					}
					if (playerOneCount == 5) {
						System.out.println("SW to NE");
						return 1;
					}
					if (playerTwoCount == 5) {
						System.out.println("SW to NE");
						return 2;
					}
				}
			}
		}
		return 0;
	}

	public int[][] getStore() {
		return store;
	}

	public void printTable() {
		int i, z;

		// First Line

		for (i = 0; i < width + 2; i++) {
			if (i > 0 && i < 20)
				System.out.printf(" %d ", (i - 1) % 10);
			else
				System.out.printf(" # ");
		}
		System.out.printf("\n");

		for (i = 0; i < height; i++) {
			for (z = 0; z < width; z++) {
				if (z == 0) {
					System.out.printf(" %d ", i % 10);
				}
				if (store[z][i] == 0) {
					System.out.printf(" _ ");
				} else {
					System.out.printf(" %d ", store[z][i]);
				}

				if (z == width - 1) {
					System.out.printf(" %d\n", i % 10);
				}
			}
		}

		for (i = 0; i < width + 2; i++) {
			if (i > 0 && i < 20)
				System.out.printf(" %d ", (i - 1) % 10);
			else
				System.out.printf(" # ");
		}
		System.out.printf("\n");

	}
}