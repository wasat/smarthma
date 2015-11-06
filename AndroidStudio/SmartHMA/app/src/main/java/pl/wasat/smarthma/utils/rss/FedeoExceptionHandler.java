package pl.wasat.smarthma.utils.rss;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import pl.wasat.smarthma.model.exception.Exception;
import pl.wasat.smarthma.model.exception.ExceptionReport;
import pl.wasat.smarthma.model.exception.ExceptionText;
import pl.wasat.smarthma.model.exception.Fedeo;

class FedeoExceptionHandler extends DefaultHandler {
    private StringBuffer chars = new StringBuffer();
    private ExceptionReport exceptionReport;
    private Exception exception;
    private ExceptionText exceptionText;
    private final Fedeo fedeo;

    public FedeoExceptionHandler() {
        super();
        fedeo = new Fedeo();
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
    public void endDocument() throws SAXException {
        fedeo.setExceptionReport(exceptionReport);
        super.endDocument();
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        chars.append(new String(ch, start, length).trim());
        super.characters(ch, start, length);
    }

    public Fedeo getFedeoException() {
        return fedeo;

    }

}
