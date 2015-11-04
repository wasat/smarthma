package pl.wasat.smarthma.customviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.octo.android.robospice.spicelist.SpiceListItemView;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.model.CollectionsGroup;

public class CollectionsGroupView extends RelativeLayout implements SpiceListItemView<CollectionsGroup> {

    private TextView groupNameTextView;
    private TextView groupContentTextView;
    private ImageView thumbImageView;
    private CollectionsGroup group;

    public CollectionsGroupView(Context context) {
        super(context);
        inflateView(context);
    }

    private void inflateView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_cell_entry_item, this);
        this.groupNameTextView = (TextView) this.findViewById(R.id.entry_item_title_textview);
        this.groupContentTextView = (TextView) this.findViewById(R.id.entry_item_dates_textview);
        this.thumbImageView = (ImageView) this.findViewById(R.id.entry_item_thumb_imageview);
    }

    @Override
    public void update(CollectionsGroup collectionGroup) {
        this.group = collectionGroup;
        groupNameTextView.setText(collectionGroup.getGroupName());
        groupContentTextView.setText(String.valueOf(collectionGroup.getStandard()));
        //thumbImageView.setImageResource(R.drawable.eo_coll_group);

        //Picasso.with(getContext()).load(R.drawable.eo_coll_group).resize(72, 72).centerCrop().into(thumbImageView);
    }

    @Override
    public CollectionsGroup getData() {
        return group;
    }

    @Override
    public ImageView getImageView(int imageIndex) {
        return thumbImageView;
    }

    @Override
    public int getImageViewCount() {
        return 1;
    }
}
