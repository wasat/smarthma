package pl.wasat.smarthma.ui.frags.common;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.MessageFormat;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.customviews.TextViewWithFont;
import pl.wasat.smarthma.helper.enums.MetadataType;
import pl.wasat.smarthma.model.entry.Entry;
import pl.wasat.smarthma.ui.frags.base.BaseMetadataFragment;
import pl.wasat.smarthma.utils.rss.XmlSaxParser;
import pl.wasat.smarthma.utils.text.MetadataCleaner;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Use the {@link MetadataFragment#newInstance} factory
 * method to create an instance of this fragment.
 */
public class MetadataFragment extends BaseMetadataFragment {
    private static final String KEY_ENTRY_ITEM = "pl.wasat.smarthma.KEY_ENTRY_ITEM";

    private Entry entryItem;
    private MetadataType type;


    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @param entryItm Parameter 1.
     * @return A new instance of fragment MetadataFragment.
     */
    public static MetadataFragment newInstance(Entry entryItm) {
        MetadataFragment fragment = new MetadataFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_ENTRY_ITEM, entryItm);
        fragment.setArguments(args);
        return fragment;
    }

    public MetadataFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            entryItem = (Entry) getArguments().getSerializable(KEY_ENTRY_ITEM);
            type = entryItem != null ? entryItem.getMetadataType() : null;
        }
        chooseMetadataParser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_metadata, container,
                false);

        linearLayout = (LinearLayout) rootView
                .findViewById(R.id.metadata_fragment_layout_main_linear);
        ((TextView) rootView.findViewById(R.id.metadata_frag_tv_prod_name))
                .setText(entryItem.getTitle());
        ((TextView) rootView.findViewById(R.id.metadata_frag_tv_prod_dates))
                .setText(MessageFormat.format("{0}{1}", getString(R.string.date_of_publication), entryItem.getDate()));

        chooseMetadataView();

        return rootView;
    }

    private void chooseMetadataView() {
        switch (type) {
            case OM:
                prepareOMMetadataView();
                break;
            case ISO:
                prepareISOMetadataView();
                break;
            case OM11:
                prepareOMMetadataView();
                break;
            case DC:
                prepareDCMetadataView();
                break;
            case NONE:
                break;
            default:
                break;
        }
    }

    private void chooseMetadataParser() {
        XmlSaxParser xmlSaxParser = new XmlSaxParser();
        switch (type) {
            case OM:
                if (entryItem.getEarthObservation() == null) {
                    xmlSaxParser.parseOMMetadata(entryItem);
                }
                break;
            case ISO:
                if (entryItem.getMDMetadata() == null) {
                    xmlSaxParser.parseISOMetadata(entryItem);
                }
                break;
            case OM11:
                break;
            case DC:
                if (entryItem.getDc() == null) {
                    xmlSaxParser.parseDCMetadata(entryItem);
                }
                break;
            case NONE:
                break;
            default:
                break;
        }
    }

    private void prepareOMMetadataView() {
        try {
            if (!entryItem.getEarthObservation().getMetaDataProperty()
                    .getEarthObservationMetaData().getIdentifier().get_text()
                    .isEmpty()) {
                setMetaDataViews(getString(R.string.eo_metadata), entryItem
                        .getEarthObservation().getMetaDataProperty()
                        .getEarthObservationMetaData());
            }
            if (!entryItem.getEarthObservation().getObservedProperty()
                    .get_nilReason().isEmpty()) {
                setMetaDataViews(getString(R.string.eo_observed_property),
                        entryItem.getEarthObservation().getObservedProperty());
            }
            if (entryItem.getEarthObservation().getFeatureOfInterest()
                    .getFootprint() != null) {
                setMetaDataViews(getString(R.string.eo_footprint), entryItem
                        .getEarthObservation().getFeatureOfInterest()
                        .getFootprint());
            }
            if (!entryItem.getEarthObservation().getProcedure()
                    .getEarthObservationEquipment().getPlatform().isEmpty()) {
                setMetaDataViews(getString(R.string.eo_equipment), entryItem
                        .getEarthObservation().getProcedure()
                        .getEarthObservationEquipment());
            }
            if (entryItem.getEarthObservation().getResult()
                    .getEarthObservationResult() != null) {
                setMetaDataViews(getString(R.string.eo_result), entryItem
                        .getEarthObservation().getResult()
                        .getEarthObservationResult());
            }
            if (entryItem.getEarthObservation().getPhenomenonTime() != null) {
                setMetaDataViews(getString(R.string.eo_phenomenon_time), entryItem
                        .getEarthObservation().getPhenomenonTime().getTimePeriod());
            }
            if (entryItem.getEarthObservation().getResultTime().getTimeInstant() != null) {
                setMetaDataViews(getString(R.string.eo_result_time), entryItem
                        .getEarthObservation().getResultTime().getTimeInstant()
                        .getTimePosition());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void prepareISOMetadataView() {
        try {
            if (!entryItem.getMDMetadata().getFileIdentifier().getCharacterString()
                    .getText().isEmpty()) {
                setMetaDataViews(getString(R.string.metadata_file_id), entryItem.getMDMetadata()
                        .getFileIdentifier());
            }
            if (entryItem.getMDMetadata().getLanguage() != null) {
                setMetaDataViews(getString(R.string.metadata_language), entryItem.getMDMetadata()
                        .getLanguage());
            }
            if (entryItem.getMDMetadata().getHierarchyLevel().getMDScopeCode()
                    .getText().isEmpty()) {
                setMetaDataViews(getString(R.string.metadata_hierarchy_level), entryItem
                        .getMDMetadata().getHierarchyLevel());
            }
            if (!entryItem.getMDMetadata().getContact().getCIResponsibleParty()
                    .getOrganisationName().getCharacterString().getText().isEmpty()) {
                setMetaDataViews(getString(R.string.eo_provider_contact), entryItem
                        .getMDMetadata().getContact().getCIResponsibleParty());
            }
            if (entryItem.getMDMetadata().getDateStamp().getDateGco().getText()
                    .isEmpty()) {
                setMetaDataViews(getString(R.string.eo_image_date), entryItem
                        .getMDMetadata().getDateStamp());
            }
            if (entryItem.getMDMetadata().getMetadataStandardName()
                    .getCharacterString().getText().isEmpty()) {
                setMetaDataViews(getString(R.string.metadata_standards), entryItem.getMDMetadata()
                        .getMetadataStandardName());
            }
            if (!entryItem.getMDMetadata().getMetadataStandardVersion()
                    .getCharacterString().getText().isEmpty()) {
                setMetaDataViews(getString(R.string.metadata_standards), entryItem.getMDMetadata()
                        .getMetadataStandardVersion());
            }
            if (entryItem.getMDMetadata().getIdentificationInfo()
                    .getMDDataIdentification() != null) {
                setMetaDataViews(getString(R.string.metadata_identification), entryItem
                        .getMDMetadata().getIdentificationInfo()
                        .getMDDataIdentification());
            }
            if (entryItem.getMDMetadata().getDataQualityInfo().getDQDataQuality() != null) {
                setMetaDataViews(getString(R.string.metadata_quality), entryItem
                        .getMDMetadata().getDataQualityInfo().getDQDataQuality());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void prepareDCMetadataView() {
        try {
            if (!entryItem.getDc().getIdentifier().getText().isEmpty()) {
                setMetaDataViews(getString(R.string.metadata_dc_title), entryItem.getDc());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String obtainCleanMetadata(String value) {
        String cleanValue = "";
        switch (type) {
            case OM:
                cleanValue = MetadataCleaner.getCleanOMValue(value);
                break;
            case ISO:
                cleanValue = MetadataCleaner.getCleanISOValue(value);
                break;
            case OM11:
                cleanValue = MetadataCleaner.getCleanOMValue(value);
                break;
            case DC:
                cleanValue = MetadataCleaner.getCleanDCValue(value);
                break;
            case NONE:
                break;
            default:
                break;
        }
        return cleanValue;
    }

    @NonNull
    @Override
    protected TextViewWithFont defValueItemView(String value) {
        value = obtainCleanMetadata(value);
        return super.defValueItemView(value);
    }


}
