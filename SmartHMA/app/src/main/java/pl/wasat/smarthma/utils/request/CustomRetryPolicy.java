package pl.wasat.smarthma.utils.request;

import android.util.Log;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.retry.DefaultRetryPolicy;

/**
 * Created by Daniel on 2016-04-22.
 * This file is a part of module SmartHMA project.
 */
class CustomRetryPolicy extends DefaultRetryPolicy {

    @Override
    public long getDelayBeforeRetry() {
        return 15L * 1000;
    }

    @Override
    public void retry(SpiceException e) {
        Log.i("RETRY_POLICY", e.toString());
        super.retry(e);
    }
}
