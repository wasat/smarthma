package pl.wasat.smarthma.services;

import android.app.Application;

import com.octo.android.robospice.okhttp.OkHttpSpiceService;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.exception.CacheCreationException;

public class SearchCollectionsHttpSpiceService extends OkHttpSpiceService {

    @Override
    public CacheManager createCacheManager(Application application) throws CacheCreationException {
        return new CacheManager();
    }

}
