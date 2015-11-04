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
import pl.wasat.smarthma.model.iso.EntryISO;
import pl.wasat.smarthma.ui.frags.base.BaseMetadataFragment;
import pl.wasat.smarthma.utils.text.MetadataCleaner;
import pl.wasat.smarthma.utils.time.DateUtils;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Use the {@link MetadataISOFragment#newInstance} factory
 * method to create an instance of this fragment.
 */
public class MetadataISOFragment extends BaseMetadataFragment {
    private static final String KEY_ENTRY_ISO_ITEM = "pl.wasat.smarthma.KEY_ENTRY_ISO_ITEM";

    private EntryISO entryItem;


    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @param entryItm Parameter 1.
     * @return A new instance of fragment MetadataFragment.
     */
    public static MetadataISOFragment newInstance(EntryISO entryItm) {
        MetadataISOFragment fragment = new MetadataISOFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_ENTRY_ISO_ITEM, entryItm);
        fragment.setArguments(args);
        return fragment;
    }

    public MetadataISOFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            entryItem = (EntryISO) getArguments().getSerializable(KEY_ENTRY_ISO_ITEM);
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
                .setText(MessageFormat.format("{0}{1}", getString(R.string.date_of_publication), DateUtils.getISOPubDate(entryItem)));

        prepareISOMetadataView();
        return rootView;
    }

    private void prepareISOMetadataView() {
        try {
            //if (!entryItem.getMDMetadata().getFileIdentifier().getCharacterString()
            //        .getText().isEmpty()) {
            setMetaDataViews(getString(R.string.metadata_file_id), entryItem.getMDMetadata()
                    .getFileIdentifier());
            //}
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //if (!entryItem.getMDMetadata().getLanguage().getLanguageCode()
            //        .getText().isEmpty()) {
            setMetaDataViews(getString(R.string.metadata_language), entryItem.getMDMetadata()
                    .getLanguage());
            //}
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //if (entryItem.getMDMetadata().getHierarchyLevel().getMDScopeCode()
            //       .getText().isEmpty()) {
            setMetaDataViews(getString(R.string.metadata_hierarchy_level), entryItem
                    .getMDMetadata().getHierarchyLevel());
            //}
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            // if (!entryItem.getMDMetadata().getContact().getCIResponsibleParty()
            //         .getOrganisationName().getCharacterString().getText().isEmpty()) {
            setMetaDataViews(getString(R.string.eo_provider_contact), entryItem
                    .getMDMetadata().getContact().getCIResponsibleParty());
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //if (entryItem.getMDMetadata().getDateStamp().getDateGco().getText()
            //        .isEmpty()) {
            setMetaDataViews(getString(R.string.eo_image_date), entryItem
                    .getMDMetadata().getDateStamp());
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            // if (entryItem.getMDMetadata().getMetadataStandardName()
            //         .getCharacterString().getText().isEmpty()) {
            setMetaDataViews(getString(R.string.metadata_standards), entryItem.getMDMetadata()
                    .getMetadataStandardName());
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            // if (!entryItem.getMDMetadata().getMetadataStandardVersion()
            //         .getCharacterString().getText().isEmpty()) {
            setMetaDataViews(getString(R.string.metadata_standards), entryItem.getMDMetadata()
                    .getMetadataStandardVersion());
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            // if (entryItem.getMDMetadata().getIdentificationInfo()
            //         .getMDDataIdentification() != null) {
            setMetaDataViews(getString(R.string.metadata_identification), entryItem
                    .getMDMetadata().getIdentificationInfo()
                    .getMDDataIdentification());
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //  if (entryItem.getMDMetadata().getDataQualityInfo().getDQDataQuality() != null) {
            setMetaDataViews(getString(R.string.metadata_quality), entryItem
                    .getMDMetadata().getDataQualityInfo().getDQDataQuality());
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    protected TextViewWithFont defValueItemView(String value) {
        value = MetadataCleaner.getCleanISOValue(value);
        return super.defValueItemView(value);
    }
}
