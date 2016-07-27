package sprites;
import game.Constants;

/**
 * Class that is used to represent any vacuum objects

 * @author c5rathir
 *
 */
public class Vacuum extends Sprite implements Moveable  {
	
	/** holds the vacuum's current score */
	private int score;
	/** holds the vacuum's capacity */
	private int capacity;
	/** holds the vacuum's current fullness */
	private int fullness;
	/** holds which object is currently under vacuum */
	private Sprite under;
	
	/**
	 * Constructor for a vacuum object. Creates the vacuum and sets the
	 * score and fullness to 0, the capacity to the specified capacity.
	 * Furthermore, it sets the under to a cleanHallway object. 
	 * @param symbol represents the symbol for the vacuum
	 * @param row represents row location for the vacuum
	 * @param column represents the column location for the vacuum
	 * @param capacity represents how many dirts and dustballs the vacuum can
	 * hold
	 */
	public Vacuum(char symbol, int row, int column, int capacity){
		super(symbol, row, column);
		this.score = Constants.INIT_SCORE;
		this.capacity = Constants.CAPACITY;
		this.fullness = Constants.EMPTY;
		this.under = new CleanHallway(Constants.CLEAN, row, column); 
	}
	
	/**
	 * Returns true if the vacuum can clean the object, and false otherwise. 
	 * It takes in the score associated with the object (for dirts and 
	 * dustballs). It increments the score and fullness if it can, and
	 * also sets the vacuum's under to a new CleanHallway object. Otherwise
	 * it returns false and does none of the above.
	 * @param score
	 * @return
	 */
	public boolean clean (int score){
		// does vacuum still have space to take in the DustBall or dirt
		 if (this.fullness + Constants.FULLNESS_INC <= this.capacity){
			// increment score
			 this.score += score;
			 
			// increment fullness
			 this.fullness += Constants.FULLNESS_INC;
		 
			// set under to be a CleanHallway
			 this.under = new CleanHallway(Constants.CLEAN, this.row, 
					 this.column);
			 
			// return true
			 return true;
		 }
		// vacuum was full and did not have space to take in the Dustball or 
		// dirt
		 else
			 return false;
		
	}
	
	/**
	 * Empties the vacuum's contents by setting the fullness to 0.
	 */
	public void empty(){
		this.fullness = Constants.EMPTY;
	}
	
	/**
	 * Returns the current score of the vacuum. needed to access the score
	 * variable as it is a private instance variable. 
	 * @return current score of the vacuum
	 */
	public int getScore(){
		return this.score;
	}
	
	/**
	 * Set's vacuum's to be on top of the under object. 
	 * @param under
	 */
	public void setUnder(Sprite under){
		this.under = under;
	}
	
	/**
	 * Returns the Sprite object the vacuum is on top of.
	 * @return the Sprite object that is under the vacuum.
	 */
	public Sprite getUnder(){
		return this.under;
	}
	
	/**
	 * Changes the internal location of the vacuum such that the vacuum knows
	 * where on the board it is.
	 */
	public void moveTo(int row, int column){
		this.row = row;
		this.column = column;
	}
	
}
	