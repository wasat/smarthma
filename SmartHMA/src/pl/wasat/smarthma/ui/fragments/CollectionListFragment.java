package pl.wasat.smarthma.ui.fragments;

import java.util.ArrayList;

import org.acra.ACRA;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.SmartHMApplication;
import pl.wasat.smarthma.custom_view.CollectionsViewHolder;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.interfaces.OnCollectionsListSelectionListener;
import pl.wasat.smarthma.model.Collection_old;
import pl.wasat.smarthma.ui.activities.MapActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.mobsandgeeks.adapters.SimpleSectionAdapter;

public class CollectionListFragment extends ListFragment {

	private ArrayList<Collection_old> workspaceLayers = new ArrayList<Collection_old>();
	//private CollectionsListAdapter wmsLayerArrayAdapter;
	private SimpleSectionAdapter<Collection_old> sectionAdapter;

	OnCollectionsListSelectionListener mCallback;

	private ArrayList<Integer> chosenLayerIds = new ArrayList<Integer>();
	private String workspaceName;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		if (savedInstanceState != null) {
			// SCGIS.ALLayers =
			// savedInstanceState.getParcelableArrayList(WMSLayerListActivity.KEY_LAYER_LIST_DATA);
		}

		// Bundle args = getArguments();
		// workspaceName =
		// args.getString(Const.KEY_LIST_WORKSPACE_NAME_TO_LOAD);

		workspaceName = "test";

		//buildListAndSections();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.Fragment#onAttach(android.app.Activity)
	 */
	@Override
	public void onAttach(Activity activity) {
		if (MapActivity.TWO_PANEL_MODE) {
			try {
				mCallback = (OnCollectionsListSelectionListener) activity;
			} catch (ClassCastException e) {
				ACRA.getErrorReporter().handleSilentException(e);
				throw new ClassCastException(activity.toString()
						+ " must implement OnWMSLayerListSelectedListener");
			}
		}
		super.onAttach(activity);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		// outState.putParcelableArrayList(WMSLayerListActivity.KEY_LAYER_LIST_DATA,
		// SCGIS.ALLayers);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.support.v4.app.ListFragment#onListItemClick(android.widget.ListView
	 * , android.view.View, int, long)
	 */
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {

		Collection_old chosenLayer = (Collection_old) sectionAdapter
				.getItem(position);
		int layerId = chosenLayer.getId();
		//chosenLayer = SmartHMApplication.GlobalEOData.get(layerId);

		chosenLayer.toggleChecked();
		CollectionsViewHolder viewHolder = (CollectionsViewHolder) v.getTag();
		viewHolder.getCheckBox().setChecked(chosenLayer.isChecked());

		returnSpecifiedLayer(chosenLayer);

		super.onListItemClick(l, v, position, id);
	}

	/**
	 * 
	 */
/*	private void buildListAndSections() {

		Sectionizer<Collection_old> alphabetSectionizer = new Sectionizer<Collection_old>() {
			@Override
			public String getSectionTitleForItem(Collection_old wmsLayer) {
				return wmsLayer.getWorkspace();
			}
		};

		if (workspaceName.equalsIgnoreCase("all")) {
			wmsLayerArrayAdapter = new CollectionsListAdapter(getActivity(),
					SmartHMApplication.GlobalEOData);
		} else {
			getWorkspaceLayers();
			wmsLayerArrayAdapter = new CollectionsListAdapter(getActivity(),
					workspaceLayers);
		}
		sectionAdapter = new SimpleSectionAdapter<Collection_old>(getActivity()
				.getApplicationContext(), wmsLayerArrayAdapter,
				R.layout.list_section_header, R.id.title, alphabetSectionizer);
		setListAdapter(sectionAdapter);
	}*/

	private void getWorkspaceLayers() {
		//normWorkspaceName();
		for (int i = 0; i < SmartHMApplication.GlobalEOData.size(); i++) {
			String wName = SmartHMApplication.GlobalEOData.get(i)
					.getGroupName();
/*			if (workspaceName.equalsIgnoreCase(wName)) {
				workspaceLayers.add(SmartHMApplication.GlobalEOData.get(i));
			}*/
		}
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.menu_main_list, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.menu_item_display_layers) {

			Intent layerDataIntent = new Intent().putExtra(
					Const.KEY_LIST_ID_LAYERS_TO_DISPLAY, chosenLayerIds);
			getActivity().setResult(Activity.RESULT_OK, layerDataIntent);

			getActivity().finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void returnSpecifiedLayer(Collection_old chosenLayer) {
		int idToAdd = chosenLayer.getId();
		if (MapActivity.TWO_PANEL_MODE) {
			mCallback.onCollectionSelected(idToAdd, null);
		} else {
			//addValidLayerIds(idToAdd);
		}
	}

/*	*//**
	 * @param idToAdd
	 *//*
	private void addValidLayerIds(int idToAdd) {
		boolean toAdd = true;
		for (int i = 0; i < chosenLayerIds.size(); i++) {
			if (chosenLayerIds.get(i) == idToAdd) {
				toAdd = false;
				break;
			}
		}
		if (toAdd) {
			chosenLayerIds.add(idToAdd);
		}
	}

	private void normWorkspaceName() {
		workspaceName = workspaceName.replace(" ", "");
		workspaceName = workspaceName.split("Workspace")[0];
	}*/

	public void onBackPressed() {
		Intent layerDataIntent = new Intent().putExtra(
				Const.KEY_LIST_ID_LAYERS_TO_DISPLAY, chosenLayerIds);
		getActivity().setResult(Activity.RESULT_OK, layerDataIntent);
	}
}
