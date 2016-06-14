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

package pl.wasat.smarthma.ui.frags.search;

import pl.wasat.smarthma.model.osdd.OpenSearchDescription;
import pl.wasat.smarthma.ui.frags.base.BaseSearchSideParametersFragment;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link null}
 * interface to handle interaction events. Use the
 * {@link SearchBasicParametersFragment#newInstance} factory method to create an
 * instance of this fragment.
 */
public class SearchBasicParametersFragment extends BaseSearchSideParametersFragment {

    /**
     * Instantiates a new Search basic parameters fragment.
     */
    public SearchBasicParametersFragment() {
    }

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @return A new instance of fragment SearchBasicInfoRightFragment.
     */
    public static SearchBasicParametersFragment newInstance() {
        return new SearchBasicParametersFragment();
    }

    @Override
    public void onRequestSuccess(OpenSearchDescription openSearchDescription) {
        super.onRequestSuccess(openSearchDescription);

    }
}
