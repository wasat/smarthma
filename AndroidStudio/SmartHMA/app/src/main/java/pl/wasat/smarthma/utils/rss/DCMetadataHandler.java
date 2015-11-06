package pl.wasat.smarthma.utils.rss;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import pl.wasat.smarthma.model.dc.Contributor;
import pl.wasat.smarthma.model.dc.Coverage;
import pl.wasat.smarthma.model.dc.Creator;
import pl.wasat.smarthma.model.dc.Date;
import pl.wasat.smarthma.model.dc.Dc;
import pl.wasat.smarthma.model.dc.Description;
import pl.wasat.smarthma.model.dc.Format;
import pl.wasat.smarthma.model.dc.Identifier;
import pl.wasat.smarthma.model.dc.Language;
import pl.wasat.smarthma.model.dc.Publisher;
import pl.wasat.smarthma.model.dc.Relation;
import pl.wasat.smarthma.model.dc.Rights;
import pl.wasat.smarthma.model.dc.Source;
import pl.wasat.smarthma.model.dc.Subject;
import pl.wasat.smarthma.model.dc.Title;
import pl.wasat.smarthma.model.dc.Type;

public class DCMetadataHandler extends DefaultHandler {

    // Current characters being accumulated
    private StringBuffer chars = new StringBuffer();

    // DC metadata objects to use for temporary storage
    private Dc dc;
    private Title title;
    private Creator creator;
    private Subject subject;
    private Description description;
    private Publisher publisher;
    private Contributor contributor;
    private Date date;
    private Type type;
    private Format format;
    private Identifier identifier;
    private Source source;
    private Language language;
    private Relation relation;
    private Coverage coverage;
    private Rights rights;

    public Dc getDC() {
        return dc;
    }

    /*
     * This method is called everytime a start element is found (an opening XML
     * marker) here we always reset the characters StringBuffer as we are only
     * currently interested in the the text values stored at leaf nodes
     *
     * (non-Javadoc)
     *
     * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
     * java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes atts) throws SAXException {
        super.startElement(uri, localName, qName, atts);
        chars = new StringBuffer();

        // DC declarations
        if (localName.equalsIgnoreCase("dc")) {
            dc = new Dc();
            dc.setXmlnsSrwDc(atts.getValue("xmlns:srw_dc"));
            dc.setXmlnsXsi(atts.getValue("xmlns:xsi"));
            dc.setXsiSchemaLocation(atts.getValue("xsi:schemaLocation"));
        } else if (localName.equalsIgnoreCase("title")) {
            title = new Title();
        } else if (localName.equalsIgnoreCase("creator")) {
            creator = new Creator();
        } else if (localName.equalsIgnoreCase("subject")) {
            subject = new Subject();
        } else if (localName.equalsIgnoreCase("description")) {
            description = new Description();
        } else if (localName.equalsIgnoreCase("publisher")) {
            publisher = new Publisher();
        } else if (localName.equalsIgnoreCase("contributor")) {
            contributor = new Contributor();
        } else if (localName.equalsIgnoreCase("date")) {
            date = new Date();
        } else if (localName.equalsIgnoreCase("type")) {
            type = new Type();
        } else if (localName.equalsIgnoreCase("format")) {
            format = new Format();
        } else if (localName.equalsIgnoreCase("identifier")) {
            identifier = new Identifier();
        } else if (localName.equalsIgnoreCase("source")) {
            source = new Source();
        } else if (localName.equalsIgnoreCase("language")) {
            language = new Language();
        } else if (localName.equalsIgnoreCase("relation")) {
            relation = new Relation();
        } else if (localName.equalsIgnoreCase("coverage")) {
            coverage = new Coverage();
        } else if (localName.equalsIgnoreCase("rights")) {
            rights = new Rights();
        }
    }

    /*
     * This method is called everytime an end element is found (a closing XML
     * marker) here we check what element is being closed, if it is a relevant
     * leaf node that we are checking, such as Title, then we get the characters
     * we have accumulated in the StringBuffer and set the current metadata
     * title to the value
     *
     * If this is closing the "entry", it means it is the end of the EO data, so
     * we add that to the list and then reset our EO data object for the next
     * one on the stream
     *
     *
     * (non-Javadoc)
     *
     * @see org.xml.sax.helpers.Default Handler#endElement(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        super.endElement(uri, localName, qName);

        if (localName.equalsIgnoreCase("dc")) {
            dc.setTitle(title);
            dc.setCreator(creator);
            dc.setSubject(subject);
            dc.setDescription(description);
            dc.setPublisher(publisher);
            dc.setContributor(contributor);
            dc.setDate(date);
            dc.setType(type);
            dc.setFormat(format);
            dc.setIdentifier(identifier);
            dc.setSource(source);
            dc.setLanguage(language);
            dc.setRelation(relation);
            dc.setCoverage(coverage);
            dc.setRights(rights);
        } else if (localName.equalsIgnoreCase("title")) {
            title.setText(chars.toString());
        } else if (localName.equalsIgnoreCase("creator")) {
            creator.setText(chars.toString());
        } else if (localName.equalsIgnoreCase("subject")) {
            subject.setText(chars.toString());
        } else if (localName.equalsIgnoreCase("description")) {
            description.setText(chars.toString());
        } else if (localName.equalsIgnoreCase("publisher")) {
            publisher.setText(chars.toString());
        } else if (localName.equalsIgnoreCase("contributor")) {
            contributor.setText(chars.toString());
        } else if (localName.equalsIgnoreCase("date")) {
            date.setText(chars.toString());
        } else if (localName.equalsIgnoreCase("type")) {
            type.setText(chars.toString());
        } else if (localName.equalsIgnoreCase("format")) {
            format.setText(chars.toString());
        } else if (localName.equalsIgnoreCase("identifier")) {
            identifier.setText(chars.toString());
        } else if (localName.equalsIgnoreCase("source")) {
            source.setText(chars.toString());
        } else if (localName.equalsIgnoreCase("language")) {
            language.setText(chars.toString());
        } else if (localName.equalsIgnoreCase("relation")) {
            relation.setText(chars.toString());
        } else if (localName.equalsIgnoreCase("coverage")) {
            coverage.setText(chars.toString());
        } else if (localName.equalsIgnoreCase("rights")) {
            rights.setText(chars.toString());
        }

    }

    /*
     * This method is called when characters are found in between XML markers,
     * however, there is no guarantee that this will be called at the end of the
     * node, or that it will be called only once , so we just accumulate these
     * and then deal with them in endElement() to be sure we have all the text
     *
     * (non-Javadoc)
     *
     * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        chars.append(new String(ch, start, length).trim());
    }
}