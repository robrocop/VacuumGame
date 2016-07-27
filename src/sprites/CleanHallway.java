package sprites;

/**
 * Class for the CleanHallway objects.
 * @author c5rathir
 *
 */
public class CleanHallway extends Sprite {
	/**
	 * Create the constructor for CleanHallway
	 * @param symbol (required) specifies the character
	 * @param row (required) specifies the row numbers
	 * @param column (required) specifies the column numbers
	 */
	public CleanHallway (char symbol, int row, int column){
		/**
		 * calls the constructor in the Sprite class
		 */
		super(symbol, row, column);
	}
}
