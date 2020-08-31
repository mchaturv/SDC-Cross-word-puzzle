
/**
 * @author Mayank
 * Class Implementation of the Intersection Point of ant two PlaceHolder 
 * contains both the PlaceHolder object along with Coordinate
 */
public class Intersection {

	/**
	 * @ first : 1st PlaceHolder Object
	 * @ Second : 2nd PlaceHolder object
	 * @ intersectionPoint : Point of intersection
	 */
	private PlaceHolder first;
	private PlaceHolder second;
	private GridPoint intersectionPoint; 
	
	/**
	 * 
	 */
	public Intersection() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param first
	 * @param second
	 * @param intersectionPoint
	 */
	public Intersection(PlaceHolder first, PlaceHolder second, GridPoint intersectionPoint) {
		super();
		this.first = first;
		this.second = second;
		this.intersectionPoint = intersectionPoint;
	}

	/**
	 * @return the first
	 */
	public PlaceHolder getFirst() {
		return first;
	}

	/**
	 * @param first the first to set
	 */
	public void setFirst(PlaceHolder first) {
		this.first = first;
	}

	/**
	 * @return the second
	 */
	public PlaceHolder getSecond() {
		return second;
	}

	/**
	 * @param second the second to set
	 */
	public void setSecond(PlaceHolder second) {
		this.second = second;
	}

	/**
	 * @return the intersectionPoint
	 */
	public GridPoint getIntersectionPoint() {
		return intersectionPoint;
	}

	/**
	 * @param intersectionPoint the intersectionPoint to set
	 */
	public void setIntersectionPoint(GridPoint intersectionPoint) {
		this.intersectionPoint = intersectionPoint;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((intersectionPoint == null) ? 0 : intersectionPoint.hashCode());
		result = prime * result + ((second == null) ? 0 : second.hashCode());
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
		Intersection other = (Intersection) obj;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		if (intersectionPoint == null) {
			if (other.intersectionPoint != null)
				return false;
		} else if (!intersectionPoint.equals(other.intersectionPoint))
			return false;
		if (second == null) {
			if (other.second != null)
				return false;
		} else if (!second.equals(other.second))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Intersection [first=" + first + ", second=" + second + ", intersectionPoint=" + intersectionPoint + "]";
	}
	
	
	

}
