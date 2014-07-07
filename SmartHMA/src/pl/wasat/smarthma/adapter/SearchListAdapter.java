package pl.wasat.smarthma.adapter;


import java.util.List;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.model.feed.Entry;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


public class SearchListAdapter extends ArrayAdapter<Entry> {

	public SearchListAdapter(Activity activity, List<Entry> searchList) {
		super(activity, 0, searchList);
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Activity activity = (Activity) getContext();
		LayoutInflater inflater = activity.getLayoutInflater();

		View rowView = inflater.inflate(R.layout.fragment_search_list, null);
		Entry searchItem = getItem(position);
		

		TextView textView = (TextView) rowView.findViewById(R.id.search_list_name);
		textView.setText(searchItem.getTitle());
		
		TextView dateView = (TextView) rowView.findViewById(R.id.search_listing_smallprint);
		String pubDate = "Date: " +searchItem.getDate() + ", published: " + searchItem.getDate() + ", updated: " + searchItem.getUpdated();
				
//		SimpleDateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy kk:mm:ss Z", Locale.ENGLISH);
//		Date pDate;
//		try {
//			pDate = df.parse(pubDate);
//			pubDate = "published " + DateUtils.getDateDifference(pDate) + " updated " + dataSeriesItem.getUpdated();
//		} catch (ParseException e) {
//			pubDate = "published " + dataSeriesItem.getDate() + " updated " + dataSeriesItem.getUpdated();
//			
//		}
		dateView.setText(pubDate);

		
		if (!searchItem.isRead()){
			LinearLayout row = (LinearLayout) rowView.findViewById(R.id.search_list_frag_layout);
			row.setBackgroundColor(Color.WHITE);
			textView.setTypeface(Typeface.DEFAULT_BOLD);
		}
		return rowView;

	} 
}