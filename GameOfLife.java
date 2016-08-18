/**
 * This game is based on John Conway's Game of Life as described in Wikipedia.
 * https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
 * 
 * The constants and random seed generator are declared in Config.java. To 
 * change the pattern of the random world, change the seed value.
 * 
 * This was initially created for an undergraduate project given by 
 * University of Wisconsin-Madison, but has since been updated to handle
 * object-oriented programming.
 * 
 * @author Virat Singh, vsingh28@wisc.edu
 */

import java.util.Scanner; 

public class GameOfLife {

	public static void main(String[] args){

		Scanner scnr = new Scanner(System.in);
		
		Worlds gliderWorld = new Worlds();
		Worlds beaconWorld = new Worlds();
		Worlds boatWorld = new Worlds();
		Worlds rPentominoWorld = new Worlds();
		Worlds randomWorld = new Worlds();

		int choice = 0;
		int genNum = 0;

		//These are the two worlds used for the program
		boolean [][] world = 
				new boolean [Config.WORLD_ROWS][Config.WORLD_COLUMNS];
		boolean [][] newWorld = 
				new boolean [Config.WORLD_ROWS][Config.WORLD_COLUMNS];

		boolean isValid;
		String goToNextGen;

		System.out.print("Welcome to the Game Of Life");
		System.out.println();

		while (choice != 9) {
			do{
				
				clearWorld(world);
				
				//menu choices      			
				System.out.println("Select a pattern of life for the world");
				System.out.println("1 - Glider");
				System.out.println("2 - Beacon");
				System.out.println("3 - Boat");
				System.out.println("4 - R-pentomino");
				System.out.println("5 - Random");
				System.out.println("9 - Exit");
				System.out.print("Choice: ");

				// check for integer input and validating user input
				if (scnr.hasNextInt()) { 								
					choice = scnr.nextInt();
					isValid = true; 
				} 
				else { 													
					System.out.println("Invalid input");
					isValid = false; 
					scnr.nextLine();
				} 
			}while (!(isValid));

			//initializing the world based on the user's choice	
			//user chose choice 1, Glider World
			if (choice == 1) {
				gliderWorld.initializeGlider(world);
				printWorld("Glider", world, genNum);
				goToNextGen = scnr.nextLine();
				//going to next gen as long as user presses only enter
				do {
					goToNextGen = scnr.nextLine();
					if(goToNextGen.equals("")) {
						nextGeneration(world, newWorld);
						genNum++;
						printWorld("Glider", newWorld, genNum);
					}
				}while(goToNextGen.equals(""));
			}

			//user chose choice 2, Beacon World
			else if (choice == 2) {
				beaconWorld.initializeBeacon(world);
				printWorld("Beacon", world, genNum);
				goToNextGen = scnr.nextLine();
				//going to next gen as long as user presses only enter
				do {
					goToNextGen = scnr.nextLine();
					if(goToNextGen.equals("")) {
						nextGeneration(world, newWorld);
						genNum++;
						printWorld("Beacon", newWorld, genNum);
					}
				}while(goToNextGen.equals(""));
			}

			//user chose choice 3, Boat World
			else if (choice == 3) {
				boatWorld.initializeBoat(world);
				printWorld("Boat", world, genNum);
				goToNextGen = scnr.nextLine();
				//going to next gen as long as user presses only enter
				do {
					goToNextGen = scnr.nextLine();
					if(goToNextGen.equals("")) {
						nextGeneration(world, newWorld);
						genNum++;
						printWorld("Boat", newWorld, genNum);
					}
				}while(goToNextGen.equals(""));
			}

			//user chose choice 4, R-Pentomino World
			else if (choice == 4) {
				rPentominoWorld.initializeRpentomino(world);
				printWorld("R-pentomino", world, genNum);
				goToNextGen = scnr.nextLine();
				//going to next gen as long as user presses only enter
				do {
					goToNextGen = scnr.nextLine();
					if(goToNextGen.equals("")) {
						nextGeneration(world, newWorld);
						genNum++;
						printWorld("R-pentomino", newWorld, genNum);
					}
				}while(goToNextGen.equals(""));
			}

			//user chose choice 5, Random World
			else if (choice == 5) {
				randomWorld.initializeRandomWorld(world);
				printWorld("Random", world, genNum);
				goToNextGen = scnr.nextLine();
				//going to next gen as long as user presses only enter
				do {
					goToNextGen = scnr.nextLine();
					if(goToNextGen.equals("")) {
						nextGeneration(world, newWorld);
						genNum++;
						printWorld("Random", newWorld, genNum);
					}
				}while(goToNextGen.equals(""));
			}

			//loop to print out world and prompt for next generation or Exit
			else if (choice == 9) {
				System.out.print("End of Game of Life.");
			}
			//making sure user can only choose integers 1,2,3,4,5, or 9
			else 
				System.out.println("Invalid input");
		}			         
		//closing Scanner to prevent leaks
		scnr.close();
	} 
	
	/**
	 * Creates a new world
	 * @param numRows The number of rows to be in the created world
	 * @param numColumns  The number of columns to be in the created world
	 * @return A double dimension array of boolean that is numRows by 
	 * numColumns in size and initialized to all false values. 
	 */
	public static boolean[][] createNewWorld(int numRows, int numColumns) {

		//initializing the world to be used in the method
		boolean [][] worldSize = 
				new boolean [numRows][numColumns];

		//create the world of the proper size

		//going through the world rows
		for (int k = 0; k < worldSize.length; k++) {
			//going through the world columns
			for (int l = 0; l < worldSize[k].length; l++) { 
				//based on pattern, these cells are alive or dead
				if (worldSize[k][l])
					System.out.print(Config.ALIVE);
				else
					System.out.print(Config.DEAD);
			}
			System.out.println();
		}
		//returning the world
		return worldSize;
	}

	/**
	 * Sets all the cells in the world to not alive (false).
	 * @param world 
	 */
	public static void clearWorld(boolean[][]world) {

		//clearing the world so that previous patterns don't disrupt new ones
		for (int k = 0; k < world.length; k++) {
			for (int l = 0; l < world[k].length; l++) { 
				world[k][l] = false;
			}
		}
	}	

	/** 
	 * Whether a cell is living in the next generation of the game.
	 * 
	 * The rules of the game are as follows:
	 * 1) Any live cell with fewer than two live neighbors dies, as if caused
	 *    by under-population.
	 * 2) Any live cell with two or three live neighbors lives on to the next 
	 *    generation.
	 * 3) Any live cell with more than three live neighbors dies, as if by 
	 *    overcrowding.
	 * 4) Any dead cell with exactly three live neighbors becomes a live cell, 
	 *    as if by reproduction.
	 * 
	 * @param numLivingNeighbors The number of neighbors that are currently
	 *        living.
	 * @param cellCurrentlyLiving Whether the cell is currently living.
	 * 
	 * @return True if this cell is living in the next generation.    
	 */
	public static boolean isCellLivingInNextGeneration(int numLivingNeighbors, 
			boolean cellCurrentlyLiving) {

		//implementing rules of Game of Life
		if(cellCurrentlyLiving) {
			//Rule 1 and Rule 3
			if (numLivingNeighbors < 2 || numLivingNeighbors > 3)
				cellCurrentlyLiving = false;
			//Rule 2
			else if (numLivingNeighbors == 2 || numLivingNeighbors == 3)
				cellCurrentlyLiving = true;
		}
		//Rule 4
		else {
			if (numLivingNeighbors == 3)
				cellCurrentlyLiving = true;
		}
		//returning whether the cell is living or not
		return cellCurrentlyLiving;
	}

	/**
	 * Whether a specific neighbor is alive.
	 *
	 * Check whether the specific cell is alive or dead.
	 * 
	 * Note that cellRow and cellColumn may not be valid. If the cellRow is 
	 * less than 0 or greater than the number of rows -1 
	 * then the cell is outside the world. If the cellColumn is less than 0 
	 * or is greater than the number of columns -1 then 
	 * the cell is outside the world. Return false for any cell outside the 
	 * world.
	 * 
	 * @param world The current world.
	 * @param neighborCellRow The row of the cell which we are wanting to know
	 *  is alive.
	 * @param neighborCellColumn The column of the cell for which we are
	 *  wanting
	 *  to know is alive.
	 * 
	 * @return Whether the cell is alive.
	 */	
	public static boolean isNeighborAlive(boolean [][]world, 
			int neighborCellRow, int neighborCellColumn) {

		//if valid row index
		if (neighborCellRow >= 0 && neighborCellRow <= Config.WORLD_ROWS-1) {
			//if valid column index
			if (neighborCellColumn >= 0 && neighborCellColumn 
					<= Config.WORLD_COLUMNS-1){
				//returns the value as it is indeed in the world dimension
				return world[neighborCellRow][neighborCellColumn];
			}
			//returns false as the cell is not in the world dimensions
			else
				return false;
		}
		//returns false as the cell is not in the world dimensions
		return false;
	}

	/**
	 * Counts the number of neighbors that are currently living around the 
	 * specified cell.
	 *
	 * A cell has eight neighbors. The neighbors are the cells that are 
	 * horizontally, vertically and diagonally adjacent.
	 * 
	 * Note that if a specific cell is on the edge of the world then it may not 
	 * have neighboring cells.  For example: for the 0'th row (top row) of the 
	 * world there are not any rows above it.
	 * Assume all those cells are dead (have false values).
	 * 
	 * @param world The current world.
	 * @param cellRow The row of the cell for which we are looking for
	 * neighbors.
	 * @param cellColumn The column of the cell for which we are looking for 
	 * neighbors.
	 * 
	 * @return The number of neighbor cells that are currently living.
	 */	
	public static int numNeighborsAlive(boolean[][] world, int cellRow, 
			int cellColumn) {

		//setting number of neighbors alive initially to 0 to then increment
		int aliveNeighbours = 0;

		//neighbors in the row above
		if (isNeighborAlive(world, cellRow-1, cellColumn-1)) 
			aliveNeighbours++;
		if (isNeighborAlive(world, cellRow-1, cellColumn)) 
			aliveNeighbours++;
		if (isNeighborAlive(world, cellRow-1, cellColumn+1)) 
			aliveNeighbours++;
		//neighbors in the row below
		if (isNeighborAlive(world, cellRow+1, cellColumn-1))
			aliveNeighbours++;
		if (isNeighborAlive(world, cellRow+1, cellColumn))
			aliveNeighbours++;
		if (isNeighborAlive(world, cellRow+1, cellColumn+1)) 
			aliveNeighbours++;
		//neighbor to the left
		if (isNeighborAlive(world, cellRow, cellColumn-1))
			aliveNeighbours++;
		//neighbor to the right
		if (isNeighborAlive(world, cellRow, cellColumn+1)) 
			aliveNeighbours++;

		//returning the number of neighbors alive
		return aliveNeighbours;
	}

	/**
	 * Creates the next generation of the world.
	 * 
	 * @param currentWorld The world currently shown. 
	 * @param newWorld The new world to determine by looking at
	 * each cells neighbors in the current world. 
	 */
	public static void nextGeneration(boolean[][] currentWorld, 
			boolean[][] newWorld) {		

		//for each row
		for (int i = 0; i < currentWorld.length; i++) {
			//for each column
			for (int j = 0; j < currentWorld[i].length; j++) {
				//determine the number of neighbors that are alive for the 
				//specific cell
				int aliveNeighbors = numNeighborsAlive(currentWorld, i, j);
				//determine whether cell should be alive in the next generation
				newWorld[i][j] = isCellLivingInNextGeneration(aliveNeighbors, 
						currentWorld[i][j]);		
			}
		}
		for (int i = 0; i < currentWorld.length; i++) {
			for (int j = 0; j < currentWorld[i].length; j++) {
				currentWorld[i][j] = newWorld[i][j];
			}
		}
	}

	/**
	 * Prints out the world showing each cell as alive or dead.
	 * 
	 * Loops through every cell of the world. If a cell is alive, print out
	 * the Config.ALIVE character, otherwise the Config.DEAD character. 
	 * 
	 * Tracks how many cells are alive and prints out the number of cells alive
	 * or that no cells are alive.
	 * 
	 * @param patternName The name of the pattern chosen by the user. 
	 * @param world The array representation of the current world. 
	 * @param generationNum The number of the current generation.  
	 */    
	public static void printWorld(String patternName, boolean[][] world, 
			int generationNum) {

		//declare and initialize local variables
		int i, j, aliveCells;
		//the number of cells alive is aliveCells
		aliveCells = 0;

		//print out pattern and generation
		System.out.println();
		System.out.println(patternName + " generation: " + generationNum);
		//for each row in the world
		for (i = 0; i < Config.WORLD_ROWS; i++) {
			//for each column in the world
			for (j = 0; j < Config.WORLD_COLUMNS; j++) {
				//if the cell is alive
				if (world[i][j]){
					System.out.print(Config.ALIVE);
					//counting the number of cells alive
					aliveCells++;
				}
				//otherwise if the cell is dead.
				else
					System.out.print(Config.DEAD);
			}
			System.out.println();
		}
		//print out the number of cells alive.
		System.out.println(aliveCells + " cells are alive.");
		System.out.println();
		//print out options for user
		System.out.println("Options");
		System.out.println("(Enter): show next generation");
		System.out.println("end(Enter): end this simulation");
		System.out.print("Choice:");
	}
}
