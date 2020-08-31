/**
 * 
 */

/**
 * @author Mayank 
 * Class to implement grid representation of 2d matrix with 
 * x and y value
 */
public class GridPoint {

	/**
	 * representation of X and Y Index
	 */

	private int x;
	private int y;

	/**
	 * default constructor
	 */
	public GridPoint() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param x
	 * @param y
	 */
	public GridPoint(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * overriding hashCode
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	/**
	 * overriding equals
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GridPoint other = (GridPoint) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GridPoint [x=" + x + ", y=" + y + "]";
	}

}
