package pl.wasat.smarthma.model.entry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.SmartHMApplication;
import pl.wasat.smarthma.model.feed.Link;
import pl.wasat.smarthma.model.om.Browse;
import pl.wasat.smarthma.model.om.Content;
import pl.wasat.smarthma.model.om.Footprint;
import pl.wasat.smarthma.model.om.Pos;
import pl.wasat.smarthma.preferences.GlobalPreferences;
import pl.wasat.smarthma.utils.obj.LatLngExt;
import pl.wasat.smarthma.utils.rss.XmlSaxParser;

/**
 * Created by Daniel on 2015-10-10 10:11.
 * Part of the project  SmartHMA
 */
public class SimpleMetadata implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String URL_TEST_ZIP = "http://67.20.63.5/test.zip";

    private ArrayList<LatLngExt> footprint;
    private LatLngExt footprintCenter;
    private String quickLookUrl;
    private String thumbnailUrl;
    private String cloudUrl;
    private String binaryUrl;
    private Entry entry;

    public SimpleMetadata(Entry entry) {
        this.entry = entry;
        processGroupMedia(entry);
        processBinaryFileUrl(entry);
        processPolygon(entry);
    }


    public ArrayList<LatLngExt> getFootprint() {
        return footprint;
    }

    public void setFootprint(ArrayList<LatLngExt> footprint) {
        this.footprint = footprint;
    }

    public LatLngExt getFootprintCenter() {
        processFootprintCenter(entry);
        return footprintCenter;
    }

    public void setFootprintCenter(LatLngExt footprintCenter) {
        this.footprintCenter = footprintCenter;
    }

    public String getQuickLookUrl() {
        return quickLookUrl;
    }

    public void setQuickLookUrl(String quickLookUrl) {
        this.quickLookUrl = quickLookUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getCloudUrl() {
        return cloudUrl;
    }

    public void setCloudUrl(String cloudUrl) {
        this.cloudUrl = cloudUrl;
    }

    public String getBinaryUrl() {
        return binaryUrl;
    }

    public void setBinaryUrl(String binaryUrl) {
        this.binaryUrl = binaryUrl;
    }

    private void processGroupMedia(Entry entry) {
        if (entry.getGroup().getContent().size() > 0) {
            obtainUrlsFromEntry(entry);
        } else if (entry.getEarthObservation() != null) {
            obtainUrlsFromOMMetadata(entry);
        } else if (entry.getMDMetadata() != null) {
            obtainUrlsFromISOMetadata(entry);
        } else if (entry.getDc() != null) {
            obtainUrlFromDCMetadata(entry);
        } else {
            obtainDataFromRawMetadata(entry);
        }
        validateThumbsUrl();
        validateURLsForming();
    }


    private void processBinaryFileUrl(Entry entry) {
        if (entry.getLinks() != null && !entry.getLinks().isEmpty()) {
            for (Link link : entry.getLinks()) {
                if (link.getType().equalsIgnoreCase("application/x-binary")) {
                    binaryUrl = link.getHref();
                    return;
                } else if (binaryUrl == null && link.getTitle().equalsIgnoreCase("Download")) {
                    binaryUrl = link.getHref();
                }
            }
        } else {
            obtainUrlsFromISOMetadata(entry);
        }
        validateBinaryUrl();
    }

    private void validateBinaryUrl() {

        GlobalPreferences globalPreferences = new GlobalPreferences(SmartHMApplication.getAppContext());
        if (binaryUrl != null) return;
        else if (quickLookUrl != null) binaryUrl = quickLookUrl;
        else if (globalPreferences.getIsDebugMode()) binaryUrl = URL_TEST_ZIP;
    }

    private void processPolygon(Entry entry) {
        footprint = new ArrayList<>();
        if (entry.getPolygon() != null) {
            obtainPolygonFromEntry(entry);
        } else if (entry.getEarthObservation() != null) {
            obtainPolygonFromOMMetadata(entry);
        } else if (entry.getMDMetadata() != null) {
            obtainPolygonFromISOMetadata(entry);
        } else if (entry.getDc() != null) {
            obtainPolygonFromDCMetadata(entry);
        } else {
            obtainDataFromRawMetadata(entry);
        }
    }

    private void processFootprintCenter(Entry entry) {
        if (footprintCenter != null) return;
        else if (entry == null) {
            this.footprintCenter = new LatLngExt(null, null);
        } else if (entry.getEarthObservation() != null) {
            obtainFootprintCenterFromOMMetadata(entry);
        } else if (entry.getMDMetadata() != null) {
            obtainFootprintCenterFromISOMetadata(entry);
        } else if (entry.getDc() != null) {
            obtainFootprintCenterFromDCMetadata(entry);
        } else {
            obtainDataFromRawMetadata(entry);
        }
        validateFootprintCenter();
    }


    private void obtainFootprintCenterFromOMMetadata(Entry entry) {
        if (entry.getEarthObservation().getFeatureOfInterest().getFootprint().getCenterOf() != null) {
            this.footprintCenter = entry.getEarthObservation().getFeatureOfInterest()
                    .getFootprint().getCenterOf().getPoint().getPos().getLatLng();
        }
    }

    private void obtainFootprintCenterFromDCMetadata(Entry entry) {

    }

    private void obtainFootprintCenterFromISOMetadata(Entry entry) {

    }

    private void validateFootprintCenter() {
        if (footprintCenter != null) return;
        else if (footprintCenter == null && footprint.size() > 0) {
            double lat = 0;
            double lng = 0;
            for (int i = 0; i < 4; i++) {
                lat = lat + footprint.get(i).latitude;
                lng = lng + footprint.get(i).longitude;
            }
            footprintCenter = new LatLngExt(lat / 4, lng / 4);
        } else {
            this.footprintCenter = new LatLngExt(0, 0);
        }

    }

    private void obtainUrlsFromOMMetadata(Entry entry) {
        List<Browse> browseList = entry.getEarthObservation().getResult().getEarthObservationResult().getBrowseList();
        for (Browse browse : browseList) {
            if (browse.getBrowseInformation().getType().get_text()
                    .equalsIgnoreCase("QUICKLOOK")) {
                this.quickLookUrl = browse.getBrowseInformation().getFileName()
                        .getServiceReference().get_xlink_href();
            } else if (browse.getBrowseInformation().getType().get_text()
                    .equalsIgnoreCase("THUMBNAIL")) {
                this.thumbnailUrl = browse.getBrowseInformation().getFileName()
                        .getServiceReference().get_xlink_href();
            } else if (browse.getBrowseInformation().getType().get_text()
                    .equalsIgnoreCase("CLOUD")) {
                this.cloudUrl = browse.getBrowseInformation().getFileName()
                        .getServiceReference().get_xlink_href();
            }
        }
    }

    private void obtainUrlsFromEntry(Entry entry) {
        List<Content> content = entry.getGroup().getContent();
        for (Content cont : content) {
            if (cont.getCategory().get_text().equalsIgnoreCase("QUICKLOOK")) {
                this.quickLookUrl = cont.get_url();
            } else if (cont.getCategory().get_text().equalsIgnoreCase("THUMBNAIL")) {
                this.thumbnailUrl = cont.get_url();
            } else if (cont.getCategory().get_text().equalsIgnoreCase("CLOUD")) {
                this.cloudUrl = cont.get_url();
            }
        }
    }

    //TODO - process DC metadata
    private void obtainUrlFromDCMetadata(Entry entry) {

    }

    //TODO - process ISO metadata
    private void obtainUrlsFromISOMetadata(Entry entry) {

    }

    private void validateThumbsUrl() {
        if (thumbnailUrl == null && quickLookUrl != null)
            thumbnailUrl = quickLookUrl;
    }

    private void validateURLsForming() {
        try {
            if (thumbnailUrl != null && thumbnailUrl.startsWith("//"))
                thumbnailUrl = "http:" + thumbnailUrl;
            if (quickLookUrl != null && quickLookUrl.startsWith("//"))
                quickLookUrl = "http:" + quickLookUrl;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void obtainPolygonFromOMMetadata(Entry entry) {
        Footprint footprintRaw = entry.getEarthObservation()
                .getFeatureOfInterest().getFootprint();
        List<Pos> footprintPosList = footprintRaw.getMultiExtentOf()
                .getMultiSurface().getSurfaceMembers().getPolygon()
                .getExterior().getLinearRing().getPosList();
        if (footprintPosList.isEmpty()) {
            String posStr = footprintRaw.getMultiExtentOf().getMultiSurface()
                    .getSurfaceMembers().getPolygon().getExterior()
                    .getLinearRing().getPosString().getPointsString();
            footprintPosList = footprintRaw.getMultiExtentOf().getMultiSurface()
                    .getSurfaceMembers().getPolygon().getExterior()
                    .getLinearRing().setPosList(posStr);
        }
        for (Pos footprintPos : footprintPosList) {
            this.footprint.add(footprintPos.getLatLng());
        }
    }

    private void obtainPolygonFromEntry(Entry entry) {
        String polygonStr = entry.getPolygon().getText().replaceAll("  ", " ");
        String[] corrStrArr = polygonStr.split(" ");
        double lat = 0;
        double lon;
        for (int i = 0; i < corrStrArr.length; i++) {
            try {
                if (i % 2 == 0) {
                    lat = Double.parseDouble(corrStrArr[i]);
                } else {
                    lon = Double.parseDouble(corrStrArr[i]);
                    this.footprint.add(new LatLngExt(lat, lon));
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    private void obtainPolygonFromDCMetadata(Entry entry) {

    }

    private void obtainPolygonFromISOMetadata(Entry entry) {

    }

    private void obtainDataFromRawMetadata(Entry entry) {
        XmlSaxParser xmlSaxParser = new XmlSaxParser();
        switch (entry.getMetadataType()) {
            case OM:
                xmlSaxParser.parseOMMetadata(entry);
                processGroupMedia(entry);
                processPolygon(entry);
                processFootprintCenter(entry);
                break;
            case OM11:
                xmlSaxParser.parseOMMetadata(entry);
                break;
            case ISO:
                xmlSaxParser.parseISOMetadata(entry);
                break;
            case DC:
                xmlSaxParser.parseDCMetadata(entry);
                break;
            default:
                break;
        }
    }
}
