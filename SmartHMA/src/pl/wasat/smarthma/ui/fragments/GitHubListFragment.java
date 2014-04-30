package pl.wasat.smarthma.ui.fragments;

import com.octo.android.robospice.JacksonSpringAndroidSpiceService;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.spicelist.okhttp.OkHttpBitmapSpiceManager;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.adapter.GitHubUserListAdapter;
import roboguice.util.temp.Ln;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ListView;

public class GitHubListFragment extends ListFragment {

	private ListView githubListView;
	private View loadingView;

	private GitHubUserListAdapter gitHubUserListAdapter;

	private SpiceManager spiceManagerJson = new SpiceManager(
			JacksonSpringAndroidSpiceService.class);
	private OkHttpBitmapSpiceManager spiceManagerBinary = new OkHttpBitmapSpiceManager();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Ln.getConfig().setLoggingLevel(Log.ERROR);
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setProgressBarIndeterminateVisibility(false);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		if (savedInstanceState != null) {
		}

	}

	@Override
	public void onStart() {
		super.onStart();
		spiceManagerJson.start(this);
		spiceManagerBinary.start(this);

		loadListGithub();

		loadListSmartHMA();

	}

	@Override
	public void onStop() {
		spiceManagerJson.shouldStop();
		spiceManagerBinary.shouldStop();
		super.onStop();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.Fragment#onAttach(android.app.Activity)
	 */
	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

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

		super.onListItemClick(l, v, position, id);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.menu_main_list, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.menu_item_display_layers) {

			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
