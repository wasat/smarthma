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

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

/**
 * Created by Daniel on 2015-06-15.
 * This file is a part of SmartHMA project.
 */
class AndroidLocProviderImpl implements LocationListener {
    private static final int REQUEST_TIMEOUT = 30000;
    private static final int ACCURACY_LEVEL = 500;
    private static final long LOC_DEGRADATION_TIME = 1800000;

    private final Context context;
    private final LocationManager locationManager;
    private Location cachedLastLocation;
    private long updateStartTime;


    /**
     * Instantiates a new Android loc provider.
     *
     * @param context the context
     */
    public AndroidLocProviderImpl(Context context) {
        this.context = context;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    public void onLocationChanged(Location location) {
        determineBestLocation(location);
    }

    private void determineBestLocation(Location newLocation) {
        if (newLocation != null) {
            long timeDiff = System.currentTimeMillis() - updateStartTime;
            if (isBetterLocation(newLocation, cachedLastLocation)) cachedLastLocation = newLocation;
            if (timeDiff > REQUEST_TIMEOUT) {
                buildAndSendBroadcast(true);
                stopLocationUpdates();
            }
        }
    }

    /**
     * Determines whether one Location reading is better than the current Location fix
     *
     * @param location            The new Location that you want to evaluate
     * @param currentBestLocation The current Location fix, to which you want to compare the new one
     */
    private boolean isBetterLocation(Location location, Location currentBestLocation) {
        if (currentBestLocation == null) {
            return true;
        }

        long timeDelta = location.getTime() - currentBestLocation.getTime();
        boolean isSignificantlyNewer = timeDelta > LOC_DEGRADATION_TIME;
        boolean isSignificantlyOlder = timeDelta < -LOC_DEGRADATION_TIME;
        boolean isNewer = timeDelta > 0;

        if (isSignificantlyNewer) {
            return true;
        } else if (isSignificantlyOlder) {
            return false;
        }

        int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
        boolean isLessAccurate = accuracyDelta > 0;
        boolean isMoreAccurate = accuracyDelta < 0;
        boolean isSignificantlyLessAccurate = accuracyDelta > 200;

        boolean isFromSameProvider = isSameProvider(location.getProvider(),
                currentBestLocation.getProvider());

        if (isMoreAccurate) {
            return true;
        } else if (isNewer && !isLessAccurate) {
            return true;
        } else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
            return true;
        }
        return false;
    }

    private boolean isSameProvider(String provider1, String provider2) {
        if (provider1 == null) {
            return provider2 == null;
        }
        return provider1.equals(provider2);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
    }

    @Override
    public void onProviderEnabled(String s) {
    }

    @Override
    public void onProviderDisabled(String s) {
    }

    /**
     * Start.
     */
    public void start() {
        initAndroidProvider();
    }

    private void initAndroidProvider() {

        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (lastKnownLocation == null)
            lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (lastKnownLocation != null) {
            long timeDiff = System.currentTimeMillis() - lastKnownLocation.getTime();
            if (timeDiff > LOC_DEGRADATION_TIME || lastKnownLocation.getAccuracy() > ACCURACY_LEVEL) {
                startLocationUpdates();
            } else {
                cachedLastLocation = lastKnownLocation;
                buildAndSendBroadcast(true);
            }
        } else {
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                    || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                startLocationUpdates();
            } else {
                //calcDefaultPosition();
                buildAndSendBroadcast(false);
            }
        }
        updateStartTime = System.currentTimeMillis();
    }

    private void startLocationUpdates() {
        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        this.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        this.locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
    }

    private void buildAndSendBroadcast(Boolean isSuccess) {
        Intent intent = new Intent();
        intent.setAction(GoogleLocProviderImpl.GOOGLE_LOC_BROADCAST_SENT);
        intent.putExtra(GoogleLocProviderImpl.IS_SUCCESS, isSuccess);
        intent.putExtra(GoogleLocProviderImpl.GOOGLE_PROVIDER_TYPE, GoogleLocProviderImpl.GOOGLE_ANDROID);
        context.sendBroadcast(intent);
    }

    /**
     * Stop.
     */
    public void stop() {
        stopLocationUpdates();
    }

    private void stopLocationUpdates() {
        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.removeUpdates(this);
    }

    /**
     * Gets calculated position.
     *
     * @return the calculated position
     */
    public Location getCalculatedPosition() {
        if (cachedLastLocation != null) {
            Location location = new Location("Android_Location");
            location.setLatitude(cachedLastLocation.getLatitude());
            location.setLongitude(cachedLastLocation.getLongitude());
            return location;
        } else {
            return null;
        }
    }
}
