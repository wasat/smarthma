package pl.wasat.smarthma.customviews;

import java.util.HashMap;
import java.util.List;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.model.mission.MissionHeaderData;
import pl.wasat.smarthma.model.mission.MissionItemData;

import com.squareup.picasso.Picasso;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class ExpandableListAdapter extends BaseExpandableListAdapter {

	private static final String EARTH_ESA_URL = "https://earth.esa.int";
	private Context _context;
	private List<MissionHeaderData> _listDataHeader; // header titles
	// child data in format of header title, child title
	private HashMap<String, List<MissionItemData>> _listDataChild;

	public ExpandableListAdapter(Context context, List<MissionHeaderData> listDataHeader,
			HashMap<String, List<MissionItemData>> listDataChild) {
		this._context = context;
		this._listDataHeader = listDataHeader;
		this._listDataChild = listDataChild;
	}

	@Override
	public Object getChild(int groupPosition, int childPosititon) {
		return this._listDataChild.get(this._listDataHeader.get(groupPosition).getName())
				.get(childPosititon);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		MissionItemData childItem = (MissionItemData) getChild(groupPosition, childPosition);
		
		

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.view_cell_missions_list_item, null);
		}
		final String childText = childItem.getName();
		TextView txtListChild = (TextView) convertView
				.findViewById(R.id.mission_child_list_item_tv);
		
		ImageView listItemImg = (ImageView) convertView
				.findViewById(R.id.mission_child_list_item_thmb_imgview);
		String childImgUrl = EARTH_ESA_URL + childItem.getImgLink(); 
		
		Picasso.with(_context)
		  .load(childImgUrl)
		  .resize(50, 50)
		  .centerInside()
		  .into(listItemImg);

		txtListChild.setText(childText);
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		String missKey =  this._listDataHeader.get(groupPosition).getName();
		List<MissionItemData> hsh = this._listDataChild.get(missKey);
		int size = hsh.size();
		 
		return size;
				
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this._listDataHeader.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this._listDataHeader.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		MissionHeaderData obj = (MissionHeaderData) getGroup(groupPosition);
		String headerTitle = obj.getName();
		
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.view_cell_missions_list_group, null);
		}

		TextView lblListHeader = (TextView) convertView
				.findViewById(R.id.missions_list_header);
		lblListHeader.setTypeface(null, Typeface.BOLD);
		lblListHeader.setText(headerTitle);

		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
