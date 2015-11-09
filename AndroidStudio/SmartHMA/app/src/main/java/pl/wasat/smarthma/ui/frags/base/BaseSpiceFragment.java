/**
 *
 */
package pl.wasat.smarthma.ui.frags.base;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.spicelist.okhttp.OkHttpBitmapSpiceManager;

import pl.wasat.smarthma.services.SmartHmaHttpSpiceService;
import pl.wasat.smarthma.ui.frags.dialog.ExceptionDialogFragment;
import pl.wasat.smarthma.utils.rss.SpiceExceptionHandler;

/**
 * @author Daniel Zinkiewicz Wasat Sp. z o.o 14-07-2014
 */
public class BaseSpiceFragment extends Fragment {

    private final SpiceManager smartHMASpiceManager = new SpiceManager(
            SmartHmaHttpSpiceService.class);
    private final OkHttpBitmapSpiceManager spiceManagerBinary = new OkHttpBitmapSpiceManager();

    @Override
    public void onStart() {
        super.onStart();
        if (smartHMASpiceManager.getPendingRequestCount() > 0)
            smartHMASpiceManager.cancelAllRequests();
        smartHMASpiceManager.start(getActivity());

        if (spiceManagerBinary.getPendingRequestCount() > 0)
            spiceManagerBinary.cancelAllRequests();
        spiceManagerBinary.start(getActivity());
    }

    @Override
    public void onStop() {
        if (smartHMASpiceManager.isStarted())
            smartHMASpiceManager.shouldStop();
        if (spiceManagerBinary.isStarted())
            spiceManagerBinary.shouldStop();

        super.onStop();
    }

/*

    @Override
    public void onResume() {
        if (smartHMASpiceManager.getPendingRequestCount() > 0)
            smartHMASpiceManager.cancelAllRequests();
        smartHMASpiceManager.start(getActivity());

        if (spiceManagerBinary.getPendingRequestCount() > 0)
            spiceManagerBinary.cancelAllRequests();
        spiceManagerBinary.start(getActivity());
        super.onResume();
    }

    @Override
    public void onPause() {
        if (smartHMASpiceManager.isStarted())
            smartHMASpiceManager.shouldStop();
        if (spiceManagerBinary.isStarted())
            spiceManagerBinary.shouldStop();
        super.onPause();
    }
*/

    protected SpiceManager getSpiceManager() {
        return smartHMASpiceManager;
    }

    protected OkHttpBitmapSpiceManager getBitmapSpiceManager() {
        return spiceManagerBinary;
    }

    protected void parseRequestFailure(SpiceException spiceException) {
        SpiceExceptionHandler sExc = new SpiceExceptionHandler(getActivity(), spiceException);
        sExc.invoke();
        showDialog(sExc.getExTextMessage(), sExc.getExRawMessage(), sExc.getExRawCause());
    }

    private void showDialog(String messText, String exRawMessage, String exRawCause) {
        DialogFragment exceptionDialogFragment = ExceptionDialogFragment.newInstance(
                messText, exRawMessage, exRawCause);
        exceptionDialogFragment.show(getFragmentManager(), "ExceptionDialogFragment");
    }


}
