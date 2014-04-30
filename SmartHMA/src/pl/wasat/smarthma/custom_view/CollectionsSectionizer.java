/**
 * 
 */
package pl.wasat.smarthma.custom_view;

import pl.wasat.smarthma.model.Collection_old;

import com.mobsandgeeks.adapters.Sectionizer;

/**
 * @author Wasat Sp. z o.o
 *
 */
public class CollectionsSectionizer implements Sectionizer<Collection_old> {

	/* (non-Javadoc)
	 * @see com.mobsandgeeks.adapters.Sectionizer#getSectionTitleForItem(java.lang.Object)
	 */
	@Override
	public String getSectionTitleForItem(Collection_old collection) {
		return collection.getWorkspace();
	}
}