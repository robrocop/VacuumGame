package game;

/**
 * Implements an interface for the grid that will be used in ArrayGrid. It
 * can take T objects and several methods that need to be completed in the
 * array grid.
 * @author c5rathir
 *
 * @param <T>
 */
public interface Grid<T> {

	/**
	 * Will set the cell in the grid at the specified location with item
	 * @param row is the specified row number
	 * @param column is the specified column number
	 * @param item is the Sprite object that will be set
	 */
	public void setCell(int row, int column, T item);

	/**
	 * Will get the object that is in the specified cell (located at r and
	 * column).
	 * @param row is the specified row number
	 * @param column is the specified column number
	 * @return object T (which will be a Sprite object)
	 */
	public T getCell(int row, int column);

	/**
	 * returns the number of rows in the grid
	 * @return the number of rows in the grid
	 */
	public int getNumRows();

	/**
	 * returns the number of columns in the grid
	 * @return the number of columns in the grid
	 */
	public int getNumColumns();

	/**
	 * determines if the invoking object is equal to other
	 * @param other is the object that the comparison is made to
	 * @return true if the objects are equal, false otherwise
	 */
	public boolean equals(Object other);

	/**
	 * Prints a string representation
	 * @return the string representation of the invoking object
	 */
	public String toString();
}
