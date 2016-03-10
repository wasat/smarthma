package pl.wasat.smarthma.utils.loc;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by Daniel on 2015-05-27 00:15.
 * Part of the SmartHMA project
 */
class FusedLocProviderImpl implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private static final int REQUEST_TIMEOUT = 30000;
    private static final int ACCURACY_LEVEL = 300;
    private static final long LOC_DEGRADATION_TIME = 900000;
    private final Context context;
    private GoogleApiClient mGoogleApiClient;
    private Location fusedLastLocation;
    private long updateStartTime;

    public FusedLocProviderImpl(Context context) {
        this.context = context;
    }

    @Override
    public void onConnected(Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            long timeDiff = System.currentTimeMillis() - mLastLocation.getTime();
            if (timeDiff < LOC_DEGRADATION_TIME && mLastLocation.getAccuracy() < ACCURACY_LEVEL) {
                fusedLastLocation = mLastLocation;
                buildAndSendBroadcast(true);
            } else {
                startLocationUpdates();
            }
        } else {
            buildAndSendBroadcast(false);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        if (fusedLastLocation == null) {
            buildAndSendBroadcast(false);
        }
    }

    private void buildAndSendBroadcast(Boolean isSuccess) {
        Intent intent = new Intent();
        intent.setAction(GoogleLocProviderImpl.GOOGLE_LOC_BROADCAST_SENT);
        intent.putExtra(GoogleLocProviderImpl.IS_SUCCESS, isSuccess);
        intent.putExtra(GoogleLocProviderImpl.GOOGLE_PROVIDER_TYPE, GoogleLocProviderImpl.GOOGLE_FUSED);
        context.sendBroadcast(intent);
    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, createLocationRequest(), this);
        updateStartTime = System.currentTimeMillis();
    }

    private LocationRequest createLocationRequest() {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(300);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        mLocationRequest.setNumUpdates(20);
        mLocationRequest.setExpirationDuration(60000);

        return mLocationRequest;
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.e(FusedLocProviderImpl.class.getName(), "onConnectionFailed");
        buildAndSendBroadcast(false);
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            Log.i(FusedLocProviderImpl.class.getName(), location.toString());
            long timeDiff = System.currentTimeMillis() - updateStartTime;
            if (location.getAccuracy() < ACCURACY_LEVEL || timeDiff > REQUEST_TIMEOUT) {
                fusedLastLocation = location;
                buildAndSendBroadcast(true);
                stopLocationUpdates();
            }
        }
    }

    private void stopLocationUpdates() {
        if (mGoogleApiClient.isConnected())
            LocationServices.FusedLocationApi.removeLocationUpdates(
                    mGoogleApiClient, this);
    }

    public void start() {
        initFusedProvider();
    }

    private void initFusedProvider() {
        buildGoogleApiClient(context);
        mGoogleApiClient.connect();
    }

    private synchronized void buildGoogleApiClient(Context context) {
        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    public Location getCalculatedPosition() {
        if (fusedLastLocation != null) {
            Location location = new Location("Fused_Location");
            location.setLatitude(fusedLastLocation.getLatitude());
            location.setLongitude(fusedLastLocation.getLongitude());
            return location;
        } else {
            return null;
        }
    }

    public void stop() {
        stopLocationUpdates();
    }
}
