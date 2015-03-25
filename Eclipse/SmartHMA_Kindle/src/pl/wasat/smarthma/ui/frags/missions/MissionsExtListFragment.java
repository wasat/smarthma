package pl.wasat.smarthma.ui.frags.missions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.customviews.ExpandableListAdapter;
import pl.wasat.smarthma.model.mission.MissionHeaderData;
import pl.wasat.smarthma.model.mission.MissionItemData;

import android.app.Activity;
import android.net.Uri;
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

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the
 * {@link MissionsExtListFragment.OnExtendedListFragmentListener} interface to
 * handle interaction events. Use the {@link MissionsExtListFragment#newInstance}
 * factory method to create an instance of this fragment.
 * 
 */
public class MissionsExtListFragment extends Fragment {

	private ExpandableListAdapter listAdapter;
	private ExpandableListView expListView;
	private List<MissionHeaderData> listDataHeader;
	private HashMap<String, List<MissionItemData>> listDataChild;

	private List<MissionItemData> missionsList;

	private OnExtendedListFragmentListener mListener;

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * @return A new instance of fragment ExtendedListFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static MissionsExtListFragment newInstance() {
		return new MissionsExtListFragment();
	}

	public MissionsExtListFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			// mParam1 = getArguments().getString(ARG_PARAM1);
			// mParam2 = getArguments().getString(ARG_PARAM2);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View rootView = inflater.inflate(R.layout.fragment_missions_extended_list,
				container, false);

		// get the listview
		expListView = (ExpandableListView) rootView
				.findViewById(R.id.missions_extended_listview);

		// preparing list data
		prepareListData();

		listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader,
				listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);

		// Listview Group click listener
		expListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
//				Toast.makeText(getActivity(),
//						"Group Clicked " + listDataHeader.get(groupPosition),
//						Toast.LENGTH_SHORT).show();
				//getHtml(listDataHeader.get(groupPosition).getUrl(),	groupPosition);
				
				
				return false;
			}
		});

		// Listview Group expanded listener
		expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {
//				Toast.makeText(getActivity(),
//						listDataHeader.get(groupPosition) + " Expanded",
//						Toast.LENGTH_SHORT).show();

			}
		});

		// Listview Group collasped listener
		expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {
//				Toast.makeText(getActivity(),
//						listDataHeader.get(groupPosition) + " Collapsed",
//						Toast.LENGTH_SHORT).show();

			}
		});

		// Listview on child click listener
		expListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
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
				
//				Toast.makeText(getActivity(),
//						listDataHeaderName + " : " + missionItem.getName(),
//						Toast.LENGTH_SHORT).show();
				return false;
			}
		});

		return rootView;
	}

	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onExtendedListFragmentInteraction(uri);
		}
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
	 * <p>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnExtendedListFragmentListener {
		// TODO: Update argument type and name
		public void onExtendedListFragmentInteraction(Uri uri);
	}

	/*
	 * Preparing the list data
	 */
	private void prepareListData() {
		listDataHeader = new ArrayList<MissionHeaderData>();
		listDataChild = new HashMap<String, List<MissionItemData>>();

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
		listDataHeader
				.add(new MissionHeaderData(3,
						"ESA Future Missions - Candidate Earth Explorers",
						"https://earth.esa.int/web/guest/missions/esa-future-missions"));
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
		List<MissionItemData> esaEoMissions = new ArrayList<MissionItemData>();
		esaEoMissions
				.add(new MissionItemData(
						0,
						"Sentinel-1",
						"/web/guest/missions/esa-operational-eo-missions/sentinel-1",
						"/image/image_gallery?uuid=23f73931-c1c7-4013-a7dc-fb506ff182e2&groupId=10174&t=1330440801857",
						"With the objectives of Land and Ocean monitoring, Sentinel-1 will be composed of two polar-orbiting satellites operating day and night, and will perform Radar imaging, enabling them to acquire imagery regardless of the weather. The first Sentinel-1 satellite was launched on a Soyuz rocket from Europe&#39;s Spaceport in French Guiana on 03 April 2014."));
		esaEoMissions
				.add(new MissionItemData(
						1,
						"Swarm",
						"/web/guest/missions/esa-operational-eo-missions/swarm",
						"/image/image_gallery?img_id=121739&t=1331116303473",
						"A constellation of three satellites, the Swarm mission aims to provide the best survey of the Earth&#39;s geomagnetic field and its temporal evolution, as well as discovering new insights into our world&#39;s interior and climate."));
		esaEoMissions
				.add(new MissionItemData(
						2,
						"Proba-V",
						"/web/guest/missions/esa-operational-eo-missions/proba-v",
						"/documents/10174/428823/Proba-V_120/f5960be6-608c-4682-8840-3b1e15134591?t=1363175361821",
						"The Proba-V mission provides multispectral images to study the evolution of the vegetation cover on a daily and global basis. The &#39;V&#39; stands for Vegetation. This mission is extending the data set of the long-established Vegetation instrument, flown as a secondary payload aboard France&#39;s SPOT-4 and SPOT-5 satellites launched in 1998 and 2002 respectively."));
		esaEoMissions
				.add(new MissionItemData(
						3,
						"CryoSat",
						"/web/guest/missions/esa-operational-eo-missions/cryosat",
						"/image/image_gallery?img_id=16832",
						"ESA's Earth Explorer CryoSat is Europe's first ice mission. The aim of the mission is to determine variations in the thickness of the Earth's continental ice sheets and marine ice cover. Its primary objective is to test the prediction that Arctic sea ice is thinning due to global warming. In addition, it was important to discover the extent to which the Antarctic and Greenland ice sheets are contributing global sea level rise."));
		esaEoMissions
				.add(new MissionItemData(
						4,
						"SMOS",
						"/web/guest/missions/esa-operational-eo-missions/smos",
						"/image/image_gallery?img_id=17541",
						"ESA's Soil Moisture and Ocean Salinity (SMOS) mission has been designed to observe soil moisture over the Earth's landmasses and salinity over the oceans. Soil moisture data are urgently required for hydrological studies and data on ocean salinity are vital for improving our understanding of ocean circulation patterns."));
		esaEoMissions
				.add(new MissionItemData(
						5,
						"GOCE",
						"/web/guest/missions/esa-operational-eo-missions/goce",
						"/image/image_gallery?img_id=18333",
						"As part of ESA's Living Planet programme, the Gravity field and steady-state Ocean Circulation Explorer (GOCE) is the first of a series of Earth Explorer satellites in orbit, designed to provide information for understanding critical Earth system variables. It has been developed to bring about a whole new level of understanding of one of the Earth's most fundamental forces of nature - the gravity field."));
		esaEoMissions
				.add(new MissionItemData(
						6,
						"Envisat",
						"/web/guest/missions/esa-operational-eo-missions/envisat",
						"/image/image_gallery?img_id=117229&t=1330078690503",
						"Envisat (ENVIronmental SATellite) is an advanced polar-orbiting Earth observation satellite which provided measurements of the atmosphere, ocean, land, and ice over 10 years of operations. The Envisat satellite carried an ambitious and innovative payload that ensured the continuity of the data measurements from the ERS satellites. The archive of data received from the satellite supports Earth science research and aids in the long-term monitoring of environmental and climactic changes. Contact with Envisat was lost on 08 April 2012, and the mission was officially ended on 09 May 2012."));
		esaEoMissions
				.add(new MissionItemData(
						7,
						"Proba-1",
						"/web/guest/missions/esa-operational-eo-missions/proba",
						"/image/image_gallery?img_id=19399",
						"The PRoject for On-Board Autonomy (Proba) is a technology demonstration mission of the European Space Agency, The objectives of Proba are: in-orbit demonstration and evaluation of new hardware and software spacecraft technologies, onboard operational autonomy, and trial and demonstration of Earth observation and space environment instruments."));
		esaEoMissions
				.add(new MissionItemData(
						8,
						"ERS",
						"/web/guest/missions/esa-operational-eo-missions/ers",
						"/image/image_gallery?img_id=19410",
						"The European Remote Sensing satellite, ERS-1, launched in 1991, was ESA's first sun-synchronous polar orbiting remote sensing mission, operated until March 2000. ERS-1 carried a comprehensive payload including an imaging Synthetic Aperture Radar (SAR), a radar altimeter and other powerful instruments to measure ocean surface temperature and winds at sea. ERS-2, which overlapped with ERS-1, was launched in 1995 with an additional sensor for atmospheric ozone research. ERS-2 far exceeded its nominal lifetime and was retired on 04 July 2011."));

		List<MissionItemData> esaFutureSentinels = new ArrayList<MissionItemData>();
		esaFutureSentinels
				.add(new MissionItemData(0, "Sentinel-2", "", "", ""));
		esaFutureSentinels
				.add(new MissionItemData(1, "Sentinel-3", "", "", ""));
		esaFutureSentinels
				.add(new MissionItemData(2, "Sentinel-4", "", "", ""));
		esaFutureSentinels
				.add(new MissionItemData(3, "Sentinel-5", "", "", ""));
		esaFutureSentinels
				.add(new MissionItemData(4, "Sentinel-5P", "", "", ""));

		List<MissionItemData> esaFutureExplorers = new ArrayList<MissionItemData>();
		esaFutureExplorers
				.add(new MissionItemData(0, "ADM-Aeolus", "", "", ""));
		esaFutureExplorers.add(new MissionItemData(1, "Earthcare", "", "", ""));

		List<MissionItemData> esaFutureCandidates = new ArrayList<MissionItemData>();
		esaFutureCandidates.add(new MissionItemData(0, "BIOMASS", "", "", ""));
		esaFutureCandidates.add(new MissionItemData(1, "CoReH2O", "", "", ""));
		esaFutureCandidates.add(new MissionItemData(2, "PREMIER", "", "", ""));

		List<MissionItemData> thirdPartCurrent = new ArrayList<MissionItemData>();
		thirdPartCurrent
				.add(new MissionItemData(0, "COSMO-SkyMed", "", "", ""));
		thirdPartCurrent.add(new MissionItemData(1, "Deimos-1", "", "", ""));
		thirdPartCurrent.add(new MissionItemData(2, "GOSAT", "", "", ""));
		thirdPartCurrent.add(new MissionItemData(3, "GRACE", "", "", ""));
		thirdPartCurrent.add(new MissionItemData(4, "Ikonos-2", "", "", ""));
		thirdPartCurrent.add(new MissionItemData(5, "IRS-P6", "", "", ""));
		thirdPartCurrent.add(new MissionItemData(6, "KOMPSAT-2", "", "", ""));
		thirdPartCurrent.add(new MissionItemData(7, "Landsat OLI/TIRS", "", "",
				""));
		thirdPartCurrent.add(new MissionItemData(8, "NOAA AVHRR", "", "", ""));
		thirdPartCurrent.add(new MissionItemData(9, "Odin", "", "", ""));
		thirdPartCurrent.add(new MissionItemData(10, "Proba-1", "", "", ""));
		thirdPartCurrent.add(new MissionItemData(11, "Proba-V", "", "", ""));
		thirdPartCurrent.add(new MissionItemData(12, "RADARSAT", "", "", ""));
		thirdPartCurrent.add(new MissionItemData(13, "RapidEye", "", "", ""));
		thirdPartCurrent
				.add(new MissionItemData(14, "SciSat-1/ACE", "", "", ""));
		thirdPartCurrent.add(new MissionItemData(15, "SeaWiFS", "", "", ""));
		thirdPartCurrent.add(new MissionItemData(16, "SPOT-4", "", "", ""));
		thirdPartCurrent.add(new MissionItemData(17, "SPOT-5", "", "", ""));
		thirdPartCurrent.add(new MissionItemData(18, "Terra/Aqua MODIS", "",
				"", ""));
		thirdPartCurrent.add(new MissionItemData(19, "UK-DMC", "", "", ""));

		List<MissionItemData> thirdPartHistorical = new ArrayList<MissionItemData>();
		thirdPartHistorical.add(new MissionItemData(0, "ALOS", "", "", ""));
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
		thirdPartHistorical.add(new MissionItemData(8, "SeaSat", "", "", ""));

		List<MissionItemData> thirdPartPotential = new ArrayList<MissionItemData>();
		thirdPartPotential.add(new MissionItemData(0, "Aura OMI", "", "", ""));
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

		List<MissionItemData> esaEumetsat = new ArrayList<MissionItemData>();
		esaEumetsat.add(new MissionItemData(0, "Meteosat Second Generation",
				"", "", ""));
		esaEumetsat.add(new MissionItemData(1, "MetOp", "", "", ""));

		// Header, Child data
		listDataChild.put(listDataHeader.get(0).getName(), esaEoMissions);
		listDataChild.put(listDataHeader.get(1).getName(), esaFutureSentinels);
		listDataChild.put(listDataHeader.get(2).getName(), esaFutureExplorers);
		listDataChild.put(listDataHeader.get(3).getName(), esaFutureCandidates);
		listDataChild.put(listDataHeader.get(4).getName(), thirdPartCurrent);
		listDataChild.put(listDataHeader.get(5).getName(), thirdPartHistorical);
		listDataChild.put(listDataHeader.get(6).getName(), thirdPartPotential);
		listDataChild.put(listDataHeader.get(7).getName(), esaEumetsat);

	}

	@SuppressWarnings("unused")
	private void getHtml(String url, int groupPos) {
		Document doc;
		try {

			missionsList = new ArrayList<MissionItemData>();
			// need http protocol
			doc = Jsoup.connect(url).userAgent("Mozilla").get();
			Elements elementsByClass = doc.getElementsByClass("asset-abstract");
			int id = 0;

			for (int i = 0; i < elementsByClass.size(); i++) {
				Element el = elementsByClass.get(i);
				if (el.children().hasClass("asset-more")) {

					Element inCon = el.getElementsByClass("asset-content")
							.first().select("a").first();
					String name = inCon.text();
					String hrefLink = inCon.attr("href");

					Element imgEl = el
							.getElementsByClass("asset-abstract-imgLink")
							.first().select("img").first();
					String imgLink = imgEl.attr("src");

					String content = el.html();

					MissionItemData itemMission = new MissionItemData(id, name,
							hrefLink, imgLink, content);

					missionsList.add(itemMission);

					id = id + 1;

				}
			}

			listDataChild.put(listDataHeader.get(groupPos).getName(),
					missionsList);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
