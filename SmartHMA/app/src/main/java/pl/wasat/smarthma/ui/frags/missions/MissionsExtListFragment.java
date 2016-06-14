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

package pl.wasat.smarthma.ui.frags.missions;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.customviews.ExpandableListAdapter;
import pl.wasat.smarthma.model.mission.MissionHeaderData;
import pl.wasat.smarthma.model.mission.MissionItemData;
import pl.wasat.smarthma.parser.database.ParserDb;
import pl.wasat.smarthma.parser.missions.EsaEoMissions.EsaEoMissions;
import pl.wasat.smarthma.parser.missions.EsaEuemsat.EsaEumetsat;
import pl.wasat.smarthma.parser.missions.EsaFutureMissions.Adm;
import pl.wasat.smarthma.parser.missions.EsaFutureMissions.EarthCare;
import pl.wasat.smarthma.parser.missions.EsaFutureMissions.EsaFutureMissions;
import pl.wasat.smarthma.parser.missions.HistoricalMissions.HistoricalMissions;
import pl.wasat.smarthma.parser.missions.PotentialMissions.PotentialMissions;
import pl.wasat.smarthma.parser.missions.ThirdPartyMissions.ThirdPartyMissions;
import pl.wasat.smarthma.parser.model.Category;
import pl.wasat.smarthma.parser.model.Mission;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the
 * {@link MissionsExtListFragment.OnExtendedListFragmentListener} interface to
 * handle interaction events. Use the {@link MissionsExtListFragment#newInstance}
 * factory method to create an instance of this fragment.
 */
public class MissionsExtListFragment extends Fragment {

    private List<MissionHeaderData> listDataHeader;
    private HashMap<String, List<MissionItemData>> listDataChild;

    private OnExtendedListFragmentListener mListener;
    private ExpandableListView expListView;
    private ParserDb parserDb;

    /**
     * Instantiates a new Missions ext list fragment.
     */
    public MissionsExtListFragment() {
    }

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @return A new instance of fragment ExtendedListFragment.
     */
    public static MissionsExtListFragment newInstance() {
        return new MissionsExtListFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = context instanceof Activity ? (Activity) context : null;
        try {
            mListener = (OnExtendedListFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + activity.getString(R.string.must_implement)
                    + OnExtendedListFragmentListener.class.getSimpleName());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_missions_extended_list,
                container, false);

        /*initialize database*/
        parserDb = new ParserDb(getActivity());
        parserDb.open();

        expListView = (ExpandableListView) rootView
                .findViewById(R.id.missions_extended_listview);
        expListView.setGroupIndicator(null);

        prepareListData();

        ExpandableListAdapter listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader,
                listDataChild);
        expListView.setAdapter(listAdapter);
        expListView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return false;
            }
        });

        //expListView.
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });

        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
            }
        });

        expListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                startMissionDetailFragment(getMissionItemData(groupPosition, childPosition));
                return false;
            }
        });

        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        // Adding child data
        listDataHeader
                .add(new MissionHeaderData(0, getActivity().getString(R.string.esa_eo_missions),
                        "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions"));
        listDataHeader
                .add(new MissionHeaderData(1,
                        getActivity().getString(R.string.esa_future_missions_sentinels),
                        "https://earth.esa.int/web/guest/missions/esa-future-missions"));
        listDataHeader
                .add(new MissionHeaderData(2,
                        getActivity().getString(R.string.esa_future_missions_explorers),
                        "https://earth.esa.int/web/guest/missions/esa-future-missions"));
        listDataHeader
                .add(new MissionHeaderData(4,
                        getActivity().getString(R.string.third_party_missions_current),
                        "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions"));
        listDataHeader
                .add(new MissionHeaderData(5,
                        getActivity().getString(R.string.third_party_missions_historical),
                        "https://earth.esa.int/web/guest/missions/3rd-party-missions/historical-missions"));
        listDataHeader
                .add(new MissionHeaderData(6,
                        getActivity().getString(R.string.third_party_missions_potential),
                        "https://earth.esa.int/web/guest/missions/3rd-party-missions/potential-missions"));
        listDataHeader.add(new MissionHeaderData(7, "ESA / EUMETSAT",
                "https://earth.esa.int/web/guest/missions/esaeumetsat"));

        // Adding child data

        ArrayList<Mission> missionList = parserDb.getMissionsList(new Category(EsaEoMissions.CATEGORY_ID, "asd"));
        List<MissionItemData> esaEoMissions = new ArrayList<>();
        for (Mission mission : missionList) {
            esaEoMissions.add(new MissionItemData(mission));
        }

        ArrayList<Mission> futureSentinelsMissionList = parserDb.getMissionsList(new Category(EsaFutureMissions.CATEGORY_ID, "asd"));
        List<MissionItemData> esaFutureSentinels = new ArrayList<>();
        List<MissionItemData> esaFutureExplorers = new ArrayList<>();
        for (Mission mission :
                futureSentinelsMissionList) {
            if (mission.getId() == Adm.MISSION_ID || mission.getId() == EarthCare.MISSION_ID) {
                esaFutureExplorers.add(new MissionItemData(mission));
            } else {
                esaFutureSentinels.add(new MissionItemData(mission));
            }
        }


        ArrayList<Mission> thirdPartyCurrentList = parserDb.getMissionsList(new Category(ThirdPartyMissions.CATEGORY_ID, "asd"));
        List<MissionItemData> thirdPartCurrent = new ArrayList<>();
        for (Mission mission :
                thirdPartyCurrentList) {
            thirdPartCurrent.add(new MissionItemData(mission));
        }

        ArrayList<Mission> thirdPartyHistoricalList = parserDb.getMissionsList(new Category(HistoricalMissions.CATEGORY_ID, "asd"));
        List<MissionItemData> thirdPartHistorical = new ArrayList<>();
        for (Mission mission :
                thirdPartyHistoricalList) {
            thirdPartHistorical.add(new MissionItemData(mission));
        }

        ArrayList<Mission> thirdPartyPotentialList = parserDb.getMissionsList(new Category(PotentialMissions.CATEGORY_ID, "asd"));
        List<MissionItemData> thirdPartPotential = new ArrayList<>();
        for (Mission mission :
                thirdPartyPotentialList) {
            thirdPartPotential.add(new MissionItemData(mission));
        }

        ArrayList<Mission> esaEuemsatList = parserDb.getMissionsList(new Category(EsaEumetsat.CATEGORY_ID, "asd"));
        List<MissionItemData> esaEumetsat = new ArrayList<>();
        for (Mission mission :
                esaEuemsatList) {
            esaEumetsat.add(new MissionItemData(mission));
        }

        // Header, Child data
        listDataChild.put(listDataHeader.get(0).getName(), esaEoMissions);
        listDataChild.put(listDataHeader.get(1).getName(), esaFutureSentinels);
        listDataChild.put(listDataHeader.get(2).getName(), esaFutureExplorers);
        listDataChild.put(listDataHeader.get(3).getName(), thirdPartCurrent);
        listDataChild.put(listDataHeader.get(4).getName(), thirdPartHistorical);
        listDataChild.put(listDataHeader.get(5).getName(), thirdPartPotential);
        listDataChild.put(listDataHeader.get(6).getName(), esaEumetsat);
    }

    private void startMissionDetailFragment(MissionItemData missionItem) {
        MissionsDetailsFragment missionsDetailsFragment = MissionsDetailsFragment
                .newInstance(missionItem);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_details_container,
                        missionsDetailsFragment, MissionsDetailsFragment.class.getSimpleName())
                .commit();
    }

    private MissionItemData getMissionItemData(int groupPosition, int childPosition) {
        String listDataHeaderName = listDataHeader.get(groupPosition)
                .getName();
        return listDataChild.get(listDataHeaderName)
                .get(childPosition);
    }

    /**
     * Gets exp list view.
     *
     * @return the exp list view
     */
    public ExpandableListView getExpListView() {
        return expListView;
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
    public interface OnExtendedListFragmentListener {
    }
}
