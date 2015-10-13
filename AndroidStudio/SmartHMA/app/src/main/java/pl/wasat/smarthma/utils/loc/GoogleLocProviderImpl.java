package pl.wasat.smarthma.utils.loc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.util.Log;

/**
 * Created by Daniel on 2015-06-19.
 * This file is a part of SmartHMA project.
 */
public abstract class GoogleLocProviderImpl extends BroadcastReceiver implements GoogleLocProvider {
    public static final String GOOGLE_FUSED = "GOOGLE_FUSED";
    public static final String GOOGLE_ANDROID = "GOOGLE_ANDROID";
    public static final String GOOGLE_PROVIDER_TYPE = "GOOGLE_PROVIDER_TYPE";
    public static final String IS_SUCCESS = "IS_SUCCESS";
    public static final String GOOGLE_LOC_BROADCAST_SENT = "pl.wasat.navin.GOOGLE_LOC_BROADCAST_SENT";
    private static final Double DEFAULT_LAT = 50.117286; //Centre of EU
    private static final Double DEFAULT_LON = 9.247769;  //Centre of EU

    private final Context context;
    private FusedLocProviderImpl fusedLocProvider;
    private AndroidLocProviderImpl androidLocProvider;
    private Location calculatedPosition;
    private Boolean isStarted;

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

    private void initDefaultPosition() {
        //Centre of EU
        calculatedPosition = new Location("GOOGLE_DEFAULT");
        calculatedPosition.setLatitude(DEFAULT_LAT);
        calculatedPosition.setLongitude(DEFAULT_LON);
    }

    private void initNullPosition() {
        try {
            calculatedPosition = null;
            throw new Exception("Start position is not possible to calculation");
        } catch (Exception e) {
            e.printStackTrace();
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
        //Log.i(GoogleLocProviderImpl.class.getName(), "onInitialise - " + calculatedPosition.toString());
    }


    private void onInitialiseFailed(String providerName) {
        if (providerName.equals(GOOGLE_FUSED)) {
            androidLocProvider = new AndroidLocProviderImpl(context);
            androidLocProvider.start();
        } else if (providerName.equals(GOOGLE_ANDROID)) {
            //Log.i(GoogleLocProviderImpl.class.getName(), "onInitialiseFailed");
            Log.i("SMARTHMA", "Start position is not possible to calculation. Start position is set on centre of EU");
            stop();
            //buildAndSendBroadcast(true);

        }
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
        //Log.i(GoogleLocProviderImpl.class.getName(), "onReceive");
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
}
