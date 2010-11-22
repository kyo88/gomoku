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
public class Player {

	private Main.PLAYER id;
	private int pNumber;

	public Player (int pNumber)
	{
		this.pNumber = pNumber;
	}

	public void setID(Main.PLAYER id)
	{
		this.id = id;
	}

	public int [] getMove(int store[][]){
		int[] playMove = new int[2];

		if (id == Main.PLAYER.HUMAN)
			playMove = humanMove(store,pNumber);
		else if (id == Main.PLAYER.SEMI_RANDOM_AI)
			playMove = semiRandomAI(store);
		else if (id == Main.PLAYER.EVAL_AI)
			playMove = minMaxAI(store, 1, pNumber);

		return playMove;
	}

	public int[] humanMove(int store[][],int pNumber)
	{
		int[] playMove = new int[2];
		Scanner myScan = new Scanner(System.in);

		System.out.printf(
				"(Player %d)  Choose your move (0 to %d)\n",pNumber,
				18);
		System.out.printf("x: ");
		playMove[0] = myScan.nextInt();
		System.out.printf("y: ");
		playMove[1] = myScan.nextInt();
		return playMove;
	}

    //only the evaluation function is currently working
    public int[] minMaxAI(int store[][], int maxDepth, int ai) {
        int[] playMove = new int[2];
        playMove = evaluateMove(store, ai);
        return playMove;
    }

    public int[] evaluateMove(int store[][], int ai) {
        int[] bestMove = new int[3];
        int score = 0, temp = 0;
        int[] position = new int[2];
        for (int w = 0; w < 19; w++) {
            for (int h = 0; h < 19; h++) {
                temp = scoreEvaluation(store, w, h, 2);
                if (temp >= score) {
                    score = temp;
                    position[0] = w;
                    position[1] = h;
                }
            }
        }
        bestMove[0] = position[0];
        bestMove[1] = position[1];
        return bestMove;
    }

    public int scoreEvaluation(int store[][], int x, int y, int ai) {
        int valueS = 0, valueN = 0, valueNE = 0, valueSE = 0, valueNW = 0, valueSW = 0, valueE = 0, valueW = 0;

        int[] scoreOwn = {2, 4, 6, 100};
        int[] scoreOther = {1, 3, 7, 51};

        int player;
        if (ai == 1) player =2;
        else player =1;

        if (store[x][y] == 0) {
            try {
                //Check Middle to North
                if (store[x][y - 1] == player) {
                    valueN = scoreOther[0];
                    if (store[x][y - 2] == player) {
                        valueN = scoreOther[1];
                        if (store[x][y - 3] == player) {
                            valueN = scoreOther[2];
                            if (store[x][y - 4] == player) {
                                valueN = scoreOther[3];
                            }
                        }
                    }
                } else if (store[x][y - 1] == ai) {
                    valueN = scoreOwn[0];
                    if (store[x][y - 2] == ai) {
                        valueN = scoreOwn[1];
                        if (store[x][y - 3] == ai) {
                            valueN = scoreOwn[2];
                            if (store[x][y - 4] == ai) {
                                valueN = scoreOwn[3];
                            }
                        }
                    }
                }
            } catch (Exception e) {
            }

            //Check Middle to South
            try {
                if (store[x][y + 1] == player) {
                    valueS = scoreOther[0];
                    if (store[x][y + 2] == player) {
                        valueS = scoreOther[1];
                        if (store[x][y + 3] == player) {
                            valueS = scoreOther[2];
                            if (store[x][y + 3] == player) {
                                valueS = scoreOther[3];
                            }
                        }
                    }
                } else if (store[x][y + 1] == ai) {
                    valueS = scoreOwn[0];
                    if (store[x][y + 2] == ai) {
                        valueS = scoreOwn[1];
                        if (store[x][y + 3] == ai) {
                            valueS = scoreOwn[2];
                            if (store[x][y + 4] == ai) {
                                valueS = scoreOwn[3];
                            }
                        }
                    }
                }
            } catch (Exception e) {
            }



            try {
                //Check Middle to West
                if (store[x - 1][y] == player) {
                    valueW = scoreOther[0];
                    if (store[x - 2][y] == player) {
                        valueW = scoreOther[1];
                        if (store[x - 3][y] == player) {
                            valueW = scoreOther[2];
                            if (store[x - 4][y] == player) {
                                valueW = scoreOther[3];
                            }
                        }
                    }
                } else if (store[x - 1][y] == ai) {
                    valueW = scoreOwn[0];
                    if (store[x - 2][y] == ai) {
                        valueW = scoreOwn[1];
                        if (store[x - 3][y] == ai) {
                            valueW = scoreOwn[2];
                            if (store[x - 4][y] == ai) {
                                valueW = scoreOwn[3];
                            }
                        }
                    }
                }
            } catch (Exception e) {
            }

            //Check Middle to East
            try {
                if (store[x + 1][y] == player) {
                    valueE = scoreOther[0];
                    if (store[x + 2][y] == player) {
                        valueE = scoreOther[1];
                        if (store[x + 3][y] == player) {
                            valueE = scoreOther[2];
                            if (store[x + 4][y] == player) {
                                valueE = scoreOther[3];
                            }
                        }
                    }
                } else if (store[x + 1][y] == ai) {
                    valueE = scoreOwn[0];
                    if (store[x + 2][y] == ai) {
                        valueE = scoreOwn[1];
                        if (store[x + 3][y] == ai) {
                            valueE = scoreOwn[2];
                            if (store[x + 4][y] == ai) {
                                valueE = scoreOwn[3];
                            }
                        }
                    }
                }
            } catch (Exception e) {
            }

            //Check Middle to NW
            try {
                if (store[x - 1][y - 1] == player) {
                    valueNW = scoreOther[0];
                    if (store[x - 2][y - 2] == player) {
                        valueNW = scoreOther[1];
                        if (store[x - 3][y - 3] == player) {
                            valueNW = scoreOther[2];
                            if (store[x - 4][y - 4] == player) {
                                valueNW = scoreOther[3];
                            }
                        }
                    }
                } else if (store[x - 1][y - 1] == ai) {
                    valueNW = scoreOwn[0];
                    if (store[x - 2][y - 2] == ai) {
                        valueNW = scoreOwn[1];
                        if (store[x - 3][y - 3] == ai) {
                            valueNW = scoreOwn[2];
                            if (store[x - 4][y - 4] == ai) {
                                valueNW = scoreOwn[3];
                            }
                        }
                    }
                }
            } catch (Exception e) {
            }

            //Check Middle to SE
            try {
                if (store[x + 1][y + 1] == player) {
                    valueSE = scoreOther[0];
                    if (store[x + 2][y + 2] == player) {
                        valueSE = scoreOther[1];
                        if (store[x + 3][y + 3] == player) {
                            valueSE = scoreOther[2];
                            if (store[x + 4][y + 4] == player) {
                                valueSE = scoreOther[3];
                            }
                        }
                    }
                } else if (store[x + 1][y + 1] == ai) {
                    valueSE = scoreOwn[0];
                    if (store[x + 2][y + 2] == ai) {
                        valueSE = scoreOwn[1];
                        if (store[x + 3][y + 3] == ai) {
                            valueSE = scoreOwn[2];
                            if (store[x + 4][y + 4] == ai) {
                                valueSE = scoreOwn[3];
                            }
                        }
                    }

                }
            } catch (Exception e) {
            }

            //Check Middle to NE
            try {
                if (store[x + 1][y - 1] == player) {
                    valueNE = scoreOther[0];
                    if (store[x + 2][y - 2] == player) {
                        valueNE = scoreOther[1];
                        if (store[x + 3][y - 3] == player) {
                            valueNE = scoreOther[2];
                            if (store[x + 4][y - 4] == player) {
                                valueNE = scoreOther[3];
                            }
                        }
                    }
                } else if (store[x + 1][y - 1] == ai) {
                    valueNE = scoreOwn[0];
                    if (store[x + 2][y - 2] == ai) {
                        valueNE = scoreOwn[1];
                        if (store[x + 3][y - 3] == ai) {
                            valueNE = scoreOwn[2];
                            if (store[x + 4][y - 4] == ai) {
                                valueNE = scoreOwn[3];
                            }
                        }
                    }
                }
            } catch (Exception e) {
            }

            //Check Middle to SW
            try {
                if (store[x - 1][y + 1] == player) {
                    valueSW = scoreOther[0];
                    if (store[x - 2][y + 2] == player) {
                        valueSW = scoreOther[1];
                        if (store[x - 3][y + 3] == player) {
                            valueSW = scoreOther[2];
                            if (store[x - 4][y + 4] == player) {
                                valueSW = scoreOther[3];
                            }
                        }
                    }
                } else if (store[x - 1][y - 1] == ai) {
                    valueSW = scoreOwn[0];
                    if (store[x - 2][y + 2] == ai) {
                        valueSW = scoreOwn[1];
                        if (store[x - 3][y + 3] == ai) {
                            valueSW = scoreOwn[2];
                            if (store[x - 4][y + 4] == ai) {
                                valueSW = scoreOwn[3];
                            }
                        }
                    }
                }
            } catch (Exception e) {
            }
        }

        int s = Math.max(valueS, Math.max(valueN, Math.max(valueSE, Math.max(valueSW,
                Math.max(valueNE, Math.max(valueNW, Math.max(valueE, valueW)))))));
        return s;
    }

    // so so AI
    public int[] semiRandomAI(int store[][]) {
        int[] playMove = new int[2];
        for (int w = 0; w <= 18; w++) {
            for (int h = 0; h <= 18; h++) {
                if (store[w][h] == 1) {
                    if (w != 0 && h != 0 && w != 18 && h != 18) {
                        if (store[w - 1][h - 1] == 0) {
                            playMove[0] = w - 1;
                            playMove[1] = h - 1;
                            return playMove;
                        }
                        if (store[w + 1][h + 1] == 0) {
                            playMove[0] = w + 1;
                            playMove[1] = h + 1;
                            return playMove;
                        }
                        if (store[w + 1][h - 1] == 0) {
                            playMove[0] = w + 1;
                            playMove[1] = h - 1;
                        }
                        if (store[w - 1][h + 1] == 0) {
                            playMove[0] = w - 1;
                            playMove[1] = h + 1;
                        }
                        if (store[w][h - 1] == 0) {
                            playMove[0] = w;
                            playMove[1] = h - 1;
                            return playMove;
                        }
                        if (store[w][h + 1] == 0) {
                            playMove[0] = w;
                            playMove[1] = h + 1;
                            return playMove;
                        }
                        if (store[w - 1][h] == 0) {
                            playMove[0] = w - 1;
                            playMove[1] = h;
                            return playMove;
                        }
                        if (store[w + 1][h] == 0) {
                            playMove[0] = w + 1;
                            playMove[1] = h;
                            return playMove;
                        }
                    }
                }
            }
        }

        //Try to play in the middle
        playMove[0] = 6 + (int) (Math.random() * 5);
        playMove[1] = 6 + (int) (Math.random() * 5);
        return playMove;
    }
}