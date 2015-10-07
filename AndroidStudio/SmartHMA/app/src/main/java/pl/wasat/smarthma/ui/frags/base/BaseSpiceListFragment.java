/**
 *
 */
package pl.wasat.smarthma.ui.frags.base;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.ListFragment;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import pl.wasat.smarthma.model.feed.Feed;
import pl.wasat.smarthma.services.SmartHmaHttpSpiceService;
import pl.wasat.smarthma.ui.frags.dialog.ExceptionDialogFragment;
import pl.wasat.smarthma.utils.rss.SpiceExceptionHandler;

/**
 * @author Daniel Zinkiewicz Wasat Sp. z o.o 14-07-2014
 */
public class BaseSpiceListFragment extends ListFragment implements
        RequestListener<Feed> {

    protected Boolean stopSearch = false;

    private final SpiceManager smartHMASpiceManager = new SpiceManager(
            SmartHmaHttpSpiceService.class);

    @Override
    public void onStart() {
        super.onStart();
        smartHMASpiceManager.start(getActivity());
    }

    @Override
    public void onStop() {
        if (smartHMASpiceManager.isStarted()) {
            smartHMASpiceManager.shouldStop();
        }
        super.onStop();
    }

    protected SpiceManager getSpiceManager() {
        return smartHMASpiceManager;
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

        SpiceExceptionHandler sExc = new SpiceExceptionHandler(getActivity(), spiceException);
        sExc.invoke();
        showDialog(sExc.getExTextMessage(), sExc.getExRawMessage(), sExc.getExRawCause());
/*
        String messTxt;
        String exRawMessage = spiceException.getMessage();
        String exRawCause = spiceException.getCause().getMessage();

        if (spiceException.getCause() instanceof HttpResponseException) {
            FedeoExceptionHandler fedHr = null;
            try {
                //exRawMessage = spiceException.getMessage();
                //exRawCause = spiceException.getCause().toString();

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
                Log.e("RSS Parser Config", e.toString());
            }

            assert fedHr != null;
            messTxt = fedHr.getFedeoException().getExceptionReport()
                    .getException().getExceptionText().getText();

        } else {
            messTxt = getString(R.string.wide_area_or_timespan);
        }

        showDialog(messTxt, exRawMessage, exRawCause);*/

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

    private void showDialog(String messText, String exRawMessage, String exRawCause) {
        DialogFragment exceptionDialogFragment = ExceptionDialogFragment.newInstance(
                messText, exRawMessage, exRawCause);
        exceptionDialogFragment.show(getFragmentManager(), "ExceptionDialogFragment");
    }

}
