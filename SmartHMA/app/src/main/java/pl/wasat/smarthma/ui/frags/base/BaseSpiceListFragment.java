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
import pl.wasat.smarthma.ui.dialogs.ExceptionDialogFragment;
import pl.wasat.smarthma.utils.rss.SpiceExceptionHandler;

/**
 * @author Daniel Zinkiewicz Wasat Sp. z o.o 14-07-2014
 */
public class BaseSpiceListFragment extends ListFragment implements
        RequestListener<Feed> {

    private final SpiceManager smartHMASpiceManager = new SpiceManager(
            SmartHmaHttpSpiceService.class);
    protected Boolean stopSearch = false;

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
        exceptionDialogFragment.show(getFragmentManager(), ExceptionDialogFragment.class.getSimpleName());
    }

    protected SpiceManager getSpiceManager() {
        return smartHMASpiceManager;
    }

}
