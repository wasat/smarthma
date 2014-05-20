/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package pl.wasat.smarthma.model.dataseries;

/**
 * Class Polygon.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class Where implements java.io.Serializable {

	// --------------------------/
	// - Class/Member Variables -/
	// --------------------------/

	/**
	 * Field polygon.
	 */
	private pl.wasat.smarthma.model.dataseries.Polygon polygon;

	// ----------------/
	// - Constructors -/
	// ----------------/

	public Where() {
		super();
	}

	// -----------/
	// - Methods -/
	// -----------/

	/**
	 * Overrides the java.lang.Object.equals method.
	 * 
	 * @param obj
	 * @return true if the objects are equal.
	 */
	@Override()
	public boolean equals(final java.lang.Object obj) {
		if (this == obj)
			return true;

		if (obj instanceof Where) {

			Where temp = (Where) obj;
			boolean thcycle;
			boolean tmcycle;
			if (this.polygon != null) {
				if (temp.polygon == null)
					return false;
				if (this.polygon != temp.polygon) {
					thcycle = org.castor.core.util.CycleBreaker
							.startingToCycle(this.polygon);
					tmcycle = org.castor.core.util.CycleBreaker
							.startingToCycle(temp.polygon);
					if (thcycle != tmcycle) {
						if (!thcycle) {
							org.castor.core.util.CycleBreaker
									.releaseCycleHandle(this.polygon);
						}
						;
						if (!tmcycle) {
							org.castor.core.util.CycleBreaker
									.releaseCycleHandle(temp.polygon);
						}
						;
						return false;
					}
					if (!thcycle) {
						if (!this.polygon.equals(temp.polygon)) {
							org.castor.core.util.CycleBreaker
									.releaseCycleHandle(this.polygon);
							org.castor.core.util.CycleBreaker
									.releaseCycleHandle(temp.polygon);
							return false;
						}
						org.castor.core.util.CycleBreaker
								.releaseCycleHandle(this.polygon);
						org.castor.core.util.CycleBreaker
								.releaseCycleHandle(temp.polygon);
					}
				}
			} else if (temp.polygon != null)
				return false;
			return true;
		}
		return false;
	}

	/**
	 * Returns the value of field 'polygon'.
	 * 
	 * @return the value of field 'Polygon'.
	 */
	public pl.wasat.smarthma.model.dataseries.Polygon getPolygon() {
		return this.polygon;
	}

	/**
	 * Overrides the java.lang.Object.hashCode method.
	 * <p>
	 * The following steps came from <b>Effective Java Programming Language
	 * Guide</b> by Joshua Bloch, Chapter 3
	 * 
	 * @return a hash code value for the object.
	 */
	public int hashCode() {
		int result = 17;

		if (polygon != null
				&& !org.castor.core.util.CycleBreaker.startingToCycle(polygon)) {
			result = 37 * result + polygon.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(polygon);
		}

		return result;
	}

	/**
	 * Sets the value of field 'polygon'.
	 * 
	 * @param polygon
	 *            the value of field 'polygon'.
	 */
	public void setPolygon(final pl.wasat.smarthma.model.dataseries.Polygon polygon) {
		this.polygon = polygon;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Where [polygon=" + polygon + "]";
	}

}
