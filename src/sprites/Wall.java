package sprites;

public class Wall extends Sprite {
	/**
	 * Create the constructor for Wall
	 * @param symbol (required) specifies the character
	 * @param row (required) specifies the row number
	 * @param column (required) specifies the column number
	 */
	public Wall(char symbol, int row, int column){
		/**
		 * calls the constructor in the Sprite class
		 */
		super(symbol, row, column);
	}
}
