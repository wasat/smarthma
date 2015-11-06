package pl.wasat.smarthma.utils.rss;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

import pl.wasat.smarthma.model.entry.Polygon;
import pl.wasat.smarthma.model.entry.Summary;
import pl.wasat.smarthma.model.feed.Author;
import pl.wasat.smarthma.model.feed.Feed;
import pl.wasat.smarthma.model.feed.ISOPrefixes;
import pl.wasat.smarthma.model.feed.ItemsPerPage;
import pl.wasat.smarthma.model.feed.Link;
import pl.wasat.smarthma.model.feed.Query;
import pl.wasat.smarthma.model.feed.StartIndex;
import pl.wasat.smarthma.model.feed.TotalResults;
import pl.wasat.smarthma.model.iso.Abstract;
import pl.wasat.smarthma.model.iso.AccessConstraints;
import pl.wasat.smarthma.model.iso.Address;
import pl.wasat.smarthma.model.iso.BeginPosition;
import pl.wasat.smarthma.model.iso.Boolean;
import pl.wasat.smarthma.model.iso.CIAddress;
import pl.wasat.smarthma.model.iso.CICitation;
import pl.wasat.smarthma.model.iso.CIContact;
import pl.wasat.smarthma.model.iso.CIDate;
import pl.wasat.smarthma.model.iso.CIDateTypeCode;
import pl.wasat.smarthma.model.iso.CIOnlineResource;
import pl.wasat.smarthma.model.iso.CIResponsibleParty;
import pl.wasat.smarthma.model.iso.CIRoleCode;
import pl.wasat.smarthma.model.iso.CITelephone;
import pl.wasat.smarthma.model.iso.CharacterString;
import pl.wasat.smarthma.model.iso.Citation;
import pl.wasat.smarthma.model.iso.City;
import pl.wasat.smarthma.model.iso.Code;
import pl.wasat.smarthma.model.iso.CodeSpace;
import pl.wasat.smarthma.model.iso.Contact;
import pl.wasat.smarthma.model.iso.ContactInfo;
import pl.wasat.smarthma.model.iso.Country;
import pl.wasat.smarthma.model.iso.DQConformanceResult;
import pl.wasat.smarthma.model.iso.DQDataQuality;
import pl.wasat.smarthma.model.iso.DQDomainConsistency;
import pl.wasat.smarthma.model.iso.DQScope;
import pl.wasat.smarthma.model.iso.DataQualityInfo;
import pl.wasat.smarthma.model.iso.Date;
import pl.wasat.smarthma.model.iso.DateGco;
import pl.wasat.smarthma.model.iso.DateInCIDate;
import pl.wasat.smarthma.model.iso.DateStamp;
import pl.wasat.smarthma.model.iso.DateType;
import pl.wasat.smarthma.model.iso.Decimal;
import pl.wasat.smarthma.model.iso.DeliveryPoint;
import pl.wasat.smarthma.model.iso.DescriptiveKeyword;
import pl.wasat.smarthma.model.iso.EXExtent;
import pl.wasat.smarthma.model.iso.EXGeographicBoundingBox;
import pl.wasat.smarthma.model.iso.EXTemporalExtent;
import pl.wasat.smarthma.model.iso.EastBoundLongitude;
import pl.wasat.smarthma.model.iso.ElectronicMailAddress;
import pl.wasat.smarthma.model.iso.EndPosition;
import pl.wasat.smarthma.model.iso.EntryISO;
import pl.wasat.smarthma.model.iso.Explanation;
import pl.wasat.smarthma.model.iso.Extent;
import pl.wasat.smarthma.model.iso.ExtentInEXTemp;
import pl.wasat.smarthma.model.iso.Facsimile;
import pl.wasat.smarthma.model.iso.FileIdentifier;
import pl.wasat.smarthma.model.iso.GeographicElement;
import pl.wasat.smarthma.model.iso.HierarchyLevel;
import pl.wasat.smarthma.model.iso.IdentificationInfo;
import pl.wasat.smarthma.model.iso.Identifier;
import pl.wasat.smarthma.model.iso.IndividualName;
import pl.wasat.smarthma.model.iso.Keyword;
import pl.wasat.smarthma.model.iso.LILineage;
import pl.wasat.smarthma.model.iso.Language;
import pl.wasat.smarthma.model.iso.LanguageCode;
import pl.wasat.smarthma.model.iso.Level;
import pl.wasat.smarthma.model.iso.Lineage;
import pl.wasat.smarthma.model.iso.Linkage;
import pl.wasat.smarthma.model.iso.MDConstraints;
import pl.wasat.smarthma.model.iso.MDDataIdentification;
import pl.wasat.smarthma.model.iso.MDKeywords;
import pl.wasat.smarthma.model.iso.MDLegalConstraints;
import pl.wasat.smarthma.model.iso.MDMetadata;
import pl.wasat.smarthma.model.iso.MDRestrictionCode;
import pl.wasat.smarthma.model.iso.MDScopeCode;
import pl.wasat.smarthma.model.iso.MDTopicCategoryCode;
import pl.wasat.smarthma.model.iso.MetadataStandardName;
import pl.wasat.smarthma.model.iso.MetadataStandardVersion;
import pl.wasat.smarthma.model.iso.NorthBoundLatitude;
import pl.wasat.smarthma.model.iso.OnlineResource;
import pl.wasat.smarthma.model.iso.OrganisationName;
import pl.wasat.smarthma.model.iso.OtherConstraints;
import pl.wasat.smarthma.model.iso.Pass;
import pl.wasat.smarthma.model.iso.Phone;
import pl.wasat.smarthma.model.iso.PointOfContact;
import pl.wasat.smarthma.model.iso.PositionName;
import pl.wasat.smarthma.model.iso.PostalCode;
import pl.wasat.smarthma.model.iso.RSIdentifier;
import pl.wasat.smarthma.model.iso.Report;
import pl.wasat.smarthma.model.iso.ResourceConstraint;
import pl.wasat.smarthma.model.iso.Result;
import pl.wasat.smarthma.model.iso.Role;
import pl.wasat.smarthma.model.iso.Scope;
import pl.wasat.smarthma.model.iso.SouthBoundLatitude;
import pl.wasat.smarthma.model.iso.Specification;
import pl.wasat.smarthma.model.iso.Statement;
import pl.wasat.smarthma.model.iso.TemporalElement;
import pl.wasat.smarthma.model.iso.TimePeriod;
import pl.wasat.smarthma.model.iso.Title;
import pl.wasat.smarthma.model.iso.TopicCategory;
import pl.wasat.smarthma.model.iso.URL;
import pl.wasat.smarthma.model.iso.UseLimitation;
import pl.wasat.smarthma.model.iso.Voice;
import pl.wasat.smarthma.model.iso.WestBoundLongitude;

class ISODataHandler extends DefaultHandler {

    // Current characters being accumulated
    private StringBuffer chars = new StringBuffer();

    // Feed and ISO data objects to use for temporary storage
    private Feed feed;
    private boolean isInFeed = false;
    private boolean isInEntry = false;
    private boolean isInMDMetadata = false;
    private boolean isInCitation = false;
    private boolean isInCIDate = false;
    private boolean isInEXTemp = false;
    private boolean isIdAdded = false;

    private TotalResults totalResults;
    private StartIndex startIndex;
    private ItemsPerPage itemsPerPage;
    private Query query;
    private Author author;
    private String generator;
    private String feedId;
    private String identifierEntry;
    private String identifierFeed;
    private Identifier identifier;
    private String title;
    private String updated;
    private List<EntryISO> entries;
    private EntryISO entry;
    private Link link;
    private ArrayList<Link> linksEntry;
    private ArrayList<Link> linksFeed;
    private Polygon polygon;

    private String name;
    private Date date;
    private Summary summary;
    private MDMetadata mdMetadata;
    private FileIdentifier fileIdentifier;
    private Language language;
    private HierarchyLevel hierarchyLevel;
    private Contact contact;
    private DateStamp dateStamp;
    private MetadataStandardName metadataStandardName;
    private MetadataStandardVersion metadataStandardVersion;
    private IdentificationInfo identificationInfo;
    private DataQualityInfo dataQualityInfo;
    private CharacterString CharacterString;
    private LanguageCode LanguageCode;
    private MDScopeCode MDScopeCode;
    private CIResponsibleParty CIResponsibleParty;
    private IndividualName individualName;
    private OrganisationName organisationName;
    private PositionName positionName;
    private ContactInfo contactInfo;
    private Role role;
    private CIContact CIContact;
    private Address address;
    private OnlineResource onlineResource;
    private Phone phone;
    private CIAddress CIAddress;
    private City city;
    private Country country;
    private DeliveryPoint deliveryPoint;
    private ElectronicMailAddress electronicMailAddress;
    private PostalCode postalCode;
    private CIOnlineResource CIOnlineResource;
    private Linkage linkage;
    private URL URL;
    private CITelephone CITelephone;
    private Facsimile facsimile;
    private Voice voice;
    private CIRoleCode CIRoleCode;
    private MDDataIdentification MDDataIdentification;
    private Citation citation;
    private CICitation CICitation;
    private RSIdentifier RSIdentifier;
    private Title citationTitle;
    private Code code;
    private CodeSpace codeSpace;
    private CIDate CIDate;
    private DateType dateType;
    private DateInCIDate dateInCIDate;
    private DateGco dateGco;
    private Abstract _abstract;
    private PointOfContact pointOfContact;
    private List<DescriptiveKeyword> descriptiveKeywords;
    private DescriptiveKeyword descriptiveKeyword;
    private MDKeywords MDKeywords;
    private List<Keyword> keywords;
    private Keyword keyword;
    private List<ResourceConstraint> resourceConstraints;
    private ResourceConstraint resourceConstraint;
    private MDConstraints MDConstraints;
    private UseLimitation useLimitation;
    private MDLegalConstraints MDLegalConstraints;
    private AccessConstraints accessConstraints;
    private MDRestrictionCode MDRestrictionCode;
    private OtherConstraints otherConstraints;
    private TopicCategory topicCategory;
    private MDTopicCategoryCode MDTopicCategoryCode;
    private List<Extent> extents;
    private Extent extent;
    private EXExtent EXExtent;
    private TemporalElement temporalElement;
    private EXTemporalExtent EXTemporalExtent;
    private ExtentInEXTemp extentInEXTemp;
    private TimePeriod TimePeriod;
    private BeginPosition beginPosition;
    private EndPosition endPosition;
    private GeographicElement geographicElement;
    private EXGeographicBoundingBox EXGeographicBoundingBox;
    private EastBoundLongitude eastBoundLongitude;
    private Decimal Decimal;
    private NorthBoundLatitude northBoundLatitude;
    private SouthBoundLatitude southBoundLatitude;
    private WestBoundLongitude westBoundLongitude;
    private DQDataQuality DQDataQuality;
    private Lineage lineage;
    private LILineage LILineage;
    private Statement statement;
    private Report report;
    private DQDomainConsistency DQDomainConsistency;
    private Result result;
    private DQConformanceResult DQConformanceResult;
    private Explanation explanation;
    private Pass pass;
    private Boolean Boolean;
    private Specification specification;
    private Scope scope;
    private DQScope DQScope;
    private Level level;
    private CIDateTypeCode CIDateTypeCode;


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
            isInFeed = true;

            ISOPrefixes isoPrefixes = new ISOPrefixes();
            isoPrefixes.setXmlns(atts.getValue("xmlns"));
            isoPrefixes.setXmlnsDc(atts.getValue("xmlns:dc"));
            isoPrefixes.setXmlnsGeo(atts.getValue("xmlns:geo"));
            isoPrefixes.setXmlnsGeorss(atts.getValue("xmlns:georss"));
            isoPrefixes.setXmlnsGml(atts.getValue("xmlns:gml"));
            isoPrefixes.setXmlnsOs(atts.getValue("xmlns:os"));
            isoPrefixes.setXmlnsSemantic(atts.getValue("xmlns:semantic"));
            isoPrefixes.setXmlnsSru(atts.getValue("xmlns:sru"));
            isoPrefixes.setXmlnsTime(atts.getValue("xmlns:time"));
            isoPrefixes.setXmlnsWrs(atts.getValue("xmlns:wrs"));
            isoPrefixes.setXmlnsUrlencoder(atts.getValue("xmlns:urlencoder"));
            isoPrefixes.setXmlnsXlink(atts.getValue("xmlns:xlink"));
            feed.setIsoPrefixes(isoPrefixes);

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
                queryParamValues.add("-  " + atts.getValue(i).replaceAll(",", ", "));
            }
            query.setParamNameList(queryParamNames);
            query.setParamValueList(queryParamValues);

/*            query.setCount(atts.getValue("count"));
            query.setDcSubject(atts.getValue("dc:subject"));
            query.setDcType(atts.getValue("dc:type"));
            query.setGeoUid(atts.getValue("geo:uid"));
            query.setRole(atts.getValue("role"));
            query.setSearchTerms(atts.getValue("searchTerms"));
            query.setSruRecordSchema(atts.getValue("sru:recordSchema"));
            query.setStartIndex(atts.getValue("startIndex"));
            query.setGeoBox(atts.getValue("geo:box"));
            query.setTimeEnd(atts.getValue("time:end"));
            query.setTimeStart(atts.getValue("time:start"));*/
        } else if (localName.equalsIgnoreCase("author")) {
            author = new Author();
        } else if (localName.equalsIgnoreCase("identifier")) {
            identifier = new Identifier();
        } else if (localName.equalsIgnoreCase("title")) {
            if (isInCitation) {
                citationTitle = new Title();
            }
        } else if (localName.equalsIgnoreCase("link")) {
            link = new Link();
            link.setHref(atts.getValue("href"));
            link.setRel(atts.getValue("rel"));
            link.setTitle(atts.getValue("title"));
            link.setType(atts.getValue("type"));

            // Entry declarations
        } else if (localName.equalsIgnoreCase("entry")) {
            entry = new EntryISO();
            isInFeed = false;
            isInEntry = true;
            linksEntry = new ArrayList<>();
            entry.setXmlLang(atts.getValue("xml:lang"));
        } else if (localName.equals("date")) {
            if (isInCIDate) {
                dateInCIDate = new DateInCIDate();
            } else {
                date = new Date();
            }
        } else if (localName.equalsIgnoreCase("summary")) {
            summary = new Summary();
            summary.setType(atts.getValue("xml:lang"));
        } else if (localName.equalsIgnoreCase("Polygon")) {
            polygon = new Polygon();

            // MDMetadata declarations
            // MI_Metadata declarations
        } else if (localName.equalsIgnoreCase("MD_Metadata")|| localName.equalsIgnoreCase("MI_Metadata")) {
            mdMetadata = new MDMetadata();
            isInMDMetadata = true;
            mdMetadata.setXmlnsGmd(atts.getValue("xmlns:gmd"));
            mdMetadata.setXmlns(atts.getValue("xmlns"));
            mdMetadata.setXmlnsGco(atts.getValue("xmlns:gco"));
            mdMetadata.setXmlnsGmx(atts.getValue("xmlns:gmx"));
            mdMetadata.setXmlnsGsr(atts.getValue("xmlns:gsr"));
            mdMetadata.setXmlnsGss(atts.getValue("xmlns:gss"));
            mdMetadata.setXmlnsGts(atts.getValue("xmlns:gts"));
            mdMetadata.setXmlnsXsi(atts.getValue("xmlns:xsi"));
            mdMetadata.setXmlnsGml(atts.getValue("xmlns:gml"));
            mdMetadata.setXmlnsXlink(atts.getValue("xmlns:xlink"));
            mdMetadata.setId(atts.getValue("id"));
            mdMetadata
                    .setXsiSchemaLocation(atts.getValue("xsi:schemaLocation"));
        } else if (localName.equalsIgnoreCase("CharacterString")) {
            CharacterString = new CharacterString();
        } else if (localName.equalsIgnoreCase("fileIdentifier")) {
            fileIdentifier = new FileIdentifier();
        } else if (localName.equalsIgnoreCase("language")) {
            language = new Language();
        } else if (localName.equalsIgnoreCase("LanguageCode")) {
            LanguageCode = new LanguageCode();
            LanguageCode.setCodeList(atts.getValue("codeList"));
            LanguageCode.setCodeListValue(atts.getValue("codeListValue"));
        } else if (localName.equalsIgnoreCase("hierarchyLevel")) {
            hierarchyLevel = new HierarchyLevel();
        } else if (localName.equalsIgnoreCase("MD_ScopeCode")) {
            MDScopeCode = new MDScopeCode();
            MDScopeCode.setCodeList(atts.getValue("codeList"));
            MDScopeCode.setCodeListValue(atts.getValue("codeListValue"));
        }

        // Contact entry
        else if (localName.equalsIgnoreCase("contact")) {
            contact = new Contact();
        } else if (localName.equalsIgnoreCase("CI_ResponsibleParty")) {
            CIResponsibleParty = new CIResponsibleParty();
        } else if (localName.equalsIgnoreCase("individualName")) {
            individualName = new IndividualName();
        } else if (localName.equalsIgnoreCase("organisationName")) {
            organisationName = new OrganisationName();
        } else if (localName.equalsIgnoreCase("positionName")) {
            positionName = new PositionName();
        } else if (localName.equalsIgnoreCase("contactInfo")) {
            contactInfo = new ContactInfo();
        } else if (localName.equalsIgnoreCase("CI_Contact")) {
            CIContact = new CIContact();
        } else if (localName.equalsIgnoreCase("address")) {
            address = new Address();
        } else if (localName.equalsIgnoreCase("CI_Address")) {
            CIAddress = new CIAddress();
        } else if (localName.equalsIgnoreCase("city")) {
            city = new City();
        } else if (localName.equalsIgnoreCase("country")) {
            country = new Country();
        } else if (localName.equalsIgnoreCase("deliveryPoint")) {
            deliveryPoint = new DeliveryPoint();
        } else if (localName.equalsIgnoreCase("electronicMailAddress")) {
            electronicMailAddress = new ElectronicMailAddress();
        } else if (localName.equalsIgnoreCase("postalCode")) {
            postalCode = new PostalCode();
        } else if (localName.equalsIgnoreCase("onlineResource")) {
            onlineResource = new OnlineResource();
        } else if (localName.equalsIgnoreCase("CI_OnlineResource")) {
            CIOnlineResource = new CIOnlineResource();
        } else if (localName.equalsIgnoreCase("linkage")) {
            linkage = new Linkage();
        } else if (localName.equalsIgnoreCase("URL")) {
            URL = new URL();
        } else if (localName.equalsIgnoreCase("phone")) {
            phone = new Phone();
        } else if (localName.equalsIgnoreCase("CI_Telephone")) {
            CITelephone = new CITelephone();
        } else if (localName.equalsIgnoreCase("facsimile")) {
            facsimile = new Facsimile();
        } else if (localName.equalsIgnoreCase("voice")) {
            voice = new Voice();
        } else if (localName.equalsIgnoreCase("role")) {
            role = new Role();
        } else if (localName.equalsIgnoreCase("CI_RoleCode")) {
            CIRoleCode = new CIRoleCode();
            CIRoleCode.setCodeList(atts.getValue("codeList"));
            CIRoleCode.setCodeListValue(atts.getValue("codeListValue"));
        }

        // Date entry
        else if (localName.equalsIgnoreCase("dateStamp")) {
            dateStamp = new DateStamp();
        }

        // Metadata Standards
        else if (localName.equalsIgnoreCase("metadataStandardName")) {
            metadataStandardName = new MetadataStandardName();
        } else if (localName.equalsIgnoreCase("metadataStandardVersion")) {
            metadataStandardVersion = new MetadataStandardVersion();
        }

        // Identification Info
        else if (localName.equalsIgnoreCase("identificationInfo")) {
            identificationInfo = new IdentificationInfo();
        } else if (localName.equalsIgnoreCase("MD_DataIdentification")) {
            MDDataIdentification = new MDDataIdentification();
            descriptiveKeywords = new ArrayList<>();
            resourceConstraints = new ArrayList<>();
            extents = new ArrayList<>();
        } else if (localName.equalsIgnoreCase("citation")) {
            citation = new Citation();
        } else if (localName.equalsIgnoreCase("CI_Citation")) {
            CICitation = new CICitation();
            isInCitation = true;
            // } else if (localName.equalsIgnoreCase("date")) {
            // date = new Date();
        } else if (localName.equalsIgnoreCase("CI_Date")) {
            CIDate = new CIDate();
            isInCIDate = true;
            // } else if (localName.equalsIgnoreCase("date") && isInCIDate) {
            // dateInCIDate = new DateInCIDate();
        } else if (localName.equals("Date")) {
            dateGco = new DateGco();
        } else if (localName.equals("dateType")) {
            dateType = new DateType();
        } else if (localName.equals("CI_DateTypeCode")) {
            CIDateTypeCode = new CIDateTypeCode();
            CIDateTypeCode.setCodeList(atts.getValue("codeList"));
            CIDateTypeCode.setCodeListValue(atts.getValue("codeListValue"));
        } else if (localName.equalsIgnoreCase("RS_Identifier")) {
            RSIdentifier = new RSIdentifier();
        } else if (localName.equalsIgnoreCase("code")) {
            code = new Code();
        } else if (localName.equalsIgnoreCase("codeSpace")) {
            codeSpace = new CodeSpace();
        } else if (localName.equalsIgnoreCase("abstract")) {
            _abstract = new Abstract();
        } else if (localName.equalsIgnoreCase("pointOfContact")) {
            pointOfContact = new PointOfContact();
        } else if (localName.equalsIgnoreCase("descriptiveKeywords")) {
            descriptiveKeyword = new DescriptiveKeyword();
        } else if (localName.equalsIgnoreCase("MD_Keywords")) {
            MDKeywords = new MDKeywords();
            keywords = new ArrayList<>();
        } else if (localName.equalsIgnoreCase("keyword")) {
            keyword = new Keyword();
        } else if (localName.equalsIgnoreCase("resourceConstraints")) {
            resourceConstraint = new ResourceConstraint();
        } else if (localName.equalsIgnoreCase("MD_Constraints")) {
            MDConstraints = new MDConstraints();
        } else if (localName.equalsIgnoreCase("useLimitation")) {
            useLimitation = new UseLimitation();
        } else if (localName.equalsIgnoreCase("MD_LegalConstraints")) {
            MDLegalConstraints = new MDLegalConstraints();
        } else if (localName.equalsIgnoreCase("accessConstraints")) {
            accessConstraints = new AccessConstraints();
        } else if (localName.equalsIgnoreCase("MD_RestrictionCode")) {
            MDRestrictionCode = new MDRestrictionCode();
            MDRestrictionCode.setCodeList(atts.getValue("codeList"));
            MDRestrictionCode.setCodeListValue(atts.getValue("codeListValue"));
        } else if (localName.equalsIgnoreCase("otherConstraints")) {
            otherConstraints = new OtherConstraints();
        } else if (localName.equalsIgnoreCase("topicCategory")) {
            topicCategory = new TopicCategory();
        } else if (localName.equalsIgnoreCase("MD_TopicCategoryCode")) {
            MDTopicCategoryCode = new MDTopicCategoryCode();
        } else if (localName.equalsIgnoreCase("extent")) {
            extent = new Extent();
            if (isInEXTemp)
                extentInEXTemp = new ExtentInEXTemp();
        } else if (localName.equalsIgnoreCase("EX_Extent")) {
            EXExtent = new EXExtent();
        } else if (localName.equalsIgnoreCase("temporalElement")) {
            temporalElement = new TemporalElement();
        } else if (localName.equalsIgnoreCase("EX_TemporalExtent")) {
            EXTemporalExtent = new EXTemporalExtent();
            isInEXTemp = true;
            // } else if (localName.equalsIgnoreCase("extent") && isInEXTemp) {
            // extentInEXTemp = new ExtentInEXTemp();
        } else if (localName.equalsIgnoreCase("TimePeriod")) {
            TimePeriod = new TimePeriod();
            TimePeriod.setGmlId(atts.getValue("gml:id"));
        } else if (localName.equalsIgnoreCase("beginPosition")) {
            beginPosition = new BeginPosition();
        } else if (localName.equalsIgnoreCase("endPosition")) {
            endPosition = new EndPosition();
        } else if (localName.equalsIgnoreCase("geographicElement")) {
            geographicElement = new GeographicElement();
        } else if (localName.equalsIgnoreCase("EX_GeographicBoundingBox")) {
            EXGeographicBoundingBox = new EXGeographicBoundingBox();
        } else if (localName.equalsIgnoreCase("eastBoundLongitude")) {
            eastBoundLongitude = new EastBoundLongitude();
        } else if (localName.equalsIgnoreCase("Decimal")) {
            Decimal = new Decimal();
        } else if (localName.equalsIgnoreCase("northBoundLatitude")) {
            northBoundLatitude = new NorthBoundLatitude();
        } else if (localName.equalsIgnoreCase("southBoundLatitude")) {
            southBoundLatitude = new SouthBoundLatitude();
        } else if (localName.equalsIgnoreCase("westBoundLongitude")) {
            westBoundLongitude = new WestBoundLongitude();
        }

        // Data Quality Info
        else if (localName.equalsIgnoreCase("dataQualityInfo")) {
            dataQualityInfo = new DataQualityInfo();
        } else if (localName.equalsIgnoreCase("DQ_DataQuality")) {
            DQDataQuality = new DQDataQuality();
        } else if (localName.equalsIgnoreCase("lineage")) {
            lineage = new Lineage();
        } else if (localName.equalsIgnoreCase("LI_Lineage")) {
            LILineage = new LILineage();
        } else if (localName.equalsIgnoreCase("statement")) {
            statement = new Statement();
        } else if (localName.equalsIgnoreCase("report")) {
            report = new Report();
        } else if (localName.equalsIgnoreCase("DQ_DomainConsistency")) {
            DQDomainConsistency = new DQDomainConsistency();
        } else if (localName.equalsIgnoreCase("result")) {
            result = new Result();
        } else if (localName.equalsIgnoreCase("DQ_ConformanceResult")) {
            DQConformanceResult = new DQConformanceResult();
        } else if (localName.equalsIgnoreCase("explanation")) {
            explanation = new Explanation();
        } else if (localName.equalsIgnoreCase("pass")) {
            pass = new Pass();
        } else if (localName.equalsIgnoreCase("Boolean")) {
            Boolean = new Boolean();
        } else if (localName.equalsIgnoreCase("specification")) {
            specification = new Specification();
        } else if (localName.equalsIgnoreCase("scope")) {
            scope = new Scope();
        } else if (localName.equalsIgnoreCase("DQ_Scope")) {
            DQScope = new DQScope();
        } else if (localName.equalsIgnoreCase("level")) {
            level = new Level();
        } else if (localName.equalsIgnoreCase("MD_ScopeCode")) {
            MDScopeCode = new MDScopeCode();
            MDScopeCode.setCodeList(atts.getValue("codeList"));
            MDScopeCode.setCodeListValue(atts.getValue("codeListValue"));
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

        if (isInFeed) {
            if (localName.equalsIgnoreCase("feed")) {
                feed.setTotalResults(totalResults);
                feed.setStartIndex(startIndex);
                feed.setItemsPerPage(itemsPerPage);
                feed.setQuery(query);
                feed.setAuthor(author);
                feed.setGenerator(generator);
                feed.setId(feedId);
                feed.setIdentifier(identifierFeed);
                feed.setTitle(title);
                feed.setUpdated(updated);
                feed.setLink(linksFeed);
                feed.setEntriesISO(entries);
            } else if (localName.equalsIgnoreCase("totalResults")) {
                totalResults.setText(chars.toString());
            } else if (localName.equalsIgnoreCase("startIndex")) {
                startIndex.setText(chars.toString());
            } else if (localName.equalsIgnoreCase("itemsPerPage")) {
                itemsPerPage.setText(chars.toString());
                //} else if (localName.equalsIgnoreCase("query")) {
            } else if (localName.equalsIgnoreCase("author")) {
                author.setNameStr(name);
            } else if (localName.equalsIgnoreCase("name")) {
                name = chars.toString();
            } else if (localName.equalsIgnoreCase("generator")) {
                generator = chars.toString();
            } else if (localName.equalsIgnoreCase("id")) {
                feedId = chars.toString();
            } else if (localName.equalsIgnoreCase("identifier")) {
                identifierFeed = chars.toString();
            } else if (localName.equalsIgnoreCase("title")) {
                title = chars.toString();
            } else if (localName.equalsIgnoreCase("updated")) {
                updated = chars.toString();
            } else if (localName.equalsIgnoreCase("link")) {
                linksFeed.add(link);
            }
        }
        if (isInEntry) {
            if (localName.equalsIgnoreCase("entry")) {
                entry.setIdentifier(identifierEntry);
                isIdAdded = false;
                entry.setDate(date);
                entry.setPolygon(polygon);
                entry.setSummary(summary);
                entry.setLink(linksEntry);
                entry.setMDMetadata(mdMetadata);
                entries.add(entry);
                isInEntry = false;
                isInFeed = true;
            } else if (localName.equalsIgnoreCase("id")) {
                entry.setId(chars.toString());
            } else if (localName.equalsIgnoreCase("title")) {
                if (isInCitation) {
                    citationTitle.setCharacterString(CharacterString);
                } else {
                    entry.setTitle(chars.toString());
                }
            } else if (localName.equalsIgnoreCase("identifier") && !isIdAdded) {
                if (isInCitation) {
                    identifier.setRSIdentifier(RSIdentifier);
                } else {
                    identifierEntry = chars.toString();
                    isIdAdded = true;
                }
            } else if (localName.equalsIgnoreCase("updated")) {
                entry.setUpdated(chars.toString());
            } else if (localName.equalsIgnoreCase("polygon")) {
                polygon.setText(chars.toString());
            } else if (localName.equalsIgnoreCase("summary")) {
                summary.setCdata(chars.toString());
            } else if (localName.equalsIgnoreCase("link")) {
                linksEntry.add(link);
            }

            if (isInMDMetadata) {
                // EO MetaData
                if (localName.equalsIgnoreCase("MD_Metadata") || localName.equalsIgnoreCase("MI_Metadata")) {
                    mdMetadata.setFileIdentifier(fileIdentifier);
                    mdMetadata.setLanguage(language);
                    mdMetadata.setHierarchyLevel(hierarchyLevel);
                    mdMetadata.setContact(contact);
                    mdMetadata.setDateStamp(dateStamp);
                    mdMetadata.setMetadataStandardName(metadataStandardName);
                    mdMetadata
                            .setMetadataStandardVersion(metadataStandardVersion);
                    mdMetadata.setIdentificationInfo(identificationInfo);
                    mdMetadata.setDataQualityInfo(dataQualityInfo);
                    isInMDMetadata = false;
                } else if (localName.equalsIgnoreCase("CharacterString")) {
                    CharacterString.setText(chars.toString());
                } else if (localName.equalsIgnoreCase("fileIdentifier")) {
                    fileIdentifier.setCharacterString(CharacterString);
                }
                // Language
                else if (localName.equalsIgnoreCase("language")) {
                    language.setLanguageCode(LanguageCode);
                } else if (localName.equalsIgnoreCase("LanguageCode")) {
                    LanguageCode.setText(chars.toString());
                } else if (localName.equalsIgnoreCase("hierarchyLevel")) {
                    hierarchyLevel.setMDScopeCode(MDScopeCode);
                } else if (localName.equalsIgnoreCase("MD_ScopeCode")) {
                    MDScopeCode.setText(chars.toString());
                }

                // Contact entry
                else if (localName.equalsIgnoreCase("contact")) {
                    contact.setCIResponsibleParty(CIResponsibleParty);
                } else if (localName.equalsIgnoreCase("CI_ResponsibleParty")) {
                    CIResponsibleParty.setIndividualName(individualName);
                    CIResponsibleParty.setOrganisationName(organisationName);
                    CIResponsibleParty.setPositionName(positionName);
                    CIResponsibleParty.setContactInfo(contactInfo);
                    CIResponsibleParty.setRole(role);
                } else if (localName.equalsIgnoreCase("individualName")) {
                    individualName.setCharacterString(CharacterString);
                } else if (localName.equalsIgnoreCase("organisationName")) {
                    organisationName.setCharacterString(CharacterString);
                } else if (localName.equalsIgnoreCase("positionName")) {
                    positionName.setCharacterString(CharacterString);
                } else if (localName.equalsIgnoreCase("contactInfo")) {
                    contactInfo.setCIContact(CIContact);
                } else if (localName.equalsIgnoreCase("CI_Contact")) {
                    CIContact.setAddress(address);
                    CIContact.setOnlineResource(onlineResource);
                    CIContact.setPhone(phone);
                } else if (localName.equalsIgnoreCase("address")) {
                    address.setCIAddress(CIAddress);
                } else if (localName.equalsIgnoreCase("CI_Address")) {
                    CIAddress.setCity(city);
                    CIAddress.setCountry(country);
                    CIAddress.setDeliveryPoint(deliveryPoint);
                    CIAddress.setElectronicMailAddress(electronicMailAddress);
                    CIAddress.setPostalCode(postalCode);
                } else if (localName.equalsIgnoreCase("city")) {
                    city.setCharacterString(CharacterString);
                } else if (localName.equalsIgnoreCase("country")) {
                    country.setCharacterString(CharacterString);
                } else if (localName.equalsIgnoreCase("deliveryPoint")) {
                    deliveryPoint.setCharacterString(CharacterString);
                } else if (localName.equalsIgnoreCase("electronicMailAddress")) {
                    electronicMailAddress.setCharacterString(CharacterString);
                } else if (localName.equalsIgnoreCase("postalCode")) {
                    postalCode.setCharacterString(CharacterString);
                } else if (localName.equalsIgnoreCase("onlineResource")) {
                    onlineResource.setCIOnlineResource(CIOnlineResource);
                } else if (localName.equalsIgnoreCase("CI_OnlineResource")) {
                    CIOnlineResource.setLinkage(linkage);
                } else if (localName.equalsIgnoreCase("linkage")) {
                    linkage.setURL(URL);
                } else if (localName.equalsIgnoreCase("URL")) {
                    URL.setText(chars.toString());
                } else if (localName.equalsIgnoreCase("phone")) {
                    phone.setCITelephone(CITelephone);
                } else if (localName.equalsIgnoreCase("CI_Telephone")) {
                    CITelephone.setFacsimile(facsimile);
                    CITelephone.setVoice(voice);
                } else if (localName.equalsIgnoreCase("facsimile")) {
                    facsimile.setCharacterString(CharacterString);
                } else if (localName.equalsIgnoreCase("voice")) {
                    voice.setCharacterString(CharacterString);
                } else if (localName.equalsIgnoreCase("role")) {
                    role.setCIRoleCode(CIRoleCode);
                } else if (localName.equalsIgnoreCase("CI_RoleCode")) {
                    CIRoleCode.setText(chars.toString());
                } else if (localName.equalsIgnoreCase("dateStamp")) {
                    dateStamp.setDate(dateGco);
                }

                // Metadata Standard
                else if (localName.equalsIgnoreCase("metadataStandardName")) {
                    metadataStandardName.setCharacterString(CharacterString);
                } else if (localName
                        .equalsIgnoreCase("metadataStandardVersion")) {
                    metadataStandardVersion.setCharacterString(CharacterString);
                }

                // Identification Info
                else if (localName.equalsIgnoreCase("identificationInfo")) {
                    identificationInfo
                            .setMDDataIdentification(MDDataIdentification);
                } else if (localName.equalsIgnoreCase("MD_DataIdentification")) {
                    MDDataIdentification.setCitation(citation);
                    MDDataIdentification.setAbstract(_abstract);
                    MDDataIdentification.setPointOfContact(pointOfContact);
                    MDDataIdentification
                            .setDescriptiveKeywords(descriptiveKeywords);
                    MDDataIdentification
                            .setResourceConstraints(resourceConstraints);
                    MDDataIdentification.setLanguage(language);
                    MDDataIdentification.setTopicCategory(topicCategory);
                    MDDataIdentification.setExtents(extents);
                } else if (localName.equalsIgnoreCase("citation")) {
                    citation.setCICitation(CICitation);
                } else if (localName.equalsIgnoreCase("CI_Citation")) {
                    CICitation.setDate(date);
                    CICitation.setIdentifier(identifier);
                    CICitation.setTitle(citationTitle);
                    isInCitation = false;
                } else if (localName.equalsIgnoreCase("CI_Date")) {
                    CIDate.setDateInCIDate(dateInCIDate);
                    CIDate.setDateType(dateType);
                    isInCIDate = false;
                } else if (localName.equals("date")) {
                    if (isInCIDate) {
                        dateInCIDate.setDateGco(dateGco);
                    } else {
                        date.setText(chars.toString());
                        date.setCIDate(CIDate);
                    }
                } else if (localName.equals("Date")) {
                    dateGco.setText(chars.toString());
                } else if (localName.equals("dateType")) {
                    dateType.setCIDateTypeCode(CIDateTypeCode);
                } else if (localName.equalsIgnoreCase("RS_Identifier")) {
                    RSIdentifier.setCode(code);
                    RSIdentifier.setCodeSpace(codeSpace);
                } else if (localName.equalsIgnoreCase("code")) {
                    code.setCharacterString(CharacterString);
                } else if (localName.equalsIgnoreCase("codeSpace")) {
                    codeSpace.setCharacterString(CharacterString);
                } else if (localName.equalsIgnoreCase("abstract")) {
                    _abstract.setCharacterString(CharacterString);
                } else if (localName.equalsIgnoreCase("pointOfContact")) {
                    pointOfContact.setCIResponsibleParty(CIResponsibleParty);
                } else if (localName.equalsIgnoreCase("descriptiveKeywords")) {
                    descriptiveKeyword.setMDKeywords(MDKeywords);
                    descriptiveKeywords.add(descriptiveKeyword);
                } else if (localName.equalsIgnoreCase("MD_Keywords")) {
                    MDKeywords.setKeywords(keywords);
                } else if (localName.equalsIgnoreCase("keyword")) {
                    keyword.setCharacterString(CharacterString);
                    keywords.add(keyword);
                } else if (localName.equalsIgnoreCase("resourceConstraints")) {
                    resourceConstraint.setMDConstraints(MDConstraints);
                    resourceConstraint
                            .setMDLegalConstraints(MDLegalConstraints);
                    resourceConstraints.add(resourceConstraint);
                } else if (localName.equalsIgnoreCase("MD_Constraints")) {
                    MDConstraints.setUseLimitation(useLimitation);
                } else if (localName.equalsIgnoreCase("useLimitation")) {
                    useLimitation.setCharacterString(CharacterString);
                } else if (localName.equalsIgnoreCase("MD_LegalConstraints")) {
                    MDLegalConstraints.setAccessConstraints(accessConstraints);
                    MDLegalConstraints.setOtherConstraints(otherConstraints);
                } else if (localName.equalsIgnoreCase("accessConstraints")) {
                    accessConstraints.setMDRestrictionCode(MDRestrictionCode);
                } else if (localName.equalsIgnoreCase("MD_RestrictionCode")) {
                    MDRestrictionCode.setText(chars.toString());
                } else if (localName.equalsIgnoreCase("otherConstraints")) {
                    otherConstraints.setCharacterString(CharacterString);
                } else if (localName.equalsIgnoreCase("topicCategory")) {
                    topicCategory.setMDTopicCategoryCode(MDTopicCategoryCode);
                } else if (localName.equalsIgnoreCase("MD_TopicCategoryCode")) {
                    MDTopicCategoryCode.setText(chars.toString());
                } else if (localName.equalsIgnoreCase("extent")) {
                    if (isInEXTemp) {
                        extentInEXTemp.setTimePeriod(TimePeriod);
                    } else {
                        extent.setEXExtent(EXExtent);
                        extents.add(extent);
                    }
                } else if (localName.equalsIgnoreCase("EX_Extent")) {
                    EXExtent.setTemporalElement(temporalElement);
                    EXExtent.setGeographicElement(geographicElement);
                } else if (localName.equalsIgnoreCase("temporalElement")) {
                    temporalElement.setEXTemporalExtent(EXTemporalExtent);
                } else if (localName.equalsIgnoreCase("EX_TemporalExtent")) {
                    EXTemporalExtent.setExtentInEXTemp(extentInEXTemp);
                    isInEXTemp = false;
                    // } else if (localName.equalsIgnoreCase("extent") &&
                    // isInEXTemp) {
                    // extentInEXTemp.setTimePeriod(TimePeriod);
                } else if (localName.equalsIgnoreCase("TimePeriod")) {
                    TimePeriod.setBeginPosition(beginPosition);
                    TimePeriod.setEndPosition(endPosition);
                } else if (localName.equalsIgnoreCase("beginPosition")) {
                    beginPosition.setText(chars.toString());
                } else if (localName.equalsIgnoreCase("endPosition")) {
                    endPosition.setText(chars.toString());
                } else if (localName.equalsIgnoreCase("geographicElement")) {
                    geographicElement
                            .setEXGeographicBoundingBox(EXGeographicBoundingBox);
                } else if (localName
                        .equalsIgnoreCase("EX_GeographicBoundingBox")) {
                    EXGeographicBoundingBox
                            .setEastBoundLongitude(eastBoundLongitude);
                    EXGeographicBoundingBox
                            .setNorthBoundLatitude(northBoundLatitude);
                    EXGeographicBoundingBox
                            .setSouthBoundLatitude(southBoundLatitude);
                    EXGeographicBoundingBox
                            .setWestBoundLongitude(westBoundLongitude);
                } else if (localName.equalsIgnoreCase("eastBoundLongitude")) {
                    eastBoundLongitude.setDecimal(Decimal);
                } else if (localName.equalsIgnoreCase("Decimal")) {
                    Decimal.setText(chars.toString());
                } else if (localName.equalsIgnoreCase("northBoundLatitude")) {
                    northBoundLatitude.setDecimal(Decimal);
                } else if (localName.equalsIgnoreCase("southBoundLatitude")) {
                    southBoundLatitude.setDecimal(Decimal);
                } else if (localName.equalsIgnoreCase("westBoundLongitude")) {
                    westBoundLongitude.setDecimal(Decimal);
                }

                // Data Quality Info
                else if (localName.equalsIgnoreCase("dataQualityInfo")) {
                    dataQualityInfo.setDQDataQuality(DQDataQuality);
                } else if (localName.equalsIgnoreCase("DQ_DataQuality")) {
                    DQDataQuality.setLineage(lineage);
                    DQDataQuality.setReport(report);
                    DQDataQuality.setScope(scope);
                } else if (localName.equalsIgnoreCase("lineage")) {
                    lineage.setLILineage(LILineage);
                } else if (localName.equalsIgnoreCase("LI_Lineage")) {
                    LILineage.setStatement(statement);
                } else if (localName.equalsIgnoreCase("statement")) {
                    statement.setCharacterString(CharacterString);
                } else if (localName.equalsIgnoreCase("report")) {
                    report.setDQDomainConsistency(DQDomainConsistency);
                } else if (localName.equalsIgnoreCase("DQ_DomainConsistency")) {
                    DQDomainConsistency.setResult(result);
                } else if (localName.equalsIgnoreCase("result")) {
                    result.setDQConformanceResult(DQConformanceResult);
                } else if (localName.equalsIgnoreCase("DQ_ConformanceResult")) {
                    DQConformanceResult.setExplanation(explanation);
                    DQConformanceResult.setPass(pass);
                    DQConformanceResult.setSpecification(specification);
                } else if (localName.equalsIgnoreCase("explanation")) {
                    explanation.setCharacterString(CharacterString);
                } else if (localName.equalsIgnoreCase("pass")) {
                    pass.setBoolean(Boolean);
                } else if (localName.equalsIgnoreCase("Boolean")) {
                    Boolean.setText(chars.toString());
                } else if (localName.equalsIgnoreCase("specification")) {
                    specification.setCICitation(CICitation);
                } else if (localName.equalsIgnoreCase("scope")) {
                    scope.setDQScope(DQScope);
                } else if (localName.equalsIgnoreCase("DQ_Scope")) {
                    DQScope.setLevel(level);
                } else if (localName.equalsIgnoreCase("level")) {
                    level.setMDScopeCode(MDScopeCode);
                } else if (localName.equalsIgnoreCase("MD_ScopeCode")) {
                    MDScopeCode.setText(chars.toString());
                }

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