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

import pl.wasat.smarthma.model.exception.Exception;
import pl.wasat.smarthma.model.exception.ExceptionReport;
import pl.wasat.smarthma.model.exception.ExceptionText;
import pl.wasat.smarthma.model.exception.Fedeo;

/**
 * The type Fedeo exception handler.
 */
class FedeoExceptionHandler extends DefaultHandler {
    private final Fedeo fedeo;
    private StringBuffer chars = new StringBuffer();
    private ExceptionReport exceptionReport;
    private Exception exception;
    private ExceptionText exceptionText;

    /**
     * Instantiates a new Fedeo exception handler.
     */
    public FedeoExceptionHandler() {
        super();
        fedeo = new Fedeo();
    }

    @Override
    public void endDocument() throws SAXException {
        fedeo.setExceptionReport(exceptionReport);
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes atts) throws SAXException {
        super.startElement(uri, localName, qName, atts);

        chars = new StringBuffer();
        if (localName.equalsIgnoreCase("exceptionReport")) {
            exceptionReport = new ExceptionReport();
            exceptionReport.setVersion(atts.getValue("version"));
            exceptionReport.setXmlLang(atts.getValue("xml:Lang"));
            exceptionReport.setXmlnsOws(atts.getValue("xmlns:Ows"));
            exceptionReport.setXmlnsXlink(atts.getValue("xmlns:Xlink"));
            exceptionReport.setXmlnsXsi(atts.getValue("xmlns:Xsi"));
            exceptionReport.setXsiSchemaLocation(atts
                    .getValue("xsi:SchemaLocation"));
        } else if (localName.equalsIgnoreCase("exception")) {
            exception = new Exception();
            exception.setExceptionCode(atts.getValue("exceptionCode"));
            exception.setLocator(atts.getValue("locator"));
        } else if (localName.equalsIgnoreCase("exceptionText")) {
            exceptionText = new ExceptionText();
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        super.endElement(uri, localName, qName);
        if (localName.equalsIgnoreCase("exceptionReport")) {
            exceptionReport.setException(exception);
        } else if (localName.equalsIgnoreCase("exception")) {
            exception.setExceptionText(exceptionText);
        } else if (localName.equalsIgnoreCase("exceptionText")) {
            exceptionText.setText(chars.toString());
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        chars.append(new String(ch, start, length).trim());
        super.characters(ch, start, length);
    }

    /**
     * Gets fedeo exception.
     *
     * @return the fedeo exception
     */
    public Fedeo getFedeoException() {
        return fedeo;

    }

}
