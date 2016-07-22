
public class Worlds {

	/**
	 * Initializes the world to the Glider pattern.
	 * ..........
	 * .@........
	 * ..@@......
	 * .@@.......
	 * ..........
	 * ..........
	 * ..........
	 * ..........
	 * ..........
	 * ..........
	 * @param world  the existing double dimension array that will be 
	 * reinitialized to the Glider pattern. 
	 */
	public void initializeGlider(boolean[][]world) {

		//in the world set the cells to true that are alive for the 
		// Glider pattern.
		for (int i = 0; i < world.length; i++) {
			for (int j = 0; j < world[i].length; j++) {
				if ((i == 1 && j == 1) || (i == 2 && j == 2) ||
						(i == 2 && j == 3) || (i == 3 && j == 1) ||
						(i == 3 && j == 2)) {
					world[i][j] = true;
				}
				else
					world[i][j] = false;
			}
		}
	}

	/**.
	 * Initializes the world to the Beacon pattern.
	 * ..........
	 * .@@.......
	 * .@........
	 * ....@.....
	 * ...@@.....
	 * ..........
	 * ..........
	 * ..........
	 * ..........
	 * .......... 
	 * @param world the existing double dimension array that will be 
	 * reinitialized to the Beacon pattern.
	 */		
	public void initializeBeacon(boolean[][] world) {

		//in the world set the cells to true that are alive for the 
		// Beacon pattern.
		for (int i = 0; i < world.length; i++) {
			for (int j = 0; j < world[i].length; j++) {
				if ((i == 1 && j == 1) || (i == 2 && j == 1) ||
						(i == 1 && j == 2) || (i == 4 && j == 4) ||
						(i == 3 && j == 4) || (i == 4 && j == 3)) {
					world[i][j] = true;
				}
				else
					world[i][j] = false;
			}
		}
	}
	
	/**
	 * Initializes the world to the Boat pattern.
	 * ..........
	 * .@@.......
	 * .@.@......
	 * ..@.......
	 * ..........
	 * ..........
	 * ..........
	 * ..........
	 * ..........
	 * ..........
	 * @param world the existing double dimension array that will be 
	 * reinitialized to the Boat pattern.
	 */		
	public void initializeBoat(boolean[][] world) {

		//in the world set the cells to true that are alive for the 
		// Boat pattern.
		for (int i = 0; i < world.length; i++) {
			for (int j = 0; j < world[i].length; j++) {
				if ((i == 1 && j == 1) || (i == 2 && j == 1) ||
						(i == 1 && j == 2) || (i == 2 && j == 3) ||
						(i == 3 && j == 2)) {
					world[i][j] = true;
				}
				else
					world[i][j] = false;	
			}
		}
	}
	
	/**
	 * Initializes the world to the R-pentomino pattern.
	 * ..........
	 * ..@@......
	 * .@@.......
	 * ..@.......
	 * ..........
	 * ..........
	 * ..........
	 * ..........
	 * ..........
	 * ..........
	 * @param world the existing double dimension array that will be 
	 * reinitialized to the R-pentomino pattern.
	 */		
	public void initializeRpentomino(boolean[][] world) {

		//in the world set the cells to true that are alive for the 
		// R-pentomino pattern.
		for (int i = 0; i < world.length; i++) {
			for (int j = 0; j < world[i].length; j++) {
				if ((i == 1 && j == 2) || (i == 1 && j == 3) ||
						(i == 2 && j == 1) || (i == 2 && j == 2) ||
						(i == 3 && j == 2)) {
					world[i][j] = true;
				}
				else
					world[i][j] = false;	
			}
		}
	}
	
	/**
	 * Initialize the GameOfLife world with a random selection of cells alive. 
	 * 
	 * @param world  the existing double dimension array that will be 
	 * reinitialized to a Random pattern.
	 */	
	public void initializeRandomWorld(boolean[][] world){

		//loop through every row
		for (int i = 0; i < Config.WORLD_ROWS; i++) {
			//here we are within a row, so loop through every column
			for (int j = 0; j < Config.WORLD_COLUMNS; j++) {    	
				if(Config.RNG.nextDouble() < Config.CHANCE_ALIVE) {
					//set cell to be alive
					world[i][j] = true;
				}
				else 
					world[i][j] = false;
			}
		}
	}
	
}