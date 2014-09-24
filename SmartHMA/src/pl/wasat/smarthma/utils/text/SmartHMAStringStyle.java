/**
 * 
 */
package pl.wasat.smarthma.utils.text;

import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Daniel Zinkiewicz Wasat Sp. z o.o 27-06-2014
 * 
 */
public class SmartHMAStringStyle extends ToStringStyle {
	/**
	 * 
	 */
	private static final long serialVersionUID = -109264611603721844L;

	public SmartHMAStringStyle() {// constructor is copied from
									// ToStringStyle.MULTI_LINE_STYLE
		super();

		this.setUseShortClassName(true);
		this.setUseFieldNames(true);
		this.setUseIdentityHashCode(false);
		this.setContentStart("");
		this.setFieldSeparator(SystemUtils.LINE_SEPARATOR);
		this.setFieldSeparatorAtStart(false);
		this.setFieldSeparatorAtEnd(false);
		this.setUseClassName(false);
		this.setContentEnd(SystemUtils.LINE_SEPARATOR);
		this.setArrayEnd("");
		this.setArrayStart("");
		this.setArraySeparator("-");
		this.setFieldNameValueSeparator(": ");

		// this.setUseShortClassName(true);
		// this.setUseIdentityHashCode(false);
		// this.setContentStart("[");
		// this.setFieldSeparator(SystemUtils.LINE_SEPARATOR + "|");
		// this.setFieldSeparatorAtStart(true);
		// this.setContentEnd("]" + SystemUtils.LINE_SEPARATOR);

		// this.setUseShortClassName(true);
		// this.setUseIdentityHashCode(false);
		// this.setContentStart(" - ");
		// this.setFieldSeparator(SystemUtils.LINE_SEPARATOR + "  ");
		// this.setFieldSeparatorAtStart(true);
		// this.setContentEnd(SystemUtils.LINE_SEPARATOR + " ");

	}

	// override this to do checking of null, so only non-nulls are printed out
	// in toString
	@Override
	public void append(StringBuffer buffer, String fieldName, Object value,
			Boolean fullDetail) {

		if (fieldName.equalsIgnoreCase("__text")) {
			fieldName = "value";
		}
		if (!fieldName.equalsIgnoreCase("additionalProperties")
				&& !fieldName.equalsIgnoreCase("_gml_id") && value != null) {
			// buffer.append(ToStringBuilder.reflectionToString(value, this));
			super.append(buffer, fieldName, value, fullDetail);
		}

		//removeLastFieldSeparator(buffer);

	}
}
