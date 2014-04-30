/**
 * 
 */
package pl.wasat.smarthma;

import java.util.ArrayList;

import org.acra.ACRA;
import org.acra.annotation.ReportsCrashes;

import pl.wasat.smarthma.model.Collection_old;
import pl.wasat.smarthma.model.CollectionsGroup;
import pl.wasat.smarthma.model.explaindoc.ExplainData;

import android.app.Application;

/**
 * @author Daniel
 *
 */ 

@ReportsCrashes(
		formKey = "", 
		formUri = "https://geodoplaty.cloudant.com/acra-smarthma/_design/acra-storage/_update/report", 
		reportType = org.acra.sender.HttpSender.Type.JSON, 
		httpMethod = org.acra.sender.HttpSender.Method.POST, 
		formUriBasicAuthLogin = "apabyetionedishouresseri", 
		formUriBasicAuthPassword = "1koM7DkJ13AdkJFB2teSrtLJ"
		)

public class SmartHMApplication extends Application {

	public static ArrayList<CollectionsGroup> GlobalEOData = new ArrayList<CollectionsGroup>();
	public static ExplainData GlobalExplainData = new ExplainData();
	
	//workspaces layers
	public static ArrayList<Collection_old> EoProducts = new ArrayList<Collection_old>(5);
	
	//public static long DataAge = 0;
	
	@Override
	public void onCreate() {
		super.onCreate();
		// The following line triggers the initialization of ACRA
		ACRA.init(this);
		
	}
	


}


