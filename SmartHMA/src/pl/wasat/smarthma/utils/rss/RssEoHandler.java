package pl.wasat.smarthma.utils.rss;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import pl.wasat.smarthma.model.dataseries.Entry;


public class RssEoHandler extends DefaultHandler {

	// Feed and EO data objects to use for temporary storage
	
	private Entry currentDsEntry = new Entry();
	private List<Entry> entryDsList = new ArrayList<Entry>();

	// Number of rss entry added so far
	private int entryAdded = 0;

	// Number of rss entry to download
	private static final int DATASERIES_LIMIT = 15;

	//Current characters being accumulated
	StringBuffer chars = new StringBuffer();


	public List<Entry> getEntryList() {
		return entryDsList;
	}



	/* 
	 * This method is called everytime a start element is found (an opening XML marker)
	 * here we always reset the characters StringBuffer as we are only currently interested
	 * in the the text values stored at leaf nodes
	 * 
	 * (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	public void startElement(String uri, String localName, String qName, Attributes atts) {
		chars = new StringBuffer();
	}



	/* 
	 * This method is called everytime an end element is found (a closing XML marker)
	 * here we check what element is being closed, if it is a relevant leaf node that we are
	 * checking, such as Title, then we get the characters we have accumulated in the StringBuffer
	 * and set the current metadata title to the value
	 * 
	 * If this is closing the "entry", it means it is the end of the EO data, so we add that to the list
	 * and then reset our EO data object for the next one on the stream
	 * 
	 * 
	 * (non-Javadoc)
	 * @see org.xml.sax.helpers.Default Handler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void endElement(String uri, String localName, String qName) throws SAXException {
		
		if (localName.equalsIgnoreCase("id")){
			currentDsEntry.setId(chars.toString());
		} else if (localName.equalsIgnoreCase("title")){
			currentDsEntry.setTitle(chars.toString());
		} else if (localName.equalsIgnoreCase("published")){
			currentDsEntry.setPublished(chars.toString());
		} else if (localName.equalsIgnoreCase("updated")){
			currentDsEntry.setUpdated(chars.toString());
		} else if (localName.equalsIgnoreCase("date")){
			currentDsEntry.setDate(chars.toString());
		//} else if (localName.equalsIgnoreCase("where")){
		//	currentEntry.setWhere(chars.toString());
		} else if (localName.equalsIgnoreCase("content")){
			currentDsEntry.setEncodedContent(chars.toString());
		} else if (localName.equalsIgnoreCase("summary")){
			currentDsEntry.setEncodedContent(chars.toString());		
		} else if (localName.equalsIgnoreCase("entry")){
			currentDsEntry.setGuid(String.valueOf(entryAdded));
		} 


		// Check if looking for entry, and if entry is complete
		if (localName.equalsIgnoreCase("entry")) {

			entryDsList.add(currentDsEntry);

			currentDsEntry = new Entry();

			// Lets check if we've hit our limit on number of entries
			entryAdded++;
			if (entryAdded >= DATASERIES_LIMIT)
			{
				return;
			}
		}
	}


	/* 
	 * This method is called when characters are found in between XML markers, however, there is no
	 * guarante that this will be called at the end of the node, or that it will be called only once
	 * , so we just accumulate these and then deal with them in endElement() to be sure we have all the
	 * text
	 * 
	 * (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	public void characters(char ch[], int start, int length) {
		chars.append(new String(ch, start, length));
	}
}