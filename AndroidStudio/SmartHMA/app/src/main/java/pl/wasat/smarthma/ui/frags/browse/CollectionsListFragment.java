package pl.wasat.smarthma.ui.frags.browse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.ArrayList;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.SmartHMApplication;
import pl.wasat.smarthma.adapter.CollectionsListAdapter;
import pl.wasat.smarthma.model.Collection;
import pl.wasat.smarthma.ui.activities.CollectionsBrowserActivity;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link CollectionsListFragment.OnCollectionsListFragmentListener} interface
 * to handle interaction events. Use the
 * {@link CollectionsListFragment#newInstance} factory method to create an
 * instance of this fragment.
 */
public class CollectionsListFragment extends Fragment {

    private static final String KEY_COLLECTIONS_GROUP_LIST_POSITION = "pl.wasat.smarthma.KEY_COLLECTIONS_GROUP_LIST_POSITION";
    private static final String KEY_COLLECTIONS_GROUP_NAME = "pl.wasat.smarthma.KEY_COLLECTIONS_GROUP_NAME";
    public static final String KEY_COLLECTIONS_NAME = "pl.wasat.smarthma.KEY_COLLECTIONS_NAME";

    private int parentListPos;
    private String selectGroupName;
    private OnCollectionsListFragmentListener mListener;

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @param listPosParam Parameter 1.
     * @return A new instance of fragment BlankFragment.
     */
    public static CollectionsListFragment newInstance(int listPosParam) {
        CollectionsListFragment fragment = new CollectionsListFragment();
        String groupName = SmartHMApplication.GlobalEODataList
                .getCollectionsGroupList().get(listPosParam).getGroupName();
        Bundle args = new Bundle();
        args.putInt(KEY_COLLECTIONS_GROUP_LIST_POSITION, listPosParam);
        args.putString(KEY_COLLECTIONS_GROUP_NAME, groupName);
        fragment.setArguments(args);
        return fragment;
    }

    public CollectionsListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            parentListPos = getArguments().getInt(
                    KEY_COLLECTIONS_GROUP_LIST_POSITION);
            selectGroupName = getArguments().getString(
                    KEY_COLLECTIONS_GROUP_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_collections_list, container,
                false);
    }

    /*
     * (non-Javadoc)
     *
     * @see android.support.v4.app.Fragment#onViewCreated(android.view.View,
     * android.os.Bundle)
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);
        initList();
    }

    private void initList() {
        // noinspection ConstantConditions
        ListView list = (ListView) getView()
                .findViewById(R.id.collections_list);

        // Getting adapter by passing xml data ArrayList

        if (!SmartHMApplication.GlobalEODataList.getCollectionsGroupList()
                .isEmpty()) {
            final ArrayList<Collection> collections = SmartHMApplication.GlobalEODataList
                    .getCollectionsGroupList().get(parentListPos)
                    .getCollections();
            CollectionsListAdapter adapter = new CollectionsListAdapter(
                    getActivity(), collections, selectGroupName);
            list.setAdapter(adapter);

            // Click event for single list row
            list.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    String collName = collections.get(position).getName();
                    loadDataSeriesFeedsActivity(collName);

                }
            });
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed() {
        if (mListener != null) {
            mListener.onFragmentInteraction();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnCollectionsListFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnCollectionsListFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void loadDataSeriesFeedsActivity(String collName) {
        Intent dsFeedsIntent = new Intent(getActivity(),
                CollectionsBrowserActivity.class);
        dsFeedsIntent.putExtra(KEY_COLLECTIONS_NAME, collName);
        startActivity(dsFeedsIntent);
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
    public interface OnCollectionsListFragmentListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction();
    }

}
