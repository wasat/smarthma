package pl.wasat.smarthma.ui.frags.common;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TextView;

import org.apache.commons.lang3.SystemUtils;

import java.lang.reflect.Field;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.model.iso.EntryISO;
import pl.wasat.smarthma.utils.time.DateUtils;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link MetadataISOFragment.OnMetadataISOFragmentListener} interface to handle
 * interaction events. Use the {@link MetadataISOFragment#newInstance} factory
 * method to create an instance of this fragment.
 */
public class MetadataISOFragment extends Fragment {
    private static final String KEY_ENTRY_ITEM = "pl.wasat.smarthma.KEY_ENTRY_ITEM";

    private EntryISO entryItem;

    private OnMetadataISOFragmentListener mListener;

    private LinearLayout linearLayout;

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
        args.putSerializable(KEY_ENTRY_ITEM, entryItm);
        fragment.setArguments(args);
        return fragment;
    }

    public MetadataISOFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            entryItem = (EntryISO) getArguments().getSerializable(KEY_ENTRY_ITEM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_metadata, container,
                false);

        linearLayout = (LinearLayout) rootView
                .findViewById(R.id.metadata_fragment_layout_main_linear);
        ((TextView) rootView.findViewById(R.id.metadata_frag_tv_prod_name))
                .setText(entryItem.getTitle());
        ((TextView) rootView.findViewById(R.id.metadata_frag_tv_prod_dates))
                .setText("Date of publication: " + DateUtils.getISOPubDate(entryItem));

        if (!entryItem.getMDMetadata().getFileIdentifier().getCharacterString()
                .getText().isEmpty()) {
            setISOMetaDataViews("Metadata File ID ", entryItem.getMDMetadata()
                    .getFileIdentifier());
        }
        if (!entryItem.getMDMetadata().getLanguage().getLanguageCode()
                .getText().isEmpty()) {
            setISOMetaDataViews("Metadata Language ", entryItem.getMDMetadata()
                    .getLanguage());
        }
        if (entryItem.getMDMetadata().getHierarchyLevel().getMDScopeCode()
                .getText().isEmpty()) {
            setISOMetaDataViews("Metadata Hierarchy Level ", entryItem
                    .getMDMetadata().getHierarchyLevel());
        }
        if (!entryItem.getMDMetadata().getContact().getCIResponsibleParty()
                .getOrganisationName().getCharacterString().getText().isEmpty()) {
            setISOMetaDataViews("Earth Observation Provider Contact ", entryItem
                    .getMDMetadata().getContact().getCIResponsibleParty());
        }
        if (entryItem.getMDMetadata().getDateStamp().getDateGco().getText()
                .isEmpty()) {
            setISOMetaDataViews("Earth Observation Image Date ", entryItem
                    .getMDMetadata().getDateStamp());
        }
        if (entryItem.getMDMetadata().getMetadataStandardName()
                .getCharacterString().getText().isEmpty()) {
            setISOMetaDataViews("Metadata Standards ", entryItem.getMDMetadata()
                    .getMetadataStandardName());
        }
        if (!entryItem.getMDMetadata().getMetadataStandardVersion()
                .getCharacterString().getText().isEmpty()) {
            setISOMetaDataViews("Metadata Standards ", entryItem.getMDMetadata()
                    .getMetadataStandardVersion());
        }
        if (entryItem.getMDMetadata().getIdentificationInfo()
                .getMDDataIdentification() != null) {
            setISOMetaDataViews("Metadata Identification ", entryItem
                    .getMDMetadata().getIdentificationInfo()
                    .getMDDataIdentification());
        }
        if (entryItem.getMDMetadata().getDataQualityInfo().getDQDataQuality() != null) {
            setISOMetaDataViews("Metadata Quality ", entryItem
                    .getMDMetadata().getDataQualityInfo().getDQDataQuality());
        }
        return rootView;
    }

    private void setISOMetaDataViews(String headerText, Object eOMetaDataObject) {

        TextView tvMetaHeader = new TextView(getActivity());
        tvMetaHeader.setLayoutParams(new TableLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1f));
        tvMetaHeader.setPadding(16, 2, 16, 16);
        tvMetaHeader.setTextSize(18);
        tvMetaHeader.setTypeface(Typeface.DEFAULT_BOLD);
        tvMetaHeader.setText(headerText);
        linearLayout.addView(tvMetaHeader);

        Class<?> c1 = eOMetaDataObject.getClass();
        Field[] fields = c1.getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(eOMetaDataObject);
            } catch (IllegalAccessException | IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (value != null) {
                addNewRowView(name, value.toString());
            }
        }
    }

    private void addNewRowView(String title, String value) {
        if (!title.equalsIgnoreCase("additionalProperties")
                && !title.equalsIgnoreCase("_gml_id")
                && !title.equalsIgnoreCase("serialVersionUID")
                && !value.isEmpty())

        {
            TextView itemTvTitle = new TextView(getActivity());
            itemTvTitle.setLayoutParams(new TableLayout.LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 2f));
            itemTvTitle.setPadding(10, 2, 2, 2);
            itemTvTitle.setTextSize(16);
            itemTvTitle.setTextColor(0xffA9A9A9);
            itemTvTitle.setTypeface(Typeface.DEFAULT_BOLD);
            itemTvTitle.setText(title);

            TextView itemTvValue = new TextView(getActivity());
            itemTvValue.setLayoutParams(new TableLayout.LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1f));
            itemTvValue.setPadding(10, 2, 2, 2);
            itemTvValue.setTextSize(14);
            itemTvValue.setTextColor(0xff646464);
            itemTvValue.setTypeface(Typeface.DEFAULT);
            itemTvValue.setText(cleanValue(value));

            LinearLayout itemLinearRow = new LinearLayout(getActivity());
            itemLinearRow.setLayoutParams(new TableLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, 1f));
            itemLinearRow.setPadding(10, 2, 2, 2);
            itemLinearRow.addView(itemTvTitle);
            itemLinearRow.addView(itemTvValue);

            linearLayout.addView(itemLinearRow);
        }
    }

    private String cleanValue(String value) {
        String cleanedValue = value;

        String newLine = SystemUtils.LINE_SEPARATOR;
        String doubleNewLine = SystemUtils.LINE_SEPARATOR
                + SystemUtils.LINE_SEPARATOR;
        String spaceTwoNewLine = SystemUtils.LINE_SEPARATOR + " "
                + SystemUtils.LINE_SEPARATOR + " ";

        cleanedValue = cleanedValue.replaceAll("CharacterString - Text - ", "");

        cleanedValue = cleanedValue.replaceAll("CIDate - dateInCIDate - dateGco - Text - ", "");

        cleanedValue = cleanedValue.replaceAll("Decimal - Text - ", "");

        cleanedValue = cleanedValue.replaceAll("Text - ", "");

        cleanedValue = cleanedValue.replaceAll("_abstract", "abstract");

        cleanedValue = cleanedValue.replaceAll("CharacterString", "name");

        cleanedValue = cleanedValue.replaceAll(doubleNewLine, newLine);
        cleanedValue = cleanedValue.trim().replaceAll(" +", " ");
        cleanedValue = cleanedValue.replaceAll("\\[", "");
        cleanedValue = cleanedValue.replaceAll("\\]", "");
        cleanedValue = cleanedValue.replaceAll(doubleNewLine, newLine);
        cleanedValue = cleanedValue.replaceAll(" _", "");
        cleanedValue = cleanedValue.replaceAll("_", "");
        cleanedValue = cleanedValue.replaceAll(newLine + ", ", "," + newLine);
        cleanedValue = cleanedValue.replaceAll(spaceTwoNewLine, "");
        cleanedValue = cleanedValue.replaceAll(doubleNewLine, newLine);

        cleanedValue = changeUpperCase(cleanedValue);
        return cleanedValue;

    }

    private String changeUpperCase(String inputString) {

        if (inputString.isEmpty()) {
            return inputString;
        }
        String outputString = String.valueOf(inputString.charAt(0));

        for (int i = 1; i < inputString.length(); i++) {
            char cThis = inputString.charAt(i);
            char cPrev = inputString.charAt(i - 1);
            if (Character.isUpperCase(cThis) && Character.isLowerCase(cPrev)) {
                outputString = outputString + " "
                        + Character.toLowerCase(cThis);
            } else {
                outputString = outputString + cThis;
            }

        }
        return outputString;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnMetadataISOFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnMetadataISOFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated to
     * the activity and potentially other fragments contained in that activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnMetadataISOFragmentListener {
    }

}
