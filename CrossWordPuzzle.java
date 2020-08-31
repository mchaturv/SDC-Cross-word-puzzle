import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 */

/**
 * @author Mayank Class Representation of the CrossWord puzzle consisting of -
 *         Implementation of 2D matrix, - List of all the Placeholder objects of
 *         the puzzle, - Set of all the Intersection objects, - row and col size
 *         of the Crossword
 * 
 */
public class CrossWordPuzzle {

	/**
	 * Attributes of the Crossword puzzle
	 */

	private Character[][] crosswordPuzzle;
	private int rowSize;
	private int colSize;
	private ArrayList<PlaceHolder> listOfPlaceHolders;
	private HashSet<Intersection> setOfIntersections;

	/**
	 * Default Constructor
	 */
	public CrossWordPuzzle() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param rowSize
	 * @param colSize
	 * @param listOfPlaceHolders
	 * @param setOfIntersections
	 */
	public CrossWordPuzzle(int rowSize, int colSize, ArrayList<PlaceHolder> listOfPlaceHolders) {
		super();
		this.rowSize = rowSize;
		this.colSize = colSize;
		this.listOfPlaceHolders = listOfPlaceHolders;
		this.crosswordPuzzle = new Character[this.rowSize][this.colSize];
		for (Character[] row : crosswordPuzzle) {
			Arrays.fill(row, 'X');
		}
		this.setOfIntersections = findIntersection(listOfPlaceHolders);
	}

	/**
	 * @return the crosswordPuzzle
	 */
	public Character[][] getCrosswordPuzzle() {
		return crosswordPuzzle;
	}

	/**
	 * @param crosswordPuzzle the crosswordPuzzle to set
	 */
	public void setCrosswordPuzzle(Character[][] crosswordPuzzle) {
		this.crosswordPuzzle = crosswordPuzzle;
	}

	/**
	 * @return the rowSize
	 */
	public int getRowSize() {
		return rowSize;
	}

	/**
	 * @param rowSize the rowSize to set
	 */
	public void setRowSize(int rowSize) {
		this.rowSize = rowSize;
	}

	/**
	 * @return the colSize
	 */
	public int getColSize() {
		return colSize;
	}

	/**
	 * @param colSize the colSize to set
	 */
	public void setColSize(int colSize) {
		this.colSize = colSize;
	}

	/**
	 * @return the listOfPlaceHolders
	 */
	public ArrayList<PlaceHolder> getListOfPlaceHolders() {
		return listOfPlaceHolders;
	}

	/**
	 * @param listOfPlaceHolders the listOfPlaceHolders to set
	 */
	public void setListOfPlaceHolders(ArrayList<PlaceHolder> listOfPlaceHolders) {
		this.listOfPlaceHolders = listOfPlaceHolders;
	}

	/**
	 * @return the setOfIntersections
	 */
	public Set<Intersection> getSetOfIntersections() {
		return setOfIntersections;
	}

	/**
	 * @param setOfIntersections the setOfIntersections to set
	 */
	public void setSetOfIntersections(HashSet<Intersection> setOfIntersections) {
		this.setOfIntersections = setOfIntersections;
	}

	@Override
	public String toString() {

		return "CrossWordPuzzle [crosswordPuzzle=" + Arrays.toString(crosswordPuzzle) + ", rowSize=" + rowSize
				+ ", colSize=" + colSize + ", listOfPlaceHolders=" + listOfPlaceHolders + ", setOfIntersections="
				+ setOfIntersections + "]";
	}

	
	
	
	/**
	 * @param list of the Placeholder
	 * 
	 *             Method to find the Intersection between the PlaceHolder Object of
	 *             the crossword puzzle
	 */
	public HashSet<Intersection> findIntersection(ArrayList<PlaceHolder> listOfPlaceHolders) {

		HashSet<Intersection> setOfIntersections = new HashSet<Intersection>();
		
		//Iterating through the Placeholder's List
		for (int i = 0; i < listOfPlaceHolders.size() - 1; i++) {
			PlaceHolder first = listOfPlaceHolders.get(i);
			
			// Calling Method to get all the grid point of the First Placeholder
			Set<GridPoint> firstGridSet = getAllGridPoints(first);
			
			Set<GridPoint> tempGridSet = new HashSet<GridPoint>();
			//iterating for the Next placeholder in the List
			for (int j = i + 1; j < listOfPlaceHolders.size(); j++) {
				tempGridSet.addAll(firstGridSet);
				PlaceHolder second = listOfPlaceHolders.get(j);
				
				// Calling Method to get all the grid point of the Second Placeholder
				Set<GridPoint> secondGridSet = getAllGridPoints(second);
				
				// using RetainAll method return the Common Grids (X, Y) coordinate where the placeholder meets, If any Intersection exist
				tempGridSet.retainAll(secondGridSet);

				//If any Intersection Exist
				if (!tempGridSet.isEmpty()) {

					// Fetching that Intersection point and adding it to set of intersection.
					GridPoint dfg = tempGridSet.iterator().next();
					setOfIntersections.add(new Intersection(first, second, dfg));
					setOfIntersections.add(new Intersection(second, first, dfg));

				}
			}
		}

		// return the Set of intersection
		return setOfIntersections;

	}

	/**
	 * @param Placeholder
	 * 
	 *                    Method to find set of the points/grid of the Placeholder
	 *                    object
	 */
	public Set<GridPoint> getAllGridPoints(PlaceHolder placeholder) {
		
		Set<GridPoint> placeHoldergridPoints = new HashSet<GridPoint>();
		int rowindex = placeholder.getStartPoint().getX();
		int columnIndex = placeholder.getStartPoint().getY();
		
		
		// Checking the orientation/Direction
		if (placeholder.getOrientation() == 'h') {
			//Iterating from the Starting point of the placeHolder till PlaceHolder Size permits. 
			for (int i = columnIndex; i < (placeholder.getSize() + columnIndex); i++) {
				placeHoldergridPoints.add(new GridPoint(rowindex, i));
				// Making that value at those Places as " "
					crosswordPuzzle[rowindex][i] = ' ';
				
			}
		} else if (placeholder.getOrientation() == 'v') {
			//Iterating from the Starting point of the placeHolder till PlaceHolder Size permits. 
			for (int i = rowindex; i >= (rowindex - placeholder.getSize() + 1); i--) {
				placeHoldergridPoints.add(new GridPoint(i, columnIndex));
				// Making that value at those Places as " "
					crosswordPuzzle[i][columnIndex] = ' ';
			
			}
		}

		// returning Set of all the grids of the placeholder.
		return placeHoldergridPoints;
	}

}
