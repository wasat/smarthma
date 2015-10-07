package pl.wasat.smarthma.utils.xml;

import org.acra.ACRA;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import pl.wasat.smarthma.SmartHMApplication;
import pl.wasat.smarthma.model.Collection;
import pl.wasat.smarthma.model.CollectionsGroup;
import pl.wasat.smarthma.model.CollectionsGroup.List;
import pl.wasat.smarthma.model.explaindoc.ConfigInfo;
import pl.wasat.smarthma.model.explaindoc.ExplainData;
import pl.wasat.smarthma.model.explaindoc.Index;
import pl.wasat.smarthma.model.explaindoc.IndexInfo;

public class XMLParser {
    private CollectionsGroup.List collectionGrList;
    private ExplainData expData;

    public XMLParser() {
    }

    /**
     * @param xmlString String to access to xml file
     */
    public void parseXml(String xmlString) {

        IndexInfo indexInfo = null;
        Index index = null;
        ConfigInfo configInfo = null;
        ArrayList<String> supports = null;
        ArrayList<Index> indexes = null;

        CollectionsGroup group = null;
        Collection collectionItem = null;

        expData = new ExplainData();
        collectionGrList = new List();

        Boolean isAfterComment = false;

        String indexTitle = null;

        String text = null;

        int collItemId = 0;
        int groupId = 0;

        XmlPullParserFactory factory;
        XmlPullParser parser;
        try {
            StringReader reader = new StringReader(xmlString);
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            parser = factory.newPullParser();

            parser.setInput(reader);

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagName.equalsIgnoreCase("indexInfo")) {
                            indexInfo = new IndexInfo();
                            indexes = new ArrayList<>();
                        } else if (tagName.equalsIgnoreCase("index")) {
                            index = new Index();
                        } else if (tagName.equalsIgnoreCase("configInfo")) {
                            configInfo = new ConfigInfo();
                            supports = new ArrayList<>();
                        } else if (tagName.equalsIgnoreCase("supports")) {
                            collectionItem = new Collection();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;

                    case XmlPullParser.COMMENT:

                        collectionGrList.addItem(group);
                        collItemId = 0;

                        group = new CollectionsGroup();

                        String standard = parser.getText();
                        standard = standard.replace("/", "");
                        group.setStandard(standard);
                        parser.nextToken();
                        parser.nextToken();

                        String groupName = parser.getText();
                        groupName = groupName.replace("/", "");
                        group.setGroupName(groupName);
                        group.setId(groupId);
                        groupId = groupId + 1;

                        isAfterComment = true;
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagName.equalsIgnoreCase("explain")) {
                            expData.setIndexInfo(indexInfo);
                        } else if (tagName.equalsIgnoreCase("indexInfo")) {
                            indexInfo.setIndexes(indexes);
                        } else if (tagName.equalsIgnoreCase("index")) {
                            indexes.add(index);
                        } else if (tagName.equalsIgnoreCase("title")) {
                            indexTitle = text;
                        } else if (tagName.equalsIgnoreCase("configInfo")) {
                            configInfo.setSupports(supports);
                            index.setConfigInfo(configInfo);
                            if (isAfterComment) {
                                collectionGrList.addItem(group);
                                isAfterComment = false;
                            }
                        } else if (tagName.equalsIgnoreCase("supports")
                                && indexTitle.equalsIgnoreCase("Dataset series")) {
                            supports.add(text);

                            collectionItem.setName(text);
                            collectionItem.setId(collItemId);
                            collItemId = collItemId + 1;

                            if (isAfterComment) {
                                group.addItem(collectionItem);
                            } else {
                                group = new CollectionsGroup();
                                group.setId(groupId);
                                groupId = groupId + 1;
                                group.setGroupName("FEDEO Default");
                                group.setStandard("Default Standard");
                                group.addItem(collectionItem);
                            }
                        }
                        break;

                    default:
                        break;
                }
                eventType = parser.nextToken();
            }

        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
            ACRA.getErrorReporter().handleSilentException(e);
        }

        setDataGlobals();
    }

    private void setDataGlobals() {
        SmartHMApplication.GlobalEODataList = collectionGrList;
        SmartHMApplication.GlobalExplainData = expData;
    }
}
