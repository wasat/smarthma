package pl.wasat.smarthma.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import java.util.ArrayList;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.database.FavouritesDbAdapter;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.model.entry.Entry;
import pl.wasat.smarthma.model.opml.Opml;
import pl.wasat.smarthma.utils.io.FilesWriter;
import pl.wasat.smarthma.utils.xml.OpmlBuilder;

/**
 * Handles user interaction related to sorting data.
 */
public class ExportProductsDialogFragment extends DialogFragment {

    public static ExportProductsDialogFragment newInstance() {
        return new ExportProductsDialogFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.action_clear_list)
                .setMessage(R.string.export_opml_favourites)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });
        generateAndWriteOpml();
        return builder.create();
    }

    private void generateAndWriteOpml() {
        OpmlBuilder opmlBuilder = new OpmlBuilder();
        ArrayList<Entry> entryList = obtainFavouriteEntries();
        Opml opml = opmlBuilder.createOpmlObj(entryList);
        String xmlStr = opmlBuilder.buildXml(opml);
        FilesWriter filesWriter = new FilesWriter();
        filesWriter.writeToFile(xmlStr, "favourites.opml", Const.SMARTHMA_PATH);
    }

    private ArrayList<Entry> obtainFavouriteEntries() {
        FavouritesDbAdapter dba = new FavouritesDbAdapter(getActivity());
        dba.openToRead();
        ArrayList<Entry> entryList = dba.getOMEntries();
        dba.close();
        return entryList;
    }




}
