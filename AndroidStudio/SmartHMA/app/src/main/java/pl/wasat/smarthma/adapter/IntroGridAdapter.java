package pl.wasat.smarthma.adapter;

/**
 * Created by Daniel on 2015-07-30 01:41.
 * Part of the project  SmartHMA
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.ui.tooltips.Tooltip;

public class IntroGridAdapter extends BaseAdapter {
    private final Context mContext;
    private ArrayList<String> adapterNamesList = null;
    private ArrayList<String> adapterValuesList = null;
    private ArrayList<String> adapterTooltipsList = null;
    private Tooltip tooltip = null;

    public IntroGridAdapter(Context c, ArrayList<String> adapterNamesList, ArrayList<String> adapterValuesList, ArrayList<String> adapterTooltipsList) {
        this.mContext = c;
        this.adapterNamesList = adapterNamesList;
        this.adapterValuesList = adapterValuesList;
        this.adapterTooltipsList = adapterTooltipsList;
    }

    @Override
    public int getCount() {
        return adapterValuesList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            grid = inflater.inflate(R.layout.view_grid_cell_intro, null);

            TextView tvNames = (TextView) grid.findViewById(R.id.intro_grid_cell_name);
            tvNames.setText(adapterNamesList.get(position));

            TextView tvValues = (TextView) grid.findViewById(R.id.intro_grid_cell_value);
            tvValues.setText(adapterValuesList.get(position));

            final ImageView tvImages = (ImageView) grid.findViewById(R.id.intro_grid_cell_img_info);
            tvImages.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (tooltip != null) {
                        tooltip.dismiss();
                    }
                    tooltip = new Tooltip(grid.getContext(), Tooltip.TYPE_ABOVE, adapterTooltipsList.get(position));
                    if (tooltip.isDisabled()) {
                        tooltip.show(tvImages);
                    }
                }
            });
        } else {
            grid = convertView;
        }
        return grid;
    }
}
