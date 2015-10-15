/*
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
import pl.wasat.smarthma.model.entry.Entry;
import pl.wasat.smarthma.ui.frags.base.BaseMetadataFragment;
import pl.wasat.smarthma.utils.rss.XmlSaxParser;
import pl.wasat.smarthma.utils.text.MetadataCleaner;

*/
/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Use the {@link MetadataOMFragment#newInstance} factory
 * method to create an instance of this fragment.
 *//*

public class MetadataOMFragment extends BaseMetadataFragment {
    private static final String KEY_ENTRY_OM_ITEM = "pl.wasat.smarthma.KEY_ENTRY_OM_ITEM";

    private Entry entryItem;


    */
/**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @param entryItm Parameter 1.
     * @return A new instance of fragment MetadataFragment.
 *//*

    public static MetadataOMFragment newInstance(Entry entryItm) {
        MetadataOMFragment fragment = new MetadataOMFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_ENTRY_OM_ITEM, entryItm);
        fragment.setArguments(args);
        return fragment;
    }

    public MetadataOMFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            entryItem = (Entry) getArguments().getSerializable(KEY_ENTRY_OM_ITEM);
        }
        if (entryItem.getEarthObservation() == null) {
            XmlSaxParser xmlSaxParser = new XmlSaxParser();
            xmlSaxParser.parseOMMetadata(entryItem);
        }
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
        return rootView;
    }

    @NonNull
    @Override
    protected TextViewWithFont defValueItemView(String value) {
        value = MetadataCleaner.getCleanOMValue(value);
        return super.defValueItemView(value);
    }

}
*/
