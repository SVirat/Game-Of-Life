import java.util.Random;

/**
 * Contains constants that are used by GameOfLife
 * for the size of the world and the symbols used to represent
 * alive and dead cells.
 *
 * Values that are defined here must be used by name because we may 
 * change these values to ensure that your program works with any settings.
 * 
 */
public class Config {
	
	/** 
     * The maximum and minimum number of rows and columns that the world can be.
     *
     * For testing purposes, WORLD_ROWS and WORLD_COLUMNS could range in value
     * from MIN_WORLD_SIZE to MAX_WORLD_SIZE.
     */	
	public static final int MIN_WORLD_SIZE = 5;
	public static final int MAX_WORLD_SIZE = 80;

	/** 
     * The number of rows and columns of cells in the world. 
     * 
     * You must use these named constants in GameOfLife.java and not hard code 
     * the magic number 10 in case a different size world is desired in the 
     * future.  For testing we may set each of these to any value between 
     * MIN_WORLD_SIZE to MAX_WORLD_SIZE.  WORLD_ROWS and WORLD_COLUMNS are not
     * required to be the same value.
     */	
	public static final int WORLD_ROWS = 10;
	public static final int WORLD_COLUMNS = 10;
	
    /** 
     * The character displayed in the world if that cell is alive.
     */	
	public static final char ALIVE = '@';
	
    /** 
     * The character displayed in the world if that cell is dead.
     */	
	public static final char DEAD = '.';
	
    /** Used to seed the java.util.Random object for generating
     * random numbers used by the initializeRandomWorld method.  
     * By seeding the Random generator, we can predict
     * the "pseudo-random" values that will be generated.
     * This predictability aids in program development 
     * and allows for automated grading.
     */	
	private static final int SEED = 123;
	
    /** CHANCE_ALIVE is used in the initializeRandomWorld method to initialize 
     * the cells that are alive in the initial generation. For example a value 
     * of 0.25 means about 25% of the cells should be alive.
     */
    public static final double CHANCE_ALIVE = 0.25;

    /** Use the RNG named constant in the initializeRandomWorld method of 
	 * your GameOfLife.java program to initialize the cells that are alive in 
	 * the initial generation.
	 */
	public static final Random RNG = new Random( SEED );	
}