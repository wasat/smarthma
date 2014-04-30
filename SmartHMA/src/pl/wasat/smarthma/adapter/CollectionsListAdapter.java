/*package pl.wasat.smarthma.adapter;

import java.util.List;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.custom_view.CollectionsViewHolder;
import pl.wasat.smarthma.model.Collection_old;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class CollectionsListAdapter extends ArrayAdapter<Collection_old> {

	private final LayoutInflater layoutInflater;

	public CollectionsListAdapter(Context context, List<Collection_old> objects) {
		super(context, R.layout.list_row_layer, objects);
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		final Collection_old collection = getItem(position);
		View collectionListRow = convertView;
		TextView collectionNameView;
		CheckBox collectionCheckBox;

		if (collectionListRow == null) {
			collectionListRow = layoutInflater.inflate(R.layout.list_row_layer,
					parent, false);

			collectionNameView = (TextView) collectionListRow
					.findViewById(R.id.layer_title);
			collectionCheckBox = (CheckBox) collectionListRow
					.findViewById(R.id.layer_checkbox);

			
			 * checkBox.setOnCheckedChangeListener(new
			 * CompoundButton.OnCheckedChangeListener() {
			 * 
			 * @Override public void onCheckedChanged(CompoundButton buttonView,
			 * boolean isChecked) { wmsLayer.setOverlied(isChecked); } });
			 

			collectionListRow.setTag(new CollectionsViewHolder(
					collectionNameView, collectionCheckBox));

			// If CheckBox is toggled, update the planet it is tagged with.
			
			 * checkBox.setOnClickListener(new View.OnClickListener() { public
			 * void onClick(View v) { CheckBox cb = (CheckBox) v; WMSLayer
			 * wmsLayer = (WMSLayer) cb.getTag();
			 * wmsLayer.setOverlied(cb.isChecked()); } });
			 
		}
		// Re-use existing row view
		else {

			CollectionsViewHolder viewHolder = (CollectionsViewHolder) convertView
					.getTag();
			collectionCheckBox = viewHolder.getCheckBox();
			collectionNameView = viewHolder.getTextView();
		}

		collectionCheckBox.setTag(collection);

		collectionNameView.setText(collection.getTitle());
		collectionCheckBox.setChecked(collection.isChecked());

		return collectionListRow;
	}
}
*/