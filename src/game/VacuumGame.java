package game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import sprites.CleanHallway;
import sprites.Dirt;
import sprites.Dumpster;
import sprites.DustBall;
import sprites.Sprite;
import sprites.Vacuum;
import sprites.Wall;

/**
 * A class that represents the basic functionality of the vacuum game. This
 * class is responsible for performing the following operations: 1.At creation
 * it initializes the instance variables used to store the current state of the
 * game. 2. When a move is specified, it checks if it is a legal move and makes
 * the move if it is legal. 3. It reports information about the current state 
 * of the game when asked.
 */
public class VacuumGame {

	// a random number generator to move the DustBalls
	private Random random;

	// the grid
	private Grid<Sprite> grid;

	// the first player
	private Vacuum vacuum1;

	/// the second player
	private Vacuum vacuum2;

	// the dirt (both static dirt and mobile dust balls)
	private List<Dirt> dirts;

	// the dumpsters
	private List<Dumpster> dumpsters;

	// extra list to hold which only dustballs
	private List<DustBall> dustballs;

	/**
	 * Creates a new VacuumGame that corresponds to the given input text file.
	 * Assumes that the input file has one or more lines of equal lengths, and
	 * that each character in it (other than newline) is a character that
	 * represents one of the sprites in this game.
	 * 
	 * @param layoutFileName
	 *            path to the input grid file
	 */
	public VacuumGame(String layoutFileName) throws IOException {
		this.dirts = new ArrayList<Dirt>();
		this.dumpsters = new ArrayList<Dumpster>(); // Jen: may not need this
		this.dustballs = new ArrayList<DustBall>();
		this.random = new Random();

		// open the file, read the contents, and determine
		// dimensions of the grid
		int[] dimensions = getDimensions(layoutFileName);
		this.grid = new ArrayGrid<Sprite>(dimensions[0], dimensions[1]);

		// open the file again, read the contents, and store them in grid
		Scanner sc = new Scanner(new File(layoutFileName));

		// INITIALIZE THE GRID HERE

		// rowToImplement is used as a line to hold a line of text that is
		// read from the text files
		String rowToImplement;
		
		// element is the individual characters that are read from
		// rowToImplement
		char element;
		
		// read through the text file to initialize the grid so that it
		// matches the text file. variable i deals with the row, element j
		// deals with column
		for (int i = 0; i < dimensions[0]; i++) {

			// deal with each line one by one
			rowToImplement = sc.nextLine();
			for (int j = 0; j < dimensions[1]; j++) {
				
				//element is set as the character in row i, column j of the
				//text file
				element = rowToImplement.charAt(j);

				// checks what character the element is and
				// sets row i, column j in the grid accordingly

				switch (element) {

				// if this character represents a clean hallway
				// then create a new clean hallway object
				case Constants.CLEAN:
					CleanHallway cleanH = new CleanHallway(Constants.CLEAN,
							i, j);
					this.grid.setCell(i, j, cleanH);
					break;

				// if this character represents a dirt object
				// then create a new dirt object
				case Constants.DIRT:
					Dirt dirt = new Dirt(Constants.DIRT, i, j, 
							Constants.DIRT_SCORE);
					this.grid.setCell(i, j, dirt);
					// add the dirt object to the dirts list
					dirts.add(dirt);
					break;
				
				// if this character represents a dumpster
				// then create a new dumpster object
				case Constants.DUMPSTER:
					Dumpster dumpster = new Dumpster(Constants.DUMPSTER,
							i, j);
					this.grid.setCell(i, j, dumpster);
					break;

				// if this character represents a dustball
				// then create a new dustball object
				case Constants.DUST_BALL:
					DustBall dustBall = new DustBall(Constants.DUST_BALL,
							i, j, Constants.DUST_BALL_SCORE);
					this.grid.setCell(i, j, dustBall);
					
					// add  dust ball to the dustball list
					dustballs.add(dustBall);
					
					// add dust ball to the dirts list
					dirts.add(dustBall);
					break;

				// if this character represents a wall
				// then create a new wall object
				case Constants.WALL:
					Wall wall = new Wall(Constants.WALL, i, j);
					this.grid.setCell(i, j, wall);
					break;

				// if this character represents vacuum 1
				case '1':
					vacuum1 = new Vacuum('1', i, j, Constants.CAPACITY);
					this.grid.setCell(i, j, vacuum1);
					break;

				// if this character represents vacuum 2
				case '2':
					vacuum2 = new Vacuum('2', i, j, Constants.CAPACITY);
					this.grid.setCell(i, j, vacuum2);
					break;
				}
			}
		}

		sc.close();
	}

	/**
	 * @return the grid
	 */
	public Grid<Sprite> getGrid() {
		return grid;
	}

	/**
	 * Returns the dimensions of the grid in the file named layoutFileName.
	 * 
	 * @param layoutFileName
	 *            path of the input grid file
	 * @return an array [numRows, numCols] where numRows is the number of rows
	 *         and numCols is the number of columns in the grid that 
	 *         corresponds to the given input grid file
	 * @throws IOException
	 */
	private int[] getDimensions(String layoutFileName) throws IOException {

		Scanner sc = new Scanner(new File(layoutFileName));

		// find the number of columns
		String nextLine = sc.nextLine();
		int numCols = nextLine.length();

		int numRows = 1;

		// find the number of rows
		while (sc.hasNext()) {
			numRows++;
			nextLine = sc.nextLine();
		}
		sc.close();
		return new int[] { numRows, numCols };
	}
	/**
	 * Returns the a Vacuum object, more specifically it returns vacuum one.
	 * The object contains all of it's instance variables and methods (all
	 * of it's information).
	 * @return the object vacuum1 
	 */
	public Vacuum getVacuumOne() {
		return vacuum1;
	}

	/**
	 * Returns the a Vacuum object, more specifically it returns vacuum two.
	 * The object contains all of it's instance variables and methods (all
	 * of it's information).
	 * @return the object vacuum2
	 */
	public Vacuum getVacuumTwo() {
		return vacuum2;
	}

	/**
	 * Returns the number of rows that are present in the grid as an integer.
	 * This occurs immediately.
	 * @return the number of rows for the grid
	 */
	public int getNumRows() {
		return grid.getNumRows();
	}

	/**
	 * Returns the number of columns that are present in the grid as an
	 * integer. This occurs immediately. It uses grid's getNumColumns()
	 * method.
	 * @return the number of columns for the grid
	 */
	public int getNumColumns() {
		return grid.getNumColumns();
	}

	/**
	 * Returns the Sprite object that is specified at a specific row, i,
	 * and column, j. It uses grid's getCell method which requires two
	 * integer parameters, i and j. These correspond to the specific
	 * location on the grid.
	 * @param i specifies the row value
	 * @param j specifies the column value
	 * @return the Sprite object that is at the specified location.
	 */
	public Sprite getSprite(int i, int j) {
		return grid.getCell(i, j);
	}

	/**
	 * Returns a boolean value if the game is over. It will return true if all
	 * the dustball and dirt objects are no longer present, otherwise it will
	 * return false. To determine this, the dirts list and dustballs list
	 * are used. It will return false if their sizes are greater than 0,
	 * otherwise it will return true.
	 * @return true or false depending on if the game is over or not.
	 */
	public boolean gameOver() {
		if (dirts.size() > 0 || dustballs.size() > 0)
			return false;
		else
			return true;
	}

	/**
	 * Returns the winner of the game by comparing the score of each vacuum.
	 * Uses each vacuum's getScore method for the comparison.
	 * @return the winner of the game by returning 1 if player one wins and 2
	 * if player two wins. Returns 0 if the game is tied.
	 */
	public int getWinner() {
		if (vacuum1.getScore() > vacuum2.getScore())
			return 1;
		else if (vacuum2.getScore() > vacuum1.getScore())
			return 2;
		else
			return 0;
	}

	/**
	 * Returns true if the vacuum is able to move, and false if the vacuum is
	 * unable to move. It first check's if the key pressed by the user is a
	 * valid key. If it is, then it checks if the possible destination is a
	 * valid move for a vacuum. If it is possible, the vacuum moves to the
	 * destination. This is all handled by the private determineSprite method.
	 * Everything that the vacuum needs to do such as cleaning the dirts, 
	 * emptying the vacuum etc is taken care by this method. After the method
	 * has determined whether or not the vacuum was able to move and completed
	 * it's appropriate tasks, all the dustballs are moved by calling the
	 * moveDustBall method.
	 * @param nextMove
	 * @return true if the vacuum was able to move, else returns false
	 */
	public boolean move(char nextMove) {
		// validity is changed to true if the vacuum moves
		boolean validity = false;

		// obtains the sprite at the destination
		Sprite dest;

		// gets the row and column for possible destination
		int rowDest;
		int colDest;

		// the cases are used to see if player has entered a valid key
		switch (nextMove) {
		
		// checks if nextMove was the 's' key
		case Constants.P1_DOWN:
			// gets the desired destination's row number
			rowDest = vacuum1.getRow() + Constants.DOWN;
			
			//gets the desired destination's column number
			colDest = vacuum1.getColumn();
			
			//gets the sprite that is currently at the desired destination
			dest = getSprite(rowDest, colDest);
			
			// determines if it is possible to move to the required location
			// calls the determineSprite method to do this
			validity = determineSprite(vacuum1, rowDest, colDest, dest);
			break;
		
		// the rest of the cases are the same steps as the first case
		case Constants.P1_LEFT:
			rowDest = vacuum1.getRow();
			colDest = vacuum1.getColumn() + Constants.LEFT;
			dest = getSprite(rowDest, colDest);
			validity = determineSprite(vacuum1, rowDest, colDest, dest);
			break;
		case Constants.P1_RIGHT:
			rowDest = vacuum1.getRow();
			colDest = vacuum1.getColumn() + Constants.RIGHT;
			dest = getSprite(rowDest, colDest);
			validity = determineSprite(vacuum1, rowDest, colDest, dest);
			break;
		case Constants.P1_UP:
			rowDest = vacuum1.getRow() + Constants.UP;
			colDest = vacuum1.getColumn();
			dest = getSprite(rowDest, colDest);
			validity = determineSprite(vacuum1, rowDest, colDest, dest);
			break;
		case Constants.P2_DOWN:
			rowDest = vacuum2.getRow() + Constants.DOWN;
			colDest = vacuum2.getColumn();
			dest = getSprite(rowDest, colDest);
			validity = determineSprite(vacuum2, rowDest, colDest, dest);
			break;
		case Constants.P2_LEFT:
			rowDest = vacuum2.getRow();
			colDest = vacuum2.getColumn() + Constants.LEFT;
			dest = getSprite(rowDest, colDest);
			validity = determineSprite(vacuum2, rowDest, colDest, dest);
			break;
		case Constants.P2_RIGHT:
			rowDest = vacuum2.getRow();
			colDest = vacuum2.getColumn() + Constants.RIGHT;
			dest = getSprite(rowDest, colDest);
			validity = determineSprite(vacuum2, rowDest, colDest, dest);
			break;
		case Constants.P2_UP:
			rowDest = vacuum2.getRow() + Constants.UP;
			colDest = vacuum2.getColumn();
			dest = getSprite(rowDest, colDest);
			validity = determineSprite(vacuum2, rowDest, colDest, dest);
			break;
			
		// if the key entered was not a valid key, then return false
		default:
			return false;
		}
		
		// moves dustballs, regardless of whether vacuum moved
		moveDustBall();

		// return whether the vacuum was able to move
		return validity;
	}

	/**
	 * Private method used when a vacuum moves onto a dumpster. The procedures
	 * in this method are specific to this case scenario only and should be
	 * used accordingly. This method changes the vacuum's internal row and 
	 * column value, updates the grid to represent the appropriate
	 * objects at the source (where the vacuum is coming from) and at the
	 * destination. It updates what is under the vacuum (in this case a 
	 * dumpster) and it empties the contents of the vacuum by calling
	 * its empty method.
	 * @param vac is the vacuum that is moving
	 * @param row is the destination row number
	 * @param col is the destination column number
	 * @param obj is the Sprite object that is at the destination
	 */
	private void vacuumDumpster(Vacuum vac, int row, int col, Sprite obj) {
		// update the internal reference of vacuum
		vac.moveTo(row, col);

		// update grid for source location shows what is currently under 
		// vacuum
		Sprite temp = vac.getUnder();
		grid.setCell(temp.getRow(), temp.getColumn(), temp);

		// set under to point to the dumpster object
		vac.setUnder(obj);

		// since vacuum is on a dumpster, clear it's contents
		vac.empty();

		// update the grid so that it shows the vacuum instead of the dumpster
		grid.setCell(row, col, vac);
	}

	/**
	 * Private method used when a vacuum moves onto a CleanHallway. The 
	 * procedures in this method are specific to this case scenario only 
	 * and should be used accordingly. This method changes the vacuum's 
	 * internal row and column value, updates the grid to represent the 
	 * appropriate objects at the source (where the vacuum is coming from)
	 * and at the destination. It updates what is under the vacuum (in this
	 * case a cleanHallway).
	 * @param vac is the vacuum that is moving
	 * @param row is the destination row number
	 * @param col is the destination column number
	 * @param obj is the Sprite object that is at the destination
	 */
	private void vacuumCleanHallway(Vacuum vac, int row, int col, Sprite obj){
		// update the internal reference of vacuum
		vac.moveTo(row, col);

		// update grid for source location to what is currently under vacuum
		Sprite temp = vac.getUnder();
		grid.setCell(temp.getRow(), temp.getColumn(), temp);

		// set under to point to the clean hallway object
		vac.setUnder(obj);

		// update the grid so that it shows the vacuum instead of the 
		// cleanHallway
		grid.setCell(row, col, vac);
	}

	/**
	 * Private method used when a vacuum moves onto a DustBall. The 
	 * procedures in this method are specific to this case scenario only 
	 * and should be used accordingly. This method changes the vacuum's 
	 * internal row and column value, updates the grid to represent the 
	 * appropriate objects at the source (where the vacuum is coming from)
	 * and at the destination. It updates what is under the vacuum (in this
	 * case either a DustBall, if it was unable to clean it, or a new
	 * cleanHallway object if it was able to clean it). This method calls
	 * the vacuum's clean method to determine if it is eligible to clean the
	 * dustball. If it is, it will also remove the dustball from the
	 * dustballs and dirts list.
	 * @param vac is the vacuum that is moving
	 * @param row is the destination row number
	 * @param col is the destination column number
	 * @param obj is the Sprite object that is at the destination - in this case
	 * a DustBall
	 */
	private void vacuumDustBall(Vacuum vac, int row, int col, Sprite obj) {
		// update the internal reference of vacuum
		vac.moveTo(row, col);

		// update grid for source location to what is currently under vacuum
		Sprite temp = vac.getUnder();
		grid.setCell(temp.getRow(), temp.getColumn(), temp);

		// check if the new location can be cleaned
		if (vac.clean(Constants.DUST_BALL_SCORE)) {
			
			// remove the dustball from dirts and dustballs list
			dustballs.remove(obj);
			dirts.remove(obj);
		}
		// otherwise sets vacuum.under to the dustball object
		else
			vac.setUnder(obj);

		// update the grid so that it shows the vacuum instead of the dustball
		grid.setCell(row, col, vac);
	}

	/**
	 * Private method used when a vacuum moves onto a Dirt. The 
	 * procedures in this method are specific to this case scenario only 
	 * and should be used accordingly. This method changes the vacuum's 
	 * internal row and column value, updates the grid to represent the 
	 * appropriate objects at the source (where the vacuum is coming from)
	 * and at the destination. It updates what is under the vacuum (in this
	 * case either a Dirt, if it was unable to clean it, or a new
	 * cleanHallway object if it was able to clean it). This method calls
	 * the vacuum's clean method to determine if it is eligible to clean the
	 * dirt. If it is, it will also remove the dirt from the dirts list.
	 * @param vac is the vacuum that is moving
	 * @param row is the destination row number
	 * @param col is the destination column number
	 * @param obj is the Sprite object that is at the destination - in this case
	 * a dirt
	 */
	private void vacuumDirt(Vacuum vac, int row, int col, Sprite obj) {
		// update the internal reference of vacuum
		vac.moveTo(row, col);

		// update grid for source location to what is currently under vacuum
		Sprite temp = vac.getUnder();
		grid.setCell(temp.getRow(), temp.getColumn(), temp);

		// check if the new location can be cleaned
		if (vac.clean(Constants.DIRT_SCORE))
			// remove the dirt object from list
			dirts.remove(obj);
		else
			vac.setUnder(obj);

		// update the grid so that it shows the vacuum instead of the dirt
		grid.setCell(row, col, vac);
	}

	/**
	 * Private method that returns a true or false value if the vacuum
	 * is able to move to the specified row and column. This is done by
	 * checking what the obj's (at destination) symbol is. By determining
	 * the object at the destination, it can call the specific move object
	 * pertaining to that object.
	 * @param vac is the vacuum that is trying to move to the specified
	 * row and column
	 * @param row is the specified row number
	 * @param col is the specified column number
	 * @param obj is the object at the specified row and column
	 * @return true if the vacuum can move to the specified row and column and
	 *  false otherwise
	 */
	private boolean determineSprite(Vacuum vac, int row, int col, Sprite obj){
		
		// sets the return value to true and will only be changed to false
		// if the object is either the other vacuum or a wall
		boolean retVal = true;
		
		// if the obj is a dumpster, call the vacuumDumpster method
		if (obj.getSymbol() == Constants.DUMPSTER)
			vacuumDumpster(vac, row, col, obj);
		
		// if the obj is a cleanHallway, call the vacuumCleanHallway method
		else if (obj.getSymbol() == Constants.CLEAN)
			vacuumCleanHallway(vac, row, col, obj);
		
		// if the obj is a DustBall, call the vacuumDustBall method
		else if (obj.getSymbol() == Constants.DUST_BALL)
			vacuumDustBall(vac, row, col, obj);
		
		// if the obj is a Dirt, call the vacuumDirt method
		else if (obj.getSymbol() == Constants.DIRT)
			vacuumDirt(vac, row, col, obj);
		
		// object is either the other vacuum or a wall
		else
			retVal = false;
		return retVal;
	}

	/**
	 * Private method that moves the dustball. This is called after a vacuum
	 * attempts to move. It goes through the list that contains only dustballs
	 * and retrieves each dustball. It get's a random number between 0 and 3
	 * inclusive, and attempts to move in the corresponding direction (each
	 * number has a corresponding direction associated with it). If it cannot
	 * move in a direction, the number is decremented and a new number is
	 * retrieved. It does this until the counter, directionsRemaining is 0, 
	 * which at this point the dustball cannot move so it skips to the next 
	 * dustball in the list, or terminates this method if there are no more 
	 * dustballs. This method calls another method, validDBMove to determine 
	 * if the move is valid.
	 */
	private void moveDustBall() {
		
		// need to loop through the dirts list to obtain each DustBall
		for (int i = 0; i < dustballs.size(); i++) {
			
			// validity is used to see if the dustball was able to move
			boolean validity = false;
			
			// rowDest and colDest are used to determine the the destination
			// coordinates
			int rowDest;
			int colDest;
			
			// direction is the number we obtain from the random number 
			// generator
			int direction;
			
			//create a new list of strings which has the corresponding 
			// directions
			List<String> dirString = new ArrayList<String>
			(Arrays.asList("Left", "Right", "Down", "Up"));
			
			// set the initial counter to 4 to try all four positions
			int directionsRemaining = 4;
			
			// a do-while loop is used to make sure that everything below
			// happens atleast once
			do {
				// obtain the random number between 0 and 3 inclusive
				direction = random.nextInt(directionsRemaining);
			
				// checkSprite is to determine what object is at the 
				// destination
				Sprite checkSprite;
				switch (dirString.get(direction)) {
				
				// move left
				case "Left":
					
					// get the ith dustball from the list and get it's
					// row and columns and determine what the Sprite object at
					// the destination are
					rowDest = dustballs.get(i).getRow();
					colDest = dustballs.get(i).getColumn() + Constants.LEFT;
					checkSprite = getSprite(rowDest, colDest);
					
					//determine if moving to this destination is valid. If it
					//is, then do the appropraite changes (handled by the 
					//validDBMove method) and change validity to true.
					if (validDBMove(dustballs.get(i), rowDest, colDest, 
							checkSprite))
						validity = true;
					break;
					
				// move right
				// the remaining cases follow the same structure as the "left"
				// case
				case "Right":
					rowDest = dustballs.get(i).getRow();
					colDest = dustballs.get(i).getColumn() + Constants.RIGHT;
					checkSprite = getSprite(rowDest, colDest);
					if (validDBMove(dustballs.get(i), rowDest, colDest, 
							checkSprite))
						validity = true;
					break;
					
				// move down
				case "Down":
					rowDest = dustballs.get(i).getRow() + Constants.DOWN;
					colDest = dustballs.get(i).getColumn();
					checkSprite = getSprite(rowDest, colDest);
					if (validDBMove(dustballs.get(i), rowDest, colDest, 
							checkSprite))
						validity = true;
					break;
					
				// move up
				case "Up":
					rowDest = dustballs.get(i).getRow() + Constants.UP;
					colDest = dustballs.get(i).getColumn();
					checkSprite = getSprite(rowDest, colDest);
					if (validDBMove(dustballs.get(i), rowDest, colDest, 
							checkSprite))
						validity = true;
					break;
				}
				// decrement directionsRemaining counter in case the attempted
				// destination was invalid. Removes the direction from list
				// to ensure the same direction cannot return
				directionsRemaining--;
				dirString.remove(direction);
			
				//repeat this if the move was invalid and the number of
				//destinations is greater than 0
			} while (!validity && directionsRemaining > 0);
		}
	}

	/**
	 * Returns whether the attempted move by the dustball is valid by checking
	 * the object at the destination (by checking what the object sprite is). 
	 * This calls another method, backboneOfMovingDB which does the actual 
	 * moving.
	 * @param db is the specified dustball that is attempting to move
	 * @param row is the specified row number
	 * @param col is the specified column number
	 * @param sprite is the Sprite object at the destination
	 * @return true if the dustball can move, and false otherwise.
	 */
	private boolean validDBMove(DustBall db, int row, int col, Sprite sprite){
		
		// retValue is needed to determine whether the dustball was able to
		// move. Initially it is set to true and will remain true unless
		// the dustball is unable to move which is handled by the default
		// case
		boolean retValue = true;

		//determine the object at destination by checking sprite's symbol
		switch (sprite.getSymbol()) {
		
		//sprite is a cleanHallway object
		case Constants.CLEAN:
			backboneOfMovingDB(db, row, col, sprite);
			break;
			
		case Constants.DIRT:
			
			// get the dirt object that is at the destination
			Dirt dirtAtDestination = (Dirt) (getSprite(row, col));
			backboneOfMovingDB(db, row, col, sprite);
			
			// delete the dirt object that was at the destination from the
			// dirts list as it has been replaced by the dustball, db.
			dirts.remove(dirtAtDestination);
			break;
			
		case Constants.DUST_BALL:
			
			// get the dustball object that is at the destination
			DustBall dustballAtDest = (DustBall) (getSprite(row, col));
			backboneOfMovingDB(db, row, col, sprite);
			
			// remove the dustball object that was at the destination from the
			// dirts list and the dustballs list as it has been replaced by
			// the dustball, db.
			dustballs.remove(dustballAtDest);
			dirts.remove(dustballAtDest);
			break;
			
		// the sprite at the destination is not a dustball, dirt or 
		// cleanhallway
		default:
			retValue = false;
		}
		return retValue;
	}

	/**
	 * Does the actual implementation of moving the dustball.
	 * @param dustb is the dustball that moves
	 * @param row is the specified row number
	 * @param col is the specified column number
	 * @param sprite is the Sprite object at the location
	 */
	private void backboneOfMovingDB(DustBall dustb, int row, int col, 
			Sprite sprite) {
		// obtain the coordinates of current location for dustball
		int rowPrev = dustb.getRow();
		int colPrev = dustb.getColumn();

		// update the internal location of dustball
		dustb.moveTo(row, col);

		// create dirt object
		Dirt dirt = new Dirt(Constants.DIRT, rowPrev, colPrev, 
				Constants.DIRT_SCORE);

		// add this dirts object to the list
		dirts.add(dirt);

		// check to see if the previous location holds a vacuum. If it does 
		// then set the previous location to the vacuum, otherwise set it to
		// the new dirt object
		Sprite checkVacuum = getSprite(rowPrev, colPrev);

		if (checkVacuum.getSymbol() == '1')
			vacuum1.setUnder(dirt);
		else if (checkVacuum.getSymbol() == '2')
			vacuum2.setUnder(dirt);
		else
			grid.setCell(rowPrev, colPrev, dirt);
		
		// update the grid so that it displays the new location for the 
		// dustball
		grid.setCell(row, col, dustb);

	}
}