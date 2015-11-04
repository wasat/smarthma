package pl.wasat.smarthma.parser.Parser;

import android.content.Context;

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
 * Created by marcel on 2015-08-04 00:09.
 * Part of the project  SmartHMA
 */
public class Parser {
    final String sent3Url = "https://earth.esa.int/web/guest/missions/esa-future-missions/sentinel-3";
    private final String landsatUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/landsat-oli-tirs";
    final String landsatRbvUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/historical-missions/landsat-mssrbv";
    final String potentialMissionUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/potential-missions";

    private final Context context;


    public Parser(Context context) {

        this.context = context;
        ParserDb parserDb = new ParserDb(context);
        parserDb.open();
        parserDb.truncateTables();
        parserDb.close();
    }

    public void cat0() {
        try {
            String esaEoMissionsUrl = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions";
            EsaEoMissions esaEoMissions = new EsaEoMissions(esaEoMissionsUrl, context);
            esaEoMissions.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String sentinelUrl = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/sentinel-1";
            Sentinel1 sentinel1 = new Sentinel1(sentinelUrl, context);
            sentinel1.mainContent();
            sentinel1.news();
            sentinel1.milestones();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String swarmUrl = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/swarm";
            Swarm swarm = new Swarm(swarmUrl, context);
            swarm.mainContent();
            swarm.faq();
            swarm.news();
            swarm.other();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String smosUrl = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/smos";
            Smos smos = new Smos(smosUrl, context);
            smos.mainContent();
            smos.objectives();
            smos.satellite();
            smos.instruments();
            smos.scientificRequirements();
            smos.operations();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String probaVurl = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/proba-v";
            ProbaV probaV = new ProbaV(probaVurl, context);
            //probaV.imageOfTheWeek();
            probaV.mainContent();
            probaV.faq();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String proba1Url = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/proba";
            Proba1 proba1 = new Proba1(proba1Url, context);
            proba1.mainContent();
            proba1.satellite();
            proba1.groundSegment();
            proba1.instruments();
            proba1.history();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String goceUrl = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/goce";
            Goce goce = new Goce(goceUrl, context);
            goce.mainContent();
            goce.science();
            goce.objectives();
            goce.satellite();
            goce.groundSegment();
            goce.instruments();
            goce.applications();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String cryoSatUrl = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/cryosat";
            CryoSat cryoSat = new CryoSat(cryoSatUrl, context);
            cryoSat.mainContent();
            cryoSat.overview();
            cryoSat.objectives();
            cryoSat.satellite();
            cryoSat.groundSegment();
            cryoSat.instruments();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String envisatUrl = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/envisat";
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String ersUrl = "https://earth.esa.int/web/guest/missions/esa-operational-eo-missions/ers";
            Ers ers = new Ers(ersUrl, context);
            ers.mainContent();
            ers.instruments();
            ers.groundSegment();
            ers.operations();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //CAT 1
    public void cat1() {


        try {
            String esaFutureMissionsUrl = "https://earth.esa.int/web/guest/missions/esa-future-missions";
            EsaFutureMissions esaFutureMissions = new EsaFutureMissions(esaFutureMissionsUrl, context);
            esaFutureMissions.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String sentinel2Url = "https://earth.esa.int/web/guest/missions/esa-future-missions/sentinel-2";
            Sentinel2 sentinel2 = new Sentinel2(sentinel2Url, context);
            sentinel2.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String sentinel3Url = "https://earth.esa.int/web/guest/missions/esa-future-missions/sentinel-3";
            Sentinel3 sentinel3 = new Sentinel3(sentinel3Url, context);
            sentinel3.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String sentinel4Url = "https://earth.esa.int/web/guest/missions/esa-future-missions/sentinel-4";
            Sentinel4 sentinel4 = new Sentinel4(sentinel4Url, context);
            sentinel4.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String sentinel5Url = "https://earth.esa.int/web/guest/missions/esa-future-missions/sentinel-5";
            Sentinel5 sentinel5 = new Sentinel5(sentinel5Url, context);
            sentinel5.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String sentinel5pUrl = "https://earth.esa.int/web/guest/missions/esa-future-missions/sentinel-5p";
            Sentinel5p sentinel5p = new Sentinel5p(sentinel5pUrl, context);
            sentinel5p.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String admUrl = "https://earth.esa.int/web/guest/missions/esa-future-missions/adm-aeolus";
            Adm adm = new Adm(admUrl, context);
            adm.mainContent();
            adm.milestones();
            adm.objectives();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String earthCareUrl = "https://earth.esa.int/web/guest/missions/esa-future-missions/earthcare";
            EarthCare earthCare = new EarthCare(earthCareUrl, context);
            earthCare.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //CAT 2

    public void cat2() {
        try {
            String thirdPartyUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions";
            ThirdPartyMissions thirdPartyMissions = new ThirdPartyMissions(thirdPartyUrl, context);
            thirdPartyMissions.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String auraOmiUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/aura-omi";
            AuraOmi auraOmi = new AuraOmi(auraOmiUrl, context);
            auraOmi.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String cosmoSkyMedUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/cosmo-skymed";
            CosmoSkyMed cosmoSkyMed = new CosmoSkyMed(cosmoSkyMedUrl, context);
            cosmoSkyMed.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String deimos1Url = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/deimos-1";
            Deimos1 deimos1 = new Deimos1(deimos1Url, context);
            deimos1.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String gosatUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/gosat";
            Gosat gosat = new Gosat(gosatUrl, context);
            gosat.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String graceUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/grace";
            Grace grace = new Grace(graceUrl, context);
            grace.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String ikonos2Url = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/ikonos-2";
            Ikonos2 ikonos2 = new Ikonos2(ikonos2Url, context);
            ikonos2.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String irsP6Url = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/irs-p6";
            IrsP6 irsP6 = new IrsP6(irsP6Url, context);
            irsP6.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String kompsat2Url = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/kompsat-2";
            Kompsat2 kompsat2 = new Kompsat2(kompsat2Url, context);
            kompsat2.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            LandsatOliTiris landsatOliTiris = new LandsatOliTiris(landsatUrl, context);
            landsatOliTiris.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String novaAvhrrUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/noaa-avhrr";
            NovaAvhrr novaAvhrr = new NovaAvhrr(novaAvhrrUrl, context);
            novaAvhrr.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String odinUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/odin";
            Odin odin = new Odin(odinUrl, context);
            odin.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String pleiadesUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/pleiades-hr";
            Pleiades pleiades = new Pleiades(pleiadesUrl, context);
            pleiades.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String radarsatUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/radarsat";
            Radarsat radarsat = new Radarsat(radarsatUrl, context);
            radarsat.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String rapidEyeUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/rapideye";
            RapidEye rapidEye = new RapidEye(rapidEyeUrl, context);
            rapidEye.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String sciSatUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/scisat";
            SciSat sciSat = new SciSat(sciSatUrl, context);
            sciSat.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String seaWifsUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/seawifs";
            SeaWifs seaWifs = new SeaWifs(seaWifsUrl, context);
            seaWifs.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String spotUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/spot";
            Spot spot = new Spot(spotUrl, context);
            spot.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String spot1Url = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/spot-1";
            Spot1 spot1 = new Spot1(spot1Url, context);
            spot1.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String spot2Url = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/spot-2";
            Spot2 spot2 = new Spot2(spot2Url, context);
            spot2.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String spot3Url = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/spot-3";
            Spot3 spot3 = new Spot3(spot3Url, context);
            spot3.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String spot4Url = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/spot-4";
            Spot4 spot4 = new Spot4(spot4Url, context);
            spot4.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String spot5Url = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/spot-5";
            Spot5 spot5 = new Spot5(spot5Url, context);
            spot5.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String spot67Url = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/spot-6-7";
            Spot67 spot67 = new Spot67(spot67Url, context);
            spot67.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String terraAruaModisUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/terraaqua-modis";
            TerraAquaModis terraAquaModis = new TerraAquaModis(terraAruaModisUrl, context);
            terraAquaModis.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String ukDmcUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/current-missions/uk-dmc";
            UkDmc ukDmc = new UkDmc(ukDmcUrl, context);
            ukDmc.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //CAT 3

    public void cat3() {
        try {
            String historicalMissionsUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/historical-missions";
            HistoricalMissions historicalMissions = new HistoricalMissions(historicalMissionsUrl, context);
            historicalMissions.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String alosUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/historical-missions/alos";
            Alos alos = new Alos(alosUrl, context);
            alos.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String irsP3Url = "https://earth.esa.int/web/guest/missions/3rd-party-missions/historical-missions/irs-p3";
            IrsP3 irsP3 = new IrsP3(irsP3Url, context);
            irsP3.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String jers1Url = "https://earth.esa.int/web/guest/missions/3rd-party-missions/historical-missions/jers-1";
            Jers1 jers1 = new Jers1(jers1Url, context);
            jers1.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String kompsat1Url = "https://earth.esa.int/web/guest/missions/3rd-party-missions/historical-missions/kompsat-1";
            Kompsat1 kompsat1 = new Kompsat1(kompsat1Url, context);
            kompsat1.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            LandsatRbv landsatRbv = new LandsatRbv(landsatUrl, context);
            landsatRbv.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String landsatTmEtmUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/historical-missions/landsat-tmetm";
            LandsatTmEtm landsatTmEtm = new LandsatTmEtm(landsatTmEtmUrl, context);
            landsatTmEtm.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String nimbus7Url = "https://earth.esa.int/web/guest/missions/3rd-party-missions/historical-missions/nimbus-7";
            Nimbus7 nimbus7 = new Nimbus7(nimbus7Url, context);
            nimbus7.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String quikScatUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/historical-missions/quikscat";
            QuickScat quickScat = new QuickScat(quikScatUrl, context);
            quickScat.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String seaSatUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/historical-missions/seasat";
            SeaSat seaSat = new SeaSat(seaSatUrl, context);
            seaSat.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //CAT 4
    public void cat4() {

        try {
            String potentialMissionsUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/potential-missions";
            PotentialMissions potentialMissions = new PotentialMissions(potentialMissionsUrl, context);
            potentialMissions.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String cbersUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/potential-missions/cbers";
            Cbers cbers = new Cbers(cbersUrl, context);
            cbers.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String oceanSat2Url = "https://earth.esa.int/web/guest/missions/3rd-party-missions/potential-missions/oceansat-2";
            OceanSat2 oceanSat2 = new OceanSat2(oceanSat2Url, context);
            oceanSat2.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String quickBird2Url = "https://earth.esa.int/web/guest/missions/3rd-party-missions/potential-missions/quickbird-2";
            QuickBird2 quickBird2 = new QuickBird2(quickBird2Url, context);
            quickBird2.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String saocomUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/potential-missions/saocom";
            Saocom saocom = new Saocom(saocomUrl, context);
            saocom.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String terraSarXUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/potential-missions/terrasar-x";
            TerraSarX terraSarX = new TerraSarX(terraSarXUrl, context);
            terraSarX.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String theosUrl = "https://earth.esa.int/web/guest/missions/3rd-party-missions/potential-missions/theos";
            Theos theos = new Theos(theosUrl, context);
            theos.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //CAT 5

    public void cat5() {
        try {
            String esaEumsatUrl = "https://earth.esa.int/web/guest/missions/esaeumetsat";
            EsaEuemsat esaEuemsat = new EsaEuemsat(esaEumsatUrl, context);
            esaEuemsat.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String meteosatUrl = "https://earth.esa.int/web/guest/esaeumetsat/msg";
            Meteosat meteosat = new Meteosat(meteosatUrl, context);
            meteosat.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String metOpUrl = "https://earth.esa.int/web/guest/esaeumetsat/metop";
            MetOp metOp = new MetOp(metOpUrl, context);
            metOp.mainContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
