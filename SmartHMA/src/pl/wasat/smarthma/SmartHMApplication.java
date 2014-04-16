/**
 * 
 */
package pl.wasat.smarthma;

import java.util.ArrayList;

import org.acra.ACRA;
import org.acra.annotation.ReportsCrashes;

import pl.wasat.smarthma.model.Collection;

import android.app.Application;

/**
 * @author Daniel
 *
 */ 

@ReportsCrashes(
		formKey = "", 
		formUri = "https://geodoplaty.cloudant.com/acra-safecitygis/_design/acra-storage/_update/report", 
		reportType = org.acra.sender.HttpSender.Type.JSON, 
		httpMethod = org.acra.sender.HttpSender.Method.POST, 
		formUriBasicAuthLogin = "julandedeattystionchaved", 
		formUriBasicAuthPassword = "S4pJd0cCSJjhAg5pmHc6TrgU"
		)

public class SmartHMApplication extends Application {

	public static ArrayList<Collection> EoCollections = new ArrayList<Collection>();
	
	//workspaces layers
	public static ArrayList<Collection> EoProducts = new ArrayList<Collection>(5);
	
	//public static long DataAge = 0;
	
	@Override
	public void onCreate() {
		super.onCreate();
		// The following line triggers the initialization of ACRA
		ACRA.init(this);
		
	}
	


}


