package pl.wasat.smarthma.parser.Parser;

import android.content.Context;
import android.os.AsyncTask;

import pl.wasat.smarthma.parser.database.ParserDb;
import pl.wasat.smarthma.parser.missions.EsaEoMissions.CryoSat;
import pl.wasat.smarthma.parser.missions.EsaEoMissions.Envisat;
import pl.wasat.smarthma.parser.missions.EsaEoMissions.Ers;
import pl.wasat.smarthma.parser.missions.EsaEoMissions.EsaEoMissions;
import pl.wasat.smarthma.parser.missions.EsaEoMissions.Goce;
import pl.wasat.smarthma.parser.missions.EsaEoMissions.Proba1;
import pl.wasat.smarthma.parser.missions.EsaEoMissions.ProbaV;
import pl.wasat.smarthma.parser.missions.EsaEoMissions.Sentinel1;
import pl.wasat.smarthma.parser.missions.EsaEoMissions.Smos;
import pl.wasat.smarthma.parser.missions.EsaEoMissions.Swarm;
import pl.wasat.smarthma.parser.missions.EsaEuemsat.EsaEuemsat;
import pl.wasat.smarthma.parser.missions.EsaEuemsat.MetOp;
import pl.wasat.smarthma.parser.missions.EsaEuemsat.Meteosat;
import pl.wasat.smarthma.parser.missions.EsaFutureMissions.Adm;
import pl.wasat.smarthma.parser.missions.EsaFutureMissions.EarthCare;
import pl.wasat.smarthma.parser.missions.EsaFutureMissions.EsaFutureMissions;
import pl.wasat.smarthma.parser.missions.EsaFutureMissions.Sentinel2;
import pl.wasat.smarthma.parser.missions.EsaFutureMissions.Sentinel3;
import pl.wasat.smarthma.parser.missions.EsaFutureMissions.Sentinel4;
import pl.wasat.smarthma.parser.missions.EsaFutureMissions.Sentinel5;
import pl.wasat.smarthma.parser.missions.EsaFutureMissions.Sentinel5p;
import pl.wasat.smarthma.parser.missions.HistoricalMissions.Alos;
import pl.wasat.smarthma.parser.missions.HistoricalMissions.HistoricalMissions;
import pl.wasat.smarthma.parser.missions.HistoricalMissions.IrsP3;
import pl.wasat.smarthma.parser.missions.HistoricalMissions.Jers1;
import pl.wasat.smarthma.parser.missions.HistoricalMissions.Kompsat1;
import pl.wasat.smarthma.parser.missions.HistoricalMissions.LandsatRbv;
import pl.wasat.smarthma.parser.missions.HistoricalMissions.LandsatTmEtm;
import pl.wasat.smarthma.parser.missions.HistoricalMissions.Nimbus7;
import pl.wasat.smarthma.parser.missions.HistoricalMissions.QuickScat;
import pl.wasat.smarthma.parser.missions.HistoricalMissions.SeaSat;
import pl.wasat.smarthma.parser.missions.PotentialMissions.Cbers;
import pl.wasat.smarthma.parser.missions.PotentialMissions.OceanSat2;
import pl.wasat.smarthma.parser.missions.PotentialMissions.PotentialMissions;
import pl.wasat.smarthma.parser.missions.PotentialMissions.QuickBird2;
import pl.wasat.smarthma.parser.missions.PotentialMissions.Saocom;
import pl.wasat.smarthma.parser.missions.PotentialMissions.TerraSarX;
import pl.wasat.smarthma.parser.missions.PotentialMissions.Theos;
import pl.wasat.smarthma.parser.missions.ThirdPartyMissions.AuraOmi;
import pl.wasat.smarthma.parser.missions.ThirdPartyMissions.CosmoSkyMed;
import pl.wasat.smarthma.parser.missions.ThirdPartyMissions.Deimos1;
import pl.wasat.smarthma.parser.missions.ThirdPartyMissions.Gosat;
import pl.wasat.smarthma.parser.missions.ThirdPartyMissions.Grace;
import pl.wasat.smarthma.parser.missions.ThirdPartyMissions.Ikonos2;
import pl.wasat.smarthma.parser.missions.ThirdPartyMissions.IrsP6;
import pl.wasat.smarthma.parser.missions.ThirdPartyMissions.Kompsat2;
import pl.wasat.smarthma.parser.missions.ThirdPartyMissions.LandsatOliTiris;
import pl.wasat.smarthma.parser.missions.ThirdPartyMissions.NovaAvhrr;
import pl.wasat.smarthma.parser.missions.ThirdPartyMissions.Odin;
import pl.wasat.smarthma.parser.missions.ThirdPartyMissions.Pleiades;
import pl.wasat.smarthma.parser.missions.ThirdPartyMissions.Radarsat;
import pl.wasat.smarthma.parser.missions.ThirdPartyMissions.RapidEye;
import pl.wasat.smarthma.parser.missions.ThirdPartyMissions.SciSat;
import pl.wasat.smarthma.parser.missions.ThirdPartyMissions.SeaWifs;
import pl.wasat.smarthma.parser.missions.ThirdPartyMissions.Spot;
import pl.wasat.smarthma.parser.missions.ThirdPartyMissions.Spot1;
import pl.wasat.smarthma.parser.missions.ThirdPartyMissions.Spot2;
import pl.wasat.smarthma.parser.missions.ThirdPartyMissions.Spot3;
import pl.wasat.smarthma.parser.missions.ThirdPartyMissions.Spot4;
import pl.wasat.smarthma.parser.missions.ThirdPartyMissions.Spot5;
import pl.wasat.smarthma.parser.missions.ThirdPartyMissions.Spot67;
import pl.wasat.smarthma.parser.missions.ThirdPartyMissions.TerraAquaModis;
import pl.wasat.smarthma.parser.missions.ThirdPartyMissions.ThirdPartyMissions;
import pl.wasat.smarthma.parser.missions.ThirdPartyMissions.UkDmc;

/**
 * Created by marcel on 2015-08-04.
 */
public class Parser {
	final String probaVurl = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/proba-v";
	final String esaEoMissionsUrl = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions";
	final String sentinelUrl = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/sentinel-1";
	final String swarmUrl = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/swarm";
	final String cryoSatUrl = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/cryosat";
	final String smosUrl = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/smos";
	final String goceUrl = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/goce";
	final String envisatUrl = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/envisat";
	final String potentialMissionsUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/potential-missions";
	final String sent3Url = "https://earth.esa.int/web/guest/missions/esa-future-missions/sentinel-3";
	final String proba1Url = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/proba";
	final String ersUrl = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/ers";
	final String esaFutureMissionsUrl = "https://earth.esa.int/web/guest/missions/esa-future-missions";
	final String sentinel2Url = "https://earth.esa.int/web/guest/missions/esa-future-missions/sentinel-2";
	final String sentinel3Url = "https://earth.esa.int/web/guest/missions/esa-future-missions/sentinel-3";
	final String sentinel4Url = "https://earth.esa.int/web/guest/missions/esa-future-missions/sentinel-4";
	final String sentinel5Url = "https://earth.esa.int/web/guest/missions/esa-future-missions/sentinel-5";
	final String sentinel5pUrl = "https://earth.esa.int/web/guest/missions/esa-future-missions/sentinel-5p";
	final String admUrl = "https://earth.esa.int/web/guest/missions/esa-future-missions/adm-aeolus";
	final String earthCareUrl = "https://earth.esa.int/web/guest/missions/esa-future-missions/earthcare";
	final String thirdPartyUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions";
	final String auraOmiUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/aura-omi";
	final String cosmoSkyMedUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/cosmo-skymed";
	final String deimos1Url = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/deimos-1";
	final String gosatUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/gosat";
	final String graceUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/grace";
	final String ikonos2Url = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/ikonos-2";
	final String irsP6Url = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/irs-p6";
	final String kompsat2Url = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/kompsat-2";
	final String landsatUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/landsat-oli-tirs";
	final String novaAvhrrUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/noaa-avhrr";
	final String odinUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/odin";
	final String pleiadesUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/pleiades-hr";
	final String radarsatUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/radarsat";
	final String rapidEyeUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/rapideye";
	final String sciSatUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/scisat";
	final String seaWifsUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/seawifs";
	final String spotUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/spot";
	final String spot1Url = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/spot-1";
	final String spot2Url = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/spot-2";
	final String spot3Url = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/spot-3";
	final String spot4Url= "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/spot-4";
	final String spot5Url = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/spot-5";
	final String spot67Url = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/spot-6-7";
	final String terraAruaModisUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/terraaqua-modis";
	final String ukDmcUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/uk-dmc";
	final String historicalMissionsUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/historical-missions";
	final String alosUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/historical-missions/alos";
	final String irsP3Url = "https://earth.esa.int/web/guest/missions/3rd-party-missions/historical-missions/irs-p3";
	final String jers1Url = "https://earth.esa.int/web/guest/missions/3rd-party-missions/historical-missions/jers-1";
	final String kompsat1Url = "https://earth.esa.int/web/guest/missions/3rd-party-missions/historical-missions/kompsat-1";
	final String landsatRbvUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/historical-missions/landsat-mssrbv";
	final String landsatTmEtmUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/historical-missions/landsat-tmetm";
	final String nimbus7Url = "https://earth.esa.int/web/guest/missions/3rd-party-missions/historical-missions/nimbus-7";
	final String quikScatUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/historical-missions/quikscat";
	final String seaSatUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/historical-missions/seasat";
	final String potentialMissionUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/potential-missions";
	final String cbersUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/potential-missions/cbers";
	final String oceanSat2Url = "https://earth.esa.int/web/guest/missions/3rd-party-missions/potential-missions/oceansat-2";
	final String quickBird2Url = "https://earth.esa.int/web/guest/missions/3rd-party-missions/potential-missions/quickbird-2";
	final String saocomUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/potential-missions/saocom";
	final String terraSarXUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/potential-missions/terrasar-x";
	final String theosUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/potential-missions/theos";
	final String esaEumsatUrl = "https://earth.esa.int/web/guest/missions/esaeumetsat";
	final String meteosatUrl = "https://earth.esa.int/web/guest/esaeumetsat/msg";
	final String metOpUrl = "https://earth.esa.int/web/guest/esaeumetsat/metop";

	private Context context;


	public Parser(Context context) {

		this.context=context;
		ParserDb parserDb = new ParserDb(context);
		parserDb.open();
		parserDb.truncateTables();
		parserDb.close();
	}

	public void cat0(){
		EsaEoMissions esaEoMissions = new EsaEoMissions(esaEoMissionsUrl, context);
		esaEoMissions.mainContent();

		Sentinel1 sentinel1 = new Sentinel1(sentinelUrl, context);
		sentinel1.mainContent();
		sentinel1.news();
		sentinel1.milestones();

		Swarm swarm = new Swarm(swarmUrl, context);
		swarm.mainContent();
		swarm.faq();
		swarm.news();
		swarm.other();

		Smos smos = new Smos(smosUrl, context);
		smos.mainContent();
		smos.objectives();
		smos.satellite();
		smos.instruments();
		smos.scientificRequirements();
		smos.operations();

		ProbaV probaV = new ProbaV(probaVurl, context);
		probaV.imageOfTheWeek();
		probaV.mainContent();
		probaV.faq();

		Proba1 proba1 = new Proba1(proba1Url, context);
		proba1.mainContent();
		proba1.satellite();
		proba1.groundSegment();
		proba1.instruments();
		proba1.history();

		Goce goce = new Goce(goceUrl, context);
		goce.mainContent();
		goce.science();
		goce.objectives();
		goce.satellite();
		goce.groundSegment();
		goce.instruments();
		goce.applications();

		CryoSat cryoSat = new CryoSat(cryoSatUrl, context);
		cryoSat.mainContent();
		cryoSat.overview();
		cryoSat.objectives();
		cryoSat.satellite();
		cryoSat.groundSegment();
		cryoSat.instruments();

		Envisat envisat = new Envisat(envisatUrl, context);
		envisat.history();
		envisat.industry();
		envisat.mainContent();
		envisat.objectives();
		envisat.satellite();
		envisat.groundSegment();
		envisat.industry();
		envisat.operations();
		envisat.applications();

		Ers ers = new Ers(ersUrl, context);
		ers.mainContent();
		ers.instruments();
		ers.groundSegment();
		ers.operations();
	}

	//CAT 1
	public void cat1() {


		EsaFutureMissions esaFutureMissions = new EsaFutureMissions(esaFutureMissionsUrl, context);
		esaFutureMissions.mainContent();

		Sentinel2 sentinel2 = new Sentinel2(sentinel2Url, context);
		sentinel2.mainContent();

		Sentinel3 sentinel3 = new Sentinel3(sentinel3Url, context);
		sentinel3.mainContent();

		Sentinel4 sentinel4 = new Sentinel4(sentinel4Url, context);
		sentinel4.mainContent();

		Sentinel5 sentinel5 = new Sentinel5(sentinel5Url, context);
		sentinel5.mainContent();

		Sentinel5p sentinel5p = new Sentinel5p(sentinel5pUrl, context);
		sentinel5p.mainContent();

		Adm adm = new Adm(admUrl, context);
		adm.mainContent();
		adm.milestones();
		adm.objectives();

		EarthCare earthCare = new EarthCare(earthCareUrl, context);
		earthCare.mainContent();
	}


		//CAT 2

	public void cat2() {
		ThirdPartyMissions thirdPartyMissions = new ThirdPartyMissions(thirdPartyUrl, context);
		thirdPartyMissions.mainContent();

		AuraOmi auraOmi = new AuraOmi(auraOmiUrl, context);
		auraOmi.mainContent();

		CosmoSkyMed cosmoSkyMed = new CosmoSkyMed(cosmoSkyMedUrl, context);
		cosmoSkyMed.mainContent();

		Deimos1 deimos1 = new Deimos1(deimos1Url, context);
		deimos1.mainContent();

		Gosat gosat = new Gosat(gosatUrl, context);
		gosat.mainContent();

		Grace grace = new Grace(graceUrl, context);
		grace.mainContent();

		Ikonos2 ikonos2 = new Ikonos2(ikonos2Url, context);
		ikonos2.mainContent();

		IrsP6 irsP6 = new IrsP6(irsP6Url, context);
		irsP6.mainContent();

		Kompsat2 kompsat2 = new Kompsat2(kompsat2Url, context);
		kompsat2.mainContent();

		LandsatOliTiris landsatOliTiris = new LandsatOliTiris(landsatUrl, context);
		landsatOliTiris.mainContent();

		NovaAvhrr novaAvhrr = new NovaAvhrr(novaAvhrrUrl, context);
		novaAvhrr.mainContent();

		Odin odin = new Odin(odinUrl, context);
		odin.mainContent();

		Pleiades pleiades = new Pleiades(pleiadesUrl, context);
		pleiades.mainContent();

		Radarsat radarsat = new Radarsat(radarsatUrl, context);
		radarsat.mainContent();

		RapidEye rapidEye = new RapidEye(rapidEyeUrl, context);
		rapidEye.mainContent();

		SciSat sciSat = new SciSat(sciSatUrl, context);
		sciSat.mainContent();

		SeaWifs seaWifs = new SeaWifs(seaWifsUrl, context);
		seaWifs.mainContent();

		Spot spot = new Spot(spotUrl, context);
		spot.mainContent();

		Spot1 spot1 = new Spot1(spot1Url, context);
		spot1.mainContent();

		Spot2 spot2 = new Spot2(spot2Url, context);
		spot2.mainContent();

		Spot3 spot3 = new Spot3(spot3Url, context);
		spot3.mainContent();

		Spot4 spot4 = new Spot4(spot4Url, context);
		spot4.mainContent();

		Spot5 spot5 = new Spot5(spot5Url, context);
		spot5.mainContent();

		Spot67 spot67 = new Spot67(spot67Url, context);
		spot67.mainContent();

		TerraAquaModis terraAquaModis = new TerraAquaModis(terraAruaModisUrl, context);
		terraAquaModis.mainContent();

		UkDmc ukDmc = new UkDmc(ukDmcUrl, context);
		ukDmc.mainContent();
	}
		//CAT 3

	public void cat3() {
		HistoricalMissions historicalMissions = new HistoricalMissions(historicalMissionsUrl, context);
		historicalMissions.mainContent();

		Alos alos = new Alos(alosUrl, context);
		alos.mainContent();

		IrsP3 irsP3 = new IrsP3(irsP3Url, context);
		irsP3.mainContent();

		Jers1 jers1 = new Jers1(jers1Url, context);
		jers1.mainContent();

		Kompsat1 kompsat1 = new Kompsat1(kompsat1Url, context);
		kompsat1.mainContent();

		LandsatRbv landsatRbv = new LandsatRbv(landsatUrl, context);
		landsatRbv.mainContent();

		LandsatTmEtm landsatTmEtm = new LandsatTmEtm(landsatTmEtmUrl, context);
		landsatTmEtm.mainContent();

		Nimbus7 nimbus7 = new Nimbus7(nimbus7Url, context);
		nimbus7.mainContent();

		QuickScat quickScat = new QuickScat(quikScatUrl, context);
		quickScat.mainContent();

		SeaSat seaSat = new SeaSat(seaSatUrl, context);
		seaSat.mainContent();
	}

		//CAT 4
	public void cat4() {

		PotentialMissions potentialMissions = new PotentialMissions(potentialMissionsUrl, context);
		potentialMissions.mainContent();

		Cbers cbers = new Cbers(cbersUrl, context);
		cbers.mainContent();

		OceanSat2 oceanSat2 = new OceanSat2(oceanSat2Url, context);
		oceanSat2.mainContent();

		QuickBird2 quickBird2 = new QuickBird2(quickBird2Url, context);
		quickBird2.mainContent();

		Saocom saocom = new Saocom(saocomUrl, context);
		saocom.mainContent();

		TerraSarX terraSarX = new TerraSarX(terraSarXUrl, context);
		terraSarX.mainContent();

		Theos theos = new Theos(theosUrl, context);
		theos.mainContent();
	}
		//CAT 5

	public void  cat5() {
		EsaEuemsat esaEuemsat = new EsaEuemsat(esaEumsatUrl, context);
		esaEuemsat.mainContent();

		Meteosat meteosat = new Meteosat(meteosatUrl, context);
		meteosat.mainContent();

		MetOp metOp = new MetOp(metOpUrl, context);
		metOp.mainContent();

	}



}
