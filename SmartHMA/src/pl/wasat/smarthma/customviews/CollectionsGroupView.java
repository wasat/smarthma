package pl.wasat.smarthma.customviews;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.model.CollectionsGroup;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.octo.android.robospice.spicelist.SpiceListItemView;

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
        LayoutInflater.from(context).inflate(R.layout.view_cell_collection_groups, this);
        this.groupNameTextView = (TextView) this.findViewById(R.id.collections_group_name_textview);
        this.groupContentTextView = (TextView) this.findViewById(R.id.collections_group_std_textview);
        this.thumbImageView = (ImageView) this.findViewById(R.id.collections_group_thumb_imageview);
    }

    @Override
    public void update(CollectionsGroup collectionGroup) {
        this.group = collectionGroup;
        groupNameTextView.setText(collectionGroup.getGroupName());
        groupContentTextView.setText(String.valueOf(collectionGroup.getStandard()));
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
