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

package pl.wasat.smarthma.utils.xml;

import android.util.Xml;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.xmlpull.v1.XmlSerializer;

import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.model.entry.Entry;
import pl.wasat.smarthma.model.feed.Link;
import pl.wasat.smarthma.model.opml.Body;
import pl.wasat.smarthma.model.opml.Head;
import pl.wasat.smarthma.model.opml.Opml;
import pl.wasat.smarthma.model.opml.Outline;

/**
 * Created by Daniel on 2016-02-22.
 * This file is a part of module SmartHMA project.
 */
public class OpmlBuilder {

    /**
     * Build xml string.
     *
     * @param opml the opml
     * @return the string
     */
    public String buildXml(Opml opml) {
        XmlSerializer serializer = Xml.newSerializer();
        StringWriter writer = new StringWriter();
        List<Outline> outlines = opml.getBody().getOutline();
        try {
            serializer.setOutput(writer);
            serializer.startDocument("UTF-8", true);
            serializer.startTag("", Opml.class.getSimpleName());
            serializer.attribute("", "version", opml.getVersion());

            serializer.startTag("", Head.class.getSimpleName());
            for (Field headField : opml.getHead().getClass().getDeclaredFields()) {
                serializer.startTag("", headField.getName());
                String value = String.valueOf(FieldUtils.readField(opml.getHead(), headField.getName(), true));
                serializer.text(value);
                serializer.endTag("", headField.getName());
            }
            serializer.endTag("", Head.class.getSimpleName());

            serializer.startTag("", Body.class.getSimpleName());
            for (Outline outline : outlines) {
                serializer.startTag("", Outline.class.getSimpleName());
                for (Field field : Outline.class.getDeclaredFields()) {
                    String value = String.valueOf(FieldUtils.readField(outline, field.getName(), true));
                    serializer.attribute("", field.getName(), value);
                }
                serializer.endTag("", Outline.class.getSimpleName());
            }
            serializer.endTag("", Body.class.getSimpleName());
            serializer.endTag("", Opml.class.getSimpleName());
            serializer.endDocument();
            //String xml = writer.toString();
            //Log.i("XML", xml);
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Create opml obj opml.
     *
     * @param entryList the entry list
     * @return the opml
     */
    public Opml createOpmlObj(ArrayList<Entry> entryList) {
        Opml opml = new Opml();

        Head head = new Head();
        opml.setHead(head);

        Body body = new Body();
        ArrayList<Outline> outlines = new ArrayList<>();

        for (Entry entry : entryList) {
            Outline outline = new Outline();
            outline.setTitle(entry.getTitle());
            outline.setDescription("Product description");
            outline.setHtmlUrl(Link.REL_ALTERNATE);
            outline.setLanguage("unknown");
            outline.setText(entry.getIdentifier());
            outline.setType(Link.TYPE_ATOM_XML);
            outline.setVersion("RSS2");
            outline.setXmlUrl(entry.getId());
            outlines.add(outline);
        }

        body.setOutline(outlines);
        opml.setBody(body);
        opml.setVersion("2.0");

        return opml;

        //writeOpmlXml(opml);
    }
}
