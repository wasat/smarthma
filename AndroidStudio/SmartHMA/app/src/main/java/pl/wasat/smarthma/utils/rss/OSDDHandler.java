package pl.wasat.smarthma.utils.rss;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

import pl.wasat.smarthma.model.osdd.Image;
import pl.wasat.smarthma.model.osdd.OpenSearchDescription;
import pl.wasat.smarthma.model.osdd.Option;
import pl.wasat.smarthma.model.osdd.Parameter;
import pl.wasat.smarthma.model.osdd.Query;
import pl.wasat.smarthma.model.osdd.Url;


class OSDDHandler extends DefaultHandler {

    // Current characters being accumulated
    private StringBuffer chars = new StringBuffer();

    // Number of rss entry added so far
    // private int entryAdded = 0;
    private boolean isInFeed = false;

    private OpenSearchDescription openSearchDescription;
    private String shortName;
    private String longName;
    private String description;
    private ArrayList<Url> urls;
    private Url url;
    private ArrayList<Parameter> parameters;
    private Parameter parameter;
    private ArrayList<Option> options;
    private Option option;
    private ArrayList<Query> queries;
    private Query query;
    private String tags;
    private ArrayList<Image> images;
    private Image image;
    private String developer;
    private String attribution;
    private String syndicationRight;
    private String adultContent;
    private String language;
    private String outputEncoding;
    private String inputEncoding;

    public OpenSearchDescription getOSDD() {
        return openSearchDescription;
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
    public void startElement(String uri, String localName, String qName,
                             Attributes atts) {
        chars = new StringBuffer();
        if (localName.equalsIgnoreCase("OpenSearchDescription")) {
            openSearchDescription = new OpenSearchDescription();
            openSearchDescription.setXmlns(atts.getValue("xmlns"));
            openSearchDescription.setXmlnsDc(atts.getValue("xmlns:dc"));
            openSearchDescription.setXmlnsEo(atts.getValue("xmlns:eo"));
            openSearchDescription.setXmlnsGeo(atts.getValue("xmlns:geo"));
            openSearchDescription.setXmlnsParam(atts.getValue("xmlns:param"));
            openSearchDescription.setXmlnsSru(atts.getValue("xmlns:sru"));
            openSearchDescription.setXmlnsTime(atts.getValue("xmlns:time"));

            urls = new ArrayList<>();
            //parameters = new ArrayList<>();
            queries = new ArrayList<>();
            images = new ArrayList<>();

            //} else if (localName.equalsIgnoreCase("ShortName")) {
            //} else if (localName.equalsIgnoreCase("LongName")) {
            // } else if (localName.equalsIgnoreCase("Description")) {

        } else if (localName.equalsIgnoreCase("Url")) {
            url = new Url();
            url.setRel(atts.getValue("rel"));
            url.setTemplate(atts.getValue("template"));
            url.setType(atts.getValue("type"));
            url.setIndexOffset(atts.getValue("indexOffset"));
            url.setPageOffset(atts.getValue("pageOffset"));
            parameters = new ArrayList<>();
        } else if (localName.equalsIgnoreCase("Parameter")) {
            parameter = new Parameter();
            parameter.setName(atts.getValue("name"));
            parameter.setValue(atts.getValue("value"));
            options = new ArrayList<>();
        } else if (localName.equalsIgnoreCase("Option")) {
            option = new Option();
            option.setLabel(atts.getValue("label"));
            option.setValue(atts.getValue("value"));
        } else if (localName.equalsIgnoreCase("Query")) {
            query = new Query();
            query.setGeoBox(atts.getValue("geo:box"));
            query.setRole(atts.getValue("role"));
            query.setTimeStart(atts.getValue("time:start"));
            query.setTimeEnd(atts.getValue("time:end"));
            //} else if (localName.equalsIgnoreCase("Tags")) {
        } else if (localName.equalsIgnoreCase("Image")) {
            image = new Image();
            image.setHeight(atts.getValue("height"));
            image.setWidth(atts.getValue("width"));
            image.setType(atts.getValue("type"));
            //} else if (localName.equalsIgnoreCase("Developer")) {
            //} else if (localName.equalsIgnoreCase("Attribution")) {
            // } else if (localName.equalsIgnoreCase("SyndicationRight")) {
            // } else if (localName.equalsIgnoreCase("AdultContent")) {
            //} else if (localName.equalsIgnoreCase("Language")) {
            //} else if (localName.equalsIgnoreCase("OutputEncoding")) {
            // } else if (localName.equalsIgnoreCase("InputEncoding")) {

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
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        if (localName.equalsIgnoreCase("OpenSearchDescription")) {
            openSearchDescription.setShortName(shortName);
            openSearchDescription.setLongName(longName);
            openSearchDescription.setDescription(description);
            openSearchDescription.setUrl(urls);
            //openSearchDescription.setParameter(parameters);
            openSearchDescription.setQuery(queries);
            openSearchDescription.setTags(tags);
            openSearchDescription.setImage(images);
            openSearchDescription.setDeveloper(developer);
            openSearchDescription.setAttribution(attribution);
            openSearchDescription.setSyndicationRight(syndicationRight);
            openSearchDescription.setAdultContent(adultContent);
            openSearchDescription.setLanguage(language);
            openSearchDescription.setOutputEncoding(outputEncoding);
            openSearchDescription.setInputEncoding(inputEncoding);
        } else if (localName.equalsIgnoreCase("ShortName")) {
            shortName = chars.toString();
        } else if (localName.equalsIgnoreCase("LongName")) {
            longName = chars.toString();
        } else if (localName.equalsIgnoreCase("Description")) {
            description = chars.toString();
        } else if (localName.equalsIgnoreCase("Url")) {
            url.setParameters(parameters);
            urls.add(url);
        } else if (localName.equalsIgnoreCase("Parameter")) {
            parameter.setOption(options);
            parameters.add(parameter);
        } else if (localName.equalsIgnoreCase("Option")) {
            options.add(option);
        } else if (localName.equalsIgnoreCase("Query")) {
            queries.add(query);
        } else if (localName.equalsIgnoreCase("Tags")) {
            tags = chars.toString();
        } else if (localName.equalsIgnoreCase("Image")) {
            image.setText(chars.toString());
            images.add(image);
        } else if (localName.equalsIgnoreCase("Developer")) {
            developer = chars.toString();
        } else if (localName.equalsIgnoreCase("Attribution")) {
            attribution = chars.toString();
        } else if (localName.equalsIgnoreCase("SyndicationRight")) {
            syndicationRight = chars.toString();
        } else if (localName.equalsIgnoreCase("AdultContent")) {
            adultContent = chars.toString();
        } else if (localName.equalsIgnoreCase("Language")) {
            language = chars.toString();
        } else if (localName.equalsIgnoreCase("OutputEncoding")) {
            outputEncoding = chars.toString();
        } else if (localName.equalsIgnoreCase("InputEncoding")) {
            inputEncoding = chars.toString();
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
    public void characters(char ch[], int start, int length) {
        chars.append(new String(ch, start, length).trim());
    }
}