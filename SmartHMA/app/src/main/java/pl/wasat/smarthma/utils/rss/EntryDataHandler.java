/*
 * Copyright (c) 2016.  SmartHMA ESA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
class EntryDataHandler extends DefaultHandler {
    private final StringBuffer chars = new StringBuffer();
    private Entry entry;
    private Link link;
    private ArrayList<Link> linksEntry;
    private ArrayList<Link> linksFeed;
    private Group group;
    private Content content;
    private Summary summary;
    private ArrayList<Content> contentList;
    private Category category;

    private EarthObservation earthObservation;

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
            Where where = new Where();
        } else if (localName.equalsIgnoreCase("Polygon")) {
            Polygon polygon = new Polygon();
            polygon.set_gml_id(atts.getValue("gml:id"));
            polygon.set_srsName(atts.getValue("srsName"));
        } else if (localName.equalsIgnoreCase("exterior")) {
            Exterior exterior = new Exterior();
        } else if (localName.equalsIgnoreCase("LinearRing")) {
            LinearRing linearRing = new LinearRing();
            List<Pos> posList = new ArrayList<>();
        } else if (localName.equalsIgnoreCase("poslist")) {
            PosString posString = new PosString();
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

    /**
     * Gets entry.
     *
     * @return the entry
     */
    public Entry getEntry() {
        return entry;
    }
}
