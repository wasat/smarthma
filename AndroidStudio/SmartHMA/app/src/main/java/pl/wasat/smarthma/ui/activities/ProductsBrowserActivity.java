package pl.wasat.smarthma.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.TextView;

import pl.wasat.smarthma.R;
import pl.wasat.smarthma.helper.Const;
import pl.wasat.smarthma.model.FedeoRequestParams;
import pl.wasat.smarthma.ui.activities.base.BaseProductsBrowserActivity;
import pl.wasat.smarthma.ui.frags.common.ProductsListFragment;
import pl.wasat.smarthma.ui.frags.dialog.FacebookDialogFragment;
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

        productsListFragment = ProductsListFragment
                .newInstance(fedeoRequestParams);
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
                    .findFragmentByTag("FacebookDialogFragment");
            if (facebookDialogFragment == null) return;
            facebookDialogFragment.postOnActivityResult(requestCode, resultCode, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ProductsListFragment getProductsListFragment() {
        return productsListFragment;
    }
}
