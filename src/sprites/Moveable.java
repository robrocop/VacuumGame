package sprites;

/**
 * interface that will allow Sprite objects to move and needs to be
 * overwritten
 * @author c5rathir
 *
 */
public interface Moveable {
	/**
	 * Method that will be overwritten in later classes.
	 * @param row (required) specifies the row number
	 * @param column (required) specifies the column number.
	 */
	public void moveTo(int row, int column);
}
