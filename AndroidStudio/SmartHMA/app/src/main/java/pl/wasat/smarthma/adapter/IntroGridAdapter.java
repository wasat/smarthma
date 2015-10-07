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
import android.widget.TextView;

import java.util.ArrayList;

import pl.wasat.smarthma.R;

public class IntroGridAdapter extends BaseAdapter {
    private final Context mContext;
    private ArrayList<String> adapterNamesList = null;
    private ArrayList<String> adapterValuesList = null;

    public IntroGridAdapter(Context c, ArrayList<String> adapterNamesList, ArrayList<String> adapterValuesList) {
        this.mContext = c;
        this.adapterNamesList = adapterNamesList;
        this.adapterValuesList = adapterValuesList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return adapterValuesList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            //grid = new View(mContext);
            grid = inflater.inflate(R.layout.view_grid_cell_intro, null);

            TextView tvNames = (TextView) grid.findViewById(R.id.intro_grid_cell_name);
            tvNames.setText(adapterNamesList.get(position));

            TextView tvValues = (TextView) grid.findViewById(R.id.intro_grid_cell_value);
            tvValues.setText(adapterValuesList.get(position));

        } else {
            grid = convertView;
        }

        return grid;
    }
}
