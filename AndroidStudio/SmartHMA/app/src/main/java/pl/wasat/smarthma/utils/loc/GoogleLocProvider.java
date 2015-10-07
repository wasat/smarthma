package pl.wasat.smarthma.utils.loc;

import android.location.Location;

/**
 * Created by Daniel on 2015-05-27 00:05.
 * Part of the project NavIn
 */
interface GoogleLocProvider {

    void start();

    void onLocationReceived(Location receivedLocation);

    void stop();


}
