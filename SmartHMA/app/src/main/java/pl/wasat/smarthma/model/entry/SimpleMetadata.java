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

package pl.wasat.smarthma.model.entry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.SmartHMApplication;
import pl.wasat.smarthma.model.feed.Link;
import pl.wasat.smarthma.model.om.Browse;
import pl.wasat.smarthma.model.om.Content;
import pl.wasat.smarthma.model.om.Footprint;
import pl.wasat.smarthma.model.om.PointMember;
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
    private final Entry entry;

    /**
     * Instantiates a new Simple metadata.
     *
     * @param entry the entry
     */
    public SimpleMetadata(Entry entry) {
        this.entry = entry;
        processGroupMedia(entry);
        processBinaryFileUrl(entry);
        processPolygon(entry);
    }


    /**
     * Gets footprint.
     *
     * @return the footprint
     */
    public ArrayList<LatLngExt> getFootprint() {
        return footprint;
    }

    /**
     * Sets footprint.
     *
     * @param footprint the footprint
     */
    public void setFootprint(ArrayList<LatLngExt> footprint) {
        this.footprint = footprint;
    }

    /**
     * Gets footprint center.
     *
     * @return the footprint center
     */
    public LatLngExt getFootprintCenter() {
        processFootprintCenter(entry);
        return footprintCenter;
    }

    /**
     * Sets footprint center.
     *
     * @param footprintCenter the footprint center
     */
    public void setFootprintCenter(LatLngExt footprintCenter) {
        this.footprintCenter = footprintCenter;
    }

    /**
     * Gets quick look url.
     *
     * @return the quick look url
     */
    public String getQuickLookUrl() {
        return quickLookUrl;
    }

    /**
     * Sets quick look url.
     *
     * @param quickLookUrl the quick look url
     */
    public void setQuickLookUrl(String quickLookUrl) {
        this.quickLookUrl = quickLookUrl;
    }

    /**
     * Gets thumbnail url.
     *
     * @return the thumbnail url
     */
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    /**
     * Sets thumbnail url.
     *
     * @param thumbnailUrl the thumbnail url
     */
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    /**
     * Gets cloud url.
     *
     * @return the cloud url
     */
    public String getCloudUrl() {
        return cloudUrl;
    }

    /**
     * Sets cloud url.
     *
     * @param cloudUrl the cloud url
     */
    public void setCloudUrl(String cloudUrl) {
        this.cloudUrl = cloudUrl;
    }

    /**
     * Gets binary url.
     *
     * @return the binary url
     */
    public String getBinaryUrl() {
        return binaryUrl;
    }

    /**
     * Sets binary url.
     *
     * @param binaryUrl the binary url
     */
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
        if (binaryUrl == null) {
            if (quickLookUrl != null) binaryUrl = quickLookUrl;
            else if (globalPreferences.getIsDebugMode()) binaryUrl = URL_TEST_ZIP;
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

    @SuppressWarnings({"EmptyMethod", "UnusedParameters"})
    private void obtainFootprintCenterFromDCMetadata(Entry entry) {

    }

    @SuppressWarnings({"EmptyMethod", "UnusedParameters"})
    private void obtainFootprintCenterFromISOMetadata(Entry entry) {

    }

    private void validateFootprintCenter() {
        if (footprintCenter == null) {
            if (footprint.size() > 0) {
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

    }

    private void obtainUrlsFromOMMetadata(Entry entry) {
        if (entry.getEarthObservation().getResult().getEarthObservationResult() == null) return;
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
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
    @SuppressWarnings({"EmptyMethod", "UnusedParameters"})
    private void obtainUrlFromDCMetadata(Entry entry) {

    }

    //TODO - process ISO metadata
    @SuppressWarnings({"EmptyMethod", "UnusedParameters"})
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
        List<Pos> footprintPosList = new ArrayList<>();
        if (footprintRaw.getMultiExtentOf().getMultiSurface() != null) {
            footprintPosList = footprintRaw.getMultiExtentOf()
                    .getMultiSurface().getSurfaceMembers().getPolygon()
                    .getExterior().getLinearRing().getPosList();
        } else if (footprintRaw.getLocation().getMultiPoint() != null) {
            for (PointMember member : footprintRaw.getLocation().getMultiPoint().getPointMember()) {
                footprintPosList.add(member.getPoint().getPos());
            }
        } else if (footprintRaw.getLocation().getMultiGeometry() != null) {
            footprintPosList = footprintRaw.getLocation().getMultiGeometry().getGeometryMembers().getLineString().getPosList();
        }

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

        try {
            double firstCorr = Double.parseDouble(corrStrArr[0]);
            double secCorr = Double.parseDouble(corrStrArr[1]);
            boolean isFirstLat = true;
            if (firstCorr > 90.0 || firstCorr < -90.0) {
                isFirstLat = false;
            } else if (secCorr > 90.0 || secCorr < -90.0) {
                isFirstLat = true;
            }

            double lat = 0;
            double lon;
            for (int i = 0; i < corrStrArr.length - 1; i = i + 2) {
                if (isFirstLat) {
                    lat = Double.parseDouble(corrStrArr[i]);
                    lon = Double.parseDouble(corrStrArr[i + 1]);

                } else {
                    lon = Double.parseDouble(corrStrArr[i]);
                    lat = Double.parseDouble(corrStrArr[i + 1]);
                }
                this.footprint.add(new LatLngExt(lat, lon));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings({"EmptyMethod", "UnusedParameters"})
    private void obtainPolygonFromDCMetadata(Entry entry) {

    }

    @SuppressWarnings({"EmptyMethod", "UnusedParameters"})
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
