package pl.wasat.smarthma.utils.rss;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.model.entry.Entry;
import pl.wasat.smarthma.model.entry.Group;
import pl.wasat.smarthma.model.entry.Summary;
import pl.wasat.smarthma.model.entry.Where;
import pl.wasat.smarthma.model.feed.Link;
import pl.wasat.smarthma.model.om.Category;
import pl.wasat.smarthma.model.om.Content;
import pl.wasat.smarthma.model.om.EarthObservation;
import pl.wasat.smarthma.model.om.Exterior;
import pl.wasat.smarthma.model.om.LinearRing;
import pl.wasat.smarthma.model.om.Polygon;
import pl.wasat.smarthma.model.om.Pos;
import pl.wasat.smarthma.model.om.PosString;

/**
 * Created by Daniel on 2015-10-08 00:16.
 * Part of the project  SmartHMA
 */
public class EntryDataHandler extends DefaultHandler {
    private StringBuffer chars = new StringBuffer();
    private Entry entry;
    private Link link;
    private ArrayList<Link> linksEntry;
    private ArrayList<Link> linksFeed;
    private Where where;
    private Polygon polygon;
    private Exterior exterior;
    private LinearRing linearRing;
    private PosString posString;
    private List<Pos> posList;
    private Group group;
    private Content content;
    private Summary summary;
    private ArrayList<Content> contentList;
    private Category category;

    private EarthObservation earthObservation;

    public Entry getEntry() {
        return entry;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        super.startElement(uri, localName, qName, atts);

        if (localName.equalsIgnoreCase("entry")) {
            entry = new Entry();
            linksEntry = new ArrayList<>();
        } else if (localName.equalsIgnoreCase("link")) {
            link = new Link();
            link.setHref(atts.getValue("href"));
            link.setRel(atts.getValue("rel"));
            link.setTitle(atts.getValue("title"));
            link.setType(atts.getValue("type"));
        } else if (localName.equalsIgnoreCase("summary")) {
            summary = new Summary();
        } else if (localName.equalsIgnoreCase("where")) {
            where = new Where();
        } else if (localName.equalsIgnoreCase("Polygon")) {
            polygon = new Polygon();
            polygon.set_gml_id(atts.getValue("gml:id"));
            polygon.set_srsName(atts.getValue("srsName"));
        } else if (localName.equalsIgnoreCase("exterior")) {
            exterior = new Exterior();
        } else if (localName.equalsIgnoreCase("LinearRing")) {
            linearRing = new LinearRing();
            posList = new ArrayList<>();
        } else if (localName.equalsIgnoreCase("poslist")) {
            posString = new PosString();
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
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);

        if (localName.equalsIgnoreCase("id")) {
            entry.setId(chars.toString());
        } else if (localName.equalsIgnoreCase("link")) {
            linksEntry.add(link);
        } else if (localName.equalsIgnoreCase("published")) {
            entry.setPublished(chars.toString());
        } else if (localName.equalsIgnoreCase("title")) {
            entry.setTitle(chars.toString());
        } else if (localName.equalsIgnoreCase("updated")) {
            entry.setUpdated(chars.toString());
        } else if (localName.equalsIgnoreCase("summary")) {
            summary.setCdata(chars.toString());
            entry.setSummary(summary);
        } else if (localName.equalsIgnoreCase("content")
                && !qName.equalsIgnoreCase("media:content")) {
            summary.setCdata(chars.toString());
            entry.setSummary(summary);
        } else if (localName.equalsIgnoreCase("identifier")) {
            entry.setIdentifier(chars.toString());
        } else if (localName.equalsIgnoreCase("date")) {
            entry.setDate(chars.toString());
        } else if (qName.equalsIgnoreCase("media:group")) {
            group.setContent(contentList);
            entry.setGroup(group);
        } else if (qName.equalsIgnoreCase("media:content")) {
            content.setCategory(category);
            contentList.add(content);
        } else if (qName.equalsIgnoreCase("media:category")) {
            category.set_text(chars.toString());
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        chars.append(new String(ch, start, length).trim());
    }
}
