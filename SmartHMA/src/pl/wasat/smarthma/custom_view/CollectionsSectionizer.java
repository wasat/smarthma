/**
 * 
 */
package pl.wasat.smarthma.custom_view;

import pl.wasat.smarthma.model.Collection;

import com.mobsandgeeks.adapters.Sectionizer;

/**
 * @author Wasat Sp. z o.o
 *
 */
public class CollectionsSectionizer implements Sectionizer<Collection> {

	/* (non-Javadoc)
	 * @see com.mobsandgeeks.adapters.Sectionizer#getSectionTitleForItem(java.lang.Object)
	 */
	@Override
	public String getSectionTitleForItem(Collection collection) {
		return collection.getWorkspace();
	}
}