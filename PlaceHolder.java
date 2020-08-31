import java.util.Arrays;
import java.util.HashSet;

/**
 * 
 */

/**
 * @author Mayank
 *	Implementation of PlaceHolder cell i.e all the spaces which are potential to contains the words of the puzzle
 */
public class PlaceHolder {

	/**
	 * @ startPoint : start point of the placeholder (x and y coordinate)
	 * @ size : size of the placeholder :length of the word it can contain
	 * @ direction : Direction of the placeHolder i.e either horizontal or vertical
	 * @ value : Value it will contain
	 * @ possibleWordslist : Potential set of word which can be placed in the placeholder 
	 * 							  length of the word = sixe of the placeholder
	 */
	private GridPoint startPoint;
	private int size;
	private Character orientation;
	private Character[]  value ;
	private HashSet<String> possibleWordslist;
	
	
	
	/**
	 * 
	 */
	public PlaceHolder() {
		// TODO Auto-generated constructor stub
	}



	/**
	 * @param startPoint
	 * @param size
	 * @param direction
	 * @param value
	 * @param possibleWordslist
	 */
	public PlaceHolder(GridPoint startPoint, int size, char direction, Character[] value,
			HashSet<String> possibleWordslist) {
		super();
		this.startPoint = startPoint;
		this.size = size;
		this.orientation = direction;
		this.possibleWordslist = possibleWordslist;
		
	}
	
	



	/**
	 * @param startPoint
	 * @param size
	 * @param direction
	 */
	public PlaceHolder(GridPoint startPoint, int size, char direction) {
		super();
		this.startPoint = startPoint;
		this.size = size;
		this.orientation = direction;
		this.value = new Character[this.size];
		Arrays.fill(value, ' ');
		this.possibleWordslist =  new HashSet<String>();
	}



	/**
	 * @return the startPoint
	 */
	public GridPoint getStartPoint() {
		return startPoint;
	}



	/**
	 * @param startPoint the startPoint to set
	 */
	public void setStartPoint(GridPoint startPoint) {
		this.startPoint = startPoint;
	}



	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}



	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}



	/**
	 * @return the direction
	 */
	public char getOrientation() {
		return orientation;
	}



	/**
	 * @param direction the direction to set
	 */
	public void setOrientation(char orientation) {
		this.orientation = orientation;
	}



	/**
	 * @return the value
	 */
	public Character[] getValue() {
		return value;
	}



	/**
	 * @param value the value to set
	 */
	public void setValue(Character[] value) {
		this.value = value;
	}



	/**
	 * @return the possibleWordslist
	 */
	public HashSet<String> getPossibleWordslist() {
		return possibleWordslist;
	}



	/**
	 * @param possibleWordslist the possibleWordslist to set
	 */
	public void setPossibleWordslist(HashSet<String> possibleWordslist) {
		this.possibleWordslist = possibleWordslist;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orientation == null) ? 0 : orientation.hashCode());
		result = prime * result + ((possibleWordslist == null) ? 0 : possibleWordslist.hashCode());
		result = prime * result + size;
		result = prime * result + ((startPoint == null) ? 0 : startPoint.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlaceHolder other = (PlaceHolder) obj;
		if (orientation == null) {
			if (other.orientation != null)
				return false;
		} else if (!orientation.equals(other.orientation))
			return false;
		if (possibleWordslist == null) {
			if (other.possibleWordslist != null)
				return false;
		} else if (!possibleWordslist.equals(other.possibleWordslist))
			return false;
		if (size != other.size)
			return false;
		if (startPoint == null) {
			if (other.startPoint != null)
				return false;
		} else if (!startPoint.equals(other.startPoint))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "PlaceHolder [startPoint=" + startPoint + ", size=" + size + ", direction=" + orientation + ", value="
				+ value + ", possibleWordslist=" + possibleWordslist + "]";
	}
	
	
	
	
	
	

}
