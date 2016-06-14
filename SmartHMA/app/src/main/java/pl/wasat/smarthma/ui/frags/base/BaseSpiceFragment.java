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
import android.support.v4.app.Fragment;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.spicelist.okhttp.OkHttpBitmapSpiceManager;

import pl.wasat.smarthma.services.SmartHmaHttpSpiceService;
import pl.wasat.smarthma.ui.dialogs.ExceptionDialogFragment;
import pl.wasat.smarthma.utils.rss.SpiceExceptionHandler;

/**
 * The type Base spice fragment.
 *
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
        if (smartHMASpiceManager.isStarted()) {
            smartHMASpiceManager.cancelAllRequests();
            smartHMASpiceManager.dontNotifyAnyRequestListeners();
            smartHMASpiceManager.shouldStop();
        }
        if (spiceManagerBinary.isStarted())
            spiceManagerBinary.shouldStop();
        super.onStop();
    }

    /**
     * Gets spice manager.
     *
     * @return the spice manager
     */
    protected SpiceManager getSpiceManager() {
        return smartHMASpiceManager;
    }

    /**
     * Gets bitmap spice manager.
     *
     * @return the bitmap spice manager
     */
    protected OkHttpBitmapSpiceManager getBitmapSpiceManager() {
        return spiceManagerBinary;
    }

    /**
     * Parse request failure.
     *
     * @param spiceException the spice exception
     */
    protected void parseRequestFailure(SpiceException spiceException) {
        SpiceExceptionHandler sExc = new SpiceExceptionHandler(getActivity(), spiceException);
        sExc.invoke();
        showDialog(sExc.getExTextMessage(), sExc.getExRawMessage(), sExc.getExRawCause());
    }

    private void showDialog(String messText, String exRawMessage, String exRawCause) {
        DialogFragment exceptionDialogFragment = ExceptionDialogFragment.newInstance(
                messText, exRawMessage, exRawCause);
        exceptionDialogFragment.show(getFragmentManager(), ExceptionDialogFragment.class.getSimpleName());
    }
}
