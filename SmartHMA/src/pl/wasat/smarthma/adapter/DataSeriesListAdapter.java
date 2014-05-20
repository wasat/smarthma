package pl.wasat.smarthma.adapter;


import java.util.List;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.model.dataseries.Entry;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


public class DataSeriesListAdapter extends ArrayAdapter<Entry> {

	public DataSeriesListAdapter(Activity activity, List<Entry> dataSeriesList) {
		super(activity, 0, dataSeriesList);
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Activity activity = (Activity) getContext();
		LayoutInflater inflater = activity.getLayoutInflater();

		View rowView = inflater.inflate(R.layout.fragment_dataseries_list, null);
		Entry dataSeriesItem = getItem(position);
		

		TextView textView = (TextView) rowView.findViewById(R.id.dataseries_list_name);
		textView.setText(dataSeriesItem.getTitle());
		
		TextView dateView = (TextView) rowView.findViewById(R.id.dataseries_listing_smallprint);
		String pubDate = "Date: " +dataSeriesItem.getDate() + ", published: " + dataSeriesItem.getDate() + ", updated: " + dataSeriesItem.getUpdated();
				
//		SimpleDateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy kk:mm:ss Z", Locale.ENGLISH);
//		Date pDate;
//		try {
//			pDate = df.parse(pubDate);
//			pubDate = "published " + DateUtils.getDateDifference(pDate) + " updated " + dataSeriesItem.getUpdated();
//		} catch (ParseException e) {
//			//Log.e("DATE PARSING", "Error parsing date..");
//			pubDate = "published " + dataSeriesItem.getDate() + " updated " + dataSeriesItem.getUpdated();
//			
//		}
		dateView.setText(pubDate);

		
		if (!dataSeriesItem.isRead()){
			LinearLayout row = (LinearLayout) rowView.findViewById(R.id.dataseries_list_frag_layout);
			row.setBackgroundColor(Color.WHITE);
			textView.setTypeface(Typeface.DEFAULT_BOLD);
		}
		return rowView;

	} 
}