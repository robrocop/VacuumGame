package game;

/**
 * This class allows VacuumGame to generate the grid for the game. It
 * implements all the methods that are in Grid.java.
 * @author c5rathir
 *
 * @param <T>
 */
public class ArrayGrid<T> implements Grid<T> {
	
	// private variable to determine the number of rows and columns
	private int numRows;
	private int numColumns;
	
	// private variable that holds the address for the game grid
	private T[][] myBoard;

	/**
	 * the constructor for ArrayGrid is called when the grid is initialized
	 * in VacuumGame.
	 * @param numRows specifies the number of rows needed for the board
	 * @param numColumns specifies the number of columns needed for the board
	 */
	@SuppressWarnings("unchecked")
	public ArrayGrid(int numRows, int numColumns) {
		this.numRows = numRows;
		this.numColumns = numColumns;
		this.myBoard = (T[][]) new Object[numRows][numColumns];
	}

	/** 
	 * overrides the corresponding method in Grid
	 * Will set the cell in the grid at the specified location with item
	 * @param row is the specified row number
	 * @param column is the specified column number
	 * @param item is the Sprite object that will be set
	 */
	@Override
	public void setCell(int row, int column, T item) {
		myBoard[row][column] = item;
	}

	/**
	 * overrides the corresponding method in Grid
	 * Will get the object that is in the specified cell (located at r and
	 * column).
	 * @param row is the specified row number
	 * @param column is the specified column number
	 * @return object T (which will be a Sprite object)
	 */
	@Override
	public T getCell(int row, int column) {
		return myBoard[row][column];
	}

	/**
	 *overrides the corresponding method in Grid
	 *returns the number of rows in the grid
	 * @return the number of rows in the grid
	 */
	@Override
	public int getNumRows() {
		return numRows;
	}

	/**
	 * overrides the corresponding method in Grid
	 * returns the number of columns in the grid
	 * @return the number of columns in the grid
	 */
	@Override
	public int getNumColumns() {
		return numColumns;
	}

	/**
	 *overrides the corresponding method in Grid
	 * determines if the invoking object is equal to other
	 * @param other is the object that the comparison is made to
	 * @return true if the objects are equal, false otherwise
	 */
	@Override
	public boolean equals(Object other) {
		if (other.getClass() == ArrayGrid.class) {
			if (((ArrayGrid) other).getNumRows() == this.getNumRows()
					&& ((ArrayGrid) other).getNumColumns() == 
					this.getNumColumns()) {
				for (int i = 0; i < this.getNumRows(); i++) {
					for (int j = 0; j < this.getNumColumns(); j++) {
						if (((ArrayGrid) other).getCell(i, j) != 
								this.getCell(i, j))
							return false;
					}
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 *overrides the corresponding method in Grid
	 * Prints a string representation of the grid with the objects on it
	 * @return the grid
	 */
	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				s = s + getCell(i, j);
			}
			s = s + '\n';
		}
		return s;
	}

}
