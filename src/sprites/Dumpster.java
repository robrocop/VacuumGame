package sprites;

/**
 *Class that is used to represent any dumpster objects
 * @author c5rathir
 *
 */
public class Dumpster extends Sprite {
	/**
	 * Create the constructor for Dumpster
	 * @param symbol (required) specifies the character
	 * @param row (required) specifies the row number
	 * @param column (required) specifies the column number
	 */
	public Dumpster(char symbol, int row, int column){
		/**
		 * call the construtor from the abstract Sprite class
		 */
		super(symbol, row, column);
	}

}
