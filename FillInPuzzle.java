import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 */


/**
 * @author Mayank
 * Class contains the Method to load the Puzzle, Solve the Puzzle and to print the Puzzle.
 */
public class FillInPuzzle {

	/**
	 * Global Attribute :
	 * 		puzzle : Object of the CrossWord puzzle
	 * 		row and column size of the crossword puzzle
	 * 		visited : Set of the Word which are already used in the puzzle and can't be used any further to fill in any other placeholder.
	 * 		choices : wrong choices made and later were undone
	 * 		totalChoices : total number of choices made (correct + wrong)
	 * 		visitedPlaceholder :  of list placeholder already filled in and solved.
	 */
	private static CrossWordPuzzle puzzle;
	private int rowSize, colSize = 0;
	private int wordCount;
	private HashSet<String> visited = new HashSet<String>();
	private static int choices;
	private static int totalChoices;
	private HashSet<PlaceHolder> visitedPlaceHolder = new HashSet<PlaceHolder>();

	
	/**
	 *	Default Constructor
	 * 
	 */
	public FillInPuzzle() {
		// TODO Auto-generated constructor stub
		
	}

	/**
	 * @return boolean (true or False  based on the details of the puzzle provided through bufferReader.
	 * 					- If Data provided is correct and can be loaded successfully then return true,
	 * 					  return false, otherwise
	 * 
	 */
	public Boolean loadPuzzle(BufferedReader stream) {
		boolean result = false;
		String inputLines;
		Set<Integer> wordLengths = new HashSet<Integer>();
		ArrayList<PlaceHolder> listOfPlaceHolders = new ArrayList<PlaceHolder>();
		try {
			// check for the first line providing row , column size and number of words.
			inputLines = stream.readLine().trim();
			if (inputLines.matches("([\\d]+[\\s]*){3}")) {
				String[] dimensions = inputLines.split("[\\s]+");

				// Initializing variable based on th input.
				colSize = Integer.parseInt(dimensions[0]);
				rowSize = Integer.parseInt(dimensions[1]);
				wordCount = Integer.parseInt(dimensions[2]);
				result = true;
			} else
				result = false;

			// check if first line of data provided is correct
			if (result) {
				
				// if first line of data is correct, iterate to get in number of the Placeholder
				// as equal to the word count provided.
				for (int i = 0; i < wordCount; i++) {
					inputLines = stream.readLine().trim();
					if (inputLines.matches("([\\d]+\\s){3}[hv]{1}")) {
						String[] puzzleDetails = inputLines.split("[\\s]+");

						// taking x and y coordinate from the input data
						int xCoordinate = Integer.parseInt(puzzleDetails[1]);
						int yCoordinate = Integer.parseInt(puzzleDetails[0]);

						// set it as start popint
						GridPoint startPoint = new GridPoint(xCoordinate, yCoordinate);
						
						// set the size of placeholder
						int size = Integer.parseInt(puzzleDetails[2]);
						wordLengths.add(size);

						// set the orientation/Direction of the placehHolder.
						char orientation = puzzleDetails[3].trim().toLowerCase().charAt(0);
						// Verify that based on the Orientation given PlaceHolder is valid and can be formed.
						if (verifyOrientationInPuzzle(orientation, startPoint, size)) {
							PlaceHolder placeHolder = new PlaceHolder(startPoint, size, orientation);
							listOfPlaceHolders.add(placeHolder);
							result = true;
						} else {
							result = false;
							break;
						}
					} else {
						result = false;
						break;
					}
				}
			}
			// check if data provided for the Placeholder were correct
			if (result) {
				/*
				 * if data provided for the Placeholder were correct, iterate to get in number
				 * of the words as equal to the word count provided.
				 */
				Set<String> setofWords = new HashSet<String>();
				for (int i = 0; i < wordCount; i++) {
					inputLines = stream.readLine().trim().toLowerCase();
					if (inputLines.matches("[a-zA-Z]+") && wordLengths.contains(inputLines.length())) {
						setofWords.add(inputLines);
						for (PlaceHolder temp : listOfPlaceHolders) {
							if (temp.getSize() == inputLines.length()) {
								temp.getPossibleWordslist().add(inputLines);
							}
						}
						result = true;
					} else {
						result = false;
						break;
					}
				}
				/*
				 * once all the Data is collected, Create the CrossWord Puzzle (Finding the
				 * Intersection is Internally taken care by the Crossword once it is created).
				 */

				puzzle = new CrossWordPuzzle(rowSize, colSize, listOfPlaceHolders);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// in case of any exception, return false.
			result = false;
		}
		// return the result to the calling method.
		return result;
	}
	
	
	
	
	
	/**
	 * @return boolean (true or False  
	 * 					- If with the Data provided, puzzle is solved then return true,
	 * 					  return false, otherwise
	 * 
	 */
	public boolean solve()
	{
		boolean result= false;
		try {
			
			ArrayList<PlaceHolder> listofPlaceHolders = puzzle.getListOfPlaceHolders();
			/*
			 * Stack<PlaceHolder> placeHolders = new Stack<PlaceHolder>(); for(PlaceHolder p
			 * : listofPlaceHolders) { placeHolders.push(p); }
			 */
		
			ArrayList<PlaceHolder> placeHoldersList = new ArrayList<PlaceHolder>();
			placeHoldersList.addAll(listofPlaceHolders);
			
			// Calling a method to process the crossword Puzzle for all the Place Holders.
			Character[][] c = solvePuzzle(puzzle.getCrosswordPuzzle(),placeHoldersList);
			
			//if puzzle is Solved the Character Array (Solved puzzle is returned),otherwise null
			if(c!=null) {
				puzzle.setCrosswordPuzzle(c);
				result = true;
			}
		}
		catch(Exception e) {
			result=false;
		}
		
		// returning result
		return result;
	}
	
	
	
	
	
	/**
	 * @return Character[][] array (  
	 * 					- If with the Data provided, puzzle is solved then return true,
	 * 					  return solved puzzle, null otherwise
	 * 
	 */
	Character[][] solvePuzzle(Character[][] matrix, ArrayList<PlaceHolder> placeholders) {

		// Checking if the List of placeholder have elements.
		if (placeholders.isEmpty())
			return matrix;

		ArrayList<PlaceHolder> tempplaceholder = new ArrayList<PlaceHolder>();
		tempplaceholder.addAll(placeholders);

		// taking a Placeholder out of the List from the End
		PlaceHolder pl = tempplaceholder.get(tempplaceholder.size() - 1);

		// Removing that PlaceHolder from the list
		tempplaceholder.remove(pl);

		// Adding that Place holder in the Visited Set.
		visitedPlaceHolder.add(pl);

		// Getting all the Intersection of the PlaceHolder
		for (Intersection intersection : puzzle.getSetOfIntersections()) {
			
			// For Any Intersection with teh Current Placeholder
			if (intersection.getFirst().equals(pl)) {
				PlaceHolder p2 = intersection.getSecond();
				
				// Checking if the other Placeholder forming the Intersection is already Visited
				if (!visitedPlaceHolder.contains(p2)) {
					
					// Moving the second Placeholder forming the Intersection, at the end in the
					// Placeholder's list.
					tempplaceholder.remove(p2);
					tempplaceholder.add(p2);
				}
			}
		}

		// Iterating through the possible number of words which can be filled in the
		// PlaceHolder.
		for (String word : pl.getPossibleWordslist()) {
			if (!visited.contains(word)) {
				// Calling Method 'fill' to fill in the placeholder with the word
				Character[][] possibleC = fill(matrix, word, pl);
				// if word is successfully placed in the placeholder
				if (possibleC != null) {
					totalChoices++;
					// add the word in the visited set
					visited.add(word);

					// call the SolvePuzzle Method recursively with new list of Placeholders.
					Character[][] ret = solvePuzzle(possibleC, tempplaceholder);

					// if the return is not null from the recursive call.
					if (ret != null) {
						return ret;
					} else {
						/*
						 * if the return is null from the recursive call. increase the count of undone
						 * and remove the word from the visited set */
						choices++;
						visited.remove(word);
					}
				}
			}
		}
		/* if none of the words are matched , return null and remove the current
		 placeholder from the visited set.*/
		visitedPlaceHolder.remove(pl);
		return null;
	}
	
	
	
	
	
	/**
	 * @return Character[][] array (  
	 * 					- If with the Data provided, placeholder is filled with the word,
	 * 					  return puzzle with filled placeholder, null otherwise
	 * 
	 */
	Character[][] fill(Character[][] matrix, String word, PlaceHolder pl) {

		Character[][] c = new Character[matrix.length][matrix[0].length];
		if (matrix != null) {
			for (int i = 0; i < matrix.length; i++) {
				c[i] = Arrays.copyOf(matrix[i], matrix[i].length);
			}
		}

		// get ting the Startpoint and the size
		int x = pl.getStartPoint().getX();
		int y = pl.getStartPoint().getY();
		int size = pl.getSize();

		// check for the orientation
		if (pl.getOrientation() == 'h') {

			// iterating through the column across the row
			for (int i = y; i < (y + size); i++) { // if the Placeholder places/grid's are empty and contains same
													// character from word at intersection
				if (c[x][i] != ' ' && c[x][i] != word.charAt(i - y)) {
					return null;
				}
			}
			for (int i = y; i < y + size; i++) {
				c[x][i] = word.charAt(i - y);
			}

			return c;

		} else {
			// iterating through the row across the column
			for (int i = x; i > (x - size); i--)

			{
				// if the Placeholder places/grid's are empty and contains same character from
				// word at intersection
				if (c[i][y] != ' ' && c[i][y] != word.charAt(x - i)) {
					return null;
				}
			}
			for (int i = x; i > x - size; i--) {
				c[i][y] = word.charAt(x - i);
			}

			return c;
		}
	}

    
    
	
    /**
	 * @return Boolean
	 * 	Verify that based on the Orientation given PlaceHolder is valid and can be formed.
	 * 
	 */
	public boolean verifyOrientationInPuzzle(Character orientation, GridPoint startPoint, int size) {
		boolean result = false;
		if (orientation == 'h') {
			// If PlaceHolder Size doesn't exceed the Size of the Puzzle.
			result = ((startPoint.getY() + size) <= colSize);
		} else if (orientation == 'v') {
			// If PlaceHolder Size doesn't exceed the Size of the Puzzle.
			result = (((startPoint.getX() + 1) - size) >= 0);
		}
		return result;
	}
	
	
	
	
	
	/**
	 * @return Integer : Return the Number of Choices made which were later undone.
	 */
	public int choices()
	{
		return choices;
	}
	
	
	
	
	/**
	 * Printing the Crossword Puzzle on the PrintWriter .
	 */
	public void print(PrintWriter outstream)
	{
		try {
			Character c[][] = puzzle.getCrosswordPuzzle();
			for (int i = rowSize - 1; i >= 0; i--) {
				for (int j = 0; j < colSize; j++) {
					if (c[i][j] == 'X') {
						//System.out.print(" ");
						outstream.print(" ");
					} else {
						//System.out.print(c[i][j]);
						outstream.print(c[i][j]);
					}
				}
				//System.out.println();
				outstream.print("\n");

			}
			outstream.close();
		} catch (Exception e) {

		}

	}
	
	
	
	// returning total number of choices made
	public int getTotalchoices()
	{
		return totalChoices;
	}
	
	
	// printing on console
	public void printt(Character[][] c)
	{
		String s="";
		for(int i =0 ; i<rowSize ; i++)
		{
			System.out.print("--");
			s= s.concat( "---");
			
		}
		System.out.println();
		s= s.concat("\n");
		for(int i = rowSize-1; i>=0;i--)
		{
			System.out.print("|");
			s= s.concat( "|");
			for(int j=0; j<colSize ; j++)
			{
				System.out.print(c[i][j]);
				s= s.concat( ""+c[i][j]);
				System.out.print("|");
				s= s.concat( "|");
			}
			System.out.println();
			s=s.concat("\n");
			for(int k =0 ; k<rowSize ; k++)
			{
				System.out.print("---");
				s= s.concat( "---");
			}
			System.out.println();
			s= s.concat("\n");	
		}
		//logger.info("\n"+s);
	}
	
	

}

