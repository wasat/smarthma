package pl.wasat.smarthma.ui.frags.common;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pl.wasat.smarthma.R;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Use the {@link FailureFragment#newInstance} factory
 * method to create an instance of this fragment.
 */
public class FailureFragment extends Fragment {
    private static final String KEY_FAILURE_TEXT = "pl.wasat.smarthma.KEY_FAILURE_TEXT";

    private String failureTextParam;

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @param failureText Parameter 1.
     * @return A new instance of fragment FailureFragment.
     */
    public static FailureFragment newInstance(String failureText) {
        FailureFragment fragment = new FailureFragment();
        Bundle args = new Bundle();
        args.putString(KEY_FAILURE_TEXT, failureText);
        fragment.setArguments(args);
        return fragment;
    }

    public FailureFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            failureTextParam = getArguments().getString(KEY_FAILURE_TEXT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_failure, container, false);
    }

    /*
     * (non-Javadoc)
     *
     * @see android.support.v4.app.Fragment#onViewCreated(android.view.View,
     * android.os.Bundle)
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        TextView tvFailure = (TextView) view.findViewById(
                R.id.failure_text);
        tvFailure.setText(failureTextParam);
        super.onViewCreated(view, savedInstanceState);
    }

}
