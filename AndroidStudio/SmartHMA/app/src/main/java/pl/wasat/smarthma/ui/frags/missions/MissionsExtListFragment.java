package pl.wasat.smarthma.ui.frags.missions;

import android.app.Activity;
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
import pl.wasat.smarthma.parser.missions.EsaEuemsat.EsaEuemsat;
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
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @return A new instance of fragment ExtendedListFragment.
     */

    public static MissionsExtListFragment newInstance() {
        return new MissionsExtListFragment();
    }

    public MissionsExtListFragment() {
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
                String listDataHeaderName = listDataHeader.get(groupPosition)
                        .getName();
                MissionItemData missionItem = listDataChild.get(listDataHeaderName)
                        .get(childPosition);

                MissionsDetailsFragment missionsDetailNewFragment = MissionsDetailsFragment
                        .newInstance(missionItem);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.activity_base_details_container,
                                missionsDetailNewFragment, "MissionsDetailNewFragment")
                        .commit();
                return false;
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnExtendedListFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
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
    public interface OnExtendedListFragmentListener {
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        // Adding child data
        listDataHeader
                .add(new MissionHeaderData(0, "ESA EO Missions",
                        "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions"));
        listDataHeader
                .add(new MissionHeaderData(1,
                        "ESA Future Missions - Sentinels",
                        "https://earth.esa.int/web/guest/missions/esa-future-missions"));
        listDataHeader
                .add(new MissionHeaderData(2,
                        "ESA Future Missions - Future Earth Explorers",
                        "https://earth.esa.int/web/guest/missions/esa-future-missions"));
     /*   listDataHeader
                .add(new MissionHeaderData(3,
                        "ESA Future Missions - Candidate Earth Explorers",
                        "https://earth.esa.int/web/guest/missions/esa-future-missions"));*/
        listDataHeader
                .add(new MissionHeaderData(4,
                        "Third Party Missions - Current Missions",
                        "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions"));
        listDataHeader
                .add(new MissionHeaderData(
                        5,
                        "Third Party Missions - Historical Missions",
                        "https://earth.esa.int/web/guest/missions/3rd-party-missions/historical-missions"));
        listDataHeader
                .add(new MissionHeaderData(
                        6,
                        "Third Party Missions - Potential Third Party Missions",
                        "https://earth.esa.int/web/guest/missions/3rd-party-missions/potential-missions"));
        listDataHeader.add(new MissionHeaderData(7, "ESA / EUMETSAT",
                "https://earth.esa.int/web/guest/missions/esaeumetsat"));

        // Adding child data

        ArrayList<Mission> missionList = parserDb.getMissionsList(new Category(EsaEoMissions.CATEGORY_ID, "asd"));
        List<MissionItemData> esaEoMissions = new ArrayList<>();
        for (Mission mission :
                missionList) {
            esaEoMissions.add(new MissionItemData(mission));
        }
        /*esaEoMissions
                .add(new MissionItemData(
                        0,
                        "Sentinel-1",
                        "/web/guest/missions/esa-operational-eo-missions/sentinel-1",
                        "/image/image_gallery?uuid=23f73931-c1c7-4013-a7dc-fb506ff182e2&groupId=10174&t=1330440801857",
                        "With the objectives of Land and Ocean monitoring, Sentinel-1 will be composed of two polar-orbiting satellites operating day and night, and will perform Radar imaging, enabling them to acquire imagery regardless of the weather. The first Sentinel-1 satellite was launched on a Soyuz rocket from Europe&#39;s Spaceport in French Guiana on 03 April 2014."));
*/

        ArrayList<Mission> futureSentinelsMissionList = parserDb.getMissionsList(new Category(EsaFutureMissions.CATEGORY_ID, "asd"));

        List<MissionItemData> esaFutureSentinels = new ArrayList<>();
        List<MissionItemData> esaFutureExplorers = new ArrayList<>();
        //List<MissionItemData> esaFutureCandidates = new ArrayList<>();


        for (Mission mission :
                futureSentinelsMissionList) {
            if (mission.getId() == Adm.MISSION_ID || mission.getId() == EarthCare.MISSION_ID) {
                esaFutureExplorers.add(new MissionItemData(mission));

            } else {
                esaFutureSentinels.add(new MissionItemData(mission));
            }
        }
/*        esaFutureSentinels
                .add(new MissionItemData(0, "Sentinel-2", "", "", ""));
        esaFutureSentinels
                .add(new MissionItemData(1, "Sentinel-3", "", "", ""));
        esaFutureSentinels
                .add(new MissionItemData(2, "Sentinel-4", "", "", ""));
        esaFutureSentinels
                .add(new MissionItemData(3, "Sentinel-5", "", "", ""));
        esaFutureSentinels
                .add(new MissionItemData(4, "Sentinel-5P", "", "", ""));*/

    /*    esaFutureExplorers
                .add(new MissionItemData(0, "ADM-Aeolus", "", "", ""));
        esaFutureExplorers.add(new MissionItemData(1, "Earthcare", "", "", ""));

        esaFutureCandidates.add(new MissionItemData(0, "BIOMASS", "", "", ""));
        esaFutureCandidates.add(new MissionItemData(1, "CoReH2O", "", "", ""));
        esaFutureCandidates.add(new MissionItemData(2, "PREMIER", "", "", ""));*/

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
      /*  thirdPartHistorical.add(new MissionItemData(0, "ALOS", "", "", ""));
        thirdPartHistorical.add(new MissionItemData(1, "IRS-P3", "", "", ""));
        thirdPartHistorical.add(new MissionItemData(2, "JERS-1", "", "", ""));
        thirdPartHistorical
                .add(new MissionItemData(3, "KOMPSAT-1", "", "", ""));
        thirdPartHistorical.add(new MissionItemData(4, "Landsat RBV", "", "",
                ""));
        thirdPartHistorical.add(new MissionItemData(5, "Landsat TM/ETM", "",
                "", ""));
        thirdPartHistorical.add(new MissionItemData(6, "Nimbus-7", "", "", ""));
        thirdPartHistorical.add(new MissionItemData(7, "QuikSCAT", "", "", ""));
        thirdPartHistorical.add(new MissionItemData(8, "SeaSat", "", "", ""));*/

        ArrayList<Mission> thirdPartyPotentialList = parserDb.getMissionsList(new Category(PotentialMissions.CATEGORY_ID, "asd"));

        List<MissionItemData> thirdPartPotential = new ArrayList<>();
        for (Mission mission :
                thirdPartyPotentialList) {
            thirdPartPotential.add(new MissionItemData(mission));
        }
   /*     thirdPartPotential.add(new MissionItemData(0, "Aura OMI", "", "", ""));
        thirdPartPotential.add(new MissionItemData(1, "CBERS Satellite Series",
                "", "", ""));
        thirdPartPotential
                .add(new MissionItemData(2, "OceanSat-2", "", "", ""));
        thirdPartPotential
                .add(new MissionItemData(3, "Pleiades-HR", "", "", ""));
        thirdPartPotential
                .add(new MissionItemData(4, "QuickBird-2", "", "", ""));
        thirdPartPotential.add(new MissionItemData(5, "SAOCOM", "", "", ""));
        thirdPartPotential
                .add(new MissionItemData(6, "TerraSAR-X", "", "", ""));
        thirdPartPotential.add(new MissionItemData(7, "THEOS", "", "", ""));
*/
        ArrayList<Mission> esaEuemsatList = parserDb.getMissionsList(new Category(EsaEuemsat.CATEGORY_ID, "asd"));

        List<MissionItemData> esaEumetsat = new ArrayList<>();
        for (Mission mission :
                esaEuemsatList) {
            esaEumetsat.add(new MissionItemData(mission));
        }
    /*    esaEumetsat.add(new MissionItemData(0, "Meteosat Second Generation",
                "", "", ""));
        esaEumetsat.add(new MissionItemData(1, "MetOp", "", "", ""));*/

        // Header, Child data
        listDataChild.put(listDataHeader.get(0).getName(), esaEoMissions);
        listDataChild.put(listDataHeader.get(1).getName(), esaFutureSentinels);
        listDataChild.put(listDataHeader.get(2).getName(), esaFutureExplorers);
        listDataChild.put(listDataHeader.get(3).getName(), thirdPartCurrent);
        listDataChild.put(listDataHeader.get(4).getName(), thirdPartHistorical);
        listDataChild.put(listDataHeader.get(5).getName(), thirdPartPotential);
        listDataChild.put(listDataHeader.get(6).getName(), esaEumetsat);

    }


    public ExpandableListView getExpListView() {
        return expListView;
    }
}
