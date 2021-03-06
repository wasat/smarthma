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

package pl.wasat.smarthma.utils.loc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.util.Log;

import pl.wasat.smarthma.R;

/**
 * Created by Daniel on 2015-06-19.
 * This file is a part of SmartHMA project.
 */
public abstract class GoogleLocProviderImpl extends BroadcastReceiver implements GoogleLocProvider {
    /**
     * The constant GOOGLE_FUSED.
     */
    public static final String GOOGLE_FUSED = "GOOGLE_FUSED";
    /**
     * The constant GOOGLE_ANDROID.
     */
    public static final String GOOGLE_ANDROID = "GOOGLE_ANDROID";
    /**
     * The constant GOOGLE_PROVIDER_TYPE.
     */
    public static final String GOOGLE_PROVIDER_TYPE = "GOOGLE_PROVIDER_TYPE";
    /**
     * The constant IS_SUCCESS.
     */
    public static final String IS_SUCCESS = "IS_SUCCESS";
    /**
     * The constant GOOGLE_LOC_BROADCAST_SENT.
     */
    public static final String GOOGLE_LOC_BROADCAST_SENT = "pl.wasat.smarthma.GOOGLE_LOC_BROADCAST_SENT";
    private static final Double DEFAULT_LAT = 50.117286; //Centre of EU
    private static final Double DEFAULT_LON = 9.247769;  //Centre of EU

    private final Context context;
    private FusedLocProviderImpl fusedLocProvider;
    private AndroidLocProviderImpl androidLocProvider;
    private Location calculatedPosition;
    private Boolean isStarted;

    /**
     * Instantiates a new Google loc provider.
     *
     * @param context the context
     */
    public GoogleLocProviderImpl(Context context) {
        this.context = context;
    }

    @Override
    public void start() {
        IntentFilter googleLocIntentFilter = new IntentFilter(GOOGLE_LOC_BROADCAST_SENT);
        context.registerReceiver(this, googleLocIntentFilter);

        fusedLocProvider = new FusedLocProviderImpl(context);
        fusedLocProvider.start();
        isStarted = true;
    }

    @Override
    public void stop() {
        if (fusedLocProvider != null) {
            fusedLocProvider.stop();
            fusedLocProvider = null;
        } else if (androidLocProvider != null) {
            androidLocProvider.stop();
            androidLocProvider = null;
        }
        if (isStarted) context.unregisterReceiver(this);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String intentResult = intent.getStringExtra(GOOGLE_PROVIDER_TYPE);
        if (intent.getBooleanExtra(IS_SUCCESS, true)) {
            onInitialise(intentResult);
            onLocationReceived(calculatedPosition);
        } else {
            onInitialiseFailed(intentResult);
            if (intentResult.equals(GOOGLE_ANDROID)) {
                //initDefaultPosition();
                initNullPosition();
                onLocationReceived(calculatedPosition);
            }
        }
    }

    private void onInitialise(String providerName) {
        if (providerName.equals(GOOGLE_FUSED)) {
            calculatedPosition = fusedLocProvider.getCalculatedPosition();
            stop();
        } else if (providerName.equals(GOOGLE_ANDROID)) {
            calculatedPosition = androidLocProvider.getCalculatedPosition();
            stop();
        }
    }


    private void onInitialiseFailed(String providerName) {
        if (providerName.equals(GOOGLE_FUSED)) {
            androidLocProvider = new AndroidLocProviderImpl(context);
            androidLocProvider.start();
        } else if (providerName.equals(GOOGLE_ANDROID)) {
            Log.i("SMARTHMA", context.getString(R.string.start_pos_is_not_possible_to_calc));
            stop();
        }
    }

    private void initNullPosition() {
        try {
            calculatedPosition = null;
            throw new Exception("Start position is not possible to calculation");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initDefaultPosition() {
        //Centre of EU
        calculatedPosition = new Location("GOOGLE_DEFAULT");
        calculatedPosition.setLatitude(DEFAULT_LAT);
        calculatedPosition.setLongitude(DEFAULT_LON);
    }
}
