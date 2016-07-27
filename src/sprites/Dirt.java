package sprites;

/**
 * Class that is used to represent any dirt objects
 * @author c5rathir
 *
 */
public class Dirt extends Sprite {
	
	//creates a protected variable called value
	protected int value;
	/**
	 * Create the constructor for Dirt
	 * @param symbol (required) specifies the character
	 * @param row (required) specifies the row numbers
	 * @param column (required) specifies the column numbers
	 * @param value (required) specifies the value
	 */
	public Dirt(char symbol, int row, int column, int value){
		/**
		 * calls the constructor from Sprite class
		 */
		super(symbol, row, column);
		
		//assigns the protected variable with the amount that is passed in by
		// the value parameter
		this.value = value;
	}
	/**
	 * creates the getter to obtain value
	 * @return value
	 */
	public int getValue(){
		return value;
	}

}
