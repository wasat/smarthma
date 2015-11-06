package pl.wasat.smarthma.utils.rss;

import org.apache.commons.lang3.StringUtils;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pl.wasat.smarthma.helper.enums.MetadataType;
import pl.wasat.smarthma.model.entry.Entry;
import pl.wasat.smarthma.model.entry.Group;
import pl.wasat.smarthma.model.entry.Polygon;
import pl.wasat.smarthma.model.entry.Summary;
import pl.wasat.smarthma.model.entry.Where;
import pl.wasat.smarthma.model.feed.Author;
import pl.wasat.smarthma.model.feed.EOPrefixes;
import pl.wasat.smarthma.model.feed.Feed;
import pl.wasat.smarthma.model.feed.ISOPrefixes;
import pl.wasat.smarthma.model.feed.ItemsPerPage;
import pl.wasat.smarthma.model.feed.Link;
import pl.wasat.smarthma.model.feed.Query;
import pl.wasat.smarthma.model.feed.StartIndex;
import pl.wasat.smarthma.model.feed.TotalResults;
import pl.wasat.smarthma.model.iso.Date;
import pl.wasat.smarthma.model.iso.DateInCIDate;
import pl.wasat.smarthma.model.om.Category;
import pl.wasat.smarthma.model.om.Content;

/**
 * Created by Daniel on 2015-10-08 00:15.
 * Part of the project  SmartHMA
 */
public class FeedDataHandler extends DefaultHandler {

    // Current characters being accumulated
    private StringBuffer chars = new StringBuffer();
    private String[] inStrArrFeed;
    private Locator locator;
    private int startLine;

    // Feed and ISO data objects to use for temporary storage
    private boolean isInFeed = false;
    private boolean isInEntry = false;
    //private boolean isInMDMetadata = false;
    private boolean isInCitation = false;
    private boolean isInCIDate = false;
    private boolean isIdAdded = false;

    private Feed feed;

    private TotalResults totalResults;
    private StartIndex startIndex;
    private ItemsPerPage itemsPerPage;
    private Query query;
    private Author author;
    private String generator;
    private String feedId;
    private String identifierFeed;
    private String title;
    private String updated;
    private Link link;
    private ArrayList<Link> linksFeed;
    private List<Entry> entries;

    private Entry entry;
    private String identifierEntry;
    private ArrayList<Link> linksEntry;
    private Polygon polygon;
    private Date date;
    private DateInCIDate dateInCIDate;
    private Where where;
    private Group group;
    private Content content;
    private ArrayList<Content> contentList;
    private Category category;
    private Summary summary;
    private String rawMetadata;

    private EOPrefixes eoPrefixes;

    public FeedDataHandler(String inStringFeed) {
        this.inStrArrFeed = inStringFeed.split("\n");
    }

    public void setDocumentLocator(Locator locator) {
        this.locator = locator;
    }

    public Feed getFeeds() {
        return feed;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        super.startElement(uri, localName, qName, atts);

        chars = new StringBuffer();
        if (localName.equalsIgnoreCase("feed")) {
            feed = new Feed();
            isInFeed = true;

            ISOPrefixes isoPrefixes = new ISOPrefixes();
            isoPrefixes.setXmlns(atts.getValue("xmlns"));
            isoPrefixes.setXmlnsDc(atts.getValue("xmlns:dc"));
            isoPrefixes.setXmlnsGeo(atts.getValue("xmlns:geo"));
            isoPrefixes.setXmlnsGeorss(atts.getValue("xmlns:georss"));
            isoPrefixes.setXmlnsGml(atts.getValue("xmlns:gml"));
            isoPrefixes.setXmlnsOs(atts.getValue("xmlns:os"));
            isoPrefixes.setXmlnsSemantic(atts.getValue("xmlns:semantic"));
            isoPrefixes.setXmlnsSru(atts.getValue("xmlns:sru"));
            isoPrefixes.setXmlnsTime(atts.getValue("xmlns:time"));
            isoPrefixes.setXmlnsWrs(atts.getValue("xmlns:wrs"));
            isoPrefixes.setXmlnsUrlencoder(atts.getValue("xmlns:urlencoder"));
            isoPrefixes.setXmlnsXlink(atts.getValue("xmlns:xlink"));
            feed.setIsoPrefixes(isoPrefixes);

            entries = new ArrayList<>();
            linksFeed = new ArrayList<>();
        } else if (localName.equalsIgnoreCase("totalResults")) {
            totalResults = new TotalResults();
        } else if (localName.equalsIgnoreCase("startIndex")) {
            startIndex = new StartIndex();
        } else if (localName.equalsIgnoreCase("itemsPerPage")) {
            itemsPerPage = new ItemsPerPage();
        } else if (localName.equalsIgnoreCase("query")) {
            query = new Query();

            ArrayList<String> queryParamNames = new ArrayList<>();
            ArrayList<String> queryParamValues = new ArrayList<>();
            for (int i = 0; i < atts.getLength(); i++) {
                queryParamNames.add(atts.getLocalName(i));
                queryParamValues.add("-  " + atts.getValue(i).replaceAll(",", ", "));
            }
            query.setParamNameList(queryParamNames);
            query.setParamValueList(queryParamValues);

        } else if (localName.equalsIgnoreCase("author")) {
            author = new Author();
            //} else if (localName.equalsIgnoreCase("identifier")) {
            //    identifierFeed = new Identifier();
            //} else if (localName.equalsIgnoreCase("title")) {
            //if (isInCitation) {
            //    citationTitle = new Title();
            //}
        } else if (localName.equalsIgnoreCase("link")) {
            link = new Link();
            link.setHref(atts.getValue("href"));
            link.setRel(atts.getValue("rel"));
            link.setTitle(atts.getValue("title"));
            link.setType(atts.getValue("type"));

            // Entry declarations
        } else if (localName.equalsIgnoreCase("entry")) {
            entry = new Entry();
            isInFeed = false;
            isInEntry = true;
            linksEntry = new ArrayList<>();
            //entry.setXmlLang(atts.getValue("xml:lang"));

            // } else if (localName.equals("date")) {
/*            if (isInCIDate) {
                dateInCIDate = new DateInCIDate();
            } else {
                date = new Date();
            }*/
        } else if (localName.equalsIgnoreCase("summary")) {
            summary = new Summary();
            summary.setType(atts.getValue("xml:lang"));
        } else if (localName.equalsIgnoreCase("Polygon")) {
            polygon = new Polygon();
        } else if (localName.equalsIgnoreCase("where")) {
            where = new Where();
        } else if (localName.equalsIgnoreCase("group")) {
            group = new Group();
            contentList = new ArrayList<>();
        } else if (localName.equalsIgnoreCase("content")) {
            content = new Content();
            content.set_medium(atts.getValue("medium"));
            content.set_type(atts.getValue("type"));
            content.set_url(atts.getValue("url"));
        } else if (localName.equalsIgnoreCase("category")) {
            category = new Category();
            category.set_scheme(atts.getValue("schema"));

            // MDMetadata declarations
            // MI_Metadata declarations
        } else if (localName.equalsIgnoreCase("MD_Metadata")) {
            startRawMetadata();
        } else if (localName.equalsIgnoreCase("MI_Metadata")) {
            startRawMetadata();
        } else if (localName.equalsIgnoreCase("EarthObservation")) {
            startRawMetadata();
        } else if (localName.equalsIgnoreCase("dc")) {
            startRawMetadata();
        }
    }


    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);

        if (isInFeed) {
            if (localName.equalsIgnoreCase("feed")) {
                feed.setTotalResults(totalResults);
                feed.setStartIndex(startIndex);
                feed.setItemsPerPage(itemsPerPage);
                feed.setQuery(query);
                feed.setAuthor(author);
                feed.setGenerator(generator);
                feed.setId(feedId);
                feed.setIdentifier(identifierFeed);
                feed.setTitle(title);
                feed.setUpdated(updated);
                feed.setLink(linksFeed);
                feed.setEntries(entries);
            } else if (localName.equalsIgnoreCase("totalResults")) {
                totalResults.setText(chars.toString());
            } else if (localName.equalsIgnoreCase("startIndex")) {
                startIndex.setText(chars.toString());
            } else if (localName.equalsIgnoreCase("itemsPerPage")) {
                itemsPerPage.setText(chars.toString());
                //} else if (localName.equalsIgnoreCase("query")) {
                //} else if (localName.equalsIgnoreCase("author")) {
                //    author.setNameStr(name);
                // } else if (localName.equalsIgnoreCase("name")) {
                //     name = chars.toString();
            } else if (localName.equalsIgnoreCase("generator")) {
                generator = chars.toString();
            } else if (localName.equalsIgnoreCase("id")) {
                feedId = chars.toString();
            } else if (localName.equalsIgnoreCase("identifier")) {
                identifierFeed = chars.toString();
            } else if (localName.equalsIgnoreCase("title")) {
                title = chars.toString();
            } else if (localName.equalsIgnoreCase("updated")) {
                updated = chars.toString();
            } else if (localName.equalsIgnoreCase("link")) {
                linksFeed.add(link);
            }
        }
        if (isInEntry) {
            if (localName.equalsIgnoreCase("entry")) {
                entry.setIdentifier(identifierEntry);
                isIdAdded = false;
                //entry.setDate(date);
                entry.setPolygon(polygon);
                entry.setSummary(summary);
                entry.setLinks(linksEntry);
                entry.setRawMetadata(rawMetadata);
                //entry.setMDMetadata(mdMetadata);
                entry.generateSimpleMetadata();

                entries.add(entry);
                isInEntry = false;
                isInFeed = true;
            } else if (localName.equalsIgnoreCase("id")) {
                entry.setId(chars.toString());
            } else if (localName.equalsIgnoreCase("title")) {
                if (!isInCitation) {
                    entry.setTitle(chars.toString());
                }
            } else if (localName.equalsIgnoreCase("identifier") && !isIdAdded) {
                if (!isInCitation) {
                    identifierEntry = chars.toString();
                    isIdAdded = true;
                }
            } else if (localName.equalsIgnoreCase("date")) {
                entry.setDate(chars.toString());
            } else if (localName.equalsIgnoreCase("published")) {
                entry.setPublished(chars.toString());
            } else if (localName.equalsIgnoreCase("updated")) {
                entry.setUpdated(chars.toString());
            } else if (localName.equalsIgnoreCase("polygon")) {
                polygon.setText(chars.toString());
            } else if (localName.equalsIgnoreCase("summary")) {
                summary.setCdata(chars.toString());
                entry.setSummary(summary);
            } else if (localName.equalsIgnoreCase("content")
                    && !qName.equalsIgnoreCase("media:content")) {
                summary.setCdata(chars.toString());
                entry.setSummary(summary);
            } else if (localName.equalsIgnoreCase("identifier")) {
                entry.setIdentifier(chars.toString());
            } else if (qName.equalsIgnoreCase("media:group")) {
                group.setContent(contentList);
                entry.setGroup(group);
            } else if (qName.equalsIgnoreCase("media:content")) {
                content.setCategory(category);
                contentList.add(content);
            } else if (qName.equalsIgnoreCase("media:category")) {
                category.set_text(chars.toString());
            } else if (localName.equalsIgnoreCase("link")) {
                linksEntry.add(link);
            }

            // EO MetaData
            else if (localName.equalsIgnoreCase("MD_Metadata")) {
                rawMetadata = buildRawMetadata(inStrArrFeed, startLine - 1, locator.getLineNumber());
                entry.setMetadataType(MetadataType.ISO);
            } else if (localName.equalsIgnoreCase("MI_Metadata")) {
                rawMetadata = buildRawMetadata(inStrArrFeed, startLine - 1, locator.getLineNumber());
                entry.setMetadataType(MetadataType.ISO);
            } else if (localName.equalsIgnoreCase("EarthObservation")) {
                rawMetadata = buildRawMetadata(inStrArrFeed, startLine - 1, locator.getLineNumber());
                entry.setMetadataType(MetadataType.OM);
            } else if (localName.equalsIgnoreCase("dc")) {
                rawMetadata = buildRawMetadata(inStrArrFeed, startLine - 1, locator.getLineNumber());
                entry.setMetadataType(MetadataType.DC);
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        chars.append(new String(ch, start, length).trim());
    }

    private void startRawMetadata() {
        startLine = locator.getLineNumber();
    }

/*    private void endRawMetadata() {
        int endLine = locator.getLineNumber();
        rawMetadata = substringLine(inStrArrFeed, startLine - 1, endLine);
    }

    private String substringLineOM(String[] strArray, int startLine, int endLine) {
        String[] subString = Arrays.copyOfRange(strArray, startLine, endLine);
        return StringUtils.join(subString, "\n");
    }

    private String substringLine(String[] strArray, int startLine, int endLine) {
        String[] subString = Arrays.copyOfRange(strArray, startLine, endLine);
        String[] feedLine = inStrArrFeed[1].split(" ");
        String metaName = subString[0].split(" ")[0];
        feedLine[0] = metaName;
        String firstLine = StringUtils.join(feedLine, " ");
        subString[0] = firstLine;
        return StringUtils.join(subString, "\n");
    }*/

    private String buildRawMetadata(String[] strArray, int startLine, int endLine) {
        String[] metadataStrArr = Arrays.copyOfRange(strArray, startLine, endLine);
        String metadataStart = metadataStrArr[0].replace(">", "");

        int div = inStrArrFeed[1].indexOf(" ");
        String feedDef = inStrArrFeed[1].substring(div);

        String metadataFirstLine = metadataStart + feedDef;
        metadataStrArr[0] = validateNamespace(metadataFirstLine);
        return StringUtils.join(metadataStrArr, "\n");
    }

    public static String validateNamespace(String txt) {
        boolean containAttr = false;
        List<String> values = new ArrayList<>();
        String[] splitTag = txt.split(" ");

        values.add(splitTag[0]);

        for (int i = 1; i < splitTag.length; ++i) {
            String namespace = splitTag[i].split("=")[0];
            for (String value : values) {
                String valueNamespace = value.split("=")[0];
                if (namespace.equalsIgnoreCase(valueNamespace)) {
                    containAttr = true;
                    break;
                }
            }
            if (!containAttr) {
                values.add(splitTag[i]);
            }
            containAttr = false;
        }
        return StringUtils.join(values, " ");

    }

}
