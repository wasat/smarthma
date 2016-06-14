/*
 * Copyright (c) 2016.  SmartHMA ESA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 *
 */
package pl.wasat.smarthma.ui.frags.base;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.ListFragment;
import android.util.Log;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import pl.wasat.smarthma.model.feed.Feed;
import pl.wasat.smarthma.services.SmartHmaHttpSpiceService;
import pl.wasat.smarthma.ui.dialogs.ExceptionDialogFragment;
import pl.wasat.smarthma.utils.rss.SpiceExceptionHandler;
import roboguice.util.temp.Ln;

/**
 * The type Base spice list fragment.
 *
 * @author Daniel Zinkiewicz Wasat Sp. z o.o 14-07-2014
 */
public class BaseSpiceListFragment extends ListFragment implements
        RequestListener<Feed> {

    private final SpiceManager smartHMASpiceManager = new SpiceManager(
            SmartHmaHttpSpiceService.class);
    /**
     * The Stop search.
     */
    protected Boolean stopSearch = false;

    @Override
    public void onStart() {
        super.onStart();
        Ln.getConfig().setLoggingLevel(Log.ERROR);
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

    /**
     * Gets spice manager.
     *
     * @return the spice manager
     */
    protected SpiceManager getSpiceManager() {
        return smartHMASpiceManager;
    }


}
