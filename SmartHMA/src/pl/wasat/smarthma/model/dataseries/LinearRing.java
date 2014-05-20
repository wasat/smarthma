/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package pl.wasat.smarthma.model.dataseries;

/**
 * Class LinearRing.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class LinearRing implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _posList.
     */
    private pl.wasat.smarthma.model.dataseries.PosList _posList;


      //----------------/
     //- Constructors -/
    //----------------/

    public LinearRing() {
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

        if (obj instanceof LinearRing) {

            LinearRing temp = (LinearRing)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._posList != null) {
                if (temp._posList == null) return false;
                if (this._posList != temp._posList) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._posList);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._posList);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._posList); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._posList); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._posList.equals(temp._posList)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._posList);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._posList);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._posList);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._posList);
                    }
                }
            } else if (temp._posList != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'posList'.
     * 
     * @return the value of field 'PosList'.
     */
    public pl.wasat.smarthma.model.dataseries.PosList getPosList(
    ) {
        return this._posList;
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

        if (_posList != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_posList)) {
           result = 37 * result + _posList.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_posList);
        }

        return result;
    }

    /**
     * Sets the value of field 'posList'.
     * 
     * @param posList the value of field 'posList'.
     */
    public void setPosList(
            final pl.wasat.smarthma.model.dataseries.PosList posList) {
        this._posList = posList;
    }

}
