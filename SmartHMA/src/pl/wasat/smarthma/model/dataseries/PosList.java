/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id$
 */

package pl.wasat.smarthma.model.dataseries;

/**
 * Class PosList.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class PosList implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * internal content storage
     */
    private java.lang.String _content = "";

    /**
     * Field _srsDimension.
     */
    private java.lang.Byte _srsDimension;


      //----------------/
     //- Constructors -/
    //----------------/

    public PosList() {
        super();
        setContent("");
    }

    public PosList(final java.lang.String defaultValue) {
        try {
            setContent( new java.lang.String(defaultValue));
         } catch(Exception e) {
            throw new RuntimeException("Unable to cast default value for simple content!");
         } 
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

        if (obj instanceof PosList) {

            PosList temp = (PosList)obj;
            boolean thcycle;
            boolean tmcycle;
            if (this._content != null) {
                if (temp._content == null) return false;
                if (this._content != temp._content) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._content);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._content);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._content); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._content); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._content.equals(temp._content)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._content);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._content);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._content);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._content);
                    }
                }
            } else if (temp._content != null)
                return false;
            if (this._srsDimension != null) {
                if (temp._srsDimension == null) return false;
                if (this._srsDimension != temp._srsDimension) {
                    thcycle=org.castor.core.util.CycleBreaker.startingToCycle(this._srsDimension);
                    tmcycle=org.castor.core.util.CycleBreaker.startingToCycle(temp._srsDimension);
                    if (thcycle!=tmcycle) {
                        if (!thcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(this._srsDimension); };
                        if (!tmcycle) { org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._srsDimension); };
                        return false;
                    }
                    if (!thcycle) {
                        if (!this._srsDimension.equals(temp._srsDimension)) {
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(this._srsDimension);
                            org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._srsDimension);
                            return false;
                        }
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(this._srsDimension);
                        org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._srsDimension);
                    }
                }
            } else if (temp._srsDimension != null)
                return false;
            return true;
        }
        return false;
    }

    /**
     * Returns the value of field 'content'. The field 'content'
     * has the following description: internal content storage
     * 
     * @return the value of field 'Content'.
     */
    public java.lang.String getContent(
    ) {
        return this._content;
    }

    /**
     * Returns the value of field 'srsDimension'.
     * 
     * @return the value of field 'SrsDimension'.
     */
    public java.lang.Byte getSrsDimension(
    ) {
        return this._srsDimension;
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

        if (_content != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_content)) {
           result = 37 * result + _content.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_content);
        }
        if (_srsDimension != null
               && !org.castor.core.util.CycleBreaker.startingToCycle(_srsDimension)) {
           result = 37 * result + _srsDimension.hashCode();
           org.castor.core.util.CycleBreaker.releaseCycleHandle(_srsDimension);
        }

        return result;
    }

    /**
     * Sets the value of field 'content'. The field 'content' has
     * the following description: internal content storage
     * 
     * @param content the value of field 'content'.
     */
    public void setContent(
            final java.lang.String content) {
        this._content = content;
    }

    /**
     * Sets the value of field 'srsDimension'.
     * 
     * @param srsDimension the value of field 'srsDimension'.
     */
    public void setSrsDimension(
            final java.lang.Byte srsDimension) {
        this._srsDimension = srsDimension;
    }

}
