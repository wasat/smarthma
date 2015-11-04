package pl.wasat.smarthma.model.entry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.model.om.Browse;
import pl.wasat.smarthma.model.om.Content;
import pl.wasat.smarthma.model.om.Footprint;
import pl.wasat.smarthma.model.om.Pos;
import pl.wasat.smarthma.utils.obj.LatLngExt;
import pl.wasat.smarthma.utils.rss.XmlSaxParser;

/**
 * Created by Daniel on 2015-10-10 10:11.
 * Part of the project  SmartHMA
 */
public class SimpleMetadata implements Serializable {
    private static final long serialVersionUID = 1L;

    private ArrayList<LatLngExt> footprint;
    private LatLngExt footprintCenter;
    private String quickLookUrl;
    private String thumbnailUrl;
    private String cloudUrl;

    public SimpleMetadata(Entry entry) {
        processGroupMedia(entry);
        processPolygon(entry);
    }

    public ArrayList<LatLngExt> getFootprint() {
        return footprint;
    }

    public void setFootprint(ArrayList<LatLngExt> footprint) {
        this.footprint = footprint;
    }

    public LatLngExt getFootprintCenter() {
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

    private void processGroupMedia(Entry entry) {
        if (entry.getGroup() != null) {
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

    private void obtainUrlFromDCMetadata(Entry entry) {

    }

    private void obtainUrlsFromISOMetadata(Entry entry) {

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
        String[] corrStrArr = entry.getPolygon().getText().split(" ");
        double lat = 0;
        double lon = 0;
        for (int i = 0; i < corrStrArr.length; i++) {
            if (i % 2 == 0) {
                lat = Double.parseDouble(corrStrArr[i]);
            } else {
                lon = Double.parseDouble(corrStrArr[i]);
                this.footprint.add(new LatLngExt(lat, lon));
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
                break;
            case OM11:
                break;
            case ISO:
                break;
            case DC:
                break;
            default:
                break;
        }
    }
}
