package sprites;

/**
 *Class that is used to represent any dustball objects
 * @author c5rathir
 *
 */
public class DustBall extends Dirt implements Moveable {
	/**
	 * Create the constructor for DustBall
	 * @param symbol (required) specifies the character
	 * @param row (required) specifies the row numbers
	 * @param col (required) specifies the column numbers
	 * @param value (required) specifies the value
	 */
	public DustBall(char symbol, int row, int col, int value){
		/**
		 * call the constructor from Dirt
		 */
		super(symbol, row, col, value);
	}
	
	public void moveTo(int row, int column){
		// assign the new row and column values to Dustball
		this.row = row;
		this.column = column;
		
	
		}
		
	}


