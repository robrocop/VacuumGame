package sprites;

/**
 * General class that is used to represent any Sprite objects that will be
 * present on the grid.
 * @author c5rathir
 *
 */
public abstract class Sprite {
	
	/** holds the Sprite's symbol */
	protected char symbol;
	/** holds the Sprite's row location */
	protected int row;
	/** holds the Sprite's column location */
	protected int column;
	
	/**
	 * Constructor
	 * 
	 * @param s (required) is the character that will represent symbol.
	 * @param r (required) is the number of rows.
	 * @param c (required) specifies the number of columns.
	 */
	public Sprite(char symbol, int row, int column){
		this.symbol = symbol;
		this.row = row;
		this.column = column;
	}
	/**
	 * a getter to get the symbol value
	 * @return symbol
	 */
	public char getSymbol(){
		return symbol;
	}
	
	/**
	 * a getter to get the row value
	 * @return row
	 */
	public int getRow(){
		return row;
	}
	
	/**
	 * a getter to get the column value
	 * @return column
	 */
	public int getColumn(){
		return column;
	}
	
	//returns the string representation of the symbol
	@Override
	public String toString() {
		return String.valueOf(symbol);
	}
}
