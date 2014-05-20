/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package pl.wasat.smarthma.model.dataseries;

/**
 * Class Exterior.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class Exterior implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _linearRing.
     */
    private pl.wasat.smarthma.model.dataseries.LinearRing _linearRing;


      //----------------/
     //- Constructors -/
    //----------------/

    public Exterior() {
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

        if (obj instanceof Exterior) {

            Exterior temp = (Exterior)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._linearRing != null) {
                if (temp._linearRing == null) return false;
                if (this._linearRing != temp._linearRing) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._linearRing);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._linearRing);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._linearRing); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._linearRing); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._linearRing.equals(temp._linearRing)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._linearRing);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._linearRing);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._linearRing);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._linearRing);
                    }
                }
            } else if (temp._linearRing != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'linearRing'.
     * 
     * @return the value of field 'LinearRing'.
     */
    public pl.wasat.smarthma.model.dataseries.LinearRing getLinearRing(
    ) {
        return this._linearRing;
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

        if (_linearRing != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_linearRing)) {
           result = 37 * result + _linearRing.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_linearRing);
        }

        return result;
    }

    /**
     * Sets the value of field 'linearRing'.
     * 
     * @param linearRing the value of field 'linearRing'.
     */
    public void setLinearRing(
            final pl.wasat.smarthma.model.dataseries.LinearRing linearRing) {
        this._linearRing = linearRing;
    }

}
