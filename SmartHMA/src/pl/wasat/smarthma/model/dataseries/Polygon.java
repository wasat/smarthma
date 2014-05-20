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
public class Polygon implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _exterior.
     */
    private pl.wasat.smarthma.model.dataseries.Exterior _exterior;


      //----------------/
     //- Constructors -/
    //----------------/

    public Polygon() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Overrides the java.lang.Object.equals method.
     * 
     * @param obj
     * @return true if the objects are equal.
     */
    @Override()
    public boolean equals(
            final java.lang.Object obj) {
        if ( this == obj )
            return true;

        if (obj instanceof Polygon) {

            Polygon temp = (Polygon)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._exterior != null) {
                if (temp._exterior == null) return false;
                if (this._exterior != temp._exterior) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._exterior);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._exterior);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._exterior); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._exterior); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._exterior.equals(temp._exterior)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._exterior);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._exterior);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._exterior);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._exterior);
                    }
                }
            } else if (temp._exterior != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'exterior'.
     * 
     * @return the value of field 'Exterior'.
     */
    public pl.wasat.smarthma.model.dataseries.Exterior getExterior(
    ) {
        return this._exterior;
    }

    /**
     * Overrides the java.lang.Object.hashCode method.
     * <p>
     * The following steps came from <b>Effective Java Programming
     * Language Guide</b> by Joshua Bloch, Chapter 3
     * 
     * @return a hash code value for the object.
     */
    public int hashCode(
    ) {
        int result = 17;

        if (_exterior != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_exterior)) {
           result = 37 * result + _exterior.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_exterior);
        }

        return result;
    }

    /**
     * Sets the value of field 'exterior'.
     * 
     * @param exterior the value of field 'exterior'.
     */
    public void setExterior(
            final pl.wasat.smarthma.model.dataseries.Exterior exterior) {
        this._exterior = exterior;
    }

}
