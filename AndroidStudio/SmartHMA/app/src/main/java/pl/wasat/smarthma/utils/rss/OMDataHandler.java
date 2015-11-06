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
import pl.wasat.smarthma.model.feed.Author;
import pl.wasat.smarthma.model.feed.EOPrefixes;
import pl.wasat.smarthma.model.feed.Feed;
import pl.wasat.smarthma.model.feed.ItemsPerPage;
import pl.wasat.smarthma.model.feed.Link;
import pl.wasat.smarthma.model.feed.Query;
import pl.wasat.smarthma.model.feed.StartIndex;
import pl.wasat.smarthma.model.feed.TotalResults;
import pl.wasat.smarthma.model.om.*;

class OMDataHandler extends DefaultHandler {

    // Feed and EO data objects to use for temporary storage

    private Feed feed;
    // Number of rss entry added so far
    private int entryAdded = 0;
    private boolean isInEntry = false;
    private boolean isInEarthObservation = false;
    private int instrumentLevel = 1;
    private int platformLevel = 1;
    private int sensorLevel = 1;
    private int curveMemberLevel = 1;
    private int pointMemberLevel = 1;

    // Current characters being accumulated
    private StringBuffer chars = new StringBuffer();
    private TotalResults totalResults;
    private StartIndex startIndex;
    private ItemsPerPage itemsPerPage;
    private Query query;
    private Author author;
    private String generator;
    private String feedId;
    private String title;
    private String updated;
    private List<Entry> entries;
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
    private PhenomenonTime phenomenonTime;
    private ResultTime resultTime;
    private Procedure procedure;
    private ObservedProperty observedProperty;
    private FeatureOfInterest featureOfInterest;
    private Result result;
    private MetaDataProperty metaDataProperty;
    private TimePeriod timePeriod;
    private BeginPosition beginPosition;
    private EndPosition endPosition;
    private TimeInstant timeInstant;
    private TimePosition timePosition;
    private EarthObservationEquipment earthObservationEquipment;
    private AcquisitionParameters acquisitionParameters;
    private Acquisition acquisition;
    private AcrossTrackIncidenceAngle acrossTrackIncidenceAngle;
    private AlongTrackIncidenceAngle alongTrackIncidenceAngle;
    private CycleNumber cycleNumber;
    private IlluminationAzimuthAngle illuminationAzimuthAngle;
    private IsSegment isSegment;
    private LastOrbitNumber lastOrbitNumber;
    private OrbitDirection orbitDirection;
    private OrbitNumber orbitNumber;
    private Pitch pitch;
    private PolarisationChannels polarisationChannels;
    private PolarisationMode polarisationMode;
    private RelativePassNumber relativePassNumber;
    private Roll roll;
    private WrsLatitudeGrid wrsLatitudeGrid;
    private WrsLongitudeGrid wrsLongitudeGrid;
    private Yaw yaw;
    private AuxiliaryInstrument auxiliaryInstrument;
    private InstrumentType instrumentType;
    private ShortName shortName;
    private List<Instrument> instruments;
    private Instrument instrument;
    private List<Platform> platforms;
    private Platform platform;
    private SerialIdentifier serialIdentifier;
    private List<Sensor> sensors;
    private Sensor sensor;
    private MeasurementType measurementType;
    private OperationalMode operationalMode;
    private Footprint footprint;
    private CenterOf centerOf;
    private Point point;
    private Coordinates coordinates;
    private Pos pos;
    private LocationName locationName;
    private MaximumAltitude maximumAltitude;
    private MinimumAltitude minimumAltitude;
    private MultiExtentOf multiExtentOf;
    private MultiSurface multiSurface;
    private SurfaceMembers surfaceMembers;
    private NominalTrack nominalTrack;
    private MultiCurve multiCurve;
    private List<CurveMember> curveMembers;
    private CurveMember curveMember;
    private LineString lineString;
    private OccultationPoints occultationPoints;
    private MultiPoint multiPoint;
    private List<PointMember> pointMembers;
    private PointMember pointMember;
    private Resolution resolution;
    private SensorType sensorType;
    private SwathIdentifier swathIdentifier;
    private EarthObservationResult earthObservationResult;
    private List<Browse> browseList;
    private Browse browse;
    private BrowseInformation browseInformation;
    private FileName fileName;
    private ServiceReference serviceReference;
    private RequestMessage requestMessage;
    private ReferenceSystemIdentifier referenceSystemIdentifier;
    private Type type;
    private CloudCoverPercentage cloudCoverPercentage;
    private CloudCoverPercentageQuotationMode cloudCoverPercentageQuotationMode;
    private List<Mask> maskList;
    private Mask mask;
    private MaskInformation maskInformation;
    private Format format;
    private SubType subType;
    private Parameter parameter;
    private ParameterInformation parameterInformation;
    private Phenomenon phenomenon;
    private Name name;
    private UnitOfMeasure unitOfMeasure;
    private Product product;
    private ProductInformation productInformation;
    private Size size;
    private Timeliness timeliness;
    private EarthObservationMetaData earthObservationMetaData;
    private AcquisitionSubType acquisitionSubType;
    private AcquisitionType acquisitionType;
    private ArchivedIn archivedIn;
    private ArchivingInformation archivingInformation;
    private ArchivingCenter archivingCenter;
    private ArchivingDate archivingDate;
    private ComposedOf composedOf;
    private CreationDate creationDate;
    private DerivedFrom derivedFrom;
    private DownlinkedTo downlinkedTo;
    private DownlinkInformation downlinkInformation;
    private AcquisitionStation acquisitionStation;
    private Identifier identifier;
    private ModificationDate modificationDate;
    private NominalDate nominalDate;
    private ParentIdentifier parentIdentifier;
    private List<Processing> processingList;
    private Processing processing;
    private ProcessingInformation processingInformation;
    private GroundTrackUncertainty groundTrackUncertainty;
    private Method method;
    private ProcessingCenter processingCenter;
    private ProcessingDate processingDate;
    private ProcessingMode processingMode;
    private ProcessorName processorName;
    private ProcessorVersion processorVersion;
    private List<SamplingRate> samplingRateList;
    private SamplingRate samplingRate;
    private ProductGroupId productGroupId;
    private ProductQualityDegradation productQualityDegradation;
    private ProductQualityDegradationQuotationMode productQualityDegradationQuotationMode;
    private List<ProductQualityDegradationTag> productQualityDegradationTagList;
    private ProductQualityDegradationTag productQualityDegradationTag;
    private ProductQualityReportURL productQualityReportURL;
    private ProductQualityStatus productQualityStatus;
    private ProductType productType;
    private Status status;
    private StatusSubType statusSubType;

    public Feed getFeeds() {
        return feed;
    }

    /*
     * This method is called everytime a start element is found (an opening XML
     * marker) here we always reset the characters StringBuffer as we are only
     * currently interested in the the text values stored at leaf nodes
     *
     * (non-Javadoc)
     *
     * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
     * java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    public void startElement(String uri, String localName, String qName,
                             Attributes atts) {
        chars = new StringBuffer();
        if (localName.equalsIgnoreCase("feed")) {
            feed = new Feed();

            EOPrefixes eoPrefixes = new EOPrefixes();
            eoPrefixes.set_xmlns(atts.getValue("xmlns"));
            eoPrefixes.set_xmlns_dc(atts.getValue("xmlns:dc"));
            eoPrefixes.set_xmlns_eo(atts.getValue("xmlns:eo"));
            eoPrefixes.set_xmlns_geo(atts.getValue("xmlns:geo"));
            eoPrefixes.set_xmlns_georss(atts.getValue("xmlns:georss"));
            eoPrefixes.set_xmlns_media(atts.getValue("xmlns:media"));
            eoPrefixes.set_xmlns_os(atts.getValue("xmlns:os"));
            eoPrefixes.set_xmlns_sru(atts.getValue("xmlns:sru"));
            eoPrefixes.set_xmlns_time(atts.getValue("xmlns:time"));
            eoPrefixes.set_xmlns_wrs(atts.getValue("xmlns:wrs"));
            feed.setEoPrefixes(eoPrefixes);

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
                queryParamValues.add("-  " + atts.getValue(i));
            }
            query.setParamNameList(queryParamNames);
            query.setParamValueList(queryParamValues);

            query.setCount(atts.getValue("count"));
            query.setDcSubject(atts.getValue("dc:subject"));
            query.setEoParentIdentifier(atts.getValue("eo:parentIdentifier"));
            query.setRole(atts.getValue("role"));
            query.setSearchTerms(atts.getValue("searchTerms"));
            query.setSruRecordSchema(atts.getValue("sru:recordSchema"));
            query.setStartIndex(atts.getValue("startIndex"));
            query.setGeoBox(atts.getValue("geo:box"));
            query.setTimeEnd(atts.getValue("time:end"));
            query.setTimeStart(atts.getValue("time:start"));
        } else if (localName.equalsIgnoreCase("author")) {
            author = new Author();
        } else if (localName.equalsIgnoreCase("entry")) {
            entry = new Entry();
            isInEntry = true;
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

        // EO MetaData
        else if (localName.equalsIgnoreCase("EarthObservation")) {
            earthObservation = new EarthObservation();
            earthObservation.set_gml_id(atts.getValue("gml:id"));
            earthObservation.set_xmlns_atm(atts.getValue("xmlns:atm"));
            earthObservation.set_xmlns_eop(atts.getValue("xmlns:eop"));
            earthObservation.set_xmlns_gml(atts.getValue("xmlns:gml"));
            earthObservation.set_xmlns_om(atts.getValue("xmlns:om"));
            earthObservation.set_xmlns_opt(atts.getValue("xmlns:opt"));
            earthObservation.set_xmlns_ows(atts.getValue("xmlns:ows"));
            earthObservation.set_xmlns_swe(atts.getValue("xmlns:swe"));
            earthObservation.set_xmlns_xlink(atts.getValue("xmlns:xlink"));
            earthObservation.set_xmlns_xsi(atts.getValue("xmlns:xsi"));
            earthObservation.set_xsi_schemaLocation(atts
                    .getValue("xsi:schemaLocation"));

            isInEarthObservation = true;
        }
        // PhenomenonTime markup
        else if (localName.equalsIgnoreCase("PhenomenonTime")) {
            phenomenonTime = new PhenomenonTime();
        } else if (localName.equalsIgnoreCase("timePeriod")) {
            timePeriod = new TimePeriod();
        } else if (localName.equalsIgnoreCase("beginPosition")) {
            beginPosition = new BeginPosition();
        } else if (localName.equalsIgnoreCase("endPosition")) {
            endPosition = new EndPosition();
        }

        // ResultTime markup
        else if (localName.equalsIgnoreCase("ResultTime")) {
            resultTime = new ResultTime();
        } else if (localName.equalsIgnoreCase("timeInstant")) {
            timeInstant = new TimeInstant();
        } else if (localName.equalsIgnoreCase("timePosition")) {
            timePosition = new TimePosition();
        }

        // Procedure markup
        else if (localName.equalsIgnoreCase("Procedure")) {
            procedure = new Procedure();
        } else if (localName.equalsIgnoreCase("earthObservationEquipment")) {
            earthObservationEquipment = new EarthObservationEquipment();
        } else if (localName.equalsIgnoreCase("acquisitionParameters")) {
            acquisitionParameters = new AcquisitionParameters();
        } else if (localName.equalsIgnoreCase("acquisition")) {
            acquisition = new Acquisition();
        }
        // Procedure - Acquisition markup
        else if (localName.equalsIgnoreCase("acrossTrackIncidenceAngle")) {
            acrossTrackIncidenceAngle = new AcrossTrackIncidenceAngle();
            acrossTrackIncidenceAngle.setUom(atts.getValue("uom"));
        } else if (localName.equalsIgnoreCase("alongTrackIncidenceAngle")) {
            alongTrackIncidenceAngle = new AlongTrackIncidenceAngle();
            alongTrackIncidenceAngle.setUom(atts.getValue("uom"));
        } else if (localName.equalsIgnoreCase("cycleNumber")) {
            cycleNumber = new CycleNumber();
        } else if (localName.equalsIgnoreCase("illuminationAzimuthAngle")) {
            illuminationAzimuthAngle = new IlluminationAzimuthAngle();
            illuminationAzimuthAngle.setUom(atts.getValue("uom"));
        } else if (localName.equalsIgnoreCase("isSegment")) {
            isSegment = new IsSegment();
        } else if (localName.equalsIgnoreCase("lastOrbitNumber")) {
            lastOrbitNumber = new LastOrbitNumber();
        } else if (localName.equalsIgnoreCase("orbitDirection")) {
            orbitDirection = new OrbitDirection();
        } else if (localName.equalsIgnoreCase("orbitNumber")) {
            orbitNumber = new OrbitNumber();
        } else if (localName.equalsIgnoreCase("pitch")) {
            pitch = new Pitch();
            pitch.setUom(atts.getValue("uom"));
        } else if (localName.equalsIgnoreCase("polarisationChannels")) {
            polarisationChannels = new PolarisationChannels();
        } else if (localName.equalsIgnoreCase("polarisationMode")) {
            polarisationMode = new PolarisationMode();
        } else if (localName.equalsIgnoreCase("relativePassNumber")) {
            relativePassNumber = new RelativePassNumber();
        } else if (localName.equalsIgnoreCase("roll")) {
            roll = new Roll();
            roll.setUom(atts.getValue("uom"));
        } else if (localName.equalsIgnoreCase("wrsLatitudeGrid")) {
            wrsLatitudeGrid = new WrsLatitudeGrid();
            wrsLatitudeGrid.setCodeSpace(atts.getValue("codeSpace"));
        } else if (localName.equalsIgnoreCase("wrsLongitudeGrid")) {
            wrsLongitudeGrid = new WrsLongitudeGrid();
            wrsLongitudeGrid.setCodeSpace(atts.getValue("codeSpace"));
        } else if (localName.equalsIgnoreCase("yaw")) {
            yaw = new Yaw();
            yaw.setUom(atts.getValue("uom"));
        }

        // Procedure - AuxiliaryInstrument markup
        else if (localName.equalsIgnoreCase("auxiliaryInstrument")) {
            auxiliaryInstrument = new AuxiliaryInstrument();
        } else if (localName.equalsIgnoreCase("instrumentType")) {
            instrumentType = new InstrumentType();
        } else if (localName.equalsIgnoreCase("shortName")) {
            shortName = new ShortName();
        } else if (localName.equalsIgnoreCase("instrument")) {
            instrumentLevel = instrumentLevel - 1;
            if (instrumentLevel == 0) {
                instruments = new ArrayList<>();
            }
            if (instrumentLevel == -1) {
                instrument = new Instrument();
            }
        } else if (localName.equalsIgnoreCase("platform")) {
            platformLevel = platformLevel - 1;
            if (platformLevel == 0) {
                platforms = new ArrayList<>();
            }
            if (platformLevel == -1) {
                platform = new Platform();
            }
        } else if (localName.equalsIgnoreCase("serialIdentifier")) {
            serialIdentifier = new SerialIdentifier();
        }
        // Procedure - Sensor markup
        else if (localName.equalsIgnoreCase("sensor")) {
            sensorLevel = sensorLevel - 1;
            if (sensorLevel == 0) {
                sensors = new ArrayList<>();
            }
            if (sensorLevel == -1) {
                sensor = new Sensor();
            }
        } else if (localName.equalsIgnoreCase("measurementType")) {
            measurementType = new MeasurementType();
        } else if (localName.equalsIgnoreCase("operationalMode")) {
            operationalMode = new OperationalMode();
            operationalMode.setCodeSpace(atts.getValue("codeSpace"));
        } else if (localName.equalsIgnoreCase("footprint")) {
            footprint = new Footprint();
            footprint.set_gml_id(atts.getValue("gml:id"));
        } else if (localName.equalsIgnoreCase("centerOf")) {
            centerOf = new CenterOf();
        } else if (localName.equalsIgnoreCase("point")) {
            point = new Point();
            point.set_gml_id(atts.getValue("gml:id"));
        } else if (localName.equalsIgnoreCase("coordinates")) {
            coordinates = new Coordinates();
            pos = new Pos();
        } else if (localName.equalsIgnoreCase("pos")) {
            pos = new Pos();
        } else if (localName.equalsIgnoreCase("locationName")) {
            locationName = new LocationName();
        } else if (localName.equalsIgnoreCase("maximumAltitude")) {
            maximumAltitude = new MaximumAltitude();
            maximumAltitude.setUom(atts.getValue("uom"));
        } else if (localName.equalsIgnoreCase("minimumAltitude")) {
            minimumAltitude = new MinimumAltitude();
            minimumAltitude.setUom(atts.getValue("uom"));
        } else if (localName.equalsIgnoreCase("multiExtentOf")) {
            multiExtentOf = new MultiExtentOf();
        } else if (localName.equalsIgnoreCase("multiSurface")) {
            multiSurface = new MultiSurface();
            multiSurface.set_gml_id(atts.getValue("gml:id"));
            multiSurface.set_srsName(atts.getValue("srsName"));
        } else if (localName.equalsIgnoreCase("surfaceMembers")) {
            surfaceMembers = new SurfaceMembers();
        } else if (localName.equalsIgnoreCase("nominalTrack")) {
            nominalTrack = new NominalTrack();
        } else if (localName.equalsIgnoreCase("multiCurve")) {
            multiCurve = new MultiCurve();
            multiCurve.set_gml_id(atts.getValue("gml:id"));
        } else if (localName.equalsIgnoreCase("curveMember")) {
            curveMemberLevel = curveMemberLevel - 1;
            if (curveMemberLevel == 0) {
                curveMembers = new ArrayList<>();
            }
            if (curveMemberLevel == -1) {
                curveMember = new CurveMember();
            }
        } else if (localName.equalsIgnoreCase("lineString")) {
            lineString = new LineString();
            lineString.set_gml_id(atts.getValue("gml:id"));
        } else if (localName.equalsIgnoreCase("occultationPoints")) {
            occultationPoints = new OccultationPoints();
        } else if (localName.equalsIgnoreCase("multiPoint")) {
            multiPoint = new MultiPoint();
            multiPoint.set_gml_id(atts.getValue("gml:id"));
        } else if (localName.equalsIgnoreCase("pointMember")) {
            pointMemberLevel = pointMemberLevel - 1;
            if (pointMemberLevel == 0) {
                pointMembers = new ArrayList<>();
            }
            if (pointMemberLevel == -1) {
                pointMember = new PointMember();
            }
        } else if (localName.equalsIgnoreCase("resolution")) {
            resolution = new Resolution();
            resolution.setUom(atts.getValue("uom"));
        } else if (localName.equalsIgnoreCase("sensorType")) {
            sensorType = new SensorType();
        } else if (localName.equalsIgnoreCase("swathIdentifier")) {
            swathIdentifier = new SwathIdentifier();
        }

        // Result markup
        else if (localName.equalsIgnoreCase("Result")) {
            result = new Result();
        } else if (localName.equalsIgnoreCase("earthObservationResult")) {
            earthObservationResult = new EarthObservationResult();
            earthObservationResult.set_gml_id(atts.getValue("gml:id"));
            browseList = new ArrayList<>();
            maskList = new ArrayList<>();
        } else if (localName.equalsIgnoreCase("browse")) {
            browse = new Browse();
        } else if (localName.equalsIgnoreCase("browseInformation")) {
            browseInformation = new BrowseInformation();
        } else if (localName.equalsIgnoreCase("fileName")) {
            fileName = new FileName();
        } else if (localName.equalsIgnoreCase("serviceReference")) {
            serviceReference = new ServiceReference();
            serviceReference.set_xlink_href(atts.getValue("xlink:href"));
            serviceReference.set_xmlns();
        } else if (localName.equalsIgnoreCase("requestMessage")) {
            requestMessage = new RequestMessage();
        } else if (localName.equalsIgnoreCase("referenceSystemIdentifier")) {
            referenceSystemIdentifier = new ReferenceSystemIdentifier();
            referenceSystemIdentifier.setCodeSpace(atts.getValue("codeSpace"));
        } else if (localName.equalsIgnoreCase("type")) {
            type = new Type();
        } else if (localName.equalsIgnoreCase("cloudCoverPercentage")) {
            cloudCoverPercentage = new CloudCoverPercentage();
            cloudCoverPercentage.setUom(atts.getValue("uom"));
        } else if (localName
                .equalsIgnoreCase("cloudCoverPercentageQuotationMode")) {
            cloudCoverPercentageQuotationMode = new CloudCoverPercentageQuotationMode();
        } else if (localName.equalsIgnoreCase("mask")) {
            mask = new Mask();
        } else if (localName.equalsIgnoreCase("maskInformation")) {
            maskInformation = new MaskInformation();
        } else if (localName.equalsIgnoreCase("format")) {
            format = new Format();
        } else if (localName.equalsIgnoreCase("subType")) {
            subType = new SubType();
        } else if (localName.equalsIgnoreCase("parameter")) {
            parameter = new Parameter();
        } else if (localName.equalsIgnoreCase("parameterInformation")) {
            parameterInformation = new ParameterInformation();
        } else if (localName.equalsIgnoreCase("phenomenon")) {
            phenomenon = new Phenomenon();
            phenomenon.set_gml31_id(atts.getValue("gml31:id"));
            phenomenon.set_ns1_id(atts.getValue("ns1:id"));
            phenomenon.set_xmlns_gml31(atts.getValue("xmlns:gml31"));
            phenomenon.set_xmlns_ns1(atts.getValue("xmlns:ns1"));
        } else if (localName.equalsIgnoreCase("name")) {
            name = new Name();
            name.setCodeSpace(atts.getValue("codeSpace"));
            name.set_xmlns(atts.getValue("xmlns"));
        } else if (localName.equalsIgnoreCase("unitOfMeasure")) {
            unitOfMeasure = new UnitOfMeasure();
            unitOfMeasure.setUom(atts.getValue("uom"));
        } else if (localName.equalsIgnoreCase("product")) {
            product = new Product();
        } else if (localName.equalsIgnoreCase("productInformation")) {
            productInformation = new ProductInformation();
        } else if (localName.equalsIgnoreCase("size")) {
            size = new Size();
            size.setUom(atts.getValue("uom"));
        } else if (localName.equalsIgnoreCase("timeliness")) {
            timeliness = new Timeliness();
            timeliness.setCodeSpace(atts.getValue("codeSpace"));
        } else if (localName.equalsIgnoreCase("ObservedProperty")) {
            observedProperty = new ObservedProperty();
        } else if (localName.equalsIgnoreCase("FeatureOfInterest")) {
            featureOfInterest = new FeatureOfInterest();
        } else if (localName.equalsIgnoreCase("Result")) {
            result = new Result();
        }

        // MetaDataProperty Markups start
        else if (localName.equalsIgnoreCase("MetaDataProperty")) {
            metaDataProperty = new MetaDataProperty();
        } else if (localName.equalsIgnoreCase("earthObservationMetaData")) {
            earthObservationMetaData = new EarthObservationMetaData();
            processingList = new ArrayList<>();
            productQualityDegradationTagList = new ArrayList<>();
        } else if (localName.equalsIgnoreCase("acquisitionSubType")) {
            acquisitionSubType = new AcquisitionSubType();
        } else if (localName.equalsIgnoreCase("acquisitionType")) {
            acquisitionType = new AcquisitionType();
        } else if (localName.equalsIgnoreCase("archivedIn")) {
            archivedIn = new ArchivedIn();
        } else if (localName.equalsIgnoreCase("archivingInformation")) {
            archivingInformation = new ArchivingInformation();
        } else if (localName.equalsIgnoreCase("archivingCenter")) {
            archivingCenter = new ArchivingCenter();
            archivingCenter.setCodeSpace(atts.getValue("codeSpace"));
        } else if (localName.equalsIgnoreCase("archivingDate")) {
            archivingDate = new ArchivingDate();
        } else if (localName.equalsIgnoreCase("composedOf")) {
            composedOf = new ComposedOf();
        } else if (localName.equalsIgnoreCase("creationDate")) {
            creationDate = new CreationDate();
        } else if (localName.equalsIgnoreCase("derivedFrom")) {
            derivedFrom = new DerivedFrom();
            derivedFrom.setXlinkHref(atts.getValue("xlink:href"));
        } else if (localName.equalsIgnoreCase("downlinkedTo")) {
            downlinkedTo = new DownlinkedTo();
        } else if (localName.equalsIgnoreCase("downlinkInformation")) {
            downlinkInformation = new DownlinkInformation();
        } else if (localName.equalsIgnoreCase("acquisitionStation")) {
            acquisitionStation = new AcquisitionStation();
            acquisitionStation.setCodeSpace(atts.getValue("codeSpace"));
        } else if (localName.equalsIgnoreCase("identifier")) {
            identifier = new Identifier();
        } else if (localName.equalsIgnoreCase("modificationDate")) {
            modificationDate = new ModificationDate();
        } else if (localName.equalsIgnoreCase("nominalDate")) {
            nominalDate = new NominalDate();
        } else if (localName.equalsIgnoreCase("parentIdentifier")) {
            parentIdentifier = new ParentIdentifier();
        } else if (localName.equalsIgnoreCase("processing")) {
            processing = new Processing();
        } else if (localName.equalsIgnoreCase("processingInformation")) {
            processingInformation = new ProcessingInformation();
            samplingRateList = new ArrayList<>();
        } else if (localName.equalsIgnoreCase("groundTrackUncertainty")) {
            groundTrackUncertainty = new GroundTrackUncertainty();
            groundTrackUncertainty.setUom(atts.getValue("uom"));
        } else if (localName.equalsIgnoreCase("method")) {
            method = new Method();
        } else if (localName.equalsIgnoreCase("processingCenter")) {
            processingCenter = new ProcessingCenter();
            processingCenter.setCodeSpace(atts.getValue("codeSpace"));
        } else if (localName.equalsIgnoreCase("processingDate")) {
            processingDate = new ProcessingDate();
        } else if (localName.equalsIgnoreCase("processingMode")) {
            processingMode = new ProcessingMode();
        } else if (localName.equalsIgnoreCase("processorName")) {
            processorName = new ProcessorName();
        } else if (localName.equalsIgnoreCase("processorVersion")) {
            processorVersion = new ProcessorVersion();
        } else if (localName.equalsIgnoreCase("samplingRate")) {
            samplingRate = new SamplingRate();
            samplingRate.setUom(atts.getValue("uom"));
        } else if (localName.equalsIgnoreCase("productGroupId")) {
            productGroupId = new ProductGroupId();
        } else if (localName.equalsIgnoreCase("productQualityDegradation")) {
            productQualityDegradation = new ProductQualityDegradation();
            productQualityDegradation.setUom(atts.getValue("uom"));
        } else if (localName
                .equalsIgnoreCase("productQualityDegradationQuotationMode")) {
            productQualityDegradationQuotationMode = new ProductQualityDegradationQuotationMode();
        } else if (localName.equalsIgnoreCase("productQualityDegradationTag")) {
            productQualityDegradationTag = new ProductQualityDegradationTag();
            productQualityDegradationTag.setCodeSpace(atts
                    .getValue("codeSpace"));
        } else if (localName.equalsIgnoreCase("productQualityReportURL")) {
            productQualityReportURL = new ProductQualityReportURL();
        } else if (localName.equalsIgnoreCase("productQualityStatus")) {
            productQualityStatus = new ProductQualityStatus();
        } else if (localName.equalsIgnoreCase("productType")) {
            productType = new ProductType();
        } else if (localName.equalsIgnoreCase("status")) {
            status = new Status();
        } else if (localName.equalsIgnoreCase("statusSubType")) {
            statusSubType = new StatusSubType();
        }

    }

    /*
     * This method is called everytime an end element is found (a closing XML
     * marker) here we check what element is being closed, if it is a relevant
     * leaf node that we are checking, such as Title, then we get the characters
     * we have accumulated in the StringBuffer and set the current metadata
     * title to the value
     *
     * If this is closing the "entry", it means it is the end of the EO data, so
     * we add that to the list and then reset our EO data object for the next
     * one on the stream
     *
     *
     * (non-Javadoc)
     *
     * @see org.xml.sax.helpers.Default Handler#endElement(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        if (!isInEntry) {
            if (localName.equalsIgnoreCase("feed")) {
                feed.setEntriesEO(entries);
                feed.setTotalResults(totalResults);
                feed.setStartIndex(startIndex);
                feed.setItemsPerPage(itemsPerPage);
                feed.setQuery(query);
                feed.setAuthor(author);
                feed.setGenerator(generator);
                feed.setId(feedId);
                feed.setTitle(title);
                feed.setUpdated(updated);
                feed.setLink(linksFeed);
            } else if (localName.equalsIgnoreCase("totalResults")) {
                totalResults.setText(chars.toString());
            } else if (localName.equalsIgnoreCase("startIndex")) {
                startIndex.setText(chars.toString());
            } else if (localName.equalsIgnoreCase("itemsPerPage")) {
                itemsPerPage.setText(chars.toString());
                //} else if (localName.equalsIgnoreCase("query")) {
            } else if (localName.equalsIgnoreCase("author")) {
                author.setName(name);
            } else if (localName.equalsIgnoreCase("name")) {
                name.set_text(chars.toString());
            } else if (localName.equalsIgnoreCase("generator")) {
                generator = chars.toString();
            } else if (localName.equalsIgnoreCase("id")) {
                feedId = chars.toString();
            } else if (localName.equalsIgnoreCase("title")) {
                title = chars.toString();
            } else if (localName.equalsIgnoreCase("updated")) {
                updated = chars.toString();
            } else if (localName.equalsIgnoreCase("link")) {
                linksFeed.add(link);
            }
        }
        if (isInEntry) {
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

            if (isInEarthObservation) {
                // EO MetaData
                if (localName.equalsIgnoreCase("EarthObservation")) {
                    earthObservation.setFeatureOfInterest(featureOfInterest);
                    earthObservation.setMetaDataProperty(metaDataProperty);
                    earthObservation.setObservedProperty(observedProperty);
                    earthObservation.setPhenomenonTime(phenomenonTime);
                    earthObservation.setProcedure(procedure);
                    earthObservation.setResult(result);
                    earthObservation.setResultTime(resultTime);
                    entry.setEarthObservation(earthObservation);
                    isInEarthObservation = false;
                }
                // PhenomenonTime markup
                else if (localName.equalsIgnoreCase("beginPosition")) {
                    beginPosition.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("endPosition")) {
                    endPosition.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("timePeriod")) {
                    timePeriod.set_gml_id(chars.toString());
                    timePeriod.setBeginPosition(beginPosition);
                    timePeriod.setEndPosition(endPosition);
                } else if (localName.equalsIgnoreCase("PhenomenonTime")) {
                    phenomenonTime.setTimePeriod(timePeriod);
                }

                // ResultTime markup
                else if (localName.equalsIgnoreCase("ResultTime")) {
                    resultTime.setTimeInstant(timeInstant);
                } else if (localName.equalsIgnoreCase("timeInstant")) {
                    timeInstant.set_gml_id(chars.toString());
                    timeInstant.setTimePosition(timePosition);
                } else if (localName.equalsIgnoreCase("timePosition")) {
                    timePosition.set_text(chars.toString());
                }

                // Procedure markup
                else if (localName
                        .equalsIgnoreCase("earthObservationEquipment")) {
                    earthObservationEquipment.set_gml_id(chars.toString());
                    earthObservationEquipment
                            .setAcquisitionParameters(acquisitionParameters);
                    earthObservationEquipment
                            .setAuxiliaryInstrument(auxiliaryInstrument);
                    earthObservationEquipment.setInstrument(instruments);
                    earthObservationEquipment.setPlatform(platforms);
                    earthObservationEquipment.setSensor(sensors);
                } else if (localName.equalsIgnoreCase("acquisitionParameters")) {
                    acquisitionParameters.setAcquisition(acquisition);
                }
                // Procedure - Acquisition markup
                else if (localName.equalsIgnoreCase("acquisition")) {
                    acquisition
                            .setAcrossTrackIncidenceAngle(acrossTrackIncidenceAngle);
                    acquisition
                            .setAlongTrackIncidenceAngle(alongTrackIncidenceAngle);
                    acquisition.setCycleNumber(cycleNumber);
                    acquisition
                            .setIlluminationAzimuthAngle(illuminationAzimuthAngle);
                    acquisition.setIsSegment(isSegment);
                    acquisition.setLastOrbitNumber(lastOrbitNumber);
                    acquisition.setOrbitDirection(orbitDirection);
                    acquisition.setOrbitNumber(orbitNumber);
                    acquisition.setPitch(pitch);
                    acquisition.setPolarisationChannels(polarisationChannels);
                    acquisition.setPolarisationMode(polarisationMode);
                    acquisition.setRelativePassNumber(relativePassNumber);
                    acquisition.setRoll(roll);
                    acquisition.setWrsLatitudeGrid(wrsLatitudeGrid);
                    acquisition.setWrsLongitudeGrid(wrsLongitudeGrid);
                    acquisition.setYaw(yaw);
                } else if (localName
                        .equalsIgnoreCase("acrossTrackIncidenceAngle")) {
                    acrossTrackIncidenceAngle.set_text(chars.toString());
                } else if (localName
                        .equalsIgnoreCase("alongTrackIncidenceAngle")) {
                    alongTrackIncidenceAngle.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("cycleNumber")) {
                    cycleNumber.set_text(chars.toString());
                } else if (localName
                        .equalsIgnoreCase("illuminationAzimuthAngle")) {
                    illuminationAzimuthAngle.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("isSegment")) {
                    isSegment.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("lastOrbitNumber")) {
                    lastOrbitNumber.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("orbitDirection")) {
                    orbitDirection.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("orbitNumber")) {
                    orbitNumber.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("pitch")) {
                    pitch.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("polarisationChannels")) {
                    polarisationChannels.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("polarisationMode")) {
                    polarisationMode.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("relativePassNumber")) {
                    relativePassNumber.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("roll")) {
                    roll.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("wrsLatitudeGrid")) {
                    wrsLatitudeGrid.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("wrsLongitudeGrid")) {
                    wrsLongitudeGrid.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("yaw")) {
                    yaw.set_text(chars.toString());
                }

                // Procedure - AuxiliaryInstrument markup
                else if (localName.equalsIgnoreCase("auxiliaryInstrument")) {
                    auxiliaryInstrument
                            .setAuxiliaryInstrument(auxiliaryInstrument);
                    auxiliaryInstrument.setInstrumentType(instrumentType);
                    auxiliaryInstrument.setShortName(shortName);
                } else if (localName.equalsIgnoreCase("instrumentType")) {
                    instrumentType.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("shortName")) {
                    shortName.set_text(chars.toString());
                }

                // Procedure - Instrument markup
                else if (localName.equalsIgnoreCase("instrument")) {
                    if (instrumentLevel == -1) {
                        instrument.setInstrumentType(instrumentType);
                        instrument.setShortName(shortName);
                    }
                    if (instrumentLevel == 0) {
                        instruments.add(instrument);
                    }
                    instrumentLevel = instrumentLevel + 1;
                } else if (localName.equalsIgnoreCase("platform")) {
                    if (platformLevel == -1) {
                        platform.setSerialIdentifier(serialIdentifier);
                        platform.setShortName(shortName);
                    }
                    if (platformLevel == 0) {
                        platforms.add(platform);
                    }
                    platformLevel = platformLevel + 1;
                } else if (localName.equalsIgnoreCase("serialIdentifier")) {
                    serialIdentifier.set_text(chars.toString());
                }

                // Procedure - Sensor markup
                else if (localName.equalsIgnoreCase("sensor")) {
                    if (sensorLevel == -1) {
                        sensor.setMeasurementType(measurementType);
                        sensor.setOperationalMode(operationalMode);
                        sensor.setResolution(resolution);
                        sensor.setSensorType(sensorType);
                        sensor.setSwathIdentifier(swathIdentifier);
                    }
                    if (sensorLevel == 0) {
                        sensors.add(sensor);
                    }
                    sensorLevel = sensorLevel + 1;
                } else if (localName.equalsIgnoreCase("measurementType")) {
                    measurementType.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("operationalMode")) {
                    operationalMode.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("footprint")) {
                    footprint.setCenterOf(centerOf);
                    footprint.setLocationName(locationName);
                    footprint.setMaximumAltitude(maximumAltitude);
                    footprint.setMinimumAltitude(minimumAltitude);
                    footprint.setMultiExtentOf(multiExtentOf);
                    footprint.setNominalTrack(nominalTrack);
                    footprint.setOccultationPoints(occultationPoints);

                } else if (localName.equalsIgnoreCase("centerOf")) {
                    centerOf.setPoint(point);
                } else if (localName.equalsIgnoreCase("point")) {
                    point.setCoordinates(coordinates);
                    point.setPos(pos);
                } else if (localName.equalsIgnoreCase("coordinates")) {
                    coordinates.set_text(chars.toString());
                    pos.set_text(chars.toString());
                    // } else if (localName.equalsIgnoreCase("pos")) {
                    // pos.setText(chars.toString());
                } else if (localName.equalsIgnoreCase("locationName")) {
                    locationName.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("maximumAltitude")) {
                    maximumAltitude.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("minimumAltitude")) {
                    minimumAltitude.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("multiExtentOf")) {
                    multiExtentOf.setMultiSurface(multiSurface);
                } else if (localName.equalsIgnoreCase("multiSurface")) {
                    multiSurface.setSurfaceMembers(surfaceMembers);
                } else if (localName.equalsIgnoreCase("surfaceMembers")) {
                    surfaceMembers.setPolygon(polygon);
                } else if (localName.equalsIgnoreCase("nominalTrack")) {
                    nominalTrack.setMultiCurve(multiCurve);
                } else if (localName.equalsIgnoreCase("multiCurve")) {
                    multiCurve.setCurveMember(curveMembers);
                } else if (localName.equalsIgnoreCase("curveMember")) {
                    if (curveMemberLevel == -1) {
                        curveMember.setLineString(lineString);
                    }
                    if (curveMemberLevel == 0) {
                        curveMembers.add(curveMember);
                    }
                    curveMemberLevel = curveMemberLevel + 1;
                } else if (localName.equalsIgnoreCase("lineString")) {
                    lineString.set_srsName(chars.toString());
                    lineString.setCoordinates(coordinates);
                } else if (localName.equalsIgnoreCase("occultationPoints")) {
                    occultationPoints.setMultiPoint(multiPoint);
                } else if (localName.equalsIgnoreCase("multiPoint")) {
                    multiPoint.set_srsName(chars.toString());
                    multiPoint.setPointMember(pointMembers);
                } else if (localName.equalsIgnoreCase("pointMember")) {
                    if (pointMemberLevel == -1) {
                        pointMember.setPoint(point);
                    }
                    if (pointMemberLevel == 0) {
                        pointMembers.add(pointMember);
                    }
                    pointMemberLevel = pointMemberLevel + 1;
                } else if (localName.equalsIgnoreCase("resolution")) {
                    resolution.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("sensorType")) {
                    sensorType.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("swathIdentifier")) {
                    swathIdentifier.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("Procedure")) {
                    procedure
                            .setEarthObservationEquipment(earthObservationEquipment);
                } else if (localName.equalsIgnoreCase("ObservedProperty")) {
                    observedProperty.set_nilReason(chars.toString());
                } else if (localName.equalsIgnoreCase("FeatureOfInterest")) {
                    featureOfInterest.setFootprint(footprint);
                }

                // Results markups
                else if (localName.equalsIgnoreCase("Result")) {
                    result.setEarthObservationResult(earthObservationResult);
                } else if (localName.equalsIgnoreCase("earthObservationResult")) {
                    earthObservationResult.setBrowseList(browseList);
                    earthObservationResult
                            .setCloudCoverPercentage(cloudCoverPercentage);
                    earthObservationResult
                            .setCloudCoverPercentageQuotationMode(cloudCoverPercentageQuotationMode);
                    earthObservationResult.setMask(maskList);
                    earthObservationResult.setParameter(parameter);
                    earthObservationResult.setProduct(product);

                } else if (localName.equalsIgnoreCase("browse")) {
                    browse.setBrowseInformation(browseInformation);
                    browseList.add(browse);
                } else if (localName.equalsIgnoreCase("browseInformation")) {
                    browseInformation.setFileName(fileName);
                    browseInformation
                            .setReferenceSystemIdentifier(referenceSystemIdentifier);
                    browseInformation.setType(type);

                } else if (localName.equalsIgnoreCase("fileName")) {
                    fileName.setServiceReference(serviceReference);
                } else if (localName.equalsIgnoreCase("serviceReference")) {
                    serviceReference.setRequestMessage(requestMessage);
                    //} else if (localName.equalsIgnoreCase("requestMessage")) {
                } else if (localName
                        .equalsIgnoreCase("referenceSystemIdentifier")) {
                    referenceSystemIdentifier.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("type")) {
                    type.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("cloudCoverPercentage")) {
                    cloudCoverPercentage.set_text(chars.toString());
                } else if (localName
                        .equalsIgnoreCase("cloudCoverPercentageQuotationMode")) {
                    cloudCoverPercentageQuotationMode.set_text(chars
                            .toString());
                } else if (localName.equalsIgnoreCase("mask")) {
                    mask.setMaskInformation(maskInformation);
                    maskList.add(mask);
                } else if (localName.equalsIgnoreCase("maskInformation")) {
                    maskInformation.setFileName(fileName);
                    maskInformation.setFormat(format);
                    maskInformation.setMultiExtentOf(multiExtentOf);
                    maskInformation
                            .setReferenceSystemIdentifier(referenceSystemIdentifier);
                    maskInformation.setSubType(subType);
                    maskInformation.setType(type);
                } else if (localName.equalsIgnoreCase("format")) {
                    format.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("subType")) {
                    subType.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("parameter")) {
                    parameter.setParameterInformation(parameterInformation);
                } else if (localName.equalsIgnoreCase("parameterInformation")) {
                    parameterInformation.setPhenomenon(phenomenon);
                    parameterInformation.setUnitOfMeasure(unitOfMeasure);
                } else if (localName.equalsIgnoreCase("phenomenon")) {
                    phenomenon.setName(name);
                } else if (localName.equalsIgnoreCase("name")) {
                    name.set_text(chars.toString());
                    // } else if (localName.equalsIgnoreCase("unitOfMeasure")) {
                } else if (localName.equalsIgnoreCase("product")) {
                    product.setProductInformation(productInformation);
                } else if (localName.equalsIgnoreCase("productInformation")) {
                    productInformation.setFileName(fileName);
                    productInformation
                            .setReferenceSystemIdentifier(referenceSystemIdentifier);
                    productInformation.setSize(size);
                    productInformation.setTimeliness(timeliness);
                } else if (localName.equalsIgnoreCase("size")) {
                    size.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("timeliness")) {
                    timeliness.set_text(chars.toString());
                }

                // MetaDataProperty Markups
                else if (localName.equalsIgnoreCase("MetaDataProperty")) {
                    metaDataProperty
                            .setEarthObservationMetaData(earthObservationMetaData);
                } else if (localName
                        .equalsIgnoreCase("earthObservationMetaData")) {
                    earthObservationMetaData
                            .setAcquisitionSubType(acquisitionSubType);
                    earthObservationMetaData
                            .setAcquisitionType(acquisitionType);
                    earthObservationMetaData.setArchivedIn(archivedIn);
                    earthObservationMetaData.setComposedOf(composedOf);
                    earthObservationMetaData.setCreationDate(creationDate);
                    earthObservationMetaData.setDerivedFrom(derivedFrom);
                    earthObservationMetaData.setDownlinkedTo(downlinkedTo);
                    earthObservationMetaData.setIdentifier(identifier);
                    earthObservationMetaData
                            .setModificationDate(modificationDate);
                    earthObservationMetaData.setNominalDate(nominalDate);
                    earthObservationMetaData
                            .setParentIdentifier(parentIdentifier);
                    earthObservationMetaData.setProcessing(processingList);
                    earthObservationMetaData.setProductGroupId(productGroupId);
                    earthObservationMetaData
                            .setProductQualityDegradation(productQualityDegradation);
                    earthObservationMetaData
                            .setProductQualityDegradationQuotationMode(productQualityDegradationQuotationMode);
                    earthObservationMetaData
                            .setProductQualityDegradationTag(productQualityDegradationTagList);
                    earthObservationMetaData
                            .setProductQualityReportURL(productQualityReportURL);
                    earthObservationMetaData
                            .setProductQualityStatus(productQualityStatus);
                    earthObservationMetaData.setProductType(productType);
                    earthObservationMetaData.setStatus(status);
                    earthObservationMetaData.setStatusSubType(statusSubType);
                } else if (localName.equalsIgnoreCase("acquisitionSubType")) {
                    acquisitionSubType.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("acquisitionType")) {
                    acquisitionType.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("archivedIn")) {
                    archivedIn.setArchivingInformation(archivingInformation);
                } else if (localName.equalsIgnoreCase("archivingInformation")) {
                    archivingInformation.setArchivingCenter(archivingCenter);
                    archivingInformation.setArchivingDate(archivingDate);
                } else if (localName.equalsIgnoreCase("archivingCenter")) {
                    archivingCenter.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("archivingDate")) {
                    archivingDate.set_text(chars.toString());
                    //} else if (localName.equalsIgnoreCase("composedOf")) {
                } else if (localName.equalsIgnoreCase("creationDate")) {
                    creationDate.set_text(chars.toString());
                    //} else if (localName.equalsIgnoreCase("derivedFrom")) {
                } else if (localName.equalsIgnoreCase("downlinkedTo")) {
                    downlinkedTo.setDownlinkInformation(downlinkInformation);
                } else if (localName.equalsIgnoreCase("downlinkInformation")) {
                    downlinkInformation
                            .setAcquisitionStation(acquisitionStation);
                } else if (localName.equalsIgnoreCase("acquisitionStation")) {
                    acquisitionStation.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("identifier")) {
                    identifier.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("modificationDate")) {
                    modificationDate.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("nominalDate")) {
                    nominalDate.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("parentIdentifier")) {
                    parentIdentifier.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("processing")) {
                    processing.setProcessingInformation(processingInformation);
                    processingList.add(processing);
                } else if (localName.equalsIgnoreCase("processingInformation")) {
                    processingInformation
                            .setGroundTrackUncertainty(groundTrackUncertainty);
                    processingInformation.setMethod(method);
                    processingInformation.setProcessingCenter(processingCenter);
                    processingInformation.setProcessingDate(processingDate);
                    processingInformation.setProcessingMode(processingMode);
                    processingInformation.setProcessorName(processorName);
                    processingInformation.setProcessorVersion(processorVersion);
                    processingInformation.setSamplingRate(samplingRateList);
                } else if (localName.equalsIgnoreCase("groundTrackUncertainty")) {
                    groundTrackUncertainty.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("method")) {
                    method.set_text(chars.toString());
                    // } else if (localName.equalsIgnoreCase("processingCenter")) {
                } else if (localName.equalsIgnoreCase("processingDate")) {
                    processingDate.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("processingMode")) {
                    processingMode.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("processorName")) {
                    processorName.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("processorVersion")) {
                    processorVersion.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("samplingRate")) {
                    samplingRate.set_text(chars.toString());
                    samplingRateList.add(samplingRate);
                } else if (localName.equalsIgnoreCase("productGroupId")) {
                    productGroupId.set_text(chars.toString());
                } else if (localName
                        .equalsIgnoreCase("productQualityDegradation")) {
                    productQualityDegradation.set_text(chars.toString());
                } else if (localName
                        .equalsIgnoreCase("productQualityDegradationQuotationMode")) {
                    productQualityDegradationQuotationMode.set_text(chars
                            .toString());
                } else if (localName
                        .equalsIgnoreCase("productQualityDegradationTag")) {
                    productQualityDegradationTag.set_text(chars.toString());
                    productQualityDegradationTagList
                            .add(productQualityDegradationTag);
                } else if (localName
                        .equalsIgnoreCase("productQualityReportURL")) {
                    productQualityReportURL.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("productQualityStatus")) {
                    productQualityStatus.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("productType")) {
                    productType.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("status")) {
                    status.set_text(chars.toString());
                } else if (localName.equalsIgnoreCase("statusSubType")) {
                    statusSubType.set_text(chars.toString());
                }

                // Coordinates - footprint and polygons
                else if (localName.equalsIgnoreCase("where")) {
                    where.setPolygon(polygon);
                    entry.setWhere(where);
                } else if (localName.equalsIgnoreCase("Polygon")) {
                    polygon.setExterior(exterior);
                } else if (localName.equalsIgnoreCase("exterior")) {
                    exterior.setLinearRing(linearRing);
                } else if (localName.equalsIgnoreCase("LinearRing")) {
                    linearRing.setPosList(posList);
                    linearRing.setPosString(posString);
                } else if (localName.equalsIgnoreCase("pos")) {
                    pos.set_text(chars.toString());
                    posList.add(pos);
                } else if (localName.equalsIgnoreCase("poslist")) {
                    posString.setPointsString(chars.toString());
                }
            }
            // Close Entry
            else if (localName.equalsIgnoreCase("entry")) {
                entry.setGuid(String.valueOf(entryAdded));
                entry.setLinks(linksEntry);
                entries.add(entry);
                // Lets check if we've hit our limit on number of entries
                entryAdded++;
                isInEntry = false;

            }

        }
    }

    /*
     * This method is called when characters are found in between XML markers,
     * however, there is no guarantee that this will be called at the end of the
     * node, or that it will be called only once , so we just accumulate these
     * and then deal with them in endElement() to be sure we have all the text
     *
     * (non-Javadoc)
     *
     * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
     */
    public void characters(char ch[], int start, int length) {
        chars.append(new String(ch, start, length).trim());
    }
}