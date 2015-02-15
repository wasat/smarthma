/**
 *
 */
package pl.wasat.smarthma.ui.frags.base;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.widget.Toast;

import com.google.api.client.http.HttpResponseException;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.model.feed.Feed;
import pl.wasat.smarthma.services.SmartHmaHttpSpiceService;
import pl.wasat.smarthma.ui.activities.BaseSmartHMActivity;
import pl.wasat.smarthma.utils.rss.FedeoExceptionHandler;

/**
 * @author Daniel Zinkiewicz Wasat Sp. z o.o 14-07-2014
 */
public class BaseSpiceListFragment extends ListFragment implements
        RequestListener<Feed> {

    protected Boolean stopSearch = false;

    private final SpiceManager samrtHMASpiceManager = new SpiceManager(
            SmartHmaHttpSpiceService.class);

    @Override
    public void onStart() {
        super.onStart();
        samrtHMASpiceManager.start(getActivity());
    }

    @Override
    public void onStop() {
        if (samrtHMASpiceManager.isStarted()) {
            samrtHMASpiceManager.shouldStop();
        }
        super.onStop();
    }

    protected SpiceManager getSpiceManager() {
        return samrtHMASpiceManager;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.octo.android.robospice.request.listener.RequestListener#onRequestFailure
     * (com.octo.android.robospice.persistence.exception.SpiceException)
     */
    @Override
    public void onRequestFailure(SpiceException spiceException) {

        String messTxt;

        if (spiceException.getCause() instanceof HttpResponseException) {
            FedeoExceptionHandler fedHr = null;
            try {

                String inStr;

                HttpResponseException exception = (HttpResponseException) spiceException
                        .getCause();
                inStr = exception.getContent();

                SAXParserFactory spf = SAXParserFactory.newInstance();
                SAXParser sp = spf.newSAXParser();
                XMLReader xr = sp.getXMLReader();

                fedHr = new FedeoExceptionHandler();

                xr.setContentHandler(fedHr);
                InputSource inputSource = new InputSource(new StringReader(
                        inStr));
                xr.parse(inputSource);

            } catch (IOException e) {
                Log.e("RSS Handler IO", e.toString());
            } catch (SAXException e) {
                Log.e("RSS Handler SAX", e.toString());
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                Log.e("RSS Handler Parser Config", e.toString());
            }

            assert fedHr != null;
            messTxt = fedHr.getFedeoException().getExceptionReport()
                    .getException().getExceptionText().getText();

        } else {
            messTxt = "Something wrong...";
        }

        Toast.makeText(getActivity(), messTxt, Toast.LENGTH_LONG).show();

        showDialog(messTxt);

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.octo.android.robospice.request.listener.RequestListener#onRequestSuccess
     * (java.lang.Object)
     */
    @Override
    public void onRequestSuccess(Feed feed) {

    }

    private void showDialog(String messText) {
        DialogFragment newFragment = ExceptionDialogFragment.newInstance(
                messText);
        newFragment.show(getFragmentManager(), "dialog");
    }

    public static class ExceptionDialogFragment extends DialogFragment {

        public static ExceptionDialogFragment newInstance(String message) {
            ExceptionDialogFragment frag = new ExceptionDialogFragment();
            Bundle args = new Bundle();
            args.putInt("title", R.string.alert_dialog_response_error);
            args.putString("Message", message);
            frag.setArguments(args);
            return frag;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            int title = getArguments().getInt("title");
            String exMess = getArguments().getString("Message");

            return new AlertDialog.Builder(getActivity())
                    .setIcon(R.drawable.ic_action_name)
                    .setTitle(title)
                    .setMessage(exMess + getString(R.string.please_correct_your_query_))
                    .setPositiveButton(R.string.alert_dialog_ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int whichButton) {
                                    ((BaseSmartHMActivity) getActivity())
                                            .doPositiveClick();
                                }
                            })
                    .setNegativeButton(R.string.alert_dialog_cancel,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int whichButton) {
                                    ((BaseSmartHMActivity) getActivity())
                                            .doNegativeClick();
                                }
                            }).create();
        }
    }

}
