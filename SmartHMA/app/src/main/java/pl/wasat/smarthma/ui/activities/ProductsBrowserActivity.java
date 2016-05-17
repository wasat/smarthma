package pl.wasat.smarthma.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.TextView;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.model.FedeoRequestParams;
import pl.wasat.smarthma.ui.activities.base.BaseProductsBrowserActivity;
import pl.wasat.smarthma.ui.dialogs.FacebookDialogFragment;
import pl.wasat.smarthma.ui.frags.common.ProductsListFragment;
import pl.wasat.smarthma.ui.menus.ProductsBrowserMenuHandler;

public class ProductsBrowserActivity extends BaseProductsBrowserActivity {

    private ProductsListFragment productsListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        TextView text = (TextView) findViewById(R.id.action_bar_title);
        text.setText(getString(R.string.activity_name_products_browser));

        FedeoRequestParams fedeoRequestParams = (FedeoRequestParams) intent.getSerializableExtra(Const.KEY_INTENT_FEDEO_REQUEST_PARAMS);
        //TODO - remove after test
        //if (fedeoRequestParams == null) fedeoRequestParams = loadTestRequestValues();

        String osddUrl = intent.getStringExtra(Const.KEY_INTENT_FEDEO_OSDD_URL);

        productsListFragment = ProductsListFragment
                .newInstance(fedeoRequestParams, osddUrl);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_base_list_container,
                        productsListFragment).commit();

        commonMenuHandler = new ProductsBrowserMenuHandler(this, R.id.menu_button);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        FragmentManager manager = getSupportFragmentManager();

        try {
            FacebookDialogFragment facebookDialogFragment = (FacebookDialogFragment) manager
                    .findFragmentByTag(FacebookDialogFragment.class.getSimpleName());
            if (facebookDialogFragment == null) return;
            facebookDialogFragment.postOnActivityResult(requestCode, resultCode, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ProductsListFragment getProductsListFragment() {
        return productsListFragment;
    }

    //TODO - for test purposes
    private FedeoRequestParams loadTestRequestValues() {
        FedeoRequestParams fedeoRequestParams = new FedeoRequestParams();
        FedeoRequestParams.IS_BUILD_FROM_SHARED = false;
        fedeoRequestParams.setTemplateUrl("http://fedeo.esa.int/opensearch/request/?httpAccept=application/atom%2Bxml&parentIdentifier=EOP:SPOT:MULTISPECTRAL&startRecord={startIndex?}&startPage={startPage?}&maximumRecords={count?}&startDate={time:start}&endDate={time:end}&geometry={geo:geometry?}&bbox={geo:box?}&name={geo:name?}&lat={geo:lat?}&lon={geo:lon?}&radius={geo:radius?}&uid={geo:uid?}&productType={eo:productType?}&platform={eo:platform?}&platformSerialIdentifier={eo:platformSerialIdentifier?}&instrument={eo:instrument?}&sensorType={eo:sensorType?}&compositeType={eo:compositeType?}&processingLevel={eo:processingLevel?}&orbitType={eo:orbitType?}&resolution={eo:resolution?}&productionStatus={eo:productionStatus?}&acquisitionType={eo:acquisitionType?}&orbitNumber={eo:orbitNumber?}&orbitDirection={eo:orbitDirection?}&track={eo:track?}&frame={eo:frame?}&swathIdentifier={eo:swathIdentifier?}&cloudCover={eo:cloudCover?}&snowCover={eo:snowCover?}&acquisitionStation={eo:acquisitionStation?}&productQualityStatus={eo:productQualityStatus?}&processorName={eo:processorName?}&sensorMode={eo:sensorMode?}&archivingCenter={eo:archivingCenter?}&acquisitionSubType={eo:acquisitionSubType?}&startTimeFromAscendingNode={eo:startTimeFromAscendingNode?}&completionTimeFromAscendingNode={eo:completionTimeFromAscendingNode?}&illuminationAzimuthAngle={eo:illuminationAzimuthAngle?}&illuminationElevationAngle={eo:illuminationElevationAngle?}&recordSchema={sru:recordSchema?}");
        fedeoRequestParams.setUrl("http://fedeo.esa.int/opensearch/request/?httpAccept=application/atom%2Bxml&parentIdentifier=EOP:SPOT:MULTISPECTRAL&startRecord=1&startPage=1&maximumRecords=30&startDate=2015-08-01T00:00:00Z&endDate=2015-09-01T00:00:00Z&bbox=14.513488,49.820297,24.58961,54.345848&recordSchema=server-choice");
        fedeoRequestParams.addOsddValue("{time:start}", "2015-08-01T00:00:00Z");
        fedeoRequestParams.addOsddValue("{time:end}", "2015-09-01T00:00:00Z");
        fedeoRequestParams.addOsddValue("{geo:box}", "14.513488,49.820297,24.58961,54.345848");
        fedeoRequestParams.addOsddValue("{sru:recordSchema}", "server-choice");
        return fedeoRequestParams;
    }
}
