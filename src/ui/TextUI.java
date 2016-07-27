package ui;

import game.VacuumGame;
import java.util.Scanner;

/**
 * This Class is used to play the game in console. It
 * over-writes the launchGame and displayWinner methods to
 * be able to do this.
 * @author c5rathir
 *
 */
public class TextUI implements UI {
	private VacuumGame game;

	/**
	 * The constructor for when an object of TextUI is called
	 * @param game
	 */
	public TextUI(VacuumGame game) {
		this.game = game;
	}

	/**
	 * Overrides the launchGame. Introduces a Scanner variable
	 * that reads the key entered and calls the game.move method with
	 * the corresponding key. This keeps happening until the game is over
	 */
	@Override
	public void launchGame() {
		// TODO Auto-generated method stub
		Scanner keyboard = new Scanner(System.in);
		while (!game.gameOver()) {
			System.out.println(this.game.getGrid().toString());
			String input = keyboard.next();
			this.game.move(input.charAt(0));
		}
		keyboard.close();
	}

	/**
	 * Overrides the displayWinner method. Calls the game.getWinner method
	 * which determines if the winner is player 1, player 2 or if the game is
	 * tied. It then prints the appropriate method with the score of the 
	 * winning player. 
	 */
	@Override
	public void displayWinner() {
		// TODO Auto-generated method stub
		
		// variable to hold the string
		String returnMessage;
		
		// checks if the winner is player 1
		if (this.game.getWinner() == 1)
			returnMessage = "Congratulations Player 1! You have won the "
					+ "game" + " with a score of "
					+ this.game.getVacuumOne().getScore() + ".";
		
		// checks if the winner is player 2
		else if (this.game.getWinner() == 2)
			returnMessage = "Congratulations Player 2! You have won the "
					+ "game" + " with a score of "
					+ this.game.getVacuumTwo().getScore() + ".";
		
		// by default if neither player 1 or player 2 win, the game is tied
		else
			returnMessage = "The game is tied. Both of you got a score of "
			+this.game.getVacuumTwo().getScore()+".";
		
		// print out the message
		System.out.println(returnMessage);
	}

}
